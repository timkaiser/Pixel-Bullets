import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.RenderingHints;

public class Hintergrund extends Hindernis{    
    public static BufferedImage bild;
    private final GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    protected static String blutmodus = "Blut";

    public Hintergrund(BufferedImage bild, double x, double y){
        super(null,x,y,1,1);
        this.bild = bild;
    }

    public boolean kollision(Rectangle r, Projektil p ){
        return false;
    }

    public boolean kollision(Rectangle r){
        return false;
    }

    public static void addBlut(int x, int y, int schaden){
        Graphics g = bild.createGraphics();
        if(blutmodus != "Kein Blut"){
            if(blutmodus == "Blut"){
                g.setColor(new Color(200,0,0,30));
            }else if(blutmodus == "Paintball"){
                g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255),30));
            }
            int anzahl = (schaden/2+2+(int)(Math.random()*3))*2;
            for(int i=0;i<anzahl;i++){
                int nop = 1; //neagativ oder positiv
                if((int)(Math.random()*2)==1)
                    nop=-1;
                int xV = (((int)Math.pow((int)(Math.random()*Math.sqrt(30)),2)*nop)/5)*5;
                nop = 1;
                if((int)(Math.random()*2)==1)
                    nop=-1;
                int yV = (((int)Math.pow((int)(Math.random()*Math.sqrt(30)),2)*nop)/5)*5;;
                g.fillRect(x*genauigkeit+xV,y*genauigkeit+yV,1*genauigkeit,1*genauigkeit);
            }
        }
    }

    public static void setBlutmodus(String blut){
        blutmodus = blut;
    }

    public void zeichnen(Graphics g, JPanel panel){     
        g.drawImage(bild,(int)(x),(int)(y),bild.getWidth(),bild.getHeight(), panel);
    }

}
