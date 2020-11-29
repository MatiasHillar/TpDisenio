package interfaz;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel{

	
	JButton buttonListarCompetencias;
	JButton buttonAltaCompetencia;
	
	PanelPrincipal(){
		super();

		inicializarComponentes();
		armarPanel();
	}
	void inicializarComponentes() {
		//Buttons
		buttonListarCompetencias = new JButton("Listar Competencias");
		buttonAltaCompetencia = new JButton("Alta de Competencia");
		buttonListarCompetencias.setPreferredSize(new Dimension(200,50));
		buttonAltaCompetencia.setPreferredSize(new Dimension(200,50));
		buttonListarCompetencias.setBackground(Color.decode("#112349"));
		buttonListarCompetencias.setForeground(Color.white);
		buttonAltaCompetencia.setBackground(Color.decode("#112349"));
		buttonAltaCompetencia.setForeground(Color.white);
	}
	void armarPanel() {
		this.setBackground(Color.decode("#21489c"));
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.add(buttonAltaCompetencia);
		buttonAltaCompetencia.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Box.createRigidArea(new Dimension(0,15)));

		this.add(buttonListarCompetencias);	
		buttonListarCompetencias.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Box.createVerticalGlue());

		this.setBorder(BorderFactory.createEmptyBorder(150,0,150,0));

	}
}
