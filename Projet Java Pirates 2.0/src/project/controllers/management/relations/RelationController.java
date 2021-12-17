package project.controllers.management.relations;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import project.controllers.CrewCreationController;
import project.controllers.management.GraphController;
import project.crew.Pirate;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Pour ajouter ou supprimer des relations depuis
 * l'interface graphique.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 * @since 3.0
 * @version 1.0
 */
public class RelationController extends GraphController {
	
	/**
	 * Ajouter une relation depuis l'interface graphique 
	 * à partir des 2 ChoiceBox qui contiennent les noms
	 * des pirates.
	 * Cela met aussi à jour le graphe contenu dans graph.png
	 * et la ImageView depuis l'inteface.
	 * 
	 * @param firstPirateChoice La première ChoiceBox correspondant
	 * au premier pirate.
	 * @param secondPirateChoice La deuxième ChoiceBox correspondant
	 * au deuxième pirate.
	 * @param imgView La ImageView du graphe contenu dans la fenêtre.
	 */
	public static void addRelation(ChoiceBox<Pirate> firstPirateChoice, ChoiceBox<Pirate> secondPirateChoice, ImageView imgView) {
		if (firstPirateChoice.getSelectionModel().getSelectedItem() != null
				&& secondPirateChoice.getSelectionModel().getSelectedItem() != null) {
			try {
				CrewCreationController.model.addBadRelations(firstPirateChoice.getSelectionModel().getSelectedIndex(),
						secondPirateChoice.getSelectionModel().getSelectedIndex());
				
				updateGraph(imgView);
				
				firstPirateChoice.getSelectionModel().clearSelection();
				secondPirateChoice.getSelectionModel().clearSelection();
			} catch (IllegalArgumentException | EdgeDuplicateException | VertexNotFoundException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Relation incorrect");
				alert.setHeaderText(e.getMessage());
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Relation incorrect");
			alert.setHeaderText("Veuillez sélectionner 2 pirates !");
			alert.showAndWait();
		}
	}
	
	/**
	 * Supprimer une relation depuis l'intefarce graphique
	 * à partir des 2 ChoiceBox qui contiennent les noms
	 * des pirates.
	 * Cela met aussi à jour le graphe contenu dans graph.png
	 * et la ImageView depuis l'inteface.
	 * 
	 * @param firstPirateChoice La première ChoiceBox correspondant
	 * au premier pirate.
	 * @param secondPirateChoice La deuxième ChoiceBox correspondant
	 * au deuxième pirate.
	 * @param imgView La ImageView du graphe contenu dans la fenêtre.
	 */
	public static void deleteRelation(ChoiceBox<Pirate> firstPirateChoice, ChoiceBox<Pirate> secondPirateChoice, ImageView imgView) {
		if (firstPirateChoice.getSelectionModel().getSelectedItem() != null
				&& secondPirateChoice.getSelectionModel().getSelectedItem() != null) {
			try {
				//TODO
				
				updateGraph(imgView);
				
				firstPirateChoice.getSelectionModel().clearSelection();
				secondPirateChoice.getSelectionModel().clearSelection();
			} catch (IllegalArgumentException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Relation incorrect");
				alert.setHeaderText(e.getMessage());
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Relation incorrect");
			alert.setHeaderText("Veuillez sélectionner 2 pirates !");
			alert.showAndWait();
		}
	}
}
