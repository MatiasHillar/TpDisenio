/**
 * 
 */
package logica;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import acceso.CompetenciaDAOimpl;
import acceso.DisponibilidadDAOimpl;
import acceso.FormaDePuntuacionDAOimpl;


/**
 * @author Pichi
 *
 */
public class GestorCompetencia {
	
	
	
	
	
	public static String saveCompetencia(String nombre, String deporte, HashMap<LugarRealizacion,Integer> lugares,
			String modalidad, String forma_p, String reglamento, Integer tantos_default,
			Integer puntos_pg, Integer puntos_pe, boolean permite_empate, Integer max_sets) {
		DisponiblePara dp = null;
		if(nombre.length()<=0)
			return "El campo nombre no puede estar vacio";
		
		if(lugares.isEmpty())
			return "La lista de sedes no puede estar vacia";
		if (modalidad=="<Ninguna>")
			return "No se ha escogido ninguna modalidad";
		if(forma_p == "<Ninguna>")
			return "No se ha escogido ninguna forma de puntuacion";
		
		try {
			if(modalidad.contentEquals("Liga")) {
				if(forma_p.contentEquals("Sets")) {
					
					Sets forma = new Sets(max_sets);
					Liga competencia = new Liga(new Deporte(deporte), forma, new Usuario(GestorUsuario.usuario_autenticado),
							nombre, permite_empate, "CREADA", puntos_pg, puntos_pe);
					HashSet<DisponiblePara> disponibleParas = new HashSet<DisponiblePara>();
					for (Map.Entry<LugarRealizacion, Integer> entry : lugares.entrySet()) {
						dp = new DisponiblePara(competencia, entry.getKey(), entry.getValue());
						disponibleParas.add(dp);
					}
					competencia.setDisponibleParas(disponibleParas);
					competencia.setReglamento(reglamento);
					(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
					return "guardado";
					
				}
				else {
					if(puntos_pg<=puntos_pe)
						return "Los puntos por partido ganado deben ser mayores que los puntos por partido empatado";
					Puntuacion forma = new Puntuacion(tantos_default);
					Liga competencia = new Liga(new Deporte(deporte), forma, new Usuario(GestorUsuario.usuario_autenticado),
							nombre, permite_empate, "CREADA", puntos_pg, puntos_pe);
					HashSet<DisponiblePara> disponibleParas = new HashSet<DisponiblePara>();
					for (Map.Entry<LugarRealizacion, Integer> entry : lugares.entrySet()) {
						dp = new DisponiblePara(competencia, entry.getKey(), entry.getValue());
						disponibleParas.add(dp);
					}
					competencia.setDisponibleParas(disponibleParas);
					competencia.setReglamento(reglamento);
					(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
					return "guardado";
				}
					
				
				
				
				
			}
			else {
				if(modalidad.contentEquals("Eliminacion Simple")) {
					if(forma_p.contentEquals("Sets")) {
						Sets forma = new Sets(max_sets);
						EliminacionSimple competencia = new EliminacionSimple(new Deporte(deporte), forma, new Usuario(GestorUsuario.usuario_autenticado),
								nombre, false, "CREADA");
						HashSet<DisponiblePara> disponibleParas = new HashSet<DisponiblePara>();
						for (Map.Entry<LugarRealizacion, Integer> entry : lugares.entrySet()) {
							dp = new DisponiblePara(competencia, entry.getKey(), entry.getValue());
							disponibleParas.add(dp);
						}
						competencia.setDisponibleParas(disponibleParas);
						competencia.setReglamento(reglamento);
						(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
						return "guardado";
						
					}
					else {
						ResultadoFinal forma = new ResultadoFinal();
						EliminacionSimple competencia = new EliminacionSimple(new Deporte(deporte), forma, new Usuario(GestorUsuario.usuario_autenticado),
								nombre, false, "CREADA");
						HashSet<DisponiblePara> disponibleParas = new HashSet<DisponiblePara>();
						for (Map.Entry<LugarRealizacion, Integer> entry : lugares.entrySet()) {
							dp = new DisponiblePara(competencia, entry.getKey(), entry.getValue());
							disponibleParas.add(dp);
						}
						competencia.setDisponibleParas(disponibleParas);
						competencia.setReglamento(reglamento);
						(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
						return "guardado";
					}
					
					
					
				}
				else {
					if(forma_p.contentEquals("Sets")) {
						Sets forma = new Sets(max_sets);
						EliminacionSimple competencia = new EliminacionSimple(new Deporte(deporte), forma, new Usuario(GestorUsuario.usuario_autenticado),
								nombre, false, "CREADA");
						HashSet<DisponiblePara> disponibleParas = new HashSet<DisponiblePara>();
						for (Map.Entry<LugarRealizacion, Integer> entry : lugares.entrySet()) {
							dp = new DisponiblePara(competencia, entry.getKey(), entry.getValue());
							disponibleParas.add(dp);
						}
						competencia.setDisponibleParas(disponibleParas);
						competencia.setReglamento(reglamento);
						(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
						return "guardado";
						
					}
					else {
						ResultadoFinal forma = new ResultadoFinal();
						EliminacionSimple competencia = new EliminacionSimple(new Deporte(deporte), forma, new Usuario(GestorUsuario.usuario_autenticado),
								nombre, false, "CREADA");
						HashSet<DisponiblePara> disponibleParas = new HashSet<DisponiblePara>();
						for (Map.Entry<LugarRealizacion, Integer> entry : lugares.entrySet()) {
							dp = new DisponiblePara(competencia, entry.getKey(), entry.getValue());
							disponibleParas.add(dp);
						}
						competencia.setDisponibleParas(disponibleParas);
						competencia.setReglamento(reglamento);
						(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
						return "guardado";
					}
					
					
					
				}
			}
		}
		catch(SQLException e) {
			return "Hubo un error al guardar la competencia en la base de datos";
		}
		/*catch(CompetenciaExistenteException e) {
			return "Ya existe una competencia con ese nombre";
		}*/
		
		
		
		
		
	}
	
}
