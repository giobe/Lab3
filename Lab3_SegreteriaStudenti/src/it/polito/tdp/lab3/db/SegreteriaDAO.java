package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;




public class SegreteriaDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
	
	public Collection<Corso> caricaCorsi(){
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select * from corso";
			ResultSet res= st.executeQuery(sql);
			LinkedList<Corso> corsi = new LinkedList<Corso>();
			while(res.next()){
				//trovo qualcosa
				Corso c = new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"), res.getInt("pd"));
				corsi.add(c);
			}
			res.close();
				conn.close();
				return corsi;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Collection<Studente> StudentiPerCorso(String nomeCorso){
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select s.matricola,s.nome,cognome,CDS from studente s, iscrizione i, corso c  where c.codins=i.codins AND s.matricola=i.matricola AND c.nome=\""+nomeCorso+"\"";
			ResultSet res= st.executeQuery(sql);
			LinkedList<Studente> studenti = new LinkedList<Studente>();
			while(res.next()){
				//trovo qualcosa
				Studente s = new Studente(res.getString("matricola"),
						res.getString("cognome"),
						res.getString("nome"), res.getString("CDS"));
				studenti.add(s);
			}
			res.close();
				conn.close();
				return studenti;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Collection<Corso> CorsiPerStudente(String matricola){
		int m = Integer.parseInt(matricola);
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select i.codins,crediti,c.nome,pd from iscrizione i, corso c, studente s where i.matricola=\""+m+"\" AND s.matricola=i.matricola AND c.codins=i.codins";
			ResultSet res= st.executeQuery(sql);
			LinkedList<Corso> corsi = new LinkedList<Corso>();
			while(res.next()){
				//trovo qualcosa
				Corso c = new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"), res.getInt("pd"));
				corsi.add(c);
			}
			res.close();
				conn.close();
				return corsi;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean StudenteNelCorso(String matricola, String nomeCorso){
		int m = Integer.parseInt(matricola);
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select matricola, c.nome from iscrizione i, corso c where  c.codins=i.codins AND c.nome=\""+nomeCorso+"\" AND matricola=\""+m+"\"";
			ResultSet res= st.executeQuery(sql);
			
			if(res.next()){
				//trovo qualcosa
				res.close();
				conn.close();
				return true;
			}else{
				res.close();
				conn.close();
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean aggiungiIscrizione(int matricola, String codins){
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES ("+matricola+", '"+codins+"');";
			int res= st.executeUpdate(sql);
			
			conn.close();
			if(res==1)
				return true;
			else
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public Studente caricaDatiStudente(int matricola){
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select * from studente where matricola=\""+matricola+"\"";
			ResultSet res= st.executeQuery(sql);
			
			if(res.next()){
				//trovo qualcosa
				Studente v = new Studente(res.getString("matricola"),
						res.getString("cognome"),
						res.getString("nome"), res.getString("CDS"));
				res.close();
				conn.close();
				return v;
			}else{
				res.close();
				conn.close();
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
