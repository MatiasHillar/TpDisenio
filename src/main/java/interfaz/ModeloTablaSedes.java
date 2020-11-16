package interfaz;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaSedes extends DefaultTableModel {
	
	String[] columnas;
	Object[][] data;
	
	public ModeloTablaSedes(Object [][] data,String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data,columnas);
	}
	
	public ModeloTablaSedes() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
}
