package br.com.model.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Motorista2 extends PessoaFisica2{
	@Column(length=75)
	private String habilitacao;
	private Date validadeHabilitacao;
	public Motorista2() {
		super();
	}
	


	public Motorista2(String habilitacao, Date validadeHabilitacao) {
		super();
		this.habilitacao = habilitacao;
		this.validadeHabilitacao = validadeHabilitacao;
	}



	public String getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(String habilitacao) {
		this.habilitacao = habilitacao;
	}

	public Date getValidadeHabilitacao() {
		return validadeHabilitacao;
	}

	public void setValidadeHabilitacao(Date validadeHabilitacao) {
		this.validadeHabilitacao = validadeHabilitacao;
	}
	
	
	
}
