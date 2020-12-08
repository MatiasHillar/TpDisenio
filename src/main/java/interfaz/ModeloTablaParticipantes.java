package interfaz;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaParticipantes extends DefaultTableModel{
	
	private Object[][] data;
	private String[] columnas;

	public ModeloTablaParticipantes(Object [][] data,String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data,columnas);
	}
	
	public ModeloTablaParticipantes() {
		
	}
	@Override
	public boolean isCellEditable(int row, int column) {
        return false;
    }
}
