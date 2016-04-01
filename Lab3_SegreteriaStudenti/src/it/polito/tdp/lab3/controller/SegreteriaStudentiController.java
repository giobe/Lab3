package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Segreteria;
import it.polito.tdp.lab3.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	
	private Segreteria model;
	
	//ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList(model.nomiCorsi());
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> bxCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnDati;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    public void setModel(Segreteria model){
    	this.model=model;
    	bxCorso.getItems().addAll(model.nomiCorsi());
    }
    
    @FXML
    void doCerca(ActionEvent event) {
    	String corso = bxCorso.getValue();
    	String matricola= txtMatricola.getText();
    	
    	if((corso.compareTo("")!=0) && matricola.isEmpty()==false){
    		boolean res = model.cercaIscrizione(matricola, corso);
    		if(res==true){
    			Studente s = model.caricaDatiStudente(matricola);
    			txtResult.setText("la matricola "+matricola+" "+s.getNome()+" "+s.getCognome()+" risulta iscritta al corso "+corso);
    		}
    		else if(res==false){
    			Studente s = model.caricaDatiStudente(matricola);
    			txtResult.setText("la matricola "+matricola+" "+s.getNome()+" "+s.getCognome()+"non risulta iscritta al corso "+corso);
    		}
    	}
    	else if(corso!=null && matricola.isEmpty()==true && corso.compareTo("")!=0){
    		LinkedList<Studente> studenti = (LinkedList<Studente>) model.StudentiPerCorso(corso);
    		if(studenti.isEmpty()==true)
    			txtResult.setText("Il corso non è frequentato da nessuno studente");
    		else if(studenti.isEmpty()==false){
    			String tot="";
    			for(Studente s : studenti){
    				tot=tot+s.toString();
    			}
    			txtResult.setText(tot);
    		}
    	}
    	else if(matricola!=null && corso.compareTo("")==0){
    		LinkedList<Corso> corsi = (LinkedList<Corso>) model.CorsiPerStudente(matricola);
    		if(corsi.isEmpty()==true){
    			txtResult.setText("lo studente non segue nessu corso");
    		}
    		else if(corsi.isEmpty()==false){
    			String tot="";
    			for(Corso s : corsi){
    				tot=tot+s.toString();
    			}
    			txtResult.setText(tot);
    		}
    	}
    	else if(corso.compareTo("")==0 && matricola.isEmpty()==true){
    		txtResult.setText("inserire dati");
    	}

    }

    @FXML
    void doDatiAssociati(ActionEvent event) {
    	String matricola= txtMatricola.getText();
    	if(matricola!=null){
    	Studente s = model.caricaDatiStudente(matricola);
    	if(s!=null){
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	}
    	else{
    		txtNome.clear();
    		txtCognome.clear();
    	}
    	}
    	else{
    		txtNome.clear();
    		txtCognome.clear();
    		//txtResult.setText(");
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	String corso = bxCorso.getValue();
    	String matricola= txtMatricola.getText();
    	if(corso==null || matricola==null){
    		txtResult.setText("per poter inserire bisogna selezionare sia un corso che una matricola");
    	}
    	else{
    		boolean ret = model.aggiungiIscrizione(matricola, corso);
    		if(ret==true){
    			Studente s = model.caricaDatiStudente(matricola);
    			txtResult.setText("la matricola "+matricola+" "+s.getNome()+" "+s.getCognome()+" è stata iscritta al corso "+corso);
    		}
    		else if(ret==false){
    			txtResult.setText("impossibile effettuare l'inserimento richiesto");
    		}
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	/*int a = bxCorso.getItems().size();
    	String q = bxCorso.getItems().get(a);
    	bxCorso.setValue(q);*/

    }

    @FXML
    void doSelezionaCorso(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert bxCorso != null : "fx:id=\"bxCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnDati != null : "fx:id=\"btnDati\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
       // bxCorso.setItems(list);
       // for(String s : model.nomiCorsi()){
        	//bxCorso.getItems().addAll(model.nomiCorsi());
        

    }
}

