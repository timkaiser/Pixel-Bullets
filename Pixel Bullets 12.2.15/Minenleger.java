import java.util.ArrayList;

public class Minenleger extends Waffe{
    private ArrayList<Mine> minen;
    private int anzahlMinen;
    public Minenleger(Spieler besitzer){
        super(besitzer);
        maxCooldown = 250;
        reichweite = 7;
        schaden = 150;
        minen = new ArrayList<Mine>();
        anzahlMinen = 5;
        startwerteAnpassen();
    }

    public void aktivieren(){
        if(cooldown<=0){
            if(minen.size()<anzahlMinen){
                cooldown=maxCooldown;
                benutzt = true;
                minen.add(new Mine(besitzer.getX(),besitzer.getY(), (int) reichweite, schaden, besitzer,this));
            }else{
                cooldown=maxCooldown/2;
                minen.get(0).sprengen();
            }
        }
    }

    public String schussgeschwindigkeitsUpgrade(){
        if(anzahlMinen < 10)
            anzahlMinen+=1;
        return "Mehr Minen";
    }

    public void reset(){
        for(int i=0;i<minen.size();i++){
            minen.get(i).sprengen();
        }
    }

    public static void playSound(){ 
    }

    public void mineEntfernen(Mine m){
        minen.remove(m);
    }

    public String getType(){
        return "Minen";
    }

    public Waffe getKopie(Spieler s){
        return new Minenleger(s);
    }

    public void setMinenAnzahl(int anzahl){
        anzahlMinen = anzahl;
    }

    public int getMinenAnzahl(){
        return anzahlMinen;
    }
}