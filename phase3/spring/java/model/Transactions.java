package model;


import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transactions implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	private String message;
	private Date transactionDate;
	@Column(name="acc_num")
	private int accNum;
	@OneToOne
	@PrimaryKeyJoinColumn(name="acc_num")
	Account acc;
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Transactions(int accNum,String message) {
		super();
		this.accNum=accNum;
        this.message = message;
	}
	
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void generateDate() {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		System.out.println(date);
		transactionDate=  date;  
	}
	public void generatetransactionId() {
		Random r= new Random();
		int num=r.nextInt(99999)+100000;
		//while(accountNumExists(num)==true)
			//num= r.nextInt(999999999)+1000000000;
		transactionId=num;	
		
	}

	

}
