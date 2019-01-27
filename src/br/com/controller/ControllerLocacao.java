package br.com.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import br.com.main.Main;
import br.com.model.beans.Locacao;
import br.com.model.dao.DAOLocacao;
import br.com.model.dao.DAOVeiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerLocacao implements Initializable{

	public static TableView<LocacaoAdapter> tb;
	public static ObservableList<LocacaoAdapter> ob;

	@FXML
	private TableView<LocacaoAdapter> tbLocacao;

	@FXML
	private TableColumn<LocacaoAdapter, String> codigoCol;

	@FXML
	private TableColumn<LocacaoAdapter, String> veiculoCol;

	@FXML
	private TableColumn<LocacaoAdapter, String> categoriaCol;

	@FXML
	private TableColumn<LocacaoAdapter, Date> dataCol;

	@FXML
	private TableColumn<LocacaoAdapter, String> stausCol;

	@FXML
	private Button btnBuscarLocacao;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnFinalizar;


	@FXML
	private TextField fdBuscar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnAtualizar;

	@FXML
	void actionAddLocacao(ActionEvent event) {

	}

	@FXML
	void actionAtualizar(ActionEvent event) {

	}

	@FXML
	void actionFinalizar(ActionEvent event) {
		Locacao locacao = DAOLocacao.getInstance().findById(Locacao.class,tb.getSelectionModel().getSelectedItem().codigo);
		Alert alert = new Alert(AlertType.ERROR);
		if(locacao.getStatus().equals("Ok")) {
			alert.setContentText("Esta locação já está finalizada");
			alert.show();
		}else {
			calcularHora(locacao);
			Main.novaTela("FinalizarLocacao");
		}

	}

	private void calcularHora(Locacao locacao) {
		Calendar calendar = new GregorianCalendar();
		Calendar calendarAux1 = new GregorianCalendar();
		Calendar calendarAux4 = new GregorianCalendar();
		Calendar calendarAux5 = new GregorianCalendar();
		Date data = locacao.getReserva().getDataFinalPrevista();
		calendar.setTime(new Date());
		calendarAux1.setTime(data);
		calendarAux4.setTime(data);
		calendarAux5.setTime(data);

		calendarAux1.set(Calendar.HOUR, calendarAux1.get(Calendar.HOUR)+1);
		calendarAux4.set(Calendar.HOUR, calendarAux4.get(Calendar.HOUR)+3);
		calendarAux5.set(Calendar.HOUR, calendarAux5.get(Calendar.HOUR)+5);
		if(calendar.after(calendarAux5)){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Esta Locação está mais de 4 horas atrazada. Será cobrado Multa de mais uma Diaria");
			alert.show();
			Double valor = locacao.getReserva().getValorPrevisto();
			ControllerFinalizarLocacao.setValor(valor+valor);	
		}else if(calendar.after(calendarAux4)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Esta Locação está 4 horas atrazada. Será cobrado Multa de 1/4 da Diaria");
			alert.show();
			Double multa = locacao.getReserva().getValorPrevisto()*0.25;
			ControllerFinalizarLocacao.setValor(locacao.getReserva().getValorPrevisto()+multa);	
		}else if(calendar.after(calendarAux1)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Esta Locação está mais de 1 hora atrazada.");
			alert.show();
		}
	}

	@FXML
	void actionBuscarLocacao(ActionEvent event) {

	}

	@FXML
	void actionEditar(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoriaCol.setCellValueFactory(
				new PropertyValueFactory<>("categoria"));
		veiculoCol.setCellValueFactory(
				new PropertyValueFactory<>("veiculo"));
		codigoCol.setCellValueFactory(
				new PropertyValueFactory<>("codigo"));

		stausCol.setCellValueFactory(
				new PropertyValueFactory<>("status"));
		dataCol.setCellValueFactory(
				new PropertyValueFactory<>("data"));

		tb=tbLocacao;
		List<Locacao> locacoes = DAOLocacao.getInstance().findAll();
		carregarTabela(locacoes);





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

	private static void carregarTabela(List<Locacao> locacoes) {
		ob = FXCollections.observableArrayList();
		for (Locacao locacao : locacoes) {
			ob.add(new LocacaoAdapter(locacao.getId(), locacao.getVeiculo().getModelo().getNome()+" "+locacao.getVeiculo().getPlaca(),
					locacao.getVeiculo().getCategoria().getNome(), locacao.getReserva().getDataInicial(), locacao.getStatus()));
		}
		if (ob.size()>0) 
			tb.setItems(ob);
	}


	public static class LocacaoAdapter{
		private Integer codigo;
		private String  veiculo, categoria,  status;
		private Date data;

		public LocacaoAdapter(Integer codigo, String veiculo, String categoria, Date data, String status) {
			super();
			this.codigo = codigo;
			this.veiculo = veiculo;
			this.categoria = categoria;
			this.data = data;
			this.status = status;
		}

		public Integer getCodigo() {
			return codigo;
		}

		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}

		public String getVeiculo() {
			return veiculo;
		}

		public void setVeiculo(String veiculo) {
			this.veiculo = veiculo;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}


	}


	public static void finalizar(float valor, String status, String km) {
		Locacao locacao = DAOLocacao.getInstance().findById(Locacao.class,
				tb.getSelectionModel().getSelectedItem().codigo);
		
		locacao.setDataFinal(new Date());
		locacao.setStatusVeiculo(status);
		locacao.setStatus("Ok");
		locacao.getVeiculo().setFilialAtual(locacao.getReserva().getFilialEntrega());
		locacao.getVeiculo().setKmAnterior(locacao.getVeiculo().getKmAtual());
		locacao.getVeiculo().setKmAtual(Double.parseDouble(km));
		
		if(locacao.getVeiculo().getCategoria().getTamanho().equals("Pequeno")||
				locacao.getVeiculo().getCategoria().getTamanho().equals("Grande")&& 
				locacao.getVeiculo().getKmAtual()-locacao.getVeiculo().getKmAnterior()>=5000) {

			locacao.getVeiculo().setStatus("Revisao");
		}else if(locacao.getVeiculo().getCategoria().getTamanho().equals("Grande") && 
				locacao.getVeiculo().getKmAtual()-locacao.getVeiculo().getKmAnterior()>=10000) {
			locacao.getVeiculo().setStatus("Revisao");
		}else if(locacao.getStatusVeiculo().equals("Sujo")){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Será cobrado Multa de 2%");
			alert.show();
			locacao.setValorFinal(locacao.getValorFinal()*1.02);
			locacao.getVeiculo().setStatus("Limpeza");
		}else if(locacao.getStatusVeiculo().equals("Sem Combustivel")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Será cobrado Multa de 3%");
			alert.show();
			locacao.setValorFinal(locacao.getValorFinal()*1.03);
			locacao.getVeiculo().setStatus("Disponivel");
		}else {
			locacao.getVeiculo().setStatus("Disponivel");
		}
		
		
		
		DAOLocacao.getInstance().saveOrUpdate(locacao);
		DAOVeiculo.getInstance().saveOrUpdate(locacao.getVeiculo());
		carregarTabela(DAOLocacao.getInstance().findAll());


	}
}



