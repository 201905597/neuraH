package dominio;

import java.awt.*;
import java.io.Serializable;

public class Dia implements Serializable
{
    private int numero;
    private int anio;
    private String tipoDia;
    private Mes mes;

    private Animo animo;
    private Habito habito;

    String diaDosDigitos;
    String asociacion;
    int coloreado;

    public Dia(int numero, Mes mes, int anio, String tipoDia)
    {
        this.numero = numero;
        this.anio = anio;
        this.tipoDia = tipoDia;
        this.mes = mes;
        this.setAsociacion(" ");
        this.setColoreado(0);

        //Inicialmente el día no tiene ni ánimo ni hábito asociado
        this.animo = null;
        this.habito = null;

        //si solo tiene un digito, añadir un 0 delante
        if (numero<10)
            diaDosDigitos = "0" + String.valueOf(numero);
        else
            diaDosDigitos = String.valueOf(numero);
    }

    public void setColoreado(int coloreado)
    {
        this.coloreado = coloreado;
    }

    public void setAsociacion(String asociacion)
    {
        this.asociacion = asociacion;
    }

    public void setAnimo(Animo animo)
    {
        this.animo = animo;
    }

    public void setHabito(Habito habito)
    {
        this.habito = habito;
    }

    public String getFecha()
    {
        return diaDosDigitos + mes.getMesYAnio();
    }

    public String getAsociacion()
    {
        return asociacion;
    }

    public String getDiaDosDigitos()
    {
        return diaDosDigitos;
    }

    /**
     * @return true si se ha rellenado el día (con emoción o hábito)
     */
    public boolean isColoreado()
    {
        if (coloreado == 1)
            return true;
        else
            return false;
    }

    /**
     * @return objeto String del mes y el año concatenados "Enero2022"
     */
    public String getMesYAnio()
    {
        return mes.getMesYAnio();
    }

    public String getTipoDia()
    {
        return tipoDia;
    }

    public Color getColorAsociacion()
    {
        if (animo != null)
            return animo.getColor();
        else if (habito != null)
            return habito.getColor();
        else
            return null;
    }

    public Animo getAnimo()
    {
        return animo;
    }

    public Habito getHabito()
    {
        return habito;
    }
}
