package pe.unjfsc.daw.spet.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.Resource;

@Configuration
public class HsQldbConfiguration {

	@Value("classpath:/org/springframework/batch/core/schema-hsqldb.sql")
	private Resource dataScript;
	
	@Value("classpath:/org/springframework/batch/core/schema-drop-hsqldb.sql")
	private Resource dataScriptDrop;
	
	@Bean
	public SingleConnectionDataSource getDataSource() {
		SingleConnectionDataSource connection = new SingleConnectionDataSource();
		connection.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		connection.setUrl("jdbc:hsqldb:hsql://localhost/testdb");
		connection.setUsername("SA");
		connection.setPassword("");
		connection.setSuppressClose(true);
		initializeDataBase().execute(connection);
		return connection;
	}
	
	@Bean
	public ResourceDatabasePopulator initializeDataBase() {
	    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    populator.addScript(dataScriptDrop);
	    populator.addScript(dataScript);
	    return populator;
	}
	
	@Bean
	public DataSourceTransactionManager transactionM() {
		DataSourceTransactionManager transactionM = new DataSourceTransactionManager();
		transactionM.setDataSource(getDataSource());
		return transactionM;
	}
	
}
