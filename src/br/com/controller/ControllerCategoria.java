package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.main.Main;
import br.com.model.beans.Categoria;
import br.com.model.dao.DAOCategoria;
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
    	Main.novaTela("NovaCategoria");
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
