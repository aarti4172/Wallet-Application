package wallet;

import java.util.Collection;

public interface WalletDaoInterface<T,K> {
	void createAccount(T t);
	void update(T t);
	T getByIdentity(K id);
	Collection<T> getAll();
	void delete(K id);
	void deposit(K id, double mon);
	void withdraw(K id, double mon);
	void fundTransfer(K id1, K id2, double mon);
	void printTransactions(Integer id);
	

}
