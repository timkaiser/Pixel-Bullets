public class Shotgun extends Waffe{
    public Shotgun(Spieler besitzer){
        super(besitzer);
        maxCooldown = 2000;
        reichweite = 17; 
        schaden=40;
        startwerteAnpassen();
    }

    public static void playSound(){  
        if(Optionsmenue.soundAnAus()){
            Sound schusssound = new Sound("Shotgun.wav");
            schusssound.start();
        }
    }

    public void aktivieren(){
        if(cooldown<=0){
            cooldown=maxCooldown;
            benutzt = true;
            if(besitzer.getRichtung().equals("HOCH")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,-0.2+besitzer.getXSpeed(),-1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,0+besitzer.getXSpeed(),-1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,0.2+besitzer.getXSpeed(),-1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("RUNTER")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,-0.2+besitzer.getXSpeed(),1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,0+besitzer.getXSpeed(),1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,0.2+besitzer.getXSpeed(),1+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("LINKS")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,-1+besitzer.getXSpeed(),-0.2+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,-1+besitzer.getXSpeed(),0+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,-1+besitzer.getXSpeed(),0.2+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("RECHTS")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,1+besitzer.getXSpeed(),-0.2+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,1+besitzer.getXSpeed(),0+besitzer.getYSpeed(),reichweite,schaden,besitzer));
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,1+besitzer.getXSpeed(),0.2+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            playSound();
        }
    }
    
    public String reichweiteUpgrade(){
        if(reichweite*1.2 <= startReichweite*3)
        reichweite *=1.2;
        return "Reichweiten-Upgrade";
    }
    
    public String getType(){
        return "Shotgun";
    }
    
    public Waffe getKopie(Spieler s){
        return new Shotgun(s);
    }
}