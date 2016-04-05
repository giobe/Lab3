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
	
	public String getCDS() {
		return CDS;
	}
	public void setCDS(String cDS) {
		CDS = cDS;
	}
	@Override
	public String toString() {
		return  matricola + " " + nome + " " + cognome + " " + CDS + "\n";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}
	

}
