package dominio;

import java.io.Serializable;

public class Actividad implements Serializable
{
    private String descripcion;
    private String lugar; //exterior o interior
    private Boolean gratis;

    public Actividad()
    {

    }

    public Actividad(String descripcion, String lugar, Boolean gratis)
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

    public Boolean isGratis()
    {
        return gratis;
    }

    @Override
    public String toString()
    {
        return descripcion;
    }
}
