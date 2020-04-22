package wallet;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AccountHolder extends Account implements Serializable {
	
	private String name;
	private long mobileNum;
	private Date dateOfBirth;
	private String address;
	private String email;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (mobileNum ^ (mobileNum >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountHolder other = (AccountHolder) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (mobileNum != other.mobileNum)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	public AccountHolder() {
		super();
	}

	public AccountHolder(
			String name, long mobileNum, Date dob, String address,
			String email) throws InvalidAccountDetailException {
		super();
		
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
		this.name = name;
		this.mobileNum = mobileNum;
		this.dateOfBirth = dob;
		this.address = address;
		this.email = email;
	}
	
	
	
	public AccountHolder(double balance, LocalDate date,
			String name, long mobileNum, Date dob, String address,
			String email) throws LowBalanceException, InvalidAccountDetailException{
		super(balance, date);
		if(balance<0) {
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
		this.name = name;
		this.mobileNum = mobileNum;
		this.dateOfBirth = dob;
		this.address = address;
		this.email = email;
	}
	
	public void callException() {
		
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
	public void createAccount() {
		
	}
	
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return "Account Number = " + getAccountNum() + "\n   Name= " + name + "\n   Balance= " + getBalance() + "\n   Opening Date= " + getOpeningDate() +"\n   Mobile Number= " + mobileNum + "\n   Date Of Birth= " + dateFormat.format(dateOfBirth)
				+ "\n   Address= " + address + "\n   Email Address= " + email  + ".";
	}
	@Override
	public void showBalance() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showTransaction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void transferFunds() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
