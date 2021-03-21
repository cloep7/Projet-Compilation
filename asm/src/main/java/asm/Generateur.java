package asm;

import java.util.ArrayList;

import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Noeud;
import fr.ul.miage.arbre.TantQue;
import tds.FonctionTDS;
import tds.IDFTds;
import tds.Tds;

public class Generateur {

	String generer_programme(Noeud a, Tds tds) {
		String res = "";
		res= generer_debut();
		res+=generer_data(tds);
		
		for(Noeud n : a.getFils()) {
			res+= generer_fonction(n,tds);
		}
		res+=generer_finProg();
		return res;
	}
	
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
	
	String generer_affectation(Noeud a, Tds tds) {
		String res="";
		res+=generer_expression(a.getFils().get(1),tds);
		
		String fg = a.getFils().get(0).getLabel();
		String nomIdf = fg.substring(fg.indexOf("/")+1);
		if (tds.getIdfByName(nomIdf).getCat().equals("global")) {
	        res+="\tPOP(R0)\r\n"+
	        		"\tST(R0,"+nomIdf+")\r\n";
		} else if (tds.getIdfByName(nomIdf).getCat().equals("local")) {
			res+="\tPOP(R0)\r\n"+
					"\tPUTFRAME(R0,"+tds.getIdfByName(nomIdf).getRang() * 4+")\r\n";
		} else if (tds.getIdfByName(nomIdf).getCat().equals("param")) {
			int nbParam=tds.getFuncByName(tds.getIdfByName(nomIdf).getScope()).getNbparam();
			res+="\tPOP(R0)\r\n"+
					"\tPUTFRAME(R0,"+ (2+nbParam-tds.getIdfByName(nomIdf).getRang())*-4 +")\r\n";
		}
        
        return res;
	}
	
	String generer_ecrire(Noeud a, Tds tds) {
		String res="";
		res=generer_expression(a.getFils().get(0),tds);
		res+="\tPOP(R0)\r\n" +
				"\tWRINT()\r\n";
		return res;
	}
	
	String generer_bloc(Noeud a, Tds tds) {
		String res="";
		for(Noeud n : a.getFils()) {
			res+=generer_instruction(n,tds);
		}
		return res;
	}
	
