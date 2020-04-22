package wallet;

import java.util.Collection;



public class AccountGetAllMain {

	
	public static void main(String[] args) {
		WalletDaoInterface idao= new AccountProcessing();
		Collection<Account> a= idao.getAll();
		for(Account x: a ) {
			System.out.println(x);
		}
		
		

	}

}
