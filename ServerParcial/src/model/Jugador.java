package model;

import java.util.ArrayList;

public class Jugador {
	
	public final static String RETIRADO = "ret";
	public final static String SIGUE = "sigue";
	
	private String ID;
	private ArrayList<Carta> masoPrivado;
	private String estado;
	
	public Jugador(String ID) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		masoPrivado = new ArrayList<Carta>();
		estado = SIGUE;
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setMasoPrivado(ArrayList<Carta> masoPrivado) {
		this.masoPrivado = masoPrivado;
	}

	
	public String getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}
	
	public void añadirCartasAMaso(Carta carta1, Carta carta2) {
		masoPrivado.add(carta1);
		masoPrivado.add(carta2);
	}
	
	public ArrayList<Carta> getMasoPrivado(){
		return masoPrivado;
	}

}
