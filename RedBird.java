import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RedBird extends MouseAdapter
{
	private double x, y;
	private int radius;
	private boolean dragging;

	public RedBird(double x, double y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		dragging = false;
	}

	public void update()
	{

	}

	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval((int)x, (int)y, radius * 2, radius * 2);
	}

	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		

		if(mx >= (int)x && mx <= (int)x + radius * 2 && my >= (int)y && my <= (int)y + radius * 2)
		{
			dragging = true;
		}
	}

	public void mouseDragged(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();

		if(dragging)
		{
			
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		dragging = false;
	}
}