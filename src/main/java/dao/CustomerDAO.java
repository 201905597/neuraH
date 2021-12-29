package dao;

import domain.Customer;
import dominio.*;
import dominio.Actividad;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CustomerDAO
{
    public static void getClientes(ArrayList<Customer> lista) {
        Connection con= ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios where id= 1");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                lista.add(new Customer(rs.getString(2),rs.getString(1)));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }
    public void getUsuarioAutenticado(ArrayList<Customer> lista, String id, String nombre) {
        Connection con=ConnectionDAO.getInstance().getConnection();
        int encontrado=0;
        int respuesta=0;
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios where id= \'" + id + "\' ");
             ResultSet rs = pst.executeQuery()) {
            System.out.println("hola que tal");
            while (rs.next()) {
                lista.add(new Customer(rs.getString(2),rs.getString(1)));
                if(nombre.equals(rs.getString(1)) && (id.equals(rs.getString(2))))
                {
                    System.out.println("Encontrado bien nombre e id en la bbdd");
                    encontrado=1;
                    respuesta=1;
                }
                else
                {
                    encontrado=0;
                }


            }
            if(encontrado==0)
            {
                System.out.println("NO encontrado en la bbdd");
            }



        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }

    /**
     * Método de autenticación del usuario: busca los datos de inicio de sesión introducidos en la bbdd
     * @param id el id del usuario que ha iniciado sesión
     * @param nombre el nombre del usuario
     * @return 1 si está registrado en la bbdd, 0 si no
     */
    public static int autenticarAlUsuario(String id,String nombre)
    {
        int encontrado =0 ;
        int respuesta=0;


        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                if(nombre.equals(rs.getString(1)) && (id.equals(rs.getString(2))))
                {
                    System.out.println("Encontrado bien nombre e id en la bbdd");
                    encontrado=1;
                    respuesta=1;
                }



            }
            if(encontrado==0)
            {
                System.out.println("NO encontrado en la bbdd");
            }



        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return respuesta;
    }

    /**
     * Método de autenticación del psicólogo: busca los datos de inicio de sesión introducidos en la bbdd
     * @param id el id del psicólogo que ha iniciado sesión
     * @param centro  el centro al que pertenece el psicólogo
     * @return 1 si está registrado en la bbdd, 0 si no
     */
    public static int autenticarAlPsicologo(String id, String centro)
    {

        int encontrado =0 ;
        int respuesta=0;

        Connection con=ConnectionDAO.getInstance().getConnection();

        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM psicologos");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                if (id.equals(rs.getString(1)) && (centro.equals(rs.getString(3)))) {
                    System.out.println("Encontrado bien id y centro del psicologo en la bbdd");
                    encontrado = 1;
                    respuesta = 1;
                }
                else
                {
                    System.out.println("no encontrado");
                }

            }

        }
        catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }


        if(encontrado==0)
        {
            System.out.println("NO encontrado psicologo en la bbdd");
        }


        return respuesta;
    }

    /**
     * Método para registrar las emociones que introduce el usuario en el calendario
     * @param idConectado id del usuario que se ha conectado
     * @param fechaYemocion mapa fecha --> emocion (el estado de ánimo del usuario en dicha fecha)
     */
    public static void rellenarAnimo(String idConectado, String fecha, String emocion)
    {

        Connection con = ConnectionDAO.getInstance().getConnection();

        String idfecha= idConectado+fecha;

        try (PreparedStatement pst = con.prepareStatement("INSERT INTO usuarioanimos (id,fecha,emocion,idfecha) VALUES (\'" + idConectado + "\',\'" + fecha + "\',\'" + emocion + "\',\'" + idfecha + "\')");
             ResultSet rs = pst.executeQuery()) {


        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }

    /**
     * Método para recuperar los datos de fecha --> emoción del usuario de la base de datos
     * @param idConectado
     * @return
     */
    public static HashSet<Mes> recuperarAnimos(String idConectado)
    {
        //ArrayList<MonthPanel> mesesArray = new ArrayList<MonthPanel>();
        HashSet<Mes> mesesHSet = new HashSet<Mes>();
        Connection con = ConnectionDAO.getInstance().getConnection();
        HashMap<String, Color> colorEmocion = new HashMap<String, Color>();
        colorEmocion.put("Feliz",new Color(255,153,0));
        colorEmocion.put("Triste",new Color(51,153,255));
        colorEmocion.put("Estresad@",new Color(255,51,51));
        colorEmocion.put("Cansad@",new Color(102,20,153));
        colorEmocion.put("Productiv@",new Color(0,204,0));
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarioanimos");
             ResultSet rs = pst.executeQuery()) {
            HashMap<String, String> hmFechaEmocion = new HashMap<String, String>();
            while (rs.next()) {

                if (idConectado.equals(rs.getString(1))) {
                    System.out.println("Encuentro el usuario que ha entrado");
                    String fecha = rs.getString(2);
                    String emocion = rs.getString(3);
                    hmFechaEmocion.put(fecha, emocion);
                } else {
                    System.out.println("este usuario no tiene emociones guardadas");
                }
            }

            mesesHSet = new HashSet<Mes>();
            HashSet<String> mesAnioSet = new HashSet<String>();
            for (Map.Entry<String, String> entry : hmFechaEmocion.entrySet())
            {
                String fecha = entry.getKey();
                String mesYanio = fecha.substring(2);
                mesAnioSet.add(mesYanio);
            }

            for (String mesAnio : mesAnioSet)
            {
                String nombreMes = mesAnio.substring(0,mesAnio.length()-4);
                String anio = mesAnio.substring(mesAnio.length()-4);
                mesesHSet.add(new Mes(nombreMes,Integer.parseInt(anio), "Animo"));
            }

            for (Mes mes : mesesHSet)
            {
                for (Dia day : mes.getDayArray())
                {
                    String diaDosDigitos = day.getDiaDosDigitos();
                    for (Map.Entry<String, String> entry : hmFechaEmocion.entrySet())
                    {
                        String emocion = entry.getValue();
                        if (entry.getKey().substring(2).equals(day.getMesYAnio()) && entry.getKey().substring(0,2).equals(diaDosDigitos))
                        {
                            day.setAsociacion(emocion);
                            day.setColoreado(1);
                            Animo animo = new Animo(emocion);
                            day.setAnimo(animo);
                        }
                    }
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

        return mesesHSet;
    }

    /**
     * Método para registrar los hábitos que introduce el usuario en el calendario
     * @param idConectado del usuario
     * @param fechaYhabito hashmap de cada fecha rellenada con su hábito asociado
     */
    public static void rellenarHabitos(String idConectado, HashMap<String,String> fechaYhabito)
    {
        Connection con = ConnectionDAO.getInstance().getConnection();

        for (Map.Entry<String, String> entry : fechaYhabito.entrySet())
        {

            String fecha = entry.getKey();
            String habitoEstado = entry.getValue();
            String habito = habitoEstado.substring(0,habitoEstado.indexOf("#"));
            String estado = habitoEstado.substring(habitoEstado.indexOf("#")+1);

            try (PreparedStatement pst = con.prepareStatement("INSERT INTO usuariohabitos (id,fecha,habito,estado,mmyyhabito) VALUES (\'" + idConectado + "\',\'" + fecha + "\',\'" + habito + "\',\'" + estado + "\', \'" + idConectado +fecha + habito +"\')");
                 ResultSet rs = pst.executeQuery()) {



            } catch (SQLException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Método para recuperar los datos de fecha --> hábito hecho/no hecho del usuario de la base de datos
     * @param idConectado
     * @return
     */
    public static HashSet<Mes> recuperarHabitos(String idConectado,String habitoRecuperado)
    {
        HashSet<Mes> mesesHSet = new HashSet<Mes>();
        Connection con = ConnectionDAO.getInstance().getConnection();
        HashMap<String, Color> habitoColor = new HashMap<String, Color>();
        habitoColor.put("Hecho",new Color(68,175,118));
        habitoColor.put("No hecho",new Color(253,65,65));
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuariohabitos");
             ResultSet rs = pst.executeQuery()) {
            HashMap<String, String> hmFechaHabito = new HashMap<String, String>();
            while (rs.next()) {

                if (idConectado.equals(rs.getString(1)) && habitoRecuperado.equals(rs.getString(3))) {
                    System.out.println("Encuentro el usuario que ha entrado");
                    String fecha = rs.getString(2);
                    String habito = rs.getString(3);
                    String estado = rs.getString(4);
                    hmFechaHabito.put(fecha, habito+"#"+estado);
                } else {
                    System.out.println("este usuario no tiene habitos guardados");
                }
            }

            mesesHSet = new HashSet<Mes>();
            HashSet<String> mesAnioSet = new HashSet<String>();
            String habito = " ";
            for (Map.Entry<String, String> entry : hmFechaHabito.entrySet())
            {
                String fecha = entry.getKey();
                String mesYanio = fecha.substring(2);
                mesAnioSet.add(mesYanio);
                String habitoEstado = entry.getValue();
                habito = habitoEstado.substring(0,habitoEstado.indexOf("#"));
            }

            for (String mesAnio : mesAnioSet)
            {
                String nombreMes = mesAnio.substring(0,mesAnio.length()-4);
                String anio = mesAnio.substring(mesAnio.length()-4);
                mesesHSet.add(new Mes(nombreMes,Integer.parseInt(anio), habito));
            }

            for (Mes mes : mesesHSet)
            {
                for (Dia day : mes.getDayArray())
                {
                    String diaDosDigitos = day.getDiaDosDigitos();
                    for (Map.Entry<String, String> entry : hmFechaHabito.entrySet())
                    {
                        String habitoEstado = entry.getValue();
                        String estado = habitoEstado.substring(habitoEstado.indexOf("#")+1);
                        if (entry.getKey().substring(2).equals(day.getMesYAnio()) && entry.getKey().substring(0,2).equals(diaDosDigitos))
                        {
                            day.setAsociacion(estado);
                            day.setColoreado(1);
                            Habito habitoNuevo = new Habito(estado,habito);
                            day.setHabito(habitoNuevo);
                        }
                    }
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

        return mesesHSet;
    }

    /**
     * Método para recuperar los nombres de los hábitos del usuario de la base de datos
     * @param idConectado id del usuario
     * @return
     */
    public static HashSet<String> recuperarNombreHabitos(String idConectado) {
        HashSet<String> habitosbbdd = new HashSet<String>();
        Connection con = ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuariohabitos");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {

                if (idConectado.equals(rs.getString(1))) {
                    System.out.println("Encuentro el usuario que ha entrado");
                    String habito = rs.getString(3);
                    if (habito != "Deporte" && habito != "Sueño")
                        habitosbbdd.add(habito);
                } else {
                    System.out.println("Este usuario no tiene habitos guardados");
                }
            }

        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return habitosbbdd;
    }

    /**
     * Método para recuperar los nonbres de los pacientes del psicólogo
     * @param idConectado id del psicólogo
     * @return
     */
    public static HashMap<String,String> recuperarPacientes(String idConectado)
    {
        System.out.println(idConectado);
        HashMap<String,String> pacientes = new HashMap<String,String>();
        Connection con = ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM psicologopacientes");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {

                if (idConectado.equals(rs.getString(2))) {
                    System.out.println("Encuentro el psicologo que ha entrado");
                    String idPaciente = rs.getString(3);
                    String nombrePaciente = rs.getString(4);
                    pacientes.put(idPaciente,nombrePaciente);
                } else {
                    System.out.println("Este psicologo no tiene pacientes que usen NeuraHealth");
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        for (Map.Entry<String, String> entry : pacientes.entrySet())
        {
            System.out.println(entry.getKey() + entry.getValue());
        }

        return pacientes;
    }

    /**
     * Lee de la tabla de actividades de la bbdd
     * @param lugar
     * @param gratis
     * @return arraylist con todas las actividades posibles
     */
    public static ArrayList<Actividad> getListaActividades(String lugar, Boolean gratis)
    {
        ArrayList<Actividad> actividades = new ArrayList<Actividad>();
        Connection con = ConnectionDAO.getInstance().getConnection();
        String gratis2;
        if (gratis)
            gratis2 = "t";
        else
            gratis2 = "f";
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM actividades");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {

                if (lugar.equals(rs.getString(2)) && gratis2.equals(rs.getString(3))) {
                    Actividad actividad = new Actividad(rs.getString(1),lugar,gratis);
                    actividades.add(actividad);
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return actividades;
    }

    /**
     * Método para rellenar la tabla de actividades que hace cada usuario (y las veces que ha hecho cada una)
     * @param idConectado del usuario
     * @param actividades las actividades que tiene hechas (actualizadas)
     */
    public static void rellenarActividades(String idConectado, HashMap<String,Actividad> actividades)
    {
        Connection con = ConnectionDAO.getInstance().getConnection();

        for (Map.Entry<String,Actividad> entry : actividades.entrySet())
        {
            String descripcion = entry.getKey();
            Actividad actividad = entry.getValue();
            int veces = actividad.getVecesRealizada();
            String lugar = actividad.getLugar();
            Boolean gratis = actividad.isGratis();
            String idActividad = idConectado + descripcion;

            boolean repetido = false;

            try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarioactividades");
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {

                    if (idConectado.equals(rs.getString(1)) && descripcion.equals(rs.getString(2))) {

                        try (PreparedStatement pst2 = con.prepareStatement("UPDATE usuarioactividades SET veces=" + veces + " WHERE id = " + "\'" + idConectado + "\' and actividad = \'" + descripcion + "\';");
                             ResultSet rs2 = pst2.executeQuery()) {
                            repetido = true;


                        } catch (SQLException ex) {

                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }

            if (!repetido)
            {
                try (PreparedStatement pst3 = con.prepareStatement("INSERT INTO usuarioactividades (id,Actividad,veces,lugar,gratis,idactividad) VALUES (\'" + idConectado + "\',\'" + descripcion + "\'," + veces+ ",\'" + lugar + "\'," + gratis + ", \'" + idActividad +"\');");
                     ResultSet rs3 = pst3.executeQuery()) {


                } catch (SQLException ex) {

                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Método que lee de la bbdd las actividades que ha hecho el usuario
     * @param idConectado del usuario
     * @return las actividades guardadas
     */
    public static HashMap<String,Actividad> recuperarActividades(String idConectado)
    {
        HashMap<String,Actividad> actividades = new HashMap<String,Actividad>();
        Connection con = dao.ConnectionDAO.getInstance().getConnection();

        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarioactividades");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {

                if (idConectado.equals(rs.getString(1))) {

                    Actividad actividad = new Actividad(rs.getString(2),rs.getString(4),Boolean.parseBoolean(rs.getString(5)));
                    int veces = Integer.parseInt(rs.getString(3));
                    actividad.setVecesRealizada(veces);
                    actividades.put(actividad.getDescripcion(),actividad);
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return actividades;
    }

    //-------------------------------------

    public static void main(String[] args) {


        ArrayList<Customer> lista=new ArrayList<Customer>();
        CustomerDAO.getClientes(lista);


        for (Customer customer : lista) {
            System.out.println("He leído el id: "+customer.getId()+" con nombre: "+customer.getName());
        }

    }
}
