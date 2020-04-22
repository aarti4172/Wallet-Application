package repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import exceptions.LowBalanceException;
import model.Account;
import model.Transactions;


@Repository("accountDbDao")
@Transactional
public class AccountDbDaoRepository{

	@Autowired
	private AccountDaoInterface<Account, Integer> accDao;
	@Autowired 
	private TransactionsDaoInterface<Transactions, Integer> trDao;
	
	
	public Account save(Account account) {
		accDao.save(account);
		return account;
	}
	
	public Optional<Account> findById(Integer accNum) {
		Optional<Account> acc= accDao.findById(accNum);
		return acc;
	}

	public boolean existsById(Integer accNum) {
		boolean result= accDao.existsById(accNum);
		return result;
	}

	public void deleteById(Integer accNum) {
		accDao.deleteById(accNum);
	}
	
	public List<Account> findAll() {
		List<Account> accounts= accDao.findAll();
		return accounts;
	}
	
	public boolean deposit(Integer accNum, double amt) throws LowBalanceException {
		Optional<Account> optional= findById(accNum);
		if (optional.isPresent()) {
			Account account= optional.get();
			double newBalance=account.getBalance()+ amt;
			account.setBalance(newBalance);
			Transactions tr= new Transactions(accNum, "Rs. "+amt+"deposited in your account on.");
			trDao.save(tr);
		    return true;
		}
		else {
			return false;
		}
	}


	public boolean withdraw(Integer accNum, double amt) throws LowBalanceException {
		Optional<Account> optional= findById(accNum);
		if (optional.isPresent()) {
			Account account= optional.get();
			double newBalance=account.getBalance()- amt;
			account.setBalance(newBalance);
			Transactions tr= new Transactions(accNum, "Rs. "+amt+"withdrawn from your account on.");
			trDao.save(tr);
		    return true;
		}
		else {
			return false;
		}
		
	}

	public void fundTransfer(Integer accNum1, Integer accNum2, double amt) throws LowBalanceException {
		withdraw(accNum1, amt);
		deposit(accNum2, amt);		
	}

	@Query("SELECT * from transactions where acc_num= :accNum")
	public <List>Transactions printTransactions(@Param("acc_num")int accNum) {
		
		return null;
	}

	public boolean update(Integer accNum, Integer choice, String choiceValue) {
		
		return false;
		
		
		
	}

	

	



	

	


	

	

	
	
	
	/*
	@Autowired
	private AccountDaoInterface<AccountHolder, Integer> adao;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void createAccount(AccountHolder acc) {
		em.persist(acc);
		em.flush();
	}
	
	@Override
	public AccountHolder viewAccountDetails(Integer accNum) {
		return em.find(AccountHolder.class, accNum);		
	}
	
	@Override
	public List<AccountHolder> getAll() {
		TypedQuery<AccountHolder> query= em.createQuery("from account_master", AccountHolder.class);
		List<AccountHolder> accounts= query.getResultList();
		return accounts;
	}
	
	@Override
	public void delete(Integer accNum) {
		AccountHolder ah= em.find(AccountHolder.class, accNum);
		em.remove(ah);
		em.flush();
		System.out.println("Account deleted");
	}
	
	@Override
	public void deposit(Integer accNum, double amt) {
		
		AccountHolder ah= viewAccountDetails(accNum);
	    double currBalance= ah.getBalance();
		try {
			ah.setBalance(currBalance + amt);
			update(ah);
			//String s= "Rs."+ amt +" has been deposited on "+ LocalDate.now();
			//transaction(accNum,s);
			
		} catch (LowBalanceException e) {
			System.err.println("Low amount! Your current account balance is Rs."+ ah.getBalance());
		}
		
	}
	
	@Override
	public void withdraw(Integer accNum, double amt) {
		AccountHolder ah= viewAccountDetails(accNum);
	    double currBalance= ah.getBalance();
		try {
			ah.setBalance(currBalance - amt);
			update(ah);
			//String s= "Rs."+ amt +" has been deposited on "+ LocalDate.now();
			//transaction(accNum,s);
		} catch (LowBalanceException e) {
			System.err.println("Low amount! Your current account balance is Rs."+ ah.getBalance());
		}
		
	}

	@Override
	public void fundTransfer(Integer accNum1, Integer accNum2, double amt) {
		AccountHolder debitFrom= viewAccountDetails(accNum1);
		AccountHolder creditTo= viewAccountDetails(accNum2);
		withdraw(accNum1,amt);	
	    deposit(accNum2,amt);
		/*(String s1= "Rs."+amt+" has been withdrawn from your account on "+ LocalDate.now() +" and deposited in the account number XXX";
		System.out.println(s1);
		String s2= "Rs."+ amt +" has been deposited on "+ LocalDate.now() ;
		transaction(accNum1,s1);
		transaction(accNum2,s2);	
	}
	
	@Override
	public void printTransactions(Integer accNum) {
		
		
	}
	@Override
	public void update(AccountHolder acc) {
		em.merge(acc);
		em.flush();
	}
	
	public double showBalance(Integer accNum) {
		AccountHolder acc= viewAccountDetails(accNum);
		return acc.getBalance();
	}

	@Override
	public List<AccountHolder> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountHolder> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountHolder> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AccountHolder> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends AccountHolder> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<AccountHolder> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccountHolder getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AccountHolder> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AccountHolder> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AccountHolder> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AccountHolder> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AccountHolder> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AccountHolder entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends AccountHolder> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends AccountHolder> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AccountHolder> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AccountHolder> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends AccountHolder> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
	

}
