/**
 * 
 */
package logica;

import java.util.ArrayList;

/**
 * @author Pichi
 *
 */
public class ProvinciaDTO {
	String nombre;
	String[] localidades;
	
	
	public ProvinciaDTO(String nombre, ArrayList<Localidad> localidades) {
		super();
		this.nombre = nombre;
		String[] localString = new String[localidades.size()];
		int i=0;
		for(Localidad l:localidades) {
			localString[i] = l.getNombre();
			i++;
		}
		this.localidades = localString;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String[] getLocalidades() {
		return localidades;
	}


	public void setLocalidades(String[] localidades) {
		this.localidades = localidades;
	}
	
	
	
	
	
	
	
	
}
