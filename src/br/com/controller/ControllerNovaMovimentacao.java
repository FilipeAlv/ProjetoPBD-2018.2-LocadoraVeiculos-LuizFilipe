package br.com.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.model.beans.Financeiro;
import br.com.model.dao.DAOFinanceiro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerNovaMovimentacao implements Initializable{

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField fdValor;

    @FXML
    private TextArea fdDescricao;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    void actionSalvar(ActionEvent event) {
    	String tipo, descricao;
    	Double valor;
    	
    	tipo = cbTipo.getValue();
    	descricao = fdDescricao.getText().toString();
    	valor = Double.parseDouble(fdValor.getText().toString());
    	
    	Financeiro f = new Financeiro();
    	f.setDescricao(descricao);
    	f.setTipo(tipo);
    	f.setValor(valor);
    	f.setDia(new Date());
    	
    	DAOFinanceiro.getInstance().saveOrUpdate(f);
    	
    	ControllerMovimentacao.carregarTabela(DAOFinanceiro.getInstance().findAll());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("Entrada", "Saída");
		cbTipo.setItems(ob);
		
	}

}