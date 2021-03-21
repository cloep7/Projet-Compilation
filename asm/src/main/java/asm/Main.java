package asm;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.arbre.Affectation;
import fr.ul.miage.arbre.Appel;
import fr.ul.miage.arbre.Bloc;
import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Ecrire;
import fr.ul.miage.arbre.Fonction;
import fr.ul.miage.arbre.Idf;
import fr.ul.miage.arbre.Inferieur;
import fr.ul.miage.arbre.Lire;
import fr.ul.miage.arbre.Multiplication;
import fr.ul.miage.arbre.Noeud;
import fr.ul.miage.arbre.Plus;
import fr.ul.miage.arbre.Prog;
import fr.ul.miage.arbre.Retour;
import fr.ul.miage.arbre.Si;
import fr.ul.miage.arbre.Superieur;
import fr.ul.miage.arbre.TantQue;
import fr.ul.miage.arbre.TxtAfficheur;
import tds.FonctionTDS;
import tds.IDFTds;
import tds.Symbole;
import tds.Tds;

public class Main {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		int choix;
		boolean again=true;
		ArrayList<Symbole> listSym = new ArrayList<Symbole>();
		Tds tds = new Tds(listSym);
		Generateur gen = new Generateur();
		while (again) {
			listSym.clear();
			System.out.println("Veuillez rentrer le numéro d'exemple dont vous voulez obtenir l'arbre, la TDS ainsi que le code ASM :  ");
			choix = sc.nextInt();
		switch (choix) {
			case 1: 
				System.out.println("Exemple 1\n==============================");
				
				Noeud prog1 = new Prog();
				prog1.ajouterUnFils(new Fonction("main"));
				
				System.out.println("==========Arbre généré==========");
				TxtAfficheur.afficher(prog1);
				
				FonctionTDS main1 = new FonctionTDS("main","void","fonction",0,0);
				listSym.add(main1);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog1, tds));
				
				generer_fichierASM("1", prog1, tds);
				System.out.println("Code ASM copié dans exemple1.asm ! Vous le retrouverez à la racine de ce projet\n");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
				break;
			case 2:
				System.out.println("Exemple 2\n==============================");
				
				Noeud prog2 = new Prog();
				prog2.ajouterUnFils(new Fonction("main"));
				System.out.println("==========Arbre généré==========");
				TxtAfficheur.afficher(prog2);
				
				FonctionTDS main2 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds i2 = new IDFTds("i", "int", "global", 10, 0, null);
				IDFTds j2 = new IDFTds("j", "int", "global", 20, 0, null);
				IDFTds k2 = new IDFTds("k", "int", "global", 0, 0, null);
				IDFTds l2 = new IDFTds("l", "int", "global", 0, 0, null);
				
