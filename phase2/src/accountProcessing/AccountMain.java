package accountProcessing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import entities.AccountHolder;
import exceptions.InvalidAccountDetailException;
import exceptions.LowBalanceException;

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
		WalletDaoInterface<AccountHolder,Integer> idao= new WalletDaoImpl();
		AccountHolder acc= new AccountHolder();
		
		switch(choice){
		
		
		case 1:{    //Create new account:
           
			 
			    
			    LocalDate date= LocalDate.now();
				//Date date= Date.valueOf(ld);
				acc.setOpeningDate(date);
				scr.nextLine(); 
				
			do {
				System.out.println("Enter name: ");
				String name=scr.nextLine();
				try {
					acc.setName(name);
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
					acc.setBalance(bal);
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
					acc.setMobileNum(mobNum);
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
					 acc.setDateOfBirth(dob);
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
					acc.setEmail(mail);
					ok=false;
				} catch (InvalidAccountDetailException e1) {
					System.err.println(e1.getMessage());
					ok=true;
				}
			}while(ok);
			
				System.out.println("Enter address: ");
				String addr= scr.nextLine();
				acc.setAddress(addr);
				idao.createAccount(acc);
				
        }break;
		
       
		case 2:{    //Withdraw money:
			
			System.out.println("Enter the account number from which money has to be withdrawn.");
			int anum= scr.nextInt();
		    System.out.println("Enter the amount of money to be withdrawn");
		    double mon= scr.nextDouble();
		    idao.withdraw(anum,mon); 
		    acc=idao.viewAccountDetails(anum);
		    System.out.println("Rs."+ mon+" has been withdrawn.");
			System.out.println("New balance is: Rs."+ acc.getBalance());
	
		}break;
		
		case 3:{  //Deposit:
			
			System.out.println("Enter the account number in which money is to be deposited.");
			int anum= scr.nextInt();
		    System.out.println("Enter the amount of money to be deposited");
		    double mon= scr.nextDouble();
		    idao.deposit(anum,mon); 
		    acc=idao.viewAccountDetails(anum);
		    System.out.println("Rs."+ mon+" has been deposited.");
			System.out.println("New balance is: Rs."+ acc.getBalance());
		   
		}break;
		 
		 
		case 4:{   // Transfer Funds:   
			
			System.out.println("Enter the account number from which money has to be transferred:");
			int id1= scr.nextInt();
		    System.out.println("Enter the amount of money to be transferred");
		    double mon= scr.nextDouble();
		    System.out.println("Enter the account number to which money is to be transferred:");
			int id2= scr.nextInt();
		    idao.fundTransfer(id1,id2,mon);  
		    
		}break;
		
		case 5:{   //Show Balance:
			
			System.out.println("Enter your account number:");
			int id= scr.nextInt();
			 acc=idao.viewAccountDetails(id);
			System.out.println("Your current account balance is: Rs."+ acc.getBalance());
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
			boolean boolVal= true;
			while(boolVal) {
				System.out.println("\nWhat detail do you wish to alter?:");
				System.out.println("1. Name");
				System.out.println("2. Mobile Number");
				System.out.println("3. Address");
				System.out.println("4. Email Address");
				System.out.println("5. Date of Birth");
				System.out.println("6. Exit");
				int choice1=scr.nextInt();
				if(choice1== 6) {
					boolVal=false;
				}
				System.out.println("Enter new detail:");
				String choiceValue= scr.nextLine();
				scr.next();
				idao.update(accNum, choice1, choiceValue);
			}
		}break;
		
		
		
		case 8:{   //delete account:
			
			System.out.println("Enter the account number to be deleted: ");
			int accNum= scr.nextInt();
			idao.delete(accNum);
		}break;
		
		
		case 9:{   //exit:
			
			idao.closeEntity();
			System.exit(1);
			
		}
			
		default:{
			
			System.out.println("No such option!Kindly choose from one of the numbers mentioned above.\n");
			
		}break;
		
		}
		
		}
	}
		
}
