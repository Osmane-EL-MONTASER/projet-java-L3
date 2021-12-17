package project.graph.exceptions;

/**
 * Thrown when there is already an edge between the 2
 * given vertices.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 2.0
 */
public class EdgeDuplicateException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls super() constructor of Exception.
	 * 
	 * @param errorMessage Message of the exception.
	 */
	public EdgeDuplicateException(String errorMessage) {
        super(errorMessage);
    }
}
