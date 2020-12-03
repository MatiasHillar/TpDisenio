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
	JButton iniciarSesion;
	JButton crearCuenta;
	JButton buttonListarCompetencias;
	JButton buttonAltaCompetencia;
	PanelPrincipal(){
		super();
		inicializarComponentes();
		armarPanel();
	}
	void inicializarComponentes() {
		//Labels
		titulo = new JLabel("<HTML><B><font color = 'white'>&emsp;&emsp;BIENVENIDO A <br><font size=> COMPETITION MANAGER  </B></HTML>");
		//Buttons
		iniciarSesion = new JButton("Iniciar Sesion");
		crearCuenta = new JButton("Crear una cuenta nueva");
		iniciarSesion.setPreferredSize(new Dimension(200,50));
		crearCuenta.setPreferredSize(new Dimension(200,50));
		buttonListarCompetencias = new JButton("Listar Competencias");
		buttonAltaCompetencia = new JButton("Alta de Competencia");
		buttonListarCompetencias.setPreferredSize(new Dimension(200,50));
		buttonAltaCompetencia.setPreferredSize(new Dimension(200,50));
		//Color buttons
		iniciarSesion.setBackground(Color.decode("#112349"));
		iniciarSesion.setForeground(Color.white);
		crearCuenta.setBackground(Color.decode("#112349"));
		crearCuenta.setForeground(Color.white);
		buttonListarCompetencias.setBackground(Color.decode("#112349"));
		buttonListarCompetencias.setForeground(Color.white);
		buttonAltaCompetencia.setBackground(Color.decode("#112349"));
		buttonAltaCompetencia.setForeground(Color.white);
		//Listeners
		ActionListener listarCompListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelListarCompetencias());
				ventana.setSize(new Dimension(1000,650));
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
				ventana.setSize(new Dimension(1000,650));
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
				ventana.setSize(new Dimension(300,300));
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            	}
        };
        iniciarSesion.addActionListener(iniciarSesionListener);
        ActionListener cuentaNuevaListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelCuentaNueva());
				ventana.setSize(new Dimension(300,400));
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            	}
        };
        crearCuenta.addActionListener(cuentaNuevaListener);
	}
	void armarPanel() {
		SpringLayout sLayout = new SpringLayout();
		this.setLayout(sLayout);
		this.add(titulo);
		sLayout.putConstraint(SpringLayout.WEST,titulo,80,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,titulo,40,SpringLayout.NORTH,this);
		this.add(iniciarSesion);
		sLayout.putConstraint(SpringLayout.WEST,iniciarSesion,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,iniciarSesion,90,SpringLayout.NORTH,this);
		this.add(crearCuenta);
		sLayout.putConstraint(SpringLayout.WEST,crearCuenta,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,crearCuenta,20,SpringLayout.SOUTH,iniciarSesion);
		this.add(buttonAltaCompetencia);
		sLayout.putConstraint(SpringLayout.WEST,buttonAltaCompetencia,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonAltaCompetencia,20,SpringLayout.SOUTH,crearCuenta);
		this.add(buttonListarCompetencias);	
		sLayout.putConstraint(SpringLayout.WEST,buttonListarCompetencias,45,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,buttonListarCompetencias,20,SpringLayout.SOUTH,buttonAltaCompetencia);
	}
}
