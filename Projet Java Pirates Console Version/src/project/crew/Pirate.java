package project.crew;

/**
 * Repr�sente un pirate de l'�quipage.
 * Il poss�de un nom et sa liste de pr�f�rences dans
 * l'ordre croissant.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 2.0
 * @since 1.0
 */
public class Pirate {
	/**
	 * Permet d'incr�menter l'identifiant des pirates.
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
	 * La liste de ses pr�f�rences.
	 */
	private Treasure[] preferences;
	
	/**
	 * Permet de construire un pirate.
	 * 
	 * @param name Le nom du pirate.
	 * @param preferences La liste des pr�f�rences du
	 * pirates. Peut �tre null au d�part.
	 */
	public Pirate(String name, Treasure[] preferences) {
		this.id = incrId++;
		this.name = name;
		this.preferences = preferences;
	}
	
	/**
	 * R�cup�rer l'id du pirate.
	 * @return l'id du pirate.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * R�cup�rer la liste des pr�f�rences du pirate.
	 * @return la liste des pr�f�rences.
	 */
	public Treasure[] getPreferences() {
		return preferences;
	}
	
	/**
	 * Permet de modifier la liste des pr�f�rences du
	 * pirate.
	 * 
	 * @param preferences les nouvelles pr�f�rences.
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
	 * R�cup�rer le nom du pirate.
	 * 
	 * @return Le nom du pirate.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Convertir le Pirate en une cha�ne de 
	 * caract�res.
	 */
	@Override
	public String toString() {
		String str = new String();
		
		str += name;
		
		return str;
	}
}
