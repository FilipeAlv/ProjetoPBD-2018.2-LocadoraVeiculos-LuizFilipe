package br.com.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DaoGenerico;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static Scene sceneLogin, scenePrincipal;
	private static Stage stage;
	private static Pane telaPrincipal, telaLogin;
	@Override
	public void start(Stage primaryStage) {
		try {
			telaLogin = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			telaPrincipal = FXMLLoader.load(getClass().getResource("../view/Principal.fxml"));
			sceneLogin = new Scene(telaLogin);
			scenePrincipal = new Scene(telaPrincipal);
			
			primaryStage.setScene(sceneLogin);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			stage = primaryStage;
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Pessoa pessoa = new PessoaFisica();
		pessoa.setLogin("admin");
		pessoa.setSenha("admin");
		DaoGenerico<Pessoa> dao = new DAOPessoaFisica();
		dao.saveOrUpdate(pessoa);		
		launch(args);
	}
	
	public static void alterarTela(String tela) {	
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		switch(tela) {
		case "Principal":
			stage.setWidth(d.getWidth());
			stage.setHeight(d.getHeight());
			stage.setX(0);
			stage.setY(0);
			stage.setScene(scenePrincipal);
			break;
			
		case "Login":
			stage.setWidth(500);
			stage.setHeight(400);
			stage.setX((d.getWidth()-500)/2);
			stage.setY((d.getHeight()-400)/2);
			stage.setScene(sceneLogin);
			break;
		}
	}
}