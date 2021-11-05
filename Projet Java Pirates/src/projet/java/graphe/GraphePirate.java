package projet.java.graphe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import projet.java.main.Pirate;

public class GraphePirate extends Graph {
	
	public GraphePirate(int nbPirate, ArrayList<Pirate> pirates) {
		super(nbPirate, pirates);
	}
	
	@Override
	public String toString() {
		String str = new String();
		int i = 0;
		
		LinkedHashMap<Vertex<Pirate, Pirate>, Boolean> temp = (LinkedHashMap<Vertex<Pirate, Pirate>, Boolean>) adjacency;
		
		str += "Matrice d'adjacence de l'équipage : \n";
		for (Entry<Vertex<Pirate, Pirate>, Boolean> e : temp.entrySet()) {
			if (i == nbT) {
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
