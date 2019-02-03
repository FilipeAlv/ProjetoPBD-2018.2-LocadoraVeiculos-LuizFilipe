package br.com.model.beans;

import javax.persistence.Entity;

@Entity
public class CaminhonetaCarga extends Categoria{
	private int capacidade;
	private String desempenho;
	private int volumeTanque;
	private int distanciaEixo;
	private float potenciaMotor;
	private String tipoEmbreagem;
	public CaminhonetaCarga() {
		super();
		
	}
	public CaminhonetaCarga(int capacidade, String desempenho, int volumeTanque, int distanciaEixo, float potenciaMotor,
			String tipoEmbreagem) {
		super();
		this.capacidade = capacidade;
		this.desempenho = desempenho;
		this.volumeTanque = volumeTanque;
		this.distanciaEixo = distanciaEixo;
		this.potenciaMotor = potenciaMotor;
		this.tipoEmbreagem = tipoEmbreagem;
	}

	
	
	public CaminhonetaCarga(String nome, String tamanho, String tipoCambio, boolean arCondicionado,
			boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3, int capacidade, String desempenho,
			int volumeTanque, int distanciaEixo, float potenciaMotor, String tipoEmbreagem) {
		super(nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
		this.capacidade = capacidade;
		this.desempenho = desempenho;
		this.volumeTanque = volumeTanque;
		this.distanciaEixo = distanciaEixo;
		this.potenciaMotor = potenciaMotor;
		this.tipoEmbreagem = tipoEmbreagem;
	}
	public CaminhonetaCarga(String nome, String tamanho, String tipoCambio, boolean arCondicionado,
			boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3) {
		super(nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public String getDesempenho() {
		return desempenho;
	}
	public void setDesempenho(String desempenho) {
		this.desempenho = desempenho;
	}
	public int getVolumeTanque() {
		return volumeTanque;
	}
	public void setVolumeTanque(int volumeTanque) {
		this.volumeTanque = volumeTanque;
	}
	public int getDistanciaEixo() {
		return distanciaEixo;
	}
	public void setDistanciaEixo(int distanciaEixo) {
		this.distanciaEixo = distanciaEixo;
	}
	public float getPotenciaMotor() {
		return potenciaMotor;
	}
	public void setPotenciaMotor(float potenciaMotor) {
		this.potenciaMotor = potenciaMotor;
	}
	public String getTipoEmbreagem() {
		return tipoEmbreagem;
	}
	public void setTipoEmbreagem(String tipoEmbreagem) {
		this.tipoEmbreagem = tipoEmbreagem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + capacidade;
		result = prime * result + ((desempenho == null) ? 0 : desempenho.hashCode());
		result = prime * result + distanciaEixo;
		result = prime * result + Float.floatToIntBits(potenciaMotor);
		result = prime * result + ((tipoEmbreagem == null) ? 0 : tipoEmbreagem.hashCode());
		result = prime * result + volumeTanque;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaminhonetaCarga other = (CaminhonetaCarga) obj;
		if (capacidade != other.capacidade)
			return false;
		if (desempenho == null) {
			if (other.desempenho != null)
				return false;
		} else if (!desempenho.equals(other.desempenho))
			return false;
		if (distanciaEixo != other.distanciaEixo)
			return false;
		if (Float.floatToIntBits(potenciaMotor) != Float.floatToIntBits(other.potenciaMotor))
			return false;
		if (tipoEmbreagem == null) {
			if (other.tipoEmbreagem != null)
				return false;
		} else if (!tipoEmbreagem.equals(other.tipoEmbreagem))
			return false;
		if (volumeTanque != other.volumeTanque)
			return false;
		return true;
	}
	
	
	
	

}
