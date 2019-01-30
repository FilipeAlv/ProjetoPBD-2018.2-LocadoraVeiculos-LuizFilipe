package br.com.util;

import java.security.InvalidKeyException;

import br.com.model.beans.Pessoa;
import br.com.model.dao.DAOPessoa;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Util {
	public static final String SENHA_PADRAO = "PBDLF#55NH";
	public static boolean SCRIPT = false;
	
	public static String gerarCodigo(Pessoa pessoa) {
		String codigo = (pessoa.getNome().substring(0,3)).toUpperCase();
		codigo=codigo+"-"+(DAOPessoa.getInstace().findMaxId()+1);
		return codigo;
		
	}
	
    public static String removerCaracteres(String conteudo) {
        conteudo = conteudo.replaceAll("\\.", "");
        conteudo = conteudo.replaceAll("-", "");
        conteudo = conteudo.replaceAll("/", "");
        return conteudo;
    }
	
	public static String subNome(Pessoa pessoa) {
		String codigo = (pessoa.getNome().substring(0,3)).toUpperCase();
		return codigo;
		
	}
	
	public static class Criptografia {

		private static char[] key = "cadeacarapaca".toCharArray();
		private static int[] sbox;
		private static final int SBOX_LENGTH = 256;
		private static final int TAM_MIN_CHAVE = 5;

		public static String decriptografa(final char[] msg) {
			return new String(criptografa(msg));
		}

		public static char[] criptografa(final char[] msg) {
			sbox = initSBox(key);
			char[] code = new char[msg.length];
			int i = 0;
			int j = 0;
			for (int n = 0; n < msg.length; n++) {
				i = (i + 1) % SBOX_LENGTH;
				j = (j + sbox[i]) % SBOX_LENGTH;
				swap(i, j, sbox);
				int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGTH];
				code[n] = (char) (rand ^ (int) msg[n]);
			}
			return code;
		}

		private static int[] initSBox(char[] key) {
			int[] sbox = new int[SBOX_LENGTH];
			int j = 0;

			for (int i = 0; i < SBOX_LENGTH; i++) {
				sbox[i] = i;
			}

			for (int i = 0; i < SBOX_LENGTH; i++) {
				j = (j + sbox[i] + key[i % key.length]) % SBOX_LENGTH;
				swap(i, j, sbox);
			}
			return sbox;
		}

		private static void swap(int i, int j, int[] sbox) {
			int temp = sbox[i];
			sbox[i] = sbox[j];
			sbox[j] = temp;
		}

		public void setKey(String key) throws InvalidKeyException {
			if (!(key.length() >= TAM_MIN_CHAVE && key.length() < SBOX_LENGTH)) {
				throw new InvalidKeyException("Tamanho da chave deve ser entre "
						+ TAM_MIN_CHAVE + " e " + (SBOX_LENGTH - 1));
			}

			Criptografia.key = key.toCharArray();
		}

	}
	
	
	
	public static class Mascarar {

	    public static void somenteNumeroInteiro(TextField textField) {

	        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            if (!newValue.matches("\\d*")) {
	                textField.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	        });

	    }

	    public static void somenteNumero(TextField textField) {

	        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            newValue = newValue.replaceAll(",", ".");
	            if (newValue.length() > 0) {
	                try {
	                    Double.parseDouble(newValue);
	                    textField.setText(newValue.replaceAll(",", "."));
	                } catch (Exception e) {
	                    textField.setText(oldValue);
	                }
	            }
	        });

	    }

	    public static void Data(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("0123456789".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if (event.getCharacter().trim().length() == 0) { 

	                if (textField.getText().length() == 3) {
	                    textField.setText(textField.getText().substring(0, 2));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 6) {
	                    textField.setText(textField.getText().substring(0, 5));
	                    textField.positionCaret(textField.getText().length());
	                }

	            } else { 

	                if (textField.getText().length() == 10) {
	                    event.consume();
	                }

	                if (textField.getText().length() == 2) {
	                    textField.setText(textField.getText() + "/");
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 5) {
	                    textField.setText(textField.getText() + "/");
	                    textField.positionCaret(textField.getText().length());
	                }

	            }
	        });

	        textField.setOnKeyReleased((KeyEvent evt) -> {

	            if (!textField.getText().matches("\\d/*")) {
	                textField.setText(textField.getText().replaceAll("[^\\d/]", ""));
	                textField.positionCaret(textField.getText().length());
	            }
	        });

	    }

	    public static void Data(DatePicker datePicker) {

	        datePicker.getEditor().setOnKeyTyped((KeyEvent event) -> {
	            if ("0123456789".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if (event.getCharacter().trim().length() == 0) { // apagando
	                if (datePicker.getEditor().getText().length() == 3) {
	                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 2));
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }
	                if (datePicker.getEditor().getText().length() == 6) {
	                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 5));
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }

	            } else { // escrevendo

	                if (datePicker.getEditor().getText().length() == 10) {
	                    event.consume();
	                }

	                if (datePicker.getEditor().getText().length() == 2) {
	                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "/");
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }
	                if (datePicker.getEditor().getText().length() == 5) {
	                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "/");
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }

	            }
	        });

	        datePicker.getEditor().setOnKeyReleased((KeyEvent evt) -> {

	            if (!datePicker.getEditor().getText().matches("\\d/*")) {
	                datePicker.getEditor().setText(datePicker.getEditor().getText().replaceAll("[^\\d/]", ""));
	                datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	            }
	        });

	    }

	    public static void CPF(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("0123456789".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if (event.getCharacter().trim().length() == 0) { // apagando

	                if (textField.getText().length() == 4) {
	                    textField.setText(textField.getText().substring(0, 3));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 8) {
	                    textField.setText(textField.getText().substring(0, 7));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 12) {
	                    textField.setText(textField.getText().substring(0, 11));
	                    textField.positionCaret(textField.getText().length());
	                }

	            } else { // escrevendo

	                if (textField.getText().length() == 14) {
	                    event.consume();
	                }

	                if (textField.getText().length() == 3) {
	                    textField.setText(textField.getText() + ".");
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 7) {
	                    textField.setText(textField.getText() + ".");
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 11) {
	                    textField.setText(textField.getText() + "-");
	                    textField.positionCaret(textField.getText().length());
	                }

	            }
	        });

	        if (!textField.getText().matches("\\d.-*")) {
	            textField.setText(textField.getText().replaceAll("[^\\d.-]", ""));
	            textField.positionCaret(textField.getText().length());
	        }

	    }

	    public static void CNPJ(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("0123456789".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if (event.getCharacter().trim().length() == 0) { // apagando

	                if (textField.getText().length() == 3) {
	                    textField.setText(textField.getText().substring(0, 2));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 7) {
	                    textField.setText(textField.getText().substring(0, 6));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 11) {
	                    textField.setText(textField.getText().substring(0, 10));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 16) {
	                    textField.setText(textField.getText().substring(0, 15));
	                    textField.positionCaret(textField.getText().length());
	                }

	            } else { // escrevendo

	                if (textField.getText().length() == 18) {
	                    event.consume();
	                }

	                if (textField.getText().length() == 2) {
	                    textField.setText(textField.getText() + ".");
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 6) {
	                    textField.setText(textField.getText() + ".");
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 10) {
	                    textField.setText(textField.getText() + "/");
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 15) {
	                    textField.setText(textField.getText() + "-");
	                    textField.positionCaret(textField.getText().length());
	                }

	            }
	        });

	        if (!textField.getText().matches("\\d./-*")) {
	            textField.setText(textField.getText().replaceAll("[^\\d./-]", ""));
	            textField.positionCaret(textField.getText().length());
	        }

	    }

	    public static void mascaraEmail(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz._-@".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if ("@".equals(event.getCharacter()) && textField.getText().contains("@")) {
	                event.consume();
	            }

	            if ("@".equals(event.getCharacter()) && textField.getText().length() == 0) {
	                event.consume();
	            }
	        });

	    }

	    public static void mascaraTelefone(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("0123456789".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if (event.getCharacter().trim().length() == 0) { // apagando

	                if (textField.getText().length() == 10 && textField.getText().substring(9, 10).equals("-")) {
	                    textField.setText(textField.getText().substring(0, 9));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 9 && textField.getText().substring(8, 9).equals("-")) {
	                    textField.setText(textField.getText().substring(0, 8));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 4) {
	                    textField.setText(textField.getText().substring(0, 3));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if (textField.getText().length() == 1) {
	                    textField.setText("");
	                }

	            } else { //escrevendo

	                if (textField.getText().length() == 14) {
	                    event.consume();
	                }

	                if (textField.getText().length() == 0) {
	                    textField.setText("(" + event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if (textField.getText().length() == 3) {
	                    textField.setText(textField.getText() + ")" + event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if (textField.getText().length() == 8) {
	                    textField.setText(textField.getText() + "-" + event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if (textField.getText().length() == 9 && textField.getText().substring(8, 9) != "-") {
	                    textField.setText(textField.getText() + "-" + event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if (textField.getText().length() == 13 && textField.getText().substring(8, 9).equals("-")) {
	                    textField.setText(textField.getText().substring(0, 8) + textField.getText().substring(9, 10) + "-" + textField.getText().substring(10, 13) + event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }

	            }

	        });

	        textField.setOnKeyReleased((KeyEvent evt) -> {

	            if (!textField.getText().matches("\\d()-*")) {
	                textField.setText(textField.getText().replaceAll("[^\\d()-]", ""));
	                textField.positionCaret(textField.getText().length());
	            }
	        });

	    }

	    public static void Habilitacao(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("0123456789".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if (textField.getText().length() == 11) {
	                event.consume();
	            }

	        });

	        if (!textField.getText().matches("\\d.-*")) {
	            textField.setText(textField.getText().replaceAll("[^\\d.-]", ""));
	            textField.positionCaret(textField.getText().length());
	        }

	    }

	    public static void InscricaoEstadual(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("0123456789".contains(event.getCharacter()) == false) {
	                event.consume();
	            }
	            if (event.getCharacter().trim().length() == 0) {
	                if (textField.getText().length() == 9) {
	                    textField.setText(textField.getText().substring(0, 8));
	                    textField.positionCaret(textField.getText().length());
	                }

	            } else {
	                if (textField.getText().length() == 8) {
	                    textField.setText(textField.getText() + "-");
	                    textField.positionCaret(textField.getText().length());
	                }

	                if (textField.getText().length() == 11) {
	                    event.consume();
	                }
	            }

	        });

	    }

	    public static void Placa(TextField textField) {

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz-".contains(event.getCharacter()) == false) {
	                event.consume();
	            }

	            if (event.getCharacter().trim().length() == 0) { // apagando

	                if (textField.getText().length() == 5) {
	                    textField.setText(textField.getText().substring(0, 4));
	                    textField.positionCaret(textField.getText().length());
	                }

	            } else { // escrevendo

	                if (textField.getText().length() == 9) {
	                    event.consume();
	                }

	                if (textField.getText().length() == 4) {
	                    textField.setText(textField.getText() + "-");
	                    textField.positionCaret(textField.getText().length());
	                }

	            }
	        });

	        if (!textField.getText().matches("\\d.-*")) {
	            textField.setText(textField.getText().replaceAll("[^\\d.-]", ""));
	            textField.positionCaret(textField.getText().length());
	        }

	    }

	}
}