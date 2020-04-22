package wallet;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;

public class AccountProcessing implements WalletDaoInterface<Account,Integer>{
	
	Map<Integer , String > transactionMap= null;
	String string="";
	
		
	//Create new account:
	
	@Override
	public void createAccount(Account newCustomer) {
		String fileName="AccountInfo.txt";
		File f= new File(fileName);
		Map<Integer,Account> accountMap= null;
		if(f.exists()) {
			try(FileInputStream fr= new FileInputStream (f);
					ObjectInputStream br= new ObjectInputStream(fr)){
				Object obj= br.readObject();
				accountMap= (Map<Integer,Account>) obj;	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else {
			accountMap= new HashMap<Integer,Account>();
		}
		accountMap.put(newCustomer.getAccountNum(), newCustomer);
		transaction(newCustomer.getAccountNum(),string);
		try(OutputStream out= new FileOutputStream("AccountInfo.txt");
				ObjectOutputStream fout= new ObjectOutputStream(out)){
			fout.writeObject(accountMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	//Show Account details(By account Number):
	
	@Override
	public Account getByIdentity(Integer id) {
		Account foundAccount= null;
		Map<Integer,Account> accountMap= new HashMap<>();
		File f= new File("AccountInfo.txt");
		if(f.exists()) {
			try(FileInputStream fin= new FileInputStream(f);
					ObjectInputStream br =new ObjectInputStream (fin)){
				Object obj= br.readObject();
				accountMap= (Map<Integer,Account>) obj;
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			foundAccount= accountMap.get(id);
		}
		
		
		return foundAccount;
	}

	
	//delete account:
	
	@Override
	public void delete(Integer id) {
		Account foundAccount= null;
		Map<Integer,Account> accountMap= new HashMap<>();
		File f= new File("AccountInfo.txt");
		if(f.exists()) {
			try(FileInputStream fin= new FileInputStream(f);
					ObjectInputStream br =new ObjectInputStream (fin)){
				Object obj= br.readObject();
				accountMap= (Map<Integer,Account>) obj;
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			accountMap.remove(id);
		}
		try(OutputStream out= new FileOutputStream("AccountInfo.txt");
				ObjectOutputStream fout= new ObjectOutputStream(out)){
			fout.writeObject(accountMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Account removed successfully!");
		
		
	}

	
	//Display all the accounts created so far:
	
	@Override
	public Collection<Account> getAll() {
		File f= new File("AccountInfo.txt");
		Map<Integer, Account> accountMap= null;
		try(InputStream fin= new FileInputStream(f);
				ObjectInputStream br= new ObjectInputStream(fin)){
			Object obj= br.readObject();
			accountMap=(Map<Integer,Account>) obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Collection y= accountMap.values();


		return y;
	}

	//Update an account:
	
	@Override
	public void update(Account changedAccount) {
		Account foundAccount= null;
		String fileName= "AccountInfo.txt";
		File f= new File(fileName);
		Map<Integer,Account> AccountMap= null;
		if(f.exists()) {
			try(FileInputStream fin= new FileInputStream(f);
					ObjectInputStream in = new ObjectInputStream(fin)){
				Object obj= in.readObject();             
				AccountMap = (Map<Integer, Account>)obj;   
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int AccountNum= changedAccount.getAccountNum();
			AccountMap.replace(AccountNum, changedAccount); //previous value gets replaced by new value
			
			//serialization:
			try(FileOutputStream fout= new FileOutputStream("AccountInfo.txt");
					ObjectOutputStream out= new ObjectOutputStream(fout)){
				out.writeObject(AccountMap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
		
	}
	
	//deposit money in account:
	
	@Override
	public void deposit(Integer id, double depositAmt) {
		Account a= getByIdentity(id);
		double currentBalance= a.getBalance();
		double newBalance= currentBalance+ depositAmt;
		try {
			a.setBalance(newBalance);
			update(a);
			String s= "Rs."+depositAmt+" has been deposited on "+ LocalDate.now();
			transaction(id,s);
		} catch (LowBalanceException e) {
			System.err.println("Enter a valid amount!");
		}
		
	}

	//withdraw money from account:
	
	@Override
	public void withdraw(Integer id, double withdrawAmt) {
		Account a= getByIdentity(id);
		double currentBalance= a.getBalance();
		double newBalance= currentBalance - withdrawAmt;
		try {
			a.setBalance(newBalance);
			update(a);
			System.out.println("Rs."+withdrawAmt+" has been debited.");
		    System.out.println("New balance is: Rs."+newBalance);
			String s= "Rs."+withdrawAmt+" has been withdrawn on "+ LocalDate.now();
			transaction(id,s);
		} catch (LowBalanceException e) {
			System.err.println("You don't have enough money in your account! Enter a valid amount.");
		}
		
		
	}

	
    //transfer money from one account to another:
	
	@Override
	public void fundTransfer(Integer id1, Integer id2, double amt) {
		Account debitFrom= getByIdentity(id1);
		Account creditTo= getByIdentity(id2);
		if(debitFrom!= null) {
			withdraw(id1,amt);
			
		}
		if(creditTo!= null) {
			deposit(id2,amt);
		}
		String s= "Rs."+amt+" has been withdrawn on "+ LocalDate.now() +" and deposited in the account number XXX";
		transaction(id1,s);
		
		
	}

	//print transaction implementation:
	
	public void transaction(Integer accNum,String str){
		
		File f= new File("TransactionHistory.txt");
		if(f.exists()) {
			try(FileInputStream fr= new FileInputStream (f);
					ObjectInputStream br= new ObjectInputStream(fr)){
				Object obj= br.readObject();
				transactionMap= (Map<Integer,String>) obj;
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else {
			transactionMap= new HashMap<Integer,String>();
		}
		String newString="";
		if(transactionMap.get(accNum)==null) {
			newString =str;
		}
		else {
			newString =transactionMap.get(accNum)  +  "\n"+ str;
		}
		transactionMap.put(accNum, newString);
		try(OutputStream out= new FileOutputStream("TransactionHistory.txt");
				ObjectOutputStream fout= new ObjectOutputStream(out)){
			fout.writeObject(transactionMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//show all transactions made:
	
	@Override
	public void printTransactions(Integer accNum) {
		File f= new File("TransactionHistory.txt");
		if(f.exists()) {
			try(FileInputStream fr= new FileInputStream (f);
					ObjectInputStream br= new ObjectInputStream(fr)){
				Object obj= br.readObject();
				transactionMap= (Map<Integer,String>) obj;
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else {
			System.out.println("No transactions made yet!!!");
		}
		String transactionHistory= transactionMap.get(accNum);
		System.out.println("Transaction history of the account number "+ accNum+" is:");
		System.out.println(transactionHistory);
	}
	

}
