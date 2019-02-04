package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Motorista;
import br.com.model.dao.DAOMotorista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	private Button btnDeletar;

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
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovoMotorista.fxml"));
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
	void actionDeletar(ActionEvent event) {
		Motorista motorista = DAOMotorista.getInstace().findById(Motorista.class, tbMotorista.getSelectionModel().getSelectedItem().getId());
		motorista.setStatusOb(false);
		DAOMotorista.getInstace().saveOrUpdate(motorista);
		carregarTabela();
	}

	@FXML
	void actionBuscar(ActionEvent event) {

	}

	@FXML
	void actionEditar(ActionEvent event) {
		Motorista motorista = DAOMotorista.getInstace().findById(Motorista.class, tbMotorista.getSelectionModel().getSelectedItem().getId());
		Pane tela = null;
		Scene scene;
		Stage stage;
		ControllerNovoMotorista c;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NovoMotorista.fxml"));
			tela = loader.load();
			c = loader.getController();
			c.carregarEditar(motorista);
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

	public static void carregarTabela() {
		List<Motorista> motoristas = DAOMotorista.getInstace().findAll();
		ob = FXCollections.observableArrayList();
		ob.addAll(motoristas);
		if (ob.size()>0) 
			tb.setItems(ob);
	}


}
