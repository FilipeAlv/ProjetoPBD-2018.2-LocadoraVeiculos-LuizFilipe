package br.com.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import br.com.main.Main;
import br.com.model.beans.CaminhonetaCarga;
import br.com.model.beans.CaminhonetaPassageiro;
import br.com.model.beans.Categoria;
import br.com.model.beans.Config;
import br.com.model.beans.Endereco;
import br.com.model.beans.Filial;
import br.com.model.beans.Funcionario;
import br.com.model.beans.Locacao;
import br.com.model.beans.Marca;
import br.com.model.beans.Modelo;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.model.beans.PessoaJuridica;
import br.com.model.beans.Reserva;
import br.com.model.beans.Veiculo;
import br.com.model.dao.DAOCaminhonetaCarga;
import br.com.model.dao.DAOCaminhonetaPassageiro;
import br.com.model.dao.DAOCategoria;
import br.com.model.dao.DAOConfig;
import br.com.model.dao.DAOFilial;
import br.com.model.dao.DAOFuncionario;
import br.com.model.dao.DAOLocacao;
import br.com.model.dao.DAOMarca;
import br.com.model.dao.DAOModelo;
import br.com.model.dao.DAOMotorista;
import br.com.model.dao.DAOPessoa;
import br.com.model.dao.DAOPessoaFisica;
import br.com.model.dao.DAOPessoaJuridica;
import br.com.model.dao.DAOReserva;
import br.com.model.dao.DAOVeiculo;
import br.com.util.Util;
import br.com.util.Util.Criptografia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerLogin {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private Button btnEntrar;

    @FXML
    private ImageView btnClose;

    @FXML
    void actionClose(MouseEvent event) {
    	System.exit(0);
    }
    
	@FXML
    void ectionEntrar(ActionEvent event){
    	Alert alert = new Alert (AlertType.INFORMATION);
		alert.setTitle("Erro ao fazer login");
		alert.setHeaderText("Erro ao fazer login");
    	String login = loginField.getText().toString();
    	String senha = null;
    	if(senhaField.getText().length()==0)
    		senha = Util.SENHA_PADRAO;
    	else
    		senha = new String(senhaField.getText());
    		Pessoa usuario = DAOPessoa.getInstace().findByLogin(login);
    		Config config = Config.getInstace();
    		config.setUsuario(usuario);
    		String senhaDec = Criptografia.decriptografa(usuario.getSenha().toCharArray());
    		if(senhaDec.equals(senha)){
    			if(Util.SCRIPT)
    				script();
    			DAOConfig.getInstance().saveOrUpdate(config);
    			telas();
        		
    		}else {
    			alert.setContentText("Senha Incorreta!");
    			alert.show();
    		}
		
    	
    }
    
    private void telas() {
    	Main.alterarTela("Principal");
		loginField.setText("");
		senhaField.setText("");
    }
    
    private void script() {
		Endereco endereco = new Endereco("Rua 15", "01A", "Quebrada", "Belmonte", "PE");
		CaminhonetaCarga caminhonetaCarga = null;
		CaminhonetaPassageiro caminhonetaPassageiro = null;
		PessoaFisica pessoaFisica = null;
		PessoaJuridica pessoaJuridica = null;
		Motorista motorista = null;
		Funcionario funcionario = null;
		Locacao locacao = null;
		Filial filial = null;
		Categoria cat = null;
		Marca marca = null;
		Modelo modelo = null;
		Veiculo veiculo = null;
		Reserva reserva = null;
		
		DAOCaminhonetaCarga daoCC = new DAOCaminhonetaCarga();
		DAOCaminhonetaPassageiro daoCP = new DAOCaminhonetaPassageiro();
		DAOCategoria daoC = new DAOCategoria();
		DAOFilial daoF = new DAOFilial();
		DAOFuncionario daoFU = new DAOFuncionario();
		DAOLocacao daoL = new DAOLocacao();
		DAOMotorista daoM = new DAOMotorista();
		DAOPessoaFisica daoPF = new DAOPessoaFisica();
		DAOPessoaJuridica daoPJ = new DAOPessoaJuridica();
		DAOReserva daoR = new DAOReserva();
		DAOVeiculo daoV = new DAOVeiculo();
		DAOMarca daoMR = new DAOMarca();
		DAOModelo daoMD = new DAOModelo();
		
		
		Calendar cal = new GregorianCalendar();
		cal.set(2013, Calendar.FEBRUARY, 28);
        Date data = cal.getTime();
		
		
		Alert dialog = new Alert(AlertType.CONFIRMATION); 
		dialog.setTitle("BEM VINDO!");
		dialog.setHeaderText("PERSISTIR");
		dialog.setContentText("Deseja Persistir pessoa fisica?");
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.get() == ButtonType.OK){
			pessoaFisica = new PessoaFisica( 
					"C012", "Luiz Filipe Alves da Silva", "login1",new String (Criptografia.criptografa("senha1".toCharArray())), endereco, "123.456.789-10", "99999999", data, "masculino");
			daoPF.saveOrUpdate(pessoaFisica);
		}
		dialog.setContentText("Deseja Persistir pessoa juridica?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			pessoaJuridica = new PessoaJuridica("C02", "Luiz Filipe Alves da Silva", "login2",new String (Criptografia.criptografa("senha2".toCharArray())), endereco,"12.456.789.0001-10", "12345678");
			daoPJ.saveOrUpdate(pessoaJuridica);
		}
		dialog.setContentText("Deseja Persistir motorista?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			motorista = new Motorista("C03", "Luiz Filipe Alves da Silva", "login3", new String (Criptografia.criptografa("senha3".toCharArray())), endereco, "123.456.789-11", "99999998", data, "masculino", "1237854", data);
			daoM.saveOrUpdate(motorista);
		}
		dialog.setContentText("Deseja Persistir categoria?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			cat = new Categoria("PGC", "pequeno", "automatico", true, true, false, true, false);
			daoC.saveOrUpdate(cat);
			cat=DAOCategoria.getInstance().findById(Categoria.class, cat.getId());
		}
		dialog.setContentText("Deseja Persistir categoria caminhoneta carga?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			caminhonetaCarga = new CaminhonetaCarga("CGS", "grande", "hidraulico", true, true, true, true,
					false, 4000, "otimo", 150, (int)2.5, (float)2.5,"hidraulica");
			daoCC.saveOrUpdate(caminhonetaCarga);
			caminhonetaCarga=DAOCaminhonetaCarga.getInstace().findById(CaminhonetaCarga.class, caminhonetaCarga.getId());
		}
		dialog.setContentText("Deseja Persistir categoria caminhoneta passageiros?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			caminhonetaPassageiro = new CaminhonetaPassageiro("GCP", "grande", "automatico", true, true,
					false, true, false, true, false, false, true, false);
			daoCP.saveOrUpdate(caminhonetaPassageiro);
			caminhonetaPassageiro=DAOCaminhonetaPassageiro.getInstace().findById(CaminhonetaPassageiro.class, caminhonetaPassageiro.getId());
		}
		dialog.setContentText("Deseja Persistir filial?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			filial = new Filial("Pajeu 01", endereco);
			daoF.saveOrUpdate(filial);
		}
		dialog.setContentText("Deseja Persistir funcionario?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			funcionario = new Funcionario("C04", "Luiz Filipe Alves da Silva", "login4",
					new String (Criptografia.criptografa("senha4".toCharArray())), endereco,
					"123.456.789-13", "99999998", data, "masculino", "gerente", "full");  			
			daoFU.saveOrUpdate(funcionario);
		}
		dialog.setContentText("Deseja Persistir Marca?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			marca = new Marca("Wolgsvage");
			daoMR.saveOrUpdate(marca);
			marca = daoMR.findByNome(marca.getNome());
		}
		
		dialog.setContentText("Deseja Persistir Modelo?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			modelo = new Modelo(2000,"Gol",2000,(float)2.5,4,4,2,"flex", marca);
			DAOModelo.getInstance().saveOrUpdate(modelo);
			modelo= DAOModelo.getInstance().findByNome(modelo.getNome());
		} 
		dialog.setContentText("Deseja Persistir veiculo?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			veiculo = new Veiculo("ckj1212","123123123", 255.20, 125454,"vermelho", cat, modelo, filial, "Disponivel");
			daoV.saveOrUpdate(veiculo);
			veiculo = DAOVeiculo.getInstance().findByPlaca(veiculo.getPlaca());
		}
		dialog.setContentText("Deseja Persistir reserva?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			reserva = new Reserva(data, data, data, "KMLivre", 150.00,pessoaJuridica, motorista,
					filial, filial,cat , "Aguardando");
			daoR.saveOrUpdate(reserva);
			reserva = DAOReserva.getInstance().findById(Reserva.class, reserva.getId());
		}
		
		dialog.setContentText("Deseja Persistir locacao?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			locacao = new Locacao(data, 15.00, "Bom estado", veiculo, reserva, "Aguardando");
			daoL.saveOrUpdate(locacao);
		}
	
		
		dialog.setAlertType(AlertType.INFORMATION);
		dialog.setHeaderText("ALTERAR");
		dialog.setContentText("Deseja Alterar pessoa fisica?");
		result = dialog.showAndWait();
		
		if (result.get() == ButtonType.OK){
			pessoaFisica.setCodigo("F00");
			daoPF.saveOrUpdate(pessoaFisica);
		}
		dialog.setContentText("Deseja Alterar pessoa juridica?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			pessoaJuridica.setCodigo("F01");
			daoPJ.saveOrUpdate(pessoaJuridica);
		}
		dialog.setContentText("Deseja Alterar motorista?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			motorista.setCodigo("F02");
			daoM.saveOrUpdate(motorista);
		}
		dialog.setContentText("Deseja Alterar categoria?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			cat.setNome("Categoria");
			cat.setMp3(true);
			daoC.saveOrUpdate(cat);
		}
		dialog.setContentText("Deseja Alterar categoria caminhoneta carga?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			caminhonetaCarga.setNome("CategoriaCaminhoneta");
			daoCC.saveOrUpdate(caminhonetaCarga);
		}
		dialog.setContentText("Deseja Alterar categoria caminhoneta passageiros?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			caminhonetaPassageiro.setNome("Camihoneta Passageiro");
			daoCP.saveOrUpdate(caminhonetaPassageiro);
		}
		dialog.setContentText("Deseja Alterar filial?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			filial.setNome("Filial Serra Talhada");
			daoF.saveOrUpdate(filial);
		}
		dialog.setContentText("Deseja Alterar funcionario?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			funcionario.setCodigo("F04");
			daoFU.saveOrUpdate(funcionario);
		}
		dialog.setContentText("Deseja Alterar Marca?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			marca = DAOMarca.getInstance().findByNome(marca.getNome());
			marca.setNome("FIAT");
			daoMR.saveOrUpdate(marca);
		}
		dialog.setContentText("Deseja Alterar Modelo?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			modelo.setNome("UNO");
			daoMD.saveOrUpdate(modelo);
		}
		dialog.setContentText("Deseja Alterar veiculo?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			veiculo.setCor("Verde");
			daoV.saveOrUpdate(veiculo);
		}
		dialog.setContentText("Deseja Alterar reserva?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			reserva.setTipoLocacao("KMControle");
			daoR.saveOrUpdate(reserva);
		}
		
		dialog.setContentText("Deseja Alterar locacao?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			locacao.setStatusVeiculo("Pessimo");
			daoL.saveOrUpdate(locacao);
		}
		
		dialog.setAlertType(AlertType.WARNING);
		dialog.setHeaderText("DELETAR");
		
		dialog.setContentText("Deseja deletar locacao?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoL.remove(Locacao.class, locacao.getId());
		}

		dialog.setContentText("Deseja deletar reserva?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoR.remove(Reserva.class, reserva.getId());
		}
		
		dialog.setContentText("Deseja deletar veiculo?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoV.remove(Veiculo.class, veiculo.getId());
		}
		
		dialog.setContentText("Deseja deletar Modelo?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoMD.remove(Modelo.class, modelo.getId());
		}
		
		dialog.setContentText("Deseja deletar Marca?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoMR.remove(Marca.class, marca.getId());
		}
		
		dialog.setContentText("Deseja deletar funcionario?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoFU.remove(Funcionario.class, funcionario.getId());
		}

		dialog.setContentText("Deseja deletar filial?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoF.remove(Filial.class, filial.getId());
		}

		dialog.setContentText("Deseja deletar categoria caminhoneta passageiros?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoCP.remove(CaminhonetaPassageiro.class, caminhonetaPassageiro.getId());
		}

		dialog.setContentText("Deseja deletar categoria caminhoneta carga?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoCC.remove(CaminhonetaCarga.class,caminhonetaCarga.getId());
		}

		dialog.setContentText("Deseja deletar categoria?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoC.remove(Categoria.class,cat.getId());
		}

		dialog.setContentText("Deseja deletar motorista?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoM.remove(Motorista.class, motorista.getId());
		}

		dialog.setContentText("Deseja deletar pessoa juridica?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoPJ.remove(Pessoa.class, pessoaJuridica.getId());
		}
		dialog.setContentText("Deseja deletar pessoa fisica?");
		result = dialog.showAndWait();
		if (result.get() == ButtonType.OK){
			daoPF.remove(Pessoa.class,pessoaFisica.getId());
		}
    }

}
