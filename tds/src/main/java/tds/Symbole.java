package tds;

public class Symbole {
	private String nom;
	private String type;
	private String cat;
	
	public Symbole(String nom, String type, String cat) {
		this.nom = nom;
		this.type = type;
		this.cat = cat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}
	
	
}
