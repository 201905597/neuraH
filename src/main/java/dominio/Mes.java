package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Mes implements Serializable
{
    private String nombreMes;
    private int anio;
    private String tipoMes;
    private ArrayList<Dia> diasArrayList;

    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    int diasMeses[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    HashMap<String,Integer> mesDias = new HashMap<String,Integer>();

    public Mes(String nombreMes, int anio, String tipoMes)
    {
        this.nombreMes = nombreMes;
        this.anio = anio;
        this.tipoMes = tipoMes;
        this.diasArrayList = new ArrayList<Dia>();

        //Años bisiestos
        for (int i=0;i<12;i++)
        {
            mesDias.put(meses[i],diasMeses[i]);
        }
        if (nombreMes == "Febrero")
        {
            if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)))
                mesDias.replace("Febrero",28,29);
        }

        for (int i = 1;i<mesDias.get(nombreMes)+1;i++)
        {
            Dia diaNuevo = new Dia(i,Mes.this,(Mes.this).getAnio(), tipoMes);
            diasArrayList.add(diaNuevo);
        }
    }

    public int getAnio()
    {
        return anio;
    }

    public String getNombreMes()
    {
        return nombreMes;
    }

    public String getTipoMes()
    {
        return tipoMes;
    }

    public String getMesYAnio()
    {
        return nombreMes + String.valueOf(anio);
    }

    public ArrayList<Dia> getDayArray()
    {
        return diasArrayList;
    }

    public HashMap<String,Integer> getMesDias()
    {
        return mesDias;
    }

    /**
     * Método que devuelve los días del mes que se han rellenado con un ánimo o hábito
     * @return los días del mes que se han rellenado con un ánimo o hábito
     */
    public int getDiasSeguimiento()
    {
        int numero = 0;
        for (Dia day : Mes.this.getDayArray())
        {
            if (day != null && day.isColoreado())
            {
                numero ++;
            }
        }
        return numero;
    }

    /**
     *
     * @param asociacion el ánimo o hábito en particular
     * @return los días con la asociación pasada como parámetro (ejemplo: los días Felices)
     */
    public int getDias(String asociacion)
    {
        int numero = 0;
        for (Dia day : Mes.this.getDayArray())
        {
            if (day != null && day.isColoreado() && (day.getAsociacion().equals(asociacion)))
            {
                numero ++;
            }
        }
        return numero;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if (Mes.this.getTipoMes().equals("Animo"))
        {
            sb.append("Estado de ánimo, ")
                    .append(nombreMes + " " + anio + ":")
                    .append("\nDías seguimiento: ")
                    .append(Mes.this.getDiasSeguimiento())
                    .append("\nDías felices: ")
                    .append(Mes.this.getDias("Feliz"))
                    .append("\nDías productivos: ")
                    .append(Mes.this.getDias("Productiv@"))
                    .append("\nDías tristes: ")
                    .append(Mes.this.getDias("Triste"))
                    .append("\nDías con estrés: ")
                    .append(Mes.this.getDias("Estresad@"))
                    .append("\nDías cansados: ")
                    .append(Mes.this.getDias("Cansad@"));
        }
        else
        {
            sb.append(tipoMes + ", " + nombreMes + " " + anio + ":")
                    .append("\nDías seguimiento: ")
                    .append(Mes.this.getDiasSeguimiento())
                    .append("\nDías hecho: ")
                    .append(Mes.this.getDias("Hecho"))
                    .append("\nDías no hecho: ")
                    .append(Mes.this.getDias("No hecho"));
        }
        return sb.toString();
    }
}
