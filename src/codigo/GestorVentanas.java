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
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/** Clase para gestionar visibilización de ventanas de forma global.
 * Las ventanas se deben crear sin ser visibles, y añadirse al gestor
 *   con el método add( JFrame )
 * Se deben crear sin dispose automático al cierre 
 *   (para poderse visualizar varias veces).
 * Este método se ejecuta una única vez al inicio de la clase.
 * @author andoni
 */
public class GestorVentanas 
{

	/* Lista de ventanas. Gestión interna de todas las ventanas utilizadas */
	private static ArrayList<JFrame> listaVentanas = init();

	/** Inicializador de Ventanas.
	 * Podría crear e inicializar todas las ventanas del gestor.
	 * (o se van añadiendo con el método add)
	 * @return	ArrayList de todas las ventanas creadas
	 */
	private static ArrayList<JFrame> init() {
		ArrayList<JFrame> lista = new ArrayList<JFrame>();
		// Si queremos ventanas por defecto creadas se podrían poner aquí
		// lista.add( new Ventana... );
		return lista;
	}

	/** Añade una ventana al gestor
	 * @param vent
	 */
	public static void add( JFrame vent ) {
		listaVentanas.add( vent );
	}
	
	/** Libera y cierra todas las ventanas del gestor
	 */
	public static void closeAndDispose() {
		for (JFrame vent : listaVentanas) {
			vent.dispose();
		}
		listaVentanas.clear();
	}
	
	/** Hace visible la ventana indicada.
	 * Si hay algún error en los parámetros, no hace nada.
	 * @param ventanaAVisibilizar	Clase de la ventana a hacer visible
	 * @param ocultarElResto	Si true, oculta el resto. Si no, las deja como estuvieran
	 * @param numDeVentana	Si hay más de una ventana de la misma clase, índice de la ventana a visibilizar
	 */
	public static void hacerVisible(Class<?> ventanaAVisibilizar, boolean ocultarElResto, int numDeVentana ) {
		for (JFrame vent : listaVentanas) {
			if (vent.getClass().isAssignableFrom( ventanaAVisibilizar )) {
				// Si la clase de la ventana es igual o descendiente de la indicada
				if (numDeVentana > 0) {
					// Si no es la primera, esperar la siguiente
					numDeVentana--;
					if (ocultarElResto) vent.setVisible( false );
				} else if (numDeVentana == 0) {  // Si lo es, visibilizarla
					vent.setVisible( true );
					numDeVentana--;
				} else {
					vent.setVisible( false );
				}
			} else {
				if (ocultarElResto) vent.setVisible( false );
			}
		}
	}

	/** Oculta la ventana indicada.
	 * Si hay algún error en los parámetros, no hace nada.
	 * @param ventanaAOcultar	Clase de la ventana a hacer visible
	 * @param numDeVentana	Si hay más de una ventana de la misma clase, índice de la ventana a ocultar
	 */
	public static void ocultar(Class<?> ventanaAVisibilizar, int numDeVentana ) {
		for (JFrame vent : listaVentanas) {
			if (vent.getClass().isAssignableFrom( ventanaAVisibilizar )) {
				// Si la clase de la ventana es igual o descendiente de la indicada
				if (numDeVentana > 0) {
					// Si no es la primera, esperar la siguiente
					numDeVentana--;
				} else {  // Si lo es, visibilizarla
					vent.setVisible( false );
					break;
				}
			}
		}
	}

	
	/* Método de prueba */
	public static void main (String s[]) {
		add( new PantallaInicio() );
		add( new Principal() );
		add( new GameOver() );
		
		try
		{
		
		hacerVisible( PantallaInicio.class, true, 0 ); //Pantalla de Inicio OK
		if(PantallaInicio.jugar.isSelected()==true)
		{
		hacerVisible( Principal.class, true, 0 ); //Sale la pantalla principal pero no desaparece la pantalla de inicio
		}
		else
		{
	
		}
		if(false)
		{
		hacerVisible( GameOver.class, true, 0); //Buscar forma para ir hasta metodo cuando acabe la partida!!!!
		}
		if(GameOver.retry.isSelected()==true)
		{
		hacerVisible( Principal.class, true, 0 );// Volver a jugar OK
		}
		else if(GameOver.salir.isSelected()==true)
		{
		//Hay que corregir el boton de salir.
		}
		else
		{
		Thread.sleep( 15000 );
		ocultar( GameOver.class, 0 );
		}
		
		closeAndDispose();
		}catch(InterruptedException e)
		{
			
		}
	}
}




/* Ejemplos de ventanas para la prueba */

@SuppressWarnings("serial")
class PantallaInicio extends JFrame 
{
	JTextField usuario;
	protected static JButton jugar;

	JPanel panel;
	
