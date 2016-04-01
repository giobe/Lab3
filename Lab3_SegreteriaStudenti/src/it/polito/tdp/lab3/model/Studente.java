package it.polito.tdp.lab3.model;

public class Studente {
	String matricola;
	String nome;
	String cognome;
	String CDS;
	public Studente(String matricola, String cognome, String nome,String CDS) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.CDS=CDS;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@Override
	public String toString() {
		return  matricola + " " + nome + " " + cognome + " " + CDS + "\n";
	}
	

}
