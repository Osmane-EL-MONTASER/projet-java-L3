package projet.java.graphe;

import projet.java.main.Pirate;

/**
 * Repr�sente un sommet d'un graphe. A utiliser
 * de concours avec la classe Graph.
 * 
 * @author EL MONTASER Osmane
 *
 * @param <K1> Le type du sommet 
 * @param <K2>
 */
public class Vertex<K1, K2> {
	private K1 k1;
	private K2 k2;
	
	public Vertex(K1 k1, K2 k2) {
		this.k1 = k1;
		this.k2 = k2;
	}
	
	public K1 getKey1() {
		return k1;
	}
	
	public K2 getKey2() {
		return k2;
	}
	
	@Override
	public boolean equals(Object o) {
        if (o == this)
        	return true;
        
        if (!(o instanceof Vertex))
            return false;
        
        Vertex<Pirate, Pirate> v = (Vertex<Pirate, Pirate>) o;
        
        return v.getKey1() == this.getKey1()
        		&& v.getKey2() == this.getKey2();
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((k1 == null) ? 0 : k1.hashCode()) + ((k2 == null) ? 0 : k2.hashCode());
        return result;
    }
}
