package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PessoaJuridica2 extends Pessoa2{
	@Column(length=19)
	private String cnpj;
	@Column(length=25)
	private String inscricaoEstadual;
	public PessoaJuridica2() {
		super();
	}
	public PessoaJuridica2(String cnpj, String inscricaoEstadual) {
		super();
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((inscricaoEstadual == null) ? 0 : inscricaoEstadual.hashCode());
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
		PessoaJuridica2 other = (PessoaJuridica2) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (inscricaoEstadual == null) {
			if (other.inscricaoEstadual != null)
				return false;
		} else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
			return false;
		return true;
	}
	


}
