import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {

        x += velX;
        if (x <= 0) x = 0;
        if (x >= Game.WIDTH - 38) x = (Game.WIDTH - 38);

        y += velY;
        if (y <= 0) y = 1;
        if (y >= Game.HEIGHT - 72) y = (Game.HEIGHT - 72);

        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.id == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH--;
                }
            }
        }
    }
}
