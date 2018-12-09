package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.main.Main;
import br.com.model.beans.Categoria;
import br.com.model.beans.ValorLocacao;
import br.com.model.dao.DAOCategoria;
import br.com.model.dao.DAOValorLocacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class ControllerNovoValor implements Initializable{

    @FXML
    private ImageView btnClose;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField fdValor;

    @FXML
    private ComboBox<String> cbTipoLocacao;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private Button btnSalvar;

    @FXML
    void actionAddCategoria(ActionEvent event) {

    }

    @FXML
    void actionClose(KeyEvent event) {
    	Main.stageAddValorLocacao.close();
    }

    @FXML
    void actionSalvar(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("CADASTRO VALOR LOCAÇÃO");
    	if(validarCampos()) {
	    	Categoria categoria = DAOCategoria.getInstance().findByNome(cbCategoria.getValue());
	    	ValorLocacao vl = new ValorLocacao(Double.parseDouble(fdValor.getText()), categoria, cbTipoLocacao.getValue());
	    	DAOValorLocacao.getInstance().saveOrUpdate(vl);
	    	try {
				DAOValorLocacao.getInstance().findById(ValorLocacao.class, vl.getId());
				alert.setContentText("Valor Adicionado Com Sucesso");
				alert.show();
				Main.stageAddValorLocacao.close();
			} catch (Exception e) {
				alert.setContentText("Erro ao adicionar valor");
				alert.show();
			}
			
		}else {
			alert.setContentText("Preencha todos os campos");
			alert.show();
		}
    }

	private boolean validarCampos() {
		if(
				fdValor.getText()==null||
				cbCategoria.getValue()==null||
				cbTipoLocacao.getValue()==null
				) {

			return false;

		}
		return true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Categoria> categorias = DAOCategoria.getInstance().findAll();
		ObservableList<String> ob = FXCollections.observableArrayList();
		for(Categoria categoria : categorias) {
			ob.add(categoria.getNome());
		}
		cbCategoria.setItems(ob);
		ob = FXCollections.observableArrayList();

		ob.add("KMLivre");
		ob.add("KMControle");
		cbTipoLocacao.setItems(ob);
		
	}

}
