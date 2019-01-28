package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.model.beans.CaminhonetaCarga;
import br.com.model.beans.CaminhonetaPassageiro;
import br.com.model.beans.Categoria;
import br.com.model.dao.DAOCaminhonetaCarga;
import br.com.model.dao.DAOCaminhonetaPassageiro;
import br.com.model.dao.DAOCategoria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControllerNovaCategoria implements Initializable{

    @FXML
    private TextField fdNome;

    @FXML
    private TextField fdTipoCambio;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private ComboBox<String> cbTamanho;

    @FXML
    private CheckBox checkAr;

    @FXML
    private CheckBox checkDirHidraulica;

    @FXML
    private CheckBox checkCamRe;

    @FXML
    private CheckBox checkRadio;

    @FXML
    private CheckBox checkMp3;

    @FXML
    private Pane paneCarga;

    @FXML
    private TextField fdCapacidade;

    @FXML
    private TextField fdDesempenho;

    @FXML
    private TextField fdVolTanque;

    @FXML
    private TextField fdDisEixos;

    @FXML
    private TextField fdPotMotor;

    @FXML
    private ComboBox<String> cbEmbreagem;

    @FXML
    private Pane panePassageiro;

    @FXML
    private CheckBox checkAirBag;

    @FXML
    private CheckBox checkDirAssis;

    @FXML
    private CheckBox checkCotrPoluicao;

    @FXML
    private CheckBox checkLigaLeve;

    @FXML
    private CheckBox checkCintos;

    @FXML
    private Label lbTitle;

    @FXML
    private Button btnSalvar;

    @FXML
    void actionSalvar(ActionEvent event) {
    	if(validarCampos()) {
    		String nome, tipoCambio, tamanho;
    		boolean arCondicionado, direcaoHidraulica, cameraRe, radio,mp3;
    		
    		Categoria categoria;
    		
    		nome = fdNome.getText().toString();
    		tipoCambio = fdTipoCambio.getText().toString();
    		tamanho = cbTamanho.getValue();
    		arCondicionado = checkAr.isSelected();
    		direcaoHidraulica = checkDirHidraulica.isSelected();
    		cameraRe = checkCamRe.isSelected();
    		radio = checkRadio.isSelected();
    		mp3= checkMp3.isSelected();
    		
    		switch (cbTipo.getValue()) {
			case "Comum":
				categoria = new Categoria(nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
				DAOCategoria.getInstance().saveOrUpdate(categoria);
				break;

			case "Caminhoneta Carga":
				int capacidade, volumeTanque, distanciaEixo;
				String desempenho, tipoEmbreagem;
				float potenciaMotor;
				
				capacidade = Integer.parseInt(fdCapacidade.getText().toString());
				volumeTanque = Integer.parseInt(fdVolTanque.getText().toString());
				distanciaEixo = Integer.parseInt(fdDisEixos.getText().toString());
				desempenho = fdDesempenho.getText();
				tipoEmbreagem = cbEmbreagem.getValue();
				potenciaMotor = Float.parseFloat(fdPotMotor.getText().toString());
				
				categoria = new CaminhonetaCarga(nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe
						, radio, mp3, capacidade, desempenho, volumeTanque, distanciaEixo, potenciaMotor, tipoEmbreagem);
				DAOCaminhonetaCarga.getInstace().saveOrUpdate((CaminhonetaCarga) categoria);
				break;
				
			case "Caminhoneta Passageiro":
				boolean airBag, direcaoAssistida,ligaLeve,cintosTrasRetrateis,contrPol;
				airBag= checkAirBag.isSelected();
				direcaoAssistida= checkDirAssis.isSelected();
				ligaLeve= checkLigaLeve.isSelected();
				cintosTrasRetrateis= checkCintos.isSelected();
				contrPol= checkCotrPoluicao.isSelected();
				
				categoria = new CaminhonetaPassageiro(nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe
						, radio, mp3, airBag, direcaoAssistida, ligaLeve, cintosTrasRetrateis, contrPol);
				DAOCaminhonetaPassageiro.getInstace().saveOrUpdate((CaminhonetaPassageiro) categoria); 
				
				break;
			}
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Esta categoria foi salva com successo!");
    		alert.show();
    		new ControllerNovaReserva().carregarCombo();
    		new ControllerNovoVeiculo().carregarCombo();
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro ao Salvar Categoria" );
    		alert.setContentText("Preencha todos os campos obrigatorios");
    		alert.show();
    	}
    	
    	
    }

    @FXML
    void actionTipo(ActionEvent event) {
    	String tipo = cbTipo.getValue();
    	switch (tipo) {
    	case "Caminhoneta Carga":
    		paneCarga.setVisible(true);
    		panePassageiro.setVisible(false);
    		break;
    	case "Caminhoneta Passageiro":
    		panePassageiro.setVisible(true);
    		paneCarga.setVisible(false);
    		break;
    	default:
    		panePassageiro.setVisible(false);
    		paneCarga.setVisible(false);
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
	
		paneCarga.setVisible(false);
		panePassageiro.setVisible(false);
		lbTitle.setVisible(false);
		
		ob.add("Comum");
		ob.add("Caminhoneta Carga");
		ob.add("Caminhoneta Passageiro");
		
		cbTipo.setItems(ob);
		
		ob = FXCollections.observableArrayList();
		
		ob.add("Pequeno");
		ob.add("Médio");
		ob.add("Grande");
		
		cbTamanho.setItems(ob);
		
		ob = FXCollections.observableArrayList();
		
		ob.add("Pino");
		ob.add("Martelo Simples");
		ob.add("Duplo Martelo");
		ob.add("Hocking Dog");
		ob.add("2 jaw");
		
		cbEmbreagem.setItems(ob);
	}


	
	private boolean validarCampos() {
		if(cbTipo.getValue()=="Comum" || cbTipo.getValue()=="Caminhoneta Passageiro") {
			if(fdNome.getText().length()==0 || fdTipoCambio.getText().length()==0) {
				return false;
			}
		}else if(cbTipo.getValue()=="Caminhoneta Carga") {
			if(fdCapacidade.getText().length()==0 || fdDesempenho.getText().length()==0 || fdDisEixos.getText().length()==0
					|| fdNome.getText().length()==0 || fdPotMotor.getText().length()==0 || fdTipoCambio.getText().length()==0
					|| fdVolTanque.getText().length()==0) {
				return false;
			}
		}
		return true;
	}

}
