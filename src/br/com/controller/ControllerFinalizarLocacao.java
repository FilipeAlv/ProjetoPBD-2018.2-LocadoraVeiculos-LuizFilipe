package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerFinalizarLocacao implements Initializable{

    @FXML
    private Button btnSalvar;

    @FXML
    private static TextField fdValor;
    
    @FXML
    private TextField fdKm;

    @FXML
    private ComboBox<String> fdStatus;

    @FXML
    void actionSalvar(ActionEvent event) {
    	float valor = Float.parseFloat(fdValor.getText().toString());
    	String status = fdStatus.getValue();
    	String km = fdKm.getText().toString();
    	
    	ControllerLocacao.finalizar(valor, status, km);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("Bom Estado","Sujo","Sem Combustivel");
	
		fdStatus.setItems(ob);
		
	}
	
	public static void setValor(Double valor) {
		fdValor.setText(valor.toString());
	}

}
