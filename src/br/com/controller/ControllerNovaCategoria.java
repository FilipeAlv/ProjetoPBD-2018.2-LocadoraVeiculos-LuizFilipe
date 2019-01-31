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

	private Categoria c = new Categoria();
	private boolean edit;

	@FXML
	void actionSalvar(ActionEvent event) {
		if(cbTipo.getValue()==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro ao Salvar Categoria" );
			alert.setContentText("Selecione o tipo de categoria");
			alert.show();
		}else {
			if(validarCategoria()) {
				String nome, tipoCambio, tamanho;
				boolean arCondicionado, direcaoHidraulica, cameraRe, radio,mp3;

				nome = fdNome.getText().toString();
				tipoCambio = fdTipoCambio.getText().toString();
				tamanho = cbTamanho.getValue();
				arCondicionado = checkAr.isSelected();
				direcaoHidraulica = checkDirHidraulica.isSelected();
				cameraRe = checkCamRe.isSelected();
				radio = checkRadio.isSelected();
				mp3= checkMp3.isSelected();

				c.setNome(nome);
				c.setTamanho(tamanho);
				c.setTipoCambio(tipoCambio);
				c.setArCondicionado(arCondicionado);
				c.setDirecaoHidraulica(direcaoHidraulica);
				c.setCameraRe(cameraRe);
				c.setRadio(radio);
				c.setMp3(mp3);



				switch (cbTipo.getValue()) {
				case "Comum":
					DAOCategoria.getInstance().saveOrUpdate(c);
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

					((CaminhonetaCarga) c).setCapacidade(capacidade);
					((CaminhonetaCarga) c).setDesempenho(desempenho);
					((CaminhonetaCarga) c).setVolumeTanque(volumeTanque);
					((CaminhonetaCarga) c).setDistanciaEixo(distanciaEixo);
					((CaminhonetaCarga) c).setPotenciaMotor(potenciaMotor);
					((CaminhonetaCarga) c).setTipoEmbreagem(tipoEmbreagem);

					DAOCaminhonetaCarga.getInstace().saveOrUpdate((CaminhonetaCarga) c);
					break;

				case "Caminhoneta Passageiro":
					boolean airBag, direcaoAssistida,ligaLeve,cintosTrasRetrateis,contrPol;
					airBag= checkAirBag.isSelected();
					direcaoAssistida= checkDirAssis.isSelected();
					ligaLeve= checkLigaLeve.isSelected();
					cintosTrasRetrateis= checkCintos.isSelected();
					contrPol= checkCotrPoluicao.isSelected();

					((CaminhonetaPassageiro) c).setAirBag(airBag);
					((CaminhonetaPassageiro) c).setDirecaoAssistida(direcaoAssistida);
					((CaminhonetaPassageiro) c).setLigaLeve(ligaLeve);
					((CaminhonetaPassageiro) c).setCintosTrasRetrateis(cintosTrasRetrateis);
					((CaminhonetaPassageiro) c).setContrPol(contrPol);

					DAOCaminhonetaPassageiro.getInstace().saveOrUpdate((CaminhonetaPassageiro) c); 

					break;
				}

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucesso" );
				alert.setContentText("Esta categoria foi salva com successo!");
				alert.show();
				
				if(edit)
					ControllerCategoria.carregarTabela();
			}
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

	public void carregarEditar(Categoria categoria) {
		this.c = categoria;
		edit = true;
		cbTipo.setDisable(true);
		cbTipo.getSelectionModel().select("Comum");

		if(categoria instanceof CaminhonetaCarga) {
			paneCarga.setVisible(true);
			cbTipo.getSelectionModel().select("Caminhoneta Carga");

			fdCapacidade.setText(Integer.toString(((CaminhonetaCarga) categoria).getCapacidade()));
			fdDesempenho.setText(((CaminhonetaCarga) categoria).getDesempenho());
			fdVolTanque.setText(Integer.toString(((CaminhonetaCarga) categoria).getVolumeTanque()));
			fdDisEixos.setText(Integer.toString(((CaminhonetaCarga) categoria).getDistanciaEixo()));
			fdPotMotor.setText(Float.toString(((CaminhonetaCarga) categoria).getPotenciaMotor()));
			cbEmbreagem.getSelectionModel().select(((CaminhonetaCarga) categoria).getTipoEmbreagem());

		}
		if(categoria instanceof CaminhonetaPassageiro) {
			panePassageiro.setVisible(true);
			cbTipo.getSelectionModel().select("Caminhoneta Passageiro");

			checkAirBag.setSelected(((CaminhonetaPassageiro) categoria).isAirBag());
			checkDirAssis.setSelected(((CaminhonetaPassageiro) categoria).isDirecaoAssistida());
			checkLigaLeve.setSelected(((CaminhonetaPassageiro) categoria).isLigaLeve());
			checkCintos.setSelected(((CaminhonetaPassageiro) categoria).isCintosTrasRetrateis());
			checkCotrPoluicao.setSelected(((CaminhonetaPassageiro) categoria).isContrPol());
		}

		fdNome.setText(categoria.getNome());
		cbTamanho.getSelectionModel().select(categoria.getTamanho());
		fdTipoCambio.setText(categoria.getTipoCambio());
		checkAr.setSelected(categoria.isArCondicionado());
		checkDirHidraulica.setSelected(categoria.isDirecaoHidraulica());
		checkCamRe.setSelected(categoria.isCameraRe());
		checkRadio.setSelected(categoria.isRadio());
		checkMp3.setSelected(categoria.isMp3());
	}



	private boolean validarCampos() {
		if(cbTipo.getValue()=="Comum" || cbTipo.getValue()=="Caminhoneta Passageiro") {
			if((fdNome.getText().length()==0 || fdTipoCambio.getText().length()==0)) {
				return false;
			}
		}else if(cbTipo.getValue()=="Caminhoneta Carga") {
			if((fdCapacidade.getText().length()==0 || fdDesempenho.getText().length()==0 || fdDisEixos.getText().length()==0
					|| fdNome.getText().length()==0 || fdPotMotor.getText().length()==0 || fdTipoCambio.getText().length()==0
					|| fdVolTanque.getText().length()==0)) {
				return false;
			}
		}
		return true;

	}

	private boolean validarCategoria() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erro ao Salvar Categoria" );
		alert.setTitle("Cadastro de Categoria" );

		if(!validarCampos()) {
			alert.setContentText("Preencha todos os campos");
			alert.show();
			return false;
		}else if(DAOCategoria.getInstance().findByNome(fdNome.getText().toString())!=null && !edit) {
			alert.setContentText("Este nome já está usado em outra categoria");
			alert.show();
			return false;
		}
		return true;
	}

}
