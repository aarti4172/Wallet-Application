package wallet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class AccountMain {
	
	
	public static void main(String[] args) {
		

		
		System.out.println("Welcome to ZipZapZoo Wallet\n");
		
		while(true) {
		
		System.out.println("How can we help you? Select one of the following operations:\n");
		System.out.println("1. Create account. ");
		System.out.println("2. Withdraw money from your wallet.");
		System.out.println("3. Deposit money to your wallet.");
		System.out.println("4. Transfer money from your account.");
		System.out.println("5. Show balance.");
		System.out.println("6. Print your previous transactions.");
		System.out.println("7. Update account details.");
		System.out.println("8. Remove account.");
		System.out.println("9. Exit.");
		
		
		Scanner scr = new Scanner(System.in);
		int choice = scr.nextInt();
		boolean ok=false;
		WalletDaoInterface<Account,Integer> idao= new AccountProcessing();
		
		switch(choice){
		
		
		case 1:{    //Create new account:
           
			 
			    Account a= new AccountHolder();
				LocalDate date= LocalDate.now();
				scr.nextLine(); 
				
			do {
				System.out.println("Enter name: ");
				String name=scr.nextLine();
				try {
					a.setName(name);
					ok=false;
				} catch (InvalidAccountDetailException e1) {
					System.err.println(e1.getMessage());
					ok=true;
				}
			}while(ok);
			
			do {
				System.out.println("Enter account balance: ");
				try {
					scr = new Scanner(System.in);
					double bal= scr.nextDouble();
					a.setBalance(bal);
					ok=false;
				}catch (LowBalanceException e1 ) {
					System.err.println(e1.getMessage());
					ok=true;
				}catch( InputMismatchException e1 ) {
					System.err.println("Enter a number\n");
					ok=true;
				}
			}while(ok);
			
			do {
				System.out.println("Enter mobile number: ");
				try {
					scr = new Scanner(System.in);
					long mobNum= scr.nextLong();
					a.setMobileNum(mobNum);
					ok=false;
				} catch (InvalidAccountDetailException e1) {
					System.err.println(e1.getMessage());
					ok=true;
				}catch( InputMismatchException e1 ) {
					System.err.println("Enter a number");
					ok=true;
				}
			}while(ok);
			
			do {
				System.out.println("\nEnter date of birth in 'dd/mm/yyyy' format: ");
				String dateString= scr.next();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				try {
					 Date dob = dateFormat.parse(dateString);
					 a.setDateOfBirth(dob);
					 ok=false;
				} catch (ParseException e) {
					//e.printStackTrace();
					System.err.println("Enter a valid date.");
					ok=true;
				}
			}while(ok);
			
			scr.nextLine();
			do {
			    System.out.println("Enter email address: ");
			    String mail= scr.nextLine();
			    try {
					a.setEmail(mail);
					ok=false;
				} catch (InvalidAccountDetailException e1) {
					System.err.println(e1.getMessage());
					ok=true;
				}
			}while(ok);
			
				System.out.println("Enter address: ");
				String addr= scr.nextLine();
				a.setAddress(addr);
				idao.createAccount(a);
				
                System.out.println("Account created.");
                
                System.out.println("Your Account details are: ");
                System.out.println(a+"\n");
                
                
		}break;
		
		case 2:{    //Withdraw money:
			
			System.out.println("Enter the account number from which money has to be withdrawn.");
			int anum= scr.nextInt();
		    System.out.println("Enter the amount of money to be withdrawn");
		    double mon= scr.nextDouble();
		    idao.withdraw(anum,mon);  
	
		}break;
		
		case 3:{  //Deposit:
			
			System.out.println("Enter the account number in which money is to be deposited.");
			int anum= scr.nextInt();
		    System.out.println("Enter the amount of money to be deposited");
		    double mon= scr.nextDouble();
		    idao.deposit(anum,mon); 
		    Account p= new AccountHolder();
			p= idao.getByIdentity(anum);
			System.out.println("Rs."+ mon+" has been deposited.");
			System.out.println("New balance is: Rs."+ p.getBalance());
			
		}break;
		 
		case 4:{   // Transfer Funds:   
			
			System.out.println("Enter the account number from which money has to be transferred:");
			int id1= scr.nextInt();
		    System.out.println("Enter the amount of money to be transferred");
		    double mon= scr.nextDouble();
		    System.out.println("Enter the account number to which money is to be transferred:");
			int id2= scr.nextInt();
		    idao.fundTransfer(id1,id2,mon);  
		    Account p= new AccountHolder();
			p= idao.getByIdentity(id1);
		    System.out.println("Rs."+mon+" transferred from your account to the account number "+ id2+"\nYour new balance is Rs."+ p.getBalance()+"\n");
		    
		}break;
		
		case 5:{   //Show Balance:
			
			System.out.println("Enter your account number:");
			int id= scr.nextInt();
			Account p= new AccountHolder();
			p= idao.getByIdentity(id);
			System.out.println("Your current account balance is: Rs."+ p.getBalance());
			System.out.println();
			
		}break;
		
		case 6:{   //print transaction:
			
			System.out.println("Enter the account number to view transactions: ");
			int accNum= scr.nextInt();
			idao.printTransactions(accNum);
			
		}break;
		
		
		case 7:{   //Update account details:
			

			System.out.println("Enter your account number:");
			int accNum=scr.nextInt();
			Account p= new AccountHolder();
			p= idao.getByIdentity(accNum);
			if(p!=null) {
				
			while(true) {
				System.out.println("\nWhat detail do you wish to alter?:");
				System.out.println("1. Name");
				System.out.println("2. Mobile Number");
				System.out.println("3. Address");
				System.out.println("4. Email Address");
				System.out.println("5. Date of Birth");
				System.out.println("6. Exit");
				int choice1=scr.nextInt();
				
				switch(choice1) {
				
				case 1:{  //Change name:
					
					System.out.println("Enter new name:");
					scr.nextLine();
					try {
						(p).setName(scr.nextLine());
					} catch (InvalidAccountDetailException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}break;
				
				case 2:{  //Change Mobile Number:
					
					System.out.println("Enter new mobile number:");
					try {
						(p).setMobileNum(scr.nextLong());
					} catch (InvalidAccountDetailException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}break;
				
	            case 3:{  //Change Address:
					
					System.out.println("Enter new address:");
					scr.nextLine();
					(p).setAddress(scr.nextLine());
						
				}break;
				
	            case 4:{  //Change Email Address:
		
		             System.out.println("Enter new email:");
		             try {
						(p).setEmail(scr.next());
					} catch (InvalidAccountDetailException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
	            }break;

	            case 5:{  //Change Date of Birth:
		
	            	String dateString= scr.next();
					Date dob=null;
					try {
						 dob = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
					} catch (ParseException e) {
						e.printStackTrace();
					}
		            (p).setDateOfBirth(dob);
			
	            }break;

	           case 6:{  //Exit:
		
		            System.exit(0);  
			
	            }break;
				
				}
				idao.update(p);
				System.out.println("\nAccount updated successfully");
				System.out.println("Updated account details: ");
				System.out.println(p);
			}
			}
			else 
				System.out.println("Person not found!");

		}break;
		
		
		
		case 8:{   //delete account:
			
			System.out.println("Enter the account number to be deleted: ");
			int accNum= scr.nextInt();
			Account p= new AccountHolder();
			p= idao.getByIdentity(accNum);
			if(p!=null) {
			    idao.delete(accNum);
			}
			else 
				System.out.println("Account not found!");
			System.out.println();
			
		}break;
		
		
		case 9:{   //exit:
			
			System.exit(0);
			
		}
			
		default:{
			
			System.out.println("No such option!Kindly choose from one of the numbers mentioned above.\n");
			
		}break;
		
		}
		
		}
	}
		
}
