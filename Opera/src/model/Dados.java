package model;

public class Dados {
    private double val1;
    private double val2;
    private String opera;
    private int casasDec;

    public Dados (double val1, double val2, String opera, int casasDec){
        this.val1 = val1;
        this.val2 = val2;
        this.opera = opera;
        this.casasDec = casasDec;
    }

    public double getVal1() {
        return val1;
    }

    public void setVal1(double val1) {
        this.val1 = val1;
    }

    public double getVal2() {
        return val2;
    }

    public void setVal2(double val2) {
        this.val2 = val2;
    }

    public String getOpera() {
        return opera;
    }

    public void setOpera(String opera) {
        this.opera = opera;
    }

    public int getCasasDec() {
        return casasDec;
    }

    public void setCasasDec(int casasDec) {
        this.casasDec = casasDec;
    }

    public double soma (){
        return this.val1 + this.val2;
    }
    public double sub (){
        return this.val1 - this.val2;
    }
    public double mult (){
        return this.val1 * this.val2;
    }
    public double div (){
        return (double) this.val1 / this.val2;
    }

}
