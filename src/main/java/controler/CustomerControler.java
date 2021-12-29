package controler;

import dao.CustomerDAO;
import domain.Customer;
import dominio.Actividad;
import dominio.Mes;
import ui.JVentana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CustomerControler
{
    public void getCustomer(ArrayList<Customer> lista) {
        CustomerDAO.getClientes(lista);
    }

    public int autenticarAlUsuario(String id, String nombre) {
        return CustomerDAO.autenticarAlUsuario(id, nombre);
    }

    public int autenticarAlPsicologo(String id, String centro)
    {
        return CustomerDAO.autenticarAlPsicologo(id,centro);
    }

    public void insertarEmociones(String id, String fecha, String emocion){CustomerDAO.rellenarAnimo(id,fecha,emocion);}

    public HashSet<Mes> recuperacionDeAnimos(String id){return CustomerDAO.recuperarAnimos(id);}

    public void insertarHabitos(String id, HashMap<String,String> hm) {CustomerDAO.rellenarHabitos(id,hm);}

    public HashSet<Mes> recuperacionDeHabitos(String id,String habito){return CustomerDAO.recuperarHabitos(id,habito);}

    public HashSet<String> recuperacionNombreHabitos(String id){return CustomerDAO.recuperarNombreHabitos(id);}

    public HashMap<String,String> recuperacionPacientes(String id){return CustomerDAO.recuperarPacientes(id);}

    public ArrayList<Actividad> busquedaActividades(String lugar, Boolean gratis){return CustomerDAO.getListaActividades(lugar,gratis);}

    public HashMap<String,Actividad> recuperacionActividades(String id) {return CustomerDAO.recuperarActividades(id);}

    public void insertarActividades(String idConectado, HashMap<String,Actividad> actividades) {CustomerDAO.rellenarActividades(idConectado,actividades);}
}
