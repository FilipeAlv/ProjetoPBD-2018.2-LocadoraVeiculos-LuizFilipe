package br.com.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import br.com.model.beans.Endereco;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.PessoaJuridica;
import br.com.model.dao.DAOMotorista;
import br.com.model.dao.DAOPessoa;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
import br.com.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class ControllerNovoCliente implements Initializable{

	@FXML
	private Label lbTitle;

	@FXML
	private TextField fdRua;

	@FXML
	private  ComboBox<String> cbUf;

	@FXML
	private TextField fdNumero;

	@FXML
	private TextField fdBairro;

	@FXML
	private TextField fdCidade;

	@FXML
	private Button btnSalvar;

	@FXML
	private  Pane panelJuridica;

	@FXML
	private Pane panelFisica;

	@FXML
	private TextField fdNome;

	@FXML
	private TextField fdCnpj;

	@FXML
	private TextField fdLoginJu;

	@FXML
	private TextField fdSenhaJu;

	@FXML
	private TextField fdInscricao;

	@FXML
	private TextField fdNomeFi;

	@FXML
	private CheckBox checkMotorista;

	@FXML
	private TextField fdCpf;

	@FXML
	private TextField fdHabilitacao;

	@FXML
	private TextField fdLoginFi;

	@FXML
	private TextField fdSenhaFi;

	@FXML
	private TextField fdRg;

	@FXML
	private DatePicker fdNacimento;

	@FXML
	private DatePicker fdValidadeHabilitacao;

	@FXML
	private RadioButton radioMasculino;

	@FXML
	private ToggleGroup sexo;

	@FXML
	private RadioButton radioFeminino;

	@FXML
	private ComboBox<String> cbTipo;

	@FXML
	void actionIsMotorista(ActionEvent event) {
		if(checkMotorista.isSelected()) {
			fdValidadeHabilitacao.setVisible(true);
			fdHabilitacao.setVisible(true);
		}else {
			fdValidadeHabilitacao.setVisible(false);
			fdHabilitacao.setVisible(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PB",
				"PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO");
		cbUf.setItems(ob);

		ob = FXCollections.observableArrayList();
		ob.addAll("Pessoa Física","Pessoa Juridica");
		cbTipo.setItems(ob);
		cbTipo.setValue("Pessoa Física");

		panelJuridica.setVisible(false);
		fdValidadeHabilitacao.setVisible(false);
		fdHabilitacao.setVisible(false);

		Util.Mascarar.CPF(fdCpf);
		Util.Mascarar.CNPJ(fdCnpj);
		Util.Mascarar.InscricaoEstadual(fdInscricao);
		Util.Mascarar.Habilitacao(fdHabilitacao);
		Util.Mascarar.Data(fdNacimento);
		Util.Mascarar.Data(fdValidadeHabilitacao);


	}


	@FXML
	void actionSalvar(ActionEvent event) {
		if(validarCliente()) {
			String codigo,nome,login,senha;
			String rua,numero, bairro, cidade, uf;
			String cpf, rg,sexo;
			Date dataNascimento;
			String habilitacao;
			Date validadeHabilitacao;

			Pessoa pessoa;

			codigo = "";
			bairro = fdBairro.getText().toString();
			cidade = fdCidade.getText().toString();
			numero = fdNumero.getText().toString();
			rua = fdRua.getText().toString();
			uf = cbUf.getValue();

			Endereco endereco = new Endereco(rua, numero, bairro, cidade, uf);

			switch (cbTipo.getValue()) {
			case "Pessoa Física":
				nome = fdNomeFi.getText().toString();
				login = fdLoginFi.getText().toString();
				senha = new String( Util.Criptografia.criptografa(fdSenhaFi.getText().toCharArray()));
				cpf = Util.removerCaracteres(fdCpf.getText().toString());
				rg= fdRg.getText().toString();
				sexo = radioMasculino.isSelected()?"Masculino":"Feminino";
				dataNascimento = Date.from(fdNacimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

				if(checkMotorista.isSelected()) {
					habilitacao = Util.removerCaracteres(fdHabilitacao.getText().toString());
					validadeHabilitacao =Date.from(fdValidadeHabilitacao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					pessoa = new Motorista(codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo, habilitacao, validadeHabilitacao);
					DAOMotorista.getInstace().saveOrUpdate((Motorista)pessoa);
					DAOPessoa.getInstace().gerarCodigo(Util.subNome(pessoa));
				}else {
					pessoa = new PessoaFisica(codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);
					DAOPessoaFisica.getInstace().saveOrUpdate((PessoaFisica)pessoa);
					DAOPessoa.getInstace().gerarCodigo(Util.subNome(pessoa));
				}
				break;

			case "Pessoa Juridica":
				String cnpj, inscricao;
				nome = fdNome.getText().toString();
				login = fdLoginJu.getText().toString();
				senha = new String( Util.Criptografia.criptografa(fdSenhaJu.getText().toCharArray()));

				cnpj = Util.removerCaracteres(fdCnpj.getText().toString());
				inscricao = Util.removerCaracteres(fdInscricao.getText().toString());

				pessoa = new PessoaJuridica(codigo, nome, login, senha, endereco, cnpj, inscricao);
				pessoa.setCodigo(Util.gerarCodigo(pessoa));
				DAOPessoaJuridica.getInstace().saveOrUpdate((PessoaJuridica)pessoa);
				break;
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cadastro de Cliente" );
			alert.setHeaderText("Sucesso");
			alert.setContentText("Este cliente foi salvo com successo!");
			alert.show();

		}
	}

	@FXML
	void actionTipoPessoa(ActionEvent event) {
		switch (cbTipo.getValue()) {
		case "Pessoa Física":
			panelJuridica.setVisible(false);
			panelFisica.setVisible(true);
			break;

		case "Pessoa Juridica":
			panelJuridica.setVisible(true);
			panelFisica.setVisible(false);
			break;
		}
	}
	
	public void carregarEditar(Pessoa pessoa) {
		if(pessoa instanceof PessoaFisica || pessoa instanceof Motorista) {
			panelJuridica.setVisible(false);
			fdNomeFi.setText(pessoa.getNome());
			fdLoginFi.setText(pessoa.getLogin());
			fdNacimento.setValue(((PessoaFisica) pessoa).getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			fdSenhaFi.setText(Util.Criptografia.decriptografa(pessoa.getSenha().toCharArray()));
			fdCpf.setText(((PessoaFisica) pessoa).getCpf());
			fdRg.setText(((PessoaFisica) pessoa).getRg());
		}
		if(pessoa instanceof PessoaJuridica) {
			panelFisica.setVisible(false);
			fdNome.setText(pessoa.getNome());
			fdLoginJu.setText(pessoa.getLogin());
			fdSenhaJu.setText(Util.Criptografia.decriptografa(pessoa.getSenha().toCharArray()));
			fdCnpj.setText(((PessoaJuridica) pessoa).getCnpj());
			fdInscricao.setText(((PessoaJuridica) pessoa).getInscricaoEstadual());
		}
		if(pessoa instanceof Motorista) {
			fdHabilitacao.setText(((Motorista) pessoa).getHabilitacao());
			fdValidadeHabilitacao.setValue(((Motorista) pessoa).getValidadeHabilitacao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			checkMotorista.setSelected(true);
			fdHabilitacao.setVisible(true);
			fdValidadeHabilitacao.setVisible(true);
		}
		
		fdRua.setText(pessoa.getEndereco().getRua());
		fdBairro.setText(pessoa.getEndereco().getBairro());
		fdCidade.setText(pessoa.getEndereco().getCidade());
		fdNumero.setText(pessoa.getEndereco().getNumero());
		cbUf.getSelectionModel().select(pessoa.getEndereco().getUf());
	}


	private boolean validarCampos() {
		if(fdBairro.getText().length()==0|| fdCidade.getText().length()==0 ||
				fdNumero.getText().length()==0 || fdRua.getText().length()==0) {
			return false;
		}else
			if(cbTipo.getValue()=="Pessoa Física") {
				if(fdNomeFi.getText().length()==0 || fdCpf.getText().length()==0||fdLoginFi.getText().length()==0||
						fdSenhaFi.getText().length()==0) {
					return false;
				}else if(!(fdSenhaFi.getText().length()>=6 && fdSenhaFi.getText().length()<=11)) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erro ao Salvar Cliente" );
					alert.setHeaderText("Verifique a senha digitada");
					alert.setContentText("As senhas devem conter entre 6 e 11 caracteres");
					alert.show();
					return false;
				}
			}else if(cbTipo.getValue()=="Pessoa Juridica") {
				if(fdNome.getText().length()==0 || fdCnpj.getText().length()==0 || fdInscricao.getText().length()==0
						|| fdLoginJu.getText().length()==0 || fdSenhaJu.getText().length()==0) {
					return false;
				}else if(!(fdSenhaFi.getText().length()>=6 && fdSenhaFi.getText().length()<=11)) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erro ao Salvar Cliente" );
					alert.setHeaderText("Verifique a senha digitada");
					alert.setContentText("As senhas devem conter entre 6 e 11 caracteres");
					alert.show();
					return false;
				}
			}
		return true;
	}

	private boolean validarCliente() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Cadastro de Cliente" );
		alert.setHeaderText("Erro ao Salvar Cliente" );

		if(!validarCampos()) {
			alert.setContentText("Preencha todos os campos");
			alert.show();
			return false;
		}else if(cbTipo.getValue()=="Pessoa Física" && 
				DAOPessoaFisica.getInstace().findByCpf(Util.removerCaracteres(fdCpf.getText().toString()))!=null) {
			alert.setContentText("CPF Já Cadastrado");
			alert.show();
			return false;
		}else if(cbTipo.getValue()=="Pessoa Juridica" && 
				DAOPessoaJuridica.getInstace().findByCnpj(Util.removerCaracteres(fdCnpj.getText().toString()))!=null) {
			alert.setContentText("CNPJ Já Cadastrado");
			alert.show();
			return false;
		}else if((cbTipo.getValue().equals("Pessoa Física")&&DAOPessoa.getInstace().findByLogin(fdLoginFi.getText())!=null) ||
				(cbTipo.getValue().equals("Pessoa Juridica")&&DAOPessoa.getInstace().findByLogin(fdLoginJu.getText())!=null)) {
			alert.setContentText("Login Já Cadastrado");
			alert.show();
			return false;
		}else if(checkMotorista.isSelected()&&
				DAOMotorista.getInstace().findByHabilitacao(fdHabilitacao.getText().toString())!=null) {
			alert.setContentText("CNH Já Cadastrada");
			alert.show();
			return false;
		}else if(cbTipo.getValue().equals("Pessoa Física") && 
				Date.from(fdNacimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).after(new Date())) {
			alert.setContentText("A data de Nascimento não pode ser superior a data atual");
			alert.show();
			return false;
		}else {
			return true;
		}

	}




}
