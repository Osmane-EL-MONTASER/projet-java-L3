package project.crew.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map.Entry;

import project.crew.Crew;
import project.crew.Treasure;
import project.crew.graph.PirateVertex;
import project.crew.sharing.NaiveSharing;

/**
 * Classe dans laquelle se trouve une fonction
 * qui permet de sauvegarder une solution dans un
 * fichier texte.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @since 3.0
 * @version 1.0
 */
public class SolutionParser {
	/**
	 * Sauvegarder la solution actuelle dans un 
	 * fichier texte.
	 * 
	 * @param c L'équipage dans le quel la solution est
	 * stockée.
	 * @param path Le chemin vers lequel la solution sera
	 * sauvegardée.
	 * @throws FileNotFoundException Si le chemin spécifié est
	 * introuvable.
	 */
	public static void saveSolution(Crew c, String path) throws FileNotFoundException {
		File f = new File(path);
		
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			throw(new FileNotFoundException(e.getMessage()));
		}
		
		PrintStream ps = new PrintStream(os);
		
		ps.println("Cout=" + NaiveSharing.getAttributionCost(c, c.getTreasures()));
		
		for(Entry<PirateVertex, Treasure> v : c.getAttributions().entrySet())
			ps.println(v.getKey() + ":" + v.getValue());
		
		ps.close();
		try {
			os.close();
		} catch (IOException e1) {
			System.out.println("Impossible de fermer le fichier.");
		}
	}
}
