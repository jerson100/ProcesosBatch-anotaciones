package pe.unjfsc.daw.spet.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import pe.unjfsc.daw.spet.entity.CELostPetDTOSalida;

public class CDLostPetWriter implements ItemWriter<CELostPetDTOSalida> { 


	  private static final Logger LOG = LoggerFactory.getLogger(CDLostPetWriter.class); 

	  /*
	   * La salidas puede ser en distintos formatos...
	   * */
	  public void write(List<? extends CELostPetDTOSalida> poLostPetSalida) throws Exception { 

	     LOG.info("[DAW] =====[ Start write ]====="); 

	     LOG.info("[DAW] Object output : {} ", poLostPetSalida.toString()); 

	     for(CELostPetDTOSalida lostPet:poLostPetSalida) { 

	        LOG.info("[DAW] lostPet read  : {}", lostPet.toString()); 

	     } 
	     
	     /*String fileName = JOptionPane.showInputDialog("Ingrese el nombre del archivo en el que se guardará,"
	     		+ "\nSi no ingresa nada se generará con la fecha");
	     
	     if(fileName.isEmpty()) {
	    	 fileName = "" + new Date().getTime();
	     }*/
	     
	     writeFileInSystem(poLostPetSalida, new Date().getTime()+".txt");

	  }
	  
	  public void writeFileInSystem(List<? extends CELostPetDTOSalida> lostPets, String file) throws IOException {
		  LOG.info("[DAW] =====[ Empezando a generar el archivo de texto ]=====");
		  File f;
		  FileWriter w;
		  BufferedWriter bw=null;
		  PrintWriter wr=null;
		  try {
			  f = new File("src/main/resources/fuente/output/" + file);
			  w = new FileWriter(f);
			  bw=new BufferedWriter(w);
			  wr=new PrintWriter(bw);
			  for(CELostPetDTOSalida lostPet:lostPets) {
				  wr.append(WriteObjectToTextFile(lostPet));
			  }
			  LOG.info("[DAW] =====[ Se terminó de generar el archivo de texto ]=====");
			} catch (IOException e) {
				e.printStackTrace();
				LOG.error("[DAW] =====[ Ocurrió un error al generar el archivo ]=====");
			} finally {
				if(wr!=null) {
					wr.close();
				}
				if(bw!=null) {
					bw.close();
				}
				LOG.info("[DAW] =====[ Cerrando conexión de escritura de los archivos ]=====");
			}
	  }
	  
	  public String WriteObjectToTextFile(CELostPetDTOSalida obj) {
		  StringBuilder builder = new StringBuilder();
		  builder.append(obj.getIdPet());
			builder.append(",");
			builder.append('"'+obj.getUser()+'"');
			builder.append(",");
			builder.append('"'+obj.getPet()+'"');
			builder.append(",");
			Date d = obj.getCreatedAt();
			ZoneId timeZone = ZoneId.systemDefault();
	        LocalDate getLocalDate = d.toInstant().atZone(timeZone).toLocalDate();
			builder.append(getLocalDate.getYear()+"-"+
					addZero(getLocalDate.getMonthValue())+"-"+
					addZero(getLocalDate.getDayOfMonth()));
			builder.append(",");
			builder.append(obj.getLatitude());
			builder.append(",");
			builder.append(obj.getLongitude());
			builder.append(",");
			builder.append('"'+obj.getDescription()+'"');
			builder.append(",");
			builder.append(obj.isLocated());
			builder.append(",");
			builder.append(obj.getStatus());
			builder.append("\n");
		  return builder.toString();
	  }
	  
	  public String addZero(int value) {
		  return (value > 9 ? "" : "0" ) + value;
	  }
	  

	} 