				listSym.add(main2);
				listSym.add(i2);
				listSym.add(j2);
				listSym.add(k2);
				listSym.add(l2);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog2, tds));
				
				generer_fichierASM("2", prog2, tds);
				System.out.println("Code ASM copié dans exemple2.asm ! Vous le retrouverez à la racine de ce projet");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
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
				
				System.out.println("==========Arbre généré==========");
				TxtAfficheur.afficher(prog3);				
				
				FonctionTDS main3 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds i3 = new IDFTds("i", "int", "global", 10, 0, null);
				IDFTds j3 = new IDFTds("j", "int", "global", 20, 0, null);
				IDFTds k3 = new IDFTds("k", "int", "global", 0, 0, null);
				IDFTds l3 = new IDFTds("l", "int", "global", 0, 0, null);
				
				listSym.add(main3);
				listSym.add(i3);
				listSym.add(j3);
				listSym.add(k3);
				listSym.add(l3);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog3, tds));
				generer_fichierASM("3", prog3, tds);
				System.out.println("Code ASM copié dans exemple3.asm ! Vous le retrouverez à la racine de ce projet");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
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
				System.out.println("==========Arbre généré==========");
				TxtAfficheur.afficher(prog4);
				
				FonctionTDS main4 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds i4 = new IDFTds("i", "int", "global", 0, 0, null);
				IDFTds j4 = new IDFTds("j", "int", "global", 20, 0, null);
				
				listSym.add(main4);
				listSym.add(i4);
				listSym.add(j4);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog4, tds));
				generer_fichierASM("4", prog4, tds);
				System.out.println("Code ASM copié dans exemple4.asm ! Vous le retrouverez à la racine de ce projet");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
				break;
			case 5: 
				System.out.println("Exemple 5\n==============================");
				
				Noeud prog5 = new Prog();
				Fonction f5 = new Fonction("main");
				prog5.ajouterUnFils(f5);
				
				Affectation aff5 = new Affectation();
				Idf i5A = new Idf("i");
				aff5.setFilsGauche(i5A);
				aff5.setFilsDroit(new Lire());
				
				Si si5 = new Si(1);
				
				Superieur sup5 = new Superieur();
				sup5.setFilsGauche(new Idf("i"));
				sup5.setFilsDroit(new Const(10));
				
				Bloc bloc51 = new Bloc();
				Ecrire ecrire51 = new Ecrire();
				ecrire51.ajouterUnFils(new Const(1));
				bloc51.ajouterUnFils(ecrire51);
				
				Bloc bloc52 = new Bloc();
				Ecrire ecrire52 = new Ecrire();
				ecrire52.ajouterUnFils(new Const(2));
				bloc52.ajouterUnFils(ecrire52);
				
				si5.setCondition(sup5);
				si5.setBlocAlors(bloc51);
				si5.setBlocSinon(bloc52);
				
				f5.ajouterUnFils(aff5);
				f5.ajouterUnFils(si5);
				
				System.out.println("=========Arbre généré==========");
				TxtAfficheur.afficher(prog5);

				FonctionTDS main5 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds i5 = new IDFTds("i", "int", "global", 0, 0, null);
				
				listSym.add(main5);
				listSym.add(i5);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog5, tds));
				generer_fichierASM("5", prog5, tds);
				System.out.println("Code ASM copié dans exemple5.asm ! Vous le retrouverez à la racine de ce projet");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
				break;
			case 6:
				System.out.println("Exemple 6\n==============================");
				
				Noeud prog6 = new Prog();
				Fonction f6 = new Fonction("main");
				prog6.ajouterUnFils(f6);
				
				Affectation aff6 = new Affectation();
				Idf i6A = new Idf("i");
				aff6.setFilsGauche(i6A);
				aff6.setFilsDroit(new Const(0));
				
				TantQue tq6 = new TantQue(1);
				
				Inferieur inf6 = new Inferieur();
				inf6.setFilsGauche(i6A);
				inf6.setFilsDroit(new Idf("n"));
				
				Bloc bloc6 = new Bloc();
				
				Ecrire ecrire6 = new Ecrire();
				ecrire6.ajouterUnFils(i6A);
				
				Affectation aff62 = new Affectation();
				
				Plus plus6 = new Plus();
				plus6.setFilsGauche(i6A);
				plus6.setFilsDroit(new Const(1));
				
				aff62.setFilsGauche(i6A);
				aff62.setFilsDroit(plus6);
				
				bloc6.ajouterUnFils(ecrire6);
				bloc6.ajouterUnFils(aff62);
				
				tq6.setCondition(inf6);
				tq6.setBloc(bloc6);
				
				f6.ajouterUnFils(aff6);
				f6.ajouterUnFils(tq6);
				
				System.out.println("==========Arbre généré==========");
				TxtAfficheur.afficher(prog6);
				
				FonctionTDS main6 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds i6 = new IDFTds("i", "int", "global", 0, 0, null);
				IDFTds n6 = new IDFTds("n", "int", "global", 5, 0, null);
				
				listSym.add(main6);
				listSym.add(i6);
				listSym.add(n6);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog6, tds));
				generer_fichierASM("6", prog6, tds);
				System.out.println("Code ASM copié dans exemple6.asm ! Vous le retrouverez à la racine de ce projet");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
				break;
			case 7: 
				System.out.println("Exemple 7\n==============================");
				
				Noeud prog7 = new Prog();
				Fonction f7A = new Fonction("f");
				Fonction main7A = new Fonction("main");
				
				Affectation aff71 = new Affectation();
				Idf x7A = new Idf("x");
				aff71.setFilsGauche(x7A);
				aff71.setFilsDroit(new Const(1));
				
				Affectation aff72 = new Affectation();
				Idf y7A = new Idf("y");
				aff72.setFilsGauche(y7A);
				aff72.setFilsDroit(new Const(1));
				
				Affectation aff73 = new Affectation();
				Idf a7A = new Idf("a");
				aff73.setFilsGauche(a7A);
				
				Plus plus71 = new Plus();
				Idf i7A = new Idf("i");
				plus71.setFilsGauche(i7A);
				
				Plus plus72 = new Plus();
				plus72.setFilsGauche(x7A);
				plus72.setFilsDroit(y7A);
				
				plus71.setFilsDroit(plus72);
				
				aff73.setFilsDroit(plus71);
				
				f7A.ajouterUnFils(aff71);
				f7A.ajouterUnFils(aff72);
				f7A.ajouterUnFils(aff73);
				
				Appel appel7 = new Appel("f");
				appel7.ajouterUnFils(new Const(3));
				
				Ecrire ecrire7 = new Ecrire();
				ecrire7.ajouterUnFils(a7A);
				
				main7A.ajouterUnFils(appel7);
				main7A.ajouterUnFils(ecrire7);
				
				prog7.ajouterUnFils(f7A);
				prog7.ajouterUnFils(main7A);
				
				System.out.println("==========Arbre généré==========");
				TxtAfficheur.afficher(prog7);
							
				FonctionTDS main7 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds a7 = new IDFTds("a", "int", "global", 10, 0, null);
				FonctionTDS f7 = new FonctionTDS("f","void","fonction",1,2);
				IDFTds i7 = new IDFTds("i", "int", "param", 0, 0, "f");
				IDFTds x7 = new IDFTds("x", "int", "local", 0, 0, "f");
				IDFTds y7 = new IDFTds("y", "int", "local", 0, 1, "f");
				
				listSym.add(main7);
				listSym.add(a7);
				listSym.add(f7);
				listSym.add(i7);
				listSym.add(x7);
				listSym.add(y7);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog7, tds));
				generer_fichierASM("7", prog7, tds);
				System.out.println("Code ASM copié dans exemple7.asm ! Vous le retrouverez à la racine de ce projet");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
				break;
			case 8:
				System.out.println("Exemple 8\n==============================");
				
				Noeud prog8 = new Prog();
				Fonction f8A = new Fonction("f");
				Fonction main8A = new Fonction("main");
				
				Affectation aff81 = new Affectation();
				Idf x8A = new Idf("x");
				aff81.setFilsGauche(x8A);
				
				Plus plus81 = new Plus();
				Idf i8A = new Idf("i");
				plus81.setFilsGauche(i8A);
				Idf j8A = new Idf("j");
				plus81.setFilsDroit(j8A);
				
				aff81.setFilsDroit(plus81);
				
				Retour retour8 = new Retour("f");
				
				Plus plus82 = new Plus();
				plus82.setFilsGauche(x8A);
				plus82.setFilsDroit(new Const(10));
				
				retour8.setLeFils(plus82);
				
				f8A.ajouterUnFils(aff81);
				f8A.ajouterUnFils(retour8);
				
				Affectation aff82 = new Affectation();
				Idf a8A = new Idf("a");
				aff82.setFilsGauche(a8A);
				
				Appel appel8 = new Appel("f");
				appel8.ajouterUnFils(new Const(1));
				appel8.ajouterUnFils(new Const(2));
				
				aff82.setFilsDroit(appel8);
				
				Ecrire ecrire8 = new Ecrire();
				ecrire8.ajouterUnFils(a8A);
				
				main8A.ajouterUnFils(aff82);
				main8A.ajouterUnFils(ecrire8);
				
				prog8.ajouterUnFils(f8A);
				prog8.ajouterUnFils(main8A);
				
				System.out.println("==========Arbre généré==========");
				TxtAfficheur.afficher(prog8);
				
				FonctionTDS main8 = new FonctionTDS("main","void","fonction",0,0);
				IDFTds a8 = new IDFTds("a", "int", "global", 0, 0, null);
				FonctionTDS f8 = new FonctionTDS("f","int","fonction",2,1); 
				IDFTds x8 = new IDFTds("x", "int", "local", 0, 0, "f");
				IDFTds i8 = new IDFTds("i", "int", "param", 0, 0, "f");
				IDFTds j8 = new IDFTds("j", "int", "param", 0, 1, "f");
				
				listSym.add(main8);
				listSym.add(a8);
				listSym.add(f8);
				listSym.add(x8);
				listSym.add(i8);
				listSym.add(j8);
				System.out.println("==========TDS générée==========");
				tds.displayTDS();
				
				System.out.println("==========Code ASM généré==========");
				System.out.println(gen.generer_programme(prog8, tds));
				generer_fichierASM("8", prog8, tds);
				System.out.println("Code ASM copié dans exemple8.asm ! Vous le retrouverez à la racine de ce projet");
				System.out.print("Voulez-vous générer un autre exemple ? (1:oui/0:non) ");
				choix = sc.nextInt();
				if (choix==0) {
					again=false;
				}
				break;
			default:
				System.out.println("Vous ne pouvez rentrez qu'un chiffre en 1 et 8 !");
		}
		}
	}
	
	public static void generer_fichierASM(String numEx, Noeud prog, Tds tds) {
		Generateur gen = new Generateur();
        try {
            PrintWriter pw = new PrintWriter("exemple"+numEx+".asm", StandardCharsets.UTF_8);
            pw.println(gen.generer_programme(prog, tds));
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
