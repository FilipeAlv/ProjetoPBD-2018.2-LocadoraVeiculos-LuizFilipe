package br.com.controller;

import br.com.main.Main;
import br.com.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerPrincipal{

    @FXML
    private ImageView btnClose;

    @FXML
    private ImageView btnConfigSis;

    @FXML
    private MenuItem menuCadastroCategoria;

    @FXML
    private MenuItem menuCadastroFilial;

    @FXML
    private MenuItem menuCadastroMotorista;

    @FXML
    private MenuItem menuCadastroCliente;

    @FXML
    private MenuItem menuVerFuncionario;

    @FXML
    private MenuItem menuNovoFuncionario;

    @FXML
    private MenuItem menuVerVeiculo;

    @FXML
    private MenuItem menuNovoVeiculo;

    @FXML
    private MenuItem menuNovaMarca;

    @FXML
    private MenuItem menuNovoModelo;

    @FXML
    private MenuItem menuVerReservas;

    @FXML
    private MenuItem menuNovaReserva;

    @FXML
    private MenuItem menuVerLocacao;

    @FXML
    private MenuItem menuLocacaoSemReserva;

    @FXML
    private MenuItem menuConfigLocacao;

    @FXML
    void actionAddReserva(ActionEvent event) {
    	Main.novaTela("NovaReserva");
    }

    @FXML
    void actionCadastroCategoria(ActionEvent event) {
    	Main.novaTela("NovaCategoria");
    }

    @FXML
    void actionCadastroCliente(ActionEvent event) {
    	Main.novaTela("NovoCliente");
    }

    @FXML
    void actionCadastroFilial(ActionEvent event) {
    	Main.novaTela("NovaFilial");
    }

    @FXML
    void actionCadastroMotorista(ActionEvent event) {
    	Main.novaTela("NovoMotorista");
    }

    @FXML
    void actionClose(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void actionConfigLocacao(ActionEvent event) {
    	Main.novaTela("ConfigLocacao");
    }

    @FXML
    void actionConfigSis(MouseEvent event) {
    	Main.novaTela("AlterarSenha");
    }

    @FXML
    void actionLocacaoSemReserva(ActionEvent event) {
    	
    }

    @FXML
    void actionLogout(MouseEvent event) {
    	Main.alterarTela("Login");
		Util.SCRIPT=false;
    }

    @FXML
    void actionNovaMarca(ActionEvent event) {
    	Main.novaTela("NovaMarca");
    }

    @FXML
    void actionNovoFuncionario(ActionEvent event) {
    	Main.novaTela("NovoFuncionario");
    }

    @FXML
    void actionNovoModelo(ActionEvent event) {
    	Main.novaTela("NovoModelo");
    }

    @FXML
    void actionNovoVeiculo(ActionEvent event) {
    	Main.novaTela("NovoVeiculo");
    }

    @FXML
    void actionVerFuncionario(ActionEvent event) {
    	Main.novaTela("Funcionario");
		ControllerFuncionario.desativarBotoes();
    }

    @FXML
    void actionVerLocacao(ActionEvent event) {

    }

    @FXML
    void actionVerReserva(ActionEvent event) {
    	Main.novaTela("Reserva");
    }

    @FXML
    void actionVerVeiculo(ActionEvent event) {

    }



}
