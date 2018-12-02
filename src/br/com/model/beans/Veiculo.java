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
	@Column(length=25)
	private String fabricante;
	private float torqueMotor;
	@Column(length=15)
	private String modelo;
	@Column(length=15)
	private String combustivel;
	private int tempoLimpeza;
	private int numPassageiro;
	private int numPorta;
	private int anoFabricacao;
	private int anoModelo;
	@Column(length=15)
	private String cor;
	@ManyToOne
	private Categoria categoria;
	public Veiculo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Veiculo(Integer id, String placa, String chassi, double kmAtual, int numMotor, String fabricante,
			float torqueMotor, String modelo, String combustivel, int tempoLimpeza, int numPassageiro, int numPorta,
			int anoFabricacao, int anoModelo, String cor, Categoria categoria) {
		super();
		this.id = id;
		this.placa = placa;
		this.chassi = chassi;
		this.kmAtual = kmAtual;
		this.numMotor = numMotor;
		this.fabricante = fabricante;
		this.torqueMotor = torqueMotor;
		this.modelo = modelo;
		this.combustivel = combustivel;
		this.tempoLimpeza = tempoLimpeza;
		this.numPassageiro = numPassageiro;
		this.numPorta = numPorta;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.cor = cor;
		this.categoria = categoria;
	}
	
	public Veiculo(String placa, String chassi, double kmAtual, int numMotor, String fabricante,
			float torqueMotor, String modelo, String combustivel, int tempoLimpeza, int numPassageiro, int numPorta,
			int anoFabricacao, int anoModelo, String cor, Categoria categoria) {
		super();
		this.placa = placa;
		this.chassi = chassi;
		this.kmAtual = kmAtual;
		this.numMotor = numMotor;
		this.fabricante = fabricante;
		this.torqueMotor = torqueMotor;
		this.modelo = modelo;
		this.combustivel = combustivel;
		this.tempoLimpeza = tempoLimpeza;
		this.numPassageiro = numPassageiro;
		this.numPorta = numPorta;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.cor = cor;
		this.categoria = categoria;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public float getTorqueMotor() {
		return torqueMotor;
	}
	public void setTorqueMotor(float torqueMotor) {
		this.torqueMotor = torqueMotor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public int getTempoLimpeza() {
		return tempoLimpeza;
	}
	public void setTempoLimpeza(int tempoLimpeza) {
		this.tempoLimpeza = tempoLimpeza;
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
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public int getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoFabricacao;
		result = prime * result + anoModelo;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
		result = prime * result + ((combustivel == null) ? 0 : combustivel.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(kmAtual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + numMotor;
		result = prime * result + numPassageiro;
		result = prime * result + numPorta;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (anoFabricacao != other.anoFabricacao)
			return false;
		if (anoModelo != other.anoModelo)
			return false;
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
		if (combustivel == null) {
			if (other.combustivel != null)
				return false;
		} else if (!combustivel.equals(other.combustivel))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
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
		if (numPassageiro != other.numPassageiro)
			return false;
		if (numPorta != other.numPorta)
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (tempoLimpeza != other.tempoLimpeza)
			return false;
		if (Float.floatToIntBits(torqueMotor) != Float.floatToIntBits(other.torqueMotor))
			return false;
		return true;
	}
	
	
	
}
