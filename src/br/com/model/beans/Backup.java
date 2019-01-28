package br.com.model.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class Backup implements Initializable {

	private static final String caminho = "E:\\Documents\\FACULDADE\\DISCIPLINAS\\5 PERIODO\\PBD\\PROJETO\\ProjetoPBD-2018.2-LocadoraVeiculos-LuizFilipeAlves/backup";
	private static final String senha = "filipe200715";
	private static final String banco = "pbd";
	private static final String caminhoDump = "C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_dump.exe";

	public Backup() {

	}

	public static void realizaBackup(){
		final List<String> comandos = new ArrayList<>();

		List<String> lista = new ArrayList<>();
		File diretorio = new File(caminho);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File fList[] = diretorio.listFiles();

		if (fList.length == 0) {
			comandos.add(caminhoDump);


			comandos.add("-h");
			comandos.add("localhost");
			comandos.add("-p");
			comandos.add("5432");
			comandos.add("-U");
			comandos.add("postgres");
			comandos.add("-F");
			comandos.add("c");
			comandos.add("-b");
			comandos.add("-v");
			comandos.add("-f");

			comandos.add(caminho + "\\" + 1 + " " + getDateTime(new Date()) + ".backup"); 
			comandos.add(banco);
			ProcessBuilder pb = new ProcessBuilder(comandos);

			pb.environment().put("PGPASSWORD", senha);
			try {
				final Process process = pb.start();

				final BufferedReader r = new BufferedReader(
						new InputStreamReader(process.getErrorStream()));
				String line;

				line = r.readLine();

				while (line != null) {
					System.err.println(line);
					line = r.readLine();
				}
				r.close();


				process.waitFor();

				process.destroy();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {

			for (int i = 0; i < fList.length; i++) {
				lista.add(fList[i].getName());
			}

			char ultimoBack = Collections.max(lista).charAt(0);


			comandos.add(caminhoDump);  
			comandos.add("-h");
			comandos.add("localhost");
			comandos.add("-p");
			comandos.add("5432");
			comandos.add("-U");
			comandos.add("postgres");
			comandos.add("-F");
			comandos.add("c");
			comandos.add("-b");
			comandos.add("-v");
			comandos.add("-f");

			comandos.add(caminho + "\\" + (Character.getNumericValue(ultimoBack) + 1) + " " + getDateTime(new Date()) + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
			comandos.add(banco);
			ProcessBuilder pb = new ProcessBuilder(comandos);

			pb.environment().put("PGPASSWORD", senha);

			try {
				final Process process = pb.start();

				final BufferedReader r = new BufferedReader(
						new InputStreamReader(process.getErrorStream()));
				String line = r.readLine();
				while (line != null) {
					System.err.println(line);
					line = r.readLine();
				}
				r.close();

				process.waitFor();
				process.destroy();
				removerBackup();
				System.err.println("Fez Back");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public static void removerBackup() {
		List<String> lista = new ArrayList<String>(9);

		File diretorio = new File(caminho);
		File fList[] = diretorio.listFiles();

		if (fList.length != 0 && fList.length > 8) {

			for (File fList1 : fList) {

				lista.add(fList1.getName());
			}


			String x = Collections.min(lista);
			lista.remove(Collections.min(lista)); 

			String nome = caminho + "/" + x;
			File f = new File(nome);
			f.delete();

			int m = 0;
			String recebe;
			String result;
			while (m < lista.size()) {

				recebe = lista.get(m);

				result = recebe.replace(" ", " ").substring(1);


				m++;
				result = m + result;

				File file = new File(caminho + "/" + recebe);

				File file2 = new File(caminho + "/" + result);

				boolean success = file.renameTo(file2);
				if (!success) {
					JOptionPane.showMessageDialog(null, "Falha no momento de renomear");
				}

			}

		}

	}

	private static String getDateTime(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HHmm");

		return dateFormat.format(date);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}



}
