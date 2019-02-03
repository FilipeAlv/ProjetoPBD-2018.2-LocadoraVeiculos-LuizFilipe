package br.com.util;

public class SQLUtil {
	public class Pessoa{
		public static final String SELECT_ALL = "SELECT p FROM Pessoa p";
		public static final String SELECT_MAX_COD = "SELECT MAX(codigo) FROM Pessoa";
		public static final String SELECT_MAX_ID = "SELECT MAX(id) FROM Pessoa";
		public static final String SELECT_LOGIN_SENHA = "SELECT p FROM Pessoa p WHERE p.login = :login AND p.senha = :senha";
		public static final String SELECT_LOGIN = "SELECT p FROM Pessoa p WHERE p.login = :login";
		public static final String SELECT_CPF = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf";
		public static final String UPDADTE_SENHA = "UPDATE Pessoa p SET senha = :novaSenha WHERE senha = :senha AND login = :login";
		public static final String PROCEDURE_GERA_CODIGO = "geracodigo";
		

	}
	
	public class PessoaBackup{
		public static final String SELECT_ALL = "SELECT p FROM ClienteAdapter p";
	}
	
	public class PessoaFisica{
		public static final String SELECT_ALL = "SELECT p FROM PessoaFisica p";
		public static final String SELECT_CPF = "SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf";
		public static final String SELECT = "SELECT p FROM PessoaFisica p WHERE LOWER(p.nome) LIKE :str or LOWER(p.cpf) LIKE :str";
	}
	
	public class PessoaFisicaBackup{
		public static final String SELECT_ALL = "SELECT p FROM PessoaFisica2 p";
	}
	
	public class PessoaJuridica{
		public static final String SELECT_ALL = "SELECT p FROM PessoaJuridica p";
		public static final String SELECT_CNPJ = "SELECT p FROM PessoaJuridica p WEHERE p.cnpj = :cnpj";
		public static final String SELECT = "SELECT p FROM PessoaJuridica p WHERE LOWER(p.nome) LIKE :str or LOWER(p.cnpj) LIKE :str";
	}
	
	public class PessoaJuridicaBackup{
		public static final String SELECT_ALL = "SELECT p FROM PessoaJuridica2 p";
	}
	
	public class Categoria{
		public static final String SELECT_ALL = "SELECT c FROM Categoria c";
		public static final String SELECT_MAX_ID = "SELECT MAX(id) FROM Categoria";
		public static final String SELECT_TAMANHO = "SELECT c FROM Categoria c WHERE c.tamanho = :tamanho";
		public static final String SELECT_NOME = "SELECT c FROM Categoria c WHERE c.nome = :nome";
		public static final String SELECT_FOR_TABLE = "SELECT c.id, c.nome, c.tamanho FROM Categoria c";
		
	}
	
	public class CaminhonetaCarga{
		public static final String SELECT_ALL = "SELECT c FROM CaminhonetaCarga c";
		public static final String SELECT_POTENCIA = "SELECT c FROM CaminhonetaCarga c WHERE c.potencia = :potencia";
	}
	
	public class CaminhonetaPassageiro{
		public static final String SELECT_ALL = "SELECT c FROM CaminhonetaPassageiro c";
	}
	
	public class Endereco{
		public static final String SELECT_UF = "SELECT e FROM Endereco e WHERE e.uf = :uf";
		public static final String SELECT_MAX_ID = "SELECT MAX(id) FROM Endereco";
	}
	
	public class Filial{
		public static final String SELECT_NOME = "SELECT fl FROM Filial fl WHERE fl.nome = :nome";
		public static final String SELECT_ID_NOME = "SELECT fl.id FROM Filial fl WHERE fl.nome = :nome";
		public static final String SELECT_MAX_ID = "SELECT MAX(id) FROM Filial";
		public static final String SELECT_ALL = "SELECT f FROM Filial f";
	}
	
	public class Funcionario{
		public static final String SELECT_ALL = "SELECT f FROM Funcionario f";
		public static final String SELECT_CARGO = "SELECT f FROM Funcionario f WHERE f.cargo = :cargo";
	}
	
	public class Locacao{
		public static final String SELECT_ALL = "SELECT l FROM Locacao l";
		public static final String SELECT_DATA = "SELECT l FROM Locacao l WHERE l.dataFinal = :dataFinal";
	}
	
	public class LocacaoBackup{
		public static final String SELECT_ALL = "SELECT l FROM Locacao2 l";
		public static final String SELECT_VIEW =  "SELECT l FROM LocacaoAdapter l";
	}
	
	public class Motorista{
		public static final String SELECT_ALL = "SELECT m FROM Motorista m";
		public static final String SELECT_HABILITACAO = "SELECT m FROM Motorista m WHERE m.habilitacao = :habilitacao";
	}
	
	public class MotoristaBackup{
		public static final String SELECT_ALL = "SELECT m FROM Motorista2 m";
		}
	
	public class Reserva{
		public static final String SELECT_ALL = "SELECT r FROM Reserva r";
		public static final String SELECT_DATA = "SELECT r FROM Reserva r WHERE r.dataInicial = :dataInicial";
		public static final String SELECT_TIPO = "SELECT r FROM Reserva r WHERE e.tipoLocacao = :tipo";
	}
	
	public class ReservaBackup{
		public static final String SELECT_ALL = "SELECT r FROM Reserva2 r";
		public static final String SELECT_VIEW = "SELECT r FROM ReservaAdapter r";
	}
	
	
	public class Veiculo{
		public static final String SELECT_ALL = "SELECT v FROM Veiculo v";
		public static final String SELECT_PLACA = "SELECT v FROM Veiculo v WHERE v.placa = :placa";
		
		public static final String SELECT_BUSCA = "SELECT v FROM Veiculo v WHERE LOWER(v.placa) LIKE :str or LOWER(v.modelo.nome) LIKE :str "
				+ "or LOWER(v.categoria.nome) LIKE :str";
		
		public static final String SELECT_BUSCA_FILIAL = "SELECT v FROM Veiculo v WHERE (LOWER(v.placa) LIKE :str or LOWER(v.modelo.nome) LIKE :str "
				+ "or LOWER(v.categoria.nome) LIKE :str) AND v.filialAtual = :filial";
		
		public static final String SELECT_BUSCA_STATUS = "SELECT v FROM Veiculo v WHERE (LOWER(v.placa) LIKE :str or LOWER(v.modelo.nome) LIKE :str "
				+ "or LOWER(v.categoria.nome) LIKE :str) and (v.status LIKE :status)";
		
		public static final String SELECT_FILIAL =  "SELECT v FROM Veiculo v WHERE v.filialAtual = :filial";
		
		public static final String SELECT_STATUS =  "SELECT v FROM Veiculo v WHERE v.status = :status";
		
		public static final String SELECT_DATA = "SELECT DISTINCT v FROM Veiculo v, Locacao l, Reserva r WHERE "
				+ "(v.id = l.veiculo.id and l.reserva.id = r.id and r.dataFinalPrevista <= :data and l.status ='Aguardando') or (v.status = 'Disponivel')";
		
		public static final String SELECT_DATA_FILIAL = "SELECT DISTINCT v FROM Veiculo v, Locacao l, Reserva r WHERE "
				+ "(v.id = l.veiculo.id and l.reserva.id = r.id and r.dataFinalPrevista <= :data and l.status ='Aguardando' and r.filialEntrega = :filial) or (v.status = 'Disponivel' and v.filialAtual = :filial)";
		
		public static final String PROCEDURE_CONTA_VEICULO = "contaveiculoporcategoria";
		
		public static final String SELECT_CATEGORIA_FILIAL = "SELECT v FROM Veiculo v WHERE v.filialAtual = :filial and v.categoria = :categoria";
		
		public static final String SELECT_CATEGORIA = "SELECT v FROM Veiculo v WHERE v.categoria = :categoria";
		public static final String SELECT_STATUS_FILIAL = "SELECT v FROM Veiculo v WHERE v.filialAtual = :filial and v.status = :status";
		
		
	}
	
	public class DaoGenerico{
		public static final String EXISTE = "SELECT :objeto FROM :tabela :objeto WHERE :campo = :atributo";
	}
	
	public class ValorLocacao{
		public static final String SELECT_ALL = "SELECT v FROM ValorLocacao v";
		public static final String SELECT_TIPO_CAT = "SELECT v.valor FROM ValorLocacao v WHERE v.tipoLocacao = :tipo and v.categoria = :categoria";
	}

	public class Marca{
		public static final String SELECT_ALL = "SELECT m FROM Marca m";
		public static final String SELECT_NOME = "SELECT m FROM Marca m WHERE m.nome = :nome";
	}
	
	public class Modelo{
		public static final String SELECT_ALL = "SELECT m FROM Modelo m";
		public static final String SELECT_NOME = "SELECT m FROM Modelo m WHERE m.nome = :nome";
	}

	public class Config{
		public static final String SELECT_ALL = "SELECT c FROM Config c";
	}

}
