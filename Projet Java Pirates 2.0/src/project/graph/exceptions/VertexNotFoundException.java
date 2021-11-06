package project.graph.exceptions;

/**
 * Thrown when a vertex doesn't exists in the graph.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class VertexNotFoundException extends Exception {
	private static final long serialVersionUID = 3L;
	
	/**
	 * Calls super() constructor of Exception.
	 * 
	 * @param errorMessage
	 */
	public VertexNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
