package ui;

import dominio.Notificacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class NotificDialog extends JDialog
{
    private JVentana  ventanaOwner;
    private HashSet<Notificacion> notificaciones;
    ArrayList<JLabel> jlabels;

    public NotificDialog(JVentana ventanaOwner, boolean modal)
    {
        this.ventanaOwner = ventanaOwner;
        this.notificaciones = new HashSet<Notificacion>();
        this.setModal(modal);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.WHITE);

        //NORTE ----------------------------------------------------------
        JLabel lblMisNotif = new JLabel("MIS NOTIFICACIONES",SwingConstants.CENTER);
        lblMisNotif.setPreferredSize(new Dimension(50, 40));
        lblMisNotif.setForeground(Color.PINK.darker());
        this.add(lblMisNotif, BorderLayout.NORTH);
        //----------------------------------------------------------------

        //CENTRO ---------------------------------------------------------
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(7,1));
        notificaciones = ventanaOwner.getUsuario().getNotificaciones();
        for (Notificacion notificacion : notificaciones)
        {
            JLabel lblNotif = new JLabel(notificacion.getDescripcion());
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
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
