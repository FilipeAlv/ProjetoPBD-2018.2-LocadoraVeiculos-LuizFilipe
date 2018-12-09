package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="sequencia_valorlocacao", sequenceName="seq_valor_loc", initialValue=1, allocationSize=1)
public class ValorLocacao implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_valorlocacao")
	private int id;
	private double valor;
	@ManyToOne
	private Categoria categoria;
	@Column(length=10)
	private String tipoLocacao;
	
	@Override
	public Integer getId() {
		return null;
	}
	
	

	public ValorLocacao() {
		super();
		
	}



	public ValorLocacao(double valor, Categoria categoria, String tipoLocacao) {
		super();
		this.valor = valor;
		this.categoria = categoria;
		this.tipoLocacao = tipoLocacao;
	}



	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getTipoLocacao() {
		return tipoLocacao;
	}

	public void setTipoLocacao(String tipoLocacao) {
		this.tipoLocacao = tipoLocacao;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + id;
		result = prime * result + ((tipoLocacao == null) ? 0 : tipoLocacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ValorLocacao other = (ValorLocacao) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (id != other.id)
			return false;
		if (tipoLocacao == null) {
			if (other.tipoLocacao != null)
				return false;
		} else if (!tipoLocacao.equals(other.tipoLocacao))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	
	
}
