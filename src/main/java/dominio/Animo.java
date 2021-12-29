package dominio;

import java.io.Serializable;

public class Animo extends Asociacion implements Serializable
{
    /**
     *
     * @param asociacion puede ser Feliz, Triste, Cansad@...
     */
    public Animo(String asociacion)
    {
        super(asociacion);
    }
}
