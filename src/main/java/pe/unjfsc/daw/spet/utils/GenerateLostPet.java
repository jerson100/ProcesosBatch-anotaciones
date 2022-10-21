package pe.unjfsc.daw.spet.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerateLostPet {

	public static void main(String[] args) throws IOException {
		/*writeFileInSystem(generateLostPets(500), "mascotasperdidas500.csv", "src/main/resources/fuente/input/");
		writeFileInSystem(generateLostPets(1000), "mascotasperdidas1000.csv", "src/main/resources/fuente/input/");
		writeFileInSystem(generateLostPets(10000), "mascotasperdidas10000.csv", "src/main/resources/fuente/input/");
		writeFileInSystem(generateLostPets(100000), "mascotasperdidas100000.csv", "src/main/resources/fuente/input/");
		writeFileInSystem(generateLostPets(1000000), "mascotasperdidas1000000.csv", "src/main/resources/fuente/input/");
		writeFileInSystem(generateLostPets(10000000), "mascotasperdidas10000000.csv", "src/main/resources/fuente/input/");*/
		writeFileInSystem(generateLostPets(200000), "mascotasperdidas200000.csv", "src/main/resources/fuente/input/");
		writeFileInSystem(generateLostPets(300000), "mascotasperdidas300000.csv", "src/main/resources/fuente/input/");
	}
	
	
	public static String[] generateLostPets(int count) {
		String names[] = new String[]{"Juan","Pedro","Manuel", "Mirto", "Ruperto", "Marco", "María", "Ana"};
		String lastnames[] = new String[]{"Zaenz","Ramírez","Ortiz", "Solis", "Vasquez", "Claros","Quispe","Turpaleon"};
		String petNames[] = new String[] {"Lacy","Luna","Anlop","Alpy","Alio","Ajui","Apou","Aloiy","Alñoid"};
		String fras1[] = new String[] {"Se perdió por","La última vez que lo ví fue por",
				"No se por donde se me perdió exactamente pero creo que fue por", "Se fue por","Se marchó por", "Se fugó por"};
		String zonas[] = new String[] {"la plaza", "la pista", "la escuela x",
				"la villa", "la playa", "la vereda","el mercado de huacho", "el mercado de lima"};
		String tiempos[] = new String[] {"en la mañana", "en la tarde","en la noche","en la madrugada"};
		String acompañantes[] = new String[] {"solo", "con otros perros","con un niño","con una niña"};
		String lostPets[] = new String[count];
		for(int i = 0; i < count; i++) {
			StringBuilder str = new StringBuilder();
			str.append(i+1);
			str.append(",");str.append('"');
			str.append(names[generateNumber(0, names.length - 1)]);
			str.append(" ");
			str.append(lastnames[generateNumber(0, lastnames.length - 1)]);
			str.append('"');str.append(",");str.append('"');
			str.append(petNames[generateNumber(0, petNames.length - 1)]);
			str.append(generateNumber(1, count));
			str.append('"');str.append(",");
			str.append(generateNumber(2018, 2021));
			str.append("-");
			str.append(addZero(generateNumber(1, 12)));
			str.append("-");
			str.append(addZero(generateNumber(1, 25)));
			str.append(",");
			str.append(generateNumber(-70000, 50000));
			str.append(",");
			str.append(generateNumber(-70000, 50000));
			str.append(",");str.append('"');
			str.append(fras1[generateNumber(0, fras1.length - 1)]);
			str.append(" ");
			str.append(zonas[generateNumber(0, zonas.length - 1)]);
			str.append(" ");
			str.append(tiempos[generateNumber(0, tiempos.length - 1)]);
			str.append(" ");
			str.append(acompañantes[generateNumber(0, acompañantes.length - 1)]);
			str.append('"');str.append(",");
			str.append(false);
			str.append(",");
			str.append(1);
			lostPets[i] = str.toString();
		}
		return lostPets;
	}
	
	public static void writeFileInSystem(String data[], String file, String directory) throws IOException {
		  File f;
		  FileWriter w;
		  BufferedWriter bw=null;
		  PrintWriter wr=null;
		  try {
			  f = new File(directory + file);
			  w = new FileWriter(f);
			  bw=new BufferedWriter(w);
			  wr=new PrintWriter(bw);
			  for(int i = 0; i < data.length; i++) {
				  wr.append(data[i]+(i == data.length - 1 ? "":"\n"));
			  }
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(wr!=null) {
					wr.close();
				}
				if(bw!=null) {
					bw.close();
				}
			}
	  }
	
	public static int generateNumber(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1)); 
	}
	
	public static String addZero(int value) {
		  return (value > 9 ? "" : "0" ) + value;
	  }
	

}
