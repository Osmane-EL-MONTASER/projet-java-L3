package project.crew.io;

import java.util.Scanner;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Classe comportant les fonctions permettant de 
 * cr�er un graphe repr�sentant l'�quipage et
 * ses relations et les tr�sors.
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
	 * le nombre maximum de pirates �tant de 26 pour la
	 * premi�re version.
	 * Elle initialise tous les sommets du graphe.
	 */
	public static void setPirates() {
		int temp;
		c = new Crew();
		
		do {
			System.out.println("Veuillez saisir le nombre de pirates de votre �quipage : n c [1-26]");
			temp = sc.nextInt();
		} while(temp <= 0 || temp > 26);
		
		pirateNumber = temp;
		System.out.println("Vous avez " + pirateNumber + " pirates dans votre �quipage.");
		
		for (int i = 0; i < pirateNumber; i++)
			c.addCrewMember(new Pirate(Character.toString('A' + i), null));
		
		treasures = new Treasure[pirateNumber];
		for(int i = 0; i < pirateNumber; i++)
			treasures[i] = new Treasure("o" + (i + 1));
	}
	
	/**
	 * Fonction permettant de construire le graphe d'�quipage
	 * avec ses relations ainsi que les pr�f�rences de chaque
	 * pirate.
	 * 
	 * @return Un �quipage avec ses pirates, relations, et
	 * pr�f�rences.
	 */
	public static Crew graphMakerMenu() {
		boolean isEnd = false;
		int rep = 0;
		
		do {
			System.out.println("1) Ajouter une relation.");
			System.out.println("2) ajouter des pr�f�rences.");
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
				//Ajouter de la lisibilit�
				for (int i = 0; i < 25; ++i) System.out.println();
				break;
			case 2:
				addPreference();
				//Ajouter de la lisibilit�
				for (int i = 0; i < 25; ++i) System.out.println();
				break;
			case 3:
				isEnd = true;
				for(int i = 0; i < pirateNumber && isEnd; i++) {
					try {
						if(c.getGraph().getVertex(i).getLabel().getPreferences() == null) {
							isEnd = false;
							System.out.println("Les pr�f�rences du pirate " + Character.toString('A' + i) + " n'ont pas �t� affect�s. Impossible de finir.");
						}
					} catch (VertexNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				System.out.println("R�ponse incorrecte.");
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
			System.out.print("Deuxi�me pirate (lettre) --> ");
			
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
		System.out.println("-- Ajout de pr�f�rences --");
		System.out.println("La pr�f�rence : LETTRE indexTresor1 indexTesorN...");
		
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
	 * Permettre � l'utilisateur d'avoir le temps de voir ce
	 * qui vient de faire sur le graphe en mettant une pause.
	 */
	private static void pressAnyKeyToContinue() { 
		System.out.println("Presser la touche Entr�e pour retourner au menu...");
		try {
			System.in.read();
		}  
		catch(Exception e)
		{}  
	}
}
