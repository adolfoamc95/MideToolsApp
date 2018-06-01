package com.mide.adolf.socialmide;

import android.util.Log;

import java.io.Serializable;

public class MideObject implements Serializable{

    private int mideId;

    private String nombre;

    private String epoca, año;

    private String horario;

    private String distancia;

    private String tipoR;

    private int desSubida, desBajada;

    private int notaSev, notaOr, notaDiff, notaEsf;

    private int metrosRapel, angPend;

    private String nPasos;

    private String ruta;

    private int checkedIt, checkedDespl, selectedTipo, selectedPasos, selectedRapel, selectedNieve, selectedNievePend, selectedAño, selectedEpoca;

    public MideObject(){}

    public MideObject(int id, String nombre, String año, String ruta){
        this.mideId = id;
        this.nombre = nombre;
        this.año = año;
        this.ruta = ruta;
    }

    public MideObject(int id, String nombre, String horario, String epoca, String año, String distancia, int desSubida, int desBajada, int notaSev, int notaOr, int notaDiff, int notaEsf, int metrosRapel, String nPasos, int angPend, String tipoR) {
        this.mideId = id;
        this.nombre = nombre;
        this.horario = horario;
        this.epoca = epoca;
        this.año = año;
        this.distancia = distancia;
        this.desSubida = desSubida;
        this.desBajada = desBajada;
        this.notaSev = notaSev;
        this.notaOr = notaOr;
        this.notaDiff = notaDiff;
        this.notaEsf = notaEsf;
        this.metrosRapel = metrosRapel;
        this.nPasos = nPasos;
        this.angPend = angPend;
        this.tipoR = tipoR;
    }

    public int getMideId() {
        return mideId;
    }

    public void setMideId(int mideId) {
        this.mideId = mideId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getDesSubida() {
        return desSubida;
    }

    public void setDesSubida(int desSubida) {
        this.desSubida = desSubida;
    }

    public int getDesBajada() {
        return desBajada;
    }

    public void setDesBajada(int desBajada) {
        this.desBajada = desBajada;
    }

    public int getNotaSev() {
        return notaSev;
    }

    public void setNotaSev(int nSelected) {
        if (nSelected ==1 || nSelected ==0) {
            this.notaSev = 1;
        }else if (nSelected == 2 || nSelected == 3){
            this.notaSev = 2;
        }else if (nSelected == 4 || nSelected == 5 || nSelected == 6){
            this.notaSev = 3;
        }else if (nSelected >= 7 && nSelected <=10){
            this.notaSev = 4;
        }else if (nSelected > 10) {
            this.notaSev = 5;
        }
    }

    public int getNotaOr() {
        return notaOr;
    }

    public void setNotaOr(int notaOr) {
        this.notaOr = notaOr;
    }

    public int getNotaDiff() {
        return notaDiff;
    }

    public void setNotaDiff(int notaDiff) {
        this.notaDiff = notaDiff;
    }

    public int getNotaEsf() {
        return notaEsf;
    }

    public void setNotaEsf(int notaEsf) {
        this.notaEsf = notaEsf;
    }

    public int getMetrosRapel() {
        return metrosRapel;
    }

    public void setMetrosRapel(int pos) {
        this.metrosRapel = 5* pos;
    }

    public String getnPasos() {
        return nPasos;
    }

    public void setnPasos(int pos) {
        Log.d("NUMERO DE PASOS", String.valueOf(pos));
        switch (pos) {
            case 0:
                this.nPasos = "";
                break;
            case 1:
                this.nPasos = "II+";
                break;
            case 2:
                this.nPasos = "III";
                break;
            case 3:
                this.nPasos = "III+";
                Log.d("npasos dentro de switch", this.nPasos);
                break;
            case 4:
                this.nPasos = "IV";
                break;
            case 5:
                this.nPasos = "IV+";
                break;
            case 6:
                this.nPasos = "V";
                break;
        }
    }

    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(int opcion) {
        switch (opcion){
            case 0:
                this.epoca = "Verano";
                break;
            case 1:
                this.epoca = "Tres estaciones";
                break;
            case 2:
                this.epoca = "Invierno";
                break;
            case 3:
                this.epoca = "Todo el año";
                break;
        }
    }

    public String getAño() {
        return año;
    }

    public void setAño(int pos) {
        if(pos == 0){
            this.año = "2018";
        }if(pos == 1){
            this.año = "2017";
        }if(pos == 2){
            this.año = "2016";
        }if(pos == 3) {
            this.año = "2015";
        }if(pos == 4) {
            this.año = "2014";
        }if(pos == 5) {
            this.año = "2013";
        }if(pos == 6) {
            this.año = "2012";
        }if(pos == 7) {
            this.año = "2011";
        }if(pos == 8) {
            this.año = "2010";
        }if(pos == 9) {
            this.año = "2009";
        }if(pos == 10) {
            this.año = "2008";
        }if(pos == 11) {
            this.año = "2007";
        }if(pos == 12) {
            this.año = "2006";
        }if(pos == 13) {
            this.año = "2005";
        }if(pos == 14) {
            this.año = "2004";
        }if(pos == 15) {
            this.año = "2003";
        }if(pos == 16) {
            this.año = "2002";
        }if(pos == 17) {
            this.año = "2001";
        }if(pos == 18) {
            this.año = "2000";
        }
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(int distanciaM) {
        Log.d("Distancia:", String.valueOf(distanciaM));
        double duble = distanciaM/1000;
        String distpart = String.valueOf(duble);
        this.distancia = distpart;
    }

    public int getAngPend() {
        return angPend;
    }

    public void setAngPend(int pos) {

        this.angPend = pos*10;
    }

    public String getTipoR() {
        return tipoR;
    }

    public void setTipoR(int pos) {
        switch (pos) {
            case 1:
                this.tipoR = "Ida y vuelta";
                break;
            case 2:
                this.tipoR = "Circular";
                break;
            case 3:
                this.tipoR = "Travesía";
                break;
        }
        this.tipoR = tipoR;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getCheckedIt() {
        return checkedIt;
    }

    public void setCheckedIt(int checkedIt) {
        this.checkedIt = checkedIt;
    }

    public int getCheckedDespl() {
        return checkedDespl;
    }

    public void setCheckedDespl(int checkedDespl) {
        this.checkedDespl = checkedDespl;
    }

    public int getSelectedPasos() {
        return selectedPasos;
    }

    public void setSelectedPasos(int selectedPasos) {
        this.selectedPasos = selectedPasos;
    }

    public int getSelectedRapel() {
        return selectedRapel;
    }

    public void setSelectedRapel(int selectedRapel) {
        this.selectedRapel = selectedRapel;
    }

    public int getSelectedNieve() {
        return selectedNieve;
    }

    public void setSelectedNieve(int selectedNieve) {
        this.selectedNieve = selectedNieve;
    }

    public int getSelectedNievePend() {
        return selectedNievePend;
    }

    public void setSelectedNievePend(int selectedNievePend) {
        this.selectedNievePend = selectedNievePend;
    }

    public int getSelectedAño() {
        return selectedAño;
    }

    public void setSelectedAño(int selectedAño) {
        this.selectedAño = selectedAño;
    }

    public int getSelectedEpoca() {
        return selectedEpoca;
    }

    public void setSelectedEpoca(int selectedEpoca) {
        this.selectedEpoca = selectedEpoca;
    }

    public int getSelectedTipo() {
        return selectedTipo;
    }

    public void setSelectedTipo(int selectedTipo) {
        this.selectedTipo = selectedTipo;
    }
}
