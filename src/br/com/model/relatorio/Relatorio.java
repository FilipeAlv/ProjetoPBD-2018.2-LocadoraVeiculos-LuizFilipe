package br.com.model.relatorio;

import java.io.InputStream;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {
	public void gerarRelatorio(List<? extends Object> clientes, String caminho) {
		try {

			InputStream arq = getClass().getResourceAsStream(caminho);
			JasperReport rep = JasperCompileManager.compileReport(arq);
			JasperPrint print = JasperFillManager.fillReport(rep, null, new JRBeanCollectionDataSource(clientes));
			JasperViewer.viewReport(print, false);		

		} catch (JRException e) {
			e.printStackTrace();
		}


	}
}
