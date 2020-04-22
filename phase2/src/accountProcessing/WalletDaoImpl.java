package accountProcessing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.AccountHolder;
import exceptions.InvalidAccountDetailException;
import exceptions.LowBalanceException;

public class WalletDaoImpl implements WalletDaoInterface<AccountHolder, Integer>{


		 EntityManagerFactory emf= Persistence.createEntityManagerFactory("ZipZapZoo");
		 EntityManager em= emf.createEntityManager();
		 EntityTransaction tx= em.getTransaction();
	
	
	@Override
	public void createAccount(AccountHolder acc) {
		
		
		tx.begin();
		em.persist(acc);
		tx.commit();// add em.flush();
		System.out.println("Congrats! New account created. Your Account details are: ");
	    System.out.println(acc);	
	}

	@Override
	public void update(Integer accNum, Integer choice, String choiceValue) {
		
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("ZipZapZoo");
		EntityManager em= emf.createEntityManager();
		EntityTransaction tx= em.getTransaction();
		AccountHolder acc= viewAccountDetails(accNum);
	    tx.begin();
		   switch(choice) {
			
			case 1:{  //Change name:
				
			 	
			   try {
				(acc).setName(choiceValue);
			   }catch(InvalidAccountDetailException e1) {
				   System.err.println(e1.getMessage());
			   }
			 
					
			}break;
			
			case 2:{  //Change Mobile Number:
				
			   try {
				(acc).setMobileNum(Long.parseLong(choiceValue));
			   } catch (InvalidAccountDetailException e1) {
					System.err.println(e1.getMessage());
					
				}catch( InputMismatchException e1 ) {
					System.err.println("Enter a number");
					
				}
					
			}break;
			
            case 3:{  //Change Address:
				
				(acc).setAddress(choiceValue);
					
			}break;
			
            case 4:{  //Change Email Address:
	           try {
	             (acc).setEmail(choiceValue);
               } catch (InvalidAccountDetailException e1) {
				System.err.println(e1.getMessage());
				
			   }
		
            }break;

            case 5:{  //Change Date of Birth:
	
            	String dateString= choiceValue;
				Date dob=null;
				try {
					 dob = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
				} catch (ParseException e) {
					System.err.println("Enter date in correct format");
				}
	            (acc).setDateOfBirth(dob);
		
            }break;
			
			}
		   em.persist(acc);
			tx.commit();
			System.out.println("\nAccount updated successfully");
			System.out.println("Updated account details: ");
			System.out.println(acc);
		
		
		
	}


	public double showBalance(Integer accNum) {
		AccountHolder acc= viewAccountDetails(accNum);
		return acc.getBalance();
	}

	@Override
	public Collection<AccountHolder> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer accNum) {
		
		AccountHolder acc= viewAccountDetails(accNum);
		tx.begin();
		em.remove(acc);
		tx.commit();
		System.out.println("Account deleted!");
		
	}

	@Override
	public void deposit(Integer accNum, double amt) {
		

		AccountHolder ah= viewAccountDetails(accNum);
	    double currBalance= ah.getBalance();
	    
	    try {
	    	    tx.begin();
				ah.setBalance(currBalance + amt);
				tx.commit();
				String s= "Rs."+ amt +" has been deposited on "+ LocalDate.now();
				transaction(accNum,s);
				
			} catch (LowBalanceException e) {
				System.err.println("Low amount! Your current account balance is Rs."+ ah.getBalance());
			}finally{
				
			}
		
	}

	@Override
	public void withdraw(Integer accNum, double amt) {
		
		
		AccountHolder ah= viewAccountDetails(accNum);
		double currBalance= ah.getBalance();
		tx.begin();
		try {
			ah.setBalance(currBalance- amt);
			tx.commit();
			String s= "Rs."+ amt +" has been withdrawn on "+ LocalDate.now();
			transaction(accNum,s);
		} catch (LowBalanceException e) {
			System.err.println("Low amount! Your current account balance is Rs."+ ah.getBalance());
		}
		
	}

	@Override
	public void fundTransfer(Integer accNum1, Integer accNum2, double amt) {
		
		AccountHolder debitFrom= viewAccountDetails(accNum1);
		AccountHolder creditTo= viewAccountDetails(accNum2);
		withdraw(accNum1,amt);	
	    deposit(accNum2,amt);
		
		String s1= "Rs."+amt+" has been withdrawn from your account on "+ LocalDate.now() +" and deposited in the account number XXX";
		System.out.println(s1);
		String s2= "Rs."+ amt +" has been deposited on "+ LocalDate.now() ;
		transaction(accNum1,s1);
		transaction(accNum2,s2);
	}

    public void transaction(Integer accNum,String str){
		
    	tx.begin();
    	AccountHolder acc= new AccountHolder();
		String newString=acc.getTransactionHistory() + "\n" + str;
		acc.setTransactionHistory(newString);
		tx.commit();
	
	}
	
	//show all transactions made:
	
	@Override
	public void printTransactions(Integer accNum) {
		
		AccountHolder acc= viewAccountDetails(accNum);
		if(acc.getTransactionHistory()=="") 
			System.out.println("No transactions made yet!!!");
		String transactionHistory= acc.getTransactionHistory();
		System.out.println("Transaction history of the account number "+ accNum+" is:");
		System.out.println(transactionHistory);
	}

	@Override
	public AccountHolder viewAccountDetails(Integer accNum) {
		
		AccountHolder acc= (AccountHolder)em.find(AccountHolder.class, accNum);
		return acc;
	}
	
	@Override
	public void closeEntity() {
		 em.close();
		 emf.close();
	}
	
	
	

	

	
	
	

}
