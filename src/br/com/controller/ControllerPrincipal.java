package br.com.controller;


import br.com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerPrincipal {

    @FXML
    private ImageView btnClose;
    
    @FXML
    private MenuItem menuNovaReserva;

    @FXML
    void actionAddReserva(ActionEvent event) {
    	Main.novaTela("NovaReserva");
    }

    @FXML
    void actionClose(MouseEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void actionLogout(MouseEvent event) {
    	Main.alterarTela("Login");
    }

}
