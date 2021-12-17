package project.crew;

/**
 * Représente un trésor de l'équipage.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 2.0
 * @since 1.0
 */
public class Treasure {
	/**
	 * Incrémenter l'identifiant des trésors.
	 */
	private static int incrId = 0;
	
	/**
	 * L'identifiant du trésor.
	 */
	private int id;
	
	/**
	 * Le nom du trésor.
	 */
	private String name;
	
	/**
	 * Construit un trésor avec un nom.
	 * Le trésor peut avoir le même nom qu'un
	 * autre trésor.
	 * 
	 * @param name Nom du trésor.
	 */
	public Treasure(String name) {
		id = incrId++;
		this.name = name;
	}
	
	/**
	 * L'id du trésor pour permettre les trésors
	 * au même nom.
	 * 
	 * @return L'id du trésor.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Changer le nom du trésor.
	 * 
	 * @param name le nouveau nom du trésor.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Donne le nom du trésor seulement.
	 */
	@Override
	public String toString() {
		return name;
	}
}
