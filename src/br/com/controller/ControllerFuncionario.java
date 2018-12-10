package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.controller.ControllerConfigLocacao.VLAux;
import br.com.main.Main;
import br.com.model.beans.Funcionario;
import br.com.model.beans.ValorLocacao;
import br.com.model.dao.DAOFuncionario;
import br.com.model.dao.DAOValorLocacao;
import br.com.util.Session;
import br.com.util.Util;
import br.com.util.Util.Criptografia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerFuncionario implements Initializable{

    @FXML
    private ImageView btnClose;

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

    @FXML
    void actionAddFuncionario(ActionEvent event) {

    }

    @FXML
    void actionBuscarCliente(ActionEvent event) {

    }

    @FXML
    void actionClose(MouseEvent event) {
    	Main.stageFuncionario.close();
    }

    @FXML
    void actionEditar(ActionEvent event) {
    }

    @FXML
    void actionResetSenha(ActionEvent event) {
    	Funcionario f = tbFuncionario.getSelectionModel().getSelectedItem();
    	if(f!=null) {
	    	f.setSenha(new String(Criptografia.criptografa(Util.SENHA_PADRAO.toCharArray())));
	    	DAOFuncionario.getInstace().saveOrUpdate(f);
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
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
        
		List<Funcionario> funcionarios = DAOFuncionario.getInstace().findAll();
		ObservableList<Funcionario> ob = FXCollections.observableArrayList();
		
		for (Funcionario funcionario : funcionarios) {
			ob.add(funcionario);
		}
		if (ob.size()>0) 
			tbFuncionario.setItems(ob);
		
		if(Session.usuario!=null)
			desativarBotoes();
		
	}

    public static void desativarBotoes() {
		if(!((Funcionario)Session.usuario).getPermissao().equals("full")) {
			btnResetSenha.setDisable(true);
		}else {
			btnResetSenha.setDisable(false);
		}
		
	}
    
   
}
