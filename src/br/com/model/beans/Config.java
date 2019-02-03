package br.com.model.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.com.model.dao.DAOConfig;

@Entity
@SequenceGenerator(name="sequencia_config", sequenceName="seq_config", initialValue=1, allocationSize=1)
public class Config implements EntidadeBase{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia_config")
	private Integer  id;
	private Date horaBackup;
	private Date tempoRevisao;
	@OneToOne
	private Pessoa Usuario;
	private static Config config;
	
	public static Config getInstace() {
		List<Config>configs = DAOConfig.getInstance().findAll();
		if(configs.size()!=0) {

			System.out.println("Tem");
			config = configs.get(0);
		}else {
			System.out.println("N tem");
			config = new Config();
			DAOConfig.getInstance().saveOrUpdate(config);
		}
		return config;
	}
	
	private Config() {
		super();
	}
	
	private Config(Integer id, Date horaBackup, Date tempoRevisao, String tempoLimpeza, Pessoa usuario) {
		super();
		this.id = id;
		this.horaBackup = horaBackup;
		this.tempoRevisao = tempoRevisao;
		Usuario = usuario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getHoraBackup() {
		return horaBackup;
	}
	public void setHoraBackup(Date horaBackup) {
		this.horaBackup = horaBackup;
	}
	public Date getTempoRevisao() {
		return tempoRevisao;
	}
	public void setTempoRevisao(Date tempoRevisao) {
		this.tempoRevisao = tempoRevisao;
	}
	public Pessoa getUsuario() {
		return Usuario;
	}
	public void setUsuario(Pessoa usuario) {
		Usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Usuario == null) ? 0 : Usuario.hashCode());
		result = prime * result + ((horaBackup == null) ? 0 : horaBackup.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tempoRevisao == null) ? 0 : tempoRevisao.hashCode());
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
		Config other = (Config) obj;
		if (Usuario == null) {
			if (other.Usuario != null)
				return false;
		} else if (!Usuario.equals(other.Usuario))
			return false;
		if (horaBackup == null) {
			if (other.horaBackup != null)
				return false;
		} else if (!horaBackup.equals(other.horaBackup))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tempoRevisao == null) {
			if (other.tempoRevisao != null)
				return false;
		} else if (!tempoRevisao.equals(other.tempoRevisao))
			return false;
		return true;
	}
	
	
	
	
}
