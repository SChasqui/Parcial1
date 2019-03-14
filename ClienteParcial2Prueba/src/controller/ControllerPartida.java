package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import communication.TCPConnection;
import communication.TCPConnection.ConnectionEvent;
import model.Partida;
import view.ViewPartida;

public class ControllerPartida implements ActionListener, ConnectionEvent{
	private ViewPartida view;
	TCPConnection connection;
	private Partida partida;
	
	public ControllerPartida(ViewPartida view) {
		this.view = view;
		initView();
	}
	
	public void initView() {
//		view.getBtnEnviar().addActionListener(this);
		connection = TCPConnection.getInstance();
		System.out.println("Añadiendo listener");
		connection.addConnectionEvent(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource().equals(view.getBtnEnviar())) {
//			connection.sendMessage( view.getTfEntrada().getText() );
//			view.getTaMensajes().append(view.getTfEntrada().getText() + "\n");
//			view.getTfEntrada().setText("");
//		}
	}

	@Override
	public void onConnection() {
		//No lo tenemos que manejar		
	}

	@Override
	public void onMessage(String msj) {
		System.out.println(">>ControllerChat: " + msj);
		String[] stringSplit = msj.split("-");
		if(stringSplit[0].equals("reparticion")) {
			partida = new Partida(stringSplit[1], stringSplit[2]);
			view.getLabsCardsBottom()[0].setText(stringSplit[1]);
			view.getLabsCardsBottom()[1].setText(stringSplit[2]);
		}else if(stringSplit[0].equals("masopublico")) {
			view.getLabsCardsTop()[0].setText(stringSplit[1]);
			view.getLabsCardsTop()[1].setText(stringSplit[2]);
			view.getLabsCardsTop()[2].setText(stringSplit[3]);
			view.getLabsCardsTop()[3].setText(stringSplit[4]);
			view.getLabsCardsTop()[4].setText(stringSplit[5]);
		}
//		view.getTaMensajes().append(msj+"\n");
	}

}