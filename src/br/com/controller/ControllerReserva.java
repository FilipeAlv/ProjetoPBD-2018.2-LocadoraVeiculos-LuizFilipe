package br.com.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.main.Main;
import br.com.model.beans.Reserva;
import br.com.model.dao.DAOReserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControllerReserva implements Initializable{
	public static TableView<ReservaAux> tb;
	public static ObservableList<ReservaAux> ob;
    @FXML
    private ImageView btnClose;

    @FXML
    private TableView<ReservaAux> tbReserva;

    @FXML
    private TableColumn<ReservaAux, String> clienteCol;

    @FXML
    private TableColumn<ReservaAux, String> categoriaCol;

    @FXML
    private TableColumn<ReservaAux, String> valorCol;

    @FXML
    private TableColumn<ReservaAux, String> dataCol;

    @FXML
    private Button btnBuscarReserva;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnAtualizar;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnEditar;

    @FXML
    void actionAddReserva(ActionEvent event) {

    }

    @FXML
    void actionBuscarReserva(ActionEvent event) {

    }

    @FXML
    void actionClose(MouseEvent event) {

    }

    @FXML
    void actionEditar(ActionEvent event) {

    }
    
    @FXML
    void actionAtualizar(ActionEvent event) {
    	carregarTabela();
    	tbReserva.refresh();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoriaCol.setCellValueFactory(
                new PropertyValueFactory<>("categoria"));
        clienteCol.setCellValueFactory(
                new PropertyValueFactory<>("cliente"));
        valorCol.setCellValueFactory(
                new PropertyValueFactory<>("valor"));

        dataCol.setCellValueFactory(
                new PropertyValueFactory<>("data"));

		tb=tbReserva;
		carregarTabela();
		
		
	}
	
	public static void carregarTabela() {
		List<Reserva> reservas = DAOReserva.getInstance().findAll();
		ob = FXCollections.observableArrayList();
		for (Reserva reserva : reservas) {
			ob.add(new ReservaAux(reserva.getCliente().getNome(), reserva.getValorPrevisto(), reserva.getCategoria().getNome(),
					reserva.getDataInicial()) );
		}
		if (ob.size()>0) 
			tb.setItems(ob);
	}
	
	
	public static class ReservaAux{
		 private String cliente;
		 private String categoria;
		 private Double valor;
		 private Date data;
		
		public ReservaAux(String cliente,Double valor,String categoria, Date data) {
			this.cliente = cliente;
			this.valor = valor;
			this.categoria = categoria;
			this.data = data;
		}

		public String getCliente() {
			return cliente;
		}

		public void setCliente(String cliente) {
			this.cliente = cliente;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public Double getValor() {
			return valor;
		}

		public void setValor(Double valor) {
			this.valor = valor;
		}

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		
		
	}

}
