package br.com.model.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "viewloglocacao")
public class LocacaoAdapter{
	@Id
	private Integer codigo;
	private Date dataFinal, dataInicial, data;
	private Double valorFinal;
	private String veiculo, estadoVeiculo,  status;

	public LocacaoAdapter() {
		super();
	}
	
	public LocacaoAdapter(Integer codigo, Date dataFinal, Date dataInicial, Date data, Double valor,
			String veiculo, String statusVeiculo, String status) {
		super();
		this.codigo = codigo;
		this.dataFinal = dataFinal;
		this.dataInicial = dataInicial;
		this.data = data;
		this.valorFinal = valor;
		this.veiculo = veiculo;
		this.estadoVeiculo = statusVeiculo;
		this.status = status;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Date getDataLocacao() {
		return dataFinal;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataFinal = dataLocacao;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValor() {
		return valorFinal;
	}
	public void setValor(Double valor) {
		this.valorFinal = valor;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public String getEstadoVeiculo() {
		return estadoVeiculo;
	}
	public void setEstadoVeiculo(String estadoVeiculo) {
		this.estadoVeiculo = estadoVeiculo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
