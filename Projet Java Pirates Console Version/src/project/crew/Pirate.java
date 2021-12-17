package project.crew;

/**
 * Représente un pirate de l'équipage.
 * Il possède un nom et sa liste de préférences dans
 * l'ordre croissant.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 2.0
 * @since 1.0
 */
public class Pirate {
	/**
	 * Permet d'incrémenter l'identifiant des pirates.
	 */
	private static int incrId = 0;
	/**
	 * L'identifiant du pirate.
	 */
	private int id;
	/**
	 * Le nom du pirate.
	 */
	private String name;
	/**
	 * La liste de ses préférences.
	 */
	private Treasure[] preferences;
	
	/**
	 * Permet de construire un pirate.
	 * 
	 * @param name Le nom du pirate.
	 * @param preferences La liste des préférences du
	 * pirates. Peut être null au départ.
	 */
	public Pirate(String name, Treasure[] preferences) {
		this.id = incrId++;
		this.name = name;
		this.preferences = preferences;
	}
	
	/**
	 * Récupérer l'id du pirate.
	 * @return l'id du pirate.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Récupérer la liste des préférences du pirate.
	 * @return la liste des préférences.
	 */
	public Treasure[] getPreferences() {
		return preferences;
	}
	
	/**
	 * Permet de modifier la liste des préférences du
	 * pirate.
	 * 
	 * @param preferences les nouvelles préférences.
	 */
	public void setPreferences(Treasure[] preferences) {
		this.preferences = preferences;
	}
	
	/**
	 * Changer le nom du pirate.
	 * 
	 * @param name Le nouveau nom.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Récupérer le nom du pirate.
	 * 
	 * @return Le nom du pirate.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Convertir le Pirate en une chaîne de 
	 * caractères.
	 */
	@Override
	public String toString() {
		String str = new String();
		
		str += name;
		
		return str;
	}
}
