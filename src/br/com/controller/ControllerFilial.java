package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Filial;
import br.com.model.dao.DAOFilial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerFilial implements Initializable{
	
	public static TableView<FilialAdapter> tb;
	public static ObservableList<FilialAdapter> ob;
	
    @FXML
    private Label filial;

    @FXML
    private TableView<FilialAdapter> tbFilial;

    @FXML
    private TableColumn<FilialAdapter, String> nomeCol;

    @FXML
    private TableColumn<FilialAdapter, String> ufCol;

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
    void actionAddFilial(ActionEvent event) {
    	Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovaFilial.fxml"));
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
		tbFilial.refresh();
    }

    @FXML
    void actionBuscar(ActionEvent event) {

    }

    @FXML
    void actionEditar(ActionEvent event) {
    	Filial filial = DAOFilial.getInstance().findByNome(tb.getSelectionModel().getSelectedItem().getNome());
    	Pane tela = null;
		Scene scene;
		Stage stage;
		ControllerNovaFilial c;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NovaFilial.fxml"));
			tela = loader.load();
			c = loader.getController();
			c.carregarEditar(filial);
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
		nomeCol.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		ufCol.setCellValueFactory(
				new PropertyValueFactory<>("uf"));

		tb=tbFilial;
		carregarTabela();


	}

	public static void carregarTabela() {
		List<Filial> filiais = DAOFilial.getInstance().findAll();
		ob = FXCollections.observableArrayList();
		for (Filial filial : filiais) {
			ob.add(new FilialAdapter(filial.getNome(), filial.getEndereco().getUf()));
		}
		if (ob.size()>0) 
			tb.setItems(ob);
	}


    
    
    public static class FilialAdapter{
    	private String nome, uf;
    	

		public FilialAdapter(String nome, String uf) {
			super();
			this.nome = nome;
			this.uf = uf;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getUf() {
			return uf;
		}

		public void setUf(String uf) {
			this.uf = uf;
		}
    	
    }

}
