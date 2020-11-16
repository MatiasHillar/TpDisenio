package interfaz;

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
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
