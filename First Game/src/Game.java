import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = 480;

    private Thread thread;
    private boolean running = false;

    private Random r = new Random();
    private Handler handler = new Handler();
    private HUD hud = new HUD();

    public static void main(String[] args) {
        new Game();
    }

    public Game(){

        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "My First Game", this);

        handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));

        for(int i = 0; i < 20; i++) {
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double speed = 60.0;                           // The number of times per second your actions are processed
        double ns = 1000000000 / speed;
        double speedSync = 0;

        while(running){
            long now = System.nanoTime();
            speedSync += (now - lastTime) / ns;         // Amount of Times to loop before Rendering
            lastTime = now;
            while (speedSync >=1){                      // This loop regulates the game speed
                tick();
                speedSync--;
            }
            render();
        }
        stop();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
            System.exit(1);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void tick(){
        handler.tick();
        hud.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();

    }
}
