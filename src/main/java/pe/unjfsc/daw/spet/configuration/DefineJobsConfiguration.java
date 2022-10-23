package pe.unjfsc.daw.spet.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import pe.unjfsc.daw.spet.entity.CELostPetDTOEntrada;
import pe.unjfsc.daw.spet.entity.CELostPetDTOSalida;
import pe.unjfsc.daw.spet.listener.JobLoteMovimientosListener;
import pe.unjfsc.daw.spet.mapper.CLLostPetMapper;
import pe.unjfsc.daw.spet.process.CLLostPetProcessor;
import pe.unjfsc.daw.spet.writer.CDLostPetWriter;

@Configuration
@EnableBatchProcessing
public class DefineJobsConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public DelimitedLineTokenizer getLostPetTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames(new String[]{
				"IDPET", "USER",
				"PET", "CREATEDAT",
				"LATITUDE", "LONGITUDE",
				"DESCRIPTION", "LOCATED",
				"STATUS"});
		return tokenizer;
	}
	
	@Bean
	public DefaultLineMapper<CELostPetDTOEntrada> getLostPetLineMapper(){
		DefaultLineMapper<CELostPetDTOEntrada> lineM = new DefaultLineMapper<CELostPetDTOEntrada>();
		lineM.setLineTokenizer(getLostPetTokenizer());
		lineM.setFieldSetMapper(getLostPetMapper());
		return lineM;
	}
	
	@Bean
	public CLLostPetMapper getLostPetMapper() {
		return new CLLostPetMapper();
	}
	
	@Bean
	public FlatFileItemReader<CELostPetDTOEntrada> getLostPetReader() {
		FlatFileItemReader<CELostPetDTOEntrada> reader = new FlatFileItemReader<CELostPetDTOEntrada>();
		reader.setResource(new ClassPathResource("fuente/input/mascotasperdidas10000.csv"));
		reader.setLineMapper(getLostPetLineMapper());
		return reader;
	}
	
	@Bean
	public Job procesarLoteLostPet(JobLoteMovimientosListener listener, Step stepOne) {
		return jobBuilderFactory.get("procesarLoteLostPet")
				.listener(listener)
				.flow(stepOne)
				.end()
				.build();
	}
	
	@Bean
	public Step stepOne() {
		return stepBuilderFactory.get("stepOne")
				.<CELostPetDTOEntrada,CELostPetDTOSalida>chunk(5000)
				.reader(getLostPetReader())
				.processor(getCLLostPetProcessor())
				.writer(getCDLostPetWriter())
				.build();
	}
	
	@Bean
	public CLLostPetProcessor getCLLostPetProcessor() {
		return new CLLostPetProcessor();
	}
	
	@Bean
	public CDLostPetWriter getCDLostPetWriter() {
		return new CDLostPetWriter();
	}
	
}
