package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.model.beans.Endereco;
import br.com.model.beans.Filial;
import br.com.model.dao.DAOFilial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerNovaFilial implements Initializable{

    @FXML
    private TextField fdNome;

    @FXML
    private TextField fdRua;

    @FXML
    private ComboBox<String> cbUf;

    @FXML
    private TextField fdNumero;

    @FXML
    private TextField fdBairro;

    @FXML
    private TextField fdCidade;

    @FXML
    private Button btnSalvar;

	private boolean edit;
	private Filial f = new Filial();

    @FXML
    void actionSalvar(ActionEvent event) {
    	if(validarFilial()) {
    		String nome, bairro, cidade, numero, rua, uf;
    		Endereco endereco;
    		
    		nome = fdNome.getText();
    		bairro = fdBairro.getText().toString();
    		cidade = fdCidade.getText().toString();
    		numero = fdNumero.getText().toString();
    		rua = fdRua.getText().toString();
    		uf = cbUf.getValue();
    		
    		endereco = new Endereco(rua, numero, bairro , cidade, uf);
    		f.setNome(nome);
    		f.setEndereco(endereco);
    		
    		DAOFilial.getInstance().saveOrUpdate(f);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Esta Filial foi salva com successo!");
    		alert.show();
    		
    		if(edit)
    			ControllerFilial.carregarTabela();  		
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PB",
				"PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO");
		cbUf.setItems(ob);
		
	}
	
	private boolean validarCampos() {
		if(	fdNome.getText().length()==0 || fdBairro.getText().length()==0|| fdCidade.getText().length()==0 ||
				fdNumero.getText().length()==0 || fdRua.getText().length()==0) {
				return false;
		}
		return true;
	}
	
	private boolean validarFilial() {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Cadastro de Filial" );
    		alert.setHeaderText("Erro ao Salvar Filial");
    		if(!validarCampos()) {
    			alert.setContentText("Preencha todos os campos obrigatorios");
        		alert.show();
        		return false;
    		}else if(DAOFilial.getInstance().findByNome(fdNome.getText().toString())!=null && !edit) {
    			alert.setContentText("Nome Já Cadastrado");
        		alert.show();
        		return false;
    		}else {
    			return true;
    		}
	}

	public void carregarEditar(Filial filial) {
		this.f  = filial;
		edit = true;
		
		fdNome.setText(filial.getNome());
		fdRua.setText(filial.getEndereco().getRua());
		fdBairro.setText(filial.getEndereco().getBairro());
		fdCidade.setText(filial.getEndereco().getCidade());
		fdNumero.setText(filial.getEndereco().getNumero());
		cbUf.getSelectionModel().select(filial.getEndereco().getUf());
		
	}
}
