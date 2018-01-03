
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Prueba 
{
	private Random r1 = new Random();
	private Random r2 = new Random();
	private int posx1 = r1.nextInt(700);
	private int posx2 = r2.nextInt(700);
	private int posy1 = r1.nextInt(500);
	private int posy2 = r2.nextInt(500);
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	/** Este test comprueba que las manzanas creadas nunca van a salir en la misma posición del panel **/
	@Test
	public void testManzana() 
	{
	assertNotEquals(posx1, posy1);
	assertNotEquals(posx2, posy2);	
	}
	
}