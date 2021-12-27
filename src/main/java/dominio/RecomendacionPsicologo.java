package main.java.dominio;

import main.java.io.IORecomendaciones;

public class RecomendacionPsicologo implements Recomendacion
{
    private String recomendacion;
    private String nombre;
    private String centro;

    public RecomendacionPsicologo(String nombre,String centro)
    {
        this.nombre = nombre;
        this.centro = centro;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getCentro()
    {
        return centro;
    }


    @Override
    public String recomendar()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre)
                .append(" (")
                .append(centro)
                .append(")")
                .append(", mira esta recomendación para ti:   \n")
                .append(IORecomendaciones.getRecomendacion("psicologo"));
        this.recomendacion = sb.toString();
        return recomendacion;
    }
}
