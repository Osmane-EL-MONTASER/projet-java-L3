package projet.java.graphe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import projet.java.main.Pirate;

public class GraphePirate {
	//Une matrice d'adjacence.
	private LinkedHashMap<Vertex<Pirate, Pirate>, Boolean> adjacency;
	private int nbPirate;
	
	public GraphePirate(int nbPirate, ArrayList<Pirate> pirates) {
		this.nbPirate = nbPirate;
		
		adjacency = new LinkedHashMap<>();
		
		for (int i = 0; i < nbPirate; i++)
			for (int j = 0; j < nbPirate; j++)
				adjacency.put(new Vertex<Pirate, Pirate>(pirates.get(i), pirates.get(j)), false);
	}
	
	public void changeRelation(Pirate p1, Pirate p2, boolean r) {
		adjacency.replace(new Vertex<Pirate, Pirate>(p1, p2), r);
	}
	
	public boolean getRelation(Pirate p1, Pirate p2) {
		return adjacency.get(new Vertex<Pirate, Pirate>(p1, p2));
	}
	
	@Override
	public String toString() {
		String str = new String();
		int i = 0;
		
		str += "Matrice d'adjacence de l'équipage : \n";
		for (Entry<Vertex<Pirate, Pirate>, Boolean> e : adjacency.entrySet()) {
			if (i == nbPirate) {
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
