/**
 * 
 */
package logica;

import java.util.ArrayList;
import java.util.List;

import acceso.LugarRealizacionDAOimpl;

/**
 * @author Pichi
 *
 */
public class GestorLugarRealizacion {
	public static ArrayList buscarLugaresPorDeporte(String deporte, String usuario) {
		return LugarRealizacionDAOimpl.buscarLugaresPorDeporte(deporte, usuario);
	}
}
