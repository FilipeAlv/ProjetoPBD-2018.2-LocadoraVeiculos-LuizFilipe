package br.com.model.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "viewlogpessoa")
public class ClienteAdapter{
		@Id
		private Integer cod;
    	private String nome, login ,cpf, rg, sexo, cnpj, insc, codigo;
    	private Date nasc, data;
    	private String usuario, tipo;
    	
    	
    	
		public ClienteAdapter() {
			super();
		}


		public ClienteAdapter(String codigo, String nome, String login, String cpf, String rg, String sexo, Date nasc,
				Date data) {
			super();
			this.codigo = codigo;
			this.nome = nome;
			this.login = login;
			this.cpf = cpf;
			this.rg = rg;
			this.sexo = sexo;
			this.nasc = nasc;
			this.data = data;
		}
		
		
		public ClienteAdapter(String codigo, String nome, String login, String cnpj, String insc, Date data) {
			super();
			this.codigo = codigo;
			this.nome = nome;
			this.login = login;
			this.cnpj = cnpj;
			this.insc = insc;
			this.data = data;
		}


		
		public String getUsuario() {
			return usuario;
		}


		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}


		public String getTipo() {
			return tipo;
		}


		public void setTipo(String tipo) {
			this.tipo = tipo;
		}


		public Integer getId() {
			return cod;
		}


		public void setId(Integer id) {
			this.cod = id;
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
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public String getInsc() {
			return insc;
		}
		public void setInsc(String insc) {
			this.insc = insc;
		}
		public Date getNasc() {
			return nasc;
		}
		public void setNasc(Date nasc) {
			this.nasc = nasc;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
			result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			result = prime * result + ((cod == null) ? 0 : cod.hashCode());
			result = prime * result + ((insc == null) ? 0 : insc.hashCode());
			result = prime * result + ((login == null) ? 0 : login.hashCode());
			result = prime * result + ((nasc == null) ? 0 : nasc.hashCode());
			result = prime * result + ((nome == null) ? 0 : nome.hashCode());
			result = prime * result + ((rg == null) ? 0 : rg.hashCode());
			result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
			result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
			result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
			ClienteAdapter other = (ClienteAdapter) obj;
			if (cnpj == null) {
				if (other.cnpj != null)
					return false;
			} else if (!cnpj.equals(other.cnpj))
				return false;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			if (cpf == null) {
				if (other.cpf != null)
					return false;
			} else if (!cpf.equals(other.cpf))
				return false;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			if (cod == null) {
				if (other.cod != null)
					return false;
			} else if (!cod.equals(other.cod))
				return false;
			if (insc == null) {
				if (other.insc != null)
					return false;
			} else if (!insc.equals(other.insc))
				return false;
			if (login == null) {
				if (other.login != null)
					return false;
			} else if (!login.equals(other.login))
				return false;
			if (nasc == null) {
				if (other.nasc != null)
					return false;
			} else if (!nasc.equals(other.nasc))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (rg == null) {
				if (other.rg != null)
					return false;
			} else if (!rg.equals(other.rg))
				return false;
			if (sexo == null) {
				if (other.sexo != null)
					return false;
			} else if (!sexo.equals(other.sexo))
				return false;
			if (tipo == null) {
				if (other.tipo != null)
					return false;
			} else if (!tipo.equals(other.tipo))
				return false;
			if (usuario == null) {
				if (other.usuario != null)
					return false;
			} else if (!usuario.equals(other.usuario))
				return false;
			return true;
		}



    	
    	
    }