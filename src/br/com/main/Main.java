package br.com.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.sun.javafx.stage.StagePeerListener;

import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DaoGenerico;
import br.com.util.Util.Criptografia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	public static Scene sceneLogin, scenePrincipal, sceneNovaReserva;
	private static Stage stage;
	public static Stage stageNovaReserva;
	private static Pane telaPrincipal, telaLogin, telaNovaReserva;
	@Override
	public void start(Stage primaryStage) {
		try {
			telaLogin = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			telaPrincipal = FXMLLoader.load(getClass().getResource("../view/Principal.fxml"));
			telaNovaReserva = FXMLLoader.load(getClass().getResource("../view/NovaReserva.fxml"));
			sceneLogin = new Scene(telaLogin);
			scenePrincipal = new Scene(telaPrincipal);
			sceneNovaReserva = new Scene(telaNovaReserva);
			
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
		pessoa.setSenha(new String(Criptografia.criptografa("admin".toCharArray())));
		DaoGenerico<Pessoa> dao = new DAOPessoaFisica();
		//dao.saveOrUpdate(pessoa);		
		launch(args);
	}
	
	public static void novaTela(String tela) {	
		stageNovaReserva = new Stage();
		stageNovaReserva.initStyle(StageStyle.UNDECORATED);
		switch(tela) {
		case "NovaReserva":
			stageNovaReserva.setScene(sceneNovaReserva);
			break;
		}
		stageNovaReserva.show();
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