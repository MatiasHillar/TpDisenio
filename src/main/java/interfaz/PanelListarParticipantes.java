package interfaz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class PanelListarParticipantes extends JPanel{
	JLabel labelPartic;
	JButton buttonAgregarP;
	JButton buttonModificarP;
	JButton buttonEliminarP;
	JTable tablaPartic;
	String nombreC;
	public PanelListarParticipantes() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	private void inicializarComponentes() {
	//Labels
	nombreC="";
	labelPartic = new JLabel("<HTML>Participantes de Competencia: <B>"+nombreC+"</B> </HTML>");
	//Buttons
	buttonAgregarP = new JButton("Agregar Nuevo Participante");
	buttonModificarP = new JButton("Modificar Participante");
	buttonEliminarP = new JButton("Eliminar Participante");
	buttonAgregarP.setPreferredSize(new Dimension(240,30));
	buttonModificarP.setPreferredSize(new Dimension(240,30));
	buttonEliminarP.setPreferredSize(new Dimension(240,30));
	//Color Buttons
	buttonAgregarP.setBackground(Color.decode("#112349"));
	buttonAgregarP.setForeground(Color.white);
	buttonModificarP.setBackground(Color.decode("#112349"));
	buttonModificarP.setForeground(Color.white);
	buttonEliminarP.setBackground(Color.decode("#112349"));
	buttonEliminarP.setForeground(Color.white);
	//Tablas
	tablaPartic = new JTable();
	tablaPartic.setPreferredSize(new Dimension(824,200));
	}
	
	private void armarPanel() {
	SpringLayout sLayout = new SpringLayout();
	this.setLayout(sLayout);
	add(labelPartic);
	sLayout.putConstraint(SpringLayout.WEST,labelPartic,15,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,labelPartic,35,SpringLayout.NORTH,this);
	add(tablaPartic);
	sLayout.putConstraint(SpringLayout.WEST,tablaPartic,100,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,tablaPartic,100,SpringLayout.NORTH,this);
	add(buttonAgregarP);
	sLayout.putConstraint(SpringLayout.WEST,buttonAgregarP,292,SpringLayout.WEST,tablaPartic);
	sLayout.putConstraint(SpringLayout.NORTH,buttonAgregarP,30,SpringLayout.SOUTH,tablaPartic);
	add(buttonModificarP);
	sLayout.putConstraint(SpringLayout.WEST,buttonModificarP,0,SpringLayout.WEST,buttonAgregarP);
	sLayout.putConstraint(SpringLayout.NORTH,buttonModificarP,30,SpringLayout.SOUTH,buttonAgregarP);
	add(buttonEliminarP);
	sLayout.putConstraint(SpringLayout.WEST,buttonEliminarP,0,SpringLayout.WEST,buttonAgregarP);
	sLayout.putConstraint(SpringLayout.NORTH,buttonEliminarP,30,SpringLayout.SOUTH,buttonModificarP);
	//Colores
	this.setBackground(Color.decode("#21489c"));
	
	}
}
