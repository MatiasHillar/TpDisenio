/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import logica.Localidad;
import logica.Provincia;

/**
 * @author Pichi
 *
 */
public class ProvinciaDAOimplSQL implements ProvinciaDAO{
	
	
	private static final String INSERT_PROVINCIA = "INSERT INTO pruebacomp.PROVINCIA (nombre) VALUES (?)";
	private static final String SELECT_PROV = "SELECT * FROM pruebacomp.PROVINCIA WHERE nombre=?";

	
	@Override
	public List<Provincia> buscarProvincias() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Provincia saveOrUpdate(Connection conn, Provincia p) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;
		
		try {
			int idProv = checkNull(conn, p);
			if(idProv == -1) {
				pstmt = conn.prepareStatement(INSERT_PROVINCIA, java.sql.Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, p.getNombre());
				pstmt.executeUpdate();
				generatedKeys = pstmt.getGeneratedKeys();
				
				p.setId_provincia(generatedKeys.getInt(1));
			}
			else {	
				p.setId_provincia(idProv);	
			}
			
			
		}
		catch(SQLException e) {
			throw e;
		}
		finally {
			try {
				if(pstmt!=null)
					pstmt.close();
			}
			catch(SQLException e) {
				throw e;
			}
		}
		
		return p;
		
	}


	private int checkNull(Connection conn, Provincia p) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean ret = false;
		int  id = -1;
		try {
			pstmt = conn.prepareStatement(SELECT_PROV, ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, p.getNombre());

			rs = pstmt.executeQuery();
			ret = rs.first();
			if(ret)
				id = rs.getInt("id_provincia");
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
