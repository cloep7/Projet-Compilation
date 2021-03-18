package asm;

import java.util.ArrayList;

import fr.ul.miage.arbre.Noeud;
import tds.IDFTds;
import tds.Tds;

public class Generateur {

	/*String generer_programme(Noeud a, Tds tds) {
		
	}*/
	
	String generer_data(Tds tds) {
		String res = "";
		ArrayList<IDFTds> idfs = new ArrayList<IDFTds>();
		
		for (int i=0 ; i<tds.getTds().size() ; i++) {
			if (tds.getTds().get(i) instanceof IDFTds) {
				idfs.add((IDFTds)tds.getTds().get(i));
			}
		}
		
		for (int i=0 ; i<idfs.size() ; i++) {
			if (idfs.get(i).getCat().equals("global")) {
				res+= idfs.get(i).getNom() +":\t LONG("+idfs.get(i).getVal()+")\n";
			}
		}
		return res;
	}
	
	String generer_debut() {
		String res = ".include beta.uasm\r\n" + 
					".include intio.uasm\r\n" + 
					".options tty\n" +
					"\n" +
					"\tCMOVE(pile,SP)\r\n" + 
					"\tBR(debut)\n";
		return res;
	}
}
