public class Sniper extends Waffe{
    public Sniper(Spieler besitzer){
        super(besitzer);
        maxCooldown = 3000;
        startwerteAnpassen();
    }

    public void aktivieren(){
        if(cooldown<=0){
            cooldown=maxCooldown;
            benutzt = true;
            if(besitzer.getRichtung().equals("HOCH")){
                Objektmanager.add(new ProjektilSniper(besitzer.getX(),besitzer.getY(),1,2,0+besitzer.getXSpeed(),-2+besitzer.getYSpeed(),besitzer));
            }
            else if(besitzer.getRichtung().equals("RUNTER")){
                Objektmanager.add(new ProjektilSniper(besitzer.getX(),besitzer.getY(),1,2,0+besitzer.getXSpeed(),2+besitzer.getYSpeed(),besitzer));
            }
            else if(besitzer.getRichtung().equals("LINKS")){
                Objektmanager.add(new ProjektilSniper(besitzer.getX(),besitzer.getY(),2,1,-2+besitzer.getXSpeed(),0+besitzer.getYSpeed(),besitzer));
            }
            else if(besitzer.getRichtung().equals("RECHTS")){
                Objektmanager.add(new ProjektilSniper(besitzer.getX(),besitzer.getY(),2,1,2+besitzer.getXSpeed(),0+besitzer.getYSpeed(),besitzer));
            }
            if(besitzer.getWaffenTyp()=="MG"){
                MG.playSound();
            }
            else if(besitzer.getWaffenTyp()=="Pistole"){
                Pistole.playSound();
            }
        }
    }

    public static void playSound(){ 
        if(Optionsmenue.soundAnAus()==true){
            Sound schusssound=new Sound("Schuss.wav");
            schusssound.start();
        }
    }

    public String getType(){
        return "Sniper";
    }
    
    public Waffe getKopie(Spieler s){
        return new Sniper(s);
    }
}