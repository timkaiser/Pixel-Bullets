import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class GunGame extends Spielmodus{

    public GunGame() {
        waffenkisten = false;
        Waffenkiste.deaktivieren();
    }

    public void schleife(){
        ArrayList<Spieler> spieler = Objektmanager.getSpieler();
        for(int i = 0; i < Objektmanager.getSpieler().size(); i++){
            switch(spieler.get(i).getKillcounter()){
                case 0:
                if(!spieler.get(i).getWaffenTyp().equals("MG")){
                    spieler.get(i).setWaffe(new MG(spieler.get(i)));
                }
                break;
                case 1:
                if(!spieler.get(i).getWaffenTyp().equals("Shotgun")){
                    spieler.get(i).setWaffe(new Shotgun(spieler.get(i)));
                }
                break;
                case 2:
                if(!spieler.get(i).getWaffenTyp().equals("Sniper")){
                    spieler.get(i).setWaffe(new Sniper(spieler.get(i)));
                }
                break;
                case 3:
                if(!spieler.get(i).getWaffenTyp().equals("DoppelPistolen")){
                    spieler.get(i).setWaffe(new DoppelPistolen(spieler.get(i)));
                }
                break;
                case 4:
                if(!spieler.get(i).getWaffenTyp().equals("Pistole")){
                    spieler.get(i).setWaffe(new Pistole(spieler.get(i)));
                }
                break;
            }
        }
    }

    public Waffe getStartwaffe(Spieler sp){            
        return null;
    }

    public Spieler gibGewinnerFallsZuende(){
        ArrayList<Spieler> spieler = Objektmanager.getSpieler();
        Spieler s = null;
        for(int i = 0; i < Objektmanager.getSpieler().size(); i++){
            if(spieler.get(i).getKillcounter()==5)
                s = spieler.get(i);
        }

        return s;
    }

    public void zeichnen(Graphics g,JPanel panel,int genauigkeit,BufferedImage zeichenfeld){
        super.zeichnen(g,panel,genauigkeit,zeichenfeld);

        if(gibGewinnerFallsZuende() != null ){
            drawWinscreen(g,panel,(int)(zeichenfeld.getWidth()*0.1),(int)(zeichenfeld.getHeight()*0.1),(int)(zeichenfeld.getWidth()*0.8),(int)(zeichenfeld.getHeight()*0.8));
        }
    }

    protected String ueberSpielerZeichnen(java.util.ArrayList<Spieler> spieler, int i){
        return spieler.get(i).getName()+" "+(5-spieler.get(i).getKillcounter());
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
        Moduseinstellungspanel p = new Moduseinstellungspanel(this,container);
        p.setMaps("castle","Hyrule");
        return p;
    }
}
