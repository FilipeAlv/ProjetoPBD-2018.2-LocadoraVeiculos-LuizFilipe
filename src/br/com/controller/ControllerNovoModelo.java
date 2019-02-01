package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Marca;
import br.com.model.beans.Modelo;
import br.com.model.dao.DAOMarca;
import br.com.model.dao.DAOModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerNovoModelo implements Initializable{

	@FXML
	private Button btnAdd;

	@FXML
	private TextField fdNome;

	@FXML
	private  ComboBox<String> cbMarca;

	@FXML
	private TextField fdAnoFab;

	@FXML
	private TextField fdAnoModel;

	@FXML
	private TextField fdTorqueMotor;

	@FXML
	private TextField fdNumPortas;

	@FXML
	private TextField fdTempoLimpeza;

	@FXML
	private TextField fdCombustivel;

	@FXML
	private TextField fdNumPassageiro;

	@FXML
	private Button btnSalvar;

	private boolean edit;

	private Modelo m = new Modelo();


	@FXML
	void actionAddMarca(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovaMarca.fxml"));
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
	void actionSalvar(ActionEvent event) {
		if(validarModelo()) {
			String nome, combustivel;
			int anoFabricacao, anoModelo,numPassageiro, numPorta, tempoLimpeza;
			float torqueMotor;
			Marca marca;

			nome = fdNome.getText().toString();
			combustivel = fdCombustivel.getText().toString();
			anoFabricacao = Integer.parseInt(fdAnoFab.getText().toString());
			anoModelo = Integer.parseInt(fdAnoModel.getText().toString());
			numPassageiro = Integer.parseInt(fdNumPassageiro.getText().toString());
			numPorta = Integer.parseInt(fdNumPortas.getText().toString());
			tempoLimpeza = Integer.parseInt(fdTempoLimpeza.getText().toString());
			torqueMotor = Float.parseFloat(fdTorqueMotor.getText().toString());
			marca = DAOMarca.getInstance().findByNome(cbMarca.getValue());

			m.setNome(nome);
			m.setCombustivel(combustivel);
			m.setAnoFabricacao(anoFabricacao);
			m.setAnoModelo(anoModelo);
			m.setNumPassageiro(numPassageiro);
			m.setNumPorta(numPorta);
			m.setTempoLimpeza(tempoLimpeza);
			m.setTorqueMotor(torqueMotor);
			m.setMarca(marca);
			
			DAOModelo.getInstance().saveOrUpdate(m);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso" );
			alert.setContentText("Este Modelo foi salvo com successo!");
			alert.show();

		}
	}

	private boolean validarModelo() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Cadastro de Modelo" );
		alert.setHeaderText("Erro ao Salvar Modelo");
		if(!validarCampos()) {
			alert.setContentText("Preencha todos os campos obrigatorios");
			alert.show();
			return false;
		}else if(DAOModelo.getInstance().findByNome(fdNome.getText().toString())!=null && !edit) {
			alert.setContentText("Modelo Já Cadastrada");
			alert.show();
			return false;
		}
		return true;
	}
	
	public void carregarEditar(Modelo m) {
		this.m  = m;
		edit = true;
		
		fdNome.setText(m.getNome());
		fdCombustivel.setText(m.getCombustivel());
		fdAnoFab.setText(Integer.toString(m.getAnoFabricacao()));
		fdAnoModel.setText(Integer.toString(m.getAnoModelo()));
		fdNumPassageiro.setText(Integer.toString(m.getNumPassageiro()));
		fdNumPortas.setText(Integer.toString(m.getNumPorta()));
		fdTempoLimpeza.setText(Integer.toString(m.getTempoLimpeza()));
		fdTorqueMotor.setText(Float.toString(m.getTorqueMotor()));
		cbMarca.getSelectionModel().select(m.getMarca().getNome());
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarCombo();
	}

	public void carregarCombo() {
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<Marca> marcas = DAOMarca.getInstance().findAll();

		for (Marca marca : marcas) {
			ob.add(marca.getNome());
		}
		cbMarca.setItems(ob);
	}

	private boolean validarCampos() {
		if(fdNome.getText().length()==0||
				fdCombustivel.getText().length()==0||
				fdAnoFab.getText().length()==0||
				fdAnoModel.getText().length()==0||
				fdNumPassageiro.getText().length()==0||
				fdNumPortas.getText().length()==0||
				fdTempoLimpeza.getText().length()==0||
				fdTorqueMotor.getText().length()==0) {

			return false;
		}
		return true;
	}


}
