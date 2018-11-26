package br.com.model.beans;

import javax.persistence.Entity;

@Entity
public class CaminhonetaPassageiro extends Categoria{
	private boolean airBag;
	private boolean direcaoAssistida;
	private boolean ligaLeve;
	private boolean cintosTrasRetrateis;
	private boolean contrPol;
	
	
	public CaminhonetaPassageiro() {
		super();
	}


	public CaminhonetaPassageiro(int id, String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3) {
		super(id, nome, tamanho, tipoCambio, valorDiaria, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
	}


	public CaminhonetaPassageiro(int id, String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3,
			boolean airBag, boolean direcaoAssistida, boolean ligaLeve,
			boolean cintosTrasRetrateis, boolean contrPol) {
		super(id, nome, tamanho, tipoCambio, valorDiaria, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
		this.airBag = airBag;
		this.direcaoAssistida = direcaoAssistida;
		this.ligaLeve = ligaLeve;
		this.cintosTrasRetrateis = cintosTrasRetrateis;
		this.contrPol = contrPol;
	}


	public boolean isAirBag() {
		return airBag;
	}


	public void setAirBag(boolean airBag) {
		this.airBag = airBag;
	}


	public boolean isDirecaoAssistida() {
		return direcaoAssistida;
	}


	public void setDirecaoAssistida(boolean direcaoAssistida) {
		this.direcaoAssistida = direcaoAssistida;
	}


	public boolean isLigaLeve() {
		return ligaLeve;
	}


	public void setLigaLeve(boolean ligaLeve) {
		this.ligaLeve = ligaLeve;
	}


	public boolean isCintosTrasRetrateis() {
		return cintosTrasRetrateis;
	}


	public void setCintosTrasRetrateis(boolean cintosTrasRetrateis) {
		this.cintosTrasRetrateis = cintosTrasRetrateis;
	}


	public boolean isContrPol() {
		return contrPol;
	}


	public void setContrPol(boolean contrPol) {
		this.contrPol = contrPol;
	}
	
	
	
	

}
