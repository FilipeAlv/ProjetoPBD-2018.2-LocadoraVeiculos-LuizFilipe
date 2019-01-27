package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="sequencia_categoria", sequenceName="seq_cat", initialValue=1, allocationSize=1)
public class Categoria implements EntidadeBase{
	@Id
	@GeneratedValue(generator="sequencia_categoria", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(length=75, unique=true)
	private String nome;
	@Column(length=25)
	private String tamanho;
	@Column(length=25)
	private String tipoCambio;
	private boolean arCondicionado;
	private boolean direcaoHidraulica;
	private boolean cameraRe;
	private boolean radio;
	private boolean mp3;
	
	
	public Categoria() {
		super();
		
	}
	
	
	public Categoria(Integer id, String nome, String tamanho, String tipoCambio, boolean arCondicionado,
			boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3) {
		super();
		this.id = id;
		this.nome = nome;
		this.tamanho = tamanho;
		this.tipoCambio = tipoCambio;
		this.arCondicionado = arCondicionado;
		this.direcaoHidraulica = direcaoHidraulica;
		this.cameraRe = cameraRe;
		this.radio = radio;
		this.mp3 = mp3;
	}
	
	public Categoria(String nome, String tamanho, String tipoCambio, boolean arCondicionado,
			boolean direcaoHidraulica, boolean cameraRe, boolean radio, boolean mp3) {
		super();
		this.nome = nome;
		this.tamanho = tamanho;
		this.tipoCambio = tipoCambio;
		this.arCondicionado = arCondicionado;
		this.direcaoHidraulica = direcaoHidraulica;
		this.cameraRe = cameraRe;
		this.radio = radio;
		this.mp3 = mp3;
	}


	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
	
	public boolean isArCondicionado() {
		return arCondicionado;
	}
	public void setArCondicionado(boolean arCondicionado) {
		this.arCondicionado = arCondicionado;
	}
	public boolean isDirecaoHidraulica() {
		return direcaoHidraulica;
	}
	public void setDirecaoHidraulica(boolean direcaoHidraulica) {
		this.direcaoHidraulica = direcaoHidraulica;
	}
	public boolean isCameraRe() {
		return cameraRe;
	}
	public void setCameraRe(boolean cameraRe) {
		this.cameraRe = cameraRe;
	}
	public boolean isRadio() {
		return radio;
	}
	public void setRadio(boolean radio) {
		this.radio = radio;
	}
	public boolean isMp3() {
		return mp3;
	}
	public void setMp3(boolean mp3) {
		this.mp3 = mp3;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (arCondicionado ? 1231 : 1237);
		result = prime * result + (cameraRe ? 1231 : 1237);
		result = prime * result + (direcaoHidraulica ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (mp3 ? 1231 : 1237);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (radio ? 1231 : 1237);
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((tipoCambio == null) ? 0 : tipoCambio.hashCode());
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
		Categoria other = (Categoria) obj;
		if (arCondicionado != other.arCondicionado)
			return false;
		if (cameraRe != other.cameraRe)
			return false;
		if (direcaoHidraulica != other.direcaoHidraulica)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mp3 != other.mp3)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (radio != other.radio)
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (tipoCambio == null) {
			if (other.tipoCambio != null)
				return false;
		} else if (!tipoCambio.equals(other.tipoCambio))
			return false;
		return true;
	}
	
}
