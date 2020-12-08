package interfaz;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaCompetencias extends DefaultTableModel{
	private Object[][] data;
	private String[] columnas;

	public ModeloTablaCompetencias(Object [][] data,String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data,columnas);
	}
	
	public ModeloTablaCompetencias() {
		
	}
	@Override
	public boolean isCellEditable(int row, int column) {
        return false;
    }
}
