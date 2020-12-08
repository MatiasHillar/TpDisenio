package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import logica.*;

public class PanelAltaCompetencia extends PanelGenerico{
	JButton cancelar;
	JButton aceptar;
	JButton agregarSede;
	JButton borrarSede;
	JPanel panel;
	JPanel panelDer;
	JPanel panelIzq;
	JPanel cartas;
	JPanel basico;
	JLabel DatosCompetencia;
	JLabel datosC;
	JLabel nombre;
	JLabel dep;
	JLabel sedes;
	JLabel modalidad;
	JLabel reglamento;
	JLabel obligatorio;
	JLabel formaPunt;
	JTextField campoNombre;
	JTextArea campoReglamento;
	JComboBox<String> campoDeporte;
	JComboBox<String> campoMod;
	JComboBox<String> campoFormaP;
	MutableComboBoxModel<String> modeloFormaP;
	SpinnerNumberModel modeloSets;
	SpinnerNumberModel modeloTantos;
	SpinnerNumberModel modeloPG;
	SpinnerNumberModel modeloPE;
	JSplitPane splitHorizontal;
	JSplitPane splitVertical;
	JScrollPane scrollTabla;
	JScrollPane scrollTexto;
	JTable tablaSedes;
	ArrayList<String> columnasTabla;
	ArrayList<LugarRealizacion> lugares;
	ArrayList<Deporte> deportes;
	HashMap<LugarRealizacion,Integer> lugaresElegidos;
	boolean permiteEmpate;
	public PanelAltaCompetencia() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	private void inicializarComponentes() {
		 deportes=GestorDeporte.buscarTodos();
		 panelDer=new JPanel();
		 panelIzq=new JPanel();
		 panelDer.setPreferredSize(new Dimension(400,300));
		 lugares = new ArrayList();
		 lugaresElegidos = new HashMap<LugarRealizacion, Integer>();
		 permiteEmpate = true;
		 //Buttons
		 cancelar = new JButton("Cancelar");
		 cancelar.setPreferredSize(new Dimension(130,40));
		 aceptar = new JButton("Aceptar");
		 aceptar.setPreferredSize(new Dimension(130,40));
		 agregarSede = new JButton("Agregar Sede");
		 agregarSede.setPreferredSize(new Dimension(100,30));
		 borrarSede = new JButton("Borrar Sede");
		 borrarSede.setPreferredSize(new Dimension(100,30));
		 
		 //Color buttons
		 cancelar.setBackground(colorFondoBoton);
		 cancelar.setForeground(colorTextoBoton);
		 aceptar.setBackground(colorFondoBoton);
		 aceptar.setForeground(colorTextoBoton);
		 agregarSede.setBackground(colorFondoBoton);
		 agregarSede.setForeground(colorTextoBoton);
		 borrarSede.setBackground(colorFondoBoton);
		 borrarSede.setForeground(colorTextoBoton);
		 
		 //Labels
		 datosC = new JLabel("<HTML><U><B>Datos de la competencia:</U></B></HTML>");
		 nombre = new JLabel("Nombre:");
		 dep = new JLabel("Deporte:");
		 sedes = new JLabel("Sedes:");
		 modalidad = new JLabel("Modalidad: ");
		 reglamento = new JLabel("<HTML><B>Reglamento:</B></HTML>");
		 obligatorio = new JLabel("<HTML><B><FONT COLOR=GRAY>Todos los campos marcados con </FONT COLOR=GRAY><FONT COLOR=RED>(*) </FONT COLOR=RED> <FONT COLOR=GRAY>son obligatorios </FONT COLOR=GRAY> </B></HTML>");
		 formaPunt = new JLabel("<HTML>Forma de <br> Puntuacion: </HTML>");
		 
		 //Campos y modelos
		 campoReglamento = new JTextArea("<< Aquí escriba el reglamento >>",20,20);
		 campoNombre = new JTextField(5);
		 campoDeporte = new JComboBox<String>();
		 for (Deporte d : deportes) {
			campoDeporte.addItem(d.getNombreDeporte());
		}
		 campoMod = new JComboBox<String>();
		 campoMod.insertItemAt("<Ninguna>",0);
		 campoMod.insertItemAt("Liga",1);
		 campoMod.insertItemAt("Eliminatoria Simple",2);
		 campoMod.insertItemAt("Eliminatoria Doble",3);
		 campoMod.setSelectedIndex(0);
		 final ModeloComboFormaP  modelo = new ModeloComboFormaP();
		 modelo.insertElementAt("<Ninguna>",0);
		 campoFormaP = new JComboBox<String>(modelo);
		 campoFormaP.setSelectedIndex(0);
		 tablaSedes = new JTable(10,10);
		 scrollTabla = new JScrollPane(tablaSedes);
		 scrollTabla.setPreferredSize(new Dimension(150, 80));
		 scrollTabla.getVerticalScrollBar().setBackground(Color.decode("#5693f5"));
		 scrollTabla.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			    @Override
			    protected void configureScrollBarColors() {
			        this.thumbColor = Color.decode("#2148bc");
			        this.thumbDarkShadowColor = (Color.decode("#0f2e8a"));
			    }
			});	 
		 tablaSedes.setPreferredScrollableViewportSize(tablaSedes.getPreferredSize());
		 tablaSedes.setFillsViewportHeight(true);
		 
		 
		 scrollTexto = new JScrollPane(campoReglamento);
		 scrollTexto.setPreferredSize(new Dimension(300,400));
		 scrollTexto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollTexto.getVerticalScrollBar().setBackground(Color.decode("#5693f5"));
		 scrollTexto.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			    @Override
			    protected void configureScrollBarColors() {
			        this.thumbColor = Color.decode("#2148bc");
			        this.thumbDarkShadowColor = (Color.decode("#0f2e8a"));
			    }
			});
		 
		 
		 modeloSets = new SpinnerNumberModel(3,1,9,2);
		 modeloTantos =new SpinnerNumberModel(3,0,99,1);
		 modeloPG =new SpinnerNumberModel(3,1,99,1);
		 modeloPE =new SpinnerNumberModel(1,0,99,1);
		 
		 //SPLITS
		 splitHorizontal= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		 splitHorizontal.setLeftComponent(panelIzq);
		 splitHorizontal.setRightComponent(panelDer);
		 splitHorizontal.setPreferredSize(new Dimension(1000,600));
		 splitHorizontal.setOneTouchExpandable(false);
		 splitHorizontal.setDividerLocation(0.8);
		 splitHorizontal.setResizeWeight(1);      
		 splitHorizontal.setEnabled(false);
		 
		 splitVertical= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		 splitVertical.setTopComponent(basico);
		 splitVertical.setBottomComponent(cartas);
		 splitVertical.setPreferredSize(new Dimension(580,580));
		 splitVertical.setOneTouchExpandable(false);
		 splitVertical.setDividerLocation(0.9);
		 splitVertical.setResizeWeight(0.65); 
		 splitVertical.setEnabled(false);
		 //ActionListeners
		 ActionListener formaPListener = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	modelo.removeAllElements();
	                String mod = (String) campoMod.getSelectedItem();
	                if(mod=="Liga") {	
	                	modelo.insertElementAt("<Ninguna>",0);
	                	modelo.insertElementAt("Puntuacion",1);
	                	modelo.insertElementAt("Sets",2);
	                	campoFormaP.setSelectedIndex(0);
	                	}
	                else if(mod=="<Ninguna>") {
	                	modelo.removeAllElements();
	                	modelo.insertElementAt("<Ninguna>",0);
	                	campoFormaP.setSelectedIndex(0);
	                }
	                else {
	                	modelo.insertElementAt("<Ninguna>",0);
	                	modelo.insertElementAt("Resultado Final",1);
	                	modelo.insertElementAt("Sets",2);
	                	campoFormaP.setSelectedIndex(0);
	                	}
	                }
	            
	        };
	        
	     ActionListener cartaListener = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	CardLayout c1 = (CardLayout) cartas.getLayout();
	                String mod = (String) campoMod.getSelectedItem();
	                String puntuacion = (String) campoFormaP.getSelectedItem();
	                if(mod=="Liga") {	
	                	if(puntuacion=="Sets") 
	                		c1.show(cartas,"LIGA_SETS");
	                	else if(puntuacion=="Puntuacion") 
	                		c1.show(cartas,"LIGA_PUNTUACION");
	                	else
	                		c1.show(cartas,"PANEL_VACIO");
	                	}
	                
	                else if (mod == "<Ninguna>") {
	                	c1.show(cartas,"PANEL_VACIO");
	                	}
	                	
	                else {
	                	if(puntuacion=="Sets") {
	                		c1.show(cartas,"ELIM_SETS");
	                	}
	                	else {
	                		c1.show(cartas,"PANEL_VACIO");
	                	}
	                	}
	                }
	            
	        };
	     ActionListener cancelarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.setSize(tamPrincipal);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
			}
		}; 
		 ActionListener agregarSedeListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      JComboBox<String> comboLugares = new JComboBox<String>();
			      lugares= GestorLugarRealizacion.buscarLugaresPorDeporte((String) campoDeporte.getSelectedItem(),GestorUsuario.usuario_autenticado.getIdUsuario().toString());
			      for (LugarRealizacion l : lugares) {
					comboLugares.addItem(l.getNombre());
				}
			      SpinnerNumberModel modeloDisp;
			      modeloDisp= new SpinnerNumberModel(1,1,99,1);
			      JSpinner Spinnerdisponibilidad = new JSpinner(modeloDisp);
			      PanelGenerico panelAgregarSede = new PanelGenerico();
			      panelAgregarSede.setLayout(new GridBagLayout());
			      GridBagConstraints gbc= new GridBagConstraints();
			      gbc.gridx=0;
			      gbc.gridy=0;
			      gbc.insets = new Insets(10,10,10,10);
			      panelAgregarSede.setPreferredSize(new Dimension(300,100));
			      panelAgregarSede.add(new JLabel("Nombre:"),gbc);
			      gbc.gridx++;
			      panelAgregarSede.add(comboLugares,gbc);
			      gbc.gridx=0;
			      gbc.gridy++;
			      panelAgregarSede.add(new JLabel("Disponibilidad:"),gbc);
			      gbc.gridx++;
			      panelAgregarSede.add(Spinnerdisponibilidad,gbc);

			      
			      panelAgregarSede.setOpaque(false);

			      int result  = JOptionPane.showConfirmDialog(null,panelAgregarSede,"Agregar Sede",JOptionPane.OK_CANCEL_OPTION);
			      if(result == JOptionPane.OK_OPTION) {
					LugarRealizacion LElegido = new LugarRealizacion();
					for (LugarRealizacion l : lugares) {
						if(l.getNombre()==comboLugares.getSelectedItem())
							LElegido=l;
					}
					lugaresElegidos.put(LElegido,(Integer) Spinnerdisponibilidad.getValue());

				}
				construirTabla(setearColumnas(), obtenerMatrizDatos());				
			}
		};
		 ActionListener borrarSedeListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String sedeSeleccionada= (String) tablaSedes.getValueAt(tablaSedes.getSelectedRow(),0);
			    LugarRealizacion lugarABorrar = new LugarRealizacion();
			    for (Map.Entry<LugarRealizacion,Integer> lugar : lugaresElegidos.entrySet()) {
					if(lugar.getKey().getNombre() == sedeSeleccionada) {
						lugarABorrar=lugar.getKey();
					}
				}
			    lugaresElegidos.remove(lugarABorrar);
				construirTabla(setearColumnas(), obtenerMatrizDatos());				
			}
		};
	     ActionListener aceptarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String retorno;
				retorno = GestorCompetencia.saveCompetencia(campoNombre.getText().trim(), campoDeporte.getSelectedItem().toString(), lugaresElegidos,
			            campoMod.getSelectedItem().toString(),campoFormaP.getSelectedItem().toString(), campoReglamento.getText().trim(),(Integer) modeloTantos.getValue(),
			           (Integer) modeloPG.getValue(),(Integer) modeloPE.getValue(),permiteEmpate,(Integer) modeloSets.getValue());
			if(retorno=="guardado") {
				mensajeExito();
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.setSize(tamPrincipal);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
				}
			else  {
				mensajeError(retorno);
				}
			}
		};
		 campoMod.addActionListener(formaPListener);
	     campoFormaP.addActionListener(cartaListener);
	     aceptar.addActionListener(aceptarListener);
	     cancelar.addActionListener(cancelarListener);
	     agregarSede.addActionListener(agregarSedeListener);
	     borrarSede.addActionListener(borrarSedeListener);
	     construirTabla(setearColumnas(),obtenerMatrizDatos());
	}
	
	
	private void armarPanel() {
		this.setLayout(new BorderLayout());
		panel = new JPanel();
		this.add(panel);
		panel.add(splitHorizontal);
		CardLayout c1 = new CardLayout();
		basico = panelBasico();
		cartas = new JPanel(c1);
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		panelDer.setLayout(layout);
		panelIzq.setLayout(layout);
		panelIzq.add(splitVertical);
		this.add(panel,BorderLayout.CENTER);
		layout.putConstraint(SpringLayout.WEST,splitHorizontal,5,SpringLayout.WEST,panel);
		layout.putConstraint(SpringLayout.NORTH,splitHorizontal,5,SpringLayout.NORTH,panel);
		layout.putConstraint(SpringLayout.WEST,splitVertical,5,SpringLayout.WEST,panelIzq);
		layout.putConstraint(SpringLayout.NORTH,splitVertical,10,SpringLayout.NORTH,panelIzq);
		splitVertical.add(basico);
		splitVertical.add(cartas);
		//PANEL OPCIONES
		JPanel cartaLigaP;
		JPanel cartaLigaS;
		JPanel cartaElimS;
		JPanel vacio = new JPanel();
		cartaLigaP = cartaLigaPuntuacion();
		cartaLigaS = cartaLigaSets();
		cartaElimS = cartaElimSets();
		cartas.add(cartaLigaP,"LIGA_PUNTUACION");
		cartas.add(cartaLigaS,"LIGA_SETS");
		cartas.add(cartaElimS,"ELIM_SETS");
		cartas.add(vacio,"PANEL_VACIO");
		c1.show(cartas,"PANEL_VACIO");
		layout.putConstraint(SpringLayout.WEST,cartas,1,SpringLayout.WEST,splitVertical);
		layout.putConstraint(SpringLayout.NORTH,cartas,10,SpringLayout.NORTH,splitVertical);
		//PANEL REGLAMENTO
		panelDer.add(aceptar);
		layout.putConstraint(SpringLayout.WEST,aceptar,230,SpringLayout.WEST,panelDer);
		layout.putConstraint(SpringLayout.NORTH,aceptar,540,SpringLayout.NORTH,panelDer);
		panelDer.add(cancelar);
		layout.putConstraint(SpringLayout.WEST,cancelar,50,SpringLayout.WEST,panelDer);
		layout.putConstraint(SpringLayout.NORTH,cancelar,540,SpringLayout.NORTH,panelDer);
		panelDer.add(reglamento);
		layout.putConstraint(SpringLayout.WEST,reglamento,150,SpringLayout.WEST,panelDer);
		layout.putConstraint(SpringLayout.NORTH,reglamento,45,SpringLayout.NORTH,panelDer);
		panelDer.add(scrollTexto);
		layout.putConstraint(SpringLayout.WEST,scrollTexto,45,SpringLayout.WEST,panelDer);
		layout.putConstraint(SpringLayout.NORTH,scrollTexto,20,SpringLayout.SOUTH,reglamento);
		panelDer.add(obligatorio);
		layout.putConstraint(SpringLayout.WEST,obligatorio,0,SpringLayout.WEST,scrollTexto);
		layout.putConstraint(SpringLayout.NORTH,obligatorio,2,SpringLayout.SOUTH,cancelar);
		
		//Colores Paneles
		panel.setOpaque(false);
		panelDer.setOpaque(false);
		panelIzq.setOpaque(false);
		basico.setOpaque(false);
		cartas.setOpaque(false);
		cartaLigaP.setOpaque(false);
		cartaLigaS.setOpaque(false);
		cartaElimS.setOpaque(false);
		vacio.setOpaque(false);
	}
	
	private JPanel panelBasico() {
		JPanel panelBasico = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		panelBasico.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(4, 0, 4, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill=GridBagConstraints.BOTH;
        panelBasico.add(datosC,gbc);
		gbc.gridy++;
		panelBasico.add(nombre,gbc);		
		gbc.gridy++;
		panelBasico.add(dep,gbc);
		gbc.gridy++;
		sedes.setBorder(BorderFactory.createEmptyBorder(10, 0,70,0));
		panelBasico.add(sedes,gbc);
		gbc.gridx++;
		gbc.gridwidth=3;
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelBasico.add(scrollTabla,gbc);
		gbc.gridwidth=1;
		gbc.gridy++;
		panelBasico.add(agregarSede,gbc);
		gbc.gridx+=2;
		panelBasico.add(borrarSede,gbc);
		gbc.gridx-=3;
		gbc.gridy++;
		panelBasico.add(modalidad,gbc);
		gbc.gridy++;
		panelBasico.add(formaPunt,gbc);
		gbc.gridy=1;
		gbc.gridx++;
		panelBasico.add(campoNombre,gbc);
		gbc.gridy++;
		panelBasico.add(campoDeporte,gbc);
		gbc.gridy=5;
		panelBasico.add(campoMod,gbc);
		gbc.gridy++;
		panelBasico.add(campoFormaP,gbc);
		gbc.gridy=1;
		gbc.gridx=2;
		panelBasico.add(crearLabelObligatorio(),gbc);
		gbc.gridy++;
		panelBasico.add(crearLabelObligatorio(),gbc);
		gbc.gridy++;
		gbc.gridx=4;
		panelBasico.add(crearLabelObligatorio(),gbc);
		gbc.gridx=2;
		gbc.gridy+=2;
		panelBasico.add(crearLabelObligatorio(),gbc);
		gbc.gridy++;
		panelBasico.add(crearLabelObligatorio(),gbc);
		return panelBasico;
	}
	
	private JPanel cartaLigaPuntuacion() {
		JPanel panelLP = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel tantosDef = new JLabel("<HTML>Tantos por <br> default: </HTML>");
		JLabel puntosPG = new JLabel("<HTML>Puntos por <br>partido ganado </HTML>");
		JLabel puntosPE = new JLabel("<HTML>Puntos por <br>partido empatado </HTML>");
		JSpinner  numTantos = new JSpinner(modeloTantos);
		JSpinner numPG = new JSpinner(modeloPG);
		final JSpinner numPE = new JSpinner(modeloPE);
		final JCheckBox checkEmpate = new JCheckBox("Empate?");
		numPE.setEnabled(true);
		checkEmpate.setSelected(true);		
		panelLP.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        panelLP.add(tantosDef);
        gbc.gridy++;
		panelLP.add(puntosPG,gbc);
		gbc.gridy++;
		panelLP.add(puntosPE,gbc);
		gbc.gridy=0;
		gbc.gridx++;
		panelLP.add(numTantos,gbc);
		gbc.gridy++;
		panelLP.add(numPG,gbc);
		gbc.gridy++;
		panelLP.add(numPE,gbc);
		gbc.gridx=2;
		panelLP.add(checkEmpate,gbc);
		gbc.gridy=0;
		gbc.gridx=2;
		panelLP.add(crearLabelObligatorio(),gbc);
		gbc.gridy++;
		panelLP.add(crearLabelObligatorio(),gbc);
		gbc.gridy++;
		gbc.gridx=3;
		panelLP.add(crearLabelObligatorio(),gbc);
		
		
		ActionListener listenerEmpate = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkEmpate.isSelected()) {
					numPE.setEnabled(false);
					permiteEmpate = false;
				}
				else {
					numPE.setEnabled(true);
					permiteEmpate = true;
				}
			}
		};
		checkEmpate.addActionListener(listenerEmpate);
		return panelLP;
	}
	
	private JPanel cartaLigaSets() {
		JPanel panelLS = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel puntosPG = new JLabel("<HTML>Puntos por <br>partido ganado </HTML>");
		JLabel puntosPP = new JLabel("<HTML> Puntos por <br>presentarse </HTML>");
		JLabel maxSets = new JLabel("<HTML>Maximo de <br> sets: </HTML>");
		JSpinner numSets = new JSpinner(modeloSets);
		JSpinner numPG = new JSpinner(modeloPG);
		numSets.setPreferredSize(numPG.getPreferredSize());
		panelLS.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
		panelLS.add(maxSets,gbc);
		gbc.gridy++;
		panelLS.add(puntosPG,gbc);
		gbc.gridy=0;
		gbc.gridx++;
		panelLS.add(numSets,gbc);
		gbc.gridy++;
		panelLS.add(numPG,gbc);
		gbc.gridy=0;
		gbc.gridx=2;
		panelLS.add(crearLabelObligatorio(),gbc);
		gbc.gridy++;
		panelLS.add(crearLabelObligatorio(),gbc);
		
		return panelLS;
		}
	private JPanel cartaElimSets() {
		JPanel panelES = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel maxSets = new JLabel("<HTML>Maximo de <br> sets: </HTML>");
		JSpinner numSets = new JSpinner(modeloSets);
		panelES.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
		panelES.add(maxSets,gbc);
		gbc.gridx++;
		panelES.add(numSets,gbc);
		gbc.gridx++;
		panelES.add(crearLabelObligatorio(),gbc);
		return panelES;
	}
   
   public void construirTabla(String[] columnas,Object[][] data) {
		 ModeloTablaSedes model = new ModeloTablaSedes(data,columnas);
		 tablaSedes.setModel(model);
		 tablaSedes.getTableHeader().setDefaultRenderer(new GenericoTableHeaderRenderer());


		 tablaSedes.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasSedes("texto"));
		 tablaSedes.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldasSedes("texto"));
		 tablaSedes.getTableHeader().setReorderingAllowed(false);
		 tablaSedes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		 tablaSedes.setRowHeight(35);
		 tablaSedes.setGridColor(Color.BLACK);

		 tablaSedes.getColumnModel().getColumn(0).setPreferredWidth(10);
		 tablaSedes.getColumnModel().getColumn(1).setPreferredWidth(15);
		 
	}
	
	private String[] setearColumnas() {
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Lugar");
		columnasTabla.add("Disponibilidad");
		String[] columnas = new String[columnasTabla.size()];
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		}
		return columnas;
		
	}
	private Object[][] obtenerMatrizDatos() {
		String informacion[][] = new String[lugaresElegidos.size()][columnasTabla.size()];
		int i=0;
		System.out.println(lugaresElegidos.entrySet());
		for(Map.Entry<LugarRealizacion,Integer> lugar : lugaresElegidos.entrySet()) {
			informacion[i][0] = lugar.getKey().getNombre();
			informacion[i][1] = String.valueOf(lugar.getValue());
			System.out.println(lugar.getKey().getNombre());
			System.out.println(String.valueOf(lugar.getValue()));
			i++;
		}
		return informacion;
	}

	private JLabel crearLabelObligatorio() {
		JLabel labelObligatorio;
		labelObligatorio = new JLabel("<HTML><font color='red'> (*) </HTML>");
		return labelObligatorio;
	}
	private void mensajeExito() {
		JOptionPane.showMessageDialog(this,"Competencia guardada con exito!");
	}
	private void mensajeError(String error) {
		JOptionPane.showMessageDialog(this,error);
	}
	
}