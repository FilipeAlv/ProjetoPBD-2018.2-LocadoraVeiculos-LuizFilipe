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


	public CaminhonetaPassageiro(Integer id, String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3) {
		super(id, nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
	}


	public CaminhonetaPassageiro(Integer id, String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3,
			boolean airBag, boolean direcaoAssistida, boolean ligaLeve,
			boolean cintosTrasRetrateis, boolean contrPol) {
		super(id, nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
		this.airBag = airBag;
		this.direcaoAssistida = direcaoAssistida;
		this.ligaLeve = ligaLeve;
		this.cintosTrasRetrateis = cintosTrasRetrateis;
		this.contrPol = contrPol;
	}
	
	public CaminhonetaPassageiro(String nome, String tamanho, String tipoCambio, double valorDiaria,
			boolean arCondicionado, boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3,
			boolean airBag, boolean direcaoAssistida, boolean ligaLeve,
			boolean cintosTrasRetrateis, boolean contrPol) {
		super(nome, tamanho, tipoCambio, arCondicionado, direcaoHidraulica, cameraRe, radio, mp3);
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (airBag ? 1231 : 1237);
		result = prime * result + (cintosTrasRetrateis ? 1231 : 1237);
		result = prime * result + (contrPol ? 1231 : 1237);
		result = prime * result + (direcaoAssistida ? 1231 : 1237);
		result = prime * result + (ligaLeve ? 1231 : 1237);
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
		CaminhonetaPassageiro other = (CaminhonetaPassageiro) obj;
		if (airBag != other.airBag)
			return false;
		if (cintosTrasRetrateis != other.cintosTrasRetrateis)
			return false;
		if (contrPol != other.contrPol)
			return false;
		if (direcaoAssistida != other.direcaoAssistida)
			return false;
		if (ligaLeve != other.ligaLeve)
			return false;
		return true;
	}
	
	
}
