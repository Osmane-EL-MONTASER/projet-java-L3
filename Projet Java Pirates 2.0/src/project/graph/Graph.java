package project.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.EdgeNotFoundException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * This class represents a graph with a list of
 * vertices and edges.
 * 
 * @author EL MONTASER Osmane
 * @param <T> The label's type of the vertices
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class Graph<T> {
	protected ArrayList<Vertex<T>> vertices;
	protected ArrayList<Edge<T>> edges;
	
	/**
	 * Initializes the lists of vertices and edges.
	 */
	public Graph() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	/**
	 * Adds a vertex in the graph.
	 * 
	 * @param v Vertex T to add.
	 */
	public void addVertex(Vertex<T> v) {
		vertices.add(v);
	}
	
	/**
	 * Adds an edge between a vertex at index v1 and a vertex at index v2 with a given 
	 * weight. No edge duplicates allowed.
	 * 
	 * @param v1 First index of the first vertex.
	 * @param v2 Second index of the second vertex.
	 * @param weight The weight of the said vertex.
	 * @throws EdgeDuplicateException If the edge already exists in the graph.
	 * @throws IllegalArgumentException If one or two of the vertices doesn't 
	 * exist in the graph.
	 */
	public void addEdge(int v1, int v2, double weight) throws EdgeDuplicateException, IllegalArgumentException {
		if(isEdgeExists(vertices.get(v1), vertices.get(v2)))
			throw(new EdgeDuplicateException("Cannot add edge because there is already one between these vertices!"));
		
		if(vertices.size() < v1 || vertices.size() < v2 || v1 < 0 || v2 < 0)
			throw(new IllegalArgumentException("Cannot add edge because 1 or the 2 vertices doesn't exist!"));
		
		edges.add(new Edge<T>(vertices.get(v1), vertices.get(v2), weight));
	}
	
	/**
	 * Adds an edge between a vertex at index v1 and a vertex at index v2. The
	 * weight of the edge will be automatically set to 0.
	 * No edge duplicates allowed.
	 * 
	 * @param v1 First index of the first vertex.
	 * @param v2 Second index of the second vertex.
	 * @throws EdgeDuplicateException If the edge already exists in the graph.
	 * @throws IllegalArgumentException If one or two of the vertices doesn't 
	 * exist in the graph.
	 */
	public void addEdge(int v1, int v2) throws EdgeDuplicateException, IllegalArgumentException {
		if(v1 >= vertices.size() || v2 >= vertices.size() || v1 < 0 || v2 < 0)
			throw(new IllegalArgumentException("Cannot add edge because 1 or the 2 vertices doesn't exist!"));
		
		if(isEdgeExists(vertices.get(v1), vertices.get(v2)))
			throw(new EdgeDuplicateException("Cannot add edge because there is already one between these vertices!"));
		
		edges.add(new Edge<T>(vertices.get(v1), vertices.get(v2)));
	}
	
	/**
	 * Know if a given vertex exists in the graph.
	 * 
	 * @param v The vertex to find.
	 * @return True if the vertex exists, False otherwise.
	 */
	public boolean isVertexExists(Vertex<T> v) {
		return vertices.contains(v);
	}
	
	/**
	 * Know if there is an edge between 2 vertices.
	 * 
	 * @param v1 First vertex.
	 * @param v2 Second vertex.
	 * @return True if there is an edge, False otherwise.
	 * @throws IllegalArgumentException If one of the given vertices doesn't 
	 * exist in the graph.
	 */
	public boolean isEdgeExists(Vertex<T> v1, Vertex<T> v2) throws IllegalArgumentException {
		if (!isVertexExists(v1) || !isVertexExists(v2))
			throw(new IllegalArgumentException("One of the vertices doesn't exist!"));
		
		return (edges.contains(new Edge<T>(v1, v2)) || edges.contains(new Edge<T>(v2, v1)));
	}
	
	/**
	 * Get the edge by giving its 2 vertices.
	 * 
	 * @param v1 First Vertex.
	 * @param v2 Second Vertex.
	 * @return The edge between v1 and v2 if it exists.
	 * @throws EdgeNotFoundException If the edge doesn't exist in the graph.
	 */
	public Edge<T> getEdge(Vertex<T> v1, Vertex<T> v2) throws EdgeNotFoundException {
		if(!isEdgeExists(v1, v2))
			throw(new EdgeNotFoundException("Cannot get weight because the given edge was not found in the graph."));

		int i = edges.indexOf(new Edge<T>(v1, v2));
		
		if (i != -1)
			return edges.get(i);
		else
			return edges.get(edges.indexOf(new Edge<T>(v2, v1)));
	}
	
	/**
	 * Get a vertex of the graph.
	 * 
	 * @param i The index of the vertex to find.
	 * @return The vertex which you want to get.
	 * @throws VertexNotFoundException If the vertex was not found in the
	 * graph.
	 */
	public Vertex<T> getVertex(int i) throws VertexNotFoundException {
		if(i < 0 || i >= vertices.size())
			throw(new VertexNotFoundException("The given vertex doesn't exist."));
		
		return vertices.get(i);
	}
	
	/**
	 * Get the number of vertices in the graph.
	 * @return The order of the graph.
	 */
	public int getOrder() {
		return vertices.size();
	}
	
	/**
	 * Algorithm of Welsh and Powell to get a list of list of vertices
	 * sorted by color. A color is represented as an integer.
	 * 
	 * @deprecated
	 * @return A LinkedHashMap which contains lists of vertices for each
	 * color of the graph. 
	 */
	public LinkedHashMap<Integer, ArrayList<T>> welshPowell() {
		return null;
	}
}
