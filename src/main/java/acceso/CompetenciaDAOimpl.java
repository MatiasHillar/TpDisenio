package acceso;

import java.beans.Statement;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logica.Competencia;
import logica.CompetenciaDTO;
import logica.Deporte;
import logica.DisponiblePara;
import logica.EliminacionDoble;
import logica.EliminacionSimple;
import logica.GestorUsuario;
import logica.Liga;
import logica.Participante;
import logica.Usuario;



public class CompetenciaDAOimpl implements CompetenciaDAO{

	private static final String SELECT_COMPETENCIA = "SELECT * FROM pruebacomp.COMPETENCIA as COM, pruebacomp.";
	
	private static final String SELECT_COMPETENCIA2 = " WHERE COM.id_competencia = ?";
	
	private static final String SELECT_BY_FILTERS = "SELECT * FROM pruebacomp.COMPETENCIA as COM ";
	
	private static final String INSERT_COMPETENCIA = "INSERT INTO pruebacomp.COMPETENCIA "

			+ "(ESTADO, NOMBRE_DEPORTE, USUARIO_DUENO, PERMITE_EMPATE, NOMBRE, FORMA_PUNTUACION, REGLAMENTO)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?)";

	
	private static final String SELECT_NOMBRE_COMPETENCIA = "SELECT nombre FROM pruebacomp.COMPETENCIA WHERE "
			+ "id_competencia = ?";
	
	private static final String SELECT_COMPETENCIA_BYUSR = "SELECT nombre, estado, nombre_deporte,"
			+ " Com.id_competencia FROM pruebacomp.COMPETENCIA as Com, pruebacomp.";
	
	private static final String SELECT_COMPETENCIA_BYUSR2 = " as Li" + 
			" WHERE usuario_dueno = ? AND Com.id_competencia=Li.id_competencia";
	private static final String INSERT_LIGA = "INSERT INTO pruebacomp.LIGA VALUES"
			+ "(?, ?, ?)";
	
	private static final String INSERT_ELIM_SIMPLE = "INSERT INTO pruebacomp.ELIMINACION_SIMPLE "
			+ "VALUES (?)";
	
	private static final String INSERT_ELIM_DOBLE = "INSERT INTO pruebacomp.ELIMINACION_DOBLE "
			+ "VALUES (?)";
	
	private static final String UPDATE_LIGA = "UPDATE pruebacomp.LIGA SET PUNTOS_PARTIDO_GANADO = ?, "
			+ "PUNTOS_PARTIDO_EMPATADO = ? "
			+ "WHERE ID_COMPETENCIA = ?";
	
	private static final String UPDATE_COMPETENCIA = "UPDATE pruebacomp.COMPETENCIA SET "
			+ "FECHA_INICIO = ?, ESTADO = ?, NOMBRE_DEPORTE = ?, USUARIO_DUENO = ?, "
			+ "PERMITE_EMPATE = ?, NOMBRE = ?, FECHA_FIN = ?, FORMA_PUNTUACION = ? "
			+ "WHERE id_competencia = ?";
	
	private static final String DELETE_COMPETENCIA = "DELETE FROM pruebacomp.COMPETENCIA WHERE "
			+ "'id_competencia' = ?";
	
	
	
	private FormaDePuntuacionDAO daoFP = new FormaDePuntuacionDAOimpl();
	private DisponibilidadDAO daoDisp = new DisponibilidadDAOimpl();
	private ParticipanteDAO daoP = new ParticipanteDAOimpl();
	private FixtureDAO daoF = new FixtureDAOimpl();
	private DeporteDAO daoD = new DeporteDAOimpl();
	
