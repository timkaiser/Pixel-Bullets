import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
public class ProjektilExplosiv extends Projektil{
    private int lebensZeit = 1000;
    private double neuX,neuY;
    private int radius;
    public ProjektilExplosiv(double x, double y, int breite, int hoehe, double speedX, double speedY, double reichweite,double schaden, Spieler spieler){
        super(x,y,breite,hoehe,speedX,speedY,reichweite,schaden,spieler);
        this.radius = 7; 
    }

    public void bewegungBerechnen(){
        x+=speedX;
        y+=speedY;

        int r = radius;
        if(element=="Explosiv")
            r+=3;
        else if(element=="Verfolgung")
            verfolgen();

        reichweite -= Math.abs(speedX);
        reichweite -= Math.abs(speedY);
        if(reichweite<=0){
            new Explosion(x-Math.signum(speedX),y-Math.signum(speedY),r,150,besitzer);
            Objektmanager.getProjektile().remove(this);
        }else if(kollidiert()){
            new Explosion(x-Math.signum(speedX),y-Math.signum(speedY),r,150,besitzer);
            Objektmanager.getProjektile().remove(this);
        }

        Spieler sp = kollidiertMitSpieler();
        NPC npc = kollidiertMitNPC();
        if(sp != null){
            if(sp.getMomentanLeben()-schaden<=0){
                besitzer.killcounterErhoehen();
            }
            new Explosion(x-Math.signum(speedX),y-Math.signum(speedY),r,150,besitzer);
        }    
        if(npc != null){
            if(npc.getMomentanLeben()-schaden<=0){
                besitzer.killcounterErhoehen();
            }
            new Explosion(x-Math.signum(speedX),y-Math.signum(speedY),r,150,besitzer);
        }
    }

}