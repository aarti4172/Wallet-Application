package accountProcessing;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.AccountHolder;
import service.AccountService;
import exceptions.InvalidAccountDetailException;
import exceptions.LowBalanceException;

@Component
public class AccountProcessor {
	
	@Autowired
	private AccountService<AccountHolder, Integer> accService;

	public AccountService<AccountHolder, Integer> getAccService() {
		return accService;
	}

	public void setAccService(AccountService<AccountHolder, Integer> accService) {
		this.accService = accService;
	}
	public void createAccount() {
		@SuppressWarnings("deprecation")
		Date date= new Date(27, 03, 1500);
		try {
			AccountHolder  ah= new AccountHolder(3000,"Sanjeevani Booti",9898767655l,date,"Swarg Lok","SanB@email.em");
			accService.createAccount(ah);
			System.out.println("Inserted");
			System.out.println(ah);
		} catch (LowBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAccountDetailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
