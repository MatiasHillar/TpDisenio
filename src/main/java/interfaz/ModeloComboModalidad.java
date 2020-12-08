package interfaz;

import javax.swing.DefaultComboBoxModel;

public class ModeloComboModalidad extends DefaultComboBoxModel<String>{

	public ModeloComboModalidad() {
        super();
    }
    public ModeloComboModalidad(String[] items) {
        int size = items.length;
        for (int i = 0; i < size; i++) {
            super.addElement(items[i]);
        }
        setSelectedItem(items[0]);
    }
    @Override
    public void addElement(String element) {
        insertElementAt(element, 0);
    	}
}
