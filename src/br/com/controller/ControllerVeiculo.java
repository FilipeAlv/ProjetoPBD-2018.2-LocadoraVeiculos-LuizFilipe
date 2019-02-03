package br.com.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.beans.Categoria;
import br.com.model.beans.Filial;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOCategoria;
import br.com.model.dao.DAOFilial;
import br.com.model.dao.DAOVeiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerVeiculo implements Initializable{

	public static TableView<VeiculoAdapter> tb;
	public static ObservableList<VeiculoAdapter> ob;

	@FXML
	private Button btnRetornar;
	
	@FXML
	private Button btnDeletar;

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
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovoVeiculo.fxml"));
			scene = new Scene(tela);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(e -> stage.close());
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro de Exibição");
			alert.setContentText("Não foi possível exibir a tela. Por favor entre em contato com a equipe de desenvolvimento.");
			alert.setHeaderText("Tela não encontrada");
			e.printStackTrace();
		}
	}

	@FXML
    void actionDeletar(ActionEvent event) {
    	Veiculo veiculo = DAOVeiculo.getInstance().findByPlaca(tbVeiculo.getSelectionModel().getSelectedItem().getPlaca());
    	veiculo.setStatusOb(false);
    	DAOVeiculo.getInstance().saveOrUpdate(veiculo);
    	carregarTabela(DAOVeiculo.getInstance().findAll());
    }
	
	@FXML
	void actionAtualizar(ActionEvent event) {
		carregarTabela(DAOVeiculo.getInstance().findAll());
	}

	@FXML
	void actionBuscar(ActionEvent event) {
		boolean vazio = false;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ver Veículos");
		alert.setHeaderText("Busca de veículos");
		List<Veiculo> veiculos = new ArrayList<>();
		if(fdData.getValue()==null) {
			if(fdBuscar.getText().length()!=0 && cbFilial.getSelectionModel().getSelectedIndex()<1
					&& cbStatus.getSelectionModel().getSelectedIndex()<1) {

				veiculos = DAOVeiculo.getInstance().findByFind(fdBuscar.getText().toString());
				System.out.println("1");

			}else if(fdBuscar.getText().length()!=0 && cbFilial.getSelectionModel().getSelectedIndex()>0 
					&& cbStatus.getSelectionModel().getSelectedIndex()<1) {

				veiculos = DAOVeiculo.getInstance().findByFindFilial(fdBuscar.getText().toString(), 
						DAOFilial.getInstance().findByNome(cbFilial.getValue()));

				System.out.println("2");

			}else if(fdBuscar.getText().length()!=0 && cbFilial.getSelectionModel().getSelectedIndex()<1 
					&& (cbStatus.getSelectionModel().getSelectedIndex()>0)) {

				veiculos = DAOVeiculo.getInstance().findByFindStatus(fdBuscar.getText().toString(), cbStatus.getValue());

				System.out.println("3");
			}else if(fdBuscar.getText().length()==0 && cbFilial.getSelectionModel().getSelectedIndex()<1 
					&& cbStatus.getSelectionModel().getSelectedIndex()>0) {

				veiculos = DAOVeiculo.getInstance().findByStatus(cbStatus.getValue());
				System.out.println("4");

			}else if(fdBuscar.getText().length()==0 && cbFilial.getSelectionModel().getSelectedIndex()>0 
					&& cbStatus.getSelectionModel().getSelectedIndex()<1) {

				veiculos = DAOVeiculo.getInstance().findByFilial(DAOFilial.getInstance().findByNome(cbFilial.getValue()));
				System.out.println("4");
			}
		}else {
			if (cbStatus.getSelectionModel().getSelectedIndex()>0 || fdBuscar.getText().length()>0) {
				alert.setContentText("Quando houver uma data, os campos buscar e staus não podem estar preenchidos");
				alert.show();
				vazio = true;
			}else {
				if(cbFilial.getSelectionModel().getSelectedIndex()<1) {
					veiculos = DAOVeiculo.getInstance().findByData(Date.from(fdData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}else {
					veiculos = DAOVeiculo.getInstance().findByDataFilial(Date.from(fdData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
							DAOFilial.getInstance().findByNome(cbFilial.getValue()));
				}
			}
		}

		if(veiculos.size()==0 && !vazio) {
			alert.setContentText("Nenhum veículo para esta pesquisa");
			alert.show();
		}else {
			vazio = false;
			carregarTabela(veiculos);
		}
	}

	@FXML
	void actionEditar(ActionEvent event) {
		Veiculo veiculo = DAOVeiculo.getInstance().findByPlaca(tb.getSelectionModel().getSelectedItem().getPlaca());
		Pane tela = null;
		Scene scene;
		Stage stage;
		ControllerNovoVeiculo c;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NovoVeiculo.fxml"));
			tela = loader.load();
			c = loader.getController();
			c.carregarEditar(veiculo);
			scene = new Scene(tela);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(e -> stage.close());
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro de Exibição");
			alert.setContentText("Não foi possível exibir a tela. Por favor entre em contato com a equipe de desenvolvimento.");
			alert.setHeaderText("Tela não encontrada");
			e.printStackTrace();
		}
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

		btnRetornar.setVisible(false);

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


	@FXML
	void verificarLimpeza(MouseEvent event) {
		String status = tbVeiculo.getSelectionModel().getSelectedItem().getStatus();
		if(status.equals("Limpeza")||status.equals("Revisao")) {
			btnRetornar.setText("Retornar veiculo da "+status);
			btnRetornar.setVisible(true);
		}else {
			btnRetornar.setVisible(false);	
		}
	}
	
    @FXML
    void actionRetornar(ActionEvent event) {
    	Veiculo veiculo;
    	veiculo = DAOVeiculo.getInstance().findByPlaca(tbVeiculo.getSelectionModel().getSelectedItem().getPlaca());
    	veiculo.setStatus("Disponivel");
    	DAOVeiculo.getInstance().saveOrUpdate(veiculo);
    	carregarTabela(DAOVeiculo.getInstance().findAll());
    	btnRetornar.setVisible(false);
    }

	public static void carregarTabela(List<Veiculo> veiculos) {
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
