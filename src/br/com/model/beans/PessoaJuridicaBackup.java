package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PessoaJuridicaBackup extends PessoaBackup{
	@Column(length=19)
	private String cnpj;
	@Column(length=25)
	private String inscricaoEstadual;
	public PessoaJuridicaBackup() {
		super();
	}
	public PessoaJuridicaBackup(Integer id, String codigo, String nome, String login, String senha, Endereco endereco) {
		super(id, codigo, nome, login, senha, endereco);
	}
	public PessoaJuridicaBackup(Integer id, String codigo, String nome, String login, String senha, Endereco endereco, String cnpj, String inscricaoEstadual) {
		super(id, codigo, nome, login, senha, endereco);
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	public PessoaJuridicaBackup(String codigo, String nome, String login, String senha, Endereco endereco, String cnpj, String inscricaoEstadual) {
		super(codigo, nome, login, senha, endereco);
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



}
