package projet.java.graphe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Cette classe générique permet de représenter tout type 
 * de graphe.
 * Il faut préciser le type de chaque sommet du graphe.
 * 
 * @version 1.0.0
 * @since 2.0.0
 * 
 * @param <T> Représente le type de chaque sommet du 
 * graphe
 */
public class Graph<T> {
	//Une matrice d'adjacence.
	protected LinkedHashMap<Vertex<T, T>, Boolean> adjacency;
	protected int nbT;
	
	public Graph(int nbT, ArrayList<T> Ts) {
		this.nbT = nbT;
		
		adjacency = new LinkedHashMap<>();
		
		for (int i = 0; i < nbT; i++)
			for (int j = 0; j < nbT; j++)
				adjacency.put(new Vertex<T, T>(Ts.get(i), Ts.get(j)), false);
	}
	
	public void changeRelation(T p1, T p2, boolean r) {
		adjacency.replace(new Vertex<T, T>(p1, p2), r);
	}
	
	public boolean getRelation(T p1, T p2) {
		return adjacency.get(new Vertex<T, T>(p1, p2));
	}
	
	@Override
	public String toString() {
		String str = new String();
		int i = 0;
		
		str += "Matrice d'adjacence : \n";
		for (Entry<Vertex<T, T>, Boolean> e : adjacency.entrySet()) {
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
