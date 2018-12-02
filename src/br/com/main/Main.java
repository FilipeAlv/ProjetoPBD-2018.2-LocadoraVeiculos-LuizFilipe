package br.com.main;

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
	private static Scene scene;
	private static Stage stage;
	private static Pane telaPrincipal;
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			Pane telaLogin = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			telaPrincipal = FXMLLoader.load(getClass().getResource("../view/Principal.fxml"));
			
			
			
			scene = new Scene(telaLogin);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
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
		stage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		stage.setX(0);
		stage.setY(0);
		
		scene.setRoot(telaPrincipal);
		stage.setScene(scene);
		stage.show();
	}
}