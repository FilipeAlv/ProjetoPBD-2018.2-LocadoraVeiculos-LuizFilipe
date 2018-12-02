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
	public CaminhonetaCarga(int id, String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3) {
		super(id, nome, tamanho, tipoCambio, valorDiaria, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
		
	}
	public CaminhonetaCarga(Integer id, String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3, int capacidade, String desempenho, int volumeTanque, int distanciaEixo, float potenciaMotor,
			String tipoEmbreagem) {
		super(id, nome, tamanho, tipoCambio, valorDiaria, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
		this.capacidade = capacidade;
		this.desempenho = desempenho;
		this.volumeTanque = volumeTanque;
		this.distanciaEixo = distanciaEixo;
		this.potenciaMotor = potenciaMotor;
		this.tipoEmbreagem = tipoEmbreagem;
	}
	
	public CaminhonetaCarga(String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3, int capacidade, String desempenho, int volumeTanque, int distanciaEixo, float potenciaMotor,
			String tipoEmbreagem) {
		super(nome, tamanho, tipoCambio, valorDiaria, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
		this.capacidade = capacidade;
		this.desempenho = desempenho;
		this.volumeTanque = volumeTanque;
		this.distanciaEixo = distanciaEixo;
		this.potenciaMotor = potenciaMotor;
		this.tipoEmbreagem = tipoEmbreagem;
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
	
	
	
	

}
