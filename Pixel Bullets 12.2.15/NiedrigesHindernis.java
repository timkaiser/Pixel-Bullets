import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
import java.awt.Rectangle;

public class NiedrigesHindernis extends Hindernis{

    public NiedrigesHindernis(Color farbe, double x, double y, int breite, int hoehe){
        super(farbe,x,y,breite,hoehe);       
    }
    
    
    public boolean kollision(Rectangle r, Projektil p ){
        boolean kollidiert = kollision(r);
        if(kollidiert){
            p.kollidiertMitNiedrigemHindernis();
        }
        return (p.isTief()&&kollidiert);
    }

    public boolean istNiedrig(){
        return true;
    }
}
