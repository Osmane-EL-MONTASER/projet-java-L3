package project.crew.graph;

import project.crew.Pirate;
import project.crew.Treasure;
import project.graph.Edge;
import project.graph.Graph;
import project.graph.Vertex;
import project.graph.exceptions.EdgeNotFoundException;

/**
 * Cette classe représente un graphe de pirates et
 * hérite de la classe Graph.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * 
 * @version 1.0
 * @since 2.0
 */
public class PirateGraph extends Graph<Pirate> {
	
	/**
	 * Récupérer l'arête entre les 2 vertex de pirates passés
	 * en paramètres.
	 * 
	 * @param v1 Premier sommet.
	 * @param v2 Deuxième sommet.
	 * @return L'arête entre v1 et v2 si elle existe.
	 * @throws EdgeNotFoundException Si l'arête n'existe pas dans le graphe.
	 */
	public PirateEdge getEdge(Vertex<Pirate> v1, Vertex<Pirate> v2) throws EdgeNotFoundException {
		if(!isEdgeExists(v1, v2))
			throw(new EdgeNotFoundException("Il n'y a pas d'arête entre les 2 pirates."));
		
		int i = edges.indexOf(new PirateEdge(v1, v2));
		
		if (i != -1)
			return new PirateEdge(v1, v2);
		else
			return new PirateEdge(v2, v1);
	}
	
	public int getNeighbourNumber(Vertex<Pirate> v) {
		int nb = 0;
		
		for(Edge<Pirate> edge : edges)
			if(edge.getV1() == v || edge.getV2() == v)
				nb++;
		
		return nb;
	}
	
	/**
	 * Override la fonction toString pour afficher les pirates
	 * présents dans l'équipage ainsi que leurs relations.
	 */
	@Override
	public String toString() {
		String str = new String();
		
		str += "-----------------------------------------\n";
		str += "Liste des pirates : \n";
		
		for(Vertex<Pirate> v : vertices) {
			str += new PirateVertex(v.getLabel()) + " (" + v.getId() + "), ses préférences -->  ";
			
			if(v.getLabel().getPreferences() != null)
				for(Treasure t : v.getLabel().getPreferences())
					str += t + " (" + t.getId() + ") ; ";
			
			str += "\n";
		}
		
		str += "\n-----------------------------------------\n";
		str += "Liste des relations entre les pirates :\n";
		for(Edge<Pirate> e : edges)
			str += new PirateEdge(e.getV1(), e.getV2()) + "\n";

		return str;
	}
}
