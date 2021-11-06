package projet.java.graphe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Cette classe générique permet de représenter tout type 
 * de graphe.
 * Il faut préciser le type de chaque sommet du graphe.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * 
 * @param <T> Représente le type de chaque sommet du 
 * graphe
 * 
 * @since 1.0
 */
public class Graph<T> {
	/**
	 * Une LinkedHashMap pour représenter la matrice 
	 * d'adjacence d'un graphe et qui lui permet de 
	 * conserver l'ordre d'insertion des sommets pour 
	 * que la matrice soit dans le bon ordre lors de son affichage.
	 */
	protected LinkedHashMap<Vertex<T, T>, Boolean> adjacency;
	protected int nbVertices;
	
	/**
	 * Construit la matrice d'adjacence à partir du nombre
	 * de sommets et d'une liste de sommets passée en paramètre.
	 * 
	 * @param nbT Le nombre de sommets du graphe.
	 * @param Ts La liste des sommets.
	 * 
	 * @since 1.0
	 */
	public Graph(int nbT, ArrayList<T> Ts) {
		this.nbVertices = nbT;
		
		adjacency = new LinkedHashMap<>();
		
		for (int i = 0; i < nbT; i++)
			for (int j = 0; j < nbT; j++)
				adjacency.put(new Vertex<T, T>(Ts.get(i), Ts.get(j)), false);
	}
	
	/**
	 * Ajoute une arête entre le sommet s1 et s2.
	 * 
	 * @param s1 Le premier sommet.
	 * @param s2 Le deuxième sommet.
	 * 
	 * @since 1.0
	 */
	public void addEdge(T s1, T s2) {
		adjacency.replace(new Vertex<T, T>(s1, s2), true);
		adjacency.replace(new Vertex<T, T>(s2, s1), true);
	}
	
	/**
	 * Supprime l'arête entre le sommet s1 et s2.
	 * 
	 * @param s1 Le premier sommet.
	 * @param s2 Le deuxième sommet.
	 * 
	 * @since 1.0
	 */
	public void deleteEdge(T s1, T s2) {
		adjacency.replace(new Vertex<T, T>(s1, s2), false);
		adjacency.replace(new Vertex<T, T>(s2, s1), false);
	}
	
	/**
	 * Dit s'il existe une arête entre les 2 sommets.
	 * 
	 * @param s1 Le premier sommet.
	 * @param s2 Le deuxième sommet.
	 * 
	 * @return True s'il existe une arête entre les 2 sommets,
	 * 		   False sinon.
	 * 
	 * @since 1.0
	 */
	public boolean isEdgeExists(T s1, T s2) {
		return adjacency.get(new Vertex<T, T>(s1, s2));
	}
	
	/**
	 * Cette fonction affiche le graphe sous forme de matrice
	 * d'adjacence.
	 * 
	 * @return Le String correspondant au graphe.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {_
		String str = new String();
		int i = 0;
		
		str += "Matrice d'adjacence : \n";
		for (Entry<Vertex<T, T>, Boolean> e : adjacency.entrySet()) {
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
