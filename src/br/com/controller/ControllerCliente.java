package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.PessoaJuridica;
import br.com.model.dao.DAOMotorista;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
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

public class ControllerCliente implements Initializable{

	
	public static TableView<ClienteAdapter> tb;
	public static ObservableList<ClienteAdapter> ob;
	
    @FXML
    private TableView<ClienteAdapter> tbCliente;

    @FXML
    private TableColumn<ClienteAdapter, Integer> codigoCol;

    @FXML
    private TableColumn<ClienteAdapter, String> nomeCol;

    @FXML
    private TableColumn<ClienteAdapter, String> tipoCol;

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
    void actionAddPessoa(ActionEvent event) {

    }

    @FXML
    void actionAtualizar(ActionEvent event) {
    	carregarTabela();
		tbCliente.refresh();
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
				new PropertyValueFactory<>("codigo"));
		nomeCol.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		tipoCol.setCellValueFactory(
				new PropertyValueFactory<>("tipo"));

		tb=tbCliente;
		carregarTabela();


	}

	private static void carregarTabela() {
		List<PessoaFisica> clientesFisicos = DAOPessoaFisica.getInstace().findAll();
		List<PessoaJuridica> clientesJuridicos = DAOPessoaJuridica.getInstace().findAll();
		List<Motorista> motoristas = DAOMotorista.getInstace().findAll();
		ob = FXCollections.observableArrayList();
		
		for (Pessoa pessoa : clientesFisicos) {
			ob.add(new ClienteAdapter(pessoa.getId(), pessoa.getNome(), "Pessoa Fisica"));
		}
		for (Pessoa pessoa : clientesJuridicos) {
			ob.add(new ClienteAdapter(pessoa.getId(), pessoa.getNome(), "Pessoa Juridica"));
		}
		for (Pessoa pessoa : motoristas) {
			ob.add(new ClienteAdapter(pessoa.getId(), pessoa.getNome(), "Motorista"));
		}
		if (ob.size()>0) 
			tb.setItems(ob);
	}


    
    public static class ClienteAdapter {
    	Integer codigo;
    	String nome;
    	String tipo;
    	
    	
		public ClienteAdapter(Integer codigo, String nome, String tipo) {
			super();
			this.codigo = codigo;
			this.nome = nome;
			this.tipo = tipo;
		}
		
		public Integer getCodigo() {
			return codigo;
		}
		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
    	
    	
    }

}
