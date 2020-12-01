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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelPrincipal extends JPanel{

	
	JButton buttonListarCompetencias;
	JButton buttonAltaCompetencia;
	PanelPrincipal(){
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
		//Listeners
		ActionListener listarCompListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelListarCompetencias());
				ventana.revalidate();
				ventana.repaint();
            	}
            
        };
        buttonListarCompetencias.addActionListener(listarCompListener);
		ActionListener altaCompListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelAltaCompetencia());
				ventana.revalidate();
				ventana.repaint();
            	}
            
        };
        buttonAltaCompetencia.addActionListener(altaCompListener);
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
