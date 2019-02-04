package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
import br.com.model.relatorio.Relatorio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControllerRelatorioCliente implements Initializable {

    @FXML
    private Button btnFiltrar;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    void actionFiltrar(ActionEvent event) {
    	Relatorio r = new Relatorio();
    	if(cbTipo.getValue().equals("Pessoa Física")) 
    		r.gerarRelatorio(DAOPessoaFisica.getInstace().findAll(), "/br/com/report/relatorioPessoaFisica.jrxml");
    	else 
    		r.gerarRelatorio(DAOPessoaJuridica.getInstace().findAll(), "/br/com/report/relatorioPessoaJuridica.jrxml");
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("Pessoa Física", "Pessoa Juridica");
		cbTipo.setItems(ob);
		
	}

}
