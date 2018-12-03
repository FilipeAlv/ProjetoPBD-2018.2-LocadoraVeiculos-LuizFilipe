package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.beans.Filial;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.PessoaJuridica;
import br.com.model.dao.DAOFilial;
import br.com.model.dao.DAOMotorista;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControllerNovaReserva implements Initializable{

    @FXML
    private DatePicker fdData;

    @FXML
    private TextField fdHora;

    @FXML
    private DatePicker fdDataEntrega;

    @FXML
    private TextField fdHoraEntrega;

    @FXML
    private ComboBox<String> cbFilial;

    @FXML
    private ComboBox<String> cbFilialEntrega;

    @FXML
    private ComboBox<String> cbMotorista;

    @FXML
    private ComboBox<String> cbCliente;

    @FXML
    private TextField fdValor;

    @FXML
    private ComboBox<String> cbTipo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<PessoaFisica> clientesF = DAOPessoaFisica.getInstace().findAll();
		List<PessoaJuridica> clientesJ = DAOPessoaJuridica.getInstace().findAll();
		List<Motorista> motoristas = DAOMotorista.getInstace().findAll();
		List<Filial> filiais = DAOFilial.getInstance().findAll();
		
		for (PessoaFisica pessoaFisica : clientesF) {
			ob.add(pessoaFisica.getNome()+" "+pessoaFisica.getCpf());
		}
		for (PessoaJuridica pessoaJuridica : clientesJ) {
			ob.add(pessoaJuridica.getNome()+ " " + pessoaJuridica.getCnpj());
		}
		cbCliente.setItems(ob);
		ob = FXCollections.observableArrayList();
		
		for (Filial filial : filiais) {
			ob.add(filial.getNome());
		}
		cbFilial.setItems(ob);
		cbFilialEntrega.setItems(ob);
		ob = FXCollections.observableArrayList();
		
		for(Motorista motorista : motoristas) {
			ob.add(motorista.getNome()+" "+motorista.getCpf());
		}
		cbMotorista.setItems(ob);
		ob = FXCollections.observableArrayList();
		
		ob.add("KMLivre");
		ob.add("KMControle");
		cbTipo.setItems(ob);
		
	}


	
}

    
   


