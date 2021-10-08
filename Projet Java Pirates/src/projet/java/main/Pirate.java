package projet.java.main;

import java.util.ArrayList;

public class Pirate {
	private static char n = 'A';
	
	private char nom;
	private ArrayList<Boolean> relations;
	private ArrayList<Tresor> preferences;
	private Tresor objetRecu;
	
	public Pirate(int totalPirates) {
		if (n > 'Z')
			System.out.println("Impossible de nommer le pirate");
	
		relations = new ArrayList<>(totalPirates);
		preferences = new ArrayList<>(totalPirates);
	}
	
	//TO DO Osmane
	public boolean relationAvec(Pirate p) {
		return true;
	}
	
	public boolean estJaloux(ArrayList<Pirate> pirates) {
		boolean isJaloux = false;
		
		//S'il a recu ce qu'il voulait, il n'est 
		//pas jaloux
		if (preferences.get(0) == objetRecu)
			return isJaloux;
		
		for (Pirate p : pirates) {
			
		}
		
		return isJaloux;
	}
	
	public ArrayList<Boolean> getRelations() {
		return relations;
	}
	public ArrayList<Tresor> getPreferences() {
		return preferences;
	}
	
	public char getNom() {
		return nom;
	}
	
	public Tresor getObjetRecu() {
		return objetRecu;
	}
	
	public void setObjetRecu(Tresor objetRecu) {
		this.objetRecu = objetRecu;
	}
	
	//TO DO Paul
	public void setRelation(int i, boolean b) {
		
	}
	
	//TO DO Paul
	public void setPreference(int i, Tresor t) {
		
	}
}
