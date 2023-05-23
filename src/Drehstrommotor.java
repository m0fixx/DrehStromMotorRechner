public class Drehstrommotor {
    
    private int drehzahl;
    private double drehmoment;
    private int spannung;
    private double strom;
    private double leistungsfaktor;
    private double ubersetzungsverhaltnis;

    public Drehstrommotor(){

    }

    public Drehstrommotor(int drehzahl, double drehmoment, int spannung, double strom, double leistungsfaktor, double ubersetzungsverhaltnis){
        this.drehzahl = drehzahl;
        this.drehmoment = drehmoment;
        this.spannung = spannung;
        this.strom = strom;
        this.leistungsfaktor = leistungsfaktor;
        this.ubersetzungsverhaltnis = ubersetzungsverhaltnis;
        
    }

    //berechnet die Leistungsaufnahme in kW
    private double berechneLeistungsaufnahme(){
        double leistungsaufnahme = Math.sqrt(3)*spannung*strom*leistungsfaktor;
        leistungsaufnahme = leistungsaufnahme/1000;
        leistungsaufnahme = Math.round(leistungsaufnahme*1000.0)/1000.0;
        return leistungsaufnahme;
    }

    public double getLeistungsaufnahme(){
        double leistungsaufnahme = berechneLeistungsaufnahme();
        return leistungsaufnahme;
    }

    //berechnet die Leistungsabgabe in kW
    private double berechneLeistungsabgabe(){
        double leistungsabgabe = (drehmoment*(drehzahl/ubersetzungsverhaltnis))/9549;
        leistungsabgabe = Math.round(leistungsabgabe*1000.0)/1000.0;
        return leistungsabgabe;
    }

    public double getLeistungsabgabe(){
        double leistungsabgabe = berechneLeistungsabgabe();
        return leistungsabgabe;
    }

    //berechnet die Verlustleistung in kW
    private double berechneVerlustleistung(){
        double verlustleistung = getLeistungsaufnahme() - getLeistungsabgabe();
        verlustleistung = Math.round(verlustleistung*1000.0)/1000.0;
        return verlustleistung;
    }

    public double getVerlustleistung(){
        double verlustleistung = berechneVerlustleistung();
        return verlustleistung;
    }

    private double berechneWirkungsgrad(){
        double wirkungsgrad = getLeistungsabgabe()/getLeistungsaufnahme();
        wirkungsgrad = wirkungsgrad*100;
        wirkungsgrad = Math.round(wirkungsgrad*100.0)/100.0;
        return wirkungsgrad;
    }

    public double getWirkungsgrad(){
        double wirkungsgrad = berechneWirkungsgrad();
        return wirkungsgrad;
    }


    //Get- Set-Methoden:
    public int getDrehzahl() {
        return this.drehzahl;
    }

    public void setDrehzahl(int drehzahl) {
        this.drehzahl = drehzahl;
    }

    public double getDrehmoment() {
        return this.drehmoment;
    }

    public void setDrehmoment(double drehmoment) {
        this.drehmoment = drehmoment;
    }

    public int getSpannung() {
        return this.spannung;
    }

    public void setSpannung(int spannung) {
        this.spannung = spannung;
    }

    public double getStrom() {
        return this.strom;
    }

    public void setStrom(double strom) {
        this.strom = strom;
    }

    public double getLeistungsfaktor() {
        return this.leistungsfaktor;
    }

    public void setLeistungsfaktor(double leistungsfaktor) {
        this.leistungsfaktor = leistungsfaktor;
    }

    public double getUbersetzungsverhaltnis() {
        return this.ubersetzungsverhaltnis;
    }

    public void setUbersetzungsverhaltnis(double ubersetzungsverhaltnis) {
        this.ubersetzungsverhaltnis = ubersetzungsverhaltnis;
    }

}
