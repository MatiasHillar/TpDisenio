package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ButtonGenerico extends JButton{
	public ButtonGenerico() {
		super();
	}
	public ButtonGenerico(String s) {
		super(s);
	}
	
	
	
	@Override
    protected void paintComponent(Graphics g) {
		if(this.isEnabled()) {
			super.paintComponent(g);
			
			this.setForeground(Color.white);
			this.setBackground(Color.decode("#112349"));
			g.setColor((Color.decode("#1f376b")));
			g.drawRoundRect( getWidth()-2,getHeight()-2, getWidth()+2, getHeight()+2, 15, 15);
		}
		else if(!this.isEnabled()) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(new GradientPaint(new Point(0, 0), Color.decode("#112379"),
					new Point(0,getHeight()),Color.decode("#112349")));
			g2.fillRoundRect(0, 0, getWidth(), getHeight(),20,20);
			g2.setPaint(Color.white);
			g2.drawString(getText(),(getWidth()/4)-5,((getHeight()/2)+this.getFont().getSize()/3));
			g2.dispose();
			}
        
	}
}
