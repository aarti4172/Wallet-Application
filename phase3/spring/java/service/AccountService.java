package service;

import java.util.List;

public interface AccountService<T,K>{
	
	void createAccount(T acc);
	T viewAccountDetails(K accNum);
	List<T> getAll();
	void delete(K accNum);
	void deposit(K accNum, double amt);
	void withdraw(K accNum, double amt);
	void fundTransfer(K accNum1, K accNum2, double amt);
	void printTransactions(K accNum);
	//void update(K accNum, K choice, String choiceValue);
	void closeEntity();
	void updateProduct(T ah);

}
