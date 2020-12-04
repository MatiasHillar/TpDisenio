package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import acceso.UsuarioDAOimpl;
import excepciones.UsuarioExistenteException;
import logica.Usuario;

public class PanelCuentaNueva extends PanelGenerico {
		
		JLabel labelNombre;
		JLabel labelApellido;
		JLabel labelCorreo;
		JLabel labelContraseña;
		JLabel labelPais;
		JLabel labelProvincia;
		JLabel labelLocalidad;
		JTextField campoNombre;
		JTextField campoApellido;
		JTextField campoCorreo;
		JPasswordField campoContraseña;
		JComboBox<String> comboPais;
		JComboBox<String> comboProvincia;
		JComboBox<String> comboLocalidad;
		JButton buttonCancelar;
		JButton buttonAceptar;
		
		public PanelCuentaNueva() {
			super();
			inicializarComponentes();
			armarPanel();
			}
		

		private void inicializarComponentes() {
			//Labels
			labelNombre= new JLabel("<HTML><B>Nombre:</B></HTML>");
			labelApellido= new JLabel("<HTML><B>Apellido:</B></HTML>");
			labelCorreo= new JLabel("<HTML><B>Correo <br> Electrónico:</B></HTML>");
			labelContraseña= new JLabel("<HTML><B>Contraseña:</B></HTML>");
			labelPais= new JLabel("<HTML><B>Pais:</B></HTML>");
			labelProvincia= new JLabel("<HTML><B>Provincia:</B></HTML>");
			labelLocalidad= new JLabel("<HTML><B>Localidad</B></HTML>");
			
			//Buttons
			buttonCancelar = new JButton("Cancelar");
			buttonAceptar = new JButton("Aceptar");
			buttonCancelar.setPreferredSize(new Dimension(100,30));
			buttonAceptar.setPreferredSize(new Dimension(120,30));
			
			//Color buttons
			buttonCancelar.setBackground(colorFondoBoton);
			buttonCancelar.setForeground(colorTextoBoton);
			buttonAceptar.setBackground(colorFondoBoton);
			buttonAceptar.setForeground(colorTextoBoton);
			
			//Campos
			campoNombre = new JTextField(10);
			campoApellido = new JTextField(10);
			campoCorreo = new JTextField(10);
			campoContraseña = new JPasswordField(10);
			
			//Combos
			comboPais = new JComboBox<String>();
			comboProvincia = new JComboBox<String>();
			comboLocalidad = new JComboBox<String>();
			comboPais.addItem("<Ninguno>");
			comboProvincia.addItem("<Ninguna>");
			comboLocalidad.addItem("<Ninguna>");
			
			//Listeners
			ActionListener cancelarListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
					ventana.setContentPane(new PanelPrincipal());
					ventana.setSize(PanelGenerico.tamPrincipal);
					ventana.setLocationRelativeTo(null);
					ventana.revalidate();
					ventana.repaint();
				}
			}; 
			buttonCancelar.addActionListener(cancelarListener);
			ActionListener cuentaNuevaListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Usuario u = new Usuario(campoNombre.getText().trim(),campoApellido.getText().trim()
							,campoCorreo.getText().trim(),campoContraseña.getPassword().toString());
					try {
						(new UsuarioDAOimpl()).saveOrUpdate(u);
					} catch (UsuarioExistenteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			};
			buttonAceptar.addActionListener(cuentaNuevaListener);
		}
		
		private void armarPanel() {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor= GridBagConstraints.WEST;
			gbc.fill=GridBagConstraints.BOTH;
			gbc.insets= new Insets(0,10,10,0);
			this.setLayout(new GridBagLayout());
			gbc.gridx=0;
			gbc.gridy=0;
			this.add(labelNombre,gbc);
			gbc.gridy++;
			this.add(labelApellido,gbc);
			gbc.gridy++;
			this.add(labelCorreo,gbc);
			gbc.gridy++;
			this.add(labelContraseña,gbc);
			gbc.gridy++;
			this.add(labelPais,gbc);
			gbc.gridy++;
			this.add(labelProvincia,gbc);
			gbc.gridy++;
			this.add(labelLocalidad,gbc);
			gbc.gridy++;
			this.add(buttonCancelar,gbc);
			gbc.gridx=1;
			gbc.gridy=0;
			this.add(campoNombre,gbc);
			gbc.gridy++;
			this.add(campoApellido,gbc);
			gbc.gridy++;
			this.add(campoCorreo,gbc);
			gbc.gridy++;
			this.add(campoContraseña,gbc);
			gbc.gridy++;
			this.add(comboPais,gbc);
			gbc.gridy++;
			this.add(comboProvincia,gbc);
			gbc.gridy++;
			this.add(comboLocalidad,gbc);	
			gbc.gridy++;
			this.add(buttonAceptar,gbc);
		}
		
		
}
