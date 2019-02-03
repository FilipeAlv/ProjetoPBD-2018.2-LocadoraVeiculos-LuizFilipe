package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.beans.Config;
import br.com.model.beans.Funcionario;
import br.com.model.dao.DAOFuncionario;
import br.com.util.Util;
import br.com.util.Util.Criptografia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerFuncionario implements Initializable{

    @FXML
    private TableView<Funcionario> tbFuncionario;

    @FXML
    private TableColumn<Funcionario, String> codigoCol;

    @FXML
    private TableColumn<Funcionario, String> nomeCol;

    @FXML
    private TableColumn<Funcionario, String> cargoCol;

    @FXML
    private TableColumn<Funcionario, String> PermissaoCol;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private static Button btnResetSenha;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnEditar;

	private static TableView<Funcionario> tb;

    @FXML
    void actionAddFuncionario(ActionEvent event) {
    	Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovoFuncionario.fxml"));
			scene = new Scene(tela);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(e -> stage.close());
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro de Exibição");
			alert.setContentText("Não foi possível exibir a tela. Por favor entre em contato com a equipe de desenvolvimento.");
			alert.setHeaderText("Tela não encontrada");
			e.printStackTrace();
		}
    }

    @FXML
    void actionBuscarCliente(ActionEvent event) {

    }


    @FXML
    void actionEditar(ActionEvent event) {
    	Funcionario funcionario = DAOFuncionario.getInstace().findById(Funcionario.class, tbFuncionario.getSelectionModel().getSelectedItem().getId());
    	Pane tela = null;
		Scene scene;
		Stage stage;
		ControllerNovoFuncionario c;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NovoFuncionario.fxml"));
			tela = loader.load();
			c = loader.getController();
			c.carregarEditar(funcionario);
			scene = new Scene(tela);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(e -> stage.close());
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro de Exibição");
			alert.setContentText("Não foi possível exibir a tela. Por favor entre em contato com a equipe de desenvolvimento.");
			alert.setHeaderText("Tela não encontrada");
			e.printStackTrace();
		}
    }

    @FXML
    void actionResetSenha(ActionEvent event) {
		Alert alert = new Alert(AlertType.ERROR);
    	Funcionario f = tbFuncionario.getSelectionModel().getSelectedItem();
    	if(f!=null) {
	    	f.setSenha(new String(Criptografia.criptografa(Util.SENHA_PADRAO.toCharArray())));
	    	DAOFuncionario.getInstace().saveOrUpdate(f);
    		alert.setContentText("A senha deste Funcionario foi resetada com sucesso");
    		alert.show();
    	}else {
    		alert.setContentText("Selecione um item");
    		alert.show();
    	}
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cargoCol.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        PermissaoCol.setCellValueFactory(new PropertyValueFactory<>("permissao"));
        tb=tbFuncionario;
		carregarTabela();
		
	}
    
    public static void carregarTabela() {
        
		List<Funcionario> funcionarios = DAOFuncionario.getInstace().findAll();
		ObservableList<Funcionario> ob = FXCollections.observableArrayList();
	
		for (Funcionario funcionario : funcionarios) {
			ob.add(funcionario);
		}
		if (ob.size()>0) 
			tb.setItems(ob);
		
		if(Config.getInstace().getUsuario()!=null)
			desativarBotoes();
    }

    public static void desativarBotoes() {
//		if(!((Funcionario)Session.usuario).getPermissao().equals("full")) {
//			btnResetSenha.setDisable(true);
//		}else {
//			btnResetSenha.setDisable(false);
//		}
		
	}
    
   
}
