package asm;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.arbre.Affectation;
import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Ecrire;
import fr.ul.miage.arbre.Fonction;
import fr.ul.miage.arbre.Idf;
import fr.ul.miage.arbre.Lire;
import fr.ul.miage.arbre.Multiplication;
import fr.ul.miage.arbre.Noeud;
import fr.ul.miage.arbre.Plus;
import fr.ul.miage.arbre.Prog;
import fr.ul.miage.arbre.TxtAfficheur;
import tds.FonctionTDS;
import tds.IDFTds;
import tds.Symbole;
import tds.Tds;

public class Main {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		ArrayList<Symbole> listSym = new ArrayList<Symbole>();
		Tds tds = new Tds(listSym);
		
		switch (choix) {
			case 1: 
				System.out.println("Exemple 1\n==============================");
				
				Noeud prog1 = new Prog();
				prog1.ajouterUnFils(new Fonction("main"));
				TxtAfficheur.afficher(prog1);
				
				FonctionTDS main1 = new FonctionTDS("main","void","fonction",null,null);
				listSym.add(main1);
				tds.displayTDS();
				
				break;
			case 2:
				System.out.println("Exemple 2\n==============================");
				
				Noeud prog2 = new Prog();
				prog2.ajouterUnFils(new Fonction("main"));
				TxtAfficheur.afficher(prog2);
				
				FonctionTDS main2 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds i2 = new IDFTds("i", "int", "global", 10, null, null);
				IDFTds j2 = new IDFTds("j", "int", "global", 20, null, null);
				IDFTds k2 = new IDFTds("k", "int", "global", null, null, null);
				IDFTds l2 = new IDFTds("l", "int", "global", null, null, null);
				
				listSym.add(main2);
				listSym.add(i2);
				listSym.add(j2);
				listSym.add(k2);
				listSym.add(l2);
				tds.displayTDS();
				break;
			case 3: 
				System.out.println("Exemple 3\n==============================");
				
				Noeud prog3 = new Prog();
				Fonction f3 = new Fonction("main");
				prog3.ajouterUnFils(f3);
				
				Affectation aff31 = new Affectation();
				Idf k3A = new Idf("k");
				Const c31 = new Const(2);
				aff31.setFilsGauche(k3A);
				aff31.setFilsDroit(c31);
				
				Affectation aff32 = new Affectation();
				Idf l3A = new Idf("l");
				aff32.setFilsGauche(l3A);
				
				Plus plus3 = new Plus();
				Idf i3A = new Idf("i");
				plus3.setFilsGauche(i3A);
				
				Multiplication mul3 = new Multiplication();
				Const c32 = new Const(3);
				Idf j3A = new Idf("j");
				mul3.setFilsGauche(c32);
				mul3.setFilsDroit(j3A);
				
				plus3.setFilsDroit(mul3);
				aff32.setFilsDroit(plus3);
				f3.ajouterUnFils(aff31);
				f3.ajouterUnFils(aff32);
				
				TxtAfficheur.afficher(prog3);				
				
				FonctionTDS main3 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds i3 = new IDFTds("i", "int", "global", 10, null, null);
				IDFTds j3 = new IDFTds("j", "int", "global", 20, null, null);
				IDFTds k3 = new IDFTds("k", "int", "global", null, null, null);
				IDFTds l3 = new IDFTds("l", "int", "global", null, null, null);
				
				listSym.add(main3);
				listSym.add(i3);
				listSym.add(j3);
				listSym.add(k3);
				listSym.add(l3);
				tds.displayTDS();
				break;
			case 4:
				System.out.println("Exemple 4\n==============================");
				
				Noeud prog4 = new Prog();
				Fonction f4 = new Fonction("main");
				prog4.ajouterUnFils(f4);
				
				Affectation aff4 = new Affectation();
				Idf i4A = new Idf("i");
				aff4.setFilsGauche(i4A);
				aff4.setFilsDroit(new Lire());
				
				Ecrire ecrire4 = new Ecrire();
				
				Plus plus4 = new Plus();
				plus4.setFilsGauche(i4A);
				plus4.setFilsDroit(new Idf("j"));
				
				ecrire4.ajouterUnFils(plus4);
				
				f4.ajouterUnFils(aff4);
				f4.ajouterUnFils(ecrire4);
				TxtAfficheur.afficher(prog4);
				
				break;
			case 5: 
				System.out.println("Exemple 5");
				break;
			case 6:
				System.out.println("Exemple 6");
				break;
			case 7: 
				System.out.println("Exemple 7");
				break;
			case 8:
				System.out.println("Exemple 8");
				break;
		}
	}
}