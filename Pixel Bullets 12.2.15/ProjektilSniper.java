public class ProjektilSniper extends Projektil{

    public ProjektilSniper (double x, double y, int breite, int hoehe, double speedX, double speedY,Spieler spieler){
        super(x,y,breite,hoehe,speedX,speedY,0,0,spieler);
    }

    public void bewegungBerechnen(){
        x+=speedX;
        y+=speedY;

        reichweite += Math.abs(speedX);
        reichweite += Math.abs(speedY);

        schaden = 9/7*reichweite;

        if(kollidiert()){
            Objektmanager.getProjektile().remove(this);
            if(element.equals("Explosiv"))
                new Explosion(x,y,3,schaden/10,besitzer);
        }
        Spieler sp = kollidiertMitSpieler();
        if(sp != null){
            Objektmanager.getProjektile().remove(this);
            if(element.equals("Explosiv"))
                new Explosion(x,y,3,schaden/10,besitzer);
            if(sp.getMomentanLeben()-schaden<=0){
                besitzer.killcounterErhoehen();
            }
            sp.kriegtSchaden(schaden);
        }
    }

}