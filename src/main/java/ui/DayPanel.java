package ui;

import dominio.Dia;

import javax.swing.*;
import java.awt.*;

public class DayPanel extends JPanel
{
    Dia dia;
    JVentana ventanaOwner;
    String tipoDia;
    String diaDosDigitos;
    JButton btnDia;
    String asociacion;
    //int coloreado; //CAMBIAR A BOOLEAN??

    public DayPanel(Dia dia, JVentana ventanaOwner)
    {
        this.dia = dia;
        this.ventanaOwner = ventanaOwner;
        this.tipoDia = dia.getTipoDia();
        this.diaDosDigitos = dia.getDiaDosDigitos();
        this.asociacion = dia.getAsociacion();
        //this.coloreado = dia.
        this.setBackground(Color.WHITE);

        btnDia = new JButton(diaDosDigitos);
        this.add(btnDia);

        this.setBtnColor(dia.getColorAsociacion());

        this.setVisible(true);
    }

    public void setBtnColor(Color color) //se llama desde ColoresDialog
    {
        this.btnDia.setBackground(color);
        DayPanel.this.updateUI();
        DayPanel.this.getDia().setColoreado(1);
    }

    public Dia getDia()
    {
        return dia;
    }

    public JButton getBtnDia()
    {
        return btnDia;
    }
}
