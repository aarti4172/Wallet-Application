package wallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public abstract class Account implements Serializable{
	private int accountNum;
	private double balance;
	private LocalDate openingDate;
	
	@Override
	public String toString() {
		return "Account Number = " + accountNum + ", Balance= " + balance + ", Opening Date= " + openingDate ;
	}
	
	
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNum;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((openingDate == null) ? 0 : openingDate.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNum != other.accountNum)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (openingDate == null) {
			if (other.openingDate != null)
				return false;
		} else if (!openingDate.equals(other.openingDate))
			return false;
		return true;
	}


    
	
	public Account() {
		super();
		generateAccountNum();
	}


	public Account(double balance2, LocalDate date) throws LowBalanceException,InvalidAccountDetailException {
		generateAccountNum();
		if(balance<=0) {
			LowBalanceException lbe= 
					new LowBalanceException("Your account has been deactivated due to zero balance."
							+ " Deposit some amount to activate your account.");
			throw lbe;
		}
		this.accountNum= accountNum;
		this.balance = balance2;
		this.openingDate = date;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void generateAccountNum() {
		Random r= new Random();
		int num=r.nextInt(999999999)+1000000000;
		while(accountNumExists(num)==true)
			num= r.nextInt(999999999)+1000000000;
		accountNum=num;	
		
	}
	private boolean accountNumExists(int num) {
		boolean result=false;
		File f= new File("AccountInfo.txt");
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
		if(accountMap.containsKey(num))
			result=true;
		return result;
	}



	public void setAccountNum(int accountNum) {
		this.accountNum=accountNum;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) throws LowBalanceException {
		if(balance<=0) {
			LowBalanceException lbe= 
					new LowBalanceException("Your account has been deactivated due to zero balance."
							+ " Deposit some amount to activate your account.");
			throw lbe;
		}
		this.balance = balance;
	}
	public LocalDate getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(LocalDate openingDate)  {
		this.openingDate = openingDate;
	}
	
	

	public abstract void showBalance();
	public abstract void createAccount();
	public abstract void showTransaction();
	public abstract void withdraw();
	public abstract void deposit();
	public abstract void transferFunds();
	
	public abstract String getName();
	public abstract void setName(String name) throws InvalidAccountDetailException;
	public abstract long getMobileNum();
	public abstract void setMobileNum(long mobileNum) throws InvalidAccountDetailException;
	public abstract Date getDateOfBirth();
	public abstract void setDateOfBirth(Date dateOfBirth) ;
	public abstract String getAddress();
	public abstract void setAddress(String address) ;
	public abstract String getEmail();
	public abstract void setEmail(String email) throws InvalidAccountDetailException;
	
	
}
