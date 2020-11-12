package logica;
// default package
// Generated 7 oct. 2020 00:43:56 by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Localidad generated by hbm2java
 */
public class Localidad implements java.io.Serializable {

	private String idLocalidad;
	//private Provincia provincia;
	private String nombre;
	private Set usuarios = new HashSet(0);

	public Localidad() {
	}

	public Localidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public Localidad(String idLocalidad, String nombre, Set usuarios) {
		this.idLocalidad = idLocalidad;
		//this.provincia = provincia;
		this.nombre = nombre;
		this.usuarios = usuarios;
	}

	public String getIdLocalidad() {
		return this.idLocalidad;
	}

	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

//	public Provincia getProvincia() {
//		return this.provincia;
//	}
//
//	public void setProvincia(Provincia provincia) {
//		this.provincia = provincia;
//	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set usuarios) {
		this.usuarios = usuarios;
	}

}