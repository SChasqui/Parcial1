package view;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControllerIngreso;


public class ViewIngreso extends JFrame{
	
	private JLabel labTitulo;
	private JLabel labImagen; 
	private JButton buttIniciar;
	
	private ControllerIngreso controller;
	
	public ViewIngreso() {
		
		super("Meńu de inicio");
		BorderLayout border = new BorderLayout();
		this.setLayout(border);
		this.setSize(385,400);
		this.setLocationRelativeTo(null);
		
		labTitulo = new JLabel("Bienvenido a Texas Holdem Poker");
		labImagen= new JLabel("");
		
		ImageIcon icon =  new ImageIcon("data/pokerImg.jpg");
		labImagen.setIcon(icon);
		
		buttIniciar = new JButton("Iniciar");
		
		this.add(labTitulo, BorderLayout.NORTH);
		this.add(labImagen, BorderLayout.CENTER);
		this.add(buttIniciar, BorderLayout.SOUTH);
		
		controller = new ControllerIngreso(this);
		
	}

	public JLabel getLabTitulo() {
		return labTitulo;
	}

	public void setLabTitulo(JLabel labTitulo) {
		this.labTitulo = labTitulo;
	}

	public JLabel getLabImagen() {
		return labImagen;
	}

	public void setLabImagen(JLabel labImagen) {
		this.labImagen = labImagen;
	}

	public JButton getButtIniciar() {
		return buttIniciar;
	}

	public void setButtIniciar(JButton buttIniciar) {
		this.buttIniciar = buttIniciar;
	}

	public ControllerIngreso getController() {
		return controller;
	}

	public void setController(ControllerIngreso controller) {
		this.controller = controller;
	}
}
