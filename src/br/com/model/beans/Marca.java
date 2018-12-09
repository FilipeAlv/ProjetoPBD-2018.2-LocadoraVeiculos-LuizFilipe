package br.com.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="sequencia_marca", sequenceName="seq_marca", initialValue=1, allocationSize=1)
public class Marca implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_marca")
	private int id;
	@Column(length=75)
	private String nome;
	
	public Marca() {
		super();
	}

	public Marca(String nome) {
		super();
		this.nome = nome;
	}




	public Marca(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}


	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Marca other = (Marca) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
}


