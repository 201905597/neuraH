package dominio;

import java.io.Serializable;

public class Notificacion implements Serializable
{
    private String descripcion;

    public Notificacion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
}
