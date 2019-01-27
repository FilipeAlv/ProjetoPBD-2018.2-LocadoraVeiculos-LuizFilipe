package br.com.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import br.com.model.beans.Endereco;
import br.com.model.beans.Motorista;
import br.com.model.dao.DAOMotorista;
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

public class ControllerNovoMotorista implements Initializable{

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
	private DatePicker fdVencimentoHab;
	
	@FXML
	private RadioButton radioMasculino;

	@FXML
	private ToggleGroup sexo;

	@FXML
	private RadioButton radioFeminino;


	@FXML
	void actionSalvar(ActionEvent event) {
		if(validarCampos()) {
			String codigo,nome,login,senha;
			String rua,numero, bairro, cidade, uf;
			String cpf, rg,sexo;
			Date dataNascimento;
			String habilitacao;
			Date validadeHabilitacao;



			codigo = "";
			nome = fdNomeFi.getText().toString();
			login = fdLoginFi.getText().toString();
			senha = fdSenhaFi.getText().toString();
			bairro = fdBairro.getText().toString();
			cidade = fdCidade.getText().toString();
			numero = fdNumero.getText().toString();
			rua = fdRua.getText().toString();
			uf = cbUf.getValue();
			cpf = fdCpf.getText().toString();
			rg= fdRg.getText().toString();
			sexo = radioMasculino.isSelected()?"Masculino":"Feminino";
			dataNascimento = Date.from(fdNacimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			habilitacao = fdHabilitacao.getText().toString();
			validadeHabilitacao = Date.from(fdVencimentoHab.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

			Endereco endereco = new Endereco(rua, numero, bairro, cidade, uf);

			Motorista motorista = new Motorista(codigo, nome, login, senha, endereco, cpf, rg, 
					dataNascimento, sexo, habilitacao, validadeHabilitacao);
			DAOMotorista.getInstace().saveOrUpdate(motorista);
			
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Este Motorista foi salvo com successo!");
    		alert.show();
    		
    	}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PB",
				"PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO");
		cbUf.setItems(ob);

	}

	private boolean validarCampos() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro ao Salvar Motorista" );
		if(fdBairro.getText().length()==0|| fdCidade.getText().length()==0 ||
				fdNumero.getText().length()==0 || fdRua.getText().length()==0||fdNomeFi.getText().length()==0 || 
				fdCpf.getText().length()==0||fdLoginFi.getText().length()==0||fdSenhaFi.getText().length()==0) {
    		alert.setContentText("Preencha todos os campos obrigatorios");
    		alert.show();
			return false;
		}else if(new Date().after(Date.from(fdVencimentoHab.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
			alert.setContentText("A Habilitação não pode estar vencida.");
			alert.show();
			return false;
		}else if(calculaIdade()<21) {
				alert.setContentText("O motorista tem menos de 21 anos.");
				alert.show();
				return false;
		}else if(!(fdSenhaFi.getText().toString().length()>=6 && fdSenhaFi.getText().toString().length()<=11)) {
			alert.setContentText("As senhas devem conter entre 6 e 11 caracteres");
			alert.show();
			return false;
		}
		return true;
	}


	private int calculaIdade() {
		Calendar atual = new GregorianCalendar();
		Calendar nascimento = new GregorianCalendar();
		Date dataNascimento = Date.from(fdNacimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		int anoNascimento, anoAtual;
		
		atual.setTime(new Date());
		nascimento.setTime(dataNascimento);
		
		anoAtual=atual.get(Calendar.YEAR);
		anoNascimento = nascimento.get(Calendar.YEAR);
		System.out.println(anoAtual-anoNascimento);
		return anoAtual-anoNascimento;
		
	}
}
