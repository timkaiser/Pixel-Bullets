public class Respawnpunkt{
    private int x, y;
    private boolean aktiv;
    public Respawnpunkt(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void spielerZumSpawn(Spieler sp){
        sp.setX(x);
        sp.setY(y);
    }
    
    public int getEntfernung(int xe, int ye){
        int xentf=x-xe;
        int yentf=y-ye;
        return (int)(Math.sqrt(xentf*xentf+yentf*yentf));
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void aktivieren(){
        aktiv = true;
    }
    
     public void deaktivieren(){
        aktiv = false;
    }
}
