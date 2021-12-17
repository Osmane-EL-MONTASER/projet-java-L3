package project.crew.sharing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.crew.graph.PirateGraph;
import project.crew.graph.PirateVertex;
import project.graph.Vertex;

/**
 * Classe dans laquelle nous avons cod� une meilleure
 * version pour l'attribution du butin entre les
 * pirates.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 */
public class BetterSharing {
	
	/**
	 * Permet de faire une meilleure attribution que notre
	 * premi�re version. Cette version am�lior�e va attribuer
	 * en premier lieu les sommets "isol�s", ce qui devrait
	 * en principe rendre jaloux moins de pirates.
	 * 
	 * @param c L'�quipage sur lequel faire l'attribution.
	 */
	public static void doBetterSharing(Crew c) {
		ArrayList<Vertex<Pirate>> toMakeAttribution = getAdjacenceSortedVertices(c.getGraph());
		System.out.println("---------ATTRIBUTION AUTOMATIQUE---------");
		
		ArrayList<Treasure> remainingTreasures = new ArrayList<Treasure>(Arrays.asList(c.getTreasures()));
		LinkedHashMap<PirateVertex, Treasure> attributions = new LinkedHashMap<>();
		for(Vertex<Pirate> v : toMakeAttribution) {
			Treasure toGive = NaiveSharing.getLastTreasureAvailable(v.getLabel().getPreferences(), remainingTreasures);
			attributions.put((PirateVertex) v, toGive);
		}
		
		System.out.println("Voici l'attribution optimale trouv�e :");
		System.out.println(attributions);
		c.setAttributions(attributions);
		System.out.println("---------ATTRIBUTION AUTOMATIQUE---------");
	}
	
	/**
	 * Cette fonction va essayer de trouver une solution
	 * optimale en attribuant les tr�sors au hasard. D�s que
	 * 2 pirates ne sont plus jaloux, l'algorithme garde 
	 * l'affectation en m�moire.
	 * Elle am�liore un tout petit peu le r�sultat obtenu.
	 * Cependant elle a un grosse complexit�.
	 * 
	 * @param c L'�quipage o� trouver la solution.
	 */
	public static void doShuffleSharing(Crew c) {
		ArrayList<Vertex<Pirate>> toMakeAttribution = getAdjacenceSortedVertices(c.getGraph());
		System.out.println("---------ATTRIBUTION AUTOMATIQUE---------");
		
		ArrayList<Treasure> remainingTreasures = new ArrayList<Treasure>(Arrays.asList(c.getTreasures()));
		LinkedHashMap<PirateVertex, Treasure> attributions = new LinkedHashMap<>();
		for(Vertex<Pirate> v : toMakeAttribution) {
			Treasure toGive = NaiveSharing.getLastTreasureAvailable(v.getLabel().getPreferences(), remainingTreasures);
			attributions.put((PirateVertex) v, toGive);
		}
		c.setAttributions(attributions);
		
		double oldCost = Double.POSITIVE_INFINITY;
		double newCost = Double.POSITIVE_INFINITY;
		int tries = 0;
		
		do {
			ArrayList<ArrayList<Vertex<Pirate>>> jealousPirates = NaiveSharing.getJealous(c, c.getTreasures());
			ArrayList<Vertex<Pirate>> toChange = new ArrayList<>();
			remainingTreasures = new ArrayList<>();
			for(ArrayList<Vertex<Pirate>> jealousPair : jealousPirates) {
				if(attributions.containsKey(jealousPair.get(0))) {
					toChange.add(jealousPair.get(0));
					remainingTreasures.add(attributions.get(jealousPair.get(0)));
					attributions.remove(jealousPair.get(0));
				}
				
				if(attributions.containsKey(jealousPair.get(1))) {
					toChange.add(jealousPair.get(1));
					remainingTreasures.add(attributions.get(jealousPair.get(1)));
					attributions.remove(jealousPair.get(1));
				}
			}
			
			Collections.shuffle(toChange);
			for(Vertex<Pirate> v : toChange) {
				Treasure toGive = NaiveSharing.getLastTreasureAvailable(v.getLabel().getPreferences(), remainingTreasures);
				attributions.put((PirateVertex) v, toGive);
			}
			
			newCost = NaiveSharing.getAttributionCost(c, c.getTreasures());
			if(newCost < oldCost) {
				oldCost = newCost;
				tries = 0;
			} else if(newCost == oldCost)
				tries++;
		} while(oldCost <= newCost && tries < 5000);
		
		System.out.println("Voici l'attribution optimale trouv�e :");
		System.out.println(attributions);
		System.out.println("---------ATTRIBUTION AUTOMATIQUE---------");
	}
	
	/**
	 * Permet de r�cup�rer les sommets dans l'ordre de
	 * leur adjacence pour leur faire leur attribution.
	 * 
	 * @param g Le graphe de pirates.
	 * @return
	 */
	private static ArrayList<Vertex<Pirate>> getAdjacenceSortedVertices(PirateGraph g) {
		int graphOrder = g.getOrder();
		ArrayList<Vertex<Pirate>> vertices = new ArrayList<>();
		for(Vertex<Pirate> v : g.getVertices())
			vertices.add(v);
		ArrayList<Vertex<Pirate>> sortedList = new ArrayList<>();
		
		while(sortedList.size() != graphOrder) {
			int min = -1;
			Vertex<Pirate> minV = null;
			for(Vertex<Pirate> v : vertices) {
				int neighbours = g.getNeighbourNumber(v);
				if(min == -1 || neighbours < min) {
					min = neighbours;
					minV = v;
				}
			}
			
			sortedList.add(minV);
			vertices.remove(minV);
		}
		
		return sortedList;
	}
}
