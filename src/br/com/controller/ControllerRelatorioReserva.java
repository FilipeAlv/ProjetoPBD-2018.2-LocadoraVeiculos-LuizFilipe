package br.com.controller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.model.beans.Reserva;
import br.com.model.dao.DAOReserva;
import br.com.model.relatorio.Relatorio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class ControllerRelatorioReserva {

    @FXML
    private Button btnFiltrar;

    @FXML
    private DatePicker fdDe;

    @FXML
    private DatePicker fdAte;

    @FXML
    void actionFiltrar(ActionEvent event) {
    	Date de, ate;
    	de = Date.from(fdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	ate = Date.from(fdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	
    	List<Reserva> re = DAOReserva.getInstance().findByDataData(de, ate);
    	List<reservaRelatorio> rr = new ArrayList<>();
    	for (Reserva r : re) {
			rr.add(new reservaRelatorio(r.getId(), r.getDataReserva(), r.getDataInicial(), 
					r.getDataFinalPrevista(), r.getTipoLocacao(), r.getCliente().getNome(), r.getMotorista().getNome(), 
					r.getFilial().getNome(), r.getStatus()));
		}
    	Relatorio r = new Relatorio();
    	r.gerarRelatorio(rr, "/br/com/report/relatorioReserva.jrxml");
    }
    
    public class reservaRelatorio{
    	private Integer id;
    	private Date dataReserva;
    	private Date dataInicial;
    	private Date dataFinalPrevista;
    	private String tipoLocacao;	
    	private String cliente;
    	private String motorista;
    	private String filial;
    	private String status;
    	
    	
		public reservaRelatorio(Integer id, Date dataReserva, Date dataInicial, Date dataFinalPrevista,
				String tipoLocacao, String cliente, String motorista, String filial, String status) {
			super();
			this.id = id;
			this.dataReserva = dataReserva;
			this.dataInicial = dataInicial;
			this.dataFinalPrevista = dataFinalPrevista;
			this.tipoLocacao = tipoLocacao;
			this.cliente = cliente;
			this.motorista = motorista;
			this.filial = filial;
			this.status = status;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getDataReserva() {
			return dataReserva;
		}
		public void setDataReserva(Date dataReserva) {
			this.dataReserva = dataReserva;
		}
		public Date getDataInicial() {
			return dataInicial;
		}
		public void setDataInicial(Date dataInicial) {
			this.dataInicial = dataInicial;
		}
		public Date getDataFinalPrevista() {
			return dataFinalPrevista;
		}
		public void setDataFinalPrevista(Date dataFinalPrevista) {
			this.dataFinalPrevista = dataFinalPrevista;
		}
		public String getTipoLocacao() {
			return tipoLocacao;
		}
		public void setTipoLocacao(String tipoLocacao) {
			this.tipoLocacao = tipoLocacao;
		}
		public String getCliente() {
			return cliente;
		}
		public void setCliente(String cliente) {
			this.cliente = cliente;
		}
		public String getMotorista() {
			return motorista;
		}
		public void setMotorista(String motorista) {
			this.motorista = motorista;
		}
		public String getFilial() {
			return filial;
		}
		public void setFilial(String filial) {
			this.filial = filial;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
    	
    	
    }

}