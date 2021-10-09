package projet.java.main;
import java.util.Scanner;

import javax.management.relation.RelationService;

import java.util.ArrayList;

public class Equipage {
	
	public static ArrayList<Pirate> pirates = new ArrayList<>();
	public static ArrayList<Tresor> tresors = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);
	
	public static void echanger(Pirate p1, Pirate p2) {
		Tresor objP1,objP2;
		objP1=p1.getObjetRecu();
		objP2=p2.getObjetRecu();
		p1.setObjetRecu(objP2);
		p2.setObjetRecu(objP1);
	}
	
	public static int calculerCoutNaif() {
		int cout = 0;
		
		//Pour chaque pirate on ajoute le cout correpondant 
		//au cout total.
		for (Pirate p : pirates) {
			//Le pirate est jaloux donc on incrémente
			if (p.estJaloux(pirates))
				cout++;
		}
		
		return cout;
	}
	
	public static void afficherCout(boolean isNaive) {
		if(isNaive) {
			System.out.println("Le coût naïf est "+calculerCoutNaif()+"\n");
		}
	}
	
	//TO DO Paul
	public static void versionNaive() {
		int choix;
		int p;
		int n;
		initEquipageNaif();
		attributionNaive();
		do {
			System.out.println("1 : Indiquer les relations entre les pirates\n");
			System.out.println("2 : Indiquer les préférences des pirates sur les trésors\n");
			System.out.println("3 : Fin\n");
			choix=sc.nextInt();
			switch(choix) {
			case 1:
				System.out.println("les noms des pirates sont : ");
				for(int i=0;i<pirates.size();i++) {
					System.out.print("pirate "+(i+1)+": "+pirates.get(i).getNom()+"  ");
				}
				System.out.println("\n\nPour quel pirate voulez vous changer les relations : ");
				p=sc.nextInt()-1;
				System.out.println("Qui le pirate "+pirates.get(p).getNom()+" n'aime t-il pas? (0 s'il s'entend avec tout le monde)");
				do {
					n=sc.nextInt()-1;
					if(n!=-1) {
						pirates.get(n).setRelation(n, true);
					}
					System.out.print("Le pirate "+pirates.get(p).getNom()+" n'aime pas");
					for(int i=0;i<pirates.size();i++) {
						if(pirates.get(i).getRelation(i)) {
							System.out.print(" "+pirates.get(i).getNom());
						}
					}
					if(n!=-1) {
						System.out.println("\nQui d'autre? (0 si personne d'autre)");
					}else {
						System.out.println("\n");
					}
				}while(n!=-1);
				break;
			case 2:
				for(int i=0;i<pirates.size();i++) {
					System.out.println("Pirate "+pirates.get(i).getNom()+" : "+pirates.get(i).getPreferences());
				}
				System.out.println("\n\nPour quel pirate voulez vous changer les préférences : ");
				p=sc.nextInt()-1;
				for(int i=0;i<pirates.size();i++) {
					System.out.print("Donnez la préférence "+(i+1)+" du pirate "+pirates.get(p).getNom()+" : ");
					n=sc.nextInt();
					pirates.get(p).setPreference(i, new Tresor());
					}
				for(int i=0;i<pirates.size();i++) {
					System.out.println("Pirate "+pirates.get(i).getNom()+" : "+pirates.get(i).getPreferences());
				}
				break;
				}
			
		}while(choix!=3);
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
	
	//TO DO Paul
	public static void initEquipageNaif() {
		int nombrePirates;
		boolean init=true;
		do {
			System.out.println("Indiquer le nombre de pirates dans l'équipage (26 max) : \n");
			nombrePirates=sc.nextInt();
			if(nombrePirates>26 || nombrePirates<0) {
				System.out.println("Nombre de pirates incohérent.\nRéessayer\n");
			}else {
				for(int i=0;i<nombrePirates;i++) {
					pirates.add(new Pirate(nombrePirates));
				}
				init=false;
			}
			
		}while(init);
		System.out.println("Votre équipage comporte "+nombrePirates+" pirates.\n");
		System.out.println("leurs noms sont les suivants : ");
		for(int i=0;i<nombrePirates;i++) {
			System.out.print(pirates.get(i).getNom()+" ");
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		versionNaive();
		sc.close();
		
		/*tresors.add(new Tresor());
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
		
		pirates.get(0).setRelation(0, false);
		pirates.get(0).setRelation(1, true);
		pirates.get(0).setRelation(2, false);
		pirates.get(0).setRelation(3, false);
		
		pirates.get(1).setRelation(0, true);
		pirates.get(1).setRelation(1, false);
		pirates.get(1).setRelation(2, true);
		pirates.get(1).setRelation(3, true);
		
		pirates.get(2).setRelation(0, false);
		pirates.get(2).setRelation(1, true);
		pirates.get(2).setRelation(2, false);
		pirates.get(2).setRelation(3, false);
		
		pirates.get(3).setRelation(0, false);
		pirates.get(3).setRelation(1, true);
		pirates.get(3).setRelation(2, false);
		pirates.get(3).setRelation(3, false);
		
		attributionNaive();
		afficherCout(true);*/
	}
}
