import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
public class Deathmatch extends Spielmodus{
    public Deathmatch(){
        super();
        Waffenkiste.aktivieren();
        startwaffe = new Pistole(null);
    }

    public void schleife(){
        for(int i = 0; i< Objektmanager.getSpieler().size();i++){
            if((leben-Objektmanager.getSpieler(i).getDeathcounter())<=1){
                Objektmanager.getSpieler(i).setRespawn(false);
            }
        }
    }

    public Spieler gibGewinnerFallsZuende(){
        ArrayList<Spieler> spieler = Objektmanager.getSpieler();
        int counter = 0;
        for(int i = 0; i < spieler.size();i++){
            counter += (leben-spieler.get(i).getDeathcounter());
        }
        for(int i = 0; i < spieler.size();i++){
            if(counter == (leben-spieler.get(i).getDeathcounter())){
                return spieler.get(i);
            }
        }
        return null;
    }

    public void zeichnen(Graphics g,JPanel panel,int genauigkeit,BufferedImage zeichenfeld){
        super.zeichnen(g,panel,genauigkeit,zeichenfeld);

        if(gibGewinnerFallsZuende() != null ){
            drawWinscreen(g,panel,(int)(zeichenfeld.getWidth()*0.1),(int)(zeichenfeld.getHeight()*0.1),(int)(zeichenfeld.getWidth()*0.8),(int)(zeichenfeld.getHeight()*0.8));
        }
    }

    protected String ueberSpielerZeichnen(java.util.ArrayList<Spieler> spieler, int i){
        return spieler.get(i).getName()+" "+(leben-spieler.get(i).getDeathcounter());
    }

    public void drawWinscreen(Graphics g,JPanel panel,int x,int y,int width,int height){
        AlphaComposite ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.2f);
        g.setColor(new Color(44, 62, 80,(int)(255*0.8)));
        g.fillRect(x,y,width,height);

        g.setColor(new Color(236, 240, 241));
        Font font = new Font("Arial", Font.PLAIN, 30);
        g.setFont(font);

        for(int i = 0; i < Objektmanager.getSpieler().size(); i++){
            int xString = (int)(x+(width*0.1));
            int yString = (int)(y+((i+1)*(height/(Objektmanager.getSpieler().size()*1.5))));
            g.drawString(Objektmanager.getSpieler(i).getName(),xString,yString);
            g.drawString(""+Objektmanager.getSpieler(i).getKillcounter(),(int)(xString+(width*0.5)),yString);
        }

    }

    public Moduseinstellungspanel getEinstellungspanel(Fenster container){
        Moduseinstellungspanel p = new Moduseinstellungspanel(this, container);
        p.addLebensAuswahl();
        p.setMaps("castle","Hyrule");
        return p;
    }
}