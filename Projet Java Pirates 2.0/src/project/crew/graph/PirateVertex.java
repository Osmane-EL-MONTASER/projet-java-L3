/**
 * 
 */
package project.crew.graph;

import java.util.ArrayList;

import project.crew.Pirate;
import project.graph.Vertex;

/**
 * @author EL MONTASER Osmane
 *
 */
public class PirateVertex extends Vertex<Pirate> {

	/**
	 * @param label
	 */
	public PirateVertex(Pirate label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param label
	 */
	public PirateVertex(Pirate label, ArrayList<Pirate> pirates) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return this.label.toString();
	}
}
