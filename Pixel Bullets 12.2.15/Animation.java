import java.util.Date;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Animation{
    private BufferedImage[] bild;
    private BufferedImage anibild;
    private int bildBreite;
    private int verschiebung=0;
    private Date letzterBildwechsel=new Date();
    private int bildWechselZeit;
    private boolean laeuft=false;
    public static boolean pausiert = false;

    public Animation(BufferedImage img, int bildBreite, int bildWechselZeit){
        anibild = img;
        this.bildWechselZeit =bildWechselZeit;
        this.bildBreite = bildBreite;

        bild = new BufferedImage[anibild.getWidth()/bildBreite];

        for(int i=0;i<bild.length;i++){
            bild[i]=anibild.getSubimage(i*(bildBreite),0
            ,anibild.getWidth()/bild.length,anibild.getHeight());

        }
    }

    public Animation(BufferedImage img){
        this(img,img.getWidth(),1000000);
    }

    public void starteAnimation(){
        laeuft=true;
        // letzterBildwechsel=new Date();
    }

    public void stoppeAnimation(){
        laeuft=false;
        verschiebung=0;
    }

    public BufferedImage getNextPicture(){
        if(laeuft==true&&pausiert==false&&letzterBildwechsel.getTime()+bildWechselZeit<=new Date().getTime()){

            if(verschiebung<bild.length-1){
                verschiebung++;
                letzterBildwechsel=new Date();
            }else{ 
                verschiebung=0; 
                letzterBildwechsel=new Date();
            } 
        }
        return bild[verschiebung];

    }
}