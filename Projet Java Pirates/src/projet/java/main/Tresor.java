package projet.java.main;

public class Tresor {
	private static int n = 0;
	
	private String nom;
	private int num;
	
	Tresor() {
		num = n++;
		nom = "o" + num;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}
