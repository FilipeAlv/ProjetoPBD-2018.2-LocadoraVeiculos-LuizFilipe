package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="sequencia_pessoa", sequenceName="seq_pessoa", initialValue=1, allocationSize=1)
public abstract class Pessoa implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_pessoa")
	private Integer id;
	@Column(length=50)
	private String codigo;
	@Column(length=150)
	private String nome;
	@Column(unique=true)
	private String login;
	@Column(length=16)
	private String senha;
	@OneToOne
	private Endereco endereco;
	
	public Pessoa() {		
	}
	
	public Pessoa(Integer id, String codigo, String nome, String login, String senha, Endereco endereco) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.endereco = endereco;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
