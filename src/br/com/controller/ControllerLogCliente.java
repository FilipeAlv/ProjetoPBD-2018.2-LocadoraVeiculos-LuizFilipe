package br.com.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.beans.Motorista2;
import br.com.model.beans.PessoaFisica2;
import br.com.model.beans.PessoaJuridica2;
import br.com.model.dao.DAOMotoristaBackup;
import br.com.model.dao.DAOPessoaFisicaBackup;
import br.com.model.dao.DAOPessoaJuridicaBackup;
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
		List<PessoaFisica2> clienteFisico = DAOPessoaFisicaBackup.getInstace().findAll();
		List<PessoaJuridica2> clienteJuridico = DAOPessoaJuridicaBackup.getInstace().findAll();
		List<Motorista2> motoristas = DAOMotoristaBackup.getInstace().findAll();
		
		System.out.println(clienteFisico.size());
		ob = FXCollections.observableArrayList();
		
		for (PessoaFisica2 p : clienteFisico) {
			ob.add(new ClienteAdapter(p.getCodigo(), p.getNome(), p.getLogin(), p.getCpf(), p.getRg(), p.getSexo(),
					p.getDataNascimento(), p.getDataModificacao()));
		}
		
		for (Motorista2 p : motoristas) {
			ob.add(new ClienteAdapter(p.getCodigo(), p.getNome(), p.getLogin(), p.getCpf(), p.getRg(), p.getSexo(),
					p.getDataNascimento(), p.getDataModificacao()));
		}
		
		for (PessoaJuridica2 p : clienteJuridico) {
			ob.add(new ClienteAdapter(p.getCodigo(), p.getNome(), p.getLogin(), p.getCnpj(), p.getInscricaoEstadual(),
					p.getDataModificacao()));
		}
	
		if (ob.size()>0) 
			tb.setItems(ob);
	}


    
    
    public static class ClienteAdapter{
    	private String nome, login ,cpf, rg, sexo, cnpj, insc, codigo;
    	private Date nasc, data;
    	
    	
		public ClienteAdapter(String codigo, String nome, String login, String cpf, String rg, String sexo, Date nasc,
				Date data) {
			super();
			this.codigo = codigo;
			this.nome = nome;
			this.login = login;
			this.cpf = cpf;
			this.rg = rg;
			this.sexo = sexo;
			this.nasc = nasc;
			this.data = data;
		}
		
		
		public ClienteAdapter(String codigo, String nome, String login, String cnpj, String insc, Date data) {
			super();
			this.codigo = codigo;
			this.nome = nome;
			this.login = login;
			this.cnpj = cnpj;
			this.insc = insc;
			this.data = data;
		}


		
		public String getCodigo() {
			return codigo;
		}


		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}


		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getRg() {
			return rg;
		}
		public void setRg(String rg) {
			this.rg = rg;
		}
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public String getInsc() {
			return insc;
		}
		public void setInsc(String insc) {
			this.insc = insc;
		}
		public Date getNasc() {
			return nasc;
		}
		public void setNasc(Date nasc) {
			this.nasc = nasc;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
    	
    	

    	
    	
    }

}
