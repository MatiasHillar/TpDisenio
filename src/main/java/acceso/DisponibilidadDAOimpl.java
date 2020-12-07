/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Competencia;
import logica.DisponiblePara;
import logica.Encuentro;
import logica.LugarRealizacion;


public class DisponibilidadDAOimpl implements DisponibilidadDAO {
	private static final String INSERT_DISPO = "INSERT INTO pruebacomp.disponible_para VALUES (?,?,?)";
	private static final String SELECT_DISPO_COMPE = "SELECT * FROM pruebacomp.disponible_para WHERE id_competencia=?";
	private static final String SELECT_DISPO = "SELECT * FROM pruebacomp.disponible_para WHERE"
			+ " id_competencia = ? AND id_lugar = ? AND cant_encuentros = ?";
	
	public DisponiblePara saveOrUpdate(DisponiblePara d, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			if(!checkNull(d.getCompetencia().getIdCompetencia(), d.getLugarRealizacion().getIdLugar(),
					d.getCantidadEncuentros(), conn)) {
				pstmt = conn.prepareStatement(INSERT_DISPO);
				pstmt.setInt(1, d.getLugarRealizacion().getIdLugar());
				pstmt.setInt(2, d.getCompetencia().getIdCompetencia());
				pstmt.setInt(3, d.getCantidadEncuentros());

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
		
		return d;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<DisponiblePara> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<DisponiblePara> buscarConIdCompe(int id, Connection conn){
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<DisponiblePara> dispos = new ArrayList<DisponiblePara>();
		
		try {
			pstmt = conn.prepareStatement(SELECT_DISPO_COMPE, ResultSet.TYPE_SCROLL_INSENSITIVE,    ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			if(!res.next()) {
				return dispos;
			}
			else {
				do {
					DisponiblePara e = new DisponiblePara();
					e.setLugarRealizacion(new LugarRealizacion(res.getInt("ID_LUGAR")));
					e.setCantidadEncuentros(res.getInt("cant_encuentros"));		
					dispos.add(e);
					
				} while(res.next());		
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dispos;
	}
	
	public int buscarDisponibilidad(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean checkNull(int id_competencia, int id_lugar, int cant_encuentros, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean ret = false;
		try {
			pstmt = conn.prepareStatement(SELECT_DISPO, ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id_competencia);
			pstmt.setInt(2, id_lugar);
			pstmt.setInt(3, cant_encuentros);
			rs = pstmt.executeQuery();
			ret = rs.first();
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
		return ret;
	}
}
