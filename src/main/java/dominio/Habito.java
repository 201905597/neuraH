package dominio;

import java.io.Serializable;

public class Habito extends Asociacion implements Serializable
{
    String nombreHabito;

    public Habito(String asociacion, String nombreHabito)
    {
        super(asociacion);
        this.nombreHabito = nombreHabito;
    }
}
