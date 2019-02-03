package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.PessoaJuridica;
import br.com.model.dao.DAOMotorista;
import br.com.model.dao.DAOPessoa;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
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
	private Button btnDeletar;

	@FXML
	void actionDeletar(ActionEvent event) {
		Pessoa cliente = DAOPessoa.getInstace().findById(Pessoa.class, tbCliente.getSelectionModel().getSelectedItem().getCodigo());
		cliente.setStatusOb(false);
		DAOPessoa.getInstace().saveOrUpdate(cliente);

		carregarTabela();
	}


	@FXML
	void actionAddPessoa(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovoCliente.fxml"));
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
		tbCliente.refresh();
	}

	@FXML
	void actionBuscar(ActionEvent event) {

	}

	@FXML
	void actionEditar(ActionEvent event) {
		Pessoa cliente = DAOPessoa.getInstace().findById(Pessoa.class, tbCliente.getSelectionModel().getSelectedItem().codigo);
		Pane tela = null;
		Scene scene;
		Stage stage;
		ControllerNovoCliente c;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NovoCliente.fxml"));
			tela = loader.load();
			c = loader.getController();
			c.carregarEditar(cliente);
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
				new PropertyValueFactory<>("codigo"));
		nomeCol.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		tipoCol.setCellValueFactory(
				new PropertyValueFactory<>("tipo"));

		tb=tbCliente;
		carregarTabela();


	}

	public static void carregarTabela() {
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
