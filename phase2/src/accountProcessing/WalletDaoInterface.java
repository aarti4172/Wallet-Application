package accountProcessing;

import java.util.Collection;

import entities.AccountHolder;

public interface WalletDaoInterface<T,K> {
	void createAccount(T acc);
	T viewAccountDetails(K accNum);
	Collection<T> getAll();
	void delete(K accNum);
	void deposit(K accNum, double amt);
	void withdraw(K accNum, double amt);
	void fundTransfer(K accNum1, K accNum2, double amt);
	void printTransactions(K accNum);
	void update(K accNum, K choice, String choiceValue);
	void closeEntity();
	
	

}
