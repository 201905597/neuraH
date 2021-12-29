package ui;

import client.Client;
import dominio.Actividad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ActividadesDialog extends JDialog
{
    ui.JVentana ventanaOwner;
    JLabel lblPropuesta;
    JRadioButton btnInterior;
    JRadioButton btnExterior;
    JPanel pnlPropuesta;
    ArrayList<Actividad> actividades;
    ButtonGroup btnGroup;
    JButton btnMarcar;
    Actividad actividad;
    JPanel pnlEste = new JPanel();

    public ActividadesDialog(ui.JVentana ventanaOwner, boolean modal)
    {
        this.ventanaOwner = ventanaOwner;
        this.setModal(modal);
        this.setTitle("Actividades");
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.actividades = new ArrayList<Actividad>();
        this.actividad = new Actividad();

        //NORTE -------------------------------------------------------------------
        JLabel lblTitulo = new JLabel("IDEAS DE ACTIVIDADES",SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Basic", Font.BOLD, 20));
        lblTitulo.setForeground(Color.PINK);
        lblTitulo.setBackground(Color.WHITE);
        this.add(lblTitulo, BorderLayout.NORTH);
        //-------------------------------------------------------------------------------

        //OESTE -------------------------------------------------------------------------
        JPanel pnlOeste = new JPanel();
        pnlOeste.setBackground(new Color(253,236,250));
        pnlOeste.setLayout(new BoxLayout(pnlOeste, BoxLayout.Y_AXIS));

        JLabel lblFiltros = new JLabel("FILTROS");
        lblFiltros.setFont(new Font("Basic", Font.BOLD, 14));
        lblFiltros.setForeground(Color.BLACK);
        lblFiltros.setBackground(Color.WHITE);
        pnlOeste.add(lblFiltros);

        JLabel lblLugar = new JLabel(" Lugar: ");
        lblLugar.setBackground(new Color(253,236,250));
        pnlOeste.add(lblLugar);
        btnGroup = new ButtonGroup();
        btnInterior = new JRadioButton("Interior");
        btnInterior.setActionCommand("Interior");
        btnInterior.setSelected(true);
        btnExterior = new JRadioButton("Exterior");
        btnExterior.setActionCommand("Exterior");
        btnInterior.setBackground(new Color(253,236,250));
        btnExterior.setBackground(new Color(253,236,250));
        btnGroup.add(btnInterior);
        btnGroup.add(btnExterior);
        pnlOeste.add(btnInterior);
        pnlOeste.add(btnExterior);

        JLabel lblEspacio = new JLabel(" ");
        lblEspacio.setBackground(new Color(253,236,250));
        JCheckBox chkGratis = new JCheckBox("Actividad gratuita");
        chkGratis.setBackground(new Color(253,236,250));
        pnlOeste.add(chkGratis);

        this.add(pnlOeste,BorderLayout.WEST);
        //-------------------------------------------------------------------------------

        //CENTRO ------------------------------------------------------------------------
        JPanel pnlCentro = new JPanel();
        pnlCentro.setBackground(Color.WHITE);
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.Y_AXIS));

        pnlPropuesta = new JPanel();
        pnlPropuesta.setBackground(Color.WHITE);
        lblPropuesta = new JLabel("\n\n",SwingConstants.CENTER);
        lblPropuesta.setFont(new Font("Basic", Font.BOLD, 20));
        lblPropuesta.setHorizontalAlignment(JLabel.CENTER);
        lblPropuesta.setVerticalAlignment(JLabel.CENTER);
        pnlPropuesta.add(lblPropuesta);
        pnlCentro.add(pnlPropuesta);

        this.add(pnlCentro,BorderLayout.CENTER);
        //-------------------------------------------------------------------------------


        //SUR ---------------------------------------------------------------------------
        JPanel pnlSur = new JPanel();
        pnlSur.setBackground(Color.WHITE);
        JButton btnGenerarActividad = new JButton("Generar actividad aleatoria");
        btnGenerarActividad.setBackground(new Color(253,236,250));
        btnGenerarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                HashMap<String,Object> session = new HashMap<String,Object>();
                session.put("lugar",btnGroup.getSelection().getActionCommand());
                System.out.println(btnGroup.getSelection().getActionCommand());
                if (chkGratis.isSelected())
                {
                    Boolean b = true;
                    session.put("gratis",b);
                }
                else
                {
                    Boolean b = false;
                    session.put("gratis",b);
                }

                client.metodoClient("/getListaActividades",session);
                actividades = (ArrayList<Actividad>) session.get("RespuestaGetActividades");
                /*for (Actividad act : actividades)
                    System.out.println("Descripcion: " + act.getDescripcion());*/
                //Se elige una actividad aleatoria de las que cumplen los requisitos de los filtros
                int indice = (int) (Math.random() * actividades.size());
                actividad = actividades.get(indice);
                lblPropuesta.setText(actividad.getDescripcion());
                pnlPropuesta.updateUI();
                btnMarcar.setEnabled(true);
                pnlEste.updateUI();
            }
        });
        pnlSur.add(btnGenerarActividad);
        this.add(pnlSur,BorderLayout.SOUTH);
        //-------------------------------------------------------------------------------------------

        //ESTE --------------------------------------------------------------------------------------
        //JPanel pnlEste = new JPanel();
        pnlEste.setBackground(new Color(253,236,250));
        pnlEste.setLayout(new BoxLayout(pnlEste, BoxLayout.Y_AXIS));
        JLabel lblMarcar = new JLabel("Marcar actividad como hecha:");
        btnMarcar = new JButton("Hecha");
        btnMarcar.setEnabled(false);
        btnMarcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaOwner.getUsuario().addActividad(actividad.getDescripcion(),actividad.getLugar(),actividad.isGratis()); //add actividad
                Client client = new Client();
                HashMap<String,Object> session = new HashMap<String,Object>();
                session.put("id",ventanaOwner.getUsuario().getId());
                session.put("hm",ventanaOwner.getUsuario().getActividadesHechas());
                client.metodoClient("/insertarActividades",session);
                JOptionPane.showMessageDialog(ActividadesDialog.this,"¡Actividad realizada!");
                session = new HashMap<String,Object>();
            }
        });
        pnlEste.add(lblMarcar);
        pnlEste.add(btnMarcar);
        this.add(pnlEste,BorderLayout.EAST);
        //-------------------------------------------------------------------------------------------


        this.pack();
        this.setBackground(new Color(253,236,250));
        this.setSize(600,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
