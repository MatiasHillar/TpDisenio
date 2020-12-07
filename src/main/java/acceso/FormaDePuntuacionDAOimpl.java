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
import logica.EliminacionDoble;
import logica.EliminacionSimple;
import logica.FormaPuntuacion;
import logica.Liga;
import logica.Puntuacion;
import logica.ResultadoFinal;
import logica.Sets;

public class FormaDePuntuacionDAOimpl implements FormaDePuntuacionDAO {

	private static final String SELECT_FORMA_PUNTUACION = "SELECT * FROM pruebacomp.FORMA_PUNTUACION as FP, pruebacomp.";
	
	private static final String SELECT_FORMA_PUNTUACION2 = " as Li WHERE FP.ID_FORMA_PUNTUACION = ? AND FP.ID_FORMA_PUNTUACION=Li.ID_FORMA_PUNTUACION";
	
	
	private static final String INSERT_FORMA_PUNTUACION = "INSERT INTO pruebacomp.FORMA_PUNTUACION VALUES (DEFAULT)";
	
	private static final String INSERT_PUNTUACION = "INSERT INTO pruebacomp.puntuacion "
			+ "(ID_FORMA_PUNTUACION, TANTOS_POR_DEFAULT) VALUES(?, ?)";
	
	private static final String INSERT_SETS = "INSERT INTO pruebacomp.sets "
			+ "(ID_FORMA_PUNTUACION, CANT_MAX_SETS) VALUES(?, ?)";
	
	private static final String INSERT_RES_FINAL = "INSERT INTO pruebacomp.resultado_final "
			+ "VALUES(?)";
	
	private static final String UPDATE_PUNTUACION = "UPDATE pruebacomp.puntuacion "
			+ "SET TANTOS_POR_DEFAULT = ? WHERE ID_FORMA_PUNTUACION = ?";
	
	private static final String UPDATE_SETS = "UPDATE pruebacomp.sets "
			+ "SET CANT_MAX_SETS = ? WHERE ID_FORMA_PUNTUACION = ?";

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
					if(f instanceof Puntuacion) {
						pstmt1 = conn.prepareStatement(INSERT_PUNTUACION);
						pstmt1.setInt(2, ((Puntuacion)f).getTantosPorDefault());
				}
					else if(f instanceof Sets) {
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
				if(f instanceof Puntuacion) {
					pstmt = conn.prepareStatement(UPDATE_PUNTUACION);
					pstmt.setInt(1, ((Puntuacion)f).getTantosPorDefault());
				}
				else if(f instanceof Sets) {
					pstmt = conn.prepareStatement(UPDATE_SETS);
					pstmt.setInt(1, ((Sets)f).getCantMaxSets());
				}
				System.out.println(f.getIdFormaPuntuacion());
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
	public FormaPuntuacion buscarPorId(int id, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query;
		FormaPuntuacion f = null;
		try {
			query = SELECT_FORMA_PUNTUACION + "PUNTUACION" + SELECT_FORMA_PUNTUACION2;		
			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.first()) {
				f = new Puntuacion();
				((Puntuacion) f).setTantosPorDefault(rs.getInt("tantos_por_default"));
				f.setIdFormaPuntuacion(id);
			}
			else {
				query = SELECT_FORMA_PUNTUACION + "SETS" + SELECT_FORMA_PUNTUACION2;			
				pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				if(rs.first()) {
					f = new Sets();
					((Sets) f).setCantMaxSets(rs.getInt("cant_max_sets"));
					f.setIdFormaPuntuacion(id);
					
				}
				else {
					query = SELECT_FORMA_PUNTUACION + "RESULTADO_FINAL" + SELECT_FORMA_PUNTUACION2;			
					pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
					pstmt.setInt(1, id);
					rs = pstmt.executeQuery();
					if(rs.first()) {
						f = new ResultadoFinal();
						f.setIdFormaPuntuacion(id);
				}
			}
			}
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
		return f;
	}
	} 
	



