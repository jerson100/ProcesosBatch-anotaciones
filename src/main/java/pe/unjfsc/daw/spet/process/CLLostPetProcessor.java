package pe.unjfsc.daw.spet.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import pe.unjfsc.daw.spet.entity.CELostPetDTOEntrada;
import pe.unjfsc.daw.spet.entity.CELostPetDTOSalida;

public class CLLostPetProcessor implements ItemProcessor<CELostPetDTOEntrada, CELostPetDTOSalida> { 

	  private static final Logger MOLOG = LoggerFactory.getLogger(CLLostPetProcessor.class); 

	  public CELostPetDTOSalida process(CELostPetDTOEntrada poItemLostPet) throws Exception { 

	     MOLOG.info("[DAW] =====[ Start process ]====="); 

	     MOLOG.info("[DAW] Data received input : {} ", poItemLostPet.toString()); 

	     CELostPetDTOSalida oLostPetSalida = new CELostPetDTOSalida(); 

	     oLostPetSalida.setIdPet(poItemLostPet.getIdPet()); 
	     oLostPetSalida.setUser(poItemLostPet.getUser()); 
	     oLostPetSalida.setPet(poItemLostPet.getPet()); 
	     oLostPetSalida.setLatitude(poItemLostPet.getLatitude()); 
	     oLostPetSalida.setLongitude(poItemLostPet.getLongitude()); 
	     oLostPetSalida.setDescription(poItemLostPet.getDescription()); 
	     oLostPetSalida.setLocated(poItemLostPet.isLocated()); 
	     oLostPetSalida.setStatus(poItemLostPet.getStatus());
	     oLostPetSalida.setCreatedAt(poItemLostPet.getCreatedAt());

	     MOLOG.info("[DAW] New CELostPetDTOSalida : {} ", oLostPetSalida.toString()); 

	     return oLostPetSalida; 

	  } 

} 