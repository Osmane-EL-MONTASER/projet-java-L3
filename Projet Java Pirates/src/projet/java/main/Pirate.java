package projet.java.main;

import java.util.ArrayList;

import projet.java.graphe.GraphePirate;

public class Pirate {
	private static char n = 'A';
	
	private char nom;
	private ArrayList<Tresor> preferences;
	private Tresor objetRecu;
	
	public Pirate(int totalPirates) {
		if (n > 'Z')
			System.out.println("Impossible de nommer le pirate");
		else {
			nom=n++;
			
			preferences = new ArrayList<>();
			
			for (int i = 0; i < totalPirates; i++)
				preferences.add(null);
		}
		
	}
	
	public boolean relationAvec(GraphePirate g, Pirate p) {
		return g.getRelation(this, p);
	}
	
	public boolean estJaloux(GraphePirate g, ArrayList<Pirate> pirates) {
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
				if (relationAvec(g, pirates.get(i)))
					isJaloux = true;
			}
		}
		
		return isJaloux;
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
	
	public void setPreference(int i, Tresor t) {
		preferences.set(i, t);
	}
	
	@Override
	public boolean equals(Object o) {
        if (o == this)
        	return true;
        
        if (!(o instanceof Pirate))
            return false;
        
        Pirate c = (Pirate) o;
        
        return c.getNom() == this.nom;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + (this.getNom());
        return result;
    }
}
