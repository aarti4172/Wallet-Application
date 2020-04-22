package repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AccountDaoInterface<T,K> extends JpaRepository<T,K>,CrudRepository<T,K>{
	
	
	boolean deposit(K accNum, double amt);
	boolean withdraw(K accNum, double amt);
	boolean fundTransfer(K accNum1, K accNum2, double amt);
	boolean printTransactions(K accNum);
	boolean update(K accNum, K choice, String choiceValue);
	boolean update(T acc);

}
