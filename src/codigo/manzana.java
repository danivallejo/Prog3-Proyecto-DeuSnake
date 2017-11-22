package codigo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class manzana extends JLabel
{
	private Random r;
	private int posX;
	private int posY;
	ImageIcon icono2;
	
	public manzana() 
	{
		r= new Random();
		posX = r.nextInt(700);
		posY = r.nextInt(500);
		
		setLocation(posX, posY);
		setSize(30, 30);
		
		try
		{
			icono2 = new ImageIcon(manzana.class.getResource( "apple.png" ).toURI().toURL() );
		} catch (MalformedURLException | URISyntaxException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPosX() 
	{
		return posX;
	}
	public void setPosX(int posX) 
	{
		this.posX = posX;
	}

	public int getPosY() 
	{
		return posY;
	}

	public void setPosY(int posY)
	{
		this.posY = posY;
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
	Icon nuevoIcono = new ImageIcon(icono2.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
	setIcon(nuevoIcono);
	
	super.paintComponent(g);
	}
}
