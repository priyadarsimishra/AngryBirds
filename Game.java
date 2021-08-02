import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
	public static STATE GAME_STATE = STATE.GAME;
	private boolean run;
	private Frame frame;
	private Thread thread;
	private RedBird redBird;
	
	public Game()
	{
		run = false;
		frame = new Frame(this);
		redBird = new RedBird(250, 600, 30);
		addMouseListener(redBird);
		addMouseMotionListener(redBird);
		setFocusable(true);
	}

	public enum STATE
	{
		GAME
	}

	public synchronized void start()
	{
		if(run)
			return;
		run = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!run)
			return;
		run = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run()
	{
		long lastTime = System.nanoTime();
        double amountOfUpdates = 60.0;
        double ns = 1000000000 / amountOfUpdates;
        double catchUp = 0;
        int updates = 0;
        int FPS = 0;
        long timerCheck = System.currentTimeMillis();
        while (run) 
        {
            long now = System.nanoTime();
            catchUp += (now - lastTime) / ns;
            lastTime = now;
            while (catchUp >= 1) 
            {
                update();
                updates++;
                catchUp--;
            }
            draw();
            FPS++;
            if (System.currentTimeMillis() - timerCheck > 1000) 
            {
                timerCheck += 1000;
                System.out.println("Updates: " + updates + ", FPS: " + FPS);
                FPS = 0;
                updates = 0;
            }
        }
        stop();
	}

	public void update() 
	{
		
	}

	public void draw() 
	{
		BufferStrategy bs = this.getBufferStrategy();		
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);

		if(redBird != null)
		{
			redBird.draw(g);
		}

		
		bs.show();
		g.dispose();
	}

	public static void main(String [] args)
	{
		new Game();
	}
}