package br.com.model.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Motorista extends PessoaFisica{
	@Column(length=75)
	private String habilitacao;
	private Date validadeHabilitacao;
	public Motorista() {
		super();
	}
	
	public Motorista(Integer id, String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo) {
		super(id, codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);

	}
	public Motorista(Integer id, String codigo, String nome, String login, String senha, Endereco endereco) {
		super(id, codigo, nome, login, senha, endereco);


	}

	public Motorista(Integer id, String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo, String habilitacao, Date validadeHabilitacao) {
		super(id, codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);
		this.habilitacao = habilitacao;
		this.validadeHabilitacao = validadeHabilitacao;
	}
	
	public Motorista(String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo, String habilitacao, Date validadeHabilitacao) {
		super(codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);
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
