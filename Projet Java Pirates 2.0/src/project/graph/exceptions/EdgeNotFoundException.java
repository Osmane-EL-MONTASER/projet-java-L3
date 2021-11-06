package project.graph.exceptions;

/**
 * Thrown when an edge doesn't exists in the graph.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class EdgeNotFoundException extends Exception {
	private static final long serialVersionUID = 2L;
	
	/**
	 * Calls super() constructor of Exception.
	 * 
	 * @param errorMessage
	 */
	public EdgeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
