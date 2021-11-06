package project.crew;

/**
 * Représente un pirate de l'équipage.
 * Il possède un nom et sa liste de préférences dans
 * l'ordre croissant.
 * 
 * @author EL MONTASER Osmane
 * @version 2.0
 * @since 1.0
 */
public class Pirate {
	private int incrId = 0;
	private int id;
	private String name;
	private Treasure[] preferences;
	
	public Pirate(String name, Treasure[] preferences) {
		this.id = incrId++;
		this.name = name;
		this.preferences = preferences;
	}
	
	public int getId() {
		return id;
	}
	
	public Treasure[] getPreferences() {
		return preferences;
	}
	
	@Override
	public String toString() {
		String str = new String();
		
		str += name;
		
		return str;
	}
}
