package br.com.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.LocacaoAdapter;
import br.com.model.dao.DAOLocacaoBackup;
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

public class ControllerLogLocacao implements Initializable{

	public static TableView<LocacaoAdapter> tb;
	public static ObservableList<LocacaoAdapter> ob;

	@FXML
	private TableView<LocacaoAdapter> tbLog;

	@FXML
	private TableColumn<LocacaoAdapter, Integer> codigoCol;

	@FXML
	private TableColumn<LocacaoAdapter, Date> dataLocacaoCol;

	@FXML
	private TableColumn<LocacaoAdapter, Double> valorCol;

	@FXML
	private TableColumn<LocacaoAdapter, String> veiculoCol;

	@FXML
	private TableColumn<LocacaoAdapter, String> estadoVeiculoCol;

	@FXML
	private TableColumn<LocacaoAdapter, Date> dataInicialCol;

	@FXML
	private TableColumn<LocacaoAdapter, String> statusCol;

	@FXML
	private TableColumn<LocacaoAdapter, Date> dataCol;
    @FXML
    private TableColumn<LocacaoAdapter, String> usuarioCol;

    @FXML
    private TableColumn<LocacaoAdapter, String> tipoCol;

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
		dataLocacaoCol.setCellValueFactory(
				new PropertyValueFactory<>("dataFinal"));
		valorCol.setCellValueFactory(
				new PropertyValueFactory<>("valorFinal"));
		veiculoCol.setCellValueFactory(
				new PropertyValueFactory<>("veiculo"));
		estadoVeiculoCol.setCellValueFactory(
				new PropertyValueFactory<>("estadoVeiculo"));
		dataInicialCol.setCellValueFactory(
				new PropertyValueFactory<>("dataInicial"));
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
		List<LocacaoAdapter> locacoes = DAOLocacaoBackup.getInstance().findView();
		ob = FXCollections.observableArrayList();

		ob.setAll(locacoes);
		if (ob.size()>0) 
			tb.setItems(ob);
	}

}
