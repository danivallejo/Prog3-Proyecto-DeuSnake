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
		private int tiempo;
		private static ArrayList<String> listaPlayers;
		
	
	
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

		public int getTiempo() {
			return tiempo;
		}

		public void setTiempo(int tiempo) {
			this.tiempo = tiempo;
		}

		public static ArrayList<String> getListaPlayers() {
			return listaPlayers;
		}

		public void setListaPlayers(ArrayList<String> listaPlayers) {
			this.listaPlayers = listaPlayers;
		}

		/** Devuelve los emails como un string único, en una lista separada por comas
		 * @return	Lista de emails
		 */
		public String getEmails() 
		{
			String ret = "";
			if (listaPlayers.size()>0) ret = listaPlayers.get(0);
			for (int i=1; i<listaPlayers.size(); i++) ret += (", " + listaPlayers.get(i));
			return ret;
		}
		
		/** Constructor privado, sólo para uso interno
		 */
		private Player() 
		{
			
		}
		
		/** Constructor principal de usuario
		 * @param nick
		 * @param puntuacion
		 * @param tiempo
		 * @param listaPlayers
		 */
		public Player(String nick, int puntuacion, int tiempo,
				ArrayList<String> listaPlayers) 
		{
			super();
			this.nick = nick;
			this.puntuacion = puntuacion;
			this.tiempo = tiempo;
			this.listaPlayers = listaPlayers;
		}
		
		/** Constructor de usuario recibiendo los Jugadores como una lista de parámetros de tipo String
		 * @param nick
		 * @param puntuacion
		 * @param tiempo
		 * @param listaPlayers
		 */
		public Player(String nick, int puntuacion, int tiempo,
				String... listaPlayers ) {
			this( nick, puntuacion, tiempo, new ArrayList<String>( Arrays.asList(listaPlayers)));
		}

		@Override
		public String toString() {
			return "Usuario: " + nick + "Puntuaci�n: " + puntuacion + " tiempo: " + tiempo + 
				"\nJugadores: " + listaPlayers;
		}

		/** Devuelve los datos del usuario en una línea separados por comas<p>
		 * Formato: nick,password,nombre,apellidos,telefono,fechaUltimoLogin(msgs.),tipo,email1,email2...
		 * @return	Línea con los datos formateados
		 */
		public String aLinea() {
			String ret = nick + "," + puntuacion + "," + tiempo;
			for (String player : listaPlayers) {
				ret = ret + "," + player;
			}
			return ret;
		}

		/** Devuelve los datos del usuario en una varias líneas después de cada tag de dato<p>
		 * Formato: <p>
		 * [nick] nick\n<p>
		 * [password] password\n<p>
		 * [nombre] nombre\n<p>
		 * [apellidos] apellidos\n<p>
		 * [telefono] telefono\n<p>
		 * [fechaUltimoLogin] fechaUltimoLogin(msgs.)\n<p>
		 * [tipo] tipo\n<p>
		 * [emails] email1,email2...
		 * @return	Líneas de texto en un string con los datos formateados
		 */
		public String aLineasConTags() {
			String ret = "[nick] "            + nick 
					+ "\n[puntuacion] "       + puntuacion
					+ "\n[tiempo] "           + tiempo 
					+ "\n[players] ";
			String sep = "";
			for (String player : listaPlayers) {
				ret = ret + sep + player;
				sep = ",";
			}
			return ret;
		}

		/** Crea y devuelve un nuevo Usuario partiendo de los datos de una línea separados por comas
		 * Formato: nick,password,nombre,apellidos,telefono,fechaUltimoLogin(msgs.),tipo,email1,email2...
		 * @param linea	Línea de texto
		 * @return	Usuario creado partiendo de la línea, null si hay cualquier error
		 */
		public static Player crearDeLinea( String linea ) {
			Player u = new Player();
			StringTokenizer st = new StringTokenizer( linea, "," );
			try {
				u.nick = st.nextToken();
				u.puntuacion = Integer.parseInt(st.nextToken());
				u.tiempo = Integer.parseInt(st.nextToken());
				u.listaPlayers = new ArrayList<String>();
				while (st.hasMoreTokens()) {
					u.listaPlayers.add( st.nextToken() );
				}
				return u;
			} catch (NoSuchElementException e) {  // Error en datos insuficientes (faltan campos)
				return null;
			} catch (Exception e) {  // Cualquier otro error
				return null;
			}
		}

		/** Crea y devuelve un nuevo Usuario partiendo de los datos de un fichero
		 * en varias líneas con tags, con el formato<p>:
		 * [nick] nick\n<p>
		 * [password] password\n<p>
		 * [nombre] nombre\n<p>
		 * [apellidos] apellidos\n<p>
		 * [telefono] telefono\n<p>
		 * [fechaUltimoLogin] fechaUltimoLogin(msgs.)\n<p>
		 * [tipo] tipo\n<p>
		 * [emails] email1,email2...\n<p>
		 * [FINUSUARIO]
		 * Si se encuentra la linea 
		 * @return	Usuario creado con los valores leídos, o null si hay cualquier error
		 */
		public static Player crearDeLineasConTags( BufferedReader br ) {
			Player u = new Player();
			try {
				u.nick = chequearYLeerTag( br, "[nick]" );
				String valor = chequearYLeerTag( br, "[puntuacion]");
				u.puntuacion = Integer.parseInt( valor );
				valor = chequearYLeerTag( br, "[tiempo]");
				u.tiempo = Integer.parseInt( valor );
				valor = chequearYLeerTag( br, "[players]" );
				u.listaPlayers = new ArrayList<String>();
				StringTokenizer st = new StringTokenizer( valor, "," );
				while (st.hasMoreTokens()) {
					u.listaPlayers.add( st.nextToken() );
				}
				chequearYLeerTag( br, "[FINUSUARIO]" );
				return u;
			} catch (IOException e) {  // Error en lectura del fichero
				return null;
			} catch (Exception e) {  // Cualquier otro error
				return null;
			}
		}

			// Lee del fichero una línea, intentando comprobar si empieza en el tag indicado
			// y tiene un espacio después y el valor correspondiente.
			// Devuelve ese valor, o genera una excepción si hay error
			private static String chequearYLeerTag( BufferedReader br, String tag ) throws IOException, Exception {
				String val = br.readLine();
				if (val.startsWith(tag)) {
					val = val.substring( tag.length() );
					if (val.startsWith(" ")) val = val.substring(1);  // Quita el primer espacio
					return val;
				} else {
					throw new Exception("Tag incorrecto. Esperado '" + tag + " ' y encontrada línea " + val );
				}
			}

		// Dos usuarios son iguales si TODOS sus campos son iguales
		public boolean equals( Object o ) {
			Player u2 = null;
			if (o instanceof Player) u2 = (Player) o;
			else return false;  // Si no es de la clase, son diferentes
			return (nick.equals(u2.nick))
				&& (puntuacion == u2.puntuacion )
				&& (tiempo == u2.tiempo)
				&& (listaPlayers.equals( u2.listaPlayers ));
		}

		/** Inicializa una BD SQLITE y devuelve una conexión con ella
		 * @param nombreBD	Nombre de fichero de la base de datos
		 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
		 */
		public static Connection initBD( String nombreBD ) {
			try {
			    Class.forName("org.sqlite.JDBC");
			    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			    return con;
			} catch (ClassNotFoundException | SQLException e) {
				return null;
			}
		}
		
		/** Crea la tabla de usuarios en una base de datos
		 * @param con	Conexión ya creada y abierta a la base de datos
		 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
		 */
		public static Statement crearTablaBD( Connection con ) {
			try {
				Statement statement = con.createStatement();
				statement.setQueryTimeout(30);  // poner timeout 30 msg
				// La borramos si ya existe:
				statement.executeUpdate("drop table if exists usuario");
				statement.executeUpdate("create table usuario " +
					"(nick string, puntuacion integer, tiempo integer, players string)");
				return statement;
			} catch (SQLException e) {
				return null;
			}
		}
		
		/** Añade el usuario a la tabla abierta de BD, usando la sentencia
		 * (INSERT de SQL)
		 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
		 * @return	true si la inserción es correcta, false en caso contrario
		 */
		public boolean anyadirFilaATabla( Statement st ) {
			try {
				String listaPlay = "";
				String sep = "";
				for (String players : listaPlayers) {
					listaPlay = listaPlay + sep + players;
					sep = ",";
				}
				String sentSQL = "insert into usuario values(" +
						"'" + nick + "', " +
						"'" + puntuacion + "', " +
						"'" + tiempo + "', " +
						"'" + listaPlay + "')";
				System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
				int val = st.executeUpdate( sentSQL );
				if (val!=1) return false;  // Se tiene que añadir 1 - error si no
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		/** Realiza una consulta a la tabla abierta de usuarios de la BD, usando la sentencia
		 * (SELECT de SQL)
		 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
		 * @param codigoSelect
		 * @return	lista de usuarios cargados desde la base de datos, null si hay cualquier error
		 */
		public static ArrayList<Player> consultaATabla( Statement st, String codigoSelect ) {
			ArrayList<Player> ret = new ArrayList<>();
			try {
				String sentSQL = "select * from usuario";
				if (codigoSelect!=null && !codigoSelect.equals(""))
					sentSQL = sentSQL + " where " + codigoSelect;
				System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
				ResultSet rs = st.executeQuery( sentSQL );
				while (rs.next()) {
					Player u = new Player();
					u.nick = rs.getString( "nick" );
					u.puntuacion = rs.getInt("puntuacion");
					u.tiempo = rs.getInt("tiempo");
					u.listaPlayers = new ArrayList<String>();
					StringTokenizer stt = new StringTokenizer( rs.getString("emails"), "," );
					while (stt.hasMoreTokens()) {
						u.listaPlayers.add( stt.nextToken() );
					}
					ret.add( u );
				}
				rs.close();
				return ret;
			} catch (IllegalArgumentException e) {  // Error en tipo usuario (enumerado)
				e.printStackTrace();
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		
		/** Cierra la base de datos abierta
		 * @param con	Conexión abierta de la BD
		 * @param st	Sentencia abierta de la BD
		 */
		public static void cerrarBD( Connection con, Statement st ) {
			try {
				if (st!=null) st.close();
				if (con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}