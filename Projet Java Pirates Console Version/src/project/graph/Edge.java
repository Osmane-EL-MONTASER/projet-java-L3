package project.graph;

/**
 * Represents an edge of a graph. It has 2 vertices
 * and a weight.
 * 
 * @param <T> The type of the vertices labels.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 1.0
 * @since 2.0
 * 
 */
public class Edge<T> {
	protected Vertex<T> v1;
	protected Vertex<T> v2;
	protected double weight;
	
	/**
	 * Initialize an edge with 2 vertices. Uses 0 as
	 * default value of weight.
	 * 
	 * @param v1 First vertex.
	 * @param v2 Second vertex.
	 */
	public Edge(Vertex<T> v1, Vertex<T> v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = 0.d;
	}
	
	/**
	 * Initialize an edge with 2 vertices and a given 
	 * weight.
	 * 
	 * @param v1 First vertex.
	 * @param v2 Second vertex.
	 * @param weight The weight of that edge.
	 */
	public Edge(Vertex<T> v1, Vertex<T> v2, double weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	/**
	 * Returns the weight of the edge.
	 * 
	 * @return a weight as a double.
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Returns the first vertex of the edge.
	 * 
	 * @return a Vertex of type T.
	 */
	public Vertex<T> getV1() {
		return v1;
	}
	
	/**
	 * Returns the second vertex of the edge.
	 * 
	 * @return a Vertex of type T.
	 */
	public Vertex<T> getV2() {
		return v2;
	}
	
	/**
	 * Overrides the the equals() function to
	 * be able to search edges in the graph.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
        	return true;
        
        if (!(o instanceof Edge))
            return false;
        
        Edge<?> c = (Edge<?>) o;
        
        return c.getV1().getId() == this.getV1().getId() 
        		&& c.getV2().getId() == this.getV2().getId();
	}
	
	/**
	 * Overrides the the hashCode() function to
	 * be able to search edges in the graph.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = (int) (prime * result
                + (this.v1.getId() + this.v2.getId()));
        return result;
	}
	
	/**
	 * Overrides the toString function. It assumes
	 * that the T parameter of Edge has also a
	 * toString function overrided.
	 */
	@Override
	public String toString() {
		return "Edge (" + weight + ") : " + v1 + " ; " + v2;
	}
}
