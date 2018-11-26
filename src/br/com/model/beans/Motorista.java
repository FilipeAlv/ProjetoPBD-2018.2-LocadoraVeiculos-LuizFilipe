package br.com.model.beans;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Motorista extends PessoaFisica{
	private int idade;
	private String habilitacao;
	private Date validadeHabilitacao;
	public Motorista() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Motorista(int id, String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo) {
		super(id, codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);

	}
	public Motorista(int id, String codigo, String nome, String login, String senha, Endereco endereco) {
		super(id, codigo, nome, login, senha, endereco);


	}

	public Motorista(int id, String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo, int idade, String habilitacao, Date validadeHabilitacao) {
		super(id, codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);
		this.idade = idade;
		this.habilitacao = habilitacao;
		this.validadeHabilitacao = validadeHabilitacao;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
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
