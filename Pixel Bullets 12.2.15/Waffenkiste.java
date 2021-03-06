import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;

public class Waffenkiste extends Hindernis{    
    private boolean einsammelbar;
    private static boolean deaktiviert;
    private String schrift="";
    private int schriftcounter;
    public Waffenkiste(double x, double y){
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
            
            s.getWaffe().reset();

            boolean hatSchon = true;
            do{
                int z = (int)(Math.random()*7);
                if(z==0 && !s.getWaffenTyp().equals("Pistole")){
                    s.setWaffe(new Pistole(s));
                    hatSchon = false;
                    schrift = "Pistole";
                }else if(z==1 && !s.getWaffenTyp().equals("MG")){
                    s.setWaffe(new MG(s));
                    hatSchon = false;
                    schrift = "   MG  ";
                }else if(z==2 && !s.getWaffenTyp().equals("Shotgun")){
                    s.setWaffe(new Shotgun(s));
                    hatSchon = false;
                    schrift = "Shotgun";
                }else if(z==3 && !s.getWaffenTyp().equals("Bazooka")){
                    s.setWaffe(new Bazooka(s));
                    hatSchon = false;
                    schrift = "Bazooka";
                }else if(z==4 && !s.getWaffenTyp().equals("Sniper")){
                    s.setWaffe(new Sniper(s));
                    hatSchon = false;
                    schrift = "Sniper";
                }else if(z==5 && !s.getWaffenTyp().equals("DoppelPistolen")){
                    s.setWaffe(new DoppelPistolen(s));
                    hatSchon = false;
                    schrift = "Doppelte Pistolen";
                }else if(z==6 && !s.getWaffenTyp().equals("Minen")){
                    s.setWaffe(new Minenleger(s));
                    hatSchon = false;
                    schrift = "Minen";
                }
            }while(hatSchon);
            schriftcounter = 1000;
        }
        return false;
    }

    public static void aktivieren(){
        deaktiviert = false;
    }

    public static void deaktivieren(){
        deaktiviert = true;
    }

    public void zeichnen(Graphics g, JPanel panel){
        if(einsammelbar){
            g.setColor(Color.CYAN);
            g.fillRect((int) x*genauigkeit, (int) y*genauigkeit,2*genauigkeit,2*genauigkeit);
            g.setColor(new Color(0,160,230));
            g.fillRect((int) (x)*genauigkeit, (int) (y+2)*genauigkeit,2*genauigkeit,1*genauigkeit);
        }
        if(schriftcounter>0){
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.setColor(Color.BLACK);
            g.drawString(schrift,(int)(x*genauigkeit)-50,(int)(y*genauigkeit));
        }
    }
}
