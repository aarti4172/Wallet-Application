package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsDaoInterface<T,K> extends JpaRepository<T,K>,CrudRepository<T,K>{
	

}
