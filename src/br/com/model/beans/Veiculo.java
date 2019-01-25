package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="sequencia_veiculo", sequenceName="seq_vei", initialValue=1, allocationSize=1)
public class Veiculo implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_veiculo")
	private Integer id;
	@Column(length=8, unique=true)
	private String placa;
	@Column(unique=true)
	private String chassi;
	private double kmAtual;
	private int numMotor;
	@Column(length=15)
	private String cor;
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Modelo modelo;
	@ManyToOne
	private Filial filialAtual;
	
	public Veiculo() {
		super();
	}
	
	

	public Veiculo(String placa, String chassi, double kmAtual, int numMotor, String cor, Categoria categoria,
			Modelo modelo, Filial filial) {
		super();
		this.placa = placa;
		this.chassi = chassi;
		this.kmAtual = kmAtual;
		this.numMotor = numMotor;
		this.cor = cor;
		this.categoria = categoria;
		this.modelo = modelo;
	}






	@Override
	public Integer getId() {
		return id;
	}



	public String getPlaca() {
		return placa;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
	}



	public String getChassi() {
		return chassi;
	}



	public void setChassi(String chassi) {
		this.chassi = chassi;
	}



	public double getKmAtual() {
		return kmAtual;
	}



	public void setKmAtual(double kmAtual) {
		this.kmAtual = kmAtual;
	}



	public int getNumMotor() {
		return numMotor;
	}



	public void setNumMotor(int numMotor) {
		this.numMotor = numMotor;
	}



	public String getCor() {
		return cor;
	}



	public void setCor(String cor) {
		this.cor = cor;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public Modelo getModelo() {
		return modelo;
	}



	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Filial getFilialAtual() {
		return filialAtual;
	}



	public void setFilialAtual(Filial filialAtual) {
		this.filialAtual = filialAtual;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((filialAtual == null) ? 0 : filialAtual.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(kmAtual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + numMotor;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (chassi == null) {
			if (other.chassi != null)
				return false;
		} else if (!chassi.equals(other.chassi))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (filialAtual == null) {
			if (other.filialAtual != null)
				return false;
		} else if (!filialAtual.equals(other.filialAtual))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(kmAtual) != Double.doubleToLongBits(other.kmAtual))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (numMotor != other.numMotor)
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}



	
	
	
}
