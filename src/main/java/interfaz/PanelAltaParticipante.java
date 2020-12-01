package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import logica.GestorCompetencia;

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
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        int w = getWidth();
	        int h = getHeight();
	        Color color1 = Color.decode("#2148bc");
	        Color color2 = Color.decode("#10104a");
	        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	    }
	private void inicializarComponentes() {
		//Labels
		//nombreC = GestorCompetencia.buscarCompetencia(23).getNombre();

		nombreC = GestorCompetencia.buscarNombreYParticipantes(23).getNombre();
		labelAñadir = new JLabel("<HTML><B>Añadir participante a competencia: <font color='gray'>"+nombreC+"</font color='gray'></B> </HTML>");

		labelDatosP = new JLabel("<HTML> <B> <U> Datos del participante:</U></B>");
		labelNombre = new JLabel("Nombre: ");
		labelCorreo = new JLabel("Direccion de correo electronico:");
		labelCamposOb = new JLabel("<HTML><B> <font color='gray'>Todos los campos marcados con </font color='gray'><font color='red'> (*) <font color='gray'> son obligatorios</B></font color='gray'>");
		labelOblig1 = new JLabel("<HTML><font color='red'> (*) </HTML>");
		labelOblig2 = new JLabel("<HTML><font color='red'> (*) </HTML>");
		//Buttons
		buttonCancelar = new JButton("Cancelar");
		buttonAceptar = new JButton("Aceptar");
		buttonCancelar.setPreferredSize(new Dimension(120,30));
		buttonAceptar.setPreferredSize(new Dimension(120,30));
		//Color Buttons
		buttonCancelar.setBackground(Color.decode("#112349"));
		buttonCancelar.setForeground(Color.white);
		buttonAceptar.setBackground(Color.decode("#112349"));
		buttonAceptar.setForeground(Color.white);
		//Campos
		textNombre = new JTextField(10);
		textCorreo = new JTextField(10);
		
	}
	private void armarPanel() {
		SpringLayout sLayout = new SpringLayout();
		SpringLayout s2Layout = new SpringLayout();
		this.setLayout(s2Layout);
		JPanel panelInt = new JPanel();
		panelInt.setPreferredSize(new Dimension(450,300));
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
		sLayout.putConstraint(SpringLayout.WEST,buttonCancelar,30,SpringLayout.WEST,panelInt);
		sLayout.putConstraint(SpringLayout.NORTH,buttonCancelar,35,SpringLayout.SOUTH,textCorreo);
		panelInt.add(buttonAceptar);
		sLayout.putConstraint(SpringLayout.WEST,buttonAceptar,70,SpringLayout.EAST,buttonCancelar);
		sLayout.putConstraint(SpringLayout.NORTH,buttonAceptar,0,SpringLayout.NORTH,buttonCancelar);
		panelInt.add(labelCamposOb);
		sLayout.putConstraint(SpringLayout.WEST,labelCamposOb,0,SpringLayout.WEST,labelCorreo);
		sLayout.putConstraint(SpringLayout.NORTH,labelCamposOb,10,SpringLayout.SOUTH,buttonCancelar);


		panelInt.setOpaque(false);
	}
}
















