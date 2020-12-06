package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import logica.CompetenciaDTO;
import logica.GestorCompetencia;
import logica.GestorParticipante;

public class PanelAltaParticipante extends PanelGenerico {
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
	CompetenciaDTO dtoCompetencia;
	String nombreC= "";
	
	public PanelAltaParticipante(CompetenciaDTO dtoComp) {
		super();
		dtoCompetencia=dtoComp;
		inicializarComponentes();
		armarPanel();
	}
	
	 
	private void inicializarComponentes() {
		//Labels
		nombreC = dtoCompetencia.getNombre();
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
		buttonCancelar.setBackground(colorFondoBoton);
		buttonCancelar.setForeground(colorTextoBoton);
		buttonAceptar.setBackground(colorFondoBoton);
		buttonAceptar.setForeground(colorTextoBoton);
		//Campos
		textNombre = new JTextField(10);
		textCorreo = new JTextField(10);
		//ActionListeners
		ActionListener cancelarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ParticipanteDAO.delete(SELECCIONADO);
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelListarParticipantes(dtoCompetencia));
				ventana.setSize(tamAltaComp);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
			}
		};
		buttonCancelar.addActionListener(cancelarListener);
		ActionListener aceptarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				String respuesta = "LOL";
				respuesta = GestorParticipante.saveParticipante(dtoCompetencia.getIdCompetencia(),textNombre.getText().trim(),textCorreo.getText().trim());
				JOptionPane.showMessageDialog(ventana,respuesta);
				ventana.setContentPane(new PanelListarParticipantes(GestorCompetencia.buscarCompetencia(dtoCompetencia.getIdCompetencia())));
				ventana.setSize(500,600);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
			}
		};
		buttonAceptar.addActionListener(aceptarListener);
		
	}
	private void armarPanel() {
		SpringLayout sLayout = new SpringLayout();
		SpringLayout s2Layout = new SpringLayout();
		this.setLayout(s2Layout);
		this.setLayout(sLayout);
		this.add(labelAñadir);
		s2Layout.putConstraint(SpringLayout.WEST,this,350,SpringLayout.WEST,this);
		s2Layout.putConstraint(SpringLayout.NORTH,this,130,SpringLayout.NORTH,this);
		sLayout.putConstraint(SpringLayout.WEST,labelAñadir,10,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,labelAñadir,10,SpringLayout.NORTH,this);
		this.add(labelDatosP);
		sLayout.putConstraint(SpringLayout.WEST,labelDatosP,0,SpringLayout.WEST,labelAñadir);
		sLayout.putConstraint(SpringLayout.NORTH,labelDatosP,20,SpringLayout.SOUTH,labelAñadir);
		this.add(labelNombre);
		sLayout.putConstraint(SpringLayout.WEST,labelNombre,0,SpringLayout.WEST,labelAñadir);
		sLayout.putConstraint(SpringLayout.NORTH,labelNombre,40,SpringLayout.SOUTH,labelDatosP);
		this.add(labelCorreo);
		sLayout.putConstraint(SpringLayout.WEST,labelCorreo,0,SpringLayout.WEST,labelAñadir);
		sLayout.putConstraint(SpringLayout.NORTH,labelCorreo,40,SpringLayout.SOUTH,labelNombre);
		this.add(textNombre);
		sLayout.putConstraint(SpringLayout.WEST,textNombre,150,SpringLayout.EAST,labelNombre);
		sLayout.putConstraint(SpringLayout.NORTH,textNombre,-5,SpringLayout.NORTH,labelNombre);
		this.add(textCorreo);
		sLayout.putConstraint(SpringLayout.WEST,textCorreo,0,SpringLayout.WEST,textNombre);
		sLayout.putConstraint(SpringLayout.NORTH,textCorreo,-5,SpringLayout.NORTH,labelCorreo);
		this.add(buttonCancelar);
		sLayout.putConstraint(SpringLayout.WEST,buttonCancelar,30,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonCancelar,35,SpringLayout.SOUTH,textCorreo);
		this.add(buttonAceptar);
		sLayout.putConstraint(SpringLayout.WEST,buttonAceptar,70,SpringLayout.EAST,buttonCancelar);
		sLayout.putConstraint(SpringLayout.NORTH,buttonAceptar,0,SpringLayout.NORTH,buttonCancelar);
		this.add(labelCamposOb);
		sLayout.putConstraint(SpringLayout.WEST,labelCamposOb,0,SpringLayout.WEST,labelCorreo);
		sLayout.putConstraint(SpringLayout.NORTH,labelCamposOb,10,SpringLayout.SOUTH,buttonCancelar);
	}
}
















