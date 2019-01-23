package br.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerVeiculos {

    @FXML
    private TableView<?> tbReserva;

    @FXML
    private TableColumn<?, ?> clienteCol;

    @FXML
    private TableColumn<?, ?> categoriaCol;

    @FXML
    private TableColumn<?, ?> valorCol;

    @FXML
    private TableColumn<?, ?> dataCol;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField fdBuscar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnAtualizar;

    @FXML
    void actionAddVeiculo(ActionEvent event) {

    }

    @FXML
    void actionAtualizar(ActionEvent event) {

    }

    @FXML
    void actionBuscar(ActionEvent event) {

    }

    @FXML
    void actionEditar(ActionEvent event) {

    }

}
