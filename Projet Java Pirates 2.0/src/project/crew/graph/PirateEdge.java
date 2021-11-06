package project.crew.graph;

import project.crew.Pirate;
import project.graph.Edge;
import project.graph.Vertex;

/**
 * Représente une arête composé de 2 sommets de pirates.
 * Elle permet un affichage différent de celui des classes
 * générqiues.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class PirateEdge extends Edge<Pirate> {

	/**
	 * Construit une arête de pirates avec les sommets
	 * v1 et v2 passés en paramètres.
	 * 
	 * @param v1 Premier pirate.
	 * @param v2 Deuxième pirate.
	 */
	public PirateEdge(Vertex<Pirate> v1, Vertex<Pirate> v2) {
		super(v1, v2);
	}
	
	/**
	 * Affichage personnalisé pour une arête de pirate.
	 */
	@Override
	public String toString() {
		return "Les pirates " + v1 + " et " + v2 + " ne s'aiment pas.";
	}
}
