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
	private String veiculo, estadoVeiculo,  status, usuario, tipo;

	public LocacaoAdapter() {
		super();
	}

	public LocacaoAdapter(Integer codigo, Date dataFinal, Date dataInicial, Date data, Double valorFinal,
			String veiculo, String estadoVeiculo, String status, String usuario, String tipo) {
		super();
		this.codigo = codigo;
		this.dataFinal = dataFinal;
		this.dataInicial = dataInicial;
		this.data = data;
		this.valorFinal = valorFinal;
		this.veiculo = veiculo;
		this.estadoVeiculo = estadoVeiculo;
		this.status = status;
		this.usuario = usuario;
		this.tipo = tipo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
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

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
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


}
