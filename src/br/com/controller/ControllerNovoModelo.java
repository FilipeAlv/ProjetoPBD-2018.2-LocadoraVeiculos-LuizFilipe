package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.main.Main;
import br.com.model.beans.Marca;
import br.com.model.beans.Modelo;
import br.com.model.dao.DAOMarca;
import br.com.model.dao.DAOModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
    

    @FXML
    void actionAddMarca(ActionEvent event) {
    	Main.novaTela("NovaMarca");
    }

    @FXML
    void actionSalvar(ActionEvent event) {
    	if(validarCampos()) {
    		Modelo modelo;
    		
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
    		
    		modelo = new Modelo(anoFabricacao, nome, anoModelo, torqueMotor, numPassageiro, numPorta, tempoLimpeza, combustivel, marca);
    		DAOModelo.getInstance().saveOrUpdate(modelo);
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Este Modelo foi salvo com successo!");
    		alert.show();
    		
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro ao Salvar Modelo" );
    		alert.setContentText("Preencha todos os campos obrigatorios");
    		alert.show();
    	}
    	
    	new ControllerNovoVeiculo().carregarCombo();
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
