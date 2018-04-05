public class Bazooka extends Waffe{
private int radius;
    public Bazooka(Spieler besitzer){
        super(besitzer);
        maxCooldown = 2000;
        reichweite = 25;        
        schaden = 10;
        startwerteAnpassen();
    }

    public static void playSound(){  
        if(Optionsmenue.soundAnAus()){
            Sound schusssound = new Sound("Bazooka.wav");
            schusssound.start();
        }
    }

    public void aktivieren(){
        if(cooldown<=0){
            cooldown=maxCooldown;
            benutzt = true;
            if(besitzer.getRichtung().equals("HOCH")){
                Objektmanager.add(new ProjektilExplosiv(besitzer.getX(),besitzer.getY(),1,1,0+besitzer.getXSpeed(),-1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("RUNTER")){
                Objektmanager.add(new ProjektilExplosiv(besitzer.getX(),besitzer.getY(),1,1,0+besitzer.getXSpeed(),1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("LINKS")){
                Objektmanager.add(new ProjektilExplosiv(besitzer.getX(),besitzer.getY(),1,1,-1+besitzer.getXSpeed(),0+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("RECHTS")){
                Objektmanager.add(new ProjektilExplosiv(besitzer.getX(),besitzer.getY(),1,1,1+besitzer.getXSpeed(),0+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            playSound();
        }
    }

    public String getType(){
        return "Bazooka";
    }

    public Waffe getKopie(Spieler s){
        return new Bazooka(s);
    }
}