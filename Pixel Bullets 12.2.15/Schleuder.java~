import java.awt.*;
import javax.swing.*;

import java.util.*;
public class Schleuder extends Items{
    private int maxCooldown,cooldown;
    public Schleuder(String name,Spieler spieler,int cooldown){
        super(null,name,spieler);
        this.cooldown = cooldown;
        maxCooldown = cooldown;
    }

    public void aktivieren(boolean aktivieren){
        if(aktivieren){
            if(cooldown <= 0){
                int xBeschleunigung = 0,yBeschleunigung = 0;
                int geschwindigkeit = 15;
                if(besitzer.getRechts()){
                    xBeschleunigung = geschwindigkeit;
                    yBeschleunigung = 0;
                }
                else if(besitzer.getLinks()){
                    xBeschleunigung = -geschwindigkeit;
                    yBeschleunigung = 0;
                }
                else if(besitzer.getOben()){
                    xBeschleunigung = 0;
                    yBeschleunigung = -geschwindigkeit;
                }
                else if(besitzer.getUnten()){
                    xBeschleunigung = 0;
                    yBeschleunigung = geschwindigkeit;
                }
                besitzer.getContainer().projektilHinzufuegen(new Stein((int)(besitzer.getX()+(besitzer.getBreite()*0.5)),(int)(besitzer.getY()+(besitzer.getHoehe()*0.5)),xBeschleunigung,yBeschleunigung,besitzer.getContainer().getProjektile(),besitzer));
                cooldown = maxCooldown;
            }
            else{

                cooldown-=25;

            }
        }
        else{
            if(cooldown>0){
                cooldown -= 25;
            }
        }
    }

    public void zeichnen(Graphics g,double zoomfaktor,JPanel panel){

    }
}