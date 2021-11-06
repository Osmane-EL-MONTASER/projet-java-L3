package project.crew;

import java.util.LinkedHashMap;

import project.crew.graph.PirateGraph;
import project.crew.graph.PirateVertex;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Représente l'équipage avec ses pirates
 * et ses trésors.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class Crew {
	PirateGraph pg;
	LinkedHashMap<PirateVertex, Treasure> attributions;
	
	public Crew() {
		pg = new PirateGraph();
		attributions = new LinkedHashMap<>();
	}
	
	public void addCrewMember(Pirate p) {
		pg.addVertex(new PirateVertex(p));
	}
	
	public void addBadRelations(int i, int j) throws IllegalArgumentException, EdgeDuplicateException {
		try {
			pg.addEdge(i, j);
		} catch(IllegalArgumentException e) {
			throw(e);
		}
	}
	
	public boolean haveBadRelations(int i, int j)  throws IllegalArgumentException, VertexNotFoundException {
		try {
			return pg.isEdgeExists(pg.getVertex(i), pg.getVertex(j));
		} catch(IllegalArgumentException e) {
			throw(e);
		}
	}
	
	public PirateGraph getGraph() {
		return pg;
	}
	
	public LinkedHashMap<PirateVertex, Treasure> getAttributions() {
		return attributions;
	}
	
	public void setAttributions(LinkedHashMap<PirateVertex, Treasure> attributions) {
		this.attributions = attributions;
	}
}
