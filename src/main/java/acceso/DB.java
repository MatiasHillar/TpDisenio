package acceso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static final String url ="jdbc:postgresql://ec2-3-95-87-221.compute-1.amazonaws.com:5432/d8sunkmt4gfdaf";
	private static final String user="jmohkhfarehtza";
	private static final String pass="1e1b5c83e050a9c278ff90a47bc5ee6105837ffdbac8fa1553fb80a55ab08b77";
	private static boolean conexion = true;
	
	private static Connection crearConexion(){
		Connection conn=null;
		try {
			Class.forName("org.postgresql.Driver");
			conn= DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conexion = true;
		return conn;
	}
	

	public static Connection getConexion() {
		if(conexion) return crearConexion();
		return null;
	}
}