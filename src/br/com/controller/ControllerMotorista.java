package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.main.Main;
import br.com.model.beans.Motorista;
import br.com.model.dao.DAOMotorista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerMotorista implements Initializable{
	
	public static TableView<Motorista> tb;
	public static ObservableList<Motorista> ob;

    @FXML
    private TableView<Motorista> tbMotorista;

    @FXML
    private TableColumn<Motorista, Integer> codigoCol;

    @FXML
    private TableColumn<Motorista, String> nomeCol;

    @FXML
    private TableColumn<Motorista, String> habilitacaoCol;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnAtualizar;

    @FXML
    void actionAddMotorista(ActionEvent event) {
    	Main.novaTela("NovoMotorista");
    }


    @FXML
    void actionBuscar(ActionEvent event) {

    }

    @FXML
    void actionEditar(ActionEvent event) {

    }
    
    @FXML
	void actionAtualizar(ActionEvent event) {
		carregarTabela();
		tbMotorista.refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codigoCol.setCellValueFactory(
				new PropertyValueFactory<>("codigo"));
		nomeCol.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		habilitacaoCol.setCellValueFactory(
				new PropertyValueFactory<>("habilitacao"));

		tb=tbMotorista;
		carregarTabela();


	}

	private static void carregarTabela() {
		List<Motorista> motoristas = DAOMotorista.getInstace().findAll();
		ob = FXCollections.observableArrayList();
		ob.addAll(motoristas);
		if (ob.size()>0) 
			tb.setItems(ob);
	}


}