	public void saveOrUpdate(Competencia c) throws SQLException{
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet generatedKeys = null;
		Integer keyFP = null;
		Integer keyComp = null;
		try {
			conn.setAutoCommit(false);
			System.out.println(c.getFormaPuntuacion());
			keyFP = daoFP.saveOrUpdate(c.getFormaPuntuacion(), conn);
			if(c.getIdCompetencia() != null){
				System.out.println("EL ID NO ES NULL");
                pstmt = conn.prepareStatement(UPDATE_COMPETENCIA);
                if(c.getFechaInicio()==null)
                	pstmt.setDate(1, null);
                else
                	pstmt.setDate(1, Date.valueOf(c.getFechaInicio().toString()));
                
				pstmt.setString(2, c.getEstado());
				pstmt.setString(3, c.getDeporte().getNombreDeporte());
				pstmt.setInt(4, c.getUsuario().getIdUsuario());
				pstmt.setBoolean(5, c.getPermiteEmpate());
				pstmt.setString(6, c.getNombre());
				
				if(c.getFechaFin()==null)
					pstmt.setDate(7, null);
				else
					pstmt.setDate(7, Date.valueOf(c.getFechaFin().toString()));
				
				pstmt.setInt(8, c.getFormaPuntuacion().getIdFormaPuntuacion());
				pstmt.setInt(9, c.getIdCompetencia());
				pstmt.executeUpdate();
				System.out.println("A ver si es liga..");
				if(c instanceof Liga) {
					pstmt1 = conn.prepareStatement(UPDATE_LIGA);
					pstmt1.setInt(1, ((Liga)c).getPuntosPartidoGanado());
					pstmt1.setInt(2, ((Liga)c).getPuntosPartidoEmpatado());
					pstmt1.setInt(3, c.getIdCompetencia());
					pstmt1.executeUpdate();
					
					
					//borrar fixture si se cambió de estado 
					
				}	
				
				daoF.delete(conn, c.getIdCompetencia());
				System.out.println("GUARDO EL FIXTURE...");
				daoF.saveOrUpdate(conn, c.getFixture());
				
				for(Participante p : c.getParticipantes()) {
				daoP.saveOrUpdate(p);
			}
				
			}
			else {
				pstmt = conn.prepareStatement(INSERT_COMPETENCIA, java.sql.Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, c.getEstado());
				pstmt.setString(2, c.getDeporte().getNombreDeporte());
				pstmt.setInt(3, c.getUsuario().getIdUsuario());
				pstmt.setBoolean(4, c.getPermiteEmpate());
				pstmt.setString(5, c.getNombre());
				pstmt.setInt(6, keyFP);
				pstmt.setString(7, c.getReglamento());
				pstmt.execute();
				System.out.println("HIZO UN INSERT XD");
				generatedKeys = pstmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					if(c.getClass() == Liga.class) {
						pstmt1 = conn.prepareStatement(INSERT_LIGA);
						pstmt1.setInt(2, ((Liga) c).getPuntosPartidoGanado());
						pstmt1.setInt(3, ((Liga) c).getPuntosPartidoEmpatado());
				}
					else if(c.getClass() == EliminacionSimple.class) {
						pstmt1 = conn.prepareStatement(INSERT_ELIM_SIMPLE);
					}
					else {
						pstmt1 = conn.prepareStatement(INSERT_ELIM_DOBLE);
					}
					
					keyComp = generatedKeys.getInt(1);
					c.setIdCompetencia(keyComp);
					pstmt1.setInt(1, keyComp);
					pstmt1.execute();
			}
				
			}

			for(DisponiblePara disp : c.getDisponibleParas()) {
				disp.setCompetencia(c);
				System.out.println(c.getIdCompetencia());
				daoDisp.saveOrUpdate(disp, conn);
			}

		
				
			
			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		}
		try {
			if(pstmt!=null)pstmt.close();
			if(pstmt1!=null)pstmt1.close();
			if(conn!=null)conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Competencia> selectCompetenciaByFilters(Competencia c){
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		List<Competencia> competencias = new ArrayList<Competencia>();
		String query;
		ResultSet res = null;
		//NOMBRE DEPORTE MODALIDAD ESTADO
		try {
			conn.setAutoCommit(false);
			String filtros = "";
			if(c.getEstado() != "<Ninguno>") {
				filtros += "AND estado='" + c.getEstado().toUpperCase() + "' ";
			}
				
			
			if(!c.getNombre().equals("")) {
					filtros += "AND nombre='" + c.getNombre() + "' ";

			}
				
			
			if(c.getDeporte().getNombreDeporte() != "<Ninguno>") {
					filtros += "AND nombre_deporte='" + c.getDeporte().getNombreDeporte() + "' ";
			}
				
			
			if(c instanceof Liga) {
				pstmt = conn.prepareStatement(SELECT_BY_FILTERS + "INNER JOIN pruebacomp.liga as LI ON COM.id_competencia=LI.id_competencia WHERE usuario_dueno=" + GestorUsuario.usuario_autenticado + " " + filtros );	
				res = pstmt.executeQuery();
				competencias.addAll(armarListaDeCompes(res, "Liga"));
			}
			else {
				if(c instanceof EliminacionSimple) {
					pstmt = conn.prepareStatement(SELECT_BY_FILTERS + "INNER JOIN pruebacomp.eliminacion_simple as LI ON COM.id_competencia=LI.id_competencia WHERE usuario_dueno=" + GestorUsuario.usuario_autenticado + " "+ filtros );	
					res = pstmt.executeQuery();
					competencias.addAll(armarListaDeCompes(res, "Eliminacion Simple"));

				}
				else {
					if(c instanceof EliminacionDoble) {
						pstmt = conn.prepareStatement(SELECT_BY_FILTERS + "INNER JOIN pruebacomp.eliminacion_doble as LI ON COM.id_competencia=LI.id_competencia WHERE usuario_dueno=" + GestorUsuario.usuario_autenticado + " "+ filtros );
						res = pstmt.executeQuery();
						competencias.addAll(armarListaDeCompes(res, "Eliminacion Doble"));

					}
					else {
							pstmt = conn.prepareStatement(SELECT_BY_FILTERS + "INNER JOIN pruebacomp.liga as LI ON COM.id_competencia=LI.id_competencia WHERE usuario_dueno=" + GestorUsuario.usuario_autenticado + " " + filtros);
							res = pstmt.executeQuery();
							competencias.addAll(armarListaDeCompes(res, "Liga"));
							pstmt = conn.prepareStatement(SELECT_BY_FILTERS + "INNER JOIN pruebacomp.eliminacion_simple as LI ON COM.id_competencia=LI.id_competencia WHERE usuario_dueno=" + GestorUsuario.usuario_autenticado + " " + filtros );
							res = pstmt.executeQuery();
							competencias.addAll(armarListaDeCompes(res, "Eliminacion Simple"));
							pstmt = conn.prepareStatement(SELECT_BY_FILTERS + "INNER JOIN pruebacomp.eliminacion_doble as LI ON COM.id_competencia=LI.id_competencia WHERE usuario_dueno=" + GestorUsuario.usuario_autenticado + " " + filtros );
							res = pstmt.executeQuery();
							competencias.addAll(armarListaDeCompes(res, "Eliminacion Doble"));
							
						
					}
				}
			}
			
			
			conn.commit();
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return competencias;
		
	}
	
	
	private List<Competencia> armarListaDeCompes(ResultSet res, String modalidad){
		List<Competencia> competencias = new ArrayList<Competencia>();
		try {
			if(!res.next())
				return competencias;
			else {
				do {
					//Nombre deporte modalidad y estado
					Competencia comp = null;
					switch(modalidad) {
					case "Liga":
						comp = new Liga();
						break;
					case "Eliminacion Simple":
						comp = new EliminacionSimple();
						break;
					case "Eliminacion Doble":
						comp = new EliminacionDoble();
						break;
					}
					
					
					comp.setIdCompetencia(res.getInt("id_competencia"));
					comp.setNombre(res.getString("nombre"));
					comp.setDeporte(new Deporte(res.getString("nombre_deporte")));
					comp.setEstado(res.getString("estado"));
					
					competencias.add(comp);
					
				} while(res.next());				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return competencias;
	}
	
//	public Integer saveOrUpdate(Competencia c) {
//		Connection conn = DB.getConexion();
//		PreparedStatement pstmt = null;
//		PreparedStatement pstmt1 = null;
//		ResultSet generatedKeys = null;	
//		Integer key = null;
//		try {
//			if(c.getIdCompetencia() == null){
//				pstmt = conn.prepareStatement(INSERT_COMPETENCIA, java.sql.Statement.RETURN_GENERATED_KEYS);
//				pstmt.setString(1, c.getEstado());
//				pstmt.setString(2, c.getDeporte().getNombreDeporte());
//				pstmt.setInt(3, c.getUsuario().getIdUsuario());
//				pstmt.setBoolean(4, c.getPermiteEmpate());
//				pstmt.setString(5, c.getNombre());
//				pstmt.setInt(6, c.getFormaPuntuacion().getIdFormaPuntuacion());
//				pstmt.setString(7, c.getReglamento());
//				pstmt.execute();
//				
//				generatedKeys = pstmt.getGeneratedKeys();
//				if(generatedKeys.next()) {
//					if(c.getClass() == Liga.class) {
//						pstmt1 = conn.prepareStatement(INSERT_LIGA);
//						pstmt1.setInt(2, ((Liga) c).getPuntosPartidoGanado());
//						pstmt1.setInt(3, ((Liga) c).getPuntosPartidoEmpatado());
//				}
//					else if(c.getClass() == EliminacionSimple.class) {
//						pstmt1 = conn.prepareStatement(INSERT_ELIM_SIMPLE);
//					}
//					else {
//						pstmt1 = conn.prepareStatement(INSERT_ELIM_DOBLE);
//					}
//					
//					key = generatedKeys.getInt(1);
//					pstmt1.setInt(1, key);
//					pstmt1.execute();
//			}
//				else key = -1;
//			}
//			else {
//				pstmt = conn.prepareStatement(UPDATE_COMPETENCIA);
//			
//				pstmt.setDate(1, Date.valueOf(c.getFechaInicio().toString()));
//				pstmt.setString(2, c.getEstado());
//				pstmt.setString(3, c.getDeporte().getNombreDeporte());
//				pstmt.setInt(4, c.getUsuario().getIdUsuario());
//				pstmt.setBoolean(5, c.getPermiteEmpate());
//				pstmt.setString(6, c.getNombre());
//				pstmt.setDate(7, Date.valueOf(c.getFechaFin().toString()));
//				pstmt.setInt(8, c.getFormaPuntuacion().getIdFormaPuntuacion());
//				pstmt.setInt(9, c.getIdCompetencia());
//				pstmt.execute();
//				
//				if(c.getClass() == Liga.class) {
//					pstmt1 = conn.prepareStatement(UPDATE_LIGA);
//					pstmt1.setInt(1, ((Liga)c).getPuntosPartidoGanado());
//					pstmt1.setInt(2, ((Liga)c).getPuntosPartidoEmpatado());
//					pstmt1.setInt(3, c.getIdCompetencia());
//				}
//			}
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			if(pstmt!=null)pstmt.close();
//			if(conn!=null)conn.close();
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return key;
//	}
	
	//@Override
	public List<Competencia> buscarPorUsr(int id_participante){
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query;
		List<Competencia> lista = new ArrayList<Competencia>();
		try {
			
			query = SELECT_COMPETENCIA_BYUSR + "LIGA" + SELECT_COMPETENCIA_BYUSR2;		
			System.out.println(query);
			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
			//pstmt.setString(1, "LIGA");
			pstmt.setInt(1, id_participante);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					Competencia c = new Liga();
					c.setNombre(rs.getString("NOMBRE"));
					System.out.println(rs.getString("NOMBRE"));
					c.setEstado(rs.getString("ESTADO"));
					c.setIdCompetencia(rs.getInt("ID_COMPETENCIA"));
					c.setDeporte(new Deporte(rs.getString("NOMBRE_DEPORTE")));
					lista.add(c);
				}



			query = SELECT_COMPETENCIA_BYUSR + "ELIMINACION_SIMPLE" + SELECT_COMPETENCIA_BYUSR2;
			System.out.println(query);
			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id_participante);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					Competencia c = new EliminacionSimple();
					c.setNombre(rs.getString("NOMBRE"));
					System.out.println(rs.getString("NOMBRE"));
					c.setEstado(rs.getString("ESTADO"));
					c.setIdCompetencia(rs.getInt("ID_COMPETENCIA"));
					c.setDeporte(new Deporte(rs.getString("NOMBRE_DEPORTE")));
					lista.add(c);
				}



			query = SELECT_COMPETENCIA_BYUSR + "ELIMINACION_DOBLE" + SELECT_COMPETENCIA_BYUSR2;
			System.out.println(query);
			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id_participante);
			rs = pstmt.executeQuery();
				while(rs.next()) {
					Competencia c = new EliminacionDoble();
					c.setNombre(rs.getString("NOMBRE"));
					System.out.println(rs.getString("NOMBRE"));
					c.setEstado(rs.getString("ESTADO"));
					c.setIdCompetencia(rs.getInt("ID_COMPETENCIA"));
					c.setDeporte(new Deporte(rs.getString("NOMBRE_DEPORTE")));
					lista.add(c);
				}
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(lista.size());
		return lista;
	}
	
	public void delete(int id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_COMPETENCIA);
			pstmt.setInt(1, id);
			pstmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public List<Competencia> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	public Competencia buscarPorId(int id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Competencia c = null;
		String query;
		try {
			query = SELECT_COMPETENCIA + "LIGA" + SELECT_COMPETENCIA2;		
			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.first()) {
				c = new Liga();
				((Liga)c).setPuntosPartidoGanado(rs.getInt("PUNTOS_PARTIDO_GANADO"));
				((Liga)c).setPuntosPartidoEmpatado(rs.getInt("PUNTOS_PARTIDO_EMPATADO"));
			}
			else {
				query = SELECT_COMPETENCIA + "ELIMINACION_SIMPLE" + SELECT_COMPETENCIA2;		
				pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				if(rs.first()) {
					c = new EliminacionSimple();
				}
				else {
					query = SELECT_COMPETENCIA + "ELIMINACION_DOBLEs" + SELECT_COMPETENCIA2;		
					pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
					pstmt.setInt(1, id);
					rs = pstmt.executeQuery();
					if(rs.first()) {
						c = new EliminacionDoble();
				}
			}
			}
		c.setIdCompetencia(rs.getInt("ID_COMPETENCIA"));
		c.setFechaInicio(rs.getDate("FECHA_INICIO"));
		c.setFechaFin(rs.getDate("FECHA_FIN"));
		c.setNombre(rs.getString("NOMBRE"));
		c.setPermiteEmpate(rs.getBoolean("PERMITE_EMPATE"));
		c.setDeporte(new Deporte(rs.getString("NOMBRE_DEPORTE")));
		c.setEstado(rs.getString("ESTADO"));
		c.setParticipantes((ArrayList<Participante>) daoP.buscar(c.getIdCompetencia(),conn));
		c.setDisponibleParas((ArrayList<DisponiblePara>) daoDisp.buscarConIdCompe(id, conn));
		c.setUsuario(new Usuario(GestorUsuario.usuario_autenticado));
		c.setFormaPuntuacion(daoFP.buscarPorId(rs.getInt("forma_puntuacion"),conn));
		c.setFixture(daoF.buscarPorIdCompetencia(id,conn));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}


	public Competencia buscarNombreYParticipantes(int idCompetencia) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Competencia c = new Competencia();
		try {
			pstmt = conn.prepareStatement(SELECT_NOMBRE_COMPETENCIA,ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, idCompetencia);
			rs = pstmt.executeQuery();
			if(!rs.first()) System.out.println("no existe");
			
			c.setIdCompetencia(idCompetencia);
			c.setNombre(rs.getString("nombre"));
			c.setParticipantes((ArrayList<Participante>) daoP.buscar(idCompetencia,conn));
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return c;
	}
		
	}
	

