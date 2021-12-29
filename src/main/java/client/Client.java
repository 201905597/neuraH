package client;

import configuration.PropertiesISW;
import dominio.Actividad;
import dominio.Mes;
import message.Message;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Client {
    private String host;
    private int port;
    final static Logger logger = Logger.getLogger(Client.class);
    //public sendMessage(String context, Array)

    public void metodoClient(String tipoMensaje, HashMap<String, Object> session) {
        //Configure connections

        String host = PropertiesISW.getInstance().getProperty("host");
        int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
        Logger.getRootLogger().info("Host: " + host + " port" + port);
        //Create a cliente class
        Client cliente = new Client(host, port);

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(tipoMensaje);
        mensajeEnvio.setSession(session);

        cliente.sent(mensajeEnvio, mensajeVuelta);

        switch (mensajeVuelta.getContext()) {
            case "/peticionAccesoResponse1":
                int respuesta1 = (Integer) mensajeVuelta.getSession().get("RespuestaAcceso1");
                session.put("RespuestaAcceso1", respuesta1);
                break;

            case "/peticionAccesoResponse2":

                int respuesta2 = (Integer) mensajeVuelta.getSession().get("RespuestaAcceso2");
                session.put("RespuestaAcceso2", respuesta2);
                break;

            case "/animoUsuarioResponse":
                System.out.println("se ha realizado la insercion correctamente");
                break;

            case "/recuperacionAnimoResponse":
                HashSet<Mes> respuesta3 = new HashSet<Mes>();
                respuesta3 = (HashSet<Mes>) mensajeVuelta.getSession().get("RespuestaRecAnimos");
                session.put("RespuestaRecAnimos", respuesta3); //añadido despues de teams
                System.out.println("éxito en la recuperación de ánimos");
                break;
            case "/habitoUsuarioResponse":
                System.out.println("se ha añadido el habito correctamente");
                break;
            case "/recuperacionHabitoResponse":
                HashSet<Mes> respuesta4 = new HashSet<Mes>();
                respuesta4 = (HashSet<Mes>) mensajeVuelta.getSession().get("RespuestaRecHabitos");
                session.put("RespuestaRecHabitos", respuesta4);
                System.out.println("éxito en la recuperación de hábitos");
                break;
            case "/recuperacionNombreHabitosResponse":
                HashSet<String> respuesta6 = new HashSet<String>();
                respuesta6 = (HashSet<String>) mensajeVuelta.getSession().get("RespuestaRecNombreHabitos");
                session.put("RespuestaRecNombreHabitos", respuesta6);
                System.out.println("éxito en la recuperación de los nombres de hábitos");
                break;
            case "/recuperacionPacientesResponse":
                HashMap<String,String> respuesta7 = new HashMap<String,String>();
                respuesta7 = (HashMap<String, String>) mensajeVuelta.getSession().get("RespuestaRecPacientes") ;
                session.put("RespuestaRecPacientes",respuesta7);
                System.out.println("éxito en la recuperación de los pacientes");
                break;
            case "/getListaActividadesResponse":
                ArrayList<Actividad> respuesta8 = new ArrayList<Actividad>();
                respuesta8 = (ArrayList<Actividad>) mensajeVuelta.getSession().get("RespuestaGetActividades");
                session.put("RespuestaGetActividades",respuesta8);
                System.out.println("éxito en la búsqueda de actividades");
                break;
            case "/insertarActividadesResponse":
                System.out.println("se ha realizado la insercion de actividades correctamente");
                break;
            case "/RecuperacionActividadesResponse":
                HashMap<Actividad,Integer> respuesta9 = new HashMap<Actividad,Integer>();
                respuesta9 = (HashMap<Actividad, Integer>) mensajeVuelta.getSession().get("RespuestaRecActividades") ;
                session.put("RespuestaRecActividades",respuesta9);
                System.out.println("éxito en la recuperación de actividades");
                break;

            default:
                Logger.getRootLogger().info("Option not found");
                System.out.println("\nError a la vuelta");
                break;


            //System.out.println("3.- En Main.- El valor devuelto es: "+((String)mensajeVuelta.getSession().get("Nombre")));
        }
    }

    public Client(String host, int port) {
        this.host=host;
        this.port=port;
    }
    public Client(){}


    public void sent(Message messageOut, Message messageIn) {
        try {

            System.out.println("Connecting to host " + host + " on port " + port + ".");

            Socket echoSocket = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                echoSocket = new Socket(host, port);
                in = echoSocket.getInputStream();
                out = echoSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

                //Create the object to send
                objectOutputStream.writeObject(messageOut);

                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(in);

                Message msg=(Message)objectInputStream.readObject();
                messageIn.setContext(msg.getContext());
                messageIn.setSession(msg.getSession());

		        /*System.out.println("\n1.- El valor devuelto es: "+messageIn.getContext());
		        String cadena=(String) messageIn.getSession().get("Nombre");
		        System.out.println("\n2.- La cadena devuelta es: "+cadena);*/

            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            /** Closing all the resources */
            out.close();
            in.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
