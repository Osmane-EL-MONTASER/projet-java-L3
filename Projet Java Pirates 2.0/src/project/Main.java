package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.crew.Crew;
import project.crew.io.NaiveCrewManagement;
import project.crew.io.TerminalGraphMaker;
import project.crew.sharing.NaiveSharing;

/**
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 *
 */
public class Main extends Application {
	
	public static Parent root = null;
	public static Scene scene;
	public static Stage ps;
	
	/**
	 * Fonction permettant de lancer le programme
	 * sous sa première version sans la sauvegarde
	 * ou la chargement d'équipages.
	 * 
	 * @deprecated
	 */
	public static void terminalProject() {
		TerminalGraphMaker.setPirates();
		
		Crew c = TerminalGraphMaker.graphMakerMenu();
		c.setAttributions(NaiveSharing.naiveAttribution(c, new ArrayList<>(Arrays.asList(TerminalGraphMaker.treasures)), 0));
		NaiveCrewManagement.crewManagementMenu(c);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(Main.class.getResource("views/crewManagementView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ps = primaryStage;
		
	    scene = new Scene(root);
		scene.getStylesheets().add
		 (Main.class.getResource("views/style.css").toExternalForm());
		
		primaryStage.setResizable(false);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
}
