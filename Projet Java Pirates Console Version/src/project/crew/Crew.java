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
 * On peut aussi récupérer la liste des pirates
 * qui sont jaloux et les attributions.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 2.0
 * @since 2.0
 */
public class Crew {
	/**
	 * Le graphe de pirates correspondant à l'équipage.
	 */
	private PirateGraph pg;
	/**
	 * La liste des attributions qui ont été faites. Elle
	 * est initialisée à une liste vide.
	 */
	private LinkedHashMap<PirateVertex, Treasure> attributions;
	/**
	 * Liste des trésors à partager, initialisé à un tableau
	 * vide.
	 */
	private Treasure treasures[];
	/**
	 * Liste des pirates qui sont jaloux, initialisé à une liste
	 * vide.
	 */
	private ArrayList<Vertex<Pirate>> jealousPirates;
	
	/**
	 * Initialise le graphe de pirates, la liste des 
	 * attributions, la liste des pirates jaloux et
	 * la liste de attributions.
	 * @since 1.0
	 * @version 2.0
	 */
	public Crew() {
		pg = new PirateGraph();
		attributions = new LinkedHashMap<>();
		jealousPirates = new ArrayList<>();
		treasures = new Treasure[1];
	}
	
	/**
	 * Récupérer la liste des pirates jaloux dans cet
	 * équipage.
	 * 
	 * @return La liste des pirates jaloux.
	 */
	public ArrayList<Vertex<Pirate>> getJealousPirates() {
		return jealousPirates;
	}
	
	/**
	 * Définir quels sont les pirates jaloux dans cet
	 * équipage.
	 * 
	 * @param jealousPirates La nouvelle liste de pirates
	 * jaloux.
	 */
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
	 * @throws VertexNotFoundException Si la vertex n'a pas été trouvée.
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
	
	/**
	 * Permet de récupérer un tableau de trésors.
	 * @return un tableau de trésors.
	 */
	public Treasure[] getTreasures() {
		return treasures;
	}
	
	/**
	 * Permet de définir le tableau de trésors à cet 
	 * équipage.
	 * @param treasures le nouveau tableau de trésors.
	 */
	public void setTreasures(Treasure[] treasures) {
		this.treasures = treasures;
	}
	
	/**
	 * Redéfinition de la méthode toString() afin de
	 * l'afficher plus rapidement dans la console.
	 */
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
