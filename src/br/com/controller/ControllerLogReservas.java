package br.com.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.ReservaAdapter;
import br.com.model.dao.DAOReservaBackup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerLogReservas implements Initializable{
	
	public static TableView<ReservaAdapter> tb;
	public static ObservableList<ReservaAdapter> ob;
	
    @FXML
    private TableView<ReservaAdapter> tbLog;

    @FXML
    private TableColumn<ReservaAdapter, Integer> codigoCol;

    @FXML
    private TableColumn<ReservaAdapter, String> clienteCol;

    @FXML
    private TableColumn<ReservaAdapter, String> filialCol;

    @FXML
    private TableColumn<ReservaAdapter, String> filialEntCol;

    @FXML
    private TableColumn<ReservaAdapter, String> motoristaCol;

    @FXML
    private TableColumn<ReservaAdapter, Date> dataInicialCol;

    @FXML
    private TableColumn<ReservaAdapter, Date> dataFinalCol;

    @FXML
    private TableColumn<ReservaAdapter, String> statusCol;

    @FXML
    private TableColumn<ReservaAdapter, Date> dataCol;
    
    @FXML
    private TableColumn<ReservaAdapter, String> usuarioCol;

    @FXML
    private TableColumn<ReservaAdapter, String> tipoCol;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnAtualizar;

    @FXML
    void actionBuscar(ActionEvent event) {

    }
    
    @FXML
   	void actionAtualizar(ActionEvent event) {
   		carregarTabela();
   		tbLog.refresh();
   	}

   	@Override
   	public void initialize(URL location, ResourceBundle resources) {
   		codigoCol.setCellValueFactory(
   				new PropertyValueFactory<>("codigo"));
   		clienteCol.setCellValueFactory(
   				new PropertyValueFactory<>("cliente"));
   		filialCol.setCellValueFactory(
   				new PropertyValueFactory<>("filial"));
   		filialEntCol.setCellValueFactory(
   				new PropertyValueFactory<>("filialEnt"));
   		motoristaCol.setCellValueFactory(
   				new PropertyValueFactory<>("motorista"));
   		dataInicialCol.setCellValueFactory(
   				new PropertyValueFactory<>("dataInicial"));
   		dataFinalCol.setCellValueFactory(
   				new PropertyValueFactory<>("dataFinal"));
   		statusCol.setCellValueFactory(
   				new PropertyValueFactory<>("status"));
   		dataCol.setCellValueFactory(
   				new PropertyValueFactory<>("data"));
   		usuarioCol.setCellValueFactory(
   				new PropertyValueFactory<>("usuario"));
   		tipoCol.setCellValueFactory(
   				new PropertyValueFactory<>("tipo"));

   		tb=tbLog;
   		carregarTabela();


   	}

   	private static void carregarTabela() {
   		List<ReservaAdapter> reservas = DAOReservaBackup.getInstance().findView();
   		ob = FXCollections.observableArrayList();
   		
//   		for (Reserva2 r : reservas) {
//   			ob.add(new ReservaAdapter(
//   					r.getId(), r.getCliente().getNome(), r.getFilial().getNome(),
//   					r.getFilialEntrega().getNome(), r.getMotorista().getCodigo(), 
//   					r.getDataInicial(), r.getStatus(), r.getDataFinalPrevista(), r.getDataModificacao()));
//   		}
   		
   		ob.addAll(reservas);
   		if (ob.size()>0) 
   			tb.setItems(ob);
   	}

}
