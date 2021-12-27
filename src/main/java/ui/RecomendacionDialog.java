package main.java.ui;

import main.java.dominio.Recomendacion;
import main.java.dominio.RecomendacionPsicologo;
import main.java.dominio.RecomendacionUsuario;

import javax.swing.*;
import java.awt.*;

public class RecomendacionDialog extends JDialog
{
    ui.JVentana ventanaOwner;
    String tipoUsuario;
    Recomendacion recomendacion;

    public RecomendacionDialog(ui.JVentana ventanaOwner, boolean modal, String tipoUsuario)
    {
        this.setModal(modal);
        this.ventanaOwner = ventanaOwner;
        this.tipoUsuario = tipoUsuario;
        this.setLayout(new BorderLayout());

        //NORTE
        JLabel lblTitulo = new JLabel("Recomendación",SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Basic", Font.BOLD, 20));
        lblTitulo.setForeground(Color.PINK);
        this.add(lblTitulo,BorderLayout.NORTH);

        //CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setBackground(Color.WHITE);
        JLabel lblRecomendacion = new JLabel(" ",SwingConstants.CENTER);
        pnlCentro.add(lblRecomendacion);

        String reco = "";
        switch (tipoUsuario)
        {
            case "usuario":
                recomendacion = new RecomendacionUsuario(ventanaOwner.getUsuario().getNombre());
                reco = recomendacion.recomendar();
                break;
            case "psicologo":
                recomendacion = new RecomendacionPsicologo(ventanaOwner.getPsicologo().getNombre(),ventanaOwner.getPsicologo().getCentro());
                reco = recomendacion.recomendar();
                break;
            default:
                reco = "¡Vaya! No se ha encontrado una recomendación para ti hoy";
                break;
        }

        lblRecomendacion.setText(reco);

        pnlCentro.updateUI();
        this.add(pnlCentro,BorderLayout.CENTER);

        this.pack();
        this.setBackground(Color.WHITE);
        this.setSize(800,100);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }
}
