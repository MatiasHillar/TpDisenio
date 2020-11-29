package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class PanelAltaParticipante extends JPanel {
	JLabel labelAñadir;
	JLabel labelDatosP;
	JLabel labelNombre;
	JLabel labelCorreo;
	JLabel labelCamposOb;
	JLabel labelOblig1;
	JLabel labelOblig2;
	JButton buttonCancelar;
	JButton buttonAceptar;
	JTextField textNombre;
	JTextField textCorreo;
	String nombreC= "";
	
	public PanelAltaParticipante() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	private void inicializarComponentes() {
		//Labels
		nombreC  = "";
		labelAñadir = new JLabel("<HTML>Añadir participante a competencia: <B>"+nombreC+"</B> </HTML>");
		labelDatosP = new JLabel("<HTML> <B> <U> Datos del participante:</U></B>");
		labelNombre = new JLabel("Nombre: ");
		labelCorreo = new JLabel("Direccion de correo electronico:");
		labelCamposOb = new JLabel("<HTML> Todos los campos marcados con <font color='red'> (*) <font colot='black'> son obligatorios");
		labelOblig1 = new JLabel("<HTML><font color='red'> (*) </HTML>");
		labelOblig2 = new JLabel("<HTML><font color='red'> (*) </HTML>");
		//Buttons
		buttonCancelar = new JButton("Cancelar");
		buttonAceptar = new JButton("Aceptar");
		//Campos
		textNombre = new JTextField(10);
		textCorreo = new JTextField(10);
	}
	private void armarPanel() {
		SpringLayout sLayout = new SpringLayout();
		SpringLayout s2Layout = new SpringLayout();
		this.setLayout(s2Layout);
		JPanel panelInt = new JPanel();
		panelInt.setPreferredSize(new Dimension(300,200));
		this.add(panelInt, BorderLayout.CENTER);
		panelInt.setLayout(sLayout);
		panelInt.add(labelAñadir);
		s2Layout.putConstraint(SpringLayout.WEST,panelInt,350,SpringLayout.WEST,this);
		s2Layout.putConstraint(SpringLayout.NORTH,panelInt,130,SpringLayout.NORTH,this);
		sLayout.putConstraint(SpringLayout.WEST,labelAñadir,10,SpringLayout.WEST,panelInt);
		sLayout.putConstraint(SpringLayout.NORTH,labelAñadir,10,SpringLayout.NORTH,panelInt);
		panelInt.add(labelDatosP);
		sLayout.putConstraint(SpringLayout.WEST,labelDatosP,0,SpringLayout.WEST,labelAñadir);
		sLayout.putConstraint(SpringLayout.NORTH,labelDatosP,20,SpringLayout.SOUTH,labelAñadir);
		panelInt.add(labelNombre);
		sLayout.putConstraint(SpringLayout.WEST,labelNombre,0,SpringLayout.WEST,labelAñadir);
		sLayout.putConstraint(SpringLayout.NORTH,labelNombre,40,SpringLayout.SOUTH,labelDatosP);
		panelInt.add(labelCorreo);
		sLayout.putConstraint(SpringLayout.WEST,labelCorreo,0,SpringLayout.WEST,labelAñadir);
		sLayout.putConstraint(SpringLayout.NORTH,labelCorreo,40,SpringLayout.SOUTH,labelNombre);
		panelInt.add(textNombre);
		sLayout.putConstraint(SpringLayout.WEST,textNombre,150,SpringLayout.EAST,labelNombre);
		sLayout.putConstraint(SpringLayout.NORTH,textNombre,-5,SpringLayout.NORTH,labelNombre);
		panelInt.add(textCorreo);
		sLayout.putConstraint(SpringLayout.WEST,textCorreo,0,SpringLayout.WEST,textNombre);
		sLayout.putConstraint(SpringLayout.NORTH,textCorreo,-5,SpringLayout.NORTH,labelCorreo);
		panelInt.add(buttonCancelar);
		sLayout.putConstraint(SpringLayout.WEST,textCorreo,0,SpringLayout.WEST,textNombre);
		sLayout.putConstraint(SpringLayout.NORTH,textCorreo,-5,SpringLayout.NORTH,labelCorreo);
		panelInt.add(buttonAceptar);
	}
}
















