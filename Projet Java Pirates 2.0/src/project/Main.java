package project;

import java.util.ArrayList;
import java.util.Arrays;

import project.crew.Crew;
import project.crew.io.NaiveCrewManagement;
import project.crew.io.TerminalGraphMaker;
import project.crew.sharing.NaiveSharing;

/**
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 *
 */
public class Main {
	public static void main(String[] args) {
		TerminalGraphMaker.setPirates();
		
		Crew c = TerminalGraphMaker.graphMakerMenu();
		c.setAttributions(NaiveSharing.naiveAttribution(c, new ArrayList<>(Arrays.asList(TerminalGraphMaker.treasures)), 0));
		NaiveCrewManagement.crewManagementMenu(c);
	}
}
