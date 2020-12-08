/**
 * 
 */
package logica;

import java.sql.SQLException;
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
	
	
	
	
	public static String save(String nombreLugar, ArrayList<String> deportesAgregados) {
		if(nombreLugar.length()<=0)
			return "El nombre del lugar de realizacion no puede estar vacio";
		
		if(deportesAgregados.size()==0)
			return "Debe agregar al menos un deporte";
		
		ArrayList<Deporte> deportes = new ArrayList<Deporte>();
		for(String s: deportesAgregados) {
			deportes.add(new Deporte(s));
		}
		
		LugarRealizacion l = new LugarRealizacion(GestorUsuario.usuario_autenticado, nombreLugar, deportes);
		
		try {
			(new LugarRealizacionDAOimpl()).saveOrUpdate(l);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return "Hubo un error al guardar el lugar de realizacion en la base de datos";
		}
		
		return "exito";
		
	}
}
