package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.main.Main;
import br.com.model.beans.Filial;
import br.com.model.dao.DAOFilial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    	Main.novaTela("NovaFilial");
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

	private static void carregarTabela() {
		List<Filial> filiais = DAOFilial.getInstance().findAll();
		ob = FXCollections.observableArrayList();
		for (Filial filial : filiais) {
			ob.add(new FilialAdapter(filial.getNome(), filial.getEndereco().getUf()));
		}
		if (ob.size()>0) 
			tb.setItems(ob);
	}


    
    
    public static class FilialAdapter{
    	String nome, uf;
    	

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
