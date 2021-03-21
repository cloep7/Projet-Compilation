package tds;

import java.util.ArrayList;

public class Tds {
	private ArrayList<Symbole> tds;

	public Tds(ArrayList<Symbole> tds) {
		this.tds = tds;
	}

	public ArrayList<Symbole> getTds() {
		return tds;
	}

	public void setTds(ArrayList<Symbole> tds) {
		this.tds = tds;
	}
	
	public void displayTDS() {
		for (int i=0 ; i<this.tds.size() ; i++) {
			System.out.println(tds.get(i).toString());
		}
	}
	
	public FonctionTDS getFuncByName(String nom) {
		for (int i=0 ; i<this.tds.size() ; i++) {
			if (this.tds.get(i) instanceof FonctionTDS) {
				if (this.tds.get(i).getNom().equals(nom)) {
					return (FonctionTDS) this.tds.get(i);
				}
			}
		}
		return null;
	}
	
	public IDFTds getIdfByName(String nom ) {
		for (int i=0 ; i<this.tds.size() ; i++) {
			if (this.tds.get(i) instanceof IDFTds) {
				if (this.tds.get(i).getNom().equals(nom)) {
					return (IDFTds) this.tds.get(i);
				}
			}
		}
		return null;
	}
	
	
}
