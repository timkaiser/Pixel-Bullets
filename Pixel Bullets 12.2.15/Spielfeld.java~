import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.RenderingHints;
import Wegfindung.*;

public class Spielfeld extends JPanel  {

    private final GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    static double zoomfaktor = 0.5;
    private static JPanel s=new JPanel();

    final int genauigkeit = Objekt.genauigkeit;

    Pfad pfad;
    int zaehler_test;

    public Spielfeld(String map,Spielmodus modus,String...name){
        s=this;
        s.setLayout(null);
        s.setFocusable(true);

        s.setDoubleBuffered(true);

        Objektmanager.setMap(map);

        //!!!
        //modus=new Survival(30000);
        //!!!

        Objektmanager.get().setSpielmodus(modus);

        if(name.length>=1){
            new Spieler(name[0]);
        }
        if(name.length>=2){
            new Spieler(name[1]);
        }
        if(name.length>=3){
            new Spieler(name[2]);
        }
        if(name.length>=4){
            new Spieler(name[3]);
        }

        setBackground(Objektmanager.getHindernisse().get(1).getColor());
        Objektmanager.get().setSpielmodus(modus);

        pfad=new Pfad();
        //pfad.wegBerechnen(Knoten.getKnoten().get(0),Knoten.getKnoten().get(26));   

        new Spielschleife(this,modus);
        s.repaint();
    }

    int test;
    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );
        test++;
        setPreferredSize(null);

        int hoehe = this.getHeight();
        int breite = Objektmanager.getLevelbreite()*hoehe/Objektmanager.getLevelhoehe();

        BufferedImage zeichenfeld = gfxConf.createCompatibleImage( Objektmanager.getLevelbreite()*genauigkeit, Objektmanager.getLevelhoehe()*genauigkeit);

        ArrayList<Hindernis> hindernisse = Objektmanager.getHindernisse();
        ArrayList<Spieler> spieler = Objektmanager.getSpieler();
        ArrayList<Projektil> projektile = Objektmanager.getProjektile();
        ArrayList<NPC> npc = Objektmanager.getNPC();
        Graphics graphics = zeichenfeld.createGraphics();

        for(int i=0; i<hindernisse.size();i++){
            hindernisse.get(i).zeichnen(graphics,this);
        }

        graphics.setColor(new Color(255,255,0));
        /*Knoten.zeichnen(graphics,this);

        graphics.setColor(new Color(0,100,0));
        if(Knoten.getKnoten()!=null){
        for(int i=0; i<Knoten.getKnoten().size();i++){
        Knoten k = Knoten.getKnoten().get(i);
        graphics.fillRect(k.getX()*Objekt.genauigkeit,k.getY()*Objekt.genauigkeit,Objekt.genauigkeit,Objekt.genauigkeit);
        }
        } */

        for(int i=0; i<projektile.size();i++){
            projektile.get(i).zeichnen(graphics,this);
        }
        for(int i=0; i<spieler.size();i++){
            spieler.get(i).zeichnen(graphics,this);
        }

        for(int i=0; i<npc.size();i++){
            npc.get(i).zeichnen(graphics,this);
        }
        Objektmanager.get().getSpielmodus().zeichnen(graphics,this,genauigkeit,zeichenfeld);       
        /*
        zaehler_test++;
        pfad = new Pfad();
        Knoten k1 = Knoten.getNaechstenKnoten((int)spieler.get(0).getX(),(int)spieler.get(0).getY());
        Knoten k2 = Knoten.getNaechstenKnoten((int)spieler.get(1).getX(),(int)spieler.get(1).getY());
        pfad.wegBerechnen(k1,k2);

        ArrayList<Punkt> k = pfad.getWegpunkte();

        graphics.setColor(Color.GREEN);
        for(int i=0; i<k.size();i++){
        graphics.fillRect(k.get(i).getX()*Objekt.genauigkeit,k.get(i).getY()*Objekt.genauigkeit,Objekt.genauigkeit,Objekt.genauigkeit);
        }
         */
        g.drawImage(zeichenfeld,(this.getWidth()-breite)/2,0,breite,hoehe,this);

        requestFocus();
    }

    public static JPanel getSpielfeld(){
        return s;
    }    

}
