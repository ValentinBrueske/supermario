package src;

import java.awt.*;

/**
 * The class Figur fürs SuperMario-Spiel
 *
 * @author p6majo
 * @version 2020-11-25
 */
public abstract class Figur {

    /*
     *************************************************
     ***           physikalische Parameter  **********
     *************************************************
     */


    private double g = 2; //Fallbeschleungigung, gibt an, wie schnell eine Figur nach unten faellt
    private double dt = 0.05;//Zeitintervall;
    private double sprungV = 3.; //Sprunggeschwindigkeit
    private double laufV = 3.; //Laufgeschwindigkeit

    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */


    public enum Bewegung {rechts, links, springen, fallen, nichtfallen};

    private double x;
    private double y;
    private Bewegung bewegung;
    private Spiel spiel;

    private double vx;
    private double vy;

    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Figur(Spiel pSpiel){
    spiel = pSpiel;
}

    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getVX(){return vx;}
    public double getVY(){return vy;}

    public Bewegung getBewegung(){ return bewegung;}
    public Spiel getSpiel() { return spiel; }

    /*
     ***********************************************
     ***           Setters              ************
     ***********************************************
     */


    public void setG(double g) {
        this.g = g;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public void setSprungV(double sprungV) {
        this.sprungV = sprungV;
    }

    public void setLaufV(double laufV) {
        this.laufV = laufV;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public void setBewegung(Bewegung pBewegung) {
        switch(pBewegung){
            case rechts:
                vx = laufV;
                break;
            case links:
                vx = -laufV;
                break;
            case springen:
                vy = -sprungV;
                bewegung = Bewegung.fallen; //starte den freien Fall nach dem Sprung
                break;
            case fallen:
                break;
            case nichtfallen:
                vy = 0;
                break;
        }
    }

    /*
     ***********************************************
     ***           Public methods       ************
     ***********************************************
     */

    /**
     * Hier wird die allgemeine Bewegung der Figuren festgelegt
     */
    public  void act(){
        x=x+vx*dt;
        vx = vx*0.9; //Luftwiderstand, eventuell muss dass nur in der Spielerklasse implementiert
        //werden, damit die Pilze nicht abgebremst werden.
        if (bewegung==Bewegung.fallen) {
            y = y + vy * dt;
            vy = vy + g * dt;
        }
    };

    /**
     * Diese Transformation bewegt die Figur in die andere Richtung
     *  und reduziert die Geschwindigkeit auf pAnteil
     * @param pAnteil
     */
    public void reflektiere(double pAnteil){
        vx = vx*(-pAnteil);
    }

    public void stoppeFall(){
        vy = 0;
        bewegung = Bewegung.nichtfallen;
    }

    public void starteFall(){
        bewegung = Bewegung.fallen;
    }

    public abstract void draw(Graphics2D pScreen, int pZellGroesse,double pVerschiebeX);
    /*
     ***********************************************
     ***           Private methods      ************
     ***********************************************
     */


    /*
     ***********************************************
     ***           Overrides            ************
     ***********************************************
     */

    @Override
    public String toString() {
        return "@("+x+"|"+y+") mit Bewegung: "+bewegung;
    }



}
