package br.com.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Financeiro;
import br.com.model.beans.Locacao;
import br.com.model.beans.Reserva;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOFinanceiro;
import br.com.model.dao.DAOLocacao;
import br.com.model.dao.DAOReserva;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerReserva implements Initializable{
	public static TableView<ReservaAux> tb;
	public static ObservableList<ReservaAux> ob;

	@FXML
	private TableView<ReservaAux> tbReserva;

	@FXML
	private TableColumn<ReservaAux, Integer> codigoCol;

	@FXML
	private TableColumn<ReservaAux, String> clienteCol;

	@FXML
	private TableColumn<ReservaAux, String> categoriaCol;

	@FXML
	private TableColumn<ReservaAux, String> valorCol;

	@FXML
	private TableColumn<ReservaAux, String> dataCol;

	@FXML
	private TableColumn<ReservaAux, String> statusCol;

	@FXML
	private Button btnBuscarReserva;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEfetivar;

	@FXML
	private Button btnAtualizar;

	@FXML
	private TextField fdBuscar;

	@FXML
	private Button btnEditar;

	@FXML
	void actionAddReserva(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovaReserva.fxml"));
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
	void actionBuscarReserva(ActionEvent event) {

	}

	@FXML
	void actionEfetivar(ActionEvent event) {
		Reserva reserva = DAOReserva.getInstance().findById(Reserva.class,tb.getSelectionModel().getSelectedItem().codigo);
		Alert alert = new Alert(AlertType.ERROR);
		if(reserva.getStatus().equals("Ok")) {
			alert.setContentText("Esta reserva já está ativa");
			alert.show();
		}else if(calcularHora()){
			reserva.setStatus("Cancelada");
			alert.setContentText("Esta reserva está demasiadamente atrazada");
			alert.show();
			DAOReserva.getInstance().saveOrUpdate(reserva);
			carregarTabela();
		}else {
			Pane tela = null;
			Scene scene;
			Stage stage;
			try {
				tela = FXMLLoader.load(getClass().getResource("../view/AddVeiculoLocacao.fxml"));
				scene = new Scene(tela);
				stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setOnCloseRequest(e -> stage.close());
				stage.setScene(scene);
				stage.show();

			} catch (Exception e) {
				alert.setTitle("Erro de Exibição");
				alert.setContentText("Não foi possível exibir a tela. Por favor entre em contato com a equipe de desenvolvimento.");
				alert.setHeaderText("Tela não encontrada");
				e.printStackTrace();
			}
		}
	}



	private boolean calcularHora() {
		Calendar calendar = new GregorianCalendar();
		Calendar dataReserva = new GregorianCalendar();
		Date data = tbReserva.getSelectionModel().getSelectedItem().data;
		calendar.setTime(new Date());
		dataReserva.setTime(data);
		dataReserva.set(Calendar.HOUR, (dataReserva.get(Calendar.HOUR)+1));

		if(calendar.after(dataReserva))
			return true;
		return false;
	}

	@FXML
	void actionEditar(ActionEvent event) {
		Reserva reserva = DAOReserva.getInstance().findById(Reserva.class, tbReserva.getSelectionModel().getSelectedItem().getCodigo());
		Pane tela = null;
		Scene scene;
		Stage stage;
		ControllerNovaReserva c;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NovaReserva.fxml"));
			tela = loader.load();
			c = loader.getController();
			c.carregarEditar(reserva);
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
	void actionAtualizar(ActionEvent event) {
		carregarTabela();
		tbReserva.refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codigoCol.setCellValueFactory(
				new PropertyValueFactory<>("codigo"));
		categoriaCol.setCellValueFactory(
				new PropertyValueFactory<>("categoria"));
		clienteCol.setCellValueFactory(
				new PropertyValueFactory<>("cliente"));
		valorCol.setCellValueFactory(
				new PropertyValueFactory<>("valor"));
		dataCol.setCellValueFactory(
				new PropertyValueFactory<>("data"));
		statusCol.setCellValueFactory(
				new PropertyValueFactory<>("status"));

		tb=tbReserva;
		carregarTabela();


	}

	public static void carregarTabela() {
		List<Reserva> reservas = DAOReserva.getInstance().findAll();
		ob = FXCollections.observableArrayList();
		for (Reserva reserva : reservas) {
			if(!reserva.getStatus().equals("Locacao")) {
				ob.add(new ReservaAux(reserva.getId(), reserva.getCliente().getNome(), reserva.getValorPrevisto(), reserva.getCategoria().getNome(),
						reserva.getDataInicial(), reserva.getStatus()) );
			}
		}
		if (ob.size()>0) 
			tb.setItems(ob);
	}


	public static class ReservaAux{
		private Integer codigo;
		private String cliente;
		private String categoria;
		private Double valor;
		private Date data;
		private String status;

		public ReservaAux(Integer codigo, String cliente,Double valor,String categoria, Date data, String status) {
			this.codigo = codigo;
			this.cliente = cliente;
			this.valor = valor;
			this.categoria = categoria;
			this.data = data;
			this.status = status;
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

		public Integer getCodigo() {
			return codigo;
		}

		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}



	}
	public static void addVeiculo(Veiculo veiculo) {

		Reserva reserva = DAOReserva.getInstance().findById(Reserva.class,tb.getSelectionModel().getSelectedItem().codigo);

		Locacao locacao = new Locacao(veiculo, reserva, "Aguardando");
		DAOLocacao.getInstance().saveOrUpdate(locacao);
		reserva.setStatus("Ok");
		veiculo.setStatus("Locado");
		DAOVeiculo.getInstance().saveOrUpdate(veiculo);
		DAOReserva.getInstance().saveOrUpdate(reserva);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Efetivar Locação" );
		alert.setHeaderText("Sucesso");
		alert.setContentText("Locação Efetivada!\n Deve ser cobrado o valor de R$"+reserva.getValorPrevisto()/2+" do cliente.");
		alert.show();
		
		Financeiro financeiro = new Financeiro();
		financeiro.setDia(new Date());
		financeiro.setDescricao("Metade da Locação de "+reserva.getCliente().getCodigo()+" - "+reserva.getCliente().getNome());
		financeiro.setTipo("Entrada");
		financeiro.setValor(reserva.getValorPrevisto()/2);
		
		DAOFinanceiro.getInstance().saveOrUpdate(financeiro);

		carregarTabela();

	}

}