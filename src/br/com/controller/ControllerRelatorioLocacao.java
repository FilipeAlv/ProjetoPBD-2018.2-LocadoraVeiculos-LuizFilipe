package br.com.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Locacao;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.PessoaJuridica;
import br.com.model.dao.DAOLocacao;
import br.com.model.dao.DAOMotorista;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
import br.com.model.relatorio.Relatorio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class ControllerRelatorioLocacao implements Initializable{

	@FXML
	private Button btnFiltrar;

	@FXML
	private DatePicker fdDe;

	@FXML
	private DatePicker fdAte;

	@FXML
	private ComboBox<String> cbCliente;

	@FXML
	private ComboBox<String> cbMotorista;

	@FXML
	void actionFiltrar(ActionEvent event) {
		List<Locacao> locacoes = null;
		if(fdDe.getValue()==null && fdAte.getValue()==null 
				&& cbCliente.getSelectionModel().getSelectedIndex()<1 
				&& cbMotorista.getSelectionModel().getSelectedIndex()>0 ){

			locacoes = DAOLocacao.getInstance().findByMotorista((Motorista)DAOPessoaFisica.getInstace().findByCpf(
					pegarCPFCombo(cbMotorista.getValue())));

		}else if(fdDe.getValue()==null && fdAte.getValue()==null && cbCliente.getSelectionModel().getSelectedIndex()>0 
				&& cbMotorista.getSelectionModel().getSelectedIndex()<1){
			
			PessoaFisica pessoaFisica = DAOPessoaFisica.getInstace().findByCpf(pegarCPFCombo(cbCliente.getValue()));
			PessoaJuridica pessoaJuridica = DAOPessoaJuridica.getInstace().findByCnpj(pegarCPFCombo(cbCliente.getValue()));
			
			if(pessoaFisica != null) {
				locacoes = DAOLocacao.getInstance().findByCliente((Pessoa)pessoaFisica);
			}else if(pessoaJuridica != null) {
				locacoes = DAOLocacao.getInstance().findByCliente((Pessoa)pessoaJuridica);
			}

		}else if(fdDe.getValue()==null && fdAte.getValue()==null 
				&& cbCliente.getSelectionModel().getSelectedIndex()>0 
				&& cbMotorista.getSelectionModel().getSelectedIndex()>0){
			
			Motorista motorista = (Motorista)DAOPessoaFisica.getInstace().findByCpf(
					pegarCPFCombo(cbMotorista.getValue()));
			PessoaFisica pessoaFisica = DAOPessoaFisica.getInstace().findByCpf(pegarCPFCombo(cbCliente.getValue()));
			PessoaJuridica pessoaJuridica = DAOPessoaJuridica.getInstace().findByCnpj(pegarCPFCombo(cbCliente.getValue()));
			
			if(pessoaFisica != null) {
				locacoes = DAOLocacao.getInstance().findByClienteMotorista((Pessoa)pessoaFisica, motorista);
			}else if(pessoaJuridica != null) {
				locacoes = DAOLocacao.getInstance().findByClienteMotorista((Pessoa)pessoaJuridica, motorista);
			}

		}else if(fdDe.getValue()!=null && fdAte.getValue()!=null 
				&& cbCliente.getSelectionModel().getSelectedIndex()<1 
				&& cbMotorista.getSelectionModel().getSelectedIndex()<1 ){
			Date de, ate;
	    	de = Date.from(fdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	ate = Date.from(fdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			locacoes = DAOLocacao.getInstance().findByDataData(de, ate);

		}else
		if(fdDe.getValue()!=null && fdAte.getValue()!=null 
				&& cbCliente.getSelectionModel().getSelectedIndex()>0 
				&& cbMotorista.getSelectionModel().getSelectedIndex()<1 ){
			Date de, ate;
	    	de = Date.from(fdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	ate = Date.from(fdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	PessoaFisica pessoaFisica = DAOPessoaFisica.getInstace().findByCpf(pegarCPFCombo(cbCliente.getValue()));
			PessoaJuridica pessoaJuridica = DAOPessoaJuridica.getInstace().findByCnpj(pegarCPFCombo(cbCliente.getValue()));
			if(pessoaFisica != null) {
				locacoes = DAOLocacao.getInstance().findByDataCliente(de, ate, (Pessoa)pessoaFisica);
			}else if(pessoaJuridica != null) {
				locacoes = DAOLocacao.getInstance().findByDataCliente(de, ate, (Pessoa)pessoaJuridica);
			}
			

		}else if(fdDe.getValue()!=null && fdAte.getValue()!=null 
				&& cbCliente.getSelectionModel().getSelectedIndex()<1
				&& cbMotorista.getSelectionModel().getSelectedIndex()>0 ){
			Date de, ate;
	    	de = Date.from(fdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	ate = Date.from(fdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			locacoes = DAOLocacao.getInstance().findByDataMotorista(de, ate, (Motorista)DAOPessoaFisica.getInstace().findByCpf(
					pegarCPFCombo(cbMotorista.getValue())));

		}else if(fdDe.getValue()!=null && fdAte.getValue()!=null 
				&& cbCliente.getSelectionModel().getSelectedIndex()>0
				&& cbMotorista.getSelectionModel().getSelectedIndex()>0 ){
			Date de, ate;
	    	de = Date.from(fdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	ate = Date.from(fdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	PessoaFisica pessoaFisica = DAOPessoaFisica.getInstace().findByCpf(pegarCPFCombo(cbCliente.getValue()));
			PessoaJuridica pessoaJuridica = DAOPessoaJuridica.getInstace().findByCnpj(pegarCPFCombo(cbCliente.getValue()));
			Motorista motorista = (Motorista)DAOPessoaFisica.getInstace().findByCpf(
					pegarCPFCombo(cbMotorista.getValue()));
			if(pessoaFisica != null) {
				locacoes = DAOLocacao.getInstance().findByDataClienteMotorista(de, ate, (Pessoa)pessoaFisica, motorista);
			}else if(pessoaJuridica != null) {
				locacoes = DAOLocacao.getInstance().findByDataClienteMotorista(de, ate, (Pessoa)pessoaJuridica, motorista);
			}

		}
		
		List<relatorioLocacao> rl = new ArrayList<>();
		for (Locacao l : locacoes) {
			rl.add(new relatorioLocacao(l.getId(), l.getDataFinal(), l.getValorFinal(), l.getStatus(),
					l.getVeiculo().getModelo().getNome()+" "+l.getVeiculo().getPlaca(),l.getStatus()));
		}
		
		Relatorio r = new Relatorio();
		r.gerarRelatorio(rl, "/br/com/report/relatorioLocacao.jrxml");

	}


	private String pegarCPFCombo(String value) {
		int i = value.indexOf("-");
		String sub = value.substring(i + 1, value.length());
		System.out.println(sub);
		return sub;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ob = FXCollections.observableArrayList();
		List<PessoaFisica> clienteFisico = DAOPessoaFisica.getInstace().findAll();
		List<PessoaJuridica>  clienteJuridico = DAOPessoaJuridica.getInstace().findAll();
		List<Motorista> motoristas = DAOMotorista.getInstace().findAll();
		ob.add(" ");
		for (Motorista motorista : motoristas) {
			ob.add(motorista.getNome() + "-" + motorista.getCpf());
		}
		cbMotorista.setItems(ob);

		ob = FXCollections.observableArrayList();
		ob.add(" ");
		for (Motorista motorista : motoristas) {
			ob.add(motorista.getNome() + "-" + motorista.getCpf());
		}

		for (PessoaFisica pf : clienteFisico) {
			ob.add(pf.getNome()+"-"+pf.getCpf());
		}

		for (PessoaJuridica p : clienteJuridico) {
			ob.add(p.getNome()+"-"+p.getCnpj());
		}
		cbCliente.setItems(ob);
	}
	
	
	public class relatorioLocacao{
		private Integer id;
		private Date dataFinal;
		private double valorFinal;
		private String statusVeiculo;
		private String veiculo;
		private String status;
		
		
		public relatorioLocacao(Integer id, Date dataFinal, double valorFinal, String statusVeiculo, String veiculo,
				String status) {
			super();
			this.id = id;
			this.dataFinal = dataFinal;
			this.valorFinal = valorFinal;
			this.statusVeiculo = statusVeiculo;
			this.veiculo = veiculo;
			this.status = status;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getDataFinal() {
			return dataFinal;
		}
		public void setDataFinal(Date dataFinal) {
			this.dataFinal = dataFinal;
		}
		public double getValorFinal() {
			return valorFinal;
		}
		public void setValorFinal(double valorFinal) {
			this.valorFinal = valorFinal;
		}
		public String getStatusVeiculo() {
			return statusVeiculo;
		}
		public void setStatusVeiculo(String statusVeiculo) {
			this.statusVeiculo = statusVeiculo;
		}
		public String getVeiculo() {
			return veiculo;
		}
		public void setVeiculo(String veiculo) {
			this.veiculo = veiculo;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		
	}

}
