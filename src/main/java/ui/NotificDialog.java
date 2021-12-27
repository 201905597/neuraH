package ui;

import client.Client;
import dominio.Calendario;
import dominio.Mes;
import dominio.Notificacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class NotificDialog extends JDialog
{
    //PONER SCROLLPANE!!!!!!
    private JVentana ventanaOwner;
    private HashSet<Notificacion> notificaciones;
    ArrayList<JLabel> jlabels;

    public NotificDialog(JVentana ventanaOwner, boolean modal)
    {
        this.ventanaOwner = ventanaOwner;
        this.setTitle("NOTIFICACIONES");
        this.notificaciones = new HashSet<Notificacion>();
        this.jlabels = new ArrayList<JLabel>();
        this.setModal(modal);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.WHITE);

        //NORTE ----------------------------------------------------------
        JLabel lblMisNotif = new JLabel("MIS NOTIFICACIONES",SwingConstants.CENTER);
        lblMisNotif.setFont(new Font("Basic", Font.BOLD, 30));
        lblMisNotif.setPreferredSize(new Dimension(50, 40));
        lblMisNotif.setForeground(Color.PINK);
        this.add(lblMisNotif, BorderLayout.NORTH);
        //----------------------------------------------------------------

        //CENTRO ---------------------------------------------------------
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(7,1));

        if (!ventanaOwner.calendariosRecuperados())
            ventanaOwner.recuperarCalendarios(ventanaOwner.getUsuario().getId());
        notificaciones = ventanaOwner.getUsuario().getNotificaciones();

        for (Notificacion notificacion : notificaciones)
        {
            JLabel lblNotif = new JLabel(notificacion.getDescripcion());
            System.out.println(notificacion.getDescripcion()); //está bien
            jlabels.add(lblNotif);
        }
        for (JLabel jlbl : jlabels)
        {
            jlbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            pnlCentro.add(jlbl);
            pnlCentro.updateUI();
        }

        NotificDialog.this.add(pnlCentro,BorderLayout.CENTER);
        //----------------------------------------------------------------

        this.pack();
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
