package br.com.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.ClienteAdapter;
import br.com.model.dao.DAOPessoaBackup;
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

public class ControllerLogCliente implements Initializable{
	public static TableView<ClienteAdapter> tb;
	public static ObservableList<ClienteAdapter> ob;
	
	
	
    @FXML
    private TableView<ClienteAdapter> tbLog;

    @FXML
    private TableColumn<ClienteAdapter, Integer> codigoCol;

    @FXML
    private TableColumn<ClienteAdapter, String> nomeCol;

    @FXML
    private TableColumn<ClienteAdapter, String> loginCol;

    @FXML
    private TableColumn<ClienteAdapter, String> cpfCol;

    @FXML
    private TableColumn<ClienteAdapter, String> rgCol;

    @FXML
    private TableColumn<ClienteAdapter, Date> nascCol;

    @FXML
    private TableColumn<ClienteAdapter, String> sexoCol;

    @FXML
    private TableColumn<ClienteAdapter, String> cnpjCol;

    @FXML
    private TableColumn<ClienteAdapter, String> inscCol;

    @FXML
    private TableColumn<ClienteAdapter, Date> dataCol;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnAtualizar;


    @FXML
    void actionBuscar(ActionEvent event) {

    }
    
    @FXML
	void actionAtualizar(ActionEvent event) {
		carregarTabela();
		tbLog.refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codigoCol.setCellValueFactory(
				new PropertyValueFactory<>("codigo"));
		nomeCol.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		loginCol.setCellValueFactory(
				new PropertyValueFactory<>("login"));
		cpfCol.setCellValueFactory(
				new PropertyValueFactory<>("cpf"));
		rgCol.setCellValueFactory(
				new PropertyValueFactory<>("rg"));
		nascCol.setCellValueFactory(
				new PropertyValueFactory<>("nasc"));
		sexoCol.setCellValueFactory(
				new PropertyValueFactory<>("sexo"));
		cnpjCol.setCellValueFactory(
				new PropertyValueFactory<>("cnpj"));
		inscCol.setCellValueFactory(
				new PropertyValueFactory<>("insc"));
		dataCol.setCellValueFactory(
				new PropertyValueFactory<>("data"));

		tb=tbLog;
		carregarTabela();


	}

	private static void carregarTabela() {
		List<ClienteAdapter> clientes = DAOPessoaBackup.getInstace().findAll();
		
		System.out.println(clientes.size());
		ob = FXCollections.observableArrayList();
		
		for (ClienteAdapter p : clientes) {
			ob.add(new ClienteAdapter(p.getCodigo(), p.getNome(), p.getLogin(), p.getCpf(), p.getRg(), p.getSexo(),
					p.getNasc(), p.getData()));
		}
	
		if (ob.size()>0) 
			tb.setItems(ob);
	}


    
    
   

}
