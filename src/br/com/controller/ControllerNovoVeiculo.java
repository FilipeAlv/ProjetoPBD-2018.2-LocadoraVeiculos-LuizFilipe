package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.main.Main;
import br.com.model.beans.Categoria;
import br.com.model.beans.Filial;
import br.com.model.beans.Modelo;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOCategoria;
import br.com.model.dao.DAOFilial;
import br.com.model.dao.DAOModelo;
import br.com.model.dao.DAOVeiculo;
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

public class ControllerNovoVeiculo implements Initializable{

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField fdKmAtual;

    @FXML
    private TextField fdPlaca;

    @FXML
    private TextField fdNumMotor;

    @FXML
    private TextField fdCor;

    @FXML
    private TextField fdChassi;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private ComboBox<String> cbModelo;
    
    @FXML
    private ComboBox<String> cbFilial;

    @FXML
    private Button btnAddCategoria;

    @FXML
    private Button btnAddModelo;
    
    @FXML
    private Button btnAddFilial;

    @FXML
    void actionAddCategoria(ActionEvent event) {
    	Main.novaTela("NovaCategoria");
    }

    @FXML
    void actionAddModelo(ActionEvent event) {
    	Main.novaTela("NovoModelo");
    }
    
    @FXML
    void actionAddFilial(ActionEvent event) {
    	Main.novaTela("NovaFilial");
    }

    @FXML
    void actionSalvar(ActionEvent event) {
    	if(validarCampos()) {
	    	Veiculo veiculo;
	    	String placa,chassi, cor;
	    	double kmAtual;
	    	int numMotor;
	    	Categoria categoria;
	    	Modelo modelo;
	    	Filial filial;
	    	
	    	placa = fdPlaca.getText().toString();
	    	chassi = fdChassi.getText().toString();
	    	cor = fdCor.getText().toString();
	    	kmAtual = Double.parseDouble(fdKmAtual.getText().toString());
	    	numMotor = Integer.parseInt(fdNumMotor.getText().toString());
	    	categoria = DAOCategoria.getInstance().findByNome(cbCategoria.getValue());
	    	modelo = DAOModelo.getInstance().findByNome(pegarModeloCOmbo(cbModelo.getValue()));
	    	filial = DAOFilial.getInstance().findByNome(cbFilial.getValue());
	    	
	    	veiculo = new Veiculo(placa, chassi, kmAtual, numMotor, cor, categoria, modelo, filial);
	    	DAOVeiculo.getInstance().saveOrUpdate(veiculo);
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Este veículo foi salvo com successo!");
    		alert.show();
    		
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro ao Salvar Veiculo" );
    		alert.setContentText("Preencha todos os campos obrigatorios");
    		alert.show();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<Categoria> categorias = DAOCategoria.getInstance().findAll();
		List<Modelo> modelos = DAOModelo.getInstance().findAll();

		for (Categoria categoria : categorias) {
			ob.add(categoria.getNome());
		}
		cbCategoria.setItems(ob);
		ob = FXCollections.observableArrayList();

		for(Modelo modelo : modelos) {
			ob.add(modelo.getNome()+"-"+modelo.getMarca().getNome());
		}
		cbModelo.setItems(ob);		
	}

	private String pegarModeloCOmbo(String value) {
		int i = value.indexOf("-");
		String sub = value.substring(0,i);
		System.out.println(sub);
		return sub;
	}
	
	private boolean validarCampos() {
		if(fdPlaca.getText().length()==0 ||
    	fdChassi.getText().length()==0||
    	fdCor.getText().length()==0||
    	fdKmAtual.getText().length()==0||
    	fdNumMotor.getText().length()==0) {
			return false;
		}
		return true;
	}
}
