package pe.unjfsc.daw.spet.view;

import org.slf4j.Logger; 

import org.slf4j.LoggerFactory; 

import org.springframework.batch.core.Job; 

import org.springframework.batch.core.JobExecution; 

import org.springframework.batch.core.JobParameters; 

import org.springframework.batch.core.launch.JobLauncher; 

import org.springframework.context.ApplicationContext; 

import org.springframework.context.ConfigurableApplicationContext; 

import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.unjfsc.daw.spet.configuration.BatchContextConfiguration;

public class CV0201v01LanzarJob { 

  private static final Logger MOLOG = LoggerFactory.getLogger(CV0201v01LanzarJob.class); 

  private static ApplicationContext moContext; 

  public static void main(String[] args) { 

     MOLOG.info("[DAW]=====[ Start main ]====="); 

     moContext = new ClassPathXmlApplicationContext("beans.xml"); 

     MOLOG.info("---");  
     MOLOG.info("[DAW] ClassPathXmlApplicationContext {}", moContext); 

     BatchContextConfiguration batchContextConfiguration = (BatchContextConfiguration) moContext.getBean(BatchContextConfiguration.class); 
       
       JobLauncher jobLauncher = (JobLauncher)moContext.getBean("launcher");
       
       MOLOG.info("[DAW] JobLauncher {}", jobLauncher); 

       Job job = (Job) moContext.getBean("procesarLoteLostPet"); 

       MOLOG.info("[DAW] Job {}", job); 

       JobExecution jobExecution = null; 

     try { 
    	 
           jobExecution = jobLauncher.run(job, new JobParameters()); 

           MOLOG.info("[DAW] jobExecution        : {}", jobExecution); 

           MOLOG.info("[DAW] Estatus        : {}", jobExecution.getStatus()); 

           MOLOG.info("[DAW] Estatus Salida : {}", jobExecution.getExitStatus()); 

       } catch (Exception e) { 

          MOLOG.error("[DAW] Error al ejecutar el JOB : {}", e.getMessage()); 

       } 

       ((ConfigurableApplicationContext) moContext).close(); 

  } 

} 