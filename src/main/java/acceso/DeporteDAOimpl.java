package acceso;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Deporte;

public class DeporteDAOimpl implements DeporteDAO {

		private static final String INSERT_DEPORTE = "INSERT INTO pruebacomp.deporte VALUES"
				+ " (?)";
		private static final String UPDATE_DEPORTE = "UPDATE pruebacomp.deporte SET"
				+ " WHERE NOMBRE = ?"; 
		private static final String DELETE_DEPORTE = "DELETE FROM pruebacomp.deporte WHERE "
				+ "NOMBRE = ?";
		private static final String SELECT_DEPORTE = "SELECT * FROM pruebacomp.deporte WHERE"
				+ " NOMBRE = ?";
		private static final String SELECT_ALL_DEPORTES = "SELECT * FROM pruebacomp.deporte";
		
		public Deporte saveOrUpdate(Deporte d) {
			Connection conn = DB.getConexion();
			PreparedStatement pstmt = null;
			try {
				if(d.getNombreDeporte().isBlank()) {
					pstmt = conn.prepareStatement(INSERT_DEPORTE);
					pstmt.setString(1, d.getNombreDeporte());
				}
				else {
					pstmt = conn.prepareStatement(UPDATE_DEPORTE);
				
				//setear parametros
				}
				pstmt.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return d;
		}

		public void delete(Integer id) {
			Connection conn = DB.getConexion();
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(DELETE_DEPORTE);
				pstmt.setInt(1, id);
				pstmt.execute();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		public ArrayList<Deporte> buscarTodos() {
			Connection conn = DB.getConexion();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<Deporte> Listadeportes = new ArrayList<Deporte>();
			try {
				pstmt = conn.prepareStatement(SELECT_ALL_DEPORTES,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = pstmt.executeQuery();
				if(!rs.first()) {System.out.println("no existe");}
				else
				do {
					Deporte d = new Deporte();
					d.setNombreDeporte(rs.getString("nombre_deporte"));
					Listadeportes.add(d);
				}
				while(rs.next());
					
				
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
			
			return Listadeportes;
		}

		public Deporte buscarPorNombre(String nombre) {
			Connection conn = DB.getConexion();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Deporte d = null;
			try {
				pstmt = conn.prepareStatement(SELECT_DEPORTE,ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, nombre);
				rs = pstmt.executeQuery();
				if(!rs.first()) System.out.println("no existe");;
				d = new Deporte();
			d.setNombreDeporte(rs.getString("NOMBRE"));
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
			return d;
		}
			
	}

