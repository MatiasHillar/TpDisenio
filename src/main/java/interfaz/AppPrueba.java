package interfaz;


import java.util.ArrayList;
import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import logica.GestorCompetencia;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;


public class AppPrueba {
	public static void main(String[] args) {
		//UIManager.put("nimbusBlueGrey",Color.decode("#5693f5"));
		UIManager.put("nimbusScrollBar:\"ScrollBar.button\".size",0);
		UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(Color.decode("#24248f")));
		UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(Color.decode("#24248f")));
		UIManager.put("ScrollBar.highlight", new ColorUIResource(Color.decode("#5693f5")));
		UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(Color.decode("#24248f")));
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
		
		JFrame ventana = new JFrame();	
		ventana.setContentPane(new PanelPrincipal());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.pack();
		ventana.setSize(PanelGenerico.tamPrincipal);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		/*FUNCIONES PARA LAS VENTANAS
		 * ventana.setContentPane(new PanelListarParticipantes(GestorCompetencia.buscarNombreYParticipantes(23)));
		 * 
		 * 
		 * 
		 */
	}
}
