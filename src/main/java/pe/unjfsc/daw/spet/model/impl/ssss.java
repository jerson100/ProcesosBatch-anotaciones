package pe.unjfsc.daw.spet.model.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class ssss {
	
	public static void main(String[] args) {
		
	}
	
	public static class CV0101v01RevisarContextDB { 

		private static final Logger MOLOG = LoggerFactory.getLogger(CV0101v01RevisarContextDB.class); 

		private static ApplicationContext moContext; 

		private static String[] configJob = { 

		"classpath:/batch/config/job01-database.xml" 

		    }; 

		 

		public static void main(String[] psaLista) { 

			 Connection con = null;
		      
		      try {
		         //Registering the HSQLDB JDBC driver
		         Class.forName("org.hsqldb.jdbc.JDBCDriver");
		         //Creating the connection with HSQLDB
		         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
		         if (con!= null){
		            System.out.println("Connection created successfully");
		            
		         }else{
		            System.out.println("Problem with creating connection");
		         }
		      
		      }  catch (Exception e) {
		         e.printStackTrace(System.out);
		      }
		   }
		} 
	
}
