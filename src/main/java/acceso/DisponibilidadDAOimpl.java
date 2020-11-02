/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import logica.DisponiblePara;

/**
 * @author Pichi
 *
 */
public class DisponibilidadDAOimpl implements DisponibilidadDAO {
	private static final String INSERT_DISPO = "INSERT INTO pruebacomp.disponible_para VALUES (?,?,?)";
	
	
	public DisponiblePara saveOrUpdate(DisponiblePara d, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT_DISPO);
			pstmt.setInt(1, d.getLugarRealizacion().getIdLugar());
			pstmt.setInt(2, d.getCompetencia().getIdCompetencia());
			pstmt.setInt(3, d.getCantidadEncuentros());

			pstmt.executeUpdate();
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
		
		return d;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<DisponiblePara> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public int buscarDisponibilidad(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
