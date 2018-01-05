package codigo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import COMUN.clsConstantes.enFicDatos;
import LD.clsDatos;
import LD.itfDatos;

public class OperacionesGuardado 
{
	public static ArrayList<Player> LeerFichero()
	{
		itfDatos objDatos;
		ArrayList<Player> ListaJugadores;
		ArrayList<Serializable> ListaSerializable;
		
		objDatos = new clsDatos();
		ListaJugadores = new ArrayList <Player>();
		ListaSerializable = new ArrayList<Serializable>();
		
		try 
		{
			objDatos.ComenzarRead(enFicDatos.FICHERO_DATOS_JUGADOR);
			
		} catch (IOException e) {
			
			
		}
		
		ListaSerializable = objDatos.Read();
		
		for(Serializable aux: ListaSerializable)
		{
			ListaJugadores.add((Player)aux);
		}
		
		
		objDatos.TerminarRead();
		
		return ListaJugadores;
		
	}
	

	public static void GuardarFichero(ArrayList<Player>ListaJugadores)
	{
		itfDatos objDatos;
		
		objDatos = new clsDatos();
		
		objDatos.ResetFile(enFicDatos.FICHERO_DATOS_JUGADOR);
		objDatos.ComenzarSave(enFicDatos.FICHERO_DATOS_JUGADOR);
		
		for(Player aux : ListaJugadores)
		{
			objDatos.Save((Serializable) aux);
		}
		
		objDatos.TerminarSave();
	}
	
	public static void RegistrarJugador(String nick, int puntuacion, String tiempo) 
	{
		Player a;
		itfDatos ObjDatos;
		
		a = new Player (nick, puntuacion, tiempo);
		
		
		
			ObjDatos = new clsDatos();
			
			ObjDatos.ComenzarSave(enFicDatos.FICHERO_DATOS_JUGADOR);
			ObjDatos.Save(a);
			ObjDatos.TerminarSave();
		
		
	}
}
