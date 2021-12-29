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
    private HashMap<String,Calendario> calendariosHM;
    //private HashMap<Actividad,Integer> actividadesHechas; //actividades realizadas y las veces que las ha realizado el usuario
    private HashMap<String,Actividad> actividadesHechas;
    private HashSet<Notificacion> notificaciones;

    public Usuario(String id, String nombre) {
        this.id=id;
        this.nombre=nombre;
        this.calendarios = new HashSet<Calendario>();
        this.calendariosHM = new HashMap<String,Calendario>();
        //this.actividadesHechas = new HashMap<Actividad, Integer>();
        this.actividadesHechas = new HashMap<String,Actividad>();
        this.notificaciones = new HashSet<Notificacion>();
    }

    public void setActividades(HashMap<String,Actividad> actividades)
    {
        this.actividadesHechas = actividades;
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
        calendariosHM.put(calendario.getTipoCalendar(), calendario);
    }


    public void addActividad(String descripcion,String lugar,Boolean gratis)
    {
        dominio.Actividad actividad = new dominio.Actividad(descripcion,lugar,gratis);
        if (!Usuario.this.getActividadesHechas().isEmpty())
        {
            if (Usuario.this.getActividadesHechas().containsKey(descripcion))
            {
                HashMap<String, dominio.Actividad> actividadHashMap = Usuario.this.getActividadesHechas();
                int vecesRealizadaGuardada =  actividadHashMap.get(descripcion).getVecesRealizada();
                actividad.setVecesRealizada(vecesRealizadaGuardada + 1);
                Usuario.this.getActividadesHechas().replace(descripcion,actividad);
            }
            else
            {
                Usuario.this.getActividadesHechas().put(descripcion,actividad);
            }
        }
        else
        {
            HashMap<String, dominio.Actividad> nuevoHM = new HashMap<String, dominio.Actividad>();
            nuevoHM.put(descripcion,actividad);
            Usuario.this.setActividades(nuevoHM);
        }
    }


    public void actualizarNotificaciones()
    {
        //añadir if calendarios != null
        for (Calendario calendario : calendarios)
        {
            for (Mes mes : calendario.getMeses())
            {
                if (mes.getDias("Feliz") >= 10)
                {
                    String textoNotif = "¡ENHORABUENA! Has conseguido estar feliz en 10 o más días de " + mes.getNombreMes();
                    Notificacion notificacionFeliz = new Notificacion(textoNotif);
                    if (!notificaciones.contains(notificacionFeliz))
                        notificaciones.add(notificacionFeliz);
                }

                if (mes.getDias("Triste") >= 5)
                {
                    String textoNotif = "Has estado triste en " + mes.getNombreMes() + ". Te recomendamos que hagas actividades de exterior.";
                    Notificacion notificacionTriste = new Notificacion(textoNotif);
                    if (!notificaciones.contains(notificacionTriste))
                        notificaciones.add(notificacionTriste);
                }

                if (mes.getDias("Hecho") >= 10)
                {
                    String textoNotif = "¡ENHORABUENA! Has seguido el  hábito " + mes.getTipoMes() + " 10 o más días de " + mes.getNombreMes();
                    Notificacion notificacionHecho = new Notificacion(textoNotif);
                    if (!notificaciones.contains(notificacionHecho))
                        notificaciones.add(notificacionHecho);
                }

                if (mes.getDias("No hecho") >= 5)
                {
                    String textoNotif = "En " + mes.getNombreMes() + ", no seguiste el  hábito " + mes.getTipoMes() + ". ¿Por qué no lo retomas?";
                    Notificacion notificacionNoHecho = new Notificacion(textoNotif);
                    if (!notificaciones.contains(notificacionNoHecho))
                        notificaciones.add(notificacionNoHecho);
                }
            }
        }

        if (!Usuario.this.getActividadesHechas().isEmpty())
        {
            for (Map.Entry<String,dominio.Actividad> entry : Usuario.this.getActividadesHechas().entrySet())
            {
                //si se ha hecho una actividad se envía una notificación
                String descripcion = entry.getKey();

                String textoNotif = "¡Enhorabuena! Has hecho la actividad " + descripcion + " al menos una vez :)";

                Notificacion notificacionAct = new Notificacion(textoNotif);
                if (!notificaciones.contains(notificacionAct))
                    notificaciones.add(notificacionAct);
            }
        }

    }

    public HashSet<Notificacion> getNotificaciones()
    {
        return notificaciones;
    }

    public HashMap<String,Calendario> getCalendariosHM()
    {
        return calendariosHM;
    }

    public HashSet<Calendario> getCalendariosHS()
    {
        return calendarios;
    }

    public Calendario getCalendario(String tipoCalendario)
    {
        Calendario calendario = null;
        for (Map.Entry<String, Calendario> entry : calendariosHM.entrySet())
        {
            String key = entry.getKey();
            Calendario value = entry.getValue();
            if (tipoCalendario.equals(key))
                calendario = value;
        }
        return calendario;
    }

    public HashMap<String,Actividad> getActividadesHechas() {
        return actividadesHechas;
    }
}

