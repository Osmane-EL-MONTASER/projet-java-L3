package project.graph;

import java.util.ArrayList;

/**
 * This class represents a vertex from a graph.
 * It has a value which is the label of the 
 * said vertex.
 * 
 * @param <T> The type of the label.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @version 1.0
 * @since 2.0
 * 
 */
public class Vertex<T> {
	protected T label;
	protected ArrayList<T> neighbours;
	
	private int id;
	private static int incr = 0;
	/**
	 * Constructs a Vertex with a label but
	 * no neighbours. (they can be added later)
	 * 
	 * @param label The label of the vertex.
	 */
	public Vertex(T label) {
		this.label = label;
		id = incr++;
	}
	
	/**
	 * Constructs a Vertex with a label with
	 * neighbours.
	 * 
	 * @param label The label of the vertex.
	 * @param neighbours The neighbours of the
	 * vertex.
	 */
	public Vertex(T label, ArrayList<T> neighbours) {
		this.label = label;
		this.neighbours = neighbours;
		id = incr++;
	}
	
	/**
	 * Adds a neighbour for this vertex.
	 * 
	 * @param neighbour The neighbour to add.
	 */
	public void addNeighbour(T neighbour) {
		neighbours.add(neighbour);
	}
	
	/**
	 * Deletes a neighbour for this vertex.
	 * 
	 * @param neighbour The neighbour to delete.
	 */
	public void deleteNeighbour(T neighbour) {
		neighbours.remove(neighbour);
	}
	
	/**
	 * This returns the id of the vertex. It allows
	 * the graph to have 2 different vertices with
	 * the same labels.
	 * 
	 * @return The id of the vertex.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get the label of the vertex.
	 * 
	 * @return The label of the vertex.
	 */
	public T getLabel() {
		return label;
	}
	
	/**
	 * Overrides the toString function. It assumes
	 * that the T parameter of Vertex has also a
	 * toString function overrided.
	 */
	@Override
	public String toString() {
		return "Vertex : " + label.toString();
	}
}

