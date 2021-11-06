package project.tests;

import java.util.ArrayList;
import java.util.Arrays;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.crew.sharing.NaiveSharing;
import project.graph.exceptions.EdgeDuplicateException;

/**
 * Crée un équipage de 4 personnes représentant la situation
 * n°1 du sujet de Partage de butin.
 * 
 * La solution naïve est alors appliquée sur cet équipage.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class CrewTest2 {
	public static void main(String args[]) {
		Crew c = new Crew();
		
		Treasure[] treasures = {
			new Treasure("o1"),
			new Treasure("o2"),
			new Treasure("o3"),
			new Treasure("o4")};
		
		c.addCrewMember(new Pirate("Edward Teach",
				new Treasure[] {
						treasures[0],
						treasures[1],
						treasures[2],
						treasures[3]}));
		
		c.addCrewMember(new Pirate("Bartholomew Roberts",
				new Treasure[] {
						treasures[0],
						treasures[2],
						treasures[1],
						treasures[3]}));
		
		c.addCrewMember(new Pirate("Olivier Levasseur",
				new Treasure[] {
						treasures[2],
						treasures[1],
						treasures[0],
						treasures[3]}));
		
		c.addCrewMember(new Pirate("François l'Olonnais",
				new Treasure[] {
						treasures[0],
						treasures[3],
						treasures[1],
						treasures[2]}));
		
		try {
			c.addBadRelations(0, 1);
			c.addBadRelations(2, 1);
			c.addBadRelations(3, 1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (EdgeDuplicateException e) {
			e.printStackTrace();
		}
		
		System.out.println(c.getGraph());
		
		c.setAttributions( 
				NaiveSharing.naiveAttribution(c, new ArrayList<>(Arrays.asList(treasures)), 0));
		
		System.out.println("Coût naïf : " + NaiveSharing.getNaiveCost(c, treasures));
		
		System.out.println("Les attribtions sont les suivantes :");
		System.out.println(c.getAttributions());
	}
}
