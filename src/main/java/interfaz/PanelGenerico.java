package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PanelGenerico extends JPanel {
	//Confien en mi, esto parece andar -Martin
	static final Dimension tamPrincipal = new Dimension(300,450);
	static final Dimension tamAltaComp = new Dimension(1000,650);
	static final Dimension tamListarComp = new Dimension(1000,575);
	static final Dimension tamVerComp = new Dimension(1000,700);
	static final Dimension tamIniciarSesion = new Dimension(300,300);
	static final Dimension tamCuentaNueva = new Dimension(300,400);
	static final Dimension tamAltaPartic = new Dimension(400,300);
	static final Color colorFondoBoton =  Color.decode("#112349");
	static final Color colorTextoBoton =  Color.white;
	
	public PanelGenerico() {
		super();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.isOpaque()) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.decode("#2148bc");
        Color color2 = Color.decode("#20205a");
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        }
    }
	
	void getComponents(Container c){

	       Component[] m = c.getComponents();

	       for(int i = 0; i < m.length; i++){

	           if(m[i].getClass().getName() == "javax.swing.JPanel")
	               m[i].setBackground(Color.decode("#2148bc"));
	           
	           if(m[i].getClass().getName() == "javax.swing.JLabel")
	               m[i].setBackground(Color.decode("#2148bc"));
	           if(m[i].getClass().getName() == "javax.swing.JButton") {
	               m[i].setForeground(Color.white);
	        	   m[i].setBackground(Color.decode("#102a63"));}
	           
	           if(c.getClass().isInstance(m[i]));
	               getComponents((Container)m[i]);
	       }
	}
}
