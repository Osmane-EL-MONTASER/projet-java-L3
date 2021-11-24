package project.crew.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.graph.Edge;
import project.graph.Vertex;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.InvalidCrewFileFormatException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Cette classe permet de parser un fichier contenant
 * toutes les informations d'un équipage de pirates.
 * Elle permet de parser un fichier texte ainsi qu'un
 * fichier CSV.
 * Plus d'informations sur le format des données
 * dans le manuel d'utilisation.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 1.0
 * @since 3.0
 */
public class CrewParser {
	
	public final static int BUFF_SIZE = 255;
	
	/**
	 * Cette fonction permet de parser le contenu d'un
	 * fichier texte contenant toutes les informations
	 * d'un équipage et de le convertir en un objet de 
	 * type Crew.
	 * 
	 * @param filename Le nom du fichier texte à parser.
	 * 
	 * @throws InvalidFormatException Quand la syntaxe
	 * du fichier à parser est incorrecte.
	 * 
	 * @return Un objet de type Crew représentant 
	 * l'équipage et ses trésors.
	 */
	public static Crew parseFromTxtFile(String filename) throws InvalidCrewFileFormatException {
		Crew temp = new Crew();
		
		FileReader fReader = null;
		
		try {
			fReader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier d'équipage est introuvable !");
		}
		
		if(fReader != null) {
			ArrayList<Treasure> treasures = new ArrayList<>();
			BufferedReader bReader = new BufferedReader(fReader);
			String line = new String();
			try {
				while((line = bReader.readLine()) != null) {
					String splitted[] = line.split("[(]");
					switch(splitted[0]) {
					case "pirate":
						String pirateName = splitted[1].split("[)]")[0];
						temp.addCrewMember(new Pirate(pirateName, null));
						break;
					case "objet":
						String objectName = splitted[1].split("[)]")[0];
						treasures.add(new Treasure(objectName));
						break;
					case "deteste":
						String pirateName1 = splitted[1].split("[)]")[0].split("[,]")[0];
						String pirateName2 = splitted[1].split("[)]")[0].split(", ")[1];
						
						try {
							temp.addBadRelations(getVertexIndexFromName(pirateName1, temp), getVertexIndexFromName(pirateName2, temp));
						} catch (IllegalArgumentException | EdgeDuplicateException | VertexNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case "preferences":
						String tempPrefStr = splitted[1].split("[)]")[0];
						String pirateNamePref = tempPrefStr.split(", ")[0];
						ArrayList<Treasure> preferences = new ArrayList<>();
						
						boolean namePass = false;
						for(String str : tempPrefStr.split(", ")) {
							if(!namePass)
								namePass = true;
							else
								preferences.add(treasures.get(getTreasureIndexFromName(str, treasures)));
						}
						
						if(preferences.size() < treasures.size())
							throw(new InvalidCrewFileFormatException("Le nombre de préférences pour " + pirateNamePref + " est insuffisant."));
						
						try {
							temp.getGraph().getVertex(getVertexIndexFromName(pirateNamePref, temp)).getLabel().setPreferences(
									preferences.toArray(new Treasure[treasures.size()]));
						} catch (VertexNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				
				temp.setTreasures(treasures.toArray(new Treasure[treasures.size()]));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			fReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	public static void saveCrewToDisk(String filename, Crew c) {
		File f = new File(filename);
		
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		PrintStream ps = new PrintStream(os);
		
		for(Vertex<Pirate> p : c.getGraph().getVertices())
			ps.println("pirate(" + p + ").");
		
		if(c.getTreasures() != null)
			for(Treasure t : c.getTreasures())
				ps.println("objet(" + t + ").");
		
		for(Edge<Pirate> e : c.getGraph().getEdges())
			ps.println("deteste(" + e.getV1() + ", " + e.getV2() + ").");
		
		for(Vertex<Pirate> p : c.getGraph().getVertices()) {
			if(p.getLabel().getPreferences() != null) {
				ps.print("preferences(" + p + ", ");
				Treasure[] prefs = p.getLabel().getPreferences();
				for(int i = 0; i < prefs.length; i++) {
					if(i == prefs.length - 1)
						ps.println(prefs[i] + ").");
					else
						ps.print(prefs[i] + ", ");
				}
			}
		}
		ps.close();
		try {
			os.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public static void parseFromCSVFile() {
		//TODO !!!
	}
	
	private static int getTreasureIndexFromName(String name, ArrayList<Treasure> treasures) {
		int notFound = -1;
		int i = 0;
		for(Treasure t : treasures) {
			if(t.toString().equals(name))
				return i;
			i++;
		}
		return notFound;
	}
	
	private static int getVertexIndexFromName(String name, Crew c) {
		int notFound = -1;
		int i = 0;
		for(Vertex<Pirate> pv : c.getGraph().getVertices()) {
			if(pv.getLabel().getName().equals(name))
				return i;
			i++;
		}
		return notFound;
	}
	
	public static void main(String args[]) {
		try {
			System.out.println(parseFromTxtFile("crew.txt"));
		} catch (InvalidCrewFileFormatException e) {
			e.printStackTrace();
		}
		
		/*try {
			saveCrewToDisk("crewSaved.txt", parseFromTxtFile("crewSaved.txt"));
		} catch (InvalidCrewFileFormatException e) {
			e.printStackTrace();
		}*/
	}
}
