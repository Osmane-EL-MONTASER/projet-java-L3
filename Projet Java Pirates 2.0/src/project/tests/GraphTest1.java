package project.tests;

import project.crew.Pirate;
import project.crew.Treasure;
import project.crew.graph.PirateGraph;
import project.crew.graph.PirateVertex;

/**
 * Une classe exemple qui utilise les classes de notre
 * package project.graph pour en faire un graphe de
 * pirates.
 * 
 * Cette exemple crée un graphe de 3 pirates en sachant
 * que les pirates 0 et 1 ne s'aiment pas et que les
 * pirates 0 et 2 ne s'aiment pas.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class GraphTest1 {
	public static void main(String args[]) {
		PirateGraph pg = new PirateGraph();
		
		pg.addVertex(new PirateVertex(new Pirate("Pirate 0", new Treasure[] {})));
		pg.addVertex(new PirateVertex(new Pirate("Pirate 1", new Treasure[] {})));
		pg.addVertex(new PirateVertex(new Pirate("Pirate 2", new Treasure[] {})));
		
		try {
			pg.addEdge(0, 1);
			pg.addEdge(0, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(pg);
	}
}
