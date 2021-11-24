package project.controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import project.Main;
import project.crew.Crew;
import project.crew.Pirate;

/**
 * Cette classe gère tous les évènements liés à l'interface
 * de création de l'équipage de pirates.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 3.0
 */
public class CrewCreationController {
	public static Crew model = new Crew();
	public static ObservableList<Pirate> pirates = FXCollections.observableArrayList();
	
	@FXML
    private ListView<Pirate> pirateList;
	@FXML
	private TextField nameTextbox;
	
	public void initialize() {
		pirateList.setItems(pirates);
	}

	@FXML protected void handleNext(ActionEvent event) {
		if (pirateList.getItems().size() < 2) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Équipage incomplet");
			alert.setHeaderText("Veuillez ajouter au moins 2 pirates à votre équipage !");
			alert.showAndWait();
		} else {
			try {
				Parent page = (Parent) FXMLLoader.load(Main.class.getResource("views/crewManagementView.fxml"), null, new JavaFXBuilderFactory());
				Main.ps.getScene().setRoot(page);
				Main.ps.sizeToScene();
				Main.ps.centerOnScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML protected void handleAddPirate(ActionEvent event) {
		addPirate();
	}
	
	@FXML protected void handleEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			addPirate();
	}
	
	@FXML protected void handleDeletePirate(ActionEvent event) {
		if(!pirateList.getSelectionModel().isEmpty()) {
			model.deleteCrewMember(pirateList.getSelectionModel().getSelectedIndex());
			pirateList.getItems().remove(pirateList.getSelectionModel().getSelectedIndex());
			System.out.println(model.getGraph());
		}
	}
	
	private void addPirate() {
		if (nameTextbox.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nom de pirate incorrect");
			alert.setHeaderText("Veuillez saisir le nom du pirate !");
			alert.showAndWait();
		} else {
			String name = nameTextbox.getText();
			nameTextbox.setText("");
			Pirate toAdd = new Pirate(name, null);
			
			model.addCrewMember(toAdd);
			pirates.add(toAdd);
			System.out.println(model.getGraph());
		}
	}
}
