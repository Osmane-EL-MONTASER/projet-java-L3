package project.crew;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import project.crew.graph.PirateGraph;
import project.crew.graph.PirateVertex;
import project.graph.Vertex;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Représente l'équipage avec ses pirates
 * et ses trésors.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 2.0
 * @since 2.0
 */
public class Crew {
	private PirateGraph pg;
	private LinkedHashMap<PirateVertex, Treasure> attributions;
	private Treasure treasures[];
	private ArrayList<Vertex<Pirate>> jealousPirates;
	
	/**
	 * Initialise le graphe de pirates et la liste des 
	 * attributions.
	 * @since 1.0
	 */
	public Crew() {
		pg = new PirateGraph();
		attributions = new LinkedHashMap<>();
		jealousPirates = new ArrayList<>();
	}
	
	public ArrayList<Vertex<Pirate>> getJealousPirates() {
		return jealousPirates;
	}
	
	public void setJealousPirate(ArrayList<Vertex<Pirate>> jealousPirates) {
		this.jealousPirates = jealousPirates;
	}
	
	/**
	 * Ajoute un membre dans l'équipage.
	 * 
	 * @param p Le pirate à ajouter dans l'équipage.
	 * @since 1.0
	 */
	public void addCrewMember(Pirate p) {
		pg.addVertex(new PirateVertex(p));
	}
	
	/**
	 * Supprime un membre dans l'équipage.
	 * 
	 * @param i Le pirate à ajouter dans l'équipage.
	 * @since 2.0
	 */
	public void deleteCrewMember(int i) {
		try {
			pg.deleteVertex(pg.getVertex(i));
		} catch (VertexNotFoundException e) {
			e.printStackTrace();
		}
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
	 * @throws VertexNotFoundException 
	 * @since 1.0
	 */
	public void addBadRelations(int i, int j) throws IllegalArgumentException, EdgeDuplicateException, VertexNotFoundException {
		try {
			pg.addEdge(pg.getVertex(i), pg.getVertex(j));
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
	 * @since 1.0
	 */
	public boolean haveBadRelations(int i, int j)  throws VertexNotFoundException {
		return pg.isEdgeExists(pg.getVertex(i), pg.getVertex(j));
	}
	
	/**
	 * Récupérer le graphe correspondant à l'équipage et ses 
	 * relations.
	 * 
	 * @return Un graphe de l'équipage.
	 * @since 1.0
	 */
	public PirateGraph getGraph() {
		return pg;
	}
	
	/**
	 * Récupérer les attributions faites manuellement ou 
	 * automatiquement.
	 * 
	 * @return Les attributions du butin.
	 * @since 1.0
	 */
	public LinkedHashMap<PirateVertex, Treasure> getAttributions() {
		return attributions;
	}
	
	/**
	 * Distribuer le butin.
	 * 
	 * @param attributions les attributions à remplacer.
	 * @since 1.0
	 */
	public void setAttributions(LinkedHashMap<PirateVertex, Treasure> attributions) {
		this.attributions = attributions;
	}
	
	public Treasure[] getTreasures() {
		return treasures;
	}
	
	public void setTreasures(Treasure[] treasures) {
		this.treasures = treasures;
	}
	
	@Override
	public String toString() {
		String str = new String();
		
		str += pg;
		str += "\nLes trésors à partager :\n";
		
		if(treasures != null)
			for(Treasure t : treasures)
				str += "- " + t + ".\n";
		
		return str;
	}
}
