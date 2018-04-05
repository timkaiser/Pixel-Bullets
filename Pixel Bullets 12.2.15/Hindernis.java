import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
import java.awt.Rectangle;

public class Hindernis extends Objekt{    
    protected Color farbe;
    public Hindernis(Color farbe, double x, double y, int breite, int hoehe){
        super(x,y,breite,hoehe);
        this.farbe = farbe;
    }

    public void zeichnen(Graphics g, JPanel panel){     
        g.setColor(farbe);
        g.fillRect((int)(x*genauigkeit),(int)(y*genauigkeit), (int)(breite*genauigkeit), (int)(hoehe*genauigkeit));
    }
    
    public boolean kollision(Rectangle r, Projektil p ){
        return kollision(r);
    }
    
    public boolean kollision(Rectangle r, Spieler s){
        return kollision(r);
    }
    
    public boolean kollision(Rectangle r, NPC npc){
        return kollision(r);
    }
    
    public void berechnen(){};
    
    public boolean istNiedrig(){
        return false;
    }
    
    public Color getColor(){
        return farbe;
    }
}
