package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {

	public void create(Studente s){
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO `iscritticorsi`.`studente` (`matricola`, `cognome`, `nome`, `CDS`) "
				+ "VALUES (?, ?, ?, ?);";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(s.getMatricola()));
			st.setString(2, s.getCognome());
			st.setString(3, s.getNome());
			st.setString(4, s.getCDS());
			
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * legge da db lo studente con @param matricola = a s.matricla e poi riepie tutti i campi ad essa legati
	 * se non lo trova 
	 * 
	 * @return
	 */
	public Studente read(int matricola){
		return null;
	}
	/**
	 * questo cerca solo, verifica presenza studente, true se non c'è invece false
	 * @param s
	 * @return
	 */
	public boolean read(Studente s){
		return false;
	}
	public void updateCds(Studente s){}
	public List<Studente> searchByNome(String nome){
		return null;
	}
}
