package accountProcessing;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import model.AccountHolder;
import model.Transactions;

@Configuration
@ComponentScan(basePackages= {"accountProcessing"})
@EnableTransactionManagement
public class AccountConfiguration {
	
	@Bean(name="dataSource")
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource ds= new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:Oracle:thin:@localhost:1521:xe");
		ds.setUsername("System");
		ds.setPassword("hr");
		return ds;
	}
	
	@Bean(name="entityManagerFactory", autowire=Autowire.BY_NAME)
	public LocalContainerEntityManagerFactoryBean  entityManagerFactory() {
	     LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
	     bean.setPackagesToScan("accountProcesing");
	     bean.setDataSource(getDataSource());
	     bean.setJpaVendorAdapter(getAdaptor());
	     bean.setJpaProperties(getJpaProperties());
		return bean;
	}
	@Bean(name="jpaVendorAdapter")
	public JpaVendorAdapter getAdaptor() {
		HibernateJpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
		return adaptor;
	}
	
	@Bean(name="jpaProperties")
	public Properties getJpaProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.oracle10gDialect");
		props.put("hibernate.hbm2dd1.auto", "update");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql","true");
		//props.put("hibernate.ejb.naming_strategy","");
		return props;
	}
	
	@Bean(autowire= Autowire.BY_NAME)
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	     JpaTransactionManager transactionManager = new JpaTransactionManager();
	     transactionManager.setEntityManagerFactory(entityManagerFactory);
	     return transactionManager;
	    }
	
	@Bean(name="accountHolder")
	public AccountHolder getAccountHolder() {
		AccountHolder ah = new AccountHolder();
		return ah;
	}
	@Bean(name="transactions")
	public Transactions getTransactions() {
		Transactions transactions = new Transactions();
		return transactions;
	}
}