	public PantallaInicio() 
	{
	usuario =new JTextField();
	jugar = new JButton("Empezar Partida");
	
	usuario.setVisible(true);
	jugar.setVisible(true);
	

	panel =new JPanel();
	
	add(panel, BorderLayout.CENTER);
	
	panel.add(usuario);
	panel.add(jugar);
	
	
		if(usuario != null)
		{
			jugar.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					Principal miVentana = new Principal();
					PantallaPrincipal miVentana1 = new PantallaPrincipal();
					
					miVentana.creaBloque();
					miVentana.setVisible( true );
					
					//miVentana.miBloque.setPiloto( "Fernando Alonso" );
					// Crea el hilo de movimiento del coche y lo lanza
					
					miVentana1.miHilo = miVentana1.new MiRunnable();  // Sintaxis de new para clase interna
					
					Thread nuevoHilo = new Thread( miVentana1.miHilo );
					nuevoHilo.start();
					
					miVentana1.miHilo2 = miVentana1.new RandomApple();
					
					Thread nuevoHilo2 = new Thread( miVentana1.miHilo2 );
					nuevoHilo2.start();
					
					Thread nuevoHilo3 = new Thread (miVentana1.miHilo3 );
					nuevoHilo3.start();
					
				}
				
			});
		}
		
		
		
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setTitle("Pantalla de Inicio");
		setSize( 300, 100 );
		setLocationRelativeTo(null);
	}
}
@SuppressWarnings("serial")
class Principal extends JFrame
{
	private static final long serialVersionUID = 1L;  // Para serializaci�n
	static JPanel pPrincipal; // Panel del juego (layout nulo)
	JPanel pCabecera;
	static bloqueJuego miBloque; // Coche del juego
	static manzana miManzana = null;
	PantallaPrincipal.MiRunnable miHilo = null;// Hilo del bucle principal de juego
//PantallaPrincipal.RandomApple miHilo2 = null;
	int num_casillas[][] = new int [17][15];
	Point posicion = new Point(6, 8);
	boolean posible = true;
	
	/** Constructor de la ventana de juego. Crea y devuelve la ventana inicializada
	 * sin coches dentro
	 */
	public Principal()
	{
			
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	
			pPrincipal = new JPanel();
			pCabecera = new JPanel();
			
			// Formato y layouts
			pPrincipal.setLayout( null );
			pPrincipal.setBackground( Color.CYAN);
			
			add( pPrincipal, BorderLayout.CENTER );

			pCabecera.setBounds(0,100,850,100);
			
			add( pCabecera, BorderLayout.NORTH );
			
			
			setSize( 850, 750);
			
			pPrincipal.addKeyListener( new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					
				if(posible)
				{
					switch (e.getKeyCode()) {
						case KeyEvent.VK_UP: 
						{
							if(miBloque.getDireccionActual()==90||miBloque.getDireccionActual()==270)
							{
								
							}
							else
							{
								miBloque.setDireccionActual(90);  
							}
							break;
						}
						case KeyEvent.VK_DOWN: 
						{
							if(miBloque.getDireccionActual()==90||miBloque.getDireccionActual()==270)
							{
								
							}
							else
							{
							miBloque.setDireccionActual(270); 
							}
							break;
							
						}
						case KeyEvent.VK_LEFT: 
						{
							
							if(miBloque.getDireccionActual()==0||miBloque.getDireccionActual()==180)
							{
								
							}
							else
							{
							miBloque.setDireccionActual(180); 
							}
							break;
						}
						case KeyEvent.VK_RIGHT: 
						{
							if(miBloque.getDireccionActual()==0||miBloque.getDireccionActual()==180)
							{
								
							}
							else
							{
							miBloque.setDireccionActual(0);  
							}
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
	public void creaBloque( ) 
	{
		// Crear y a�adir el coche a la ventana
		miBloque = new bloqueJuego();
		miBloque.setPosicion( 300, 400);
		pPrincipal.add( miBloque.getGrafico());
		
	}
}



@SuppressWarnings("serial")
class GameOver  extends JFrame {
	
protected static JButton retry;
protected static JButton salir;
JPanel panel;
	
	public GameOver() 
	{
		retry =new JButton("Volver a jugar");
		salir =new JButton("Salir");
		panel = new JPanel();
		
		
		retry.setVisible(true);
		salir.setVisible(true);
		
		
		add(panel, BorderLayout.CENTER);
		
		panel.add(retry);
		panel.add(salir);
		
		salir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
				// TODO Auto-generated method stub
			}
			
		});
		retry.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Principal miVentana = new Principal();
				PantallaPrincipal miVentana1 = new PantallaPrincipal();
				
				miVentana.creaBloque();
				miVentana.setVisible( true );
				
				//miVentana.miBloque.setPiloto( "Fernando Alonso" );
				// Crea el hilo de movimiento del coche y lo lanza
				
				miVentana1.miHilo = miVentana1.new MiRunnable();  // Sintaxis de new para clase interna
				
				Thread nuevoHilo = new Thread( miVentana1.miHilo );
				nuevoHilo.start();
				
				miVentana1.miHilo2 = miVentana1.new RandomApple();
				
				Thread nuevoHilo2 = new Thread( miVentana1.miHilo2 );
				nuevoHilo2.start();
			}
			
		});
		
		setTitle( "Game Over" );
		setSize( 400, 300 );
	}
}

