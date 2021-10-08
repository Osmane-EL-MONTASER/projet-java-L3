package projet.java.main;
import java.util.Scanner;
import java.util.ArrayList;

public class Equipage {
	
	public static ArrayList<Pirate> pirates = new ArrayList<>();
	public static ArrayList<Tresor> tresors = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);
	
	//TO DO Paul
	public static void echanger(Pirate p1, Pirate p2) {
		Tresor objP1,objP2;
		objP1=p1.getObjetRecu();
		objP2=p2.getObjetRecu();
		p1.setObjetRecu(objP2);
		p2.setObjetRecu(objP1);
	}
	
	//TO DO Osmane
	public static int calculerCoutNaif() {
		int cout = 0;
		
		//Pour chaque pirate on ajoute le cout correpondant 
		//au cout total.
		for (Pirate p : pirates) {
			
		}
		
		return cout;
	}
	
	//TO DO Paul
	public static void afficherCout(boolean isNaive) {
		if(isNaive) {
			System.out.println("Le coût est "+calculerCoutNaif());
		}
	}
	
	//TO DO Paul
	public static void versionNaive() {
		
	}
	
	//TO DO Osmane
	public static void attributionNaive() {
		
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
	}
}
