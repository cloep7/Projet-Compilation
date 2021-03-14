package tds;

public class FonctionTDS extends Symbole {
	private int nbparam;
	private int nbloc;

	public FonctionTDS(String nom, String type, String cat, int nbparam, int nbloc) {
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
	
	@Override
	public String toString() {
		return "{nom= "+ this.getNom() + "; type= "+ this.getType() + "; cat= " + this.getCat() +"; nbparam= "+ nbparam +"; nbloc= "+ nbloc + "}";
	}
}