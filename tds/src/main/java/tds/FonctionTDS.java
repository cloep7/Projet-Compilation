package tds;

public class FonctionTDS extends Symbole {
	private Integer nbparam;
	private Integer nbloc;

	public FonctionTDS(String nom, String type, String cat, Integer nbparam, Integer nbloc) {
		super(nom, type, cat);
		this.nbparam = nbparam;
		this.nbloc = nbloc;
	}

	public Integer getNbparam() {
		return nbparam;
	}

	public void setNbparam(Integer nbparam) {
		this.nbparam = nbparam;
	}

	public Integer getNbloc() {
		return nbloc;
	}

	public void setNbloc(Integer nbloc) {
		this.nbloc = nbloc;
	}
	
	@Override
	public String toString() {
		return "{nom= "+ this.getNom() + "; type= "+ this.getType() + "; cat= " + this.getCat() +"; nbparam= "+ nbparam +"; nbloc= "+ nbloc + "}";
	}
}