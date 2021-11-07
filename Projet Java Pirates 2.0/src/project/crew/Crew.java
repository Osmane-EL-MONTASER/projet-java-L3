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
 * @author VIDART Paul
 * @version 1.0
 * @since 2.0
 */
public class Crew {
	PirateGraph pg;
	LinkedHashMap<PirateVertex, Treasure> attributions;
	
	/**
	 * Initialise le graphe de pirates et la liste des 
	 * attributions.
	 */
	public Crew() {
		pg = new PirateGraph();
		attributions = new LinkedHashMap<>();
	}
	
	/**
	 * Ajoute un membre dans l'équipage.
	 * 
	 * @param p Le pirate à ajouter dans l'équipage.
	 */
	public void addCrewMember(Pirate p) {
		pg.addVertex(new PirateVertex(p));
	}
	
	/**
	 * Dire que les 2 pirates aux ids i et j ne s'entendent
	 * pas bien entre eux. Ce qui peut les rendre jaloux
	 * lors de la distribution du butin.
	 * 
	 * @param i Id du premier pirate.
	 * @param j Id du deuxième pirate.
	 * @throws IllegalArgumentException Si un des pirates n'est pas 
	 * présent dans l'équipage.
	 * @throws EdgeDuplicateException Si les pirates s'entendent
	 * déjà mal.
	 */
	public void addBadRelations(int i, int j) throws IllegalArgumentException, EdgeDuplicateException {
		try {
			pg.addEdge(i, j);
		} catch(IllegalArgumentException e) {
			throw(e);
		}
	}
	
	/**
	 * Vérifier si les pirates aux ids i et j ont des mauvaises
	 * relations ou non.
	 * 
	 * @param i Id du premier pirate.
	 * @param j Id du deuxième pirate.
	 * @return True si les pirates ne s'entendent pas, False sinon.
	 * @throws VertexNotFoundException Si un des pirates n'est pas 
	 * présent dans l'équipage.
	 */
	public boolean haveBadRelations(int i, int j)  throws VertexNotFoundException {
		return pg.isEdgeExists(pg.getVertex(i), pg.getVertex(j));
	}
	
	/**
	 * Récupérer le graphe correspondant à l'équipage et ses 
	 * relations.
	 * 
	 * @return Un graphe de l'équipage.
	 */
	public PirateGraph getGraph() {
		return pg;
	}
	
	/**
	 * Récupérer les attributions faites manuellement ou 
	 * automatiquement.
	 * 
	 * @return Les attributions du butin.
	 */
	public LinkedHashMap<PirateVertex, Treasure> getAttributions() {
		return attributions;
	}
	
	/**
	 * Distribuer le butin.
	 * 
	 * @param attributions les attributions à remplacer.
	 */
	public void setAttributions(LinkedHashMap<PirateVertex, Treasure> attributions) {
		this.attributions = attributions;
	}
}
