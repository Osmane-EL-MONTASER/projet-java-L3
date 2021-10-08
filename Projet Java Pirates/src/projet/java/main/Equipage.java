package projet.java.main;
import java.util.Scanner;
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
			System.out.println("Le coût naïf est "+calculerCoutNaif());
		}
	}
	
	//TO DO Paul
	public static void versionNaive() {
		
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
	public static void initEquipage() {
		int nombrePirates;
		System.out.println("Indiquer le nombre de pirates dans l'équipage (26 max) : \n");
		nombrePirates=sc.nextInt();
		for(int i=0;i<nombrePirates;i++) {
			pirates.add(new Pirate(nombrePirates));
		}
		
	}
	
	public static void main(String[] args) {
		sc.close();
		
		tresors.add(new Tresor());
		tresors.add(new Tresor());
		tresors.add(new Tresor());
		
		pirates.add(new Pirate(3));
		pirates.add(new Pirate(3));
		pirates.add(new Pirate(3));
		
		pirates.get(0).setPreference(0, tresors.get(0));
		pirates.get(0).setPreference(1, tresors.get(2));
		pirates.get(0).setPreference(2, tresors.get(1));
		pirates.get(1).setPreference(0, tresors.get(0));
		pirates.get(1).setPreference(1, tresors.get(2));
		pirates.get(1).setPreference(2, tresors.get(1));
		pirates.get(2).setPreference(0, tresors.get(0));
		pirates.get(2).setPreference(1, tresors.get(2));
		pirates.get(2).setPreference(2, tresors.get(1));
		
		pirates.get(0).setRelation(0, true);
		pirates.get(0).setRelation(1, true);
		pirates.get(0).setRelation(2, true);
		pirates.get(1).setRelation(0, true);
		pirates.get(1).setRelation(1, false);
		pirates.get(1).setRelation(2, true);
		pirates.get(2).setRelation(0, false);
		pirates.get(2).setRelation(1, true);
		pirates.get(2).setRelation(2, false);
		
		attributionNaive();
		afficherCout(true);
	}
}
