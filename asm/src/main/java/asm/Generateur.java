package asm;

import java.util.ArrayList;

import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Noeud;
import tds.IDFTds;
import tds.Tds;

public class Generateur {

	/*String generer_programme(Noeud a, Tds tds) {
		String res = "";
		res= generer_debut();
		res+=generer_data(tds);
		
		for(Noeud n : a.getFils()) {
			//res+= generer_fonction(n);
		}
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
	
	String generer_affectation(Noeud a) {
		String res="";
		//res=generer_expression(a.getFils().get(1));
		String fg=a.getFils().get(0).getLabel();
        String label=fg.substring(fg.indexOf("/")+1);
        res+="\tPOP(R0)\r\n"+
        		"\tST(R0,"+label+")\n";
        return res;
	}
	
	String generer_ecrire(Noeud a) {
		String res="";
		//res=generer_expression(a.getFils());
		res+="\tPOP(R0)\r\n" +
				"\tWRINT()\r\n";
		return res;
	}
	
	// Pas sûr
	String generer_expression(Noeud a) {
		String res="";
		for (Noeud n : a.getFils()) {
			switch (n.getCat()) {
				case CONST:
					res+="\tCMOVE("+ n.getLabel().substring(n.getLabel().indexOf("/")+1) +",R0)\r\n"+
							"\tPUSH(R0)\r\n";
					break;
				case IDF:
					res+="\tLD("+ n.getLabel().substring(n.getLabel().indexOf("/")+1) +",R0)\r\n"+
							"\tPUSH(R0)\r\n";
					break;
				case PLUS:
					res+=generer_expression(n.getFils().get(0));
					res+=generer_expression(n.getFils().get(1));
					res+="\tPOP(R2)\r\n"+
							"\tPOP(R1)\r\n"+
							"\tADD(R1,R2,R0)\r\n"+
							"\tPUSH(R0)\r\n";
					break;
				case MOINS:
					res+=generer_expression(n.getFils().get(0));
					res+=generer_expression(n.getFils().get(1));
					
			}
		}
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
	
	String generer_finProg() {
		String res = "debut:\r\n" + 
				"\tCALL(main)\r\n" + 
				"\tHALT()\r\n" +
				"pile:\n";
		return res;
	}
}
