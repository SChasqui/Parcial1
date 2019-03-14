package model;

public class Carta {
	
	
	private String numero;
	private String palo;
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	public Carta(String numero, String palo) {
		// TODO Auto-generated constructor stub
		this.numero = numero;
		this.palo = palo;
	}
}
