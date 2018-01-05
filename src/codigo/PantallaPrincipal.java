package codigo;

import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PantallaPrincipal extends JFrame
{

	private static final long serialVersionUID = 1L;  // Para serialización
	
	MiRunnable miHilo = null;// Hilo del bucle principal de juego
	RandomApple miHilo2 = null;
	cronometro miHilo3 = null;
	int num_casillas[][] = new int [17][15];
	Point posicion = new Point(6, 8);
	boolean posible = true;
	static int puntuacion = 0;
	String tiempofinal;
	
	/** Constructor de la ventana de juego. Crea y devuelve la ventana inicializada
	 * sin coches dentro
//	 */

	class MiRunnable implements Runnable {
		boolean sigo = true;
		String Nombre = PantallaInicio.Nombre;

		@Override
		public void run() {
			// Bucle principal forever hasta que se pare el juego...
			while (sigo) {
				// Mover coche
			posible = false;
			
<<<<<<< HEAD
			for (int i = 0; i<Principal.growUp; i++)
			{
				try {
					Principal.miBloque[i].mueveX();
					Principal.miBloque[i].mueveY();
					if(i!=0)	Principal.miBloque[i].setDireccionActual(Principal.miBloque[i-1].miDireccionActual);
					
					Thread.sleep(5);
				} catch (Exception e) {
				}
			}
			posible = true;
				// Chequear choques
			if (Principal.miBloque[0].getPosX() < -JLabelBloque.TAMANYO_BLOQUE/2 || Principal.miBloque[0].getPosX()>Principal.pPrincipal.getWidth()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
				// Espejo horizontal si choca en X
=======
				for (int i = 0; i<Principal.growUp; i++)
				{
					try {
						Principal.miBloque[i].mueveX();
						Principal.miBloque[i].mueveY();
						if(i!=0)	Principal.miBloque[i].setDireccionActual(Principal.miBloque[i-1].miDireccionActual);
						
						Thread.sleep(5);
					} catch (Exception e) {
					}
				}
				
			posible = true;
				// Chequear choques
				if (Principal.miBloque[0].getPosX() < -JLabelBloque.TAMANYO_BLOQUE/2 || Principal.miBloque[0].getPosX()>Principal.pPrincipal.getWidth()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
					// Espejo horizontal si choca en X
>>>>>>> 57e5fc69adc00ed2fc6aef8e1ed0308237463acc
					
					OperacionesGuardado.RegistrarJugador(Nombre, puntuacion, tiempofinal);
					System.out.println( "Game Over");
					System.out.println("La puntuación es de: " + puntuacion);
					
					miHilo.acaba();
				
				}
				// Se comprueba tanto X como Y porque podría a la vez chocar en las dos direcciones
<<<<<<< HEAD
			if (Principal.miBloque[0].getPosY() < -JLabelBloque.TAMANYO_BLOQUE/2 || Principal.miBloque[0].getPosY()>Principal.pPrincipal.getHeight()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
				// Espejo vertical si choca en Y
					
					OperacionesGuardado.RegistrarJugador(Nombre, puntuacion, tiempofinal);
=======
				if (Principal.miBloque[0].getPosY() < -JLabelBloque.TAMANYO_BLOQUE/2 || Principal.miBloque[0].getPosY()>Principal.pPrincipal.getHeight()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
					// Espejo vertical si choca en Y
>>>>>>> 57e5fc69adc00ed2fc6aef8e1ed0308237463acc
					System.out.println( "Game Over");
					System.out.println("La puntuación es de: " + puntuacion);
					
					miHilo.acaba();
					
				}
				// Dormir el hilo 40 milisegundos
				try {
					Thread.sleep( 40);
				} catch (Exception e) {
				}
			}
		}
		/** Ordena al hilo detenerse en cuanto sea posible
		 */
		public void acaba() 
		{
			sigo = false;
		}
	};
	public void growUp()
	{
		Principal.miBloque[Principal.growUp]=new bloqueJuego();
		Principal.miBloque[Principal.growUp].setPosicion(Principal.miBloque[Principal.growUp-1].getPosX()-30, Principal.miBloque[Principal.growUp-1].getPosY());
		Principal.pPrincipal.add(Principal.miBloque[Principal.growUp].getGrafico());
		Principal.miBloque[Principal.growUp].setDireccionActual(Principal.miBloque[Principal.growUp-1].miDireccionActual);
		Principal.growUp++;
	}
	
	public void growUp()
	{
		Principal.miBloque[Principal.growUp]=new bloqueJuego();
		Principal.miBloque[Principal.growUp].setPosicion(Principal.miBloque[Principal.growUp-1].getPosX()-30, Principal.miBloque[Principal.growUp-1].getPosY());
		Principal.pPrincipal.add(Principal.miBloque[Principal.growUp].getGrafico());
		Principal.miBloque[Principal.growUp].setDireccionActual(Principal.miBloque[Principal.growUp-1].miDireccionActual);
		Principal.growUp++;
	}
	
	class RandomApple implements Runnable {
		boolean sigo2 = true;
		@Override
		public void run() {
			
			Random r;
			int  apple_posX;
			int  apple_posY;
			
		
			
			// Bucle principal forever hasta que se pare el juego...
			while (true) 
			{
				r= new Random();
				apple_posX = r.nextInt(18);
				apple_posY = r.nextInt(16);	
				
				
				if(Principal.miManzana==null)
				{
				Principal.miManzana = new manzana();
				Principal.miManzana.setLocation(apple_posX*50, apple_posY*50+100);
				Principal.pPrincipal.add(Principal.miManzana);
				System.out.println("Aparece la manzana");
				}
				
				
				if(Principal.miManzana.getLocation().distance(Principal.miBloque[0].getPosX(), Principal.miBloque[0].getPosY()) < 15)
				{	
				Principal.miManzana.setLocation(apple_posX*50, apple_posY*50+100);
				Principal.pPrincipal.add(Principal.miManzana);
				growUp();
				puntuacion = puntuacion + 1;
				
				}
			
				
				// Dormir el hilo 40 milisegundos
				try 
				{	
				Thread.sleep( 10 );
				} catch (Exception e) {
				}
				
			}
			
		}
		/** Ordena al hilo detenerse en cuanto sea posible
		 */
		public void acaba() 
		{
			sigo2 = false;
		}
		
	};
	
	
	class cronometro implements Runnable
	{
		boolean sigo3 = true;
		
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			int segundos = 0;
			int decimas = 0;
			
			

			try {
			while (sigo3)
				{
				
				if(decimas != 9)
				{ 
					decimas ++;
				}
				else
				{
					segundos ++;
					decimas = 0;
				
				}
				
					tiempofinal = segundos +"."+decimas;
					
					
					if (Principal.miBloque[0].getPosX() < -JLabelBloque.TAMANYO_BLOQUE/2 || Principal.miBloque[0].getPosX()>Principal.pPrincipal.getWidth()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
						// Espejo horizontal si choca en X
						
						System.out.println( "Game Over");
						sigo3 = false;
						 System.out.println("El tiempo final es de: "+ tiempofinal);
						
						miHilo3.acaba();
					
					}
					// Se comprueba tanto X como Y porque podría a la vez chocar en las dos direcciones
					else if (Principal.miBloque[0].getPosY() < -JLabelBloque.TAMANYO_BLOQUE/2 || Principal.miBloque[0].getPosY()>Principal.pPrincipal.getHeight()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
						// Espejo vertical si choca en Y
						System.out.println( "Game Over");
						sigo3 = false;
						 System.out.println("El tiempo final es de: "+ tiempofinal);

						miHilo3.acaba();
					}
					
					
			 System.out.println(tiempofinal);
	         Thread.sleep(99);
	         }   
			
			
	     } catch (Exception ex) 
		{
	          System.out.println(ex.getMessage());
	     }                 	
		}
		public void acaba() {
			sigo3 = false;
		}
	};
}
