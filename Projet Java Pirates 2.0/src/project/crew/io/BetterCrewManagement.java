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
 * Meilleur classe qui permet de gérer un équipage
 * depuis la console.
 * Elle permet d'échanger des trésors entre pirates,
 * de calculer le coût d'une solution courante et
 * aussi d'après notre propre algorithme de proposer
 * une solution optimale.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @since 3.0
 * @version 1.0
 */
public class BetterCrewManagement {
	/**
	 * Attribut statique permettant d'accéder à un seul
	 * Scanner dans toutes les fonctions de la classe.
	 */
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Fonction qui effectue une résolution automatique
	 * sur l'équipage et qui affiche l'attribution réalisé
	 * ainsi que le coût de cette solution.
	 * 
	 * @param c L'équipage sur lequel réaliser la résolution
	 * automatique.
	 */
	public static void automaticResolution(Crew c) {
		//Le deuxième paramètre peut sembler étrange mais il sagit d'une ancienne fonction
		//qui fonctionne et qui utilisait une ancienne version de l'objet Crew.
		BetterSharing.doShuffleSharing(c);
		double solution = NaiveSharing.getAttributionCost(c, c.getTreasures());
		System.out.println("La solution automatique a pour coût : " + solution + " !");
	}
	
	/**
	 * Récupérer les attributions restantes sur l'équipage.
	 * 
	 * @param attributions Les attributions actuelles.
	 * @param vertices Les pirates auxquels attribuer les
	 * trésors.
	 * @return La liste des sommets de pirates qui n'ont rien 
	 * reçu.
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
	 * Faire une résolution manuelle.
	 * 
	 * @param c L'équipage sur lequel faire la résolution
	 * manuelle.
	 */
	public static void manualResolution(Crew c) {
		System.out.println("Les attributions actuelles :");
		System.out.println(c.getAttributions());
		
		boolean exit = false;
		String answer = new String();
		do { 
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1) Attribuer un trésor à un pirate.");
			System.out.println("2) Échanger des trésors.");
			System.out.println("3) Afficher les attributions.");
			System.out.println("4) Afficher le coût actuel.");
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
					System.out.println("Calcul du coup impossible car il manque une attribution à :\n" 
				+ getRemainingAttributions(c.getAttributions(), c.getGraph().getVertices()));
				else
					System.out.println("Coût actuel : " + NaiveSharing.getAttributionCost(c, c.getTreasures()));
				break;
			case "5":
				exit = true;
				break;
			default:
				System.out.println("Vous devez taper un nombre entre 1 et 5 ! "
						+ "Vous avez tapé = " + answer + " !");
				break;
			}
		} while(!exit);
		
		
	}
	
	/**
	 * Fonction qui permet de sauvegarder la solution
	 * actuelle dans un fichier texte.
	 * 
	 * @param c L'équipage courant.
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
					System.out.println("Le chemin spécifié est introuvable, recommencez.");
				}
			} while(!isSaved);
		} else {
			System.out.println("Impossible de sauvegarder.");
			System.out.println("Il manque des attributions à votre solution : \n" 
					+ getRemainingAttributions(c.getAttributions(), c.getGraph().getVertices()));
		}
	}

	/**
	 * Fonction qui affiche et qui gère les interactions
	 * avec le menu demandé dans le cahier des charges.
	 * Il y a le choix entre la résolution automatique,
	 * manuelle, la sauvegarde d'un équipage et quitter
	 * le programme.
	 * 
	 * @param filename Le nom du fichier où se trouve
	 * l'équipage à charger dans l'application.
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
						+ "Vous avez tapé = " + answer + " !");
				break;
			}
		} while(!exit);
	}
	
	/**
	 * Faire l'attribution d'un trésor. Si un trésor est déjà
	 * attribuer, supprime l'attribution existante.
	 * 
	 * @param c L'équipage sur lequel réaliser l'attribution.
	 */
	private static void treasureAttribution(Crew c) {
		Vertex<Pirate> v = null;
		Treasure t = null;
		boolean isExists = false;
		
		do {
			System.out.println("-- Attribution d'un trésor --");
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
			System.out.println("-- Attribution d'un trésor --");
			System.out.print("Trésor (Nom) --> ");
			String answer = sc.nextLine();
			t = getTreasureFromName(answer, new ArrayList<Treasure>(Arrays.asList(c.getTreasures())));

			if(t == null)
				System.out.println("Le trésor " + answer + " n'existe pas !");
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
	 * Échanger les trésors entre les 2 pirates
	 * saisis dans la console.
	 * 
	 * @param c L'équipage.
	 */
	private static void swapTreasures(Crew c) {
		int i = 0, j = 0;
		boolean isExists = false;
		
		do {
			System.out.println("-- Échange de trésors --");
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
			System.out.println("-- Échange de trésors --");
			System.out.print("Deuxième pirate (Nom) --> ");
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
	 * Cette fonction permet de récupérer le vertex
	 * correspondant au nom de pirate passé en paramètre.
	 * 
	 * @param name Le nom du pirate.
	 * @param c L'équipage qui contient un objet Graph.
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
	 * Cette fonction permet de retrouver le trésor
	 * dans la liste donnée en paramètre à partir de son
	 * nom.
	 * 
	 * @param name Le nom du trésor à chercher.
	 * @param treasures La liste d'objets de type Trésor.
	 * @return Le trésor cherché, sinon null.
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
	 * demandé dans le cahier des charges.
	 */
	private static void printBCMMenu() {
		System.out.println("---------------------------------------------");
		System.out.println("1) Résolution automatique.");
		System.out.println("2) Résolution manuelle.");
		System.out.println("3) Sauvegarder la solution actuelle.");
		System.out.println("4) Quitter l'application.");
		System.out.println("---------------------------------------------");
		System.out.println("Tapez un nombre entre 1 et 4 pour continuer :");
	}
	
	/**
	 * Fonction utilitaire qui permet de gérer le chargement d'un
	 * équipagedans la fonction main.
	 * 
	 * @param filename
	 * @return L'équipage chargé si il n'y a pas eu d'erreurs, null
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
				System.out.println("La syntaxe du fichier passé en paramètre est incorrecte."
						+ " Veuillez relancer le programme avec un fichier valide.");
				
				System.out.println("Veuillez renseigner le chemin vers le fichier équipage à charger : ");
				filename = sc.nextLine();

			} catch(FileNotFoundException e) {
				System.out.println("Le fichier " + filename + " est introuvable. Vérifiez "
						+ "le chemin et recommencez.");
				
				System.out.println("Veuillez renseigner le chemin vers le fichier équipage à charger : ");
				filename = sc.nextLine();
			}
		} while(error);
		System.out.println("Votre équipage se trouvant à " + filename + " a été chargé avec succès !");
		return loadedCrew;
	}
}
