package br.com.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import br.com.model.beans.Financeiro;
import br.com.model.dao.DAOFinanceiro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerMovimentacao implements Initializable{
	

    @FXML
    private TableView<Financeiro> tbFinanceiro;

    @FXML
    private TableColumn<Financeiro, Date> dataCol;

    @FXML
    private TableColumn<Financeiro, String> descricaoCol;

    @FXML
    private TableColumn<Financeiro, String> tipoCol;

    @FXML
    private TableColumn<Financeiro, Double> valorCol;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAtualizar;

    @FXML
    private DatePicker fdDe;

    @FXML
    private DatePicker fdAte;
    
    @FXML
    private TextField fdTotal;

	public static TableView<Financeiro> tb;
	public static ObservableList<Financeiro> ob;
	 private static TextField fdValor;
    @FXML
    void actionAdd(ActionEvent event) {
    	
    	Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/NovaMovimentacao.fxml"));
			scene = new Scene(tela);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(e -> stage.close());
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro de Exibição");
			alert.setContentText("Não foi possível exibir a tela. Por favor entre em contato com a equipe de desenvolvimento.");
			alert.setHeaderText("Tela não encontrada");
			e.printStackTrace();
		}

    }

    @FXML
    void actionAtualizar(ActionEvent event) {
    	carregarTabela(DAOFinanceiro.getInstance().findAll());
    }

    @FXML
    void actionBuscar(ActionEvent event) {
    	Date de, ate;
    	de = Date.from(fdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	ate = Date.from(fdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	
    	carregarTabela(DAOFinanceiro.getInstance().findByDate(de, ate));
			
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dataCol.setCellValueFactory( 
				new PropertyValueFactory<>("dia"));
		descricaoCol.setCellValueFactory( 
				new PropertyValueFactory<>("descricao"));
		tipoCol.setCellValueFactory( 
				new PropertyValueFactory<>("tipo"));
		valorCol.setCellValueFactory( 
				new PropertyValueFactory<>("valor"));
		
		tb=tbFinanceiro;
		fdValor = fdTotal;
		carregarTabela(DAOFinanceiro.getInstance().findAll());
		
		


	}

	public static void carregarTabela(List<Financeiro> movimentacoes) {
		Double total = 0.0;
		ob = FXCollections.observableArrayList();
		ob.addAll(movimentacoes);
		if (ob.size()>0) 
			tb.setItems(ob);
		
		for (Financeiro financeiro : movimentacoes) {
			if (financeiro.getTipo().equals("Entrada"))
				total+=financeiro.getValor();
			else 
				total-=financeiro.getValor();
			
		}
		fdValor.setText(total.toString());
	}

}
