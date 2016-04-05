package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnect {
	
	private static final String jdbcUrl="jdbc:mysql//localhost/iscritticorsi?user=root&password=";
	
	public static Connection getConnection(){
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("errore connessione", e);
		}
		
	}

}
