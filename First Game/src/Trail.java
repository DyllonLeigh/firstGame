import java.awt.*;

public class Trail extends GameObject {

    private Handler handler;
    private float alpha = 1;
    private Color colour;
    private float life;

    public Trail(int x, int y, ID id, float life, Color colour, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.colour = colour;
        this.life = life;
    }

    @Override
    public void tick() {

        if(alpha > life) {
            alpha -= (life - 0.001f);
        } else handler.removeObject(this);

    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(makeTransparent(alpha));

        g.setColor(colour);
        g.fillRect(x,y,16,16);

        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {

        int type = AlphaComposite.SRC_OVER;

        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
