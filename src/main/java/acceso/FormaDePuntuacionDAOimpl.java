package acceso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import excepciones.NoExisteException;
import logica.Competencia;
import logica.EliminacionSimple;
import logica.FormaPuntuacion;
import logica.Liga;
import logica.Puntuacion;
import logica.ResultadoFinal;
import logica.Sets;

public class FormaDePuntuacionDAOimpl implements FormaDePuntuacionDAO {

	private static final String SELECT_FORMA_PUNTUACION = "SELECT * FROM pruebacomp.FORMA_PUNTUACION WHERE "
			+ "ID_FORMA_PUNTUACION = ?";
	
	private static final String INSERT_FORMA_PUNTUACION = "INSERT INTO pruebacomp.FORMA_PUNTUACION VALUES (DEFAULT)";
	
	private static final String INSERT_PUNTUACION = "INSERT INTO pruebacomp.puntuacion "
			+ "(ID_FORMA_PUNTUACION, TANTOS_POR_DEFAULT) VALUES(?, ?)";
	
	private static final String INSERT_SETS = "INSERT INTO pruebacomp.sets "
			+ "(ID_FORMA_PUNTUACION, CANT_MAX_SETS) VALUES(?, ?)";
	
	private static final String INSERT_RES_FINAL = "INSERT INTO pruebacomp.resultado_final "
			+ "VALUES(?)";
	
	private static final String UPDATE_PUNTUACION = "UPDATE pruebacomp.puntuacion "
			+ "SET TANTOS_POR_DEFAULT = ? WHERE ID_FORMA_PUNTACION = ?";
	
	private static final String UPDATE_SETS = "UPDATE pruebacomp.sets "
			+ "SET CANT_MAX_SETS = ? WHERE ID_FORMA_PUNTACION = ?";

	//	private static final String UPDATE_RES_FINAL = "UPDATE pruebacomp.forma_puntuacion SET"
//			+ " WHERE ID_FORMA_PUNTACION = ?";
	
	private static final String DELETE_FORMA_PUNTUACION = "DELETE FROM pruebacomp.forma_puntuacion"
			+ " WHERE ID_FORMA_PUNTUACION = ?";
	
	
	public Integer saveOrUpdate(FormaPuntuacion f, Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet generatedKeys = null;
		Integer key = null;
		try {
			if(f.getIdFormaPuntuacion() == null){
				pstmt = conn.prepareStatement(INSERT_FORMA_PUNTUACION, Statement.RETURN_GENERATED_KEYS);
				pstmt.executeUpdate();
				
				generatedKeys = pstmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					if(f.getClass() == Puntuacion.class) {
						pstmt1 = conn.prepareStatement(INSERT_PUNTUACION);
						pstmt1.setInt(2, ((Puntuacion)f).getTantosPorDefault());
				}
					else if(f.getClass() == Sets.class) {
						pstmt1 = conn.prepareStatement(INSERT_SETS);
						pstmt1.setInt(2, ((Sets)f).getCantMaxSets());
					}
					else {
						pstmt1 = conn.prepareStatement(INSERT_RES_FINAL);
					}
					
					key = generatedKeys.getInt(1);
					pstmt1.setInt(1, key);
					pstmt1.executeUpdate();
			}
				else key = -1;
			}
			else {
				if(f.getClass() == Puntuacion.class) {
					pstmt = conn.prepareStatement(UPDATE_PUNTUACION);
					pstmt.setInt(1, ((Puntuacion)f).getTantosPorDefault());
				}
				else if(f.getClass() == Sets.class) {
					pstmt = conn.prepareStatement(UPDATE_SETS);
					pstmt.setInt(1, ((Sets)f).getCantMaxSets());
				}
				pstmt.setInt(2, f.getIdFormaPuntuacion());
				pstmt.executeUpdate();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null)pstmt.close();
		}
		catch(SQLException e) {
			throw e;
		}
		
		return key;
	}
	
	
	public void delete(int id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_FORMA_PUNTUACION);
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
	public List<FormaPuntuacion> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	public FormaPuntuacion buscarPorId(int id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FormaPuntuacion f = null;
		try {
			pstmt = conn.prepareStatement(SELECT_FORMA_PUNTUACION,ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(!rs.first()) throw new NoExisteException();
			if(rs.getInt("ID_FORMA_PUNTUACION") == 0) {
				f = new Sets(rs.getInt("CANT_MAX_SETS"));
			}
			else if(rs.getInt("ID_FORMA_PUNTUACION") == 1) {
				f = new Puntuacion(rs.getInt("TANTOS_POR_DEFAULT"));
			}
			else {
				f = new ResultadoFinal();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(NoExisteException e) {
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
		return f;
	}
	} 
	



