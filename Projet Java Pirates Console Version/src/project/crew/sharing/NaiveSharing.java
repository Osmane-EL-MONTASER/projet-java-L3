package project.crew.sharing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.crew.graph.PirateVertex;
import project.graph.Vertex;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Cette classe contient toutes les fonctions n�cessaires
 * au calcul d'une solution na�ve.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 1.0
 * @since 2.0
 */
public class NaiveSharing {
	/**
	 * Calcul le co�t du probl�me donn� en param�tre apr�s avoir
	 * fait une attribution du butin de fa�on manuelle ou na�ve 
	 * ou automatique.
	 * 
	 * @param c L'�quipage avec son attribution d�j� finie.
	 * @param treasures Le tableau des tr�sors du butin.
	 * @return Le co�t na�f.
	 */
	public static double getAttributionCost(Crew c, Treasure[] treasures) {
		double cost = 0.d;
		for(Map.Entry<PirateVertex, Treasure> a : c.getAttributions().entrySet()) {
			boolean alreadyJealous = false;
			int attributionRank = Arrays.asList(a.getKey().getLabel().getPreferences()).indexOf(a.getValue());
			//Si le pirate n'a pas eu ce qu'il voulait
			if(!(a.getKey().getLabel().getPreferences()[0] == a.getValue())) {
				//Regarder si il n'y a pas un pirate qui aurait re�u un objet qu'il aurait pr�f�r�
				//tout en sachant que le pirate ne peut �tre jaloux qu'une seule fois.
				for(int i = 0; i < attributionRank && !alreadyJealous; i++) {
					Treasure wish = Arrays.asList(treasures).get(Arrays.asList(treasures).indexOf(a.getKey().getLabel().getPreferences()[i]));
					
					for(int j = 0; j < c.getGraph().getOrder() && !alreadyJealous; j++) {
						try {
							//Si les pirates sont en mauvaises relations et qu'il a un objet qu'il aurait pr�f�r�.
							if(c.haveBadRelations(a.getKey().getId(), j) && wish == c.getAttributions().get(c.getGraph().getVertex(j))) {
								alreadyJealous = true;
								cost++;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return cost;
	}
	/**
	 * R�cup�rer la liste des pirates jaloux.
	 * 
	 * @param c L'�quipage o� r�cup�rer la liste.
	 * @param treasures La liste des tr�sors.
	 * @return La liste des pirates jaloux.
	 */
	public static ArrayList<ArrayList<Vertex<Pirate>>> getJealous(Crew c, Treasure[] treasures) {
		ArrayList<ArrayList<Vertex<Pirate>>> jealous = new ArrayList<>();
		
		for(Map.Entry<PirateVertex, Treasure> a : c.getAttributions().entrySet()) {
			boolean alreadyJealous = false;
			int attributionRank = Arrays.asList(a.getKey().getLabel().getPreferences()).indexOf(a.getValue());
			
			//Si le pirate n'a pas eu ce qu'il voulait
			if(!(a.getKey().getLabel().getPreferences()[0] == a.getValue())) {
				//Regarder si il n'y a pas un pirate qui aurait re�u un objet qu'il aurait pr�f�r�
				//tout en sachant que le pirate ne peut �tre jaloux qu'une seule fois.
				for(int i = 0; i < attributionRank && !alreadyJealous; i++) {
					Treasure wish = Arrays.asList(treasures).get(Arrays.asList(treasures).indexOf(a.getKey().getLabel().getPreferences()[i]));
					
					for(int j = 0; j < c.getGraph().getOrder() && !alreadyJealous; j++) {
						try {
							//Si les pirates sont en mauvaises relations et qu'il a un objet qu'il aurait pr�f�r�.
							if(c.haveBadRelations(a.getKey().getId(), j) && wish == c.getAttributions().get(c.getGraph().getVertex(j))) {
								alreadyJealous = true;
								ArrayList<Vertex<Pirate>> jealousPirates = new ArrayList<>();
								jealousPirates.add(a.getKey());
								jealousPirates.add(c.getGraph().getVertex(j));
								jealous.add(jealousPirates);
							}
						} catch (Exception e) {	
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return jealous;
	}
	
	/**
	 * Fait une attribution automatique mais na�ve en attribuant
	 * le dernier tr�sor disponible pr�f�r� au pirate. 
	 * L'attribution se faisant r�cursivement il faut appeler la
	 * fonction qu'une seule fois afin de r�cup�rer la liste.
	 * 
	 * @param c L'�quipage sur lequel faire l'attribution na�ve.
	 * @param remainingTreasures Les tr�sors restants � distribuer.
	 * @param i L'index du pirate � qui attribuer le butin.
	 * @return La liste des tr�sors attribu� � chaque pirate.
	 */
	public static LinkedHashMap<PirateVertex, Treasure> naiveAttribution(Crew c, ArrayList<Treasure> remainingTreasures, int i) {
		LinkedHashMap<PirateVertex, Treasure> attributions = new LinkedHashMap<>();
		
		try {
			PirateVertex p = (PirateVertex) c.getGraph().getVertex(i);
			attributions.put(p, getLastTreasureAvailable(p.getLabel().getPreferences(), remainingTreasures));
			
		} catch (VertexNotFoundException e) {
			e.printStackTrace();
		}
		
		if(i < c.getGraph().getOrder() - 1)
			attributions.putAll(naiveAttribution(c, remainingTreasures, ++i));

		return attributions;
	}
	
	/**
	 * R�cup�rer le dernier tr�sor disponible et pr�f�r� du pirate en
	 * question avec ses pr�f�rences.
	 * 
	 * @param preferences Les pr�f�rences du pirate.
	 * @param remainingTreasures Les tr�sors restants � distribuer.
	 * @return Le dernier tr�sor disponible et pr�f�r� du pirate.
	 */
	static Treasure getLastTreasureAvailable(Treasure[] preferences, ArrayList<Treasure> remainingTreasures) {
		for(Treasure preference : preferences)
			if(remainingTreasures.contains(preference)) {
				remainingTreasures.remove(remainingTreasures.indexOf(preference));
				return preference;
			}
		
		return null;
	}
}
