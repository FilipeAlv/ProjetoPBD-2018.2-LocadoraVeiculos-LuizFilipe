package br.com.controller;

import br.com.model.beans.Marca;
import br.com.model.dao.DAOMarca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerNovaMarca {

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField fdNome;

    @FXML
    void actionSalvar(ActionEvent event) {
    	if(validarCampos()) {
    		Marca marca = new Marca(fdNome.getText().toString());
    		DAOMarca.getInstance().saveOrUpdate(marca);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Esta Marca foi salvo com successo!");
    		alert.show();
    		new ControllerNovoModelo().carregarCombo();
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro ao Salvar Marca" );
    		alert.setContentText("Preencha todos os campos obrigatorios");
    		alert.show();
    	}
    }
    
    private boolean validarCampos() {
    	if(fdNome.getText().length()==0)
    		return false;
    	return true;
    }

}
