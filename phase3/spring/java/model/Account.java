package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import exceptions.InvalidAccountDetailException;
import exceptions.LowBalanceException;

@Entity
@Table(name="account_master")
public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="acc_num")
	private int accountNum;
	@Column(name="acc_bal")
	private double balance;
	@Column(name="open_date")
	private Date openingDate;
	@Column(name="name")
	private String name;
	@Column(name="mobile_num")
	private long mobileNum;
	@JsonFormat(pattern="dd-mm-yyyy")
	private Date dateOfBirth;
	private String address;
	private String email;
	private String transactionHistory;
	@OneToOne(mappedBy="account_master")
	Transactions transactions;
	

	
	public String getTransactionHistory() {
		return transactionHistory;
	}


	public void setTransactionHistory(String transactionHistory) {
		this.transactionHistory = transactionHistory;
	}


	public Account() {
		super();
		generateAccountNum();
		generateDate();
	}

	
	public Account(double balance,
			String name, long mobileNum, Date dob, String address,
			String email) throws LowBalanceException, InvalidAccountDetailException{
		generateAccountNum();
		generateDate();
		if(balance<=0) {
			LowBalanceException lbex= 
					new LowBalanceException("Your account is inactive since balance is 0."
					+ " Deposit money to activate your account.");
			throw lbex;
		}
		
		if(name.matches("^[a-zA-Z]*$")==false) {
			InvalidAccountDetailException iadex= 
					new InvalidAccountDetailException("Name can contain only alphabets."
					+ "Please enter a valid name.");
			throw iadex;
		}
	
		if(Long.toString(mobileNum).length()!=10) {
			InvalidAccountDetailException iadex= 
					new InvalidAccountDetailException("Mobile number should contain 10 digits."
					+ "Please enter a valid mobile number.");
			throw iadex;
			
		}
		
		if((email.contains("@") && email.contains("."))  ==false) {
			InvalidAccountDetailException iadex= 
					new InvalidAccountDetailException("Please enter a valid email address.");
			throw iadex;
			
		}
		this.balance=balance;
		this.name = name;
		this.mobileNum = mobileNum;
		this.dateOfBirth = dob;
		this.address = address;
		this.email = email;
	}
	
	public void callException() {
		
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void generateAccountNum() {
		Random r= new Random();
		int num=r.nextInt(999999999)+1000000000;
		//while(accountNumExists(num)==true)
		//num= r.nextInt(999999999)+1000000000;
		accountNum=num;	
		
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
	
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) throws InvalidAccountDetailException{

		if(name.matches("^[a-zA-Z ]*$")==false) {
			InvalidAccountDetailException iadex= 
					new InvalidAccountDetailException("Name can contain only alphabets."
					+ "Please enter a valid name.");
			throw iadex;
		}
		this.name = name;
	}
	public long getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(long mobileNum) throws InvalidAccountDetailException{
		
		if(Long.toString(mobileNum).length()!=10) {
			InvalidAccountDetailException iadex= 
					new InvalidAccountDetailException("Mobile number should contain 10 digits."
					+ "Please enter a valid mobile number.");
			throw iadex;
			
		}
		this.mobileNum = mobileNum;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws InvalidAccountDetailException {
		if((email.contains("@") && email.contains("."))  ==false) {
			InvalidAccountDetailException iadex= 
					new InvalidAccountDetailException("Please enter a valid email address.");
			throw iadex;
			
		}
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "AccountHolder [accountNum=" + accountNum + ", balance=" + balance + ", openingDate=" + openingDate
				+ ", name=" + name + ", mobileNum=" + mobileNum + ", dateOfBirth=" + dateOfBirth + ", address="
				+ address + ", email=" + email + "]";
	}
	public void generateDate() {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		System.out.println(date);
		openingDate=  date;  
	}
	
	

}
