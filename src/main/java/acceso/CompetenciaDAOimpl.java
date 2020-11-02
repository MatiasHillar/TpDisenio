package acceso;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logica.Competencia;
import logica.DisponiblePara;
import logica.EliminacionSimple;
import logica.Liga;



public class CompetenciaDAOimpl implements CompetenciaDAO{

	private static final String SELECT_COMPETENCIA = "SELECT * FROM pruebacomp.COMPETENCIA"
			+ " WHERE 'id_competencia = ?";
	
	private static final String INSERT_COMPETENCIA = "INSERT INTO pruebacomp.COMPETENCIA "

			+ "(ESTADO, NOMBRE_DEPORTE, USUARIO_DUENO, PERMITE_EMPATE, NOMBRE, FORMA_PUNTUACION, REGLAMENTO)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?)";

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
			+ "WHERE 'id_patente' = ?";
	
	private static final String DELETE_COMPETENCIA = "DELETE FROM pruebacomp.COMPETENCIA WHERE "
			+ "'id_competencia' = ?";
	
	private FormaDePuntuacionDAO daoFP = new FormaDePuntuacionDAOimpl();
	private DisponibilidadDAO daoDisp = new DisponibilidadDAOimpl();
	
	public void saveOrUpdate(Competencia c) throws SQLException{
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet generatedKeys = null;
		Integer keyFP = null;
		Integer keyComp = null;
		try {
			conn.setAutoCommit(false);
			keyFP = daoFP.saveOrUpdate(c.getFormaPuntuacion(), conn);
			if(c.getIdCompetencia() != null){
                pstmt = conn.prepareStatement(UPDATE_COMPETENCIA);
				pstmt.setDate(1, Date.valueOf(c.getFechaInicio().toString()));
				pstmt.setString(2, c.getEstado());
				pstmt.setString(3, c.getDeporte().getNombreDeporte());
				pstmt.setInt(4, c.getUsuario().getIdUsuario());
				pstmt.setBoolean(5, c.getPermiteEmpate());
				pstmt.setString(6, c.getNombre());
				pstmt.setDate(7, Date.valueOf(c.getFechaFin().toString()));
				pstmt.setInt(8, c.getFormaPuntuacion().getIdFormaPuntuacion());
				pstmt.setInt(9, c.getIdCompetencia());
				pstmt.executeUpdate();
				
				if(c.getClass() == Liga.class) {
					pstmt1 = conn.prepareStatement(UPDATE_LIGA);
					pstmt1.setInt(1, ((Liga)c).getPuntosPartidoGanado());
					pstmt1.setInt(2, ((Liga)c).getPuntosPartidoEmpatado());
					pstmt1.setInt(3, c.getIdCompetencia());
					pstmt1.executeUpdate();
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
			conn.rollback();
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
		try {
			pstmt = conn.prepareStatement(SELECT_COMPETENCIA,ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(!rs.first()) System.out.println("no existe");
			c = new Competencia();
		c.setIdCompetencia(rs.getInt("ID"));
		c.setFechaInicio(rs.getDate("FECHA_INICIO"));
		c.setFechaFin(rs.getDate("FECHA_FIN"));
		c.setNombre(rs.getString("NOMBRE"));
		c.setPermiteEmpate(rs.getBoolean("PERMITE_EMPATE"));
	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
		
	}
	