	String generer_condition(Noeud a, Tds tds) {
		String res="";
		switch (a.getCat()) {
			case CONST:
				res+="\tCMOVE("+a.getLabel().substring(a.getLabel().indexOf("/")+1)+",R0)"+
						"\tPUSH(R0)\r\n";
				break;
			case INF:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tCMPLT(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case INFE:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tCMPLE(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case SUP:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tCMPLT(R2,R1,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case SUPE:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tCMPLE(R2,R1,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case EG:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tCMPEQ(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case DIF:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tCMPNEQ(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
		}
		return res;
	}
	
	String generer_si(Noeud a, Tds tds) {
		String res="";
		res+=generer_condition(a.getFils().get(0),tds);
		res+="\tPOP(R0)\r\n"+
				"\tBF(R0,sinon"+a.getLabel().substring(a.getLabel().indexOf("/")+1)+")\r\n";
		res+=generer_bloc(a.getFils().get(1),tds);
		res+="\tBR(fsi"+a.getLabel().substring(a.getLabel().indexOf("/")+1)+")\n"+
				"sinon"+a.getLabel().substring(a.getLabel().indexOf("/")+1)+":\r\n";
		res+=generer_bloc(a.getFils().get(2),tds);
		res+="fsi"+a.getLabel().substring(a.getLabel().indexOf("/")+1)+":\r\n";
		return res;
		
	}
	
	String generer_expression(Noeud a, Tds tds) {
		String res="";
		switch (a.getCat()) {
			case CONST:
				res+="\tCMOVE("+ a.getLabel().substring(a.getLabel().indexOf("/")+1) +",R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case IDF:
				String nomIdf = a.getLabel().substring(a.getLabel().indexOf("/")+1);
				if (tds.getIdfByName(nomIdf).getCat().equals("global")) {
					res+="\tLD("+ nomIdf +",R0)\r\n"+
							"\tPUSH(R0)\r\n";
				} else if (tds.getIdfByName(nomIdf).getCat().equals("local")) {
					res+="\tGETFRAME("+tds.getIdfByName(nomIdf).getRang() * 4+",R0)\r\n"+
							"\tPUSH(R0)\r\n";;
				} else if (tds.getIdfByName(nomIdf).getCat().equals("param")) {
					int nbParam=tds.getFuncByName(tds.getIdfByName(nomIdf).getScope()).getNbparam();
					res+="\tGETFRAME("+ (2 + nbParam - tds.getIdfByName(nomIdf).getRang()) * -4 +",R0)\r\n"+
							"\tPUSH(R0)\r\n";;
				}
				
				break;
			case PLUS:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tADD(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case MOINS:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tSUB(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case MUL:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tMUL(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case DIV:
				res+=generer_expression(a.getFils().get(0),tds);
				res+=generer_expression(a.getFils().get(1),tds);
				res+="\tPOP(R2)\r\n"+
						"\tPOP(R1)\r\n"+
						"\tDIV(R1,R2,R0)\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case LIRE:
				res+="\tRDINT()\r\n"+
						"\tPUSH(R0)\r\n";
				break;
			case APPEL:
				res+=generer_appel(a,tds);
			default:
					
			
		}
		return res;
	}
	
	String generer_appel(Noeud a, Tds tds) {
		String res="";
		FonctionTDS funcTDS = tds.getFuncByName(a.getLabel().substring(a.getLabel().indexOf("/")+1));
		if (!funcTDS.getType().equals("void")) {
			res+="\tALLOCATE(1)\r\n";
			for(Noeud n : a.getFils()) {
				res+=generer_expression(n,tds);
			}
			res+="\tCALL("+a.getLabel().substring(a.getLabel().indexOf("/")+1)+")\r\n"+
					"\tDEALLOCATE("+funcTDS.getNbparam()+")\r\n";
		}
		return res;
	}
	
	String generer_tq(Noeud a, Tds tds) {
		String res="";
		String numTQ = a.getLabel().substring(a.getLabel().indexOf("/")+1);
		res+="tq"+numTQ+":\r\n";
		res+=generer_condition(a.getFils().get(0), tds);
		res+="\tPOP(R0)\r\n"+
				"\tBF(R0,ftq"+numTQ+")\r\n";
		res+=generer_bloc(a.getFils().get(1),tds);
		res+="\tBR(tq"+numTQ+")\r\n";
		res+="ftq"+numTQ+":\r\n";
		return res;
	}
	
	String generer_fonction(Noeud a, Tds tds) {
		String res="";
		String nomFunc = a.getLabel().substring(a.getLabel().indexOf("/")+1);
		FonctionTDS func = tds.getFuncByName(nomFunc);
		res+=nomFunc+":\r\n";
		res+="\tPUSH(LP)\r\n"+
				"\tPUSH(BP)\r\n"+
				"\tMOVE(SP,BP)\r\n"+
				"\tALLOCATE("+func.getNbloc()+")\r\n";
		for (Noeud n : a.getFils()) {
			res+= generer_instruction(n, tds);
		}
		res+="return_"+nomFunc+":\r\n"+
				"\tDEALLOCATE("+func.getNbloc()+")\r\n"+
				"\tPOP(BP)\r\n"+
				"\tPOP(LP)\r\n"+
				"\tRTN()\r\n";
		return res;
	}
	
	String generer_retour(Noeud a, Tds tds) {
		String res="";
		String nomFunc=a.getLabel().substring(a.getLabel().indexOf("/")+1);
		int nbParam=tds.getFuncByName(nomFunc).getNbparam();
		
		res+=generer_expression(a.getFils().get(0), tds);
		int offset=(3+nbParam)*-4;
		res+="\tPOP(R0)\r\n"+
				"\tPUTFRAME(R0,"+offset+")\r\n"+
				"\tBR(return_"+nomFunc+")\r\n";
		return res;
	}
	
	String generer_instruction(Noeud a, Tds tds) {
		String res="";
		switch (a.getCat()) {
			case AFF:
				res+=generer_affectation(a,tds);
				break;
			case ECR:
				res+=generer_ecrire(a,tds);
				break;
			case SI:
				res+=generer_si(a,tds);
				break;
			case TQ:
				res+=generer_tq(a,tds);
				break;
			case APPEL:
				res+=generer_appel(a,tds);
				break;
			case RET:
				res+=generer_retour(a, tds);
				break;
			default:
				
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
	
	String generer_finProg() {
		String res = "debut:\r\n" + 
				"\tCALL(main)\r\n" + 
				"\tHALT()\r\n" +
				"pile:\n";
		return res;
	}
}
