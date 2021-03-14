package tds;

public class Fonction extends Symbole {
	private int nbparam;
	private int nbloc;

	public Fonction(String nom, String type, String cat, int nbparam, int nbloc) {
		super(nom, type, cat);
		this.nbparam = nbparam;
		this.nbloc = nbloc;
	}

	public int getNbparam() {
		return nbparam;
	}

	public void setNbparam(int nbparam) {
		this.nbparam = nbparam;
	}

	public int getNbloc() {
		return nbloc;
	}

	public void setNbloc(int nbloc) {
		this.nbloc = nbloc;
	}	
}