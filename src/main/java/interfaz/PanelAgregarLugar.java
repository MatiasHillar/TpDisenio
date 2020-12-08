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
		botonAgregarDeporte = new ButtonGenerico("Agregar ->");
		botonAgregarDeporte.setPreferredSize(new Dimension(100,30));
		botonQuitarDeporte = new ButtonGenerico("<- Quitar");
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
		
		//fields
		fieldNombre = new JTextField();
		fieldNombre.setPreferredSize(new Dimension(200, 30));
		
		//Labels	
		labelNombre = new JLabel("Nombre:");
		labelNombre.setPreferredSize(new Dimension(80,30));
		deportesAgregados = new JLabel("Deportes agregados:");
		deportesNoAgregados = new JLabel("Deportes sin agregar:");
		labelAgregarDeportes = new JLabel("<HTML><B><U> Agregar deportes </U></B></HTML>");
		
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
		scrollListaNoAgregados = new JScrollPane(listaDeportesNoAgregados);
		scrollListaNoAgregados.setPreferredSize(new Dimension(175,300));
		scrollListaNoAgregados.setMinimumSize((new Dimension(175,300)));
		scrollListaNoAgregados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollListaNoAgregados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		listaDeportesAgregados = new JList<String>(modeloAgregados);
		scrollListaAgregados = new JScrollPane(listaDeportesAgregados);
		scrollListaAgregados.setPreferredSize(new Dimension(175,300));
		scrollListaAgregados.setMinimumSize((new Dimension(175,300)));
		scrollListaAgregados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollListaAgregados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		splitVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollListaNoAgregados, scrollListaAgregados);
		splitVertical.setPreferredSize(new Dimension(350,300));
		splitVertical.setOneTouchExpandable(false);
		splitVertical.setEnabled(false);
		//LISTENERS
		listaDeportesNoAgregados.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!((JList) e.getSource()).isSelectionEmpty()) {
					listaDeportesAgregados.clearSelection();
					botonQuitarDeporte.setEnabled(false);
					botonAgregarDeporte.setEnabled(true);
				}
				
			}
			
		});
			
		listaDeportesAgregados.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!((JList) e.getSource()).isSelectionEmpty()) {
					listaDeportesNoAgregados.clearSelection();
					botonAgregarDeporte.setEnabled(false);
					botonQuitarDeporte.setEnabled(true);
				
				}
			}
			
		});
		
		
		botonAgregarDeporte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			
				modeloAgregados.addElement(listaDeportesNoAgregados.getSelectedValue());
				modeloNoAgregados.removeElement(listaDeportesNoAgregados.getSelectedValue());
				listaDeportesNoAgregados.clearSelection();
				botonAgregarDeporte.setEnabled(false);
			}
			
		});
		
		
		
		botonQuitarDeporte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				
				modeloNoAgregados.addElement(listaDeportesAgregados.getSelectedValue());
				modeloAgregados.removeElement(listaDeportesAgregados.getSelectedValue());
				listaDeportesAgregados.clearSelection();
				botonQuitarDeporte.setEnabled(false);
			}
			
		});
		
	
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> deportesAgregados = new ArrayList<String>();
				for(int i=0; i<modeloAgregados.getSize(); i++) 
					deportesAgregados.add(modeloAgregados.get(i));
				System.out.println(deportesAgregados.size());
				System.out.println(fieldNombre.getText().trim());
				
				String mensaje = GestorLugarRealizacion.save(fieldNombre.getText().trim(), deportesAgregados);
				
				if(mensaje == "exito") {
					mensajeExito();
					JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
					ventana.setContentPane(new PanelPrincipal());
					ventana.setSize(tamPrincipal);
					ventana.setLocationRelativeTo(null);
					ventana.revalidate();
					ventana.repaint();
				}
					
				else
					mensajeError(mensaje);
				
			
				
			
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
		sLayout.putConstraint(SpringLayout.WEST,labelNombre,50,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,labelNombre,30,SpringLayout.NORTH,this);
		
		
		add(fieldNombre);
		sLayout.putConstraint(SpringLayout.WEST, fieldNombre, 30, SpringLayout.EAST, labelNombre);
		sLayout.putConstraint(SpringLayout.NORTH, fieldNombre, 30, SpringLayout.NORTH, this);
		
		add(labelAgregarDeportes);
		sLayout.putConstraint(SpringLayout.WEST,labelAgregarDeportes,170,SpringLayout.WEST,this);
		sLayout.putConstraint(SpringLayout.NORTH,labelAgregarDeportes,30,SpringLayout.SOUTH,labelNombre);
		add(deportesNoAgregados);
		
		sLayout.putConstraint(SpringLayout.WEST,deportesNoAgregados,-100,SpringLayout.WEST,labelAgregarDeportes);
		sLayout.putConstraint(SpringLayout.NORTH,deportesNoAgregados,30,SpringLayout.NORTH,labelAgregarDeportes);
		
		add(deportesAgregados);
		sLayout.putConstraint(SpringLayout.WEST,deportesAgregados,180,SpringLayout.WEST,deportesNoAgregados);
		sLayout.putConstraint(SpringLayout.NORTH,deportesAgregados,0,SpringLayout.NORTH,deportesNoAgregados);
		
		
		add(splitVertical);
		sLayout.putConstraint(SpringLayout.WEST, splitVertical, 40, SpringLayout.WEST, this);
		sLayout.putConstraint(SpringLayout.NORTH, splitVertical, 5, SpringLayout.SOUTH, deportesNoAgregados);
		
		
		add(botonAgregarDeporte);
		sLayout.putConstraint(SpringLayout.WEST, botonAgregarDeporte, 22, SpringLayout.WEST, splitVertical);
		sLayout.putConstraint(SpringLayout.NORTH, botonAgregarDeporte, 10, SpringLayout.SOUTH, splitVertical);
		
		add(botonQuitarDeporte);
		sLayout.putConstraint(SpringLayout.EAST, botonQuitarDeporte, -22, SpringLayout.EAST, splitVertical);
		sLayout.putConstraint(SpringLayout.NORTH, botonQuitarDeporte, 10, SpringLayout.SOUTH, splitVertical);
		
		
		
		add(botonCancelar);
		sLayout.putConstraint(SpringLayout.SOUTH, botonCancelar, -20, SpringLayout.SOUTH, this);
		sLayout.putConstraint(SpringLayout.WEST, botonCancelar, 20, SpringLayout.WEST, this);
		
		add(botonAceptar);
		sLayout.putConstraint(SpringLayout.SOUTH, botonAceptar, -20, SpringLayout.SOUTH, this);
		sLayout.putConstraint(SpringLayout.EAST, botonAceptar, -20, SpringLayout.EAST, this);
		
		
	}

	
	private void mensajeExito() {
		JOptionPane.showMessageDialog(this,"Lugar de realizacion guardado con exito!");
	}
	private void mensajeError(String error) {
		JOptionPane.showMessageDialog(this,error);
	}
	
}