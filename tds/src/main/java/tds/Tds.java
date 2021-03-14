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
	
	
}
