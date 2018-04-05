import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
public class Survival extends Spielmodus{
    private int enemys;
    private int timeTillNextWave;
    private int timeTillFirstWave;
    private int timeCounter;
    private int waveNummer = 0;
    public Survival(int time){
        super();
        this.leben = leben;
        timeTillNextWave = time;
        timeTillFirstWave = time/6;
        Waffenkiste.aktivieren();
        startwaffe = new MG(null);
    }

    public void schleife(){
        if(gibGewinnerFallsZuende() == null){
            for(int i = 0; i < Objektmanager.getNPC().size();i++){
                if(Objektmanager.getNPC().get(i).amLeben()){
                    Objektmanager.getNPC().get(i).berechnen();
                }
            }
            for(int i = 0; i< Objektmanager.getSpieler().size();i++){
                if((leben-Objektmanager.getSpieler(i).getDeathcounter())<=1){
                    Objektmanager.getSpieler(i).setRespawn(false);
                }
            }

            timeCounter+=25;
            if(waveNummer == 0){
                if(timeCounter > timeTillFirstWave){
                    spawnEnemys();
                    timeCounter = 0;
                    waveNummer++;
                }
            }
            else{
                if(timeCounter > timeTillNextWave){
                    spawnEnemys();
                    timeCounter = 0;
                    waveNummer++;
                }
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
        Font font = new Font("Arial", Font.PLAIN, 40);
        g.setFont(font);
        g.setColor(Color.BLACK);
        if(waveNummer != 0){
            g.drawString("Nächste Welle kommt in: " + (timeTillNextWave-timeCounter),(int)(zeichenfeld.getWidth()*0.5)-"Nächste Welle kommt in: ".length() *12 ,0+50);
        }
        else{
            g.drawString("Nächste Welle kommt in: " + (timeTillFirstWave-timeCounter),(int)(zeichenfeld.getWidth()*0.5)-"Nächste Welle kommt in: ".length() *12,0+50);
        }
        if(gibGewinnerFallsZuende() != null ){
            drawWinscreen(g,panel,(int)(zeichenfeld.getWidth()*0.1),(int)(zeichenfeld.getHeight()*0.1),(int)(zeichenfeld.getWidth()*0.8),(int)(zeichenfeld.getHeight()*0.8));
        }
    }

    protected String ueberSpielerZeichnen(java.util.ArrayList<Spieler> spieler, int i){
        return spieler.get(i).getName()+" "+(leben-spieler.get(i).getDeathcounter());
    }

    public void spawnEnemys(){
        for(int i = 0; i< 10 ; i++){
            Objektmanager.add(new NPC());
        }
    }

    public void drawWinscreen(Graphics g,JPanel panel,int x,int y,int width,int height){
        AlphaComposite ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.2f);
        g.setColor(new Color(44, 62, 80,(int)(255*0.8)));
        g.fillRect(x,y,width,height);

        g.setColor(new Color(236, 240, 241));
        Font font = new Font("Arial", Font.PLAIN, 30);
        g.setFont(font);
        g.drawString(gibGewinnerFallsZuende().getName()+" hat Gewonnen!!",(int)(x+(width*0.4)),(int)(y+height*0.1));
        for(int i = 0; i < Objektmanager.getSpieler().size(); i++){
            int xString = (int)(x+(width*0.1));
            int yString = (int)(y+((i+1)*(height/(Objektmanager.getSpieler().size()*1.5))));
            g.drawString(Objektmanager.getSpieler(i).getName(),xString,yString);
            g.drawString(""+Objektmanager.getSpieler(i).getKillcounter(),(int)(xString+(width*0.5)),yString);
        }

    }

     public Moduseinstellungspanel getEinstellungspanel(Fenster container){
        Moduseinstellungspanel p = new Moduseinstellungspanel(this,container);
        p.addLebensAuswahl();
        p.setMaps("XMas");
        return p;
    }
}