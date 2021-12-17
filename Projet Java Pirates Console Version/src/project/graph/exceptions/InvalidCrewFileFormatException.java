package project.graph.exceptions;

/**
 * Cette erreur est utilis�e lorsque le parser
 * d�tecte que le contenu du fichier est incorrect.
 * 
 * @author EL MONTASER Osmane
 * @since 3.0
 * @version 1.0
 */
public class InvalidCrewFileFormatException extends Exception {
	private static final long serialVersionUID = 4L;
	
	public InvalidCrewFileFormatException(String str) {
		super(str);
	}
}
