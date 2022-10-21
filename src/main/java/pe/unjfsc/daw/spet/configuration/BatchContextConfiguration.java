package pe.unjfsc.daw.spet.configuration;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@Configuration
public class BatchContextConfiguration {

	public SingleConnectionDataSource dataSource;
	public DataSourceTransactionManager transactionManager;
	
	public BatchContextConfiguration(SingleConnectionDataSource dataSource, DataSourceTransactionManager transactionManager) {
		this.dataSource = dataSource;
		this.transactionManager = transactionManager;
	}
	
	@Bean
	public JobRepositoryFactoryBean getJobRepository() {
		JobRepositoryFactoryBean repository = new JobRepositoryFactoryBean();
		repository.setDataSource(dataSource);
		repository.setTransactionManager(transactionManager);
		return repository;
	}
	
	@Bean
	public SimpleJobLauncher launcher() {
		SimpleJobLauncher l = new SimpleJobLauncher();
		try {
			l.setJobRepository(getJobRepository().getObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
}
