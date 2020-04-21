import java.awt.*;

public class HUD {

    public static int HEALTH = 100;

    public void tick(){

        if(HEALTH < 0) {
            HEALTH = 0;
            System.exit(1);
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(15,15,200,30);

        g.setColor(Color.green);
        g.fillRect(15,15,HEALTH * 2,30);
    }
}
