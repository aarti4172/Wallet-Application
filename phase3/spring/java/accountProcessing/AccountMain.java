package accountProcessing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountMain {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
		ctx.register(AccountConfiguration.class);
		ctx.refresh();
		AccountProcessor accPro = ctx.getBean(AccountProcessor.class);
		accPro.createAccount();
		ctx.close();
		

	}

}
