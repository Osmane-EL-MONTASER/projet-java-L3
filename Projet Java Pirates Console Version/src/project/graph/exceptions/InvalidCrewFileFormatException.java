package project.graph.exceptions;

/**
 * Cette erreur est utilisée lorsque le parser
 * détecte que le contenu du fichier est incorrect.
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
