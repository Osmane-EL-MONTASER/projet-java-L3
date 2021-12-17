package project.crew.graph;

import project.crew.Pirate;
import project.graph.Vertex;

/**
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 *
 */
public class PirateVertex extends Vertex<Pirate> {

	/**
	 * Construit un sommet de pirate.
	 * 
	 * @param label Le pirate en question.
	 */
	public PirateVertex(Pirate label) {
		super(label);
	}
	
	/**
	 * Affichage personnalisé d'un sommet de pirate.
	 */
	@Override
	public String toString() {
		return this.label.toString();
	}
}
