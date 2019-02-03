package br.com.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.model.beans.Config;
import br.com.model.dao.DAOConfig;
import br.com.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerConfiguracao implements Initializable{

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField fdHoraBackup;

	@FXML
	private TextField fdTempoRevisao;

	@FXML
	void actionSalvar(ActionEvent event) {
		Date horaBackup = converterHora(fdHoraBackup.getText().toString());
		Date tempoRevisao = converterHora(fdTempoRevisao.getText().toString());
		Config config = Config.getInstace();
		config.setHoraBackup(horaBackup);
		config.setTempoRevisao(tempoRevisao);
		DAOConfig.getInstance().saveOrUpdate(config);
	}

	public void initialize(URL location, ResourceBundle resources) {
		Util.Mascarar.Hora(fdHoraBackup);
		Util.Mascarar.Hora(fdTempoRevisao);
		if(Config.getInstace().getHoraBackup()!=null && Config.getInstace().getTempoRevisao()!=null){
			fdHoraBackup.setText(pegarHora(Config.getInstace().getHoraBackup()));
			fdTempoRevisao.setText(pegarHora(Config.getInstace().getTempoRevisao()));
		}
	};

	public Date converterHora(String hora) {
		SimpleDateFormat fd = new SimpleDateFormat("HH:mm");
		Date date ;
		try {
			date = fd.parse(hora);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String pegarHora(Date date) {
		SimpleDateFormat fd = new SimpleDateFormat("HH:mm");
		String data = fd.format(date);
		return data;
	}

}
