package interfaz;

import javax.swing.DefaultComboBoxModel;

public class ModeloComboFormaP extends DefaultComboBoxModel {
	private static final long serialVersionUID = 1L;

    public ModeloComboFormaP() {
        super();
    }
    public ModeloComboFormaP(Object[] items) {
        int size = items.length;
        for (int i = 0; i < size; i++) {
            super.addElement(items[i]);
        }
        setSelectedItem(items[0]);
    }
    @Override
    public void addElement(Object element) {
        insertElementAt(element, 0);
    	}
}
