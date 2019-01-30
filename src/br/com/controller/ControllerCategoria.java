package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Categoria;
import br.com.model.dao.DAOCategoria;
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

public class ControllerCategoria implements Initializable{
	
	public static TableView<Categoria> tb;
	public static ObservableList<Categoria> ob;

    @FXML
    private TableView<Categoria> tbCategoria;

    @FXML
    private TableColumn<Categoria, Integer> codigoCol;

    @FXML
    private TableColumn<Categoria, String> nomeCol;

    @FXML
    private TableColumn<Categoria, String> tamanhoCol;

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
    void actionAddCategoria(ActionEvent event) {
    	Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovaCategoria.fxml"));
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
		tbCategoria.refresh();
    }

    @FXML
    void actionBuscar(ActionEvent event) {

    }

    @FXML
    void actionEditar(ActionEvent event) {
    	Categoria categoria = DAOCategoria.getInstance().findById(Categoria.class, tb.getSelectionModel().getSelectedItem().getId());
    	Pane tela = null;
		Scene scene;
		Stage stage;
		ControllerNovaCategoria c;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NovaCategoria.fxml"));
			tela = loader.load();
			c = loader.getController();
			c.carregarEditar(categoria);
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
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codigoCol.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		nomeCol.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		tamanhoCol.setCellValueFactory(
				new PropertyValueFactory<>("tamanho"));
		tb=tbCategoria;
		carregarTabela();


	}

	private static void carregarTabela() {
		List<Categoria> categorias = DAOCategoria.getInstance().findAll();
		ob = FXCollections.observableArrayList();
		ob.addAll(categorias);
		if (ob.size()>0) 
			tb.setItems(ob);
	}


}
