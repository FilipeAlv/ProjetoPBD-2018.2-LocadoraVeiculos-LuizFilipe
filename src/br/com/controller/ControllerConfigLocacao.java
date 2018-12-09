package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.com.main.Main;
import br.com.model.beans.ValorLocacao;
import br.com.model.dao.DAOValorLocacao;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerConfigLocacao implements Initializable{

    @FXML
    private ImageView btnClose;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<VLAux> tbValor;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEditar;
    
    @FXML
    private TableColumn<VLAux, Double> valorCol;
    
    @FXML
    private TableColumn<VLAux, String> categoriaCol;

    @FXML
    private TableColumn<VLAux, String> tipoCol;

    @FXML
    void actionAddValor(ActionEvent event)  {
    	Main.novaTela("AddValorLocacao");
    }

    @FXML
    void actionBuscar(ActionEvent event) {

    }

    @FXML
    void actionClose(MouseEvent event) {

    }

    @FXML
    void actionEditar(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
        categoriaCol.setCellValueFactory(
                new PropertyValueFactory<>("categoria"));
        tipoCol.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        valorCol.setCellValueFactory(
                new PropertyValueFactory<>("valor"));
        
		List<ValorLocacao> valores = DAOValorLocacao.getInstance().findAll();
		ObservableList<VLAux> ob = FXCollections.observableArrayList();
		
		for (ValorLocacao valorLocacao : valores) {
			ob.add(new VLAux(valorLocacao.getTipoLocacao(), valorLocacao.getValor(), valorLocacao.getCategoria().getNome()));
		}
		if (ob.size()>0) 
			tbValor.setItems(ob);
		
    	
		
	}
	
	public class VLAux{
		 private String tipo;
		 private Double valor;
		 private String categoria;
		
		public VLAux(String tipo,Double valor,String categoria) {
			this.tipo = tipo;
			this.valor = valor;
			this.categoria = categoria;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public Double getValor() {
			return valor;
		}

		public void setValor(Double valor) {
			this.valor = valor;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		
		
	}

}
