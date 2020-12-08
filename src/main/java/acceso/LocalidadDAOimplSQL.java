/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logica.Localidad;
import logica.Provincia;


public class LocalidadDAOimplSQL implements LocalidadDAO{
	
	private static final String INSERT_LOCALIDAD = "INSERT INTO pruebacomp.LOCALIDAD (id_provincia, nombre)"
			+ " VALUES (?,?)";
	private static final String SELECT_LOCALIDAD = "SELECT * FROM pruebacomp.LOCALIDAD WHERE nombre=?";
	
	@Override
	public ArrayList<Localidad> buscarPorProvincia(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	



	@Override
	public Localidad saveOrUpdate(Connection conn, Localidad l) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;
		int idLoc;
		try {
			idLoc = checkNull(conn, l);
			if(idLoc == -1) {
			pstmt = conn.prepareStatement(INSERT_LOCALIDAD, Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, l.getProvincia().getId_provincia());
			pstmt.setString(2, l.getNombre());
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			
			if(generatedKeys.next())
				l.setIdLocalidad(generatedKeys.getInt(1));
			else
				System.out.println("NA SE ROMPIO TODO AMEGO");
			
			}
			
			else {
				l.setIdLocalidad(idLoc);
			}
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			try {
				if(pstmt!=null)pstmt.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return l;
	}
	
	private int checkNull(Connection conn, Localidad l) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean ret = false;
		int  id = -1;
		try {
			pstmt = conn.prepareStatement(SELECT_LOCALIDAD, ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, l.getNombre());

			rs = pstmt.executeQuery();
			ret = rs.first();
			if(ret)
				id = rs.getInt("id_localidad");
			else
				id = -1;
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
		
		
		
		return id;
	}

}
