
public class Respawnpunkt{
    private int x,y;
    public Respawnpunkt(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void spielerToSpawn(Spieler spieler){
        spieler.setX(x);
        spieler.setY(y);
    }
    
    public void NPCToSpawn(NPC npc){
        
        npc.setX(x+(Math.random()*4));
        npc.setY(y+(Math.random()*4));
    }
}
