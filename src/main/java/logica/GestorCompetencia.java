package logica;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
	
	
	public static CompetenciaDTO buscarNombreYParticipantes(int idCompetencia) {
		CompetenciaDTO dto = new CompetenciaDTO();
		Competencia c = (new CompetenciaDAOimpl()).buscarNombreYParticipantes(idCompetencia);
			
		dto.setNombre(c.getNombre());
		
		ArrayList<Participante> participantes = c.getParticipantes();
		String[][] parString = new String[participantes.size()][3];
		int i=0;
		
		for(Participante p:participantes) {
			parString[i][0] = p.getNombre();
			parString[i][1] = p.getEmail();
			parString[i][2] = p.getIdParticipante().toString();
			i++;
 		}
		
		dto.setParticipantes(parString);
		
		
		return dto;
	}
	
	
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
		List<CompetenciaDTO> resultado = new ArrayList<CompetenciaDTO>();
		List<Competencia> competenciaObj = new ArrayList<Competencia>();
		CompetenciaDAOimpl daoComp = new CompetenciaDAOimpl();
		System.out.println(nombre + deporte + modalidad + estado);
		if(nombre.equals("") && deporte.equals("<Ninguno>") && modalidad.equals("<Ninguna>") && estado.equals("<Ninguno>")) {
			System.out.println("SIN FILTROS");
			competenciaObj = daoComp.buscarPorUsr(GestorUsuario.usuario_autenticado);
		}
		else {
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
		}
		for(Competencia comp:competenciaObj) {
			CompetenciaDTO dto = new CompetenciaDTO(comp.getIdCompetencia(), comp.getDeporte().getNombreDeporte(),  comp.getNombre(), comp.getEstado());
			if(comp.getClass() == Liga.class)
				dto.setModalidad("Liga");
			else
				if(comp.getClass() == EliminacionSimple.class)
					dto.setModalidad("Eliminacion Simple");
				else
					dto.setModalidad("Eliminacion Doble");
			resultado.add(dto);
		}
		
		System.out.println(resultado.size());
		return resultado;
		
	}
	
	

	
	public static CompetenciaDTO buscarCompetencia(int id_competencia) {
		Competencia c = (new CompetenciaDAOimpl()).buscarPorId(id_competencia);
		CompetenciaDTO dto = new CompetenciaDTO();
		
		dto.setIdCompetencia(c.getIdCompetencia());
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
	
	
	public static String generarFixture(CompetenciaDTO compe) {
		Competencia competencia = (new CompetenciaDAOimpl()).buscarPorId(compe.getIdCompetencia());
		int cantEncuentrosDisponibles = 0;
		
		for(DisponiblePara dispo: competencia.getDisponibleParas()) 
			cantEncuentrosDisponibles += dispo.getCantidadEncuentros();
		
		ArrayList<DisponiblePara> disponibilidadesAAsignar = new ArrayList<DisponiblePara>(competencia.getDisponibleParas());
		
		if(competencia.getClass() == Liga.class) {
			if(competencia.getParticipantes().size()%2==0) {
				
				int cantEquipos = competencia.getParticipantes().size();
				if(cantEncuentrosDisponibles<(cantEquipos-1)*(cantEquipos/2))
					return "La cantidad de encuentros disponible en los lugares de realizacion es insuficiente. Agregue mas lugares";
				
				ArrayList<Ronda> rondas = new ArrayList<Ronda>();
				for(int i=0,k = 0; i<cantEquipos; i++) {
					Encuentro[] encuentros = new Encuentro[cantEquipos/2];
					
					for(int j=0; j<cantEquipos/2; j++) {
						encuentros[j] = new Encuentro();
						encuentros[j].setParticipante1(competencia.getParticipantes().get(k));
						encuentros[j].setLugar(disponibilidadesAAsignar.get(0).getLugarRealizacion());
						disponibilidadesAAsignar.get(0).setCantidadEncuentros(
								disponibilidadesAAsignar.get(0).getCantidadEncuentros()-1);
						
						if(disponibilidadesAAsignar.get(0).getCantidadEncuentros()==0)
							disponibilidadesAAsignar.remove(0);
						
						k++;
						
						if(k==cantEquipos-1)
							k=0;
					}		
					
					
					Ronda ronda = new Ronda();
					ArrayList<Encuentro> listaEncuentros = new 	ArrayList<Encuentro>();
					Collections.addAll(listaEncuentros, encuentros);
					ronda.setEncuentros(listaEncuentros);
					rondas.add(ronda);
				}
				
				for(int i=0; i<cantEquipos-1; i++) {
					if(i%2==0)
						rondas.get(i).getEncuentros().get(0)
						.setParticipante2(competencia.getParticipantes().get(cantEquipos-1));
					else {
						rondas.get(i).getEncuentros().get(0)
						.setParticipante2(rondas.get(i).getEncuentros().get(0).getParticipante1());
						
						rondas.get(i).getEncuentros().get(0).setParticipante1(competencia.getParticipantes().get(cantEquipos-1));
					}
				}
				
				int equipoImparMasAlto = cantEquipos-2;
				
				for(int i=0, k=equipoImparMasAlto; i<cantEquipos-1; i++) {
					for(int j=1; j<cantEquipos/2; j++) {
						rondas.get(i).getEncuentros().get(j)
						.setParticipante2(competencia.getParticipantes().get(k));
						k--;
						
						if(k==-1)
							k = equipoImparMasAlto;
					}
				}
				
				Fixture fixture = new Fixture(rondas);
				for(Ronda ronda: rondas)
					ronda.setFixture(fixture);
				competencia.setFixture(fixture);
				try {
					(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
				}
				catch(SQLException e) {
					return "Hubo un error al guardar el fixture en la base de datos.";
				}
				
				
				return "Se generó el fixture exitosamente.";
				
			}
			else {
				int cantEquipos = competencia.getParticipantes().size();
				if(cantEncuentrosDisponibles<(cantEquipos)*((cantEquipos-1)/2))
					return "La cantidad de encuentros disponible en los lugares de realizacion es insuficiente. Agregue mas lugares";
				
				
				ArrayList<Ronda> rondas = new ArrayList<Ronda>();
				for(int i=0,k = 0; i<cantEquipos; i++) {
					Encuentro[] encuentros = new Encuentro[cantEquipos/2];
					
					for(int j=-1; j<cantEquipos/2; j++) {
						
						if(j>=0) {
						encuentros[j] = new Encuentro();
						encuentros[j].setParticipante1(competencia.getParticipantes().get(k));
						encuentros[j].setLugar(disponibilidadesAAsignar.get(0).getLugarRealizacion());
						disponibilidadesAAsignar.get(0).setCantidadEncuentros(
								disponibilidadesAAsignar.get(0).getCantidadEncuentros()-1);
						
						if(disponibilidadesAAsignar.get(0).getCantidadEncuentros()==0)
							disponibilidadesAAsignar.remove(0);
						}
						k++;
						
						if(k==cantEquipos)
							k=0;
					}		
					
					
					Ronda ronda = new Ronda();
					ArrayList<Encuentro> listaEncuentros = new 	ArrayList<Encuentro>();
					Collections.addAll(listaEncuentros, encuentros);
					ronda.setEncuentros(listaEncuentros);
					rondas.add(ronda);
				}
				
				
				int equipoMasAlto = cantEquipos-1;
				
				for(int i=0, k=equipoMasAlto; i<cantEquipos; i++) {
					for(int j=1; j<cantEquipos/2; j++) {
						rondas.get(i).getEncuentros().get(j)
						.setParticipante2(competencia.getParticipantes().get(k));
						k--;
						
						if(k==-1)
							k = equipoMasAlto;
					}
				}
				
				Fixture fixture = new Fixture(rondas);
				for(Ronda ronda: rondas)
					ronda.setFixture(fixture);
				competencia.setFixture(fixture);
				try {
					(new CompetenciaDAOimpl()).saveOrUpdate(competencia);
				}
				catch(SQLException e) {
					return "Hubo un error al guardar el fixture en la base de datos.";
				}
				
				
				return "Se generó el fixture exitosamente.";
				
			}	
		}
		
		
		return "Aun no implementado";
	}
	
}
