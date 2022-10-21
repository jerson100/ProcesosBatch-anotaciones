package pe.unjfsc.daw.spet.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import pe.unjfsc.daw.spet.entity.CELostPetDTOEntrada;

public class CLLostPetMapper implements FieldSetMapper<CELostPetDTOEntrada> {

	private static final Logger MOLOG = LoggerFactory.getLogger(CLLostPetMapper.class);
	/*Aquí podemos hacer algunas validaciones también*/
	@Override
	public CELostPetDTOEntrada mapFieldSet(FieldSet fieldSet) throws BindException {
		 MOLOG.info("[DAW] =====[ Start mapFieldSet ]====="); 
	     MOLOG.info("[DAW] read record : {} ", fieldSet.toString()); 
	     CELostPetDTOEntrada oLostPetEntrada = new CELostPetDTOEntrada(); 
	     oLostPetEntrada.setIdPet(fieldSet.readInt("IDPET")); 
	     oLostPetEntrada.setUser(fieldSet.readString("USER")); 
	     oLostPetEntrada.setPet(fieldSet.readString("PET")); 
	     oLostPetEntrada.setLatitude(fieldSet.readDouble("LATITUDE")); 
	     oLostPetEntrada.setLongitude(fieldSet.readDouble("LONGITUDE")); 
	     oLostPetEntrada.setDescription(fieldSet.readString("DESCRIPTION")); 
	     oLostPetEntrada.setLocated(fieldSet.readBoolean("LOCATED")); 
	     oLostPetEntrada.setStatus(fieldSet.readInt("STATUS"));
	     oLostPetEntrada.setCreatedAt(fieldSet.readDate("CREATEDAT"));
	     MOLOG.info("[DAW] Mapper to CELostPetDTOEntrada : {}", oLostPetEntrada.toString()); 
	     return oLostPetEntrada; 
	} 
 /*
  * 	Nos permite mapear los datos que vienen del archivo, en este
  *     caso csv a el objeto respectivo(CELostPetDTOEntrada)
  * */
}
