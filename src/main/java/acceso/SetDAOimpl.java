package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logica.Unset;

public class SetDAOimpl implements SetDAO {

	private static final String INSERT_SET = "INSERT INTO pruebacomp.SET (id_resultado_set, puntos_participante1,"
			+ " puntos_participant2, VALUES (?, ?, ?)";
	
	private static final String DELETE_SET = "DELETE FROM pruebacomp.SET WHERE ID_SET = ?";
	
	@Override
	public Unset saveOrUpdate(Connection conn, Unset s) {
		PreparedStatement pstmt = null;
		try {
			if(s.getIdSet()==null) {
			pstmt = conn.prepareStatement(INSERT_SET);
			pstmt.setInt(1, s.getResultado().getIdResultadoSet());
			pstmt.setInt(2, s.getTantosParticipante1());
			pstmt.setInt(3, s.getTantosParticipante2());
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	@Override
	public void delete(Connection conn, int id_set) {
		// TODO Auto-generated method stub

	}

}
