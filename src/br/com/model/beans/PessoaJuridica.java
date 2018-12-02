package br.com.model.beans;

import javax.persistence.Entity;

@Entity
public class PessoaJuridica extends Pessoa{
	private String cnpj;
	private String inscricaoEstadual;
	public PessoaJuridica() {
		super();
	}
	public PessoaJuridica(Integer id, String codigo, String nome, String login, String senha, Endereco endereco) {
		super(id, codigo, nome, login, senha, endereco);
	}
	public PessoaJuridica(Integer id, String codigo, String nome, String login, String senha, Endereco endereco, String cnpj, String inscricaoEstadual) {
		super(id, codigo, nome, login, senha, endereco);
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	public PessoaJuridica(String codigo, String nome, String login, String senha, Endereco endereco, String cnpj, String inscricaoEstadual) {
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
