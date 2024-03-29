package ui;

import client.Client;
import main.java.ui.RecomendacionDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class UsernameDialog extends JDialog
{
    private ui.JVentana ventanaOwner;
    JTextField jtxf,txtCentro;
    JPasswordField txtId;
    JToggleButton check;
    String tipo;

    public UsernameDialog(ui.JVentana ventanaOwner, boolean modal) {
        this.addWindowListener(new WindowAdapter() {

            public void windowOpened(WindowEvent e) {
                ventanaOwner.setVisible(false);

            }
        });
        this.setTitle("Log In / Acceder");
        this.setModal(modal);
        this.setOwner(ventanaOwner);

        JPanel pnlNorte = new JPanel();
        pnlNorte.setBackground(new Color(253,228,242));
        JLabel lblLogo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logoo.png")));
        pnlNorte.add(lblLogo);
        this.add(pnlNorte, BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(4, 2));
        pnlCentro.setBackground(new Color(253,228,242));
        this.add(pnlCentro, BorderLayout.CENTER);

        JLabel lblUser = new JLabel("Usuario: ");
        JTextField txtUser = new JTextField(12);
        txtUser.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    txtId.requestFocus();
            }
        });

        pnlCentro.add(lblUser);
        pnlCentro.add(txtUser);


        JLabel lblId = new JLabel("Id: ");
        txtId = new JPasswordField(12);
        txtId.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    txtId.requestFocus();
            }
        });
        pnlCentro.add(lblId);
        pnlCentro.add(txtId);

        check=new JToggleButton("Acceder como psicólogo");
        check.setBackground(new Color(250,167,214));
        pnlCentro.add(check);

        JPanel jpnlPsico=new JPanel();
        jpnlPsico.setBackground(new Color(253,228,242));
        jpnlPsico.setLayout(new GridLayout(1, 2));
        pnlCentro.add(jpnlPsico);


        JLabel lblCentro = new JLabel("Centro: ");
        txtCentro = new JTextField(12);
        txtCentro.setVisible(false);
        txtCentro.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    txtCentro.requestFocus();

            }
        });

        jpnlPsico.add(lblCentro);
        jpnlPsico.add(txtCentro);

        tipo = "usuario";
        ventanaOwner.setTipoUsuarioEntrante(tipo);

        /**
         * Se procede de manera distinta según si se accede como psicólogo (botón seleccionado) o no
         */
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check.isSelected())
                {
                    tipo = "psicologo";
                    ventanaOwner.setTipoUsuarioEntrante(tipo);
                    txtCentro.setVisible(true);
                }
                else
                {
                    tipo = "usuario";
                    ventanaOwner.setTipoUsuarioEntrante(tipo);
                    txtCentro.setVisible(false);
                }
            }
        });

        /**
         * Se procede de manera distinta según si se accede como psicólogo (botón seleccionado) o no
         */
        JButton btnAcceder = new JButton("Acceder");
        btnAcceder.setBackground(new Color(150,187,235));
        btnAcceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = txtUser.getText();
                String id = String.valueOf(txtId.getPassword());
                String centro = txtCentro.getText();
                Client client = new Client();
                HashMap<String,Object> session=new HashMap<String, Object>();


                int respuesta=0;

                if(tipo=="usuario")
                {
                    session.put("id",id);
                    session.put("nombre",nombre);
                    client.metodoClient("/peticionAccesoUsuario",session);
                    respuesta = (Integer) session.get("RespuestaAcceso1");
                }
                if (tipo=="psicologo")
                {
                    session.put("id",id);
                    session.put("centro",centro);
                    client.metodoClient("/peticionAccesoPsicologo",session);
                    respuesta = (Integer) session.get("RespuestaAcceso2");
                }

                if (respuesta==1)
                {
                    ventanaOwner.setIdConectado(id);
                    (UsernameDialog.this).dispose();

                    if(tipo=="usuario")
                    {
                        ventanaOwner.setTipoUsuarioEntrante("usuario");
                        ventanaOwner.setUsuario(id,nombre);
                        ventanaOwner.gestionarEventos();
                        ventanaOwner.setVisible(true);
                        //STRATEGY
                        RecomendacionDialog rcg = new RecomendacionDialog(ventanaOwner, true, tipo);
                    }
                    if (tipo=="psicologo")
                    {
                        ventanaOwner.setTipoUsuarioEntrante("psicologo");
                        ventanaOwner.setPsicologo(id,nombre,centro);
                        ventanaOwner.setVisible(true);
                        ventanaOwner.gestionarEventos();
                        //STRATEGY
                        RecomendacionDialog rcg = new RecomendacionDialog(ventanaOwner, true, tipo);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(UsernameDialog.this, "No se encuentra el nombre o el id en la base de datos");
                }


            }
        });
        pnlCentro.add(btnAcceder);

        /**
         * Si se da a Cancelar, se cierra la app
         */
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(150,187,235));
        btnCancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                (UsernameDialog.this).dispose();
                ventanaOwner.dispose();
            }
        });
        //-------------

        pnlCentro.add(btnCancelar);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(450, 350);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
    public void setOwner(JVentana ventanaOwner)
    {
        this.ventanaOwner = ventanaOwner;
    }
}
