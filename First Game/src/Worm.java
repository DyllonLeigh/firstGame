import java.awt.*;

public class Worm extends GameObject {
    private int WIDTH, HEIGHT;
    private Handler handler;
    private Color colour;
    private int energy;

    public Worm(int x, int y, ID id, int WIDTH, int HEIGHT, Color colour, int energy, Handler handler ) {
        super(x, y, id);
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.colour = colour;
        this.energy = energy;
        this.handler = handler;
    }

    @Override
    public void tick() {
        if (energy > 0) {
            energy--;
        } else handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x,y,WIDTH, HEIGHT);
        g.setColor(colour);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
