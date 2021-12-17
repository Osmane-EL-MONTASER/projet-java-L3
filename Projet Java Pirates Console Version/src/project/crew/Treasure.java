package project.crew;

/**
 * Repr�sente un tr�sor de l'�quipage.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 2.0
 * @since 1.0
 */
public class Treasure {
	/**
	 * Incr�menter l'identifiant des tr�sors.
	 */
	private static int incrId = 0;
	
	/**
	 * L'identifiant du tr�sor.
	 */
	private int id;
	
	/**
	 * Le nom du tr�sor.
	 */
	private String name;
	
	/**
	 * Construit un tr�sor avec un nom.
	 * Le tr�sor peut avoir le m�me nom qu'un
	 * autre tr�sor.
	 * 
	 * @param name Nom du tr�sor.
	 */
	public Treasure(String name) {
		id = incrId++;
		this.name = name;
	}
	
	/**
	 * L'id du tr�sor pour permettre les tr�sors
	 * au m�me nom.
	 * 
	 * @return L'id du tr�sor.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Changer le nom du tr�sor.
	 * 
	 * @param name le nouveau nom du tr�sor.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Donne le nom du tr�sor seulement.
	 */
	@Override
	public String toString() {
		return name;
	}
}
