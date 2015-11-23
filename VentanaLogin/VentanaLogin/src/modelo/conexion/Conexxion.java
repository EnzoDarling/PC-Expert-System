package modelo.conexion;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexxion {
	private String driver="org.postgresql.Driver";
	private String nombreBd="Concesionaria";
	private String usuario="postgres";
	private String password="34448544";
	private String url="jdbc:postgresql://localhost:5432/Concesionaria";

	Connection conn=null;
	//constructor de la clase
	public Conexxion(){
		try {
			//obtener el driver
			Class.forName(driver);
			//obtener la conexion
			conn=DriverManager.getConnection(url,usuario,password);
			if (conn!=null) {
				System.out.println("Conexion Exitosa  a la BD: "+nombreBd);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("ocurre una SQLException: "+e.getMessage());
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public void desconectar(){
		conn=null;
	}
}

