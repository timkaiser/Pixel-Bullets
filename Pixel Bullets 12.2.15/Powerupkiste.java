import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;

public class Powerupkiste extends Hindernis{    
    private boolean einsammelbar;
    private static boolean deaktiviert;
    private String schrift="";
    private int schriftcounter;
    public Powerupkiste(double x, double y){
        super(null,x,y,2,3);
        // Objektmanager.add(this);
    }

    public boolean kollision(Rectangle r, Projektil p ){
        return false;
    }

    public void berechnen(){
        if(!deaktiviert && (int)(Math.random()*300)==1){
            einsammelbar = true;
        }

        schriftcounter -= 25;
    }

    public boolean kollision(Rectangle r,Spieler s){
        if(einsammelbar && super.kollision(r)){
            einsammelbar = false;
            int z = (int)(Math.random()*4);
            switch (z){
                case 1:
                s.setMomentanLeben(s.getMaximalLeben());
                schrift = "Heilung";
                break;
                case 2:
                case 3:
                schrift = waffenupgrade(s);
                break;
                default:
                s.setGeschwindigkeit(s.getGeschwindigkeit()+0.05);
                schrift = "GeschwindigkeitsUpgrade";
                break;
            }

            schriftcounter = 1000;
        }
        return false;
    }

    private String waffenupgrade(Spieler spieler){
        Waffe waffe = spieler.getWaffe();
        int z = (int)(Math.random()*3);
        if(z==0){
            return waffe.schadensUpgrade();
        }else if(z==1){
            return waffe.reichweiteUpgrade();
        }else{
            return waffe.schussgeschwindigkeitsUpgrade();
        }
    }



    public static void aktivieren(){
        deaktiviert = false;
    }

    public static void deaktivieren(){
        deaktiviert = true;
    }

    public void zeichnen(Graphics g, JPanel panel){
        if(einsammelbar){
            g.setColor(new Color(0,200,0));
            g.fillRect((int) x*genauigkeit, (int) y*genauigkeit,2*genauigkeit,2*genauigkeit);
            g.setColor(new Color(0,110,0));
            g.fillRect((int) (x)*genauigkeit, (int) (y+2)*genauigkeit,2*genauigkeit,1*genauigkeit);
        }
        if(schriftcounter>0){
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.setColor(Color.BLACK);
            g.drawString(schrift,(int)(x*genauigkeit)-50,(int)(y*genauigkeit));
        }
    }
}
