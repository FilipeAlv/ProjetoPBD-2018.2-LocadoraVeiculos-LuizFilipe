package br.com.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerPrincipal {

    @FXML
    private ImageView btnClose;

    @FXML
    void actionClose(MouseEvent event) {
    	System.exit(0);
    }

}
