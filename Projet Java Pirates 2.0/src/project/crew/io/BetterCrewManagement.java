package project.crew.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.crew.graph.PirateVertex;
import project.crew.sharing.BetterSharing;
import project.crew.sharing.NaiveSharing;
import project.graph.Vertex;
import project.graph.exceptions.InvalidCrewFileFormatException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Meilleur classe qui permet de g�rer un �quipage
 * depuis la console.
 * Elle permet d'�changer des tr�sors entre pirates,
 * de calculer le co�t d'une solution courante et
 * aussi d'apr�s notre propre algorithme de proposer
 * une solution optimale.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @since 3.0
 * @version 1.0
 */
public class BetterCrewManagement {
	/**
	 * Attribut statique permettant d'acc�der � un seul
	 * Scanner dans toutes les fonctions de la classe.
	 */
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Fonction qui effectue une r�solution automatique
	 * sur l'�quipage et qui affiche l'attribution r�alis�
	 * ainsi que le co�t de cette solution.
	 * 
	 * @param c L'�quipage sur lequel r�aliser la r�solution
	 * automatique.
	 */
	public static void automaticResolution(Crew c) {
		//Le deuxi�me param�tre peut sembler �trange mais il sagit d'une ancienne fonction
		//qui fonctionne et qui utilisait une ancienne version de l'objet Crew.
		BetterSharing.doShuffleSharing(c);
		double solution = NaiveSharing.getAttributionCost(c, c.getTreasures());
		System.out.println("La solution automatique a pour co�t : " + solution + " !");
	}
	
	/**
	 * R�cup�rer les attributions restantes sur l'�quipage.
	 * 
	 * @param attributions Les attributions actuelles.
	 * @param vertices Les pirates auxquels attribuer les
	 * tr�sors.
	 * @return La liste des sommets de pirates qui n'ont rien 
	 * re�u.
	 */
	public static ArrayList<Vertex<Pirate>> getRemainingAttributions(LinkedHashMap<PirateVertex, Treasure> attributions,
			ArrayList<Vertex<Pirate>> vertices) {
		ArrayList<Vertex<Pirate>> remaining = new ArrayList<>();
		
		for(Vertex<Pirate> v : vertices)
			if(!attributions.containsKey(v))
				remaining.add(v);
		
		return remaining;
	}
	
