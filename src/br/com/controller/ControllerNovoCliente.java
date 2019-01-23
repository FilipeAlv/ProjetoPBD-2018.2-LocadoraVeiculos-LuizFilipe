package br.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControllerNovoCliente {

    @FXML
    private Label lbTitle;

    @FXML
    private TextField fdRua;

    @FXML
    private ComboBox<?> cbUf;

    @FXML
    private TextField fdNumero;

    @FXML
    private TextField fdBairro;

    @FXML
    private TextField fdCidade;

    @FXML
    private Button btnSalvar;

    @FXML
    private Pane panelJuridica;

    @FXML
    private TextField fdNome;

    @FXML
    private TextField fdCnpj;

    @FXML
    private TextField fdLoginJu;

    @FXML
    private TextField fdSenhaJu;

    @FXML
    private TextField fdInscricao;

    @FXML
    private TextField fdNomeFi;

    @FXML
    private CheckBox checkMotorista;

    @FXML
    private TextField fdCpf;

    @FXML
    private TextField fdHabilitacao;

    @FXML
    private TextField fdLoginFi;

    @FXML
    private TextField fdSenhaFi;

    @FXML
    private TextField fdRg;

    @FXML
    private DatePicker fdNacimento;

    @FXML
    private RadioButton radioMasculino;

    @FXML
    private RadioButton radioFeminino;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    void actionIsMotorista(ActionEvent event) {

    }

    @FXML
    void actionSalvar(ActionEvent event) {

    }

    @FXML
    void actionTipoPessoa(ActionEvent event) {

    }

}
