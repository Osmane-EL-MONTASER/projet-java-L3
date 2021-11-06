package project.crew.graph;

import project.crew.Pirate;
import project.graph.Edge;
import project.graph.Vertex;

/**
 * Repr�sente une ar�te compos� de 2 sommets de pirates.
 * Elle permet un affichage diff�rent de celui des classes
 * g�n�rqiues.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class PirateEdge extends Edge<Pirate> {

	/**
	 * Construit une ar�te de pirates avec les sommets
	 * v1 et v2 pass�s en param�tres.
	 * 
	 * @param v1 Premier pirate.
	 * @param v2 Deuxi�me pirate.
	 */
	public PirateEdge(Vertex<Pirate> v1, Vertex<Pirate> v2) {
		super(v1, v2);
	}
	
	/**
	 * Affichage personnalis� pour une ar�te de pirate.
	 */
	@Override
	public String toString() {
		return "Les pirates " + v1 + " et " + v2 + " ne s'aiment pas.";
	}
}
