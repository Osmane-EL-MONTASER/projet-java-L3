package project.crew.io;

import java.util.Scanner;

import project.crew.Crew;
import project.crew.Treasure;
import project.crew.graph.PirateVertex;
import project.crew.sharing.NaiveSharing;
import project.graph.exceptions.VertexNotFoundException;

/**
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 */
public class NaiveCrewManagement {
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * 
	 * @param c
	 */
	public static void crewManagementMenu(Crew c) {
		boolean isEnd = false;
		
		do {
			System.out.println("-- Gestion de l'équipage --");
			System.out.println("1) Échanger les trésors.");
			System.out.println("2) Afficher le coût naïf.");
			System.out.println("3) Fin.");
			
			System.out.println(c.getAttributions());
			int rep = sc.nextInt();
			
			switch(rep) {
			case 1:
				swapTreasures(c);
				break;
			case 2:
				System.out.println("Naive cost = " + NaiveSharing.getNaiveCost(c, TerminalGraphMaker.treasures));
				break;
			case 3:
				isEnd = true;
				break;
			default:
				System.out.println("Réponse incorrecte.");
				break;
			}
		} while(!isEnd);
	}
	
	/**
	 * 
	 * @param c
	 */
	private static void swapTreasures(Crew c) {
		int i = 0, j = 0;
		try {
			do {
				System.out.println("-- Échange de trésors --");
				System.out.print("Premier pirate (lettre) --> ");
				
				i = sc.next().charAt(0) - 'A';
				
			} while(!c.getGraph().isVertexExists(c.getGraph().getVertex(i)));
		} catch (VertexNotFoundException e2) {
			e2.printStackTrace();
		}
		
		try {
			do {
				System.out.println("-- Échange de trésors --");
				System.out.print("Deuxième pirate (lettre) --> ");
				
				j = sc.next().charAt(0) - 'A';
			} while(!c.getGraph().isVertexExists(c.getGraph().getVertex(j)));
		} catch (VertexNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			Treasure t1 = c.getAttributions().get(c.getGraph().getVertex(i));
			PirateVertex pv1 = (PirateVertex) c.getGraph().getVertex(i);
			
			Treasure t2 = c.getAttributions().get(c.getGraph().getVertex(j));
			PirateVertex pv2 = (PirateVertex) c.getGraph().getVertex(j);
			
			c.getAttributions().remove(pv1);
			c.getAttributions().remove(pv2);
			
			c.getAttributions().put(pv1, t2);
			c.getAttributions().put(pv2, t1);
		} catch (VertexNotFoundException e) {
			e.printStackTrace();
		}
	}
}
