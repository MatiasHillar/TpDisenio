package interfaz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import logica.GestorUsuario;
import logica.Usuario;

public class PanelListarCompetencias extends JPanel {
	
	JLabel labelCompetencias;
	JLabel labelNombre;
	JLabel labelDep;
	JLabel labelMod;
	JLabel labelEstado;
	JButton buttonBuscar;
	JButton buttonLimpiar;
	JButton buttonCancelar;
	JButton buttonCrearComp;
	JButton buttonVerComp;
	JComboBox<String> comboMod;
	JComboBox<String> comboDep;
	JComboBox<String> comboEstado;
	JTextField textfNombre;
	JTable tablaCompetencias;
	public PanelListarCompetencias() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	private void inicializarComponentes() {
		//Labels
		labelCompetencias = new JLabel("Competencias creadas por el usuario: "+GestorUsuario.usuario_autenticado);
		labelNombre = new JLabel("Nombre: ");
		labelDep = new JLabel("Deporte: ");
		labelMod = new JLabel("Modalidad: ");
		labelEstado = new JLabel("Estado: ");
		//Buttons
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setPreferredSize(new Dimension(150,30));
		buttonLimpiar = new JButton("Limpiar Filtros");
		buttonLimpiar.setPreferredSize(new Dimension(150,30));
		buttonCancelar = new JButton("Cancelar");
		buttonCrearComp = new JButton("Crear Nueva Competencia");
		buttonVerComp = new JButton("Ver Competencia");
		buttonCancelar.setPreferredSize(new Dimension(270,30));
		buttonCrearComp.setPreferredSize(new Dimension(270,30));
		buttonVerComp.setPreferredSize(new Dimension(270,30));
		
		//Color Buttons
		buttonBuscar.setBackground(Color.decode("#112349"));
		buttonBuscar.setForeground(Color.white);
		buttonLimpiar.setBackground(Color.decode("#112349"));
		buttonLimpiar.setForeground(Color.white);
		buttonCancelar.setBackground(Color.decode("#112349"));
		buttonCancelar.setForeground(Color.white);
		buttonCrearComp.setBackground(Color.decode("#112349"));
		buttonCrearComp.setForeground(Color.white);
		buttonVerComp.setBackground(Color.decode("#112349"));
		buttonVerComp.setForeground(Color.white);
		
		//Campos y comboboxes
		comboDep = new JComboBox();
		comboMod = new JComboBox();
		comboEstado = new JComboBox();
		comboDep.setPreferredSize(new Dimension(125,30));
		comboMod.setPreferredSize(new Dimension(125,30));
		comboEstado.setPreferredSize(new Dimension(125,30));
		textfNombre = new JTextField(10);
		//Tablas
		tablaCompetencias = new JTable();
		tablaCompetencias.setPreferredSize(new Dimension(900,270));
	}
	private void armarPanel() {
	this.setBackground(Color.decode("#21489c"));
	SpringLayout sLayout = new SpringLayout();
	this.setLayout(sLayout);
	add(labelCompetencias);
	sLayout.putConstraint(SpringLayout.WEST,labelCompetencias,30,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,labelCompetencias,10,SpringLayout.NORTH,this);
	add(labelNombre);
	sLayout.putConstraint(SpringLayout.WEST,labelNombre,170,SpringLayout.WEST,labelCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,labelNombre,40,SpringLayout.NORTH,labelCompetencias);
	add(labelDep);
	sLayout.putConstraint(SpringLayout.WEST,labelDep,170,SpringLayout.WEST,labelCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,labelDep,35,SpringLayout.SOUTH,labelNombre);
	add(labelMod);
	sLayout.putConstraint(SpringLayout.WEST,labelMod,30,SpringLayout.EAST,textfNombre);
	sLayout.putConstraint(SpringLayout.NORTH,labelMod,0,SpringLayout.NORTH,labelNombre);
	add(labelEstado);
	sLayout.putConstraint(SpringLayout.WEST,labelEstado,0,SpringLayout.WEST,labelMod);
	sLayout.putConstraint(SpringLayout.NORTH,labelEstado,0,SpringLayout.NORTH,labelDep);
	add(buttonBuscar);
	sLayout.putConstraint(SpringLayout.WEST,buttonBuscar,30,SpringLayout.EAST,comboMod);
	sLayout.putConstraint(SpringLayout.NORTH,buttonBuscar,0,SpringLayout.NORTH,comboMod);
	add(buttonLimpiar);
	sLayout.putConstraint(SpringLayout.WEST,buttonLimpiar,0,SpringLayout.WEST,buttonBuscar);
	sLayout.putConstraint(SpringLayout.NORTH,buttonLimpiar,0,SpringLayout.NORTH,comboDep);
	add(comboDep);
	sLayout.putConstraint(SpringLayout.WEST,comboDep,20,SpringLayout.EAST,labelDep);
	sLayout.putConstraint(SpringLayout.SOUTH,comboDep,23,SpringLayout.NORTH,labelDep);
	add(comboMod);
	sLayout.putConstraint(SpringLayout.WEST,comboMod,0,SpringLayout.WEST,comboEstado);
	sLayout.putConstraint(SpringLayout.SOUTH,comboMod,23,SpringLayout.NORTH,labelMod);
	add(comboEstado);
	sLayout.putConstraint(SpringLayout.WEST,comboEstado,30,SpringLayout.EAST,labelEstado);
	sLayout.putConstraint(SpringLayout.SOUTH,comboEstado,23,SpringLayout.NORTH,labelEstado);
	add(textfNombre);
	sLayout.putConstraint(SpringLayout.WEST,textfNombre,20,SpringLayout.EAST,labelNombre);
	sLayout.putConstraint(SpringLayout.NORTH,textfNombre,35,SpringLayout.NORTH,labelCompetencias);
	add(tablaCompetencias);
	sLayout.putConstraint(SpringLayout.WEST,tablaCompetencias,55,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,tablaCompetencias,80,SpringLayout.SOUTH,labelDep);
	add(buttonCancelar);
	sLayout.putConstraint(SpringLayout.WEST,buttonCancelar,15,SpringLayout.WEST,tablaCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,buttonCancelar,20,SpringLayout.SOUTH,tablaCompetencias);
	add(buttonCrearComp);
	sLayout.putConstraint(SpringLayout.WEST,buttonCrearComp,30,SpringLayout.EAST,buttonCancelar);
	sLayout.putConstraint(SpringLayout.NORTH,buttonCrearComp,20,SpringLayout.SOUTH,tablaCompetencias);
	add(buttonVerComp);
	sLayout.putConstraint(SpringLayout.EAST,buttonVerComp,-15,SpringLayout.EAST,tablaCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,buttonVerComp,20,SpringLayout.SOUTH,tablaCompetencias);
	}
	
}































