package br.com.controller;

import br.com.main.Main;
import br.com.model.dao.DAOPessoa;
import br.com.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class ControllerAlterarSenha {
	Alert alert = new Alert(AlertType.ERROR);
    @FXML
    private ImageView btnClose;

    @FXML
    private PasswordField fdNovaSenha;

    @FXML
    private PasswordField fdSenhaAtual;

    @FXML
    private PasswordField fdConfSenha;

    @FXML
    private Button btnSalvar;

    @FXML
    void actionClose(KeyEvent event) {
    	Main.stageAlterarSenha.close();
    }

    @FXML
    void actionSalvar(ActionEvent event) {
    	alert.setTitle("ALTERAR SENHA");
    	if(validarCampos()) {
    		try {
    			String novaSenha = fdNovaSenha.getText();
    			String senhaAtual = fdSenhaAtual.getText();
 
    			DAOPessoa.getInstace().updateSenha(Session.usuario.getLogin(),senhaAtual,novaSenha);
    		}catch (Exception e) {
    			alert.setContentText("Senha Atual está incorreta");
        		alert.show();
			}
    	}
    }
    
    private boolean validarCampos() {
    	
    	if(fdNovaSenha.getText().length()==0 || fdConfSenha.getText().length()==0) {
    		alert.setContentText("Os campos não podem estar vazios");
    		alert.show();
    		return false;
    	}else if(!fdNovaSenha.getText().toString().equals(fdConfSenha.getText().toString())) {
    		alert.setContentText("As senhas nao coincidem");
    		alert.show();
    		return false;
    	}else {
    		return true;
    	}
    }

}
