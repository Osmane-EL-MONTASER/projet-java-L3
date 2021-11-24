package project.crew;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import project.crew.graph.PirateGraph;
import project.crew.graph.PirateVertex;
import project.graph.Vertex;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Repr�sente l'�quipage avec ses pirates
 * et ses tr�sors.
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
	 * Ajoute un membre dans l'�quipage.
	 * 
	 * @param p Le pirate � ajouter dans l'�quipage.
	 * @since 1.0
	 */
	public void addCrewMember(Pirate p) {
		pg.addVertex(new PirateVertex(p));
	}
	
	/**
	 * Supprime un membre dans l'�quipage.
	 * 
	 * @param i Le pirate � ajouter dans l'�quipage.
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
	 * @param j Id du deuxi�me pirate.
	 * @throws IllegalArgumentException Si un des pirates n'est pas 
	 * pr�sent dans l'�quipage.
	 * @throws EdgeDuplicateException Si les pirates s'entendent
	 * d�j� mal.
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
	 * V�rifier si les pirates aux ids i et j ont des mauvaises
	 * relations ou non.
	 * 
	 * @param i Id du premier pirate.
	 * @param j Id du deuxi�me pirate.
	 * @return True si les pirates ne s'entendent pas, False sinon.
	 * @throws VertexNotFoundException Si un des pirates n'est pas 
	 * pr�sent dans l'�quipage.
	 * @since 1.0
	 */
	public boolean haveBadRelations(int i, int j)  throws VertexNotFoundException {
		return pg.isEdgeExists(pg.getVertex(i), pg.getVertex(j));
	}
	
	/**
	 * R�cup�rer le graphe correspondant � l'�quipage et ses 
	 * relations.
	 * 
	 * @return Un graphe de l'�quipage.
	 * @since 1.0
	 */
	public PirateGraph getGraph() {
		return pg;
	}
	
	/**
	 * R�cup�rer les attributions faites manuellement ou 
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
	 * @param attributions les attributions � remplacer.
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
		str += "\nLes tr�sors � partager :\n";
		
		if(treasures != null)
			for(Treasure t : treasures)
				str += "- " + t + ".\n";
		
		return str;
	}
}