	/**
	 * Faire une r�solution manuelle.
	 * 
	 * @param c L'�quipage sur lequel faire la r�solution
	 * manuelle.
	 */
	public static void manualResolution(Crew c) {
		System.out.println("Les attributions actuelles :");
		System.out.println(c.getAttributions());
		
		boolean exit = false;
		String answer = new String();
		do { 
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1) Attribuer un tr�sor � un pirate.");
			System.out.println("2) �changer des tr�sors.");
			System.out.println("3) Afficher les attributions.");
			System.out.println("4) Afficher le co�t actuel.");
			System.out.println("5) Retour.");
			
			answer = sc.nextLine();

			switch(answer) {
			case "1":
				treasureAttribution(c);
				break;
			case "2":
				swapTreasures(c);
				break;
			case "3":
				System.out.println(c.getAttributions());
				break;
			case "4":
				if(c.getAttributions().size() != c.getGraph().getOrder())
					System.out.println("Calcul du coup impossible car il manque une attribution � :\n" 
				+ getRemainingAttributions(c.getAttributions(), c.getGraph().getVertices()));
				else
					System.out.println("Co�t actuel : " + NaiveSharing.getAttributionCost(c, c.getTreasures()));
				break;
			case "5":
				exit = true;
				break;
			default:
				System.out.println("Vous devez taper un nombre entre 1 et 5 ! "
						+ "Vous avez tap� = " + answer + " !");
				break;
			}
		} while(!exit);
		
		
	}
	
	/**
	 * Fonction qui permet de sauvegarder la solution
	 * actuelle dans un fichier texte.
	 * 
	 * @param c L'�quipage courant.
	 */
	public static void saveSolution(Crew c) {
		boolean isSaved = false;
		if(c.getAttributions().size() == c.getGraph().getOrder()) {
			do {
				System.out.print("Tapez le chemin du fichier -> ");
				String path = sc.nextLine();
				try {
					SolutionParser.saveSolution(c, path);
					isSaved = true;
				} catch (FileNotFoundException e) {
					System.out.println("Le chemin sp�cifi� est introuvable, recommencez.");
				}
			} while(!isSaved);
		} else {
			System.out.println("Impossible de sauvegarder.");
			System.out.println("Il manque des attributions � votre solution : \n" 
					+ getRemainingAttributions(c.getAttributions(), c.getGraph().getVertices()));
		}
	}

	/**
	 * Fonction qui affiche et qui g�re les interactions
	 * avec le menu demand� dans le cahier des charges.
	 * Il y a le choix entre la r�solution automatique,
	 * manuelle, la sauvegarde d'un �quipage et quitter
	 * le programme.
	 * 
	 * @param filename Le nom du fichier o� se trouve
	 * l'�quipage � charger dans l'application.
	 */
	public static void bCMMenu(String filename) {
		Crew loadedCrew;
		if((loadedCrew = loadCrew(filename)) == null)
			return;
		System.out.println(loadedCrew);
		
		boolean exit = false;
		String answer = new String();
		do {
			printBCMMenu();
			answer = sc.nextLine();

			switch(answer) {
			case "1":
				automaticResolution(loadedCrew);
				break;
			case "2":
				manualResolution(loadedCrew);
				break;
			case "3":
				saveSolution(loadedCrew);
				break;
			case "4":
				System.out.println("Fermture de l'application. Au revoir !");
				exit = true;
				break;
			default:
				System.out.println("Vous devez taper un nombre entre 1 et 4 ! "
						+ "Vous avez tap� = " + answer + " !");
				break;
			}
		} while(!exit);
	}
	
	/**
	 * Faire l'attribution d'un tr�sor. Si un tr�sor est d�j�
	 * attribuer, supprime l'attribution existante.
	 * 
	 * @param c L'�quipage sur lequel r�aliser l'attribution.
	 */
	private static void treasureAttribution(Crew c) {
		Vertex<Pirate> v = null;
		Treasure t = null;
		boolean isExists = false;
		
		do {
			System.out.println("-- Attribution d'un tr�sor --");
			System.out.print("Pirate (Nom) --> ");
			String answer = sc.nextLine();
			v = getVertexFromName(answer, c);

			if(v == null)
				System.out.println("Le pirate " + answer + " n'existe pas !");
			else
				isExists = true;
		} while(!isExists);
		
		isExists = false;
		
		do {
			System.out.println("-- Attribution d'un tr�sor --");
			System.out.print("Tr�sor (Nom) --> ");
			String answer = sc.nextLine();
			t = getTreasureFromName(answer, new ArrayList<Treasure>(Arrays.asList(c.getTreasures())));

			if(t == null)
				System.out.println("Le tr�sor " + answer + " n'existe pas !");
			else
				isExists = true;
		} while(!isExists);
		
		isExists = false;
		
		if(c.getAttributions().containsValue(t)) {
			for(Entry<PirateVertex, Treasure> e : c.getAttributions().entrySet())
				if(e.getValue() == t) {
					c.getAttributions().remove(e.getKey());
					break;
				}
		}
		c.getAttributions().put((PirateVertex) v, t);
	}
	
	/**
	 * �changer les tr�sors entre les 2 pirates
	 * saisis dans la console.
	 * 
	 * @param c L'�quipage.
	 */
	private static void swapTreasures(Crew c) {
		int i = 0, j = 0;
		boolean isExists = false;
		
		do {
			System.out.println("-- �change de tr�sors --");
			System.out.print("Premier pirate (Nom) --> ");
			String answer = sc.nextLine();
			i = CrewParser.getVertexIndexFromName(answer, c);

			if(i == -1)
				System.out.println("Le pirate " + answer + " n'existe pas !");
			else
				isExists = true;
		} while(!isExists);
		
		isExists = false;
		
		do {
			System.out.println("-- �change de tr�sors --");
			System.out.print("Deuxi�me pirate (Nom) --> ");
			String answer = sc.nextLine();
			j = CrewParser.getVertexIndexFromName(answer, c);

			if(j == -1)
				System.out.println("Le pirate " + answer + " n'existe pas !");
			else
				isExists = true;
		} while(!isExists);
		
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
	
	/**
	 * Cette fonction permet de r�cup�rer le vertex
	 * correspondant au nom de pirate pass� en param�tre.
	 * 
	 * @param name Le nom du pirate.
	 * @param c L'�quipage qui contient un objet Graph.
	 * @return Le sommet du pirate en question.
	 */
	private static Vertex<Pirate> getVertexFromName(String name, Crew c) {
		for(Vertex<Pirate> pv : c.getGraph().getVertices()) {
			if(pv.getLabel().getName().equals(name))
				return pv;
		}
		
		return null;
	}
	
	/**
	 * Cette fonction permet de retrouver le tr�sor
	 * dans la liste donn�e en param�tre � partir de son
	 * nom.
	 * 
	 * @param name Le nom du tr�sor � chercher.
	 * @param treasures La liste d'objets de type Tr�sor.
	 * @return Le tr�sor cherch�, sinon null.
	 */
	private static Treasure getTreasureFromName(String name, ArrayList<Treasure> treasures) {
		for(Treasure t : treasures) {
			if(t.toString().equals(name))
				return t;
		}
		return null;
	}
	
	/**
	 * Fonction utilitaire qui permet d'afficher le menu
	 * demand� dans le cahier des charges.
	 */
	private static void printBCMMenu() {
		System.out.println("---------------------------------------------");
		System.out.println("1) R�solution automatique.");
		System.out.println("2) R�solution manuelle.");
		System.out.println("3) Sauvegarder la solution actuelle.");
		System.out.println("4) Quitter l'application.");
		System.out.println("---------------------------------------------");
		System.out.println("Tapez un nombre entre 1 et 4 pour continuer :");
	}
	
	/**
	 * Fonction utilitaire qui permet de g�rer le chargement d'un
	 * �quipagedans la fonction main.
	 * 
	 * @param filename
	 * @return L'�quipage charg� si il n'y a pas eu d'erreurs, null
	 * sinon.
	 */
	private static Crew loadCrew(String filename) {
		Crew loadedCrew = null;
		boolean error = true;
		do {
			try {
				loadedCrew = CrewParser.parseFromTxtFile(filename);
				error = false;
			} catch(InvalidCrewFileFormatException e) {
				System.out.println("La syntaxe du fichier pass� en param�tre est incorrecte."
						+ " Veuillez relancer le programme avec un fichier valide.");
				
				System.out.println("Veuillez renseigner le chemin vers le fichier �quipage � charger : ");
				filename = sc.nextLine();

			} catch(FileNotFoundException e) {
				System.out.println("Le fichier " + filename + " est introuvable. V�rifiez "
						+ "le chemin et recommencez.");
				
				System.out.println("Veuillez renseigner le chemin vers le fichier �quipage � charger : ");
				filename = sc.nextLine();
			}
		} while(error);
		System.out.println("Votre �quipage se trouvant � " + filename + " a �t� charg� avec succ�s !");
		return loadedCrew;
	}
}
