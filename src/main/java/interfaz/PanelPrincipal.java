package interfaz;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;


public class PanelPrincipal extends PanelGenerico{

	JLabel titulo;
	JButton buttonIniciarSesion;
	JButton buttonCrearCuenta;
	JButton buttonListarCompetencias;
	JButton buttonAltaCompetencia;
	JButton buttonSalir;
	Dimension tamBotonesGen;
	PanelPrincipal(){
		super();
		inicializarComponentes();
		armarPanel();
	}
	void inicializarComponentes() {
		//Variables
		tamBotonesGen = new Dimension(200,45);
		//Labels
		titulo = new JLabel("<HTML><B><font color = 'white'>&emsp;&emsp;BIENVENIDO A <br><font size=> COMPETITION MANAGER  </B></HTML>");
		//Buttons
		buttonIniciarSesion = new JButton("Iniciar Sesion");
		buttonCrearCuenta = new JButton("Crear una cuenta nueva");
		buttonListarCompetencias = new JButton("Listar Competencias");
		buttonAltaCompetencia = new JButton("Alta de Competencia");
		buttonSalir = new JButton("Salir");
		
		buttonIniciarSesion.setPreferredSize(tamBotonesGen);
		buttonCrearCuenta.setPreferredSize(tamBotonesGen);
		buttonListarCompetencias.setPreferredSize(tamBotonesGen);
		buttonAltaCompetencia.setPreferredSize(tamBotonesGen);
		buttonSalir.setPreferredSize(tamBotonesGen);
		
		//Color buttons
		buttonIniciarSesion.setBackground(PanelGenerico.colorFondoBoton);
		buttonIniciarSesion.setForeground(PanelGenerico.colorTextoBoton);
		buttonCrearCuenta.setBackground(PanelGenerico.colorFondoBoton);
		buttonCrearCuenta.setForeground(PanelGenerico.colorTextoBoton);
		buttonListarCompetencias.setBackground(PanelGenerico.colorFondoBoton);
		buttonListarCompetencias.setForeground(PanelGenerico.colorTextoBoton);
		buttonAltaCompetencia.setBackground(PanelGenerico.colorFondoBoton);
		buttonAltaCompetencia.setForeground(PanelGenerico.colorTextoBoton);
		buttonSalir.setBackground(PanelGenerico.colorFondoBoton);
		buttonSalir.setForeground(PanelGenerico.colorTextoBoton);
		
		
		//Listeners
		ActionListener listarCompListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelListarCompetencias());
				ventana.setSize(PanelGenerico.tamListarComp);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            	}
            
        };
        buttonListarCompetencias.addActionListener(listarCompListener);
		ActionListener altaCompListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelAltaCompetencia());
				ventana.setSize(PanelGenerico.tamAltaComp);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            	}
            
        };
        buttonAltaCompetencia.addActionListener(altaCompListener);
        ActionListener iniciarSesionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelInicioSesion());
				ventana.setSize(PanelGenerico.tamIniciarSesion);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            	}
        };
        buttonIniciarSesion.addActionListener(iniciarSesionListener);
        ActionListener cuentaNuevaListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelCuentaNueva());
				ventana.setSize(PanelGenerico.tamCuentaNueva);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            	}
        };
        buttonCrearCuenta.addActionListener(cuentaNuevaListener);
        ActionListener salirListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.dispose();
            	}
        };
        buttonSalir.addActionListener(salirListener);
        
	}
	void armarPanel() {
		SpringLayout sLayout = new SpringLayout();
		this.setLayout(sLayout);
		this.add(titulo);
		sLayout.putConstraint(SpringLayout.WEST,titulo,80,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,titulo,40,SpringLayout.NORTH,this);
		this.add(buttonIniciarSesion);
		sLayout.putConstraint(SpringLayout.WEST,buttonIniciarSesion,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonIniciarSesion,90,SpringLayout.NORTH,this);
		this.add(buttonCrearCuenta);
		sLayout.putConstraint(SpringLayout.WEST,buttonCrearCuenta,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonCrearCuenta,15,SpringLayout.SOUTH,buttonIniciarSesion);
		this.add(buttonAltaCompetencia);
		sLayout.putConstraint(SpringLayout.WEST,buttonAltaCompetencia,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonAltaCompetencia,15,SpringLayout.SOUTH,buttonCrearCuenta);
		this.add(buttonListarCompetencias);	
		sLayout.putConstraint(SpringLayout.WEST,buttonListarCompetencias,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonListarCompetencias,15,SpringLayout.SOUTH,buttonAltaCompetencia);
		this.add(buttonSalir);
		sLayout.putConstraint(SpringLayout.WEST,buttonSalir,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonSalir,15,SpringLayout.SOUTH,buttonListarCompetencias);
	}
}
