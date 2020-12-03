package acceso;


import java.sql.Connection;

import logica.Unset;

public interface SetDAO {

	public Unset saveOrUpdate(Connection conn, Unset s);
	public void delete (Connection conn, int id_set);
	
}
