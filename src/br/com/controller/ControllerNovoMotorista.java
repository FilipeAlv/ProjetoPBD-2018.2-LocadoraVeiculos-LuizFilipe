package br.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ControllerNovoMotorista {

    @FXML
    private Label lbTitle;

    @FXML
    private TextField fdRua;

    @FXML
    private ComboBox<String> cbUf;

    @FXML
    private TextField fdNumero;

    @FXML
    private TextField fdBairro;

    @FXML
    private TextField fdCidade;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField fdNomeFi;

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
    void actionSalvar(ActionEvent event) {

    }

}
