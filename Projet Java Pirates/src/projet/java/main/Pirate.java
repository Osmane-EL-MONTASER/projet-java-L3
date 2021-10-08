package projet.java.main;

import java.util.ArrayList;

public class Pirate {
	private static char n = 'A';
	
	private char name;
	private ArrayList<Boolean> relations;
	private ArrayList<Tresor> preferences;
	private Tresor objetRecu;
	
	public Pirate(int totalPirates) {
		if (n > 'Z')
			System.out.println("Impossible de nommer le pirate");
	
		relations = new ArrayList<>(totalPirates);
		preferences = new ArrayList<>(totalPirates);
	}
	
	//TO DO
	public boolean relationAvec(Pirate p) {
		return true;
	}
	
	public ArrayList<Boolean> getRelations() {
		return relations;
	}
	public ArrayList<Tresor> getPreferences() {
		return preferences;
	}
	
	public void setRelation(int i, boolean b) {
		
	}
	
	public void setPreference(int i, Tresor t) {
		
	}
}
