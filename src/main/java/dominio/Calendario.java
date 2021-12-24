package dominio;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Calendario implements Serializable
{
    private String tipoCalendar;
    private String idConectado;
    private HashSet<Mes> mesesHashSet;

    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public Calendario(String tipoCalendar, String idConectado)
    {
        this.tipoCalendar = tipoCalendar;
        this.idConectado = idConectado;
        this.mesesHashSet = new HashSet<Mes>();
    }

    public String getTipoCalendar()
    {
        return tipoCalendar;
    }

    public String getIdConectado()
    {
        return idConectado;
    }

    public void addMes(Mes mesNuevo)
    {
        mesesHashSet.add(mesNuevo);
    }

    public HashSet<Mes> getMeses()
    {
        return mesesHashSet;
    }
}
