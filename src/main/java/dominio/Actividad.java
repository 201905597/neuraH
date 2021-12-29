package dominio;

import java.io.Serializable;

public class Actividad implements Serializable
{
    private String descripcion;
    private String lugar; //exterior o interior
    private Boolean gratis;
    private int vecesRealizada;

    /**
     * Constructor para poder "inicializar" una actividad sin sus atributos
     */
    public Actividad()
    {

    }


    public Actividad(String descripcion, String lugar, Boolean gratis)
    {
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.gratis = gratis;
        this.setVecesRealizada(1);
    }

    public void setVecesRealizada(int vecesRealizada)
    {
        this.vecesRealizada = vecesRealizada;
    }

    public int getVecesRealizada()
    {
        return vecesRealizada;
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
