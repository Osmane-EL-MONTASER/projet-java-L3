package projet.java.main;
import java.util.Scanner;

import projet.java.graphe.GraphePirate;

import java.util.ArrayList;

public abstract class Equipage {
	
	public static ArrayList<Pirate> pirates = new ArrayList<>();
	public static ArrayList<Tresor> tresors = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);
	
	public static GraphePirate g;
	
	/**
	 * Permet d'�changer les objets re�us de 2 pirates donn�s
	 * 
	 * @param p1 pirate 1
	 * @param p2 pirate 2
	 */
	public static void echanger(Pirate p1, Pirate p2) {
		Tresor objP1,objP2;
		objP1=p1.getObjetRecu();
		objP2=p2.getObjetRecu();
		p1.setObjetRecu(objP2);
		p2.setObjetRecu(objP1);
	}
	
	/**
	 * Permet de calculer le co�t de la solution actuelle
	 * 
	 * @return le co�t de la solution actuelle
	 */
	public static int calculerCoutNaif() {
		int cout = 0;
		
		//Pour chaque pirate on ajoute le cout correpondant 
		//au cout total.
		for (Pirate p : pirates) {
			//Le pirate est jaloux donc on incr�mente
			if (p.estJaloux(pirates))
				cout++;
		}
		
		return cout;
	}
	
	/**
	 * Affiche le co�t de la solution actuelle
	 * 
	 * @param isNaive booleen qui vaut true si la version est na�ve
	 */
	public static void afficherCout(boolean isNaive) {
		if(isNaive) {
			System.out.println("Le co�t na�f est "+calculerCoutNaif()+"\n");
		}
	}
	
	/**
	 * Affiche les pr�f�rences des objets des pirates
	 */
	public static void afficherPreferences() {
		for(int i=0;i<pirates.size();i++) {
			System.out.println("Pirate "+pirates.get(i).getNom()+": "+pirates.get(i).getPreferences());
		}
	}
	
	/**
	 * Affiche les objets que les pirates ont re�u
	 */
	public static void afficherObjetsRecus() {
		for(int i=0;i<pirates.size();i++) {
			System.out.println(pirates.get(i).getNom()+":"+pirates.get(i).getObjetRecu());
		}
	}
	
	/**
	 * Affiche un menu pour pouvoir indiquer les relations entre les pirates ainsi que les pr�f�rences des pirates sur les tr�sors
	 */
	public static void menuRelationsPreferences() {
		int choix,n,p1,p2;
		String p;
		do {
			System.out.println("1 : Indiquer les relations entre les pirates\n");
			System.out.println("2 : Indiquer les pr�f�rences des pirates sur les tr�sors\n");
			System.out.println("3 : Fin\n");
			choix=sc.nextInt();
			switch(choix) {
			case 1:
				System.out.println("les noms des pirates sont : ");
				for(int i=0;i<pirates.size();i++) {
					System.out.print("pirate "+(i+1)+": "+pirates.get(i).getNom()+"  ");
				}
				System.out.println("\n\nPour quel pirate voulez vous changer les relations :\n(indiquez un nom)");
				p=sc.next();
				p1=p.charAt(0)-'A';
				System.out.println("Qui le pirate "+pirates.get(p1).getNom()+" n'aime t-il pas? (0 s'il s'entend avec tout le monde)");
				do {
					p=sc.next();
					if(p.compareTo("0")==0) {
						break;
						
					}else {
						p2=p.charAt(0)-'A';
						pirates.get(p2).setRelation(p2, true);
					}
					System.out.print("Le pirate "+pirates.get(p1).getNom()+" n'aime pas");
					for(int i=0;i<pirates.size();i++) {
						if(pirates.get(i).getRelation(i)) {
							System.out.print(" "+pirates.get(i).getNom());
						}
					}
					System.out.println("\nQui d'autre? (0 si personne d'autre)");
				}while(p.compareTo("0")!=0);
				break;
			case 2:
				afficherPreferences();
				do {
					System.out.println("\n\nPour quel pirate voulez vous changer les pr�f�rences : ");
					System.out.println("0 : Quitter");
					p=sc.next();
					if(p.compareTo("0")==0)
						break;
					p1=p.charAt(0)-'A';
					for(int i=0;i<pirates.size();i++) {
						System.out.print("Donnez la pr�f�rence "+(i+1)+" du pirate "+pirates.get(p1).getNom()+" : ");
						n=sc.nextInt()-1;
						pirates.get(p1).setPreference(i, tresors.get(n));
						}
					afficherPreferences();
				}while(true);
				}
			
		}while(choix!=3);
	}
	
	/**
	 * Affiche un menu pour pouvoir �changer les objets entre les pirates et pour afficher le co�t de la solution actuelle
	 */
	public static void menuEchangerEtCout() {
		Pirate p1,p2;
		int p;
		int choix;
		String ps;
		do {
			System.out.println("1 : Echanger des objets entre les pirates\n");
			System.out.println("2 : Afficher le co�t de la solution actuelle\n");
			System.out.println("3 : Fin\n");
			choix=sc.nextInt();
			switch(choix) {
			case 1:
				System.out.println("Les pr�ferences des pirates sont :");
				afficherPreferences();
				System.out.println("\nLes objets poss�d�s des pirates :");
				afficherObjetsRecus();
				System.out.println("\nPirates qui doivent �changer leur tr�sor :");
				System.out.println("Pirate 1 : ");
				ps=sc.next();
				p=ps.charAt(0)-'A';
				p1=pirates.get(p);
				System.out.println("Pirate 2 : ");
				ps=sc.next();
				p=ps.charAt(0)-'A';
				p2=pirates.get(p);
				echanger(p1,p2);
				do {
					System.out.println("\nLes pr�ferences des pirates sont :");
					afficherPreferences();
					System.out.println("\nLes objets poss�d�s des pirates :");
					afficherObjetsRecus();
					System.out.println("Quitter? 0:OUI 1:NON");
					choix=sc.nextInt();
					if(choix==0) {
						break;
					}
					System.out.println("\nPirates qui doivent �changer leur tr�sor :");
					System.out.println("Pirate 1 : ");
					ps=sc.next();
					p=ps.charAt(0)-'A';
					p1=pirates.get(p);
					System.out.println("Pirate 2 : ");
					ps=sc.next();
					p=ps.charAt(0)-'A';
					p2=pirates.get(p);
					echanger(p1,p2);
				}while(true);
				break;
			case 2:
				System.out.println("\nLes objets poss�d�s des pirates :");
				afficherObjetsRecus();
				afficherCout(true);
				break;
			}
		}while(choix!=3);
	}
	
	/**
	 * Appel les m�thodes pour la version na�ve
	 */
	public static void versionNaive() {
		initEquipageNaif();
		menuRelationsPreferences();
		attributionNaive();
		menuEchangerEtCout();
	}
	
	/**
	 * Appel les m�thodes pour la version automatique
	 */
	public static void versionSotomatique() {
		System.out.println("pas encore programm�\n");
	}
	
	public static void attributionNaive() {
		ArrayList<Tresor> tresorsDispos = new ArrayList<Tresor>(tresors);
		
		//Pour chaque pirate on va attribuer
		//son dernier tresor prefere disponible
		for (Pirate p : pirates) {
			boolean isTresorRecu = false;
			char a = 'A';
			for (int i = 0; i < p.getPreferences().size() && !isTresorRecu; i++) {
				//Le tresor est disponible
				if (tresorsDispos.contains(p.getPreferences().get(i))) {
					p.setObjetRecu(p.getPreferences().get(i));
					tresorsDispos.remove(p.getPreferences().get(i));
					isTresorRecu = true;
				}
			}
		}
	}
	
	/**
	 * Initialise l'�quipage d'un nombre de pirates donn�s.
	 * Ajoute un pirates dans la ArrayList pirates et ajoute un tr�sor pour la ArrayList tresors
	 */
	public static void initEquipageNaif() {
		int nombrePirates;
		boolean init=true;
		do {
			System.out.println("Indiquer le nombre de pirates dans l'�quipage (26 max) :");
			nombrePirates=sc.nextInt();
			if(nombrePirates>26 || nombrePirates<0) {
				System.out.println("Nombre de pirates incoh�rent.\nR�essayer\n");
			}else {
				for(int i=0;i<nombrePirates;i++) {
					pirates.add(new Pirate(nombrePirates));
					tresors.add(new Tresor());
				}
				init=false;
			}
		}while(init);
		System.out.println("Votre �quipage comporte "+nombrePirates+" pirates.\n");
		System.out.println("leurs noms sont les suivants : ");
		for(int i=0;i<nombrePirates;i++) {
			System.out.print(pirates.get(i).getNom()+" ");
		}
		System.out.println("\n");
	}
	
	
	public static void main(String[] args) {
		/*int choix;
		System.out.println("1 : Version Naive\n2 : version Sotomatique");
		choix=sc.nextInt();
		if(choix==1) {
			versionNaive();
		}else if(choix==2) {
			versionSotomatique();
		}else {
			System.out.println("Option impossible\n");
		}
		sc.close();
		*/
		tresors.add(new Tresor());
		tresors.add(new Tresor());
		tresors.add(new Tresor());
		tresors.add(new Tresor());
		
		pirates.add(new Pirate(4));
		pirates.add(new Pirate(4));
		pirates.add(new Pirate(4));
		pirates.add(new Pirate(4));
		
		pirates.get(0).setPreference(0, tresors.get(0));
		pirates.get(0).setPreference(1, tresors.get(1));
		pirates.get(0).setPreference(2, tresors.get(2));
		pirates.get(0).setPreference(3, tresors.get(3));
		
		pirates.get(1).setPreference(0, tresors.get(0));
		pirates.get(1).setPreference(1, tresors.get(2));
		pirates.get(1).setPreference(2, tresors.get(1));
		pirates.get(1).setPreference(3, tresors.get(3));
		
		pirates.get(2).setPreference(0, tresors.get(2));
		pirates.get(2).setPreference(1, tresors.get(1));
		pirates.get(2).setPreference(2, tresors.get(0));
		pirates.get(2).setPreference(3, tresors.get(3));
		
		pirates.get(3).setPreference(0, tresors.get(0));
		pirates.get(3).setPreference(1, tresors.get(3));
		pirates.get(3).setPreference(2, tresors.get(1));
		pirates.get(3).setPreference(3, tresors.get(2));
		
		g = new GraphePirate(4, pirates);
		
		g.changeRelation(pirates.get(0), pirates.get(1), true);
		g.changeRelation(pirates.get(0), pirates.get(2), true);
		g.changeRelation(pirates.get(0), pirates.get(3), true);
		
		g.changeRelation(pirates.get(1), pirates.get(0), true);
		
		g.changeRelation(pirates.get(2), pirates.get(0), true);
		
		g.changeRelation(pirates.get(3), pirates.get(0), true);
		
		
		attributionNaive();
		afficherCout(true);
		
		System.out.println(g);
	}
}
