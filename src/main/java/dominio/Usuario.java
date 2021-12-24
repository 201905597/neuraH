package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Usuario implements Serializable
{
    private String id;
    private String nombre;
    private HashSet<Calendario> calendarios;
    private HashMap<Actividad,Integer> actividadesHechas; //actividades realizadas y las veces que las ha realizado el usuario
    private HashSet<Notificacion> notificaciones;

    public Usuario(String id, String nombre) {
        this.id=id;
        this.nombre=nombre;
        this.calendarios = new HashSet<Calendario>();
        this.actividadesHechas = new HashMap<Actividad, Integer>();
        this.notificaciones = new HashSet<Notificacion>();
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void addCalendario(Calendario calendario)
    {
        calendarios.add(calendario);
    }

    public void addActividad(Actividad actividad)
    {
        int veces = 1;
        for (Map.Entry<Actividad, Integer> entry : actividadesHechas.entrySet())
        {
            Actividad key = entry.getKey();
            int value = entry.getValue();
            if (key.equals(actividad))
            {
                veces = value + 1;
            }
        }
        actividadesHechas.put(actividad,veces);
    }

    public void actualizarNotificaciones()
    {
        for (Calendario calendario : calendarios)
        {
            for (Mes mes : calendario.getMeses())
            {
                if (mes.getDias("Feliz") >= 10)
                {
                    String textoNotif = "¡ENHORABUENA! Has conseguido estar feliz en 10 o más días de " + mes.getNombreMes();
                    Notificacion notificacionFeliz = new Notificacion(textoNotif);
                    notificaciones.add(notificacionFeliz);
                }

                if (mes.getDias("Triste") >= 5)
                {
                    String textoNotif = "Has estado triste en " + mes.getNombreMes() + ". Te recomendamos que hagas actividades de exterior.";
                    Notificacion notificacionTriste = new Notificacion(textoNotif);
                    notificaciones.add(notificacionTriste);
                }

                if (mes.getDias("Hecho") >= 10)
                {
                    String textoNotif = "¡ENHORABUENA! Has seguido el  hábito " + mes.getTipoMes() + " 10 o más días de " + mes.getNombreMes();
                    Notificacion notificacionHecho = new Notificacion(textoNotif);
                    notificaciones.add(notificacionHecho);
                }

                if (mes.getDias("No hecho") >= 5)
                {
                    String textoNotif = "En " + mes.getNombreMes() + ", no seguiste el  hábito " + mes.getTipoMes() + ". ¿Por qué no lo retomas?";
                    Notificacion notificacionNoHecho = new Notificacion(textoNotif);
                    notificaciones.add(notificacionNoHecho);
                }
            }
        }
    }

    public HashSet<Notificacion> getNotificaciones()
    {
        return notificaciones;
    }
}
