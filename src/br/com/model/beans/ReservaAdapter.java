package br.com.model.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "viewlogreserva")
public class ReservaAdapter{
	 @Id
	 Integer codigo;
	 String cliente, filial, filialEnt, motorista, status;
	 Date dataFinal, data, dataInicial;
	 
	
	public ReservaAdapter() {
		super();
	}
	
	public ReservaAdapter(Integer codigo, String cliente, String filial, String filialEnt, String motorista,
			Date dataInicial, String status, Date dataFinal, Date data) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.filial = filial;
		this.filialEnt = filialEnt;
		this.motorista = motorista;
		this.dataInicial = dataInicial;
		this.status = status;
		this.dataFinal = dataFinal;
		this.data = data;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getFilial() {
		return filial;
	}
	public void setFilial(String filial) {
		this.filial = filial;
	}
	public String getFilialEnt() {
		return filialEnt;
	}
	public void setFilialEnt(String filialEnt) {
		this.filialEnt = filialEnt;
	}
	public String getMotorista() {
		return motorista;
	}
	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	 
	 
}