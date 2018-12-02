package br.com.model.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Funcionario extends PessoaFisica{
	@Column(length=75)
	private String cargo;
	private String permissao;
	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Funcionario(int id, String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo) {
		super(id, codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);
		
	}
	public Funcionario(int id, String codigo, String nome, String login, String senha, Endereco endereco) {
		super(id, codigo, nome, login, senha, endereco);
		
	}
	public Funcionario(int id, String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo, String cargo, String permissao) {
		super(id, codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);
		this.cargo = cargo;
		this.permissao = permissao;
	}
	
	public Funcionario(String codigo, String nome, String login, String senha, Endereco endereco, String cpf,
			String rg, Date dataNascimento, String sexo, String cargo, String permissao) {
		super(codigo, nome, login, senha, endereco, cpf, rg, dataNascimento, sexo);
		this.cargo = cargo;
		this.permissao = permissao;
	}
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getPermissao() {
		return permissao;
	}
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}
	
	
	
	
	
	
}
