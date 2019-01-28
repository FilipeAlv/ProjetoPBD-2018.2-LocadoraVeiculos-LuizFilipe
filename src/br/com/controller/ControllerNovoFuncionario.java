package br.com.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.model.beans.Endereco;
import br.com.model.beans.Funcionario;
import br.com.model.beans.Pessoa;
import br.com.model.dao.DAOFuncionario;
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

	@FXML
	void actionSalvar(ActionEvent event) {
		if(validarCampos()) {
			String codigo,nome,login,senha;
			String rua,numero, bairro, cidade, uf;
			String cpf, rg,sexo;
			Date dataNascimento;
			String cargo, permissao;

			Pessoa pessoa;

			codigo = "";
			bairro = fdBairro.getText().toString();
			cidade = fdCidade.getText().toString();
			numero = fdNumero.getText().toString();
			rua = fdRua.getText().toString();
			uf = cbUf.getValue();
			nome = fdNomeFi.getText().toString();
			login = fdLoginFi.getText().toString();
			senha = fdSenhaFi.getText().toString();
			cpf = fdCpf.getText().toString();
			rg= fdRg.getText().toString();
			sexo = radioMasculino.isSelected()?"Masculino":"Feminino";
			dataNascimento = Date.from(fdNacimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			permissao = cbPermissao.getValue();
			cargo = fdCargo.getText().toString();

			Endereco endereco = new Endereco(rua, numero, bairro, cidade, uf);
			pessoa = new Funcionario(codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo, cargo, permissao);
			pessoa.setCodigo(Util.gerarCodigo(pessoa));
			DAOFuncionario.getInstace().saveOrUpdate((Funcionario)pessoa);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso" );
			alert.setContentText("Este Funcionario foi salvo com successo!");
			alert.show();

		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro ao Salvar Funcionario" );
			alert.setContentText("Preencha todos os campos obrigatorios");
			alert.show();
		}
	}

	private boolean validarCampos() {
		if(fdBairro.getText().length()==0|| fdCidade.getText().length()==0 ||
				fdNumero.getText().length()==0 || fdRua.getText().length()==0||fdNomeFi.getText().length()==0 || 
				fdCpf.getText().length()==0||fdLoginFi.getText().length()==0||fdSenhaFi.getText().length()==0) {
			return false;
		}else if(!(fdSenhaFi.getText().length()>=6 || fdSenhaFi.getText().length()<=11)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro ao Salvar Funcionario" );
			alert.setContentText("As senhas devem conter entre 6 e 11 caracteres");
			alert.show();
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

	}


}
