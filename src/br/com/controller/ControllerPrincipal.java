package br.com.controller;

import java.io.IOException;

import br.com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerPrincipal {

    @FXML
    private ImageView btnClose;
    
    @FXML
    private MenuItem menuNovaReserva;

    @FXML
    void actionAddReserva(ActionEvent event) {
    	Pane telaNovaReserva;
		try {
			Stage stage = new Stage();
			telaNovaReserva = FXMLLoader.load(getClass().getResource("../view/NovaReserva.fxml"));
			Scene scene = new Scene(telaNovaReserva);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
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
