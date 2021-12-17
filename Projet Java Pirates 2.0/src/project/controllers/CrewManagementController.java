package project.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import project.Main;
import project.controllers.management.GraphController;
import project.controllers.management.relations.RelationController;
import project.crew.Attribution;
import project.crew.Pirate;
import project.crew.Preference;
import project.crew.Treasure;
import project.crew.graph.PirateVertex;
import project.crew.io.CrewParser;
import project.crew.sharing.NaiveSharing;
import project.graph.Vertex;
import project.graphviz.parser.GraphvizParser;

/**
 * @author EL MONTASER Osmane
 *
 */
public class CrewManagementController {
	@FXML
    private ChoiceBox<Pirate> comboPiratesEdit;
	@FXML
    private ChoiceBox<Pirate> firstPirateChoice;
	@FXML
    private ChoiceBox<Pirate> secondPirateChoice;
	@FXML
	private ImageView graph;
	@FXML
	private TableView<Preference> tablePref;
	@FXML
	private TableColumn<Preference, String> colName;
	@FXML
	private TableColumn<Preference, String> colOrder;
	private ObservableList<Preference> tableData = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Attribution> tableAttribution;
	@FXML
	private TableColumn<Attribution, String> colPirateName;
	@FXML
	private TableColumn<Attribution, String> colAttribution;
	private ObservableList<Attribution> tableDataAtt = FXCollections.observableArrayList();
	
	@FXML
	private Label labelSolutionCost;
	
	@FXML
    private TextField pirateNameEditTextbox;
	
	//private static ArrayList<Vertex<Pirate>> jealous;
	
