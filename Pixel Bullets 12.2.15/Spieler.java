import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.util.*;
import javax.swing.*;

public class Spieler extends Objekt{
    private static int spielerzahl;
    Animation animationLinks, animationRechts, animationOben, animationUnten, animation;
    private BufferedImage bilderGeducktRunter,bilderGeducktRechts,bilderGeducktHoch,bilderGeducktLinks, bildGeduckt; 
    //Status
    protected double horizontaleGeschwindigkeit, vertikaleGeschwindigkeit;
    protected int momentanLeben;
    protected int unverwundbareZeit;
    protected boolean amLeben;
    protected String richtung;
    protected int deathcounter;
    protected int killcounter;
    protected boolean respawn;
    //Eigenschaften
    protected int maximalLeben;    
    protected final double speedNormal = 0.5;
    protected double geschwindigkeit = 0.5;
    protected String name;
    private Waffe waffe;
    private boolean geduckt;

    public Spieler(String name){
        super(10,10,4,2);
        geduckt = false;
        respawn = true;
        amLeben = true;
        spielerzahl+=1;
        if(spielerzahl>4){
            spielerzahl = 1;
        }
        maximalLeben = 100;

        momentanLeben = 100;

        ArrayList<Respawnpunkt> respawnpunkte = Objektmanager.getRespawnpunkte();

        if(respawnpunkte.size()>=spielerzahl){
            respawnpunkte.get(spielerzahl-1).spielerToSpawn(this);
        }

        BufferedImage bild = null;
        try {       bild = (ImageIO.read(getClass().getResource("Spieler"+spielerzahl+".png")));
        } catch (IOException e) {}

        animationUnten = new Animation(bild.getSubimage(0,0,16,5),4,250);
        animationRechts = new Animation(bild.getSubimage(0,5,16,5),4,250);
        animationLinks = new Animation(bild.getSubimage(0,15,16,5),4,250);
        animationOben = new Animation(bild.getSubimage(0,10,16,5),4,250);

        try {       bild = (ImageIO.read(getClass().getResource("Spieler"+spielerzahl+"geduckt.png")));
        } catch (IOException e) {}

        bilderGeducktRunter = bild.getSubimage(0,0,4,5);
        bilderGeducktRechts = bild.getSubimage(0,5,4,5);
        bilderGeducktLinks = bild.getSubimage(0,15,4,5);
        bilderGeducktHoch = bild.getSubimage(0,10,4,5);
        bildGeduckt = bilderGeducktRunter;

        animation = animationUnten;
        richtung = "RUNTER";
        this.name = name;

        deathcounter = 0;
        killcounter = 0;

        waffe = Objektmanager.get().getSpielmodus().getStartwaffe(this);
        if(waffe==null){
            waffe = new Pistole(this);
        }

        Objektmanager.add(this);
    }

    public boolean amLeben(){
        return amLeben;
    }

    public void berechnen(){
        bewegungBerechnen();
        if(unverwundbareZeit>=0){
            unverwundbareZeit-=25;
        }
        waffe.cooldownVerringern();
    }

    public void bewegenHoch(){
        vertikaleGeschwindigkeit = -geschwindigkeit;
        richtung = "HOCH";
        animation = animationOben;
        bildGeduckt = bilderGeducktHoch;
    }

    public void bewegenRunter(){
        vertikaleGeschwindigkeit = geschwindigkeit;
        richtung = "RUNTER";
        animation = animationUnten;
        bildGeduckt = bilderGeducktRunter;
    }

    public void bewegenLinks(){
        horizontaleGeschwindigkeit = -geschwindigkeit;
        richtung = "LINKS";
        animation = animationLinks;
        bildGeduckt = bilderGeducktLinks;
    }

    public void bewegenRechts(){
        horizontaleGeschwindigkeit = geschwindigkeit;
        richtung = "RECHTS";
        animation = animationRechts;
        bildGeduckt = bilderGeducktRechts;
    }

    public boolean kollidiert(double plusX,double plusY){        
        ArrayList<Hindernis> hindernisse = Objektmanager.getHindernisse();
        boolean kollidiert = false;
        Rectangle r = rect;
        r.setBounds((int)(x+plusX),(int)(y+plusY),(int)breite,(int)hoehe);
        for(int i=0; i<hindernisse.size(); i++){
            if(hindernisse.get(i).kollision(rect,this)){
                kollidiert = true;
                break;
            }            
        }
        return kollidiert;
    }

