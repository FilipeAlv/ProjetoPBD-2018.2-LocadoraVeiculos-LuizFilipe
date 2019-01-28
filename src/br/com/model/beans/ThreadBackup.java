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
        Calendar calHoraAtual = Calendar.getInstance();
        Calendar calHoraBack = Calendar.getInstance();
        
        hora.set(Calendar.HOUR, 7);
        hora.set(Calendar.MINUTE, 22);
        calHoraBack.setTime(hora.getTime());

        int horaBack = calHoraBack.get(Calendar.HOUR_OF_DAY);
        int minutoBack = calHoraBack.get(Calendar.MINUTE);

        try {
            while (true) {

                Date dataAtual = new Date();
                calHoraAtual.setTime(dataAtual);

                int horaAtual = calHoraAtual.get(Calendar.HOUR_OF_DAY);
                int minutoAtual = calHoraAtual.get(Calendar.MINUTE);

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