/**
 * 
 */
package project.controllers.management;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.controllers.CrewCreationController;
import project.graphviz.parser.GraphvizParser;

/**
 * Permet de contr�ler le le graphe contenu dans
 * l'interface graphique.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @since 3.0
 * @version 1.0
 */
public class GraphController {
	
	/**
	 * Permet de mettre � jour le graphe en m�moire ainsi
	 * que de mettre � jour l'image du graphe � l'�cran.
	 * 
	 * @param graph La ImageView correspondante contenu
	 * dans la fen�tre.
	 */
	public static void updateGraph(ImageView graph) {
		try {
			GraphvizParser.savePirateGraphToPng(CrewCreationController.model.getGraph(), CrewCreationController.model);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		graph.setImage(new Image("file:graph.png"));
	}
}
