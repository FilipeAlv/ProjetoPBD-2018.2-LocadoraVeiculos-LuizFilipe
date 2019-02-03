package br.com.model.beans;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.concurrent.Task;

public class ThreadBackup extends Task<Integer> {

    public ThreadBackup() {

    }

    @Override
    protected Integer call() {
    	Calendar hora = new GregorianCalendar();
        Calendar HoraAtual = Calendar.getInstance();
        Calendar HoraBack = Calendar.getInstance();
        
        if(Config.getInstace().getHoraBackup()!=null) {
        	HoraBack.setTime(Config.getInstace().getHoraBackup());
        }else {
	        hora.set(Calendar.HOUR, 7);
	        hora.set(Calendar.MINUTE, 22);
	        HoraBack.setTime(hora.getTime());
        }

        int horaBack = HoraBack.get(Calendar.HOUR_OF_DAY);
        int minutoBack = HoraBack.get(Calendar.MINUTE);

        try {
            while (true) {

                Date dataAtual = new Date();
                HoraAtual.setTime(dataAtual);

                int horaAtual = HoraAtual.get(Calendar.HOUR_OF_DAY);
                int minutoAtual = HoraAtual.get(Calendar.MINUTE);

                if (horaBack >= horaAtual && minutoBack >= minutoAtual) {
                    Backup.realizaBackup();
                     
                    break;
                }

                Thread.sleep(5000);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return null;
    }
}