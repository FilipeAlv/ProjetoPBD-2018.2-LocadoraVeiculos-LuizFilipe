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

import br.com.model.beans.Categoria;
import br.com.model.beans.Filial;
import br.com.model.beans.Locacao;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.PessoaJuridica;
import br.com.model.beans.Reserva;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOCategoria;
import br.com.model.dao.DAOFilial;
import br.com.model.dao.DAOLocacao;
import br.com.model.dao.DAOMotorista;
import br.com.model.dao.DAOPessoa;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
import br.com.model.dao.DAOReserva;
import br.com.model.dao.DAOValorLocacao;
import br.com.model.dao.DAOVeiculo;
import br.com.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerLocacaoSemReserva implements Initializable{

	Alert alert = new Alert(AlertType.INFORMATION);

	@FXML
	private DatePicker fdDataEntrega;

	@FXML
	private TextField fdHoraEntrega;

	@FXML
	private ComboBox<String> cbFilial;

	@FXML
	private ComboBox<String> cbFilialEntrega;

	@FXML
	private ComboBox<String> cbVeiculo;

	@FXML
	private ComboBox<String> cbMotorista;

	@FXML
	private ComboBox<String> cbCliente;

	@FXML
	private TextField fdValor;

	@FXML
	private ComboBox<String> cbTipo;

	@FXML
	private ComboBox<String> cbCategoria;

	@FXML
	private Button btnBuscarCliente;

	@FXML
	private Button btnAddMotorista;

	@FXML
	private Button btnAddValor;

	@FXML
	private Button btnSalvar;

	private boolean edit;
	private Locacao l= new Locacao();

	@FXML
	void actionAddMotorista(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovoMotorista.fxml"));
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
	void actionAddValor(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/AddValorLocacao.fxml"));
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
	void actionCarregarVeiculo(ActionEvent event) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		Filial filial = DAOFilial.getInstance().findByNome(cbFilial.getValue());
		List<Veiculo> veiculos = DAOVeiculo.getInstance().findByFilialStatus(filial, "Disponivel");

		for (Veiculo veiculo : veiculos) {
			ob.add(veiculo.getModelo().getNome()+"-"+veiculo.getPlaca());
		}
		cbVeiculo.setItems(ob);
	}

	@FXML
	void actionBuscarCliente(ActionEvent event) {
		String str = cbCliente.getValue();
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<PessoaFisica> clientesF = DAOPessoaFisica.getInstace().findByFind(str);
		List<PessoaJuridica> clientesJ = DAOPessoaJuridica.getInstace().findByFind(str);

		for (PessoaFisica pf : clientesF) {
			ob.add(pf.getNome() + "-" + pf.getCpf());
		}
		for (PessoaJuridica pj : clientesJ) {
			ob.add(pj.getNome() + "-" + pj.getCnpj());
		}

		cbCliente.setItems(ob);
	}



	@FXML
	void actionSalvar(ActionEvent event) {
		if (camposPreenchidos()) {
			if (validarDatas()) {
				String as = pegarCPFCombo(cbCliente.getValue());
				Pessoa cliente = DAOPessoa.getInstace().findByCPF(as);
				Pessoa motorista = DAOPessoa.getInstace().findByCPF(pegarCPFCombo(cbMotorista.getValue()));
				Filial filialSaida = DAOFilial.getInstance().findByNome(cbFilial.getValue());
				Filial filialEntrega = DAOFilial.getInstance().findByNome(cbFilialEntrega.getValue());
				Categoria categoria = DAOCategoria.getInstance().findByNome(cbCategoria.getValue());
				Date dataReserva = new Date();
				Date dataInicio = new Date();
				Date dataEntrega = converterData(fdDataEntrega.getValue(), fdHoraEntrega.getText());
				String tipo = cbTipo.getValue();
				Double valor = Double.parseDouble(fdValor.getText());
				String status = "Locacao";
				
				Reserva r = new Reserva();
				
				r.setCliente(cliente);
				r.setMotorista(motorista);
				r.setFilial(filialSaida);
				r.setFilialEntrega(filialEntrega);
				r.setCategoria(categoria);
				r.setDataInicial(dataInicio);
				r.setDataFinalPrevista(dataEntrega);
				r.setTipoLocacao(tipo);
				r.setValorPrevisto(valor);
				if(!edit) {
					r.setDataReserva(dataReserva);
					r.setStatus(status);
				}
				
				DAOReserva.getInstance().saveOrUpdate(r);
				
				l.setReserva(r);
				l.setStatus("Aguardando");
				l.setVeiculo(DAOVeiculo.getInstance().findByPlaca(pegarPlacaCombo(cbVeiculo.getValue())));
				

				DAOLocacao.getInstance().saveOrUpdate(l);
				if(edit)
					ControllerLocacao.carregarTabela(DAOLocacao.getInstance().findAll());

				try {
					DAOReserva.getInstance().findById(Reserva.class, r.getId());
					alert.setHeaderText("SUCESSO");
					alert.setContentText("Locacao realizada com sucesso");
					alert.show();
				} catch (Exception e) {
					alert.setContentText("Locacao não cadastrada");
					alert.show();
				}
			}
		} else {
			alert.setContentText("Preencha todos os campos");
			alert.show();
		}
	}

	private boolean validarDatas() {
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		alert.setHeaderText("PROBLEMAS AO CADASTRAR RESERVA");
		if (new Date().after(converterData(fdDataEntrega.getValue(), fdHoraEntrega.getText()))) {
			alert.setContentText("A data de Entrega não pode ser inferior a data da locação");
			alert.show();
			return false;
		} else if (c.getTime().after(converterData(fdDataEntrega.getValue(), fdHoraEntrega.getText()))) {
			alert.setContentText("O tempo minimo de locação é de 24h");
			alert.show();
			return false;
		} else {
			return true;
		}
	}


	private boolean camposPreenchidos() {
		if (fdDataEntrega.getValue() == null || fdValor.getText() == null || cbCliente.getValue() == null
				|| cbFilial.getValue() == null || cbFilialEntrega.getValue() == null || cbMotorista.getValue() == null
				|| cbTipo.getValue() == null) {

			return false;

		}
		return true;
	}


	@FXML
	void calcValor(ActionEvent event) {
		if (cbCategoria.getValue() != null && cbTipo.getValue() != null) {
			Categoria categoria = DAOCategoria.getInstance().findByNome(cbCategoria.getValue());
			Calendar dataInicio = Calendar.getInstance();
			Calendar dataFim = Calendar.getInstance();
			int dias;

			try {
				dataInicio.setTime(new Date());
				dataFim.setTime(Date.from(fdDataEntrega.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				dias = dataFim.get(Calendar.DAY_OF_YEAR)-dataInicio.get(Calendar.DAY_OF_YEAR);
				Double valor = DAOValorLocacao.getInstance().findByTipoCategoria(cbTipo.getValue(), categoria);
				valor*=dias;
				fdValor.setText(valor.toString());
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CADASTRO RESERVA");
				alert.setContentText("Este Tipo de Locação não foi cadastrado.");
				alert.setHeaderText("Erro ao adicionar valor");
				alert.show();
				fdValor.setText(null);
			}

		}
	}

	@FXML
	void selecionarCategoria(ActionEvent event) {
		Veiculo veiculo = DAOVeiculo.getInstance().findByPlaca(pegarPlacaCombo(cbVeiculo.getValue()));
		cbCategoria.getSelectionModel().select(veiculo.getCategoria().getNome());
	}

	private String pegarPlacaCombo(String value) {
		int i = value.indexOf("-");
		String sub = value.substring(i+1, value.length());
		System.out.println(sub);
		return sub;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<Motorista> motoristas = DAOMotorista.getInstace().findAll();
		List<Filial> filiais = DAOFilial.getInstance().findAll();
		List<Categoria> categorias = DAOCategoria.getInstance().findAll();

		cbCategoria.setEditable(false);

		for (Filial filial : filiais) {
			ob.add(filial.getNome());
		}
		cbFilial.setItems(ob);
		cbFilialEntrega.setItems(ob);
		ob = FXCollections.observableArrayList();

		for (Motorista motorista : motoristas) {
			ob.add(motorista.getNome() + "-" + motorista.getCpf());
		}
		cbMotorista.setItems(ob);
		ob = FXCollections.observableArrayList();

		for (Categoria categoria : categorias) {
			ob.add(categoria.getNome());
		}
		cbCategoria.setItems(ob);
		ob = FXCollections.observableArrayList();

		ob.add("KMLivre");
		ob.add("KMControle");
		cbTipo.setItems(ob);

		ob = FXCollections.observableArrayList();

		fdValor.setEditable(false);
		Util.Mascarar.Hora(fdHoraEntrega);

	}

	public Date converterData(LocalDate date, String hora) {
		try {
			Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

			SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat fh = new SimpleDateFormat("dd/MM/yyyy");

			String data = fh.format(date1);
			date1 = fd.parse(data + " " + hora);
			return date1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String pegarCPFCombo(String value) {
		int i = value.indexOf("-");
		String sub = value.substring(i + 1, value.length());
		System.out.println(sub);
		return sub;
	}



}
