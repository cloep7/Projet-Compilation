package tds;

public class Idf extends Symbole {
	private int rang;
	private String scope;
	
	public Idf(String nom, String type, String cat, int rang, String scope) {
		super(nom, type, cat);
		this.rang = rang;
		this.scope = scope;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
