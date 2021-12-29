package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import dominio.Usuario;
import dominio.Psicologo;

public class InformeDialog extends JDialog
{
    private ui.JVentana ventanaOwner;
    ButtonGroup btnGroup;
    ArrayList<JRadioButton> botones;
    ButtonGroup btnGroupSistemas;

    public InformeDialog(ui.JVentana ventanaOwner, boolean modal)
    {
        this.ventanaOwner = ventanaOwner;
        this.setModal(modal);
        this.setLayout(new BorderLayout());
        botones = new ArrayList<JRadioButton>();

        //PANEL NORTE ---------------------------------------------------------------
        JPanel pnlNorte = new JPanel();
        pnlNorte.setBackground(Color.WHITE);
        pnlNorte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel lblTitulo = new JLabel("GENERACIÓN DE INFORMES",SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Basic", Font.BOLD, 20));
        lblTitulo.setForeground(Color.PINK);
        lblTitulo.setOpaque(false);
        JLabel lblLogo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logoo.png")));
        JLabel lblLogo2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logoo.png")));
        pnlNorte.add(lblLogo);
        pnlNorte.add(lblTitulo);
        pnlNorte.add(lblLogo2);
        this.add(pnlNorte,BorderLayout.NORTH);
        //---------------------------------------------------------------------------

        //PANEL CENTRO --------------------------------------------------------------
        JPanel pnlCentro = new JPanel();
        pnlCentro.setBackground(Color.WHITE);
        pnlCentro.setLayout(new BoxLayout(pnlCentro,BoxLayout.Y_AXIS));

        JLabel lblEligePaciente = new JLabel("Elija el paciente: ");
        pnlCentro.add(lblEligePaciente);
        btnGroup = new ButtonGroup();
        HashSet<Usuario> pacientes = ventanaOwner.getPsicologo().getPacientes();
        for (Usuario paciente : pacientes)
        {
            JRadioButton btnPac = new JRadioButton(paciente.getNombre() + "-" + paciente.getId());
            btnGroup.add(btnPac);
            botones.add(btnPac);
            pnlCentro.add(btnPac);
            if (botones.size() == 1)
                botones.get(0).setSelected(true);
        }

        JLabel lblEnviar = new JLabel("Elija a qué sistema enviar el informe: ");
        pnlCentro.add(lblEnviar);
        btnGroupSistemas = new ButtonGroup();
        JRadioButton btnCentro = new JRadioButton("Mi centro");
        btnCentro.setSelected(true);
        JRadioButton btnMadrid = new JRadioButton("Salud Madrid");
        btnGroupSistemas.add(btnCentro);
        btnGroupSistemas.add(btnMadrid);
        pnlCentro.add(btnCentro);
        pnlCentro.add(btnMadrid);

        this.add(pnlCentro,BorderLayout.CENTER);
        //---------------------------------------------------------------------------

        //PANEL SUR -----------------------------------------------------------------
        JPanel pnlSur = new JPanel();
        JButton btnGenerar = new JButton("Generar informe");
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //se generaría y enviaría el informe y se abre un joptionpane que indique que se ha generado bien
                JOptionPane.showMessageDialog(ui.InformeDialog.this,"Informe enviado correctamente");
            }
        });
        pnlSur.add(btnGenerar);
        this.add(pnlSur, BorderLayout.SOUTH);
        //---------------------------------------------------------------------------

        this.pack();
        this.setBackground(Color.WHITE);
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
