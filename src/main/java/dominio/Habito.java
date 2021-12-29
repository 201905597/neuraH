package dominio;

import java.io.Serializable;

public class Habito extends Asociacion implements Serializable
{
    String nombreHabito;

    /**
     * Constructor del hábito: la asociación es hecho/no hecho y nombreHabito podría ser Deporte, Sueño...
     * @param asociacion
     * @param nombreHabito
     */
    public Habito(String asociacion, String nombreHabito)
    {
        super(asociacion);
        this.nombreHabito = nombreHabito;
    }
}
