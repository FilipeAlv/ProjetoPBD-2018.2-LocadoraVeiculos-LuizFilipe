package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="sequencia_modelo", sequenceName="seq_mod", initialValue=1, allocationSize=1)
public class Modelo implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_modelo")
	int id;
	private int anoFabricacao;
	@Column(length=15, unique=true)
	private String nome;
	private int anoModelo;
	private float torqueMotor;
	private int numPassageiro;
	private int numPorta;
	private int tempoLimpeza;
	@Column(length=15)
	private String combustivel;
	@ManyToOne
	private Marca marca;
	
	
	
	
	public Modelo() {
		super();
	}



	public Modelo(int anoFabricacao, String nome, int anoModelo, float torqueMotor, int numPassageiro, int numPorta,
			int tempoLimpeza, String combustivel, Marca marca) {
		super();
		this.anoFabricacao = anoFabricacao;
		this.nome = nome;
		this.anoModelo = anoModelo;
		this.torqueMotor = torqueMotor;
		this.numPassageiro = numPassageiro;
		this.numPorta = numPorta;
		this.tempoLimpeza = tempoLimpeza;
		this.combustivel = combustivel;
		this.marca = marca;
	}



	public Modelo(int id, int anoFabricacao, String nome, int anoModelo, float torqueMotor, int numPassageiro,
			int numPorta, int tempoLimpeza, String combustivel, Marca marca) {
		super();
		this.id = id;
		this.anoFabricacao = anoFabricacao;
		this.nome = nome;
		this.anoModelo = anoModelo;
		this.torqueMotor = torqueMotor;
		this.numPassageiro = numPassageiro;
		this.numPorta = numPorta;
		this.tempoLimpeza = tempoLimpeza;
		this.combustivel = combustivel;
		this.marca = marca;
	}



	@Override
	public Integer getId() {
		return id;
	}



	public int getAnoFabricacao() {
		return anoFabricacao;
	}



	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public int getAnoModelo() {
		return anoModelo;
	}



	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}



	public float getTorqueMotor() {
		return torqueMotor;
	}



	public void setTorqueMotor(float torqueMotor) {
		this.torqueMotor = torqueMotor;
	}



	public int getNumPassageiro() {
		return numPassageiro;
	}



	public void setNumPassageiro(int numPassageiro) {
		this.numPassageiro = numPassageiro;
	}



	public int getNumPorta() {
		return numPorta;
	}



	public void setNumPorta(int numPorta) {
		this.numPorta = numPorta;
	}



	public int getTempoLimpeza() {
		return tempoLimpeza;
	}



	public void setTempoLimpeza(int tempoLimpeza) {
		this.tempoLimpeza = tempoLimpeza;
	}



	public String getCombustivel() {
		return combustivel;
	}



	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}



	public Marca getMarca() {
		return marca;
	}



	public void setMarca(Marca marca) {
		this.marca = marca;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoFabricacao;
		result = prime * result + anoModelo;
		result = prime * result + ((combustivel == null) ? 0 : combustivel.hashCode());
		result = prime * result + id;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + numPassageiro;
		result = prime * result + numPorta;
		result = prime * result + tempoLimpeza;
		result = prime * result + Float.floatToIntBits(torqueMotor);
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
		Modelo other = (Modelo) obj;
		if (anoFabricacao != other.anoFabricacao)
			return false;
		if (anoModelo != other.anoModelo)
			return false;
		if (combustivel == null) {
			if (other.combustivel != null)
				return false;
		} else if (!combustivel.equals(other.combustivel))
			return false;
		if (id != other.id)
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numPassageiro != other.numPassageiro)
			return false;
		if (numPorta != other.numPorta)
			return false;
		if (tempoLimpeza != other.tempoLimpeza)
			return false;
		if (Float.floatToIntBits(torqueMotor) != Float.floatToIntBits(other.torqueMotor))
			return false;
		return true;
	}
	
	
}
