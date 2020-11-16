package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionCeldasGen extends DefaultTableCellRenderer{
private String tipo = "texto";
	
	private Font normal = new Font("Verdana", Font.PLAIN, 12);
	private Font bold = new Font("Verdana", Font.BOLD, 12);
	private Font subrayada = new Font("Verdana", TextAttribute.UNDERLINE_ON, 12);
	private JLabel label = new JLabel();
	public GestionCeldasGen() {
		
	}
	public GestionCeldasGen(String tipo) {
		this.tipo = tipo;
	}
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

		Color colorFondo = null;
		Color colorFondoDefault =  new Color(192,192,192);
		Color colorFondoSelected = new Color(140, 140, 140);
		if (selected) {
			this.setBackground(colorFondoDefault);
		}
		else {
			this.setBackground(Color.white);
		}
		if(tipo.equals("texto")) {
			if (focused)
				colorFondo = colorFondoSelected;
			else
				colorFondo = colorFondoDefault;
			
			this.setHorizontalAlignment(JLabel.LEFT);
			this.setText((String) value);
			this.setBackground((selected)? colorFondo:Color.WHITE);
			this.setFont(normal);
			return this;
		}
		if(tipo.equals("numero")) {
			if(focused) 
				colorFondo = colorFondoSelected;
			else
				colorFondo=colorFondoDefault;
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setText(value.toString());
			this.setForeground((selected)? new Color(255,255,255) : new Color(32,117,32));
			this.setBackground((selected)? colorFondo : Color.WHITE);
			this.setFont(bold);
			return this;
		}
		
		
		return this;
	}
}
