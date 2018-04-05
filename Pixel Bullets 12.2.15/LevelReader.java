import java.awt.Graphics;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.RenderingHints;
import java.awt.*;
import Wegfindung.*;

public class LevelReader{
    FileInputStream iostream;
    DataInputStream diostream;

    private int breite, hoehe;

    ArrayList<Hindernis> hindernisse;
    ArrayList<Respawnpunkt> respawnpunkte;
    ArrayList<Gegnerspawnpunkt> gegnerspawnpunkte;

    ArrayList<Knoten> knoten;

    private int[][] verbindungspfade;

    private final GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public LevelReader(String mapname){
        levelEinlesen(mapname);
    }

    private void levelEinlesen(String mapname){
        hindernisse = new ArrayList<Hindernis>();
        ArrayList<Hindernis>  hintergrund = new ArrayList<Hindernis>();
        respawnpunkte = new ArrayList<Respawnpunkt>();
        gegnerspawnpunkte = new ArrayList<Gegnerspawnpunkt>();
        try {
            iostream = new FileInputStream(mapname + ".lvl");
            diostream = new DataInputStream(iostream);
            try {
                breite=diostream.readInt();
                hoehe=diostream.readInt();
                verbindungspfade = new int[breite][hoehe];
                int anzahl=diostream.readInt();
                for(int i=0;i<anzahl;i++){
                    char read1 = diostream.readChar();
                    switch(read1){
                        case('H'): { //High / Hohe Hindernisse
                            Color c = new Color(diostream.readInt(),diostream.readInt(),diostream.readInt());
                            int x = diostream.readInt();
                            int y = diostream.readInt();
                            hindernisse.add(new Hindernis(c,x,y,1,1));
                        }break;
                        case('L'):{//Low / Niedrige Hindernisse
                            Color c = new Color(diostream.readInt(),diostream.readInt(),diostream.readInt());
                            int x = diostream.readInt();
                            int y = diostream.readInt();
                            hindernisse.add(new NiedrigesHindernis(c,x,y,1,1));

                        }break;
                        case('B'):{//Background / Hintergrund
                            hintergrund.add(new Hindernis(new Color(diostream.readInt(),diostream.readInt(),diostream.readInt()),diostream.readInt(),diostream.readInt(),1,1));
                        }break;
                        case('O')://Other / Sonstiges
                        int rot = diostream.readInt();
                        int gruen = diostream.readInt();
                        int blau = diostream.readInt();
                        if(rot==255 && gruen == 0 && blau == 0){
                            respawnpunkte.add(new Respawnpunkt(diostream.readInt(),diostream.readInt()));
                        }else if(rot==0 && gruen == 0 && blau == 255){
                            hindernisse.add(new Waffenkiste(diostream.readInt(),diostream.readInt()));
                        }else if(rot==0 && gruen == 100 && blau == 0){
                            int x = diostream.readInt();
                            int y = diostream.readInt();
                            new Knoten(x,y);
                            verbindungspfade[x][y]=1;
                        }else if(rot==255 && gruen == 255 && blau == 0){
                            verbindungspfade[diostream.readInt()][diostream.readInt()]=2;
                        }else if(rot == 0 && gruen == 255 && blau == 0){
                            hindernisse.add(new Powerupkiste(diostream.readInt(),diostream.readInt()));
                        }
                        else if(rot == 0 && gruen == 255 && blau == 255){
                            gegnerspawnpunkte.add(new Gegnerspawnpunkt(diostream.readInt(),diostream.readInt()));
                        }else{
                            diostream.readInt();
                            diostream.readInt();
                        }
                        break;
                    }
                }
                BufferedImage background = gfxConf.createCompatibleImage( breite*10, hoehe*10);

                for(int i=0; i<hintergrund.size(); i++){
                    hintergrund.get(i).zeichnen(background.createGraphics(), null);
                }

                hindernisse.add(0,new Hintergrund(background,0,0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {}
        Knoten.matrixInitialisieren(verbindungspfade);
    }

    public ArrayList<Hindernis> getHindernisse(){
        return hindernisse;
    }

    public ArrayList<Respawnpunkt> getRespawnpunkte(){
        return respawnpunkte;
    }

    public ArrayList<Gegnerspawnpunkt> getGegnerspawnpunkte(){
        return gegnerspawnpunkte;
    }
    
    public int getBreite(){
        return breite;
    }

    public int getHoehe(){
        return hoehe;
    }
}