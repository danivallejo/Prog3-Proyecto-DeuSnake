package codigo;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

	/** Clase para gestionar usuarios. Ejemplo para ver guardado y recuperación desde ficheros
	 * @author Andoni Eguíluz Morán
	 * Facultad de Ingeniería - Universidad de Deusto
	 */
	public class Player implements Serializable 
	{
		
		private static final long serialVersionUID = 1L;
		private String nick;
		private int puntuacion;
		private String tiempo;
		
	
		public Player()
		{
		}
		public Player(String nick, int puntuacion, String tiempo) 
		{	
			this.nick = nick;
			this.puntuacion = puntuacion;
			this.tiempo = tiempo;
		}

		public String getNick() {
			return nick;
		}

		public void setNick(String nick) {
			this.nick = nick;
		}

		public int getPuntuacion() {
			return puntuacion;
		}

		public void setPuntuacion(int puntuacion) {
			this.puntuacion = puntuacion;
		}

		public String getTiempo() {
			return tiempo;
		}

		public void setTiempo(String tiempo) {
			this.tiempo = tiempo;
		}



		@Override
		public String toString() {
			return "Usuario: " + nick + "Puntuaci�n: " + puntuacion + " tiempo: " + tiempo;
		}
		}
