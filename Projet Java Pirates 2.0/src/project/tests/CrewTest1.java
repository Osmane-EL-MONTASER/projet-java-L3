package project.tests;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.graph.exceptions.EdgeDuplicateException;

/**
 * Crée un équipage de 2 personnes qui ne s'aiment pas
 * mais qui possède chacun des préférences différentes.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class CrewTest1 {
	public static void main(String args[]) {
		Crew c = new Crew();
		
		Treasure[] treasures = {
			new Treasure("Kusanagi"),
			new Treasure("Llywelyn's coronet")};
		
		c.addCrewMember(new Pirate("Edward Teach",
				new Treasure[] {
						treasures[0],
						treasures[1]}));
		
		c.addCrewMember(new Pirate("Olivier Levasseur",
				new Treasure[] {
						treasures[1],
						treasures[0]}));
		try {
			c.addBadRelations(0, 1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (EdgeDuplicateException e) {
			e.printStackTrace();
		}
		
		System.out.println(c.getGraph());
	}
}
