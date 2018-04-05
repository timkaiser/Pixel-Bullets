import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Spielmodus{
    protected boolean uhrZeichnen, lebenZeichnen, todeZeichnen, waffenkisten, teams;
    protected Waffe startwaffe;
    protected String unterSpielerZeichnen;
    protected int kills=10, leben=10, zeit=120;

    public Spielmodus(){
        Waffenkiste.aktivieren();
    }

    public abstract void schleife();

    public abstract Spieler gibGewinnerFallsZuende();

    public void zeichnen(Graphics g,JPanel panel,int genauigkeit,BufferedImage zeichenfeld){
        java.util.ArrayList<Spieler> spieler = Objektmanager.getSpieler();
        Font font = new Font("Arial", Font.PLAIN, 25);
        g.setFont(font); 

        for(int i = 0; i< spieler.size();i++){
            if(spieler.get(i).amLeben()){
                int x = (int) (spieler.get(i).getX()*genauigkeit);
                int y = (int) (spieler.get(i).getY()*genauigkeit);
                int spielerBreite = (int)(spieler.get(i).getBreite()*genauigkeit);
                int spielerHoehe = (int)(spieler.get(i).getHoehe()*genauigkeit);                        

                g.setColor(Color.BLACK);            

                String name = ueberSpielerZeichnen(spieler, i);
                int rectY = y-(spielerHoehe*3);

                JTextField tf = new JTextField();
                tf.setFont(font);
                FontMetrics fm = tf.getFontMetrics(font);
                int rectWidth = fm.stringWidth(name)+4;
                //int rectWidth = 15*name.length();
                int rectX = (int)((x)-((rectWidth*0.5)-(spielerBreite*0.5)));
                int rectHeight = 25;
                g.fill3DRect(rectX,rectY,rectWidth,rectHeight,true);            

                g.setColor(Color.WHITE);

                int stringX = rectX;
                int stringY = (int)(rectY+(rectHeight*0.75));

                g.drawString(name,stringX+2,stringY+2);

                g.setColor(Color.WHITE);

                int randY = y - (spielerHoehe*2);
                int randWidth = spielerBreite*2;
                int randX = (int)(x-((randWidth*0.5)-spielerBreite*0.5));
                int randHeight = (int)(spielerHoehe*0.3);

                g.fillRect(randX,randY,randWidth,randHeight);

                g. setColor(Color.RED);
                int lebenX = randX;
                int lebenY = randY;
                int lebenWidth = (int)(randWidth*((double)spieler.get(i).getMomentanLeben()/(double)spieler.get(i).getMaximalLeben()));
                int lebenHeight = randHeight;

                g.fillRect(lebenX,lebenY,lebenWidth,lebenHeight);
            }
        }
        font = new Font("Arial", Font.PLAIN, 50);
        g.setFont(font); 
        g.setColor(Color.BLACK);
    }

    protected String ueberSpielerZeichnen(java.util.ArrayList<Spieler> spieler, int spielerindex){
        return "Fehler";
    }

    public abstract void drawWinscreen(Graphics g,JPanel panel,int x,int y,int width,int height);

    public Waffe getStartwaffe(Spieler sp){
        return startwaffe.getKopie(sp);
    }  

    public void setLeben(int i){
        leben=i;
    }

    public void setKills(int i){
        kills=i;
    }

    public void setZeit(int i){
        zeit=i;
    }

    abstract Moduseinstellungspanel getEinstellungspanel(Fenster container);
}