package br.com.controller;
import br.com.model.beans.Marca;
import br.com.model.dao.DAOMarca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerNovaMarca {

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField fdNome;

	private boolean edit;

	private Marca m = new Marca();

    @FXML
    void actionSalvar(ActionEvent event) {
    	if(validarMarca()) {
    		m.setNome(fdNome.getText().toString());
    		DAOMarca.getInstance().saveOrUpdate(m);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso" );
    		alert.setContentText("Esta Marca foi salvo com successo!");
    		alert.show();
    		new ControllerNovoModelo().carregarCombo();
    		
    	}
    }
    
	private boolean validarMarca() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Cadastro de Marca" );
		alert.setHeaderText("Erro ao Salvar Marca");
		if(!validarCampos()) {
			alert.setContentText("Preencha todos os campos obrigatorios");
    		alert.show();
    		return false;
		}else if(DAOMarca.getInstance().findByNome(fdNome.getText().toString())!=null && !edit) {
			alert.setContentText("Marca Já Cadastrada");
    		alert.show();
    		return false;
		}else {
			return true;
		}
}
	
	public void carregarEditar(Marca marca) {
		this.m  = marca;
		edit = true;
		fdNome.setText(marca.getNome());		
	}
    
    private boolean validarCampos() {
    	if(fdNome.getText().length()==0)
    		return false;
    	return true;
    }

}
