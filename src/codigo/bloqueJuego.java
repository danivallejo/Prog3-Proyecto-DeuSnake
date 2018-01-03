package codigo;

import javax.swing.JLabel;

public class bloqueJuego extends bloque
{
	 private JLabelBloque miGrafico;
	 
	 /**  Crea un nuevo coche de juego
		 */
		public bloqueJuego() 
		{
			miGrafico = new JLabelBloque();
		}
		
		/** Devuelve el JLabel gráfico asociado al coche de juego
		 * @return	Etiqueta gráfica del coche
		 */
		public JLabelBloque getGrafico() {
			return miGrafico;
		}

		@Override
		public void setPosX(double posX) {
			super.setPosX(posX);
			miGrafico.setLocation((int) posX, (int) posY);
			// miGrafico.repaint();  // Al cambiar la location, Swing redibuja automáticamente
		}

		@Override
		public void setPosY(double posY) {
			super.setPosY(posY);
			miGrafico.setLocation((int) posX, (int) posY);
			miGrafico.repaint();  // Al cambiar la location, Swing redibuja automáticamente
		}

		@Override
		public void setDireccionActual( double dir ) {
			super.setDireccionActual(dir);
			miGrafico.setGiro(miDireccionActual);
			miGrafico.repaint();  // Necesario porque Swing no redibuja al cambiar el giro (no pasa nada en el JLabel)
		}
}
