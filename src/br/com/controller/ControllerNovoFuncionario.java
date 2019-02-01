package br.com.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.model.beans.Endereco;
import br.com.model.beans.Funcionario;
import br.com.model.beans.PessoaFisica;
import br.com.model.dao.DAOFuncionario;
import br.com.model.dao.DAOPessoa;
import br.com.model.dao.DAOPessoaFisica;
import br.com.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class ControllerNovoFuncionario implements Initializable{

	@FXML
	private Label lbTitle;

	@FXML
	private TextField fdRua;

	@FXML
	private ComboBox<String> cbUf;

	@FXML
	private TextField fdNumero;

	@FXML
	private TextField fdBairro;

	@FXML
	private TextField fdCidade;

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField fdNomeFi;

	@FXML
	private TextField fdCpf;

	@FXML
	private TextField fdCargo;

	@FXML
	private TextField fdLoginFi;

	@FXML
	private TextField fdSenhaFi;

	@FXML
	private TextField fdRg;

	@FXML
	private DatePicker fdNacimento;

	@FXML
	private RadioButton radioMasculino;

	@FXML
	private RadioButton radioFeminino;

	@FXML
	private ToggleGroup sexo;


	@FXML
	private ComboBox<String> cbPermissao;

	private boolean edit;

	private Funcionario f = new Funcionario();

	@FXML
	void actionSalvar(ActionEvent event) {
		if(validarFuncionario()) {
			String codigo,nome,login,senha;
			String rua,numero, bairro, cidade, uf;
			String cpf, rg,sexo;
			Date dataNascimento;
			String cargo, permissao;

			codigo = "";
			bairro = fdBairro.getText().toString();
			cidade = fdCidade.getText().toString();
			numero = fdNumero.getText().toString();
			rua = fdRua.getText().toString();
			uf = cbUf.getValue();
			nome = fdNomeFi.getText().toString();
			login = fdLoginFi.getText().toString();
			senha = new String(Util.Criptografia.criptografa(fdSenhaFi.getText().toCharArray()));
			cpf = Util.removerCaracteres(fdCpf.getText().toString());
			rg= fdRg.getText().toString();
			sexo = radioMasculino.isSelected()?"Masculino":"Feminino";
			dataNascimento = Date.from(fdNacimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			permissao = cbPermissao.getValue();
			cargo = fdCargo.getText().toString();
			
			Endereco endereco = new Endereco(rua, numero, bairro, cidade, uf);
			
			f.setCodigo(codigo);
			f.setNome(nome);
			f.setLogin(login);
			f.setSenha(senha);
			((PessoaFisica) f).setCpf(cpf);
			((PessoaFisica) f).setRg(rg);
			((PessoaFisica) f).setSexo(sexo);
			((PessoaFisica) f).setDataNascimento(dataNascimento);
			f.setCargo(cargo);
			f.setPermissao(permissao);
			f.setEndereco(endereco);
			
			
			
			DAOFuncionario.getInstace().saveOrUpdate((Funcionario)f);
			if(!edit)
				DAOPessoa.getInstace().gerarCodigo(Util.subNome(f));

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso" );
			alert.setContentText("Este Funcionario foi salvo com successo!");
			alert.show();
			
			if(edit)
				ControllerFuncionario.carregarTabela();
		}
	}

	private boolean validarCampos() {
		if(fdBairro.getText().length()==0|| fdCidade.getText().length()==0 ||
				fdNumero.getText().length()==0 || fdRua.getText().length()==0||fdNomeFi.getText().length()==0 || 
				fdCpf.getText().length()==0||fdLoginFi.getText().length()==0||fdSenhaFi.getText().length()==0) {
			return false;
		}
		return true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PB",
				"PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO");
		cbUf.setItems(ob);

		ob = FXCollections.observableArrayList();
		ob.addAll("Administrador","Funcionario");
		cbPermissao.setItems(ob);
		
		Util.Mascarar.CPF(fdCpf);
		Util.Mascarar.Data(fdNacimento);

	}

	public void carregarEditar(Funcionario f) {
		this.f = f;
		this.edit = true;

		fdNomeFi.setText(f.getNome());
		fdLoginFi.setText(f.getLogin());
		fdNacimento.setValue(((PessoaFisica) f).getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		fdSenhaFi.setText(Util.Criptografia.decriptografa(f.getSenha().toCharArray()));
		fdCpf.setText(((PessoaFisica) f).getCpf());
		fdRg.setText(((PessoaFisica) f).getRg());
		String sex = ((PessoaFisica)f).getSexo();
		if(sex.equals("Masculino"))
			sexo.selectToggle(radioMasculino);				
		else
			sexo.selectToggle(radioFeminino);
		fdCargo.setText(f.getCargo());
		cbPermissao.getSelectionModel().select(f.getPermissao());
		
		fdRua.setText(f.getEndereco().getRua());
		fdBairro.setText(f.getEndereco().getBairro());
		fdCidade.setText(f.getEndereco().getCidade());
		fdNumero.setText(f.getEndereco().getNumero());
		cbUf.getSelectionModel().select(f.getEndereco().getUf());

	}
	
	private boolean validarFuncionario() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Cadastro de Cliente" );
		alert.setHeaderText("Erro ao Salvar Cliente" );

		if(!validarCampos()) {
			alert.setContentText("Preencha todos os campos");
			alert.show();
			return false;
		}else if(DAOPessoaFisica.getInstace().findByCpf(Util.removerCaracteres(fdCpf.getText().toString()))!=null && !edit) {
			alert.setContentText("CPF Já Cadastrado");
			alert.show();
			return false;
		}else if(DAOPessoa.getInstace().findByLogin(fdLoginFi.getText())!=null && !edit) {
			alert.setContentText("Login Já Cadastrado");
			alert.show();
			return false;
		}else if(Date.from(fdNacimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).after(new Date())) {
			alert.setContentText("A data de Nascimento não pode ser superior a data atual");
			alert.show();
			return false;
		}else if(!(fdSenhaFi.getText().length()>=6 && fdSenhaFi.getText().length()<=11)) {
			alert.setContentText("As senhas devem conter entre 6 e 11 caracteres");
			alert.show();
			return false;
		}
		else{
			return true;
		}

	}



}
