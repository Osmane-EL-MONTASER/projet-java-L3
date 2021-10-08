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
		return 0;
	}
	
	//TO DO Paul
	public static void afficherCout(boolean isNaive) {
		if(isNaive) {
			System.out.println("Le coût est "+calculerCoutNaif()+"\n");
		}
	}
	
	//TO DO Paul
	public static void versionNaive() {
		
	}
	
	//TO DO Osmane
	public static void attributionNaive() {
		
	}
	
	//TO DO Paul
	public static void initEquipageNaif() {
		int nombrePirates;
		boolean init=false;
		do {
			System.out.println("Indiquer le nombre de pirates dans l'équipage (26 max) : \n");
			nombrePirates=sc.nextInt();
			if(nombrePirates>26 || nombrePirates<0) {
				System.out.println("Nombre de pirates incohérent.\nRéessayer\n");
			}else {
				for(int i=0;i<nombrePirates;i++) {
					pirates.add(new Pirate(nombrePirates));
				}
				init=true;
			}
			
		}while(init);
	}
	
	public static void main(String[] args) {
		sc.close();
	}
}
