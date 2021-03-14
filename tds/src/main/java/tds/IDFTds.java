package tds;

public class IDFTds extends Symbole {
	private Integer val;
	private Integer rang;
	private String scope;
	
	public IDFTds(String nom, String type, String cat, Integer val, Integer rang, String scope) {
		super(nom, type, cat);
		this.val = val;
		this.rang = rang;
		this.scope = scope;
	}

	public Integer getRang() {
		return rang;
	}

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public void setRang(Integer rang) {
		this.rang = rang;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Override
	public String toString() {
		return "{nom= "+ this.getNom() + "; type= "+ this.getType() + "; cat= " + this.getCat() + "; val= " + this.getVal() +"; rang= "+ rang +"; scope= "+ scope + "}";
	}
}
