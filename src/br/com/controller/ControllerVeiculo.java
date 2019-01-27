package br.com.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.main.Main;
import br.com.model.beans.Filial;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOFilial;
import br.com.model.dao.DAOVeiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerVeiculo implements Initializable{
	
	public static TableView<VeiculoAdapter> tb;
	public static ObservableList<VeiculoAdapter> ob;

    @FXML
    private TableView<VeiculoAdapter> tbVeiculo;

    @FXML
    private TableColumn<VeiculoAdapter, String> modeloCol;

    @FXML
    private TableColumn<VeiculoAdapter, String> placaCol;

    @FXML
    private TableColumn<VeiculoAdapter, String> categoriaCol;

    @FXML
    private TableColumn<VeiculoAdapter, String> statusCol;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private ComboBox<String> cbFilial;
    @FXML
    private ComboBox<String> cbStatus;

    @FXML
    private DatePicker fdData;

    @FXML
    void actionAddVeiculo(ActionEvent event) {
    	Main.novaTela("NovoVeiculo");
    }

    @FXML
    void actionAtualizar(ActionEvent event) {

    }

    @FXML
    void actionBuscar(ActionEvent event) {
    	List<Veiculo> veiculos;
    	if(cbFilial.getSelectionModel().getSelectedIndex()==-1||cbFilial.getValue().equals(" ")) {
    		if(fdBuscar.getText().length()==0) {
    			Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setTitle("Erro ao filtrar busca");
    			alert.setContentText("Preencha o campo de busca");
    		}else {
    			veiculos = DAOVeiculo.getInstance().findByFind(fdBuscar.getText().toString());
    			carregarTabela(veiculos);
    		}
    	}else if(cbFilial.getSelectionModel().getSelectedIndex()>0) {
    		Filial filialId = DAOFilial.getInstance().findByNome(cbFilial.getValue());
    		Date data = converterData(fdData.getValue());
    		System.out.println(data);
    		String status = cbStatus.getValue();
    		
    		veiculos = DAOVeiculo.getInstance().findByAllReserva(filialId, data, status);
			carregarTabela(veiculos);
    	
    	}else {
    		int id = DAOFilial.getInstance().findIdByNome(cbFilial.getValue());
    		veiculos = DAOVeiculo.getInstance().findByFilial(id);
			carregarTabela(veiculos);
    	}
    }

    @FXML
    void actionEditar(ActionEvent event) {

    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoriaCol.setCellValueFactory(
                new PropertyValueFactory<>("categoria"));
        modeloCol.setCellValueFactory(
                new PropertyValueFactory<>("modelo"));
        placaCol.setCellValueFactory(
                new PropertyValueFactory<>("placa"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status"));

		tb=tbVeiculo;
		List<Veiculo> veiculos = DAOVeiculo.getInstance().findAll();
		carregarTabela(veiculos);
		
		ObservableList<String> obCombo;
		obCombo = FXCollections.observableArrayList();
		List<Filial> filiais = DAOFilial.getInstance().findAll();
		obCombo.add(" ");
		for (Filial filial : filiais) {
			obCombo.add(filial.getNome());
		}
		cbFilial.setItems(obCombo);
		
		obCombo = FXCollections.observableArrayList();
		obCombo.addAll(" ","Disponivel", "Locado", "Revisão", "Limpeza");
		cbStatus.setItems(obCombo);
		
		fdData.setValue(LocalDate.now());
		
	}
	
	public Date converterData(LocalDate date) {
		try {
			Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

			SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat fh = new SimpleDateFormat("dd/MM/yyyy");

			String data = fh.format(date1);
			date1 = fd.parse(data+" 00:00");
			return date1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void carregarTabela(List<Veiculo> veiculos) {
		ob = FXCollections.observableArrayList();
		for (Veiculo veiculo : veiculos) {
			ob.add(new VeiculoAdapter(veiculo.getModelo().getNome(), veiculo.getCategoria().getNome(), 
					veiculo.getPlaca(), veiculo.getStatus()) );
		}
		if (ob.size()>0) 
			tb.setItems(ob);
	}
	
    
    public static class VeiculoAdapter{
    	private String modelo, categoria, placa, status;

		public VeiculoAdapter(String modelo, String categoria, String placa, String status) {
			super();
			this.modelo = modelo;
			this.categoria = categoria;
			this.placa = placa;
			this.status = status;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public String getPlaca() {
			return placa;
		}

		public void setPlaca(String placa) {
			this.placa = placa;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
    	
    	
    	
    }




}
