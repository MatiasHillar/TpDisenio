package interfaz;


import java.util.ArrayList;
import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import logica.GestorCompetencia;

import javax.swing.UnsupportedLookAndFeelException;


public class AppPrueba {
	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
		ventana.setContentPane(new PanelVerCompetencia(GestorCompetencia.buscarNombreYParticipantes(26)));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.pack();
		ventana.setSize(1024,655);
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
