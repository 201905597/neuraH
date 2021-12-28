package server;

import controler.CustomerControler;
import dominio.Actividad;
import dominio.Mes;
import message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SocketServer extends Thread
{
    public static final int PORT_NUMBER = 8081;

    protected Socket socket;

    private SocketServer(Socket socket) {
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            //first read the object that has been sent
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Message mensajeIn= (Message)objectInputStream.readObject();
            //Object to return informations
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            System.out.println("context de mensaje in: " + mensajeIn.getContext());
            Message mensajeOut=new Message();
            switch (mensajeIn.getContext()) {
                case ("/peticionAccesoUsuario"):
                    CustomerControler customerControler=new CustomerControler();
                    int var=customerControler.autenticarAlUsuario((String)mensajeIn.getSession().get("id"),(String)mensajeIn.getSession().get("nombre"));
                    mensajeOut.setContext("/peticionAccesoResponse1");
                    HashMap<String,Object> session=new HashMap<String, Object>();
                    session.put("RespuestaAcceso1",var);
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case ("/peticionAccesoPsicologo"):
                    CustomerControler customerControler2=new CustomerControler();
                    int var2=customerControler2.autenticarAlPsicologo((String)mensajeIn.getSession().get("id"),(String)mensajeIn.getSession().get("centro"));
                    mensajeOut.setContext("/peticionAccesoResponse2");
                    HashMap<String,Object> session2=new HashMap<String, Object>();
                    session2.put("RespuestaAcceso2",var2);
                    mensajeOut.setSession(session2);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case ("/animoUsuario"):
                    CustomerControler customerControler1=new CustomerControler();
                    customerControler1.insertarEmociones((String)mensajeIn.getSession().get("id"),(String)mensajeIn.getSession().get("fecha"),(String)mensajeIn.getSession().get("emocion"));
                    mensajeOut.setContext("/animoUsuarioResponse");
                    HashMap<String,Object> session1=new HashMap<String, Object>();
                    //session.put("RespuestaAnimo",var1);
                    mensajeOut.setSession(session1);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case ("/recuperacionAnimo"):
                    CustomerControler customerControler5=new CustomerControler();
                    HashSet<Mes> var5=customerControler5.recuperacionDeAnimos((String)mensajeIn.getSession().get("id"));
                    mensajeOut.setContext("/recuperacionAnimoResponse");
                    HashMap<String,Object> session5=new HashMap<String, Object>();
                    session5.put("RespuestaRecAnimos",var5);
                    mensajeOut.setSession(session5);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case ("/habitoUsuario"):
                    CustomerControler customerControler3=new CustomerControler();
                    customerControler3.insertarHabitos((String)mensajeIn.getSession().get("id"),(HashMap<String, String>) mensajeIn.getSession().get("hmFH"));
                    mensajeOut.setContext("/habitoUsuarioResponse");
                    HashMap<String,Object> session3=new HashMap<String, Object>();
                    mensajeOut.setSession(session3);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case ("/recuperacionHabito"):
                    System.out.println("aqui recupero de la tabla usuariohabitos");
                    CustomerControler customerControler4=new CustomerControler();
                    HashSet<Mes> var4=customerControler4.recuperacionDeHabitos((String)mensajeIn.getSession().get("id"),(String)mensajeIn.getSession().get("habito"));
                    mensajeOut.setContext("/recuperacionHabitoResponse");
                    HashMap<String,Object> session4=new HashMap<String, Object>();
                    session4.put("RespuestaRecHabitos",var4);
                    mensajeOut.setSession(session4);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case ("/recuperacionNombreHabitos"):
                    CustomerControler customerControler6=new CustomerControler();
                    HashSet<String> var6 = customerControler6.recuperacionNombreHabitos((String)mensajeIn.getSession().get("id"));
                    mensajeOut.setContext("/recuperacionNombreHabitosResponse");
                    HashMap<String,Object> session6=new HashMap<String, Object>();
                    session6.put("RespuestaRecNombreHabitos",var6);
                    mensajeOut.setSession(session6);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case ("/recuperacionPacientes"):
                    CustomerControler customerControler7=new CustomerControler();
                    HashMap<String,String> var7 = customerControler7.recuperacionPacientes((String)mensajeIn.getSession().get("id"));
                    mensajeOut.setContext("/recuperacionPacientesResponse");
                    HashMap<String,Object> session7=new HashMap<String, Object>();
                    session7.put("RespuestaRecPacientes",var7);
                    mensajeOut.setSession(session7);
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("setteo el contexto");
                    break;
                case ("/getListaActividades"):
                    CustomerControler customerControler8=new CustomerControler();
                    ArrayList<Actividad> var8 = customerControler8.busquedaActividades((String)mensajeIn.getSession().get("lugar"),(Boolean)mensajeIn.getSession().get("gratis"));
                    mensajeOut.setContext("/getListaActividadesResponse");
                    HashMap<String,Object> session8=new HashMap<String, Object>();
                    session8.put("RespuestaGetActividades",var8);
                    mensajeOut.setSession(session8);
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("setteo el contexto");
                    break;
                case ("/insertarActividades"):
                    CustomerControler customerControler9=new CustomerControler();
                    customerControler9.insertarActividades((String)mensajeIn.getSession().get("id"),(HashMap<String,Actividad>)mensajeIn.getSession().get("hm"));
                    mensajeOut.setContext("/insertarActividadesResponse");
                    HashMap<String,Object> session9=new HashMap<String, Object>();
                    mensajeOut.setSession(session9);
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("setteo el contexto");
                    break;
                case ("/recuperacionActividades"):
                    CustomerControler customerControler10=new CustomerControler();
                    //HashMap<Actividad,Integer> var10 = customerControler10.recuperacionActividades((String)mensajeIn.getSession().get("id"));
                    HashMap<String,Actividad> var10 = customerControler10.recuperacionActividades((String)mensajeIn.getSession().get("id"));
                    mensajeOut.setContext("/RecuperacionActividadesResponse");
                    HashMap<String,Object> session10=new HashMap<String, Object>();
                    session10.put("RespuestaRecActividades",var10);
                    mensajeOut.setSession(session10);
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("setteo el contexto");
                    break;
                default:
                    System.out.println("\nParámetro no encontrado");
                    break;
            }

        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } catch (ClassNotFoundException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("SocketServer Example");
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new SocketServer(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
