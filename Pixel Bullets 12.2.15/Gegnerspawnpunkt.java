
public class Gegnerspawnpunkt{
    private int x,y;
    public Gegnerspawnpunkt(int x, int y){
        this.x=x;
        this.y=y;
    }
     
    public void NPCToSpawn(NPC npc){
        
        npc.setX(x+(Math.random()*1));
        npc.setY(y+(Math.random()*1));
    }
}
