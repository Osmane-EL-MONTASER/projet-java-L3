package projet.java.graphe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import projet.java.main.Pirate;

/**
 * Représente un graphe de pirates. Elle n'a pour l'instant
 * pas de fonction spécifique à un graphe de pirates.
 * 
 * Il est à noter que cette classe ne faisait pas parti
 * du projet à sa version 1.0.0 où nous utilisions encore
 * des listes pour représenter les pirates et les 
 * relations.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 1.0
 */
public class GraphePirate extends Graph<Pirate> {
	/**
	 * Construit un graphe de pirates à partir de leur nombre
	 * et d'une liste de Pirate.
	 * 
	 * @param nbPirate Nombre de pirates de l'équipage.
	 * @param pirates La liste des pirates de l'équipage.
	 * 
	 * @throws IllegalArgumentException Si le nombre de pirate
	 * 									n'est pas compris dans [1;26].
	 * 
	 * @since 1.0
	 */
	public GraphePirate(int nbPirate, ArrayList<Pirate> pirates) throws IllegalArgumentException {
		super(nbPirate, pirates);
		
		if (nbPirate <= 0 || nbPirate > 26)
			throw(new IllegalArgumentException("Le nombre de pirates doit être supérieur à 0 et inférieur à 26."));
	}
	
	/**
	 * Cette fonction affiche le graphe de pirates sous forme
	 * de matrice d'adjacence et la liste des pirates.
	 * 
	 * @return Le String correspondant au graphe.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		String str = new String();
		int i = 0;
		
		LinkedHashMap<Vertex<Pirate, Pirate>, Boolean> temp = (LinkedHashMap<Vertex<Pirate, Pirate>, Boolean>) adjacency;
		
		str += "Liste des pirates (sommets) : \n";
		for (Vertex<Pirate, Pirate> e : temp.keySet()) {
			if (i == 3) {
				str += e.getKey1() + "\t";
				i = 0;
			} else { 
				i++;
			}
		}
		str += "\n";
		
		str += "Matrice d'adjacence de l'équipage : \n";
		for (Entry<Vertex<Pirate, Pirate>, Boolean> e : temp.entrySet()) {
			if (i == nbVertices) {
				str += "\n";
				i = 0;
			}
				
			str += e.getValue() + "\t";
			i++;
		}
		
		str += "\n";
		
		return str;
	}
}
