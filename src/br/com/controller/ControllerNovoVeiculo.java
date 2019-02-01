package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Categoria;
import br.com.model.beans.Filial;
import br.com.model.beans.Modelo;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOCategoria;
import br.com.model.dao.DAOFilial;
import br.com.model.dao.DAOModelo;
import br.com.model.dao.DAOVeiculo;
import br.com.util.Util;
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
    private  ComboBox<String> cbCategoria;

    @FXML
    private  ComboBox<String> cbModelo;
    
    @FXML
    private  ComboBox<String> cbFilial;

    @FXML
    private Button btnAddCategoria;

    @FXML
    private Button btnAddModelo;
    
    @FXML
    private Button btnAddFilial;

	private Veiculo v = new Veiculo();

	private boolean edit;

    @FXML
    void actionAddCategoria(ActionEvent event) {
    	Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovaCategoria.fxml"));
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
    void actionAddModelo(ActionEvent event) {
    	Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovoModelo.fxml"));
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
    void actionAddFilial(ActionEvent event) {
    	Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovaFilial.fxml"));
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
    	if(validarVeiculo()) {
	    	String placa,chassi, cor;
	    	double kmAtual;
	    	int numMotor;
	    	Categoria categoria;
	    	Modelo modelo;
	    	Filial filial;
	    	
	    	placa = Util.removerCaracteres(fdPlaca.getText().toString());
	    	chassi = fdChassi.getText().toString();
	    	cor = fdCor.getText().toString();
	    	kmAtual = Double.parseDouble(fdKmAtual.getText().toString());
	    	numMotor = Integer.parseInt(fdNumMotor.getText().toString());
	    	categoria = DAOCategoria.getInstance().findByNome(cbCategoria.getValue());
	    	modelo = DAOModelo.getInstance().findByNome(pegarModeloCOmbo(cbModelo.getValue()));
	    	filial = DAOFilial.getInstance().findByNome(cbFilial.getValue());
	    	
	    	v.setPlaca(placa);
	    	v.setChassi(chassi);
	    	v.setCor(cor);
	    	v.setKmAtual(kmAtual);
	    	v.setNumMotor(numMotor);
	    	v.setCategoria(categoria);
	    	v.setModelo(modelo);
	    	v.setFilialAtual(filial);
	    	v.setStatus("Disponivel");
	    	
	    	DAOVeiculo.getInstance().saveOrUpdate(v);
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Este veículo foi salvo com successo!");
    		alert.show();
    		
    		if(edit)
    			ControllerVeiculo.carregarTabela(DAOVeiculo.getInstance().findAll());  
    		
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<Categoria> categorias = DAOCategoria.getInstance().findAll();
		List<Modelo> modelos = DAOModelo.getInstance().findAll();
		List<Filial> filiais = DAOFilial.getInstance().findAll();

		for (Categoria categoria : categorias) {
			ob.add(categoria.getNome());
		}
		cbCategoria.setItems(ob);
		ob = FXCollections.observableArrayList();

		for(Modelo modelo : modelos) {
			ob.add(modelo.getNome()+"-"+modelo.getMarca().getNome());
		}
		cbModelo.setItems(ob);	
		
		ob = FXCollections.observableArrayList();

		for(Filial filial : filiais) {
			ob.add(filial.getNome());
		}
		cbFilial.setItems(ob);	
		
		Util.Mascarar.Placa(fdPlaca);
		Util.Mascarar.somenteNumeroInteiro(fdNumMotor);
		Util.Mascarar.somenteNumeroInteiro(fdChassi);
		Util.Mascarar.somenteNumero(fdKmAtual);
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
	
	private boolean validarVeiculo() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Cadastro de Veiculo" );
		alert.setHeaderText("Erro ao Salvar Veiculo");
		if(!validarCampos()) {
			alert.setContentText("Preencha todos os campos obrigatorios");
    		alert.show();
    		return false;
		}else if(DAOVeiculo.getInstance().findByPlaca(fdPlaca.getText().toString())!=null && !edit) {
			alert.setContentText("Placa Já Cadastrada");
    		alert.show();
    		return false;
		}else {
			return true;
		}
}

	public void carregarEditar(Veiculo veiculo) {
		this.v  = veiculo;
		edit = true;

    	fdPlaca.setText(veiculo.getPlaca());
    	fdChassi.setText(veiculo.getChassi());
    	fdCor.setText(veiculo.getCor());
    	fdKmAtual.setText(Double.toString(veiculo.getKmAtual()));
    	fdNumMotor.setText(Integer.toString(veiculo.getNumMotor()));
    	cbCategoria.getSelectionModel().select(veiculo.getCategoria().getNome());
    	cbModelo.getSelectionModel().select(veiculo.getModelo().getNome()+"-"
    			+veiculo.getModelo().getMarca().getNome());
    	cbFilial.getSelectionModel().select(veiculo.getFilialAtual().getNome());
	
	}
}
