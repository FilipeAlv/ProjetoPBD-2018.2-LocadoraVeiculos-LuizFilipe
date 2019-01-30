package br.com.controller;

import br.com.model.beans.Pessoa;
import br.com.model.dao.DAOPessoa;
import br.com.util.Session;
import br.com.util.Util;
import br.com.util.Util.Criptografia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ControllerAlterarSenha {
	Alert alert = new Alert(AlertType.ERROR);

	@FXML
	private PasswordField fdNovaSenha;

	@FXML
	private PasswordField fdSenhaAtual;

	@FXML
	private PasswordField fdConfSenha;

	@FXML
	private Button btnSalvar;

	@FXML
	void actionSalvar(ActionEvent event) {
		alert.setTitle("ALTERAR SENHA");
		if(validarCampos()) {
			try {
				String novaSenha = fdNovaSenha.getText();
				String senhaAtual = fdSenhaAtual.getText();
				if (senhaAtual.length()==0)
					senhaAtual=Util.SENHA_PADRAO;
				Pessoa user = DAOPessoa.getInstace().findByLogin(Session.usuario.getLogin());
				if(Criptografia.decriptografa(user.getSenha().toCharArray()).equals(senhaAtual)) {
					user.setSenha(new String(Criptografia.criptografa(novaSenha.toCharArray())));
					DAOPessoa.getInstace().saveOrUpdate(user);
					alert.setContentText("Senha Alterada com sucesso");
					alert.show();
				}else {
					alert.setContentText("Senha Atual está incorreta");
					alert.show();
				}
			}catch (Exception e) {
				alert.setContentText("ERRO INESPERADO: "+e.getMessage());
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

		}else if(!(fdNovaSenha.getText().length()>=6 || fdNovaSenha.getText().length()<=11)) {
			alert.setContentText("Sua senha deve conter entre 6 e 11 caracteres");
			alert.show();
			return false;
		}else {
			return true;
		}
	}

}
