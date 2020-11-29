/**
 * 
 */
package logica;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	public static List<CompetenciaDTO> buscarCompetenciaPorFiltros(String nombre, String deporte, String modalidad, String estado){
		
		List<Competencia> competenciaObj = new ArrayList<Competencia>();
		CompetenciaDAOimpl daoComp = new CompetenciaDAOimpl();
		switch(modalidad) {
		case "Liga":
			Liga l = new Liga(new Deporte(deporte), nombre,  estado);
			competenciaObj = daoComp.selectCompetenciaByFilters(l);
			break;
			
			
		case "Eliminacion Simple":
			EliminacionSimple es = new EliminacionSimple(new Deporte(deporte), nombre,  estado);
			competenciaObj = daoComp.selectCompetenciaByFilters(es);
			break;
			
			
			
		case "Eliminacion Doble":
			EliminacionDoble ed = new EliminacionDoble(new Deporte(deporte), nombre,  estado);
			competenciaObj = daoComp.selectCompetenciaByFilters(ed);
			break;
			
			
		default:
			Competencia c = new Competencia(new Deporte(deporte), nombre,  estado);
			competenciaObj = daoComp.selectCompetenciaByFilters(c);
			
		}
		
		List<CompetenciaDTO> resultado = new ArrayList<CompetenciaDTO>();
		
		for(Competencia comp:competenciaObj) {
			CompetenciaDTO dto = new CompetenciaDTO(comp.getIdCompetencia(), comp.getDeporte().getNombreDeporte(),  comp.getNombre(), comp.getEstado());
			resultado.add(dto);
		}
		
		
		return resultado;
		
	}
	
	

	
	public static CompetenciaDTO buscarCompetencia(int id_competencia) {
		Competencia c = (new CompetenciaDAOimpl()).buscarPorId(id_competencia);
		CompetenciaDTO dto = new CompetenciaDTO();
		
		dto.setNombre(c.getNombre());
		dto.setDeporte(c.getDeporte().getNombreDeporte());
		dto.setEstado(c.getEstado());
		
		ArrayList<Participante> participantes = c.getParticipantes();
		String[][] parString = new String[participantes.size()][2];
		int i=0;
		
		for(Participante p:participantes) {
			parString[i][0] = p.getNombre();
			parString[i][1] = p.getEmail();
			i++;
 		}
		
		dto.setParticipantes(parString);
		
		ArrayList<Encuentro> encuentros;
		ArrayList<Ronda> rondas = new ArrayList<Ronda>(c.getFixture().getRonda());
		encuentros = (ArrayList<Encuentro>) rondas.stream().map(r -> r.getEncuentros()).
				flatMap(e -> e.stream()).filter(e -> e.getFecha().after(Date.from(Instant.now()))).collect(Collectors.toList());
		
		i=0;
		String[][] fixture = new String[encuentros.size()][3];
		
		for(Encuentro e: encuentros) {
			fixture[i][0] = e.getParticipante1().getNombre(); 
			fixture[i][1] = e.getParticipante2().getNombre(); 
			fixture[i][2] = e.getLugar().getNombre(); 
			i++;
		}
		
		dto.setFixture(fixture);
		
		if(c instanceof Liga)
			dto.setModalidad("Liga");
		else
			if(c instanceof EliminacionSimple)
				dto.setModalidad("Eliminacion Simple");
			else
				dto.setModalidad("Eliminacion Doble");
		
		
		
		return dto;
		
		
		
	}
	
}
