/**
 * 
 */
package logica;

import java.util.ArrayList;

/**
 * @author Pichi
 *
 */
public class Provincia {
	private String nombre;
	private ArrayList<Localidad> localidades;
	private long id_provincia;
	
	public Provincia(String nombre, ArrayList<Localidad> localidades) {
		this.nombre = nombre;
		this.localidades = localidades;
	}

	public Provincia(String nombre, long id, ArrayList<Localidad> localidades) {
		this.nombre = nombre;
		this.localidades = localidades;
		this.id_provincia = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(ArrayList<Localidad> localidades) {
		this.localidades = localidades;
	}

	public long getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(long id_provincia) {
		this.id_provincia = id_provincia;
	}
	
	
	
}
