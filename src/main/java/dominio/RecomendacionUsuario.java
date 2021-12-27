package main.java.dominio;

import main.java.io.IORecomendaciones;

public class RecomendacionUsuario implements Recomendacion
{
    private String recomendacion;
    private String nombre;

    public RecomendacionUsuario(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }


    @Override
    public String recomendar()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre)
                .append(", mira esta recomendación para ti:  \n")
                .append(IORecomendaciones.getRecomendacion("usuario"));
        this.recomendacion = sb.toString();
        return recomendacion;
    }
}