	public void initialize() {
		comboPiratesEdit.setItems(CrewCreationController.pirates);
		firstPirateChoice.setItems(CrewCreationController.pirates);
		secondPirateChoice.setItems(CrewCreationController.pirates);
		GraphController.updateGraph(graph);
		tablePref.getItems().addAll(tableData);
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colOrder.setCellFactory(TextFieldTableCell.forTableColumn());
		
		tableAttribution.getItems().addAll(tableDataAtt);
		colAttribution.setCellFactory(TextFieldTableCell.forTableColumn());
		
		colName.setOnEditCommit(
				(TableColumn.CellEditEvent<Preference, String> t) -> {
					for(Treasure tr : CrewCreationController.model.getTreasures())
			        	if(tr.toString().equals(t.getOldValue()))
			        		tr.setName(t.getNewValue());
			        	
			        ((Preference) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setTreasureName(t.getNewValue());
			});
		
		colOrder.setOnEditCommit(
				(TableColumn.CellEditEvent<Preference, String> t) -> {
					int oldIndex = Integer.parseInt(t.getOldValue());
					int newIndex = Integer.parseInt(t.getNewValue());
			        int selectedPirateIndex = comboPiratesEdit.getSelectionModel().getSelectedIndex();
			        Pirate p = CrewCreationController.model.getGraph().getVertices().get(selectedPirateIndex).getLabel();
			        Treasure[] newPrefs = p.getPreferences();
			        Treasure tempPref = newPrefs[oldIndex];
			        newPrefs[oldIndex] = newPrefs[newIndex];
			        newPrefs[newIndex] = tempPref;
					
					p.setPreferences(newPrefs);
					
					((Preference) t.getTableView().getItems().get(
				            newIndex)
				            ).setOrder(t.getOldValue());
					
			        ((Preference) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setOrder(t.getNewValue());
			        
			        tablePref.refresh();
			});
		
		comboPiratesEdit.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			pirateNameEditTextbox.setText(newValue.toString());
			tableData.clear();
			
			if(newValue.getPreferences() == null) {
				for(int i = 0; i < CrewCreationController.pirates.size(); i++) {
					tableData.add(new Preference(CrewCreationController.model.getTreasures()[i].toString(), Integer.toString(i)));
					i++;
				}
			}
			
			int i = 0;
			for(Treasure t : newValue.getPreferences()) {
				tableData.add(new Preference(t.toString(), Integer.toString(i)));
				i++;
			}
			tablePref.getItems().clear();
			tablePref.getItems().addAll(tableData);
		});
		
		firstPirateChoice.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			if(secondPirateChoice.getSelectionModel().getSelectedItem() != null)
				try {
					GraphvizParser.saveGraphToPngWithSelected(CrewCreationController.model.getGraph(),
							firstPirateChoice.getSelectionModel().getSelectedIndex(),
							secondPirateChoice.getSelectionModel().getSelectedIndex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				try {
					GraphvizParser.saveGraphToPngWithSelected(CrewCreationController.model.getGraph(),
							firstPirateChoice.getSelectionModel().getSelectedIndex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			graph.setImage(new Image("file:graph.png"));
		});
		
		secondPirateChoice.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			if(firstPirateChoice.getSelectionModel().getSelectedItem() != null)
				try {
					GraphvizParser.saveGraphToPngWithSelected(CrewCreationController.model.getGraph(),
							firstPirateChoice.getSelectionModel().getSelectedIndex(),
							secondPirateChoice.getSelectionModel().getSelectedIndex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				try {
					GraphvizParser.saveGraphToPngWithSelected(CrewCreationController.model.getGraph(),
							secondPirateChoice.getSelectionModel().getSelectedIndex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			graph.setImage(new Image("file:graph.png"));
		});
		
		//jealous = NaiveSharing.getJealous(null, null);
	}
	
	@FXML protected void handleOptimalSolution(ActionEvent event) {
		
	}
	
	@FXML protected void handleNaiveSolution(ActionEvent event) {
		if(CrewCreationController.model.getGraph().getVertices().size() > 0) {
			CrewCreationController.model.setAttributions(NaiveSharing.naiveAttribution(CrewCreationController.model, 
					new ArrayList<Treasure>(Arrays.asList(CrewCreationController.model.getTreasures())), 0));
			
			double solution = NaiveSharing.getAttributionCost(CrewCreationController.model, CrewCreationController.model.getTreasures());
			labelSolutionCost.setText("Coût de la solution actuelle : " + Double.toString(solution));
			
			if(solution == 0)
				labelSolutionCost.setTextFill(Color.LIME);
			else
				labelSolutionCost.setTextFill(Color.RED);
			
			//CrewCreationController.model.setJealousPirate(NaiveSharing.getJealous(CrewCreationController.model, 
			//		CrewCreationController.model.getTreasures()));
			
			tableDataAtt.clear();
			
			for(Entry<PirateVertex, Treasure> t : CrewCreationController.model.getAttributions().entrySet())
				tableDataAtt.add(new Attribution(t.getKey().getLabel().toString(), t.getValue().toString()));
			
			tableAttribution.getItems().clear();
			tableAttribution.getItems().addAll(tableDataAtt);
			
			GraphController.updateGraph(graph);
		}
	}
	
	@FXML protected void handleDeleteRelation(ActionEvent event) {
		//TODO
	}
	
	@FXML protected void handleSaveCrew(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Sauvegarder un équipage");
		File f = fileChooser.showSaveDialog(Main.ps);
		
		if(f != null) {
			try {
				CrewParser.saveCrewToDisk(f.getAbsolutePath(), CrewCreationController.model);
			} catch (FileNotFoundException e) {
				//TODO
			}
		}
	}
	
	@FXML protected void handleOpenCrew(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Ouvrir un équipage");
		File f = fileChooser.showOpenDialog(Main.ps);
		
		try {
			CrewCreationController.model = CrewParser.parseFromTxtFile(f.getAbsolutePath());
			GraphController.updateGraph(graph);
			CrewCreationController.pirates.clear();
			comboPiratesEdit.getItems().clear();
			firstPirateChoice.getItems().clear();
			secondPirateChoice.getItems().clear();
			for(Vertex<Pirate> p : CrewCreationController.model.getGraph().getVertices())
				CrewCreationController.pirates.add(p.getLabel());
			comboPiratesEdit.setItems(CrewCreationController.pirates);
			firstPirateChoice.setItems(CrewCreationController.pirates);
			secondPirateChoice.setItems(CrewCreationController.pirates);
			tablePref.getItems().addAll(tableData);
			colName.setCellFactory(TextFieldTableCell.forTableColumn());
			colOrder.setCellFactory(TextFieldTableCell.forTableColumn());
			for(Vertex<Pirate> p : CrewCreationController.model.getGraph().getVertices())
				CrewCreationController.pirates.add(p.getLabel());
		} catch (Exception e) {
			showError("Échec lors de l'ouverture du fichier", "Le fichier donné est incorrect.");
		}
	}
	
	@FXML protected void handleDeselect(ActionEvent event) {
		firstPirateChoice.getSelectionModel().clearSelection();
		secondPirateChoice.getSelectionModel().clearSelection();
		GraphController.updateGraph(graph);
	}
	
	@FXML protected void handleAddRelation(ActionEvent event) {
		RelationController.addRelation(firstPirateChoice, secondPirateChoice, graph);
	}
	
	@FXML protected void handleMenuCrewCreation(ActionEvent event) {
		//Retourner au menu de création de l'équipage pour ajouter ou supprimer des pirates.
		try {
			Main.root = FXMLLoader.load(Main.class.getResource("views/crewCreationView.fxml"));
			Main.scene = new Scene(Main.root);
			Main.scene.getStylesheets().add
			 (Main.class.getResource("views/style.css").toExternalForm());
			Main.ps.setScene(Main.scene);
			Main.ps.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void showError(String title, String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	@FXML protected void handleApplyChanges(ActionEvent event) {
		if(comboPiratesEdit.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aucun pirate sélectionné");
			alert.setHeaderText("Veuillez sélectionner un pirate !");
			alert.showAndWait();
		} else {
			if(!comboPiratesEdit.getSelectionModel().getSelectedItem().toString().equals(pirateNameEditTextbox.getText())) {
				CrewCreationController.model.getGraph().getVertices().get(comboPiratesEdit.getSelectionModel().getSelectedIndex()).getLabel().setName(pirateNameEditTextbox.getText());
				int i = comboPiratesEdit.getSelectionModel().getSelectedIndex();
				Pirate p = comboPiratesEdit.getSelectionModel().getSelectedItem();
				CrewCreationController.pirates.remove(comboPiratesEdit.getSelectionModel().getSelectedIndex());
				CrewCreationController.pirates.add(i, new Pirate(pirateNameEditTextbox.getText(), p.getPreferences()));
				comboPiratesEdit.getSelectionModel().selectFirst();
				GraphController.updateGraph(graph);
			}
		}
	}
}
