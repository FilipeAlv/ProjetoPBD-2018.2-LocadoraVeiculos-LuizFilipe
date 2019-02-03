package br.com.controller;
import br.com.main.Main;
import br.com.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerPrincipal{

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
	private MenuItem menuBuscarCategoria;

	@FXML
	private MenuItem menuBuscarFilial;

	@FXML
	private MenuItem menuBuscarMotorista;

	@FXML
	private MenuItem menuBuscarCliente;

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
	private MenuItem menuLogCliente;

	@FXML
	private MenuItem menuLogReserva;

	@FXML
	private MenuItem menuLogLocacao;

	@FXML
	void actionMenuBuscar(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			if (event.getSource() == menuBuscarCategoria) {
				tela = FXMLLoader.load(getClass().getResource("../view/Categoria.fxml"));
			}else if (event.getSource() == menuBuscarCliente) {
				tela = FXMLLoader.load(getClass().getResource("../view/Cliente.fxml"));
			}else if(event.getSource() == menuBuscarFilial) {
				tela = FXMLLoader.load(getClass().getResource("../view/Filial.fxml"));
			}else if(event.getSource() == menuBuscarMotorista) {
				tela = FXMLLoader.load(getClass().getResource("../view/Motorista.fxml"));
			}

			scene = new Scene(tela);
			stage = new Stage();
			stage.setOnCloseRequest(e -> stage.close());
			stage.initModality(Modality.APPLICATION_MODAL);
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
	void actionMenuCadastro(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			if (event.getSource() == menuCadastroCliente) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovoCliente.fxml"));
			}else if (event.getSource() == menuCadastroCategoria) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovaCategoria.fxml"));
			}else if(event.getSource() == menuCadastroFilial) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovaFilial.fxml"));

			}else if(event.getSource() == menuCadastroMotorista) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovoMotorista.fxml"));
			}

			scene = new Scene(tela);
			stage = new Stage();
			stage.setOnCloseRequest(e -> stage.close());
			stage.initModality(Modality.APPLICATION_MODAL);
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
	void actionMenuFuncionario(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			if (event.getSource() == menuVerFuncionario) {
				tela = FXMLLoader.load(getClass().getResource("../view/Funcionario.fxml"));
			}else if (event.getSource() == menuNovoFuncionario) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovoFuncionario.fxml"));
			}
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
	void actionMenuLocacao(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			if (event.getSource() == menuVerLocacao) {
				tela = FXMLLoader.load(getClass().getResource("../view/Locacao.fxml"));
			}else if (event.getSource() == menuLocacaoSemReserva) {
				tela = FXMLLoader.load(getClass().getResource("../view/LocacaoSemReserva.fxml"));
			}else if (event.getSource() == menuConfigLocacao) {
				tela = FXMLLoader.load(getClass().getResource("../view/ConfigLocacao.fxml"));
			}
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
	void actionMenuLog(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			if (event.getSource() == menuLogCliente) {
				tela = FXMLLoader.load(getClass().getResource("../view/LogCliente.fxml"));
			}else if (event.getSource() == menuLogLocacao) {
				tela = FXMLLoader.load(getClass().getResource("../view/LogLocacao.fxml"));
			}else if (event.getSource() == menuLogReserva) {
				tela = FXMLLoader.load(getClass().getResource("../view/LogReservas.fxml"));
			}
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
	void actionMenuReserva(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			if (event.getSource() == menuVerReservas) {
				tela = FXMLLoader.load(getClass().getResource("../view/Reserva.fxml"));
			}else if (event.getSource() == menuNovaReserva) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovaReserva.fxml"));
			}
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
	void actionMenuVeiculo(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			if (event.getSource() == menuNovoVeiculo) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovoVeiculo.fxml"));
			}else if (event.getSource() == menuVerVeiculo) {
				tela = FXMLLoader.load(getClass().getResource("../view/Veiculos.fxml"));
			}else if (event.getSource() == menuNovaMarca) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovaMarca.fxml"));
			}else if (event.getSource() == menuNovoModelo) {
				tela = FXMLLoader.load(getClass().getResource("../view/NovoModelo.fxml"));
			}
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
	void actionClose(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void actionConfigLocacao(ActionEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/ConfigLocacao.fxml"));
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
	void actionConfigSis(MouseEvent event) {
		Pane tela = null;
		Scene scene;
		Stage stage;
		try {
			tela = FXMLLoader.load(getClass().getResource("../view/Configuracao.fxml"));
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
	void actionLogout(MouseEvent event) {
		Main.alterarTela("Login");
		Util.SCRIPT=false;
	}

}
