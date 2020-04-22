package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.AccountHolder;

import java.util.List;
import service.AccountService;

@RestController
@RequestMapping("/ZipZapZoo")
public class AccountDbController {
	
	@Autowired
	private AccountService<AccountHolder, Integer> accService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/accounts")
	public ResponseEntity<List<AccountHolder>> updateAccount(@RequestBody AccountHolder ah){
		accService.updateProduct(ah);
		List<AccountHolder> accounts= accService.getAll();
		if(accounts.isEmpty())
		{
			return new ResponseEntity("Sorry! Account cannot be updated.", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<AccountHolder>>(accounts, HttpStatus.OK);
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/accounts")
	public ResponseEntity<List<AccountHolder>> createAccount(
			@RequestBody AccountHolder acc){
		accService.createAccount(acc);
		List<AccountHolder> accounts= accService.getAll();
		if(accounts.isEmpty())
		{
			return new ResponseEntity("Sorry! Products cannot be added!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<AccountHolder>>(accounts, HttpStatus.OK);
	}
	
	
	
		
	
	

}
