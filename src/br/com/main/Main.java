package br.com.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import br.com.controller.ControllerPrincipal;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.ThreadBackup;
import br.com.model.dao.DAOPessoaFisica;
import br.com.util.Util.Criptografia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	
	private Pane telaLogin, telaPrincipal;
	private static Scene sceneLogin;
	private static Scene scenePrincipal;
	private static Stage stage, stagePrincipal;
	public static ControllerPrincipal c;

	@Override
	public void start(Stage primaryStage) {
		try {
			telaLogin = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Principal.fxml"));
			telaPrincipal = loader.load();
			c=loader.getController();
			
			stagePrincipal = new Stage();

			sceneLogin = new Scene(telaLogin);
			scenePrincipal = new Scene(telaPrincipal);

			Thread th = new Thread(new ThreadBackup());
			th.setDaemon(true);
			th.start();
			
			primaryStage.setScene(sceneLogin);
			stage = primaryStage;
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Pessoa pessoa = new PessoaFisica();
		pessoa.setLogin("admin");
		pessoa.setSenha(new String (Criptografia.criptografa("admin".toCharArray())));
		DAOPessoaFisica.getInstace().saveOrUpdate(pessoa);
		launch(args);
	}

	public static void novaTela(String tela) {	

		}

	public static void alterarTela(String tela) {	
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		switch(tela) {
		case "Principal":
			stagePrincipal.setWidth(d.getWidth());
			stagePrincipal.setHeight(d.getHeight());
			stagePrincipal.setX(0);
			stagePrincipal.setY(0);
			stagePrincipal.setScene(scenePrincipal);
			stage.close();
			stagePrincipal.show();
			break;

		case "Login":
			stage.setScene(sceneLogin);
			stagePrincipal.close();
			stage.show();
			break;
		}
	}


}