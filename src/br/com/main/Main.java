package br.com.main;

import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.dao.DaoGenerico;

public class Main {
	public static void main(String[] args) {
		DaoGenerico<Pessoa> daoPessoa = new DaoGenerico<Pessoa>();
		Pessoa pessoa = new PessoaFisica();
		pessoa.setNome("Luiz Filipe Alves da Silva");
		pessoa.setLogin("filipe");
		pessoa.setSenha("filipe");
		daoPessoa.saveOrUpdate(pessoa);
		
	}
}
