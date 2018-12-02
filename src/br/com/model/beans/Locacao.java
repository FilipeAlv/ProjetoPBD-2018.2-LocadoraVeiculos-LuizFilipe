package br.com.model.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@Entity
@SequenceGenerator(name="sequencia_locacao", sequenceName="seq_loc", initialValue=1, allocationSize=1)
public class Locacao implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_locacao" )
	private Integer id;
	private Date dataFinal;
	private double valorFinal;
	@Column(length=25)
	private String statusVeiculo;
	@OneToOne
	private Veiculo veiculo;
	@OneToOne
	private Reserva reserva;
	public Locacao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Locacao(Integer id, Date dataFinal, double valorFinal, String statusVeiculo, Veiculo veiculo, Reserva reserva) {
		super();
		this.id = id;
		this.dataFinal = dataFinal;
		this.valorFinal = valorFinal;
		this.statusVeiculo = statusVeiculo;
		this.veiculo = veiculo;
		this.reserva = reserva;
	}
	
	public Locacao(Date dataFinal, double valorFinal, String statusVeiculo, Veiculo veiculo, Reserva reserva) {
		super();
		this.dataFinal = dataFinal;
		this.valorFinal = valorFinal;
		this.statusVeiculo = statusVeiculo;
		this.veiculo = veiculo;
		this.reserva = reserva;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
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
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reserva == null) ? 0 : reserva.hashCode());
		result = prime * result + ((statusVeiculo == null) ? 0 : statusVeiculo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorFinal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reserva == null) {
			if (other.reserva != null)
				return false;
		} else if (!reserva.equals(other.reserva))
			return false;
		if (statusVeiculo == null) {
			if (other.statusVeiculo != null)
				return false;
		} else if (!statusVeiculo.equals(other.statusVeiculo))
			return false;
		if (Double.doubleToLongBits(valorFinal) != Double.doubleToLongBits(other.valorFinal))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}
	
	
}
