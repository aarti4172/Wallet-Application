package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDaoRepository;
import model.AccountHolder;

@Service
public class AccountDBServiceImpl implements AccountService<AccountHolder, Integer> {
	
	@Autowired
	private AccountDaoRepository repository;

	@Override
	public void createAccount(AccountHolder acc) {
		repository.createAccount(acc);
		}

		@Override
		public AccountHolder viewAccountDetails(Integer accNum) {
			return null;
		}

		@Override
		public List<AccountHolder> getAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void delete(Integer accNum) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deposit(Integer accNum, double amt) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void withdraw(Integer accNum, double amt) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void fundTransfer(Integer accNum1, Integer accNum2, double amt) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void printTransactions(Integer accNum) {
			// TODO Auto-generated method stub
			
		}

		//@Override
		public void update(Integer accNum, Integer choice, String choiceValue) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void closeEntity() {
			// TODO Auto-generated method stub
			
		}

		

		@Override
		public void updateProduct(AccountHolder ah) {
			// TODO Auto-generated method stub
			
		}
		
	}
	