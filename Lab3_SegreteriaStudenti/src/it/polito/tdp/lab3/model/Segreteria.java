package it.polito.tdp.lab3.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import it.polito.tdp.lab3.db.SegreteriaDAO;

public class Segreteria {
	LinkedHashSet<Corso> corsi = new LinkedHashSet<Corso>();
	LinkedHashSet<Studente> studenti = new LinkedHashSet<Studente>();
	
	public void caricaCorsi(){
		SegreteriaDAO dao = new SegreteriaDAO();
		corsi.addAll(dao.caricaCorsi());
	}
	public Studente caricaDatiStudente(String matricola){
		int m = Integer.parseInt(matricola);
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.caricaDatiStudente(m);
	}
	public Collection<String> nomiCorsi(){
		ArrayList<String> nomi =new  ArrayList<String>();
		this.caricaCorsi();
		for(Corso c : corsi){
			nomi.add(c.getNome());
		}
		nomi.add("");
		return nomi;
	}
	
	public Collection<Corso> CorsiPerStudente(String matricola){
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.CorsiPerStudente(matricola);
	}
	public Collection<Studente> StudentiPerCorso(String nomeCorso){
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.StudentiPerCorso(nomeCorso);
	}
	public boolean cercaIscrizione(String matricola, String nomeCorso){
		SegreteriaDAO dao  = new SegreteriaDAO();
		return dao.StudenteNelCorso(matricola, nomeCorso);
	}
	
	public boolean aggiungiIscrizione(String matricola, String nomeCorso){
		int m = Integer.parseInt(matricola);
		this.caricaCorsi();
		String codCorso="";
		for(Corso c : corsi){
			if(c.getNome().compareTo(nomeCorso)==0)
				codCorso= c.getCodice();
		}
		if(codCorso!="" && this.caricaDatiStudente(matricola)!=null && this.cercaIscrizione(matricola, nomeCorso)==false){
			SegreteriaDAO dao = new SegreteriaDAO();
			return dao.aggiungiIscrizione(m, codCorso);
		}
		else
		return false;
	}
	
	public static void main(String[] args){
		Segreteria s = new Segreteria();
		s.caricaCorsi();
		Collection<Corso> corsi = s.corsi;
		System.out.println(corsi.toString());
		
		Collection<Corso> corsi1 = s.CorsiPerStudente("146101");
		System.out.println(corsi1.toString());
		
		boolean ret = s.aggiungiIscrizione("148072", "Economia e finanza d'impresa");
		if(ret==true)
			System.out.println("inserito");
		else
			System.out.println("nada");
	}
	

}
