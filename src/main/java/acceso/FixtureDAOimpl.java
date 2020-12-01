package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

import logica.Competencia;
import logica.Fixture;
import logica.Participante;
import logica.Ronda;

public class FixtureDAOimpl implements FixtureDAO {

	private static final String INSERT_FIXTURE = "INSERT INTO pruebacomp.FIXURE id_competencia VALUES (?)";
	
	private static final String UPDATE_FIXTURE = "UPDATE pruebacomp.FIXTURE SET id_competencia = ?"
			+ " WHERE id_fixture = ?";
	
	private static final String DELETE_FIXTURE = "DELETE FROM pruebacomp.FIXTURE WHERE id_competencia = ?";
	
	private static final String SELECT_FIXTURE_COMP = "SELECT * FROM pruebacomp.FIXTURE WHERE id_competencia = ?";
	
	private RondaDAO daoR = new RondaDAOimpl();
	
	@Override
	public Fixture saveOrUpdate(Connection conn, Fixture f) {
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;
		try {
			if(f.getIdFixture()==null) {
			pstmt = conn.prepareStatement(INSERT_FIXTURE);
			pstmt.setInt(1, f.getCompetencia().getIdCompetencia());
		}
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(generatedKeys.next()) f.setIdFixture(generatedKeys.getInt(1));
			for(Ronda r : f.getRonda()) {
				daoR.saveOrUpdate(conn, r);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!= null) pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	return f;
	}


	@Override
	public void delete(Connection conn, int idCompetencia) {
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_FIXTURE);
			pstmt.setInt(1, idCompetencia);
			pstmt.execute();
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

	}

	@Override
	public Fixture buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Fixture buscarPorIdCompetencia(int idCompetencia) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Fixture f = new Fixture();
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SELECT_FIXTURE_COMP);
			pstmt.setInt(1, idCompetencia);
			ResultSet res = pstmt.executeQuery();
			
			
			if(res.next()) {
				
				f = new Fixture(res.getInt(1), ((RondaDAOimpl) daoR).buscarPorIdFixture(res.getInt(1)));
			}
			conn.commit();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return f;
		
		
	}

}
