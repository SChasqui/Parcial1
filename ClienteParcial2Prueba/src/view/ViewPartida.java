package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerPartida;

public class ViewPartida extends JFrame{
	
	private ControllerPartida controller;
	private JLabel[] labsCardsTop;
	
	private JLabel[] labsCardsLeft;
	private JLabel[] labsCardsRigth;
	private JLabel[] labsCardsBottom;
	
	private JPanel auxPanelNorth;
	private JPanel auxPanelLeft;
	private JPanel auxPanelRigth;
	private JPanel auxPanelBottom;
	
	public ViewPartida() {
		
		super("Texas Hold'em Poker");
		BorderLayout border = new BorderLayout();
		this.setLayout(border);
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		
		auxPanelNorth = new JPanel();
		auxPanelLeft = new JPanel();
		auxPanelRigth = new JPanel();
		auxPanelBottom = new JPanel();
		
		
//		auxPanelNorth.setLayout(new FlowLayout());
		auxPanelNorth.setPreferredSize(new Dimension(400,100));
		auxPanelBottom.setPreferredSize(new Dimension(400,100));
		
		GridLayout gridLayout =  new GridLayout(2,1);
		auxPanelLeft.setLayout(gridLayout);
		auxPanelLeft.setSize(100, 400);
		auxPanelRigth.setLayout(gridLayout);
//		auxPanelLeft.setLocation(5,100);
		
		labsCardsTop = new JLabel[5];
		labsCardsLeft = new JLabel[2];
		labsCardsRigth = new JLabel[2];
		labsCardsBottom = new JLabel[2];
		
		for (int i = 0; i < labsCardsTop.length; i++) {
			labsCardsTop[i] = new JLabel("C " +i);
			labsCardsTop[i].setPreferredSize(new Dimension(50,80));
			labsCardsTop[i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			auxPanelNorth.add(labsCardsTop[i]);
		}
		for (int i = 0; i < labsCardsBottom.length; i++) {
			labsCardsLeft[i] = new JLabel("No pasa");
			labsCardsLeft[i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			labsCardsRigth[i] = new JLabel("Pasa");
			labsCardsRigth[i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			labsCardsBottom[i] = new JLabel("C" + i);
			labsCardsBottom[i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			labsCardsBottom[i].setPreferredSize(new Dimension(50,80));
			labsCardsBottom[i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			auxPanelBottom.add(labsCardsBottom[i]);
			auxPanelLeft.add(labsCardsLeft[i]);
			auxPanelRigth.add(labsCardsRigth[i]);
		}
		
		this.add(auxPanelNorth, border.NORTH);
		this.add(auxPanelBottom, border.SOUTH);
		this.add(auxPanelLeft, border.WEST);
		this.add(auxPanelRigth,border.EAST);
		
		controller = new ControllerPartida(this);
		
	}

	public ControllerPartida getController() {
		return controller;
	}

	public void setController(ControllerPartida controller) {
		this.controller = controller;
	}

	public JLabel[] getLabsCardsTop() {
		return labsCardsTop;
	}

	public void setLabsCardsTop(JLabel[] labsCardsTop) {
		this.labsCardsTop = labsCardsTop;
	}

	public JLabel[] getLabsCardsLeft() {
		return labsCardsLeft;
	}

	public void setLabsCardsLeft(JLabel[] labsCardsLeft) {
		this.labsCardsLeft = labsCardsLeft;
	}

	public JLabel[] getLabsCardsRigth() {
		return labsCardsRigth;
	}

	public void setLabsCardsRigth(JLabel[] labsCardsRigth) {
		this.labsCardsRigth = labsCardsRigth;
	}

	public JLabel[] getLabsCardsBottom() {
		return labsCardsBottom;
	}

	public void setLabsCardsBottom(JLabel[] labsCardsBottom) {
		this.labsCardsBottom = labsCardsBottom;
	}

	public JPanel getAuxPanelNorth() {
		return auxPanelNorth;
	}

	public void setAuxPanelNorth(JPanel auxPanelNorth) {
		this.auxPanelNorth = auxPanelNorth;
	}

	public JPanel getAuxPanelLeft() {
		return auxPanelLeft;
	}

	public void setAuxPanelLeft(JPanel auxPanelLeft) {
		this.auxPanelLeft = auxPanelLeft;
	}

	public JPanel getAuxPanelRigth() {
		return auxPanelRigth;
	}

	public void setAuxPanelRigth(JPanel auxPanelRigth) {
		this.auxPanelRigth = auxPanelRigth;
	}

	public JPanel getAuxPanelBottom() {
		return auxPanelBottom;
	}

	public void setAuxPanelBottom(JPanel auxPanelBottom) {
		this.auxPanelBottom = auxPanelBottom;
	}
	
	
}
