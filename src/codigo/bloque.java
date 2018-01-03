package codigo;

import javax.swing.JLabel;

public class bloque 

{
	 protected double miDireccionActual; // Dirección en la que estoy mirando en grados (de 0 a 360)
	 protected double posX; // Posición en X (horizontal)
	 protected double posY; // Posición en Y (vertical)
	
	public bloque()
	{
		miDireccionActual=0.0;
		
	} 
	public double getDireccionActual() 
	{
		return miDireccionActual;
	}

	public void setDireccionActual( double dir ) 
	{
//		if (dir < 0) dir = 360 + dir;
//		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}
		
	public double getPosX() 
	{
		return posX;
	}

	public double getPosY() 
	{
		return posY;
	}
	public void setPosicion( double posX, double posY ) //Creo que este set lo creamos nosotros
	{
		setPosX( posX );
		setPosY( posY );
	}
	public void getPosicion( )
	{
		getPosX( );
		getPosY( );
	}
	
	public void setPosX( double posX ) {
		this.posX = posX; 
	}
	
	public void setPosY( double posY ) {
		this.posY = posY; 
	}
	
	/** Cambia la dirección actual del coche
	 * @param giro	Angulo de giro a sumar o restar de la dirección actual, en grados (-180 a +180)
	 * 				Considerando positivo giro antihorario, negativo giro horario
	 */
	public void gira( double giro ) 
	{
		setDireccionActual(giro );
	}
	
	/** Cambia la posición del coche dependiendo de su velocidad y dirección
	 */
	public void mueveX( ) 
	{

		if(miDireccionActual==0|| miDireccionActual==180)
		{
		setPosX(posX+(5*Math.cos(miDireccionActual/180.0*Math.PI)));
		}
	}
	public void mueveY( ) 
	{
		
		if(miDireccionActual==270|| miDireccionActual==90)
		{
		setPosY(posY+(5*-Math.sin(miDireccionActual/180.0*Math.PI)));
//		setPosX( posX + miVelocidad * Math.cos(miDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );
//		setPosY( posY + miVelocidad * -Math.sin(miDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );
		// el negativo es porque en pantalla la Y crece hacia abajo y no hacia arriba
		}
	}
	
	//TODO: podríamos hacer toString si lo necesitaramos
	

}
