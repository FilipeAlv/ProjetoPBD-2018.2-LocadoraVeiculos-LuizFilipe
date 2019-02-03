package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;
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

public class ControllerFinalizarLocacao implements Initializable{

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField fdValor;
    
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
    
    @FXML
    void verificarStatus(ActionEvent event) {
    	float valorLoc = Float.parseFloat(fdValor.getText().toString());
    	 if(fdStatus.getValue().equals("Sujo")){
 			Alert alert = new Alert(AlertType.INFORMATION);
 			alert.setContentText("Será cobrado Multa de 2%");
 			alert.show();
 			float valor = (float) (valorLoc*1.02);
 			fdValor.setText(Float.toString(valor));
 		}else if(fdStatus.getValue().equals("Sem Combustivel")) {
 			Alert alert = new Alert(AlertType.INFORMATION);
 			alert.setContentText("Será cobrado Multa de 3%");
 			alert.show();
 			float valor = (float) (valorLoc*1.03);
 			fdValor.setText(Float.toString(valor));
 		}else if(fdStatus.getValue().equals("Bom Estado")) {
 			fdValor.setText(Float.toString(valorLoc));
 		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		ob.addAll("Bom Estado","Sujo","Sem Combustivel");
	
		fdStatus.setItems(ob);
		
	}
	
	public void setValor(Double valor) {
		fdValor.setText(Double.toString(valor));
		fdValor.setEditable(false);
	}

}
