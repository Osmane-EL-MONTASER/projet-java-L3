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
		else {
			nom=n++;
			
			relations = new ArrayList<>();
			preferences = new ArrayList<>();
			
			for (int i = 0; i < totalPirates; i++)
				relations.add(false);
			for (int i = 0; i < totalPirates; i++)
				preferences.add(null);
		}
		
	}
	
	public boolean relationAvec(Pirate p) {
		int indexOfPirate = p.getNom() - 'A';
		return relations.get(indexOfPirate);
	}
	
	public boolean estJaloux(ArrayList<Pirate> pirates) {
		boolean isJaloux = false;
		
		//S'il a recu ce qu'il voulait, il n'est 
		//pas jaloux
		if (preferences.get(0) == objetRecu)
			return isJaloux;
		
		//On prend les objets qu'il aurait 
		//prefere avoir plutot que celui qu'il
		//a recu
		ArrayList<Tresor> meilleurPrefs = new ArrayList<Tresor>(preferences.subList(0, 
				Integer.parseInt(objetRecu.toString().substring(1))));
		
		//On regarde si un pirate l'a recu
		//et s'il a de mauvaises relations
		//avec lui
		for (int i = 0; i < pirates.size() && !isJaloux; i++) {
			//Ce pirate detient un objet qu'il
			//aurait prefere recevoir avant
			//celui qu'il a recu
			if (meilleurPrefs.contains(pirates.get(i).getObjetRecu())) {
				//Le pirate est en effet dans
				//de mauvaises relations avec
				//ce pirate
				if (relationAvec(pirates.get(i)))
					isJaloux = true;
			}
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
	
	public void setRelation(int i, boolean b) {
		relations.set(i, b);
	}
	
	public void setPreference(int i, Tresor t) {
		preferences.set(i, t);
	}
}
