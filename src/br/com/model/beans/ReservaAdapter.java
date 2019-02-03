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
	 String cliente, filial, filialEntrada, motorista, status, usuario, tipo;
	 Date dataFinal, data, dataInicial;
	 
	
	public ReservaAdapter() {
		super();
	}


	public ReservaAdapter(Integer codigo, String cliente, String filial, String filialEntrada, String motorista,
			String status, String usuario, String tipo, Date dataFinal, Date data, Date dataInicial) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.filial = filial;
		this.filialEntrada = filialEntrada;
		this.motorista = motorista;
		this.status = status;
		this.usuario = usuario;
		this.tipo = tipo;
		this.dataFinal = dataFinal;
		this.data = data;
		this.dataInicial = dataInicial;
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


	public String getFilialEntrada() {
		return filialEntrada;
	}


	public void setFilialEntrada(String filialEntrada) {
		this.filialEntrada = filialEntrada;
	}


	public String getMotorista() {
		return motorista;
	}


	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
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


	public Date getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	
	 
}