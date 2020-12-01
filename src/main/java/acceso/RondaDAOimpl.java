package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logica.Encuentro;
import logica.Ronda;

public class RondaDAOimpl implements RondaDAO {

	private static final String INSERT_RONDA = "INSERT INTO pruebacomp.RONDA (id_fixture) values (?)";
	
	private EncuentroDAO daoE = new EncuentroDAOimpl();
	
	@Override
	public Ronda saveOrUpdate(Connection conn, Ronda r) {
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;
		try {
			if(r.getIdRonda()==null) {
			pstmt = conn.prepareStatement(INSERT_RONDA, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, r.getFixture().getIdFixture());
			}
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(generatedKeys.next()) r.setIdRonda(generatedKeys.getInt(1));
			
			for(Encuentro e : r.getEncuentros()) {
				daoE.saveOrUpdate(conn, e);
			}
			
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		finally {
			try {
				if(pstmt!=null) pstmt.close();	
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return r;
	}

	@Override
	public void delete(Connection conn, int id) {
		// TODO Auto-generated method stub

	}

}
