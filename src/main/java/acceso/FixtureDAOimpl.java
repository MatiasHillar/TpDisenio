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

	private static final String INSERT_FIXTURE = "INSERT INTO pruebacomp.FIXTURE (id_competencia) VALUES (?)";
	
	private static final String UPDATE_FIXTURE = "UPDATE pruebacomp.FIXTURE SET id_competencia = ?"
			+ " WHERE id_fixture = ?";
	
	private static final String DELETE_FIXTURE = "DELETE FROM pruebacomp.FIXTURE WHERE id_competencia = ?";
	
	private static final String SELECT_FIXTURE_COMP = "SELECT * FROM pruebacomp.FIXTURE WHERE id_competencia = ?";
	
	private RondaDAO daoR = new RondaDAOimpl();
	
	@Override
	public Fixture saveOrUpdate(Connection conn, Fixture f) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;
		try {
			if(f.getIdFixture()==null) {
			pstmt = conn.prepareStatement(INSERT_FIXTURE, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, f.getCompetencia().getIdCompetencia());
			}
			pstmt.executeUpdate();
			System.out.println("INSERTO");
			generatedKeys = pstmt.getGeneratedKeys();
			if(generatedKeys.next()) f.setIdFixture(generatedKeys.getInt(1));
			System.out.println(f.getIdFixture());
			for(Ronda r : f.getRonda()) {
				r.setFixture(f);
				daoR.saveOrUpdate(conn, r);
			}
		}
		catch(SQLException e) {
			throw e;
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
	
	@Override
	public Fixture buscarPorIdCompetencia(int idCompetencia, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Fixture f = new Fixture();
		
		try {
			pstmt = conn.prepareStatement(SELECT_FIXTURE_COMP);
			pstmt.setInt(1, idCompetencia);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				
				f = new Fixture(rs.getInt("id_fixture"), daoR.buscarPorIdFixture(rs.getInt("id_fixture"),conn));
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null)pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return f;
		
		
	}

}
