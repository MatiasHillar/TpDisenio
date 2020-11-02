/**
 * 
 */
package logica;

import java.util.ArrayList;
import java.util.List;

import acceso.DeporteDAOimpl;

/**
 * @author Pichi
 *
 */
public class GestorDeporte {
	public static ArrayList<Deporte> buscarTodos(){
		return (new DeporteDAOimpl()).buscarTodos();
	}
	
} 