    protected void bewegungBerechnen(){
        if(!geduckt){
            if(horizontaleGeschwindigkeit!=0 || vertikaleGeschwindigkeit!=0){
                animation.starteAnimation();
            }else{
                animation.stoppeAnimation();
            }

            if(!kollidiert(horizontaleGeschwindigkeit,0)){
                x += horizontaleGeschwindigkeit;
            }else{
                for(int i=0;i<Math.abs(horizontaleGeschwindigkeit);i++){
                    if(!kollidiert(Math.signum(horizontaleGeschwindigkeit),0)){
                        x += Math.signum(horizontaleGeschwindigkeit);
                        break;
                    }
                }
            }
            horizontaleGeschwindigkeit = 0;        

            if(!kollidiert(0,vertikaleGeschwindigkeit)){
                y += vertikaleGeschwindigkeit;
            }else{
                for(int i=0;i<Math.abs(vertikaleGeschwindigkeit);i++){
                    if(!kollidiert(0,Math.signum(vertikaleGeschwindigkeit))){
                        y += Math.signum(vertikaleGeschwindigkeit);
                        break;
                    }
                }
            }
            vertikaleGeschwindigkeit = 0;

            rect.setBounds((int)x,(int)y,(int)breite,(int)hoehe);
        }else{
            animationLinks.stoppeAnimation(); 
            animationRechts.stoppeAnimation(); 
            animationOben.stoppeAnimation();
            animationUnten.stoppeAnimation();
        }
    }

    public void kriegtSchaden(double schaden){
        if(unverwundbareZeit<=0&&amLeben){
            momentanLeben -= schaden;
            Hintergrund.addBlut((int)x,(int)y,(int)schaden);
            if(momentanLeben<=0){
                momentanLeben=0;
                sterben();
            }
        }
    }

    protected void sterben(){
        deathcounter++;
        amLeben = false;
        geschwindigkeit = speedNormal;
        if(respawn){
            amLeben = true;
            momentanLeben=maximalLeben;
            unverwundbareZeit=3000;
            Waffe w = Objektmanager.get().getSpielmodus().getStartwaffe(this);
            if(w!=null)
                waffe = w;
            Objektmanager.getRandomRespawnpunkt().spielerToSpawn(this);
        }
    }

    public void angriff(){
        waffe.aktivieren();        
    }

    public void killcounterErhoehen(){
        killcounter++;
    }

    public int getKillcounter(){
        return killcounter;
    }

    public void ducken(){
        if(!geduckt)
            geduckt = true;
    }

    public void aufstehen(){
        if(geduckt)
            geduckt = false;
    }

    public void setX(double x){
        this.x=x;
    }

    public void setY(double y){
        this.y=y;
    }
    
    public double getXSpeed(){
        return horizontaleGeschwindigkeit;
    }
    
    public double getYSpeed(){
        return vertikaleGeschwindigkeit;
    }

    public int getMaximalLeben(){
        return maximalLeben;
    }

    public void setMaximalLeben(int leben){
        maximalLeben = leben;
    }

    public int getMomentanLeben(){
        return momentanLeben;
    }

    public void setMomentanLeben(int leben){
        momentanLeben = leben;
    }

    public String getRichtung(){
        return richtung;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setWaffe(Waffe w){
        waffe.reset();
        waffe=w;
    }

    public Waffe getWaffe(){
        return waffe;
    }

    public String getWaffenTyp(){
        return waffe.getType();
    }

    public int getDeathcounter(){
        return deathcounter;
    }

    public void setDeathcounter(int counter){
        deathcounter = counter;
    }

    public boolean isGeduckt(){
        return geduckt;
    }

    public static void setSpielerzahlAuf0(){
        spielerzahl=0;
    }

    public void setRespawn(boolean a){
        respawn = a;
    }

    public boolean getRespawn(){
        return respawn;
    }

    public void setGeschwindigkeit(double speed){
        geschwindigkeit = speed;
    }
    
    public double getGeschwindigkeit(){
        return geschwindigkeit;
    }
    
    public void zeichnen(Graphics g, JPanel panel){  
        if(amLeben){
            BufferedImage bild;
            if(geduckt){
                bild = bildGeduckt;
            }else{
                bild = animation.getNextPicture();
            }
            g.drawImage(bild,(int)(x*genauigkeit),(int)((y-3)*genauigkeit),bild.getWidth()*genauigkeit,bild.getHeight()*genauigkeit,panel);
            waffe.zeichnen(g,panel);
        }
    }

}