import java.awt.*;
import java.util.Random;

public class BasicEnemy extends GameObject {

    Handler handler = new Handler();
    Random r = new Random();

    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            velX = r.nextInt(5) + 1;
            velY = r.nextInt(5) + 1;
        }
    }

    @Override
    public void tick() {

        x += velX;
        if ((x <= 0) || (x >= Game.WIDTH -23)) velX *= -1;

        y += velY;
        if ((y <= 0) || (y >= Game.HEIGHT -47)) velY *= -1;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
