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
@SequenceGenerator(name="sequencia_locacao_backup", sequenceName="seq_loc_backup", initialValue=1, allocationSize=1)
public class Locacao2 implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_locacao_backup" )
	private Integer id;
	private Date dataFinal;
	private double valorFinal;
	@Column(length=25)
	private String statusVeiculo;
	@OneToOne
	private Veiculo veiculo;
	@OneToOne
	private Reserva reserva;
	private String status;
	private Date dataModificacao;
	public Locacao2() {
		super();
	}
	
	public Locacao2( Veiculo veiculo, Reserva reserva, String status) {
		super();
		this.veiculo = veiculo;
		this.reserva = reserva;
		this.status = status;
	}
	
	public Locacao2(Date dataFinal, double valorFinal, String statusVeiculo, Veiculo veiculo, Reserva reserva, String status) {
		super();
		this.dataFinal = dataFinal;
		this.valorFinal = valorFinal;
		this.statusVeiculo = statusVeiculo;
		this.veiculo = veiculo;
		this.reserva = reserva;
		this.status = status;
	}

	@Override
	public Integer getId() {
		return id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result + ((dataModificacao == null) ? 0 : dataModificacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reserva == null) ? 0 : reserva.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Locacao2 other = (Locacao2) obj;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (dataModificacao == null) {
			if (other.dataModificacao != null)
				return false;
		} else if (!dataModificacao.equals(other.dataModificacao))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
