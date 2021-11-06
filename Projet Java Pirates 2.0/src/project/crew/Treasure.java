package project.crew;

/**
 * Repr�sente un tr�sor de l'�quipage.
 * 
 * @author EL MONTASER Osmane
 * @version 2.0
 * @since 1.0
 */
public class Treasure {
	private static int incrId = 0;
	
	private int id;
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
	 * Donne le nom du tr�sor seulement.
	 */
	@Override
	public String toString() {
		return name;
	}
}
