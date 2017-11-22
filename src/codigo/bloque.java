package codigo;

import javax.swing.JLabel;

public class bloque 

{
	private double miVelocidad; // Velocidad en pixels/segundo
	 protected double miDireccionActual; // Direcci�n en la que estoy mirando en grados (de 0 a 360)
	 protected double posX; // Posici�n en X (horizontal)
	 protected double posY; // Posici�n en Y (vertical)
	
	public bloque()
	{
		
		miVelocidad=200.0;
		miDireccionActual=0.0;
		posX = 300;
		posY = 300;
		
	} 
	public double getMiVelocidad()
	{
		return miVelocidad;
	}
	/** Cambia la velocidad actual del coche
	 * @param miVelocidad
	 */
	public void setVelocidad( double miVelocidad ) 
	{
		this.miVelocidad = miVelocidad;
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
	
	public void setPosX( double posX ) {
		this.posX = posX; 
	}
	
	public void setPosY( double posY ) {
		this.posY = posY; 
	}
	
	/** Cambia la direcci�n actual del coche
	 * @param giro	Angulo de giro a sumar o restar de la direcci�n actual, en grados (-180 a +180)
	 * 				Considerando positivo giro antihorario, negativo giro horario
	 */
	public void gira( double giro ) 
	{
		setDireccionActual( miDireccionActual + giro );
	}
	
	/** Cambia la posici�n del coche dependiendo de su velocidad y direcci�n
	 * @param tiempoDeMovimiento	Tiempo transcurrido, en segundos
	 */
	public void mueve( double tiempoDeMovimiento ) {
		setPosX( posX + miVelocidad * Math.cos(miDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );
		setPosY( posY + miVelocidad * -Math.sin(miDireccionActual/180.0*Math.PI) * tiempoDeMovimiento );
		// el negativo es porque en pantalla la Y crece hacia abajo y no hacia arriba
	}
	
	//TODO: podr�amos hacer toString si lo necesitaramos
	

}
