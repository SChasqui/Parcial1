package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import communication.TCPConnection;
import communication.TCPConnection.ConnectionEvent;
import view.ViewIngreso;
import view.ViewPartida;

public class ControllerIngreso implements ActionListener, ConnectionEvent{

	private ViewIngreso view;
	TCPConnection connection;
	
	public ControllerIngreso(ViewIngreso view) {
		this.view = view;
		initView();
	}

	public void initView() {
		view.getButtIniciar().addActionListener(this);
		connection = TCPConnection.getInstance(0);
		connection.addConnectionEvent(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(view.getButtIniciar())) {
			//Poner en espera
			connection.connect("127.0.0.1", 5000);
		}
	}

	
	
	@Override
	public void onConnection() {
		//Desplegar la siguiente ventana
		System.out.println("CONECTADOS!");
		view.setVisible(false);
		
		ViewPartida vistaPartida = new ViewPartida();
		vistaPartida.setVisible(true);
//		ViewChat chat = new ViewChat();
//		chat.setVisible(true);
		
	}

	@Override
	public void onMessage(String msj) {
		//No vamos a tomar acciones	
	}
	
	
}
