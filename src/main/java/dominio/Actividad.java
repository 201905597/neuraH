package dominio;

import java.io.Serializable;

public class Actividad implements Serializable
{
    private String descripcion;
    private String lugar; //exterior o interior
    private boolean gratis;

    public Actividad(String descripcion, String lugar, boolean gratis)
    {
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.gratis = gratis;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getLugar()
    {
        return lugar;
    }

    public boolean isGratis()
    {
        return gratis;
    }
}
