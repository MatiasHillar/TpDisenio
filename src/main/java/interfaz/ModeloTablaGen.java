package interfaz;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaGen extends DefaultTableModel{
	String[] columnas;
	Object[][] data;
	
	public ModeloTablaGen(Object [][] data,String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data,columnas);
	}
	
	public ModeloTablaGen() {
		
	}
	
	
	public ModeloTablaGen(ArrayList<String> arrayParticipantes, String columna) {
		super();
		this.data = new Object[1][];
		this.data[0] = arrayParticipantes.toArray();
		this.columnas = new String[1];
		this.columnas[0] = columna;
		setDataVector(data,columnas);
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
