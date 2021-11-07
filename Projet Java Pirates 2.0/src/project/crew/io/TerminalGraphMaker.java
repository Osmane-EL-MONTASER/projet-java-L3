package project.crew.io;

import java.util.Scanner;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Classe comportant les fonctions permettant de 
 * créer un graphe représentant l'équipage et
 * ses relations et les trésors.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 1.0
 * @since 2.0
 */
public class TerminalGraphMaker {
	public static Scanner sc = new Scanner(System.in);
	private static int pirateNumber;
	private static Crew c;
	public static Treasure[] treasures;
	
	/**
	 * Fonction permettant de fixer le nombre de pirates,
	 * le nombre maximum de pirates étant de 26 pour la
	 * première version.
	 * Elle initialise tous les sommets du graphe.
	 */
	public static void setPirates() {
		int temp;
		c = new Crew();
		
		do {
			System.out.println("Veuillez saisir le nombre de pirates de votre équipage : n c [1-26]");
			temp = sc.nextInt();
		} while(temp <= 0 || temp > 26);
		
		pirateNumber = temp;
		System.out.println("Vous avez " + pirateNumber + " pirates dans votre équipage.");
		
		for (int i = 0; i < pirateNumber; i++)
			c.addCrewMember(new Pirate(Character.toString('A' + i), null));
		
		treasures = new Treasure[pirateNumber];
		for(int i = 0; i < pirateNumber; i++)
			treasures[i] = new Treasure("o" + (i + 1));
	}
	
	/**
	 * Fonction permettant de construire le graphe d'équipage
	 * avec ses relations ainsi que les préférences de chaque
	 * pirate.
	 * 
	 * @return Un équipage avec ses pirates, relations, et
	 * préférences.
	 */
	public static Crew graphMakerMenu() {
		boolean isEnd = false;
		int rep = 0;
		
		do {
			System.out.println("1) Ajouter une relation.");
			System.out.println("2) ajouter des préférences.");
			System.out.println("3) Fin.");
			
			System.out.println(c.getGraph());
			
			boolean pass = false;
			do {
				pass = true;
				try {
					rep = sc.nextInt();
				} catch(Exception e) {
					e.printStackTrace();
					pass = false;
				}
			} while(!pass);
			
			switch(rep) {
			case 1:
				addRelation();
				//Ajouter de la lisibilité
				for (int i = 0; i < 25; ++i) System.out.println();
				break;
			case 2:
				addPreference();
				//Ajouter de la lisibilité
				for (int i = 0; i < 25; ++i) System.out.println();
				break;
			case 3:
				isEnd = true;
				for(int i = 0; i < pirateNumber && isEnd; i++) {
					try {
						if(c.getGraph().getVertex(i).getLabel().getPreferences() == null) {
							isEnd = false;
							System.out.println("Les préférences du pirate " + Character.toString('A' + i) + " n'ont pas été affectés. Impossible de finir.");
						}
					} catch (VertexNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				System.out.println("Réponse incorrecte.");
				break;
			}
		} while(!isEnd);
		
		System.out.println(c.getGraph());
		
		return c;
	}
	
	private static void addRelation() {
		int i, j;
		do {
			System.out.println("-- Ajout d'une relation --");
			System.out.print("Premier pirate (lettre) --> ");
			
			i = sc.next().charAt(0) - 'A';
			
		} while(!c.getGraph().isVertexExists(i));
		
		do {
			System.out.println("-- Ajout d'une relation --");
			System.out.print("Deuxième pirate (lettre) --> ");
			
			j = sc.next().charAt(0) - 'A';
		} while(!c.getGraph().isVertexExists(j));
		
		try {
			c.addBadRelations(i, j);
		} catch (IllegalArgumentException | EdgeDuplicateException e) {
			e.printStackTrace();
		}
		
		pressAnyKeyToContinue();
	}
	
	private static void addPreference() {
		System.out.println("-- Ajout de préférences --");
		System.out.println("La préférence : LETTRE indexTresor1 indexTesorN...");
		
		sc.nextLine();
		String pref = sc.nextLine();
		
		String[] splited = pref.split(" ");
		
		int p = splited[0].charAt(0) - 'A';
		Treasure[] preferences = new Treasure[pirateNumber];
		
		for(int i = 0; i < pirateNumber; i++)
			preferences[i] = treasures[Integer.parseInt(splited[i + 1])];
		
		try {
			c.getGraph().getVertex(p).getLabel().setPreferences(preferences);
		} catch (VertexNotFoundException e) {
			e.printStackTrace();
		}
		
		pressAnyKeyToContinue();
	}
	
	/**
	 * Permettre à l'utilisateur d'avoir le temps de voir ce
	 * qui vient de faire sur le graphe en mettant une pause.
	 */
	private static void pressAnyKeyToContinue() { 
		System.out.println("Presser la touche Entrée pour retourner au menu...");
		try {
			System.in.read();
		}  
		catch(Exception e)
		{}  
	}
}
