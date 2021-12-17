package project;

import java.util.Scanner;

import project.crew.io.BetterCrewManagement;

/**
 * Cette classe contient le Main qui va lancer
 * la version du projet qui se lancera dans le
 * terminal.
 * Ceci correspond donc � la version qui a �t�
 * demand� dans le sujet et ne pr�sente aucune
 * am�lioration, si ce n'est que pour 
 * l'algorithme de partage de butin.
 * 
 * @author EL MONTASER Osmane
 * @author VIDART Paul
 */
public class TerminalMain {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {
		String filename;
		
		if(args.length < 1) {
			System.out.println("Veuillez renseigner le chemin vers le fichier �quipage � charger : ");
			filename = sc.nextLine();
		} else {
			filename = args[0];
		}
		BetterCrewManagement.bCMMenu(filename);
	}
}
