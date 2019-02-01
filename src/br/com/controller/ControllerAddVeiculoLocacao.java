package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOVeiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControllerAddVeiculoLocacao implements Initializable{

    @FXML
    private Button btnSalvar;

    @FXML
    private  ComboBox<String> cbVeiculo;

    @FXML
    void actionSalvar(ActionEvent event) {
    	String placa = pegarPlacaCombo(cbVeiculo.getValue());
    	Veiculo veiculo = DAOVeiculo.getInstance().findByPlaca(placa);
    	ControllerReserva.addVeiculo(veiculo);
		
		btnSalvar.setDisable(true);
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<Veiculo> veiculos = DAOVeiculo.getInstance().findByStatus("Disponivel");

		for (Veiculo veiculo : veiculos) {
			ob.add(veiculo.getModelo().getNome()+"-"+veiculo.getPlaca());
		}
		cbVeiculo.setItems(ob);
	}
	
	
	private String pegarPlacaCombo(String value) {
		int i = value.indexOf("-");
		String sub = value.substring(i+1, value.length());
		System.out.println(sub);
		return sub;
	}

    
    

}
