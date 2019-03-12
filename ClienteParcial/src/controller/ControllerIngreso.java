package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import communication.TCPConnection;
import communication.TCPConnection.ConnectionEvent;
import view.ViewIngreso;

public class ControllerIngreso implements ActionListener, ConnectionEvent{

	private ViewIngreso view;
	TCPConnection connection;
	
	public ControllerIngreso(ViewIngreso view) {
		this.view = view;
		initView();
	}

	public void initView() {
//		view.getBtnConectar().addActionListener(this);
//		connection = TCPConnection.getInstance(0);
//		connection.addConnectionEvent(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource().equals(view.getBtnConectar())) {
//			String ip = view.getTfIP().getText();
//			int puerto = Integer.parseInt(view.getTfPuerto().getText());
//			connection.connect(ip, puerto);
//		}
	}

	
	
	@Override
	public void onConnection() {
		//Desplegar la siguiente ventana
		System.out.println("CONECTADOS!");
		view.setVisible(false);
		
//		ViewChat chat = new ViewChat();
//		chat.setVisible(true);
		
	}

	@Override
	public void onMessage(String msj) {
		//No vamos a tomar acciones	
	}
	
	
}
