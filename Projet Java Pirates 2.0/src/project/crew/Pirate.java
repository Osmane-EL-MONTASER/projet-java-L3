package project.crew;

/**
 * Repr�sente un pirate de l'�quipage.
 * Il poss�de un nom et sa liste de pr�f�rences dans
 * l'ordre croissant.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class Pirate {
	private String name;
	
	public Pirate(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
