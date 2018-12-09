package br.com.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.util.Util.Criptografia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	public static Scene sceneLogin, scenePrincipal, sceneNovaReserva, sceneConfigLocacao, sceneAddValorLocacao, sceneAlterarSenha;
	private static Stage stage;
	public static Stage stageNovaReserva, stageConfigLocacao, stageAddValorLocacao, stageAlterarSenha;
	private static Pane telaPrincipal, telaLogin, telaNovaReserva, telaConfigLocacao, telaAddValorLocacao, telaAlterarSenha;
	@Override
	public void start(Stage primaryStage) {
		try {
			telaLogin = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			telaPrincipal = FXMLLoader.load(getClass().getResource("../view/Principal.fxml"));
			telaNovaReserva = FXMLLoader.load(getClass().getResource("../view/NovaReserva.fxml"));
			telaConfigLocacao = FXMLLoader.load(getClass().getResource("../view/ConfigLocacao.fxml"));
			telaAddValorLocacao =  FXMLLoader.load(getClass().getResource("../view/AddValorLocacao.fxml"));
			telaAlterarSenha = FXMLLoader.load(getClass().getResource("../view/AlterarSenha.fxml"));
			sceneLogin = new Scene(telaLogin);
			scenePrincipal = new Scene(telaPrincipal);
			sceneNovaReserva = new Scene(telaNovaReserva);
			sceneConfigLocacao = new Scene(telaConfigLocacao);
			sceneAddValorLocacao = new Scene(telaAddValorLocacao);
			sceneAlterarSenha = new Scene(telaAlterarSenha);
			
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
		//DaoGenerico<Pessoa> dao = new DAOPessoaFisica();
		//dao.saveOrUpdate(pessoa);		
		launch(args);
	}
	
	public static void novaTela(String tela) {	
		switch(tela) {
		case "NovaReserva":
			stageNovaReserva = new Stage();
			stageNovaReserva.initModality(Modality.APPLICATION_MODAL);
			stageNovaReserva.initStyle(StageStyle.UNDECORATED);
			stageNovaReserva.setScene(sceneNovaReserva);
			stageNovaReserva.show();
			break;
		case "ConfigLocacao":
			stageConfigLocacao = new Stage();
			stageConfigLocacao.initModality(Modality.APPLICATION_MODAL);
			stageConfigLocacao.initStyle(StageStyle.UNDECORATED);
			stageConfigLocacao.setScene(sceneConfigLocacao);
			stageConfigLocacao.show();
			break;
		case "AddValorLocacao":
			stageAddValorLocacao = new Stage();
			stageAddValorLocacao.initModality(Modality.APPLICATION_MODAL);
			stageAddValorLocacao.initStyle(StageStyle.UNDECORATED);
			stageAddValorLocacao.setScene(sceneAddValorLocacao);
			stageAddValorLocacao.show();
			break;
		case "AlterarSenha":
			stageAlterarSenha = new Stage();
			stageAlterarSenha.initModality(Modality.APPLICATION_MODAL);
			stageAlterarSenha.initStyle(StageStyle.UNDECORATED);
			stageAlterarSenha.setScene(sceneAlterarSenha);
			stageAlterarSenha.show();
			break;
		}
		
		
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