/**
 * 
 */
package project.crew.graph;

import project.crew.Pirate;
import project.graph.Edge;
import project.graph.Vertex;

/**
 * @author EL MONTASER Osmane
 *
 */
public class PirateEdge extends Edge<Pirate> {

	/**
	 * @param v1
	 * @param v2
	 */
	public PirateEdge(Vertex<Pirate> v1, Vertex<Pirate> v2) {
		super(v1, v2);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param weight
	 */
	public PirateEdge(Vertex<Pirate> v1, Vertex<Pirate> v2, double weight) {
		super(v1, v2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Les pirates " + v1 + " et " + v2 + " ne s'aiment pas.";
	}
}
