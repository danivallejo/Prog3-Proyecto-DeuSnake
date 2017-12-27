package codigo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.AreaAveragingScaleFilter;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

	/** Clase para visualizar un coche en Swing como un JLabel,
	 *  con un gráfico específico de coche
	 * @author Andoni Eguíluz
	 */
	public class JLabelBloque extends JLabel 
	{
		private static final long serialVersionUID = 1L;  // Para serialización
		public static final int TAMANYO_BLOQUE= 30; // píxels (igual ancho que algo)
		public static final int RADIO_ESFERA_BLOQUE = 10;		
		ImageIcon icono;
		
		//TODO: Hay que reducir la imagen a 20 pixels. Ahora mismo solo se ve parte de la imagen, hay que reducir!!!!
	
		/** Construye y devuelve el JLabel del coche con su gráfico y tamaño
		 */
		public JLabelBloque() {
	
			// Esto se haría para acceder por sistema de ficheros
			// 		super( new ImageIcon( "bin/ud/prog3/pr00/coche.png" ) );
			// Esto se hace para acceder tanto por recurso (jar) como por fichero
			try {
				
				icono= new ImageIcon(JLabelBloque.class.getResource( "Image.jpg" ).toURI().toURL() );
				
			} catch (Exception e) {
				System.err.println( "Error en carga de recurso: coche.png no encontrado" );
				e.printStackTrace();
			}
			setSize(TAMANYO_BLOQUE, TAMANYO_BLOQUE);
			// Esto sería útil cuando hay algún problema con el gráfico: borde de color del JLabel
			setBorder( BorderFactory.createLineBorder( Color.CYAN, 1 ));
		}
		
		// giro
		private double miGiro = Math.PI/2;
		/** Cambia el giro del JLabel
		 * @param gradosGiro	Grados a los que tiene que "apuntar" el coche,
		 * 						considerados con el 0 en el eje OX positivo,
		 * 						positivo en sentido antihorario, negativo horario.
		 */
		public void setGiro( double gradosGiro ) 
		{
			// De grados a radianes...
			miGiro = gradosGiro/180*Math.PI;
			// El giro en la pantalla es en sentido horario (inverso):
			miGiro = -miGiro;  // Cambio el sentido del giro
			// Y el gráfico del coche apunta hacia arriba (en lugar de derecha OX)
			miGiro = miGiro + Math.PI/2; // Sumo 90º para que corresponda al origen OX
		}
		

		// Redefinición del paintComponent para que se escale y se rote el gráfico
		@Override
			protected void paintComponent(Graphics g) {
			Icon nuevoIcono = new ImageIcon(icono.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			setIcon(nuevoIcono);
			
			super.paintComponent(g);

	}
	}

	
	

