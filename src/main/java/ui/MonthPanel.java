package ui;

import dominio.Dia;
import dominio.Mes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthPanel extends JPanel
{
    private Mes mes;
    private ArrayList<DayPanel> diasArrayList;
    private ArrayList<Dia> diasArrayListDominio;
    private String nombreMes;
    private int anio;
    private JVentana ventanaOwner;
    private String tipoMes; //indica si se trata de un mes del calendario de ánimos o de hábitos
    //org
    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    int diasMeses[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    HashMap<String,Integer> mesDias = new HashMap<String,Integer>();

    public MonthPanel(Mes mes, JVentana ventanaOwner)
    {
        this.mes = mes;
        this.setLayout(new BorderLayout());
        this.diasArrayListDominio = mes.getDayArray();
        this.diasArrayList = new ArrayList<DayPanel>();
        this.anio = mes.getAnio();
        this.nombreMes = mes.getNombreMes();
        this.tipoMes = mes.getTipoMes();
        this.ventanaOwner = ventanaOwner;
        this.mesDias = mes.getMesDias();
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));

        //PANEL NORTE
        JPanel pnlNorte = new JPanel();
        pnlNorte.setOpaque(false);
        JLabel lblMes = new JLabel(nombreMes + " ");
        JLabel lblAnio = new JLabel(String.valueOf(anio));
        pnlNorte.add(lblMes);
        pnlNorte.add(lblAnio);
        this.add(pnlNorte, BorderLayout.NORTH);

        //PANEL CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setOpaque(false);
        pnlCentro.setLayout(new GridLayout(7,5));

        for (Dia dia : diasArrayListDominio)
        {
            DayPanel diaPanel = new DayPanel(dia,ventanaOwner);
            diasArrayList.add(diaPanel);
            pnlCentro.add(diaPanel);
        }

        this.add(pnlCentro, BorderLayout.CENTER);


        this.setVisible(false);
    }

    public Mes getMes()
    {
        return mes;
    }

    public ArrayList<DayPanel> getDiasArrayList() {
        return diasArrayList;
    }
}
