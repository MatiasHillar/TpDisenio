package interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logica.Deporte;
import logica.GestorDeporte;
import logica.GestorLugarRealizacion;

public class PanelAgregarLugar extends PanelGenerico{
	ButtonGenerico botonAceptar;
	ButtonGenerico botonCancelar;
	ButtonGenerico botonQuitarDeporte;
	ButtonGenerico botonAgregarDeporte;
	JList<String> listaDeportesAgregados;
	JList<String> listaDeportesNoAgregados;
	JLabel labelNombre;
	JTextField fieldNombre;
	JLabel labelAgregarDeportes;
	JLabel deportesAgregados;
	JLabel deportesNoAgregados;
	JSplitPane splitVertical;
	JScrollPane scrollListaAgregados;
	JScrollPane scrollListaNoAgregados;
	ArrayList<Deporte> deportes;
	DefaultListModel<String> modeloAgregados;
	DefaultListModel<String> modeloNoAgregados;
	
	public PanelAgregarLugar() {
		super();
		inicializarComponentes();
		armarPanel();
	}

	private void inicializarComponentes() {
		
		deportes = GestorDeporte.buscarTodos();
		//Buttons
		botonCancelar = new ButtonGenerico("Cancelar");
		botonCancelar.setPreferredSize(new Dimension(130,40));
		botonAceptar = new ButtonGenerico("Aceptar");
		botonAceptar.setPreferredSize(new Dimension(130,40));
		botonAgregarDeporte = new ButtonGenerico("Agregar");
		botonAgregarDeporte.setPreferredSize(new Dimension(100,30));
		botonQuitarDeporte = new ButtonGenerico("Quitar");
		botonQuitarDeporte.setPreferredSize(new Dimension(100,30));
		 
		 //Color buttons
		botonCancelar.setBackground(colorFondoBoton);
		botonCancelar.setForeground(colorTextoBoton);
		botonAceptar.setBackground(colorFondoBoton);
		botonAceptar.setForeground(colorTextoBoton);
		botonAgregarDeporte.setBackground(colorFondoBoton);
		botonAgregarDeporte.setForeground(colorTextoBoton);
		botonAgregarDeporte.setEnabled(false);
		botonQuitarDeporte.setBackground(colorFondoBoton);
		botonQuitarDeporte.setForeground(colorTextoBoton);
		botonQuitarDeporte.setEnabled(false);
		
		
		//Labels	
		labelNombre = new JLabel("Nombre:");
		deportesAgregados = new JLabel("Deportes agregados:");
		deportesNoAgregados = new JLabel("Deportes sin agregar:");
		labelAgregarDeportes = new JLabel("Agregar Deportes");
		
		// LIST MODELS
		modeloAgregados = new DefaultListModel<String>();
		modeloNoAgregados = new DefaultListModel<String>();
		
		//PASO LOS DEPORTES A ArrayList String
		ArrayList<String> deporteStrings = new ArrayList<String>();
		for(Deporte d: deportes) {
			deporteStrings.add(d.getNombreDeporte());
		}
		
		modeloNoAgregados.addAll(deporteStrings);
		
		// Listas
		listaDeportesNoAgregados = new JList<String>(modeloNoAgregados);
		listaDeportesAgregados = new JList<String>(modeloAgregados);

		
		//LISTENERS
		listaDeportesNoAgregados.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!((JList) e.getSource()).isSelectionEmpty()) {
					listaDeportesAgregados.clearSelection();
					botonQuitarDeporte.setEnabled(true);
					botonAgregarDeporte.setEnabled(false);
				}
				
			}
			
		});
			
		listaDeportesAgregados.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!((JList) e.getSource()).isSelectionEmpty()) {
					listaDeportesNoAgregados.clearSelection();
					botonAgregarDeporte.setEnabled(true);
					botonQuitarDeporte.setEnabled(false);
				
				}
			}
			
		});
		
		
		botonAgregarDeporte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modeloAgregados.addElement(listaDeportesNoAgregados.getSelectedValue());
				modeloNoAgregados.removeElement(listaDeportesNoAgregados.getSelectedValue());
			}
			
		});
		
		
		
		botonQuitarDeporte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modeloNoAgregados.addElement(listaDeportesAgregados.getSelectedValue());
				modeloAgregados.removeElement(listaDeportesAgregados.getSelectedValue());
			}
			
		});
		
	
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> deportesAgregados = new ArrayList<String>();
				for(int i=0; i<modeloAgregados.getSize(); i++) 
					deportesAgregados.add(modeloAgregados.get(i));
				
				//String mensaje = GestorLugarRealizacion.save(fieldNombre.getText().trim(), deportesAgregados);
				
				/*if(mensaje == "exito") 
					mensajeExito();
				else
					mensajeError(mensaje);*/
				
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.setSize(tamPrincipal);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		
		
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.setSize(tamPrincipal);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		
		
	}
	
	private void armarPanel() {
		SpringLayout sLayout = new SpringLayout();
		this.setLayout(sLayout);
		add(labelNombre);
		sLayout.putConstraint(SpringLayout.WEST,labelNombre,20,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,labelNombre,30,SpringLayout.NORTH,this);
		add(labelAgregarDeportes);
		sLayout.putConstraint(SpringLayout.WEST,labelAgregarDeportes,20,SpringLayout.WEST,labelNombre);
		sLayout.putConstraint(SpringLayout.NORTH,labelAgregarDeportes,30,SpringLayout.NORTH,labelNombre);
		add(deportesAgregados);
		sLayout.putConstraint(SpringLayout.WEST,deportesAgregados,20,SpringLayout.WEST,labelAgregarDeportes);
		sLayout.putConstraint(SpringLayout.NORTH,deportesAgregados,30,SpringLayout.NORTH,labelAgregarDeportes);
		add(deportesNoAgregados);
		sLayout.putConstraint(SpringLayout.WEST,deportesNoAgregados,20,SpringLayout.WEST,deportesAgregados);
		sLayout.putConstraint(SpringLayout.NORTH,deportesNoAgregados,30,SpringLayout.NORTH,deportesAgregados);
		add(listaDeportesNoAgregados);
		add(listaDeportesAgregados);
	}

	
	private void mensajeExito() {
		JOptionPane.showMessageDialog(this,"Lugar de realizacion guardado con exito!");
	}
	private void mensajeError(String error) {
		JOptionPane.showMessageDialog(this,error);
	}
	
}