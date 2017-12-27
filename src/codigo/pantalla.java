package codigo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class pantalla extends JFrame
{

	private static final long serialVersionUID = 1L;  // Para serialización
	JPanel pPrincipal; // Panel del juego (layout nulo)
	JPanel pCabecera;
	bloqueJuego miBloque; // Coche del juego
	manzana miManzana = null;
	MiRunnable miHilo = null;// Hilo del bucle principal de juego
	RandomApple miHilo2 = null;
	int num_casillas[][] = new int [17][15];
	Point posicion = new Point(6, 8);
	boolean posible = true;
	
	/** Constructor de la ventana de juego. Crea y devuelve la ventana inicializada
	 * sin coches dentro
	 */
	public pantalla() {
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		pCabecera = new JPanel();
		//JMenuBar Rankings = new JMenuBar( );
		
		// Formato y layouts
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.CYAN);
		// Añadido de componentes a contenedores
		add( pPrincipal, BorderLayout.CENTER );
//		pCabecera.add( Rankings );
//		pBotonera.add( bFrenar );
//		pBotonera.add( bGiraIzq );
//		pBotonera.add( bGiraDer );
		add( pCabecera, BorderLayout.NORTH );
//		pCabecera.setBounds(200, 200, 700, 200);
		// Formato de ventana
		setSize( 850, 850);
		// Escuchadores de botones
//	Rankings.addActionListener( new ActionListener() 
//		{
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (miCoche.getVelocidad()==0)
//					miCoche.acelera( +5 );
//				else 
//					miCoche.acelera( +5 );
//					// miCoche.acelera( miCoche.getVelocidad()*0.10 );   // para acelerar progresivo
//				System.out.println( "Nueva velocidad de coche: " + miCoche.getVelocidad() );
//			}
//		});
//		bFrenar.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				miCoche.acelera( -5 );
//				System.out.println( "Nueva velocidad de coche: " + miCoche.getVelocidad() );
//			}
//		});
//		bGiraIzq.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				miCoche.gira( +10 );
//				System.out.println( "Nueva dirección de coche: " + miCoche.getDireccionActual() );
//			}
//		});
//		bGiraDer.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				miCoche.gira( -10 );
//				System.out.println( "Nueva dirección de coche: " + miCoche.getDireccionActual() );
//			}
//		});
		// Añadido para que también se gestione por teclado con el KeyListener
		pPrincipal.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			if(posible)
			{
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: {
						miBloque.setDireccionActual(90);  
						break;
					}
					case KeyEvent.VK_DOWN: {
						miBloque.setDireccionActual(270);  
						break;
					}
					case KeyEvent.VK_LEFT: {
						miBloque.setDireccionActual(180);  
						break;
					}
					case KeyEvent.VK_RIGHT: {
						miBloque.setDireccionActual(0);  
						break;
					}
				}
				
			}
			}
		});
		pPrincipal.setFocusable(true);
		pPrincipal.requestFocus();
		pPrincipal.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				pPrincipal.requestFocus();
			}
		});
		// Cierre del hilo al cierre de la ventana
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (miHilo!=null) miHilo.acaba();
			}
		});
	}
	
	/** Crea un coche nuevo y lo añade a la ventana 
	 * @param 	Posición X de pixel del nuevo coche
	 * @param e	Posición Y de píxel del nuevo coche
	 */
	public void creaBloque( ) 
	{
		// Crear y añadir el coche a la ventana
		miBloque = new bloqueJuego();
		miBloque.setPosicion( 300, 400);
		pPrincipal.add( miBloque.getGrafico());
		
	}
	
	/** Programa principal de la ventana de juego
	 * @param args
	 */
	
	
	/** Clase interna para implementación de bucle principal del juego como un hilo
	 * @author Andoni Eguíluz
	 * Facultad de Ingeniería - Universidad de Deusto (2014)
	 */
	class MiRunnable implements Runnable {
		boolean sigo = true;

		@Override
		public void run() {
			// Bucle principal forever hasta que se pare el juego...
			while (sigo) {
				// Mover coche
			posible = false;
			
				for (int i = 0; i<10; i++)
				{
					try {
						miBloque.mueveX();
						miBloque.mueveY();
						Thread.sleep(5);
					} catch (Exception e) {
					}
				}
			posible = true;
				// Chequear choques
				if (miBloque.getPosX() < -JLabelBloque.TAMANYO_BLOQUE/2 || miBloque.getPosX()>pPrincipal.getWidth()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
					// Espejo horizontal si choca en X
					
					
	
					System.out.println( "Game Over");
					
					miHilo.acaba();
				
				}
				// Se comprueba tanto X como Y porque podría a la vez chocar en las dos direcciones
				if (miBloque.getPosY() < -JLabelBloque.TAMANYO_BLOQUE/2 || miBloque.getPosY()>pPrincipal.getHeight()-JLabelBloque.TAMANYO_BLOQUE/2 ) {
					// Espejo vertical si choca en Y
					System.out.println( "Game Over");

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
		public void acaba() {
			sigo = false;
		}
	};
	
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
				if(miManzana==null)
				{
				r= new Random();
				apple_posX = r.nextInt(18);
				apple_posY = r.nextInt(16);
				
				miManzana = new manzana();
				miManzana.setLocation(apple_posX*50, apple_posY*50+100);
				pPrincipal.add(miManzana);
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
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		pantalla miVentana = new pantalla();
		
		miVentana.creaBloque();
		miVentana.setVisible( true );
		
		//miVentana.miBloque.setPiloto( "Fernando Alonso" );
		// Crea el hilo de movimiento del coche y lo lanza
		
		miVentana.miHilo = miVentana.new MiRunnable();  // Sintaxis de new para clase interna
		Thread nuevoHilo = new Thread( miVentana.miHilo );
		nuevoHilo.start();
		
		miVentana.miHilo2 = miVentana.new RandomApple();
		Thread nuevoHilo2 = new Thread( miVentana.miHilo2 );
		nuevoHilo2.start();
		
	}	

}
