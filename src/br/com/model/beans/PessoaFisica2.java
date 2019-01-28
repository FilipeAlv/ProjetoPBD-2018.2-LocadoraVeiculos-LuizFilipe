package br.com.model.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class PessoaFisica2 extends Pessoa2{
	@Column(length=15)
	private String cpf;
	@Column(length=25)
	private String rg;
	private Date dataNascimento;
	@Column(length=10)
	private String sexo;
	
	public PessoaFisica2() {
		super();
		
	}
	public PessoaFisica2(int id, String codigo, String nome, String login, String senha, Endereco endereco) {
		super(id, codigo, nome, login, senha, endereco);
		
	}
	
	public PessoaFisica2(int id, String codigo, String nome, String login, String senha, Endereco endereco, String cpf, String rg, Date dataNascimento, String sexo) {
		super(id, codigo, nome, login, senha, endereco);
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
	public PessoaFisica2( String codigo, String nome, String login, String senha, Endereco endereco, String cpf, String rg, Date dataNascimento, String sexo) {
		super(codigo, nome, login, senha, endereco);
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	

}
