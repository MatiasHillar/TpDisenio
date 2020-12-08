package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logica.Competencia;
import logica.Encuentro;
import logica.Fixture;
import logica.Participante;
import logica.Ronda;
import logica.TIPO_RONDA;

public class RondaDAOimpl implements RondaDAO {

	private static final String INSERT_RONDA = "INSERT INTO pruebacomp.RONDA (id_fixture, tipo) values (?, ?::pruebacomp.tipo_ronda)";
	private static final String SELECT_RONDAS_FIXTURE = "SELECT * FROM pruebacomp.RONDA WHERE id_fixture = ?";
	private EncuentroDAO daoE = new EncuentroDAOimpl();
	
	@Override
	public Ronda saveOrUpdate(Connection conn, Ronda r) {
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;
		try {
			if(r.getIdRonda()==null) {
			pstmt = conn.prepareStatement(INSERT_RONDA, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, r.getFixture().getIdFixture());
			pstmt.setString(2, TIPO_RONDA.UNICA.toString());
			}
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(generatedKeys.next()) r.setIdRonda(generatedKeys.getInt(1));
			
			for(Encuentro e : r.getEncuentros()) {
				e.setRonda(r);
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
	
	@Override
	public List<Ronda> buscarPorIdFixture(int idFixture, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Ronda> rondas = new ArrayList<Ronda>();
		
		try {
			pstmt = conn.prepareStatement(SELECT_RONDAS_FIXTURE);
			pstmt.setInt(1, idFixture);
			rs = pstmt.executeQuery();
			
			
			if(!rs.next()) {
				return rondas;
			}
			else {
				do {
					Ronda r = new Ronda();
					r.setIdRonda(rs.getInt("id_ronda"));
					r.setTipo(TIPO_RONDA.valueOf(rs.getString("tipo")));			
					r.setEncuentros((ArrayList<Encuentro>)daoE.buscarEncuentrosPorRonda(rs.getInt("id_ronda"),conn));
					rondas.add(r);
				} while(rs.next());		
			}
			
		}
		catch(SQLException e) {
			throw e;
		}
		finally {
			try {
				if(pstmt!=null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return rondas;
	}
}
