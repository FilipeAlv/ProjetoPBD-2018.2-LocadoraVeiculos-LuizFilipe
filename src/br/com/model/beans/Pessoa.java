package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@SequenceGenerator(name="sequencia_pessoa", sequenceName="seq_pessoa", initialValue=1, allocationSize=1)
public class Pessoa implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_pessoa")
	private Integer id;
	@Column(length=50)
	private String codigo;
	@Column(length=150)
	private String nome;
	@Column(unique=true)
	private String login;
	@Column(length=11)
	private String senha;
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Endereco endereco;
	private boolean statusOb = true;
	
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
	
	public Pessoa(String codigo, String nome, String login, String senha, Endereco endereco) {
		super();
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
	
	public boolean isStatusOb() {
		return statusOb;
	}

	public void setStatusOb(boolean statusOb) {
		this.statusOb = statusOb;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + (statusOb ? 1231 : 1237);
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
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (statusOb != other.statusOb)
			return false;
		return true;
	}
	
	
	
	
	
}
