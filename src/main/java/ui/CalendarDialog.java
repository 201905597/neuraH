package ui;

import client.Client;
import dominio.Calendario;
import dominio.Mes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CalendarDialog extends JDialog
{
    private Calendario calendario;
    private String strTitulo;
    private String tipoCalendar;
    private JVentana ventanaOwner;
    private HashMap<String,Mes> hmMeses; // Septiembre2021, Mes
    private JComboBox cmbMesesSeg;
    private JComboBox cmbNuevosMeses;
    private JButton btnAnadirMes;
    private JTextField txtAnio;
    private String idConectado;
    //private ArrayList<MonthPanel> mesesArray;
    private HashSet<MonthPanel> mesesHSet;
    JPanel pnlCentro;

    private JButton btnVerMeses;
    String cmbDefault;

    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public CalendarDialog(JVentana ventanaOwner, boolean modal, Calendario calendario, String title)
    {
        this.calendario = calendario;
        this.setModal(modal);
        this.setTitle(calendario.getTipoCalendar());
        this.strTitulo = title;
        this.ventanaOwner = ventanaOwner;
        this.hmMeses = new HashMap<String,Mes>();
        this.idConectado = calendario.getIdConectado();
        this.tipoCalendar = calendario.getTipoCalendar();
        mesesHSet = new HashSet<MonthPanel>();

        //PANEL NORTE ----------------------------------------------------------------------------------
        JPanel pnlNorte = new JPanel();
        pnlNorte.setBackground(Color.WHITE);;
        JLabel lblTitulo = new JLabel(strTitulo.toUpperCase());
        lblTitulo.setFont(new Font("Basic", Font.BOLD, 20));
        lblTitulo.setForeground(Color.PINK);
        pnlNorte.add(lblTitulo);
        cmbMesesSeg = new JComboBox();
        cmbDefault = "No hay seguimiento aún";
        cmbMesesSeg.addItem(cmbDefault);
        pnlNorte.add(cmbMesesSeg);
        btnVerMeses = new JButton("Ver mes");
        pnlNorte.add(btnVerMeses);
        btnVerMeses.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String mesAnioSel = String.valueOf(cmbMesesSeg.getSelectedItem());
                if (mesAnioSel != null && mesAnioSel != "No hay seguimiento aún")
                {
                    for (MonthPanel mesPanel : mesesHSet)
                    {
                        if (mesPanel != null)
                        {
                            if (mesPanel.getMes().getMesYAnio().equals(mesAnioSel))
                            {
                                for (DayPanel day : mesPanel.getDiasArrayList())
                                {
                                    day.getBtnDia().addActionListener(new ActionListener()
                                    {
                                        @Override
                                        public void actionPerformed(ActionEvent e)
                                        {
                                            ColoresDialog coloresDlg = new ColoresDialog(day.getDia().getFecha(),ventanaOwner,true, day,ventanaOwner.getUsuario().getId(), tipoCalendar);
                                        }
                                    });
                                }
                                mesPanel.setVisible(true);
                                mesPanel.revalidate();
                                mesPanel.updateUI();
                                pnlCentro.updateUI();
                            }
                            else
                                mesPanel.setVisible(false);
                        }
                    }
                    /*for(Map.Entry<String, Mes> entry : hmMeses.entrySet())
                    {
                        String mes = entry.getKey();
                        if (mes != null)
                        {
                            if (mes.equals(mesAnioSel))
                            {
                                MonthPanel mesPnl = entry.getValue();

                                for (DayPanel day : mesPnl.getMes().getDayArray())
                                {
                                    day.getBtnDia().addActionListener(new ActionListener()
                                    {
                                        @Override
                                        public void actionPerformed(ActionEvent e)
                                        {
                                            ColoresDialog coloresDlg = new ColoresDialog(day.getDia().getFecha(),ventanaOwner,true, day,ventanaOwner.getIdConectado(), tipoCalendar);
                                        }
                                    });
                                }
                                mesPnl.setVisible(true);
                                mesPnl.revalidate();
                                mesPnl.updateUI();
                                pnlCentro.updateUI();
                            }
                            else
                                entry.getValue().setVisible(false);
                        }
                    }*/
                }
            }
        });
        this.add(pnlNorte, BorderLayout.NORTH);
        //---------------------------------------------------------------------------------------------------

        //PANEL CENTRO
        pnlCentro = new JPanel();
        pnlCentro.setBackground(Color.WHITE);;
        this.add(pnlCentro, BorderLayout.CENTER);
        //---------------------------------------------------------------------------------------------------

        //PANEL SUR -----------------------------------------------------------------------------------------
        JPanel pnlSur = new JPanel();
        pnlSur.setBackground(Color.WHITE);;
        JLabel lblAnadir = new JLabel("Nuevo seguimiento de ");
        cmbNuevosMeses = new JComboBox(meses);
        JLabel lblAnio = new JLabel(" del año ");
        txtAnio = new JTextField("2021");
        btnAnadirMes = new JButton("Añadir");

        pnlSur.add(lblAnadir);
        pnlSur.add(cmbNuevosMeses);
        pnlSur.add(lblAnio);
        pnlSur.add(txtAnio);
        pnlSur.add(btnAnadirMes);

        btnAnadirMes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int anioNuevo=2022;
                try{
                    anioNuevo = Integer.parseInt(txtAnio.getText());
                    String nombreMesNuevo = String.valueOf(cmbNuevosMeses.getSelectedItem());
                    if (nombreMesNuevo != null && nombreMesNuevo != cmbDefault)
                    {
                        Mes mesNuevo = new Mes(nombreMesNuevo, anioNuevo, tipoCalendar);
                        CalendarDialog.this.addMonthPnl(mesNuevo);
                    }
                }
                catch(NumberFormatException nfe )
                {
                    JOptionPane.showMessageDialog(CalendarDialog.this, "El año debe ser un numero");
                }
            }
        });

        this.add(pnlSur, BorderLayout.SOUTH);
        //---------------------------------------------------------------------------------------------------


        /**
         * Cuando se abre el CalendarDialog, se leen los datos guardados anteriormente de la bbdd
         */
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e)
            {
                System.out.println("calendar dialog abierto");
                ArrayList<String> mesesAdded = new ArrayList<String>();
                Client client = new Client();
                HashMap<String,Object> session=new HashMap<String, Object>();
                session.put("id",idConectado);
                session.put("habito",tipoCalendar);
                session.put("ventana",ventanaOwner);

                HashSet<Mes> respuestaHSet = new HashSet<Mes>();
                if (tipoCalendar == "Animo")
                {
                    client.metodoClient("/recuperacionAnimo",session);
                    respuestaHSet = (HashSet<Mes>) session.get("RespuestaRecAnimos");
                }
                else
                {
                    client.metodoClient("/recuperacionHabito",session);
                    respuestaHSet = (HashSet<Mes>) session.get("RespuestaRecHabitos");
                }

                for (Mes mes : respuestaHSet)
                {
                    if (!mesesAdded.contains(mes.getMesYAnio()))
                    {
                        MonthPanel mesPanel = new MonthPanel(mes, ventanaOwner);
                        mesesHSet.add(mesPanel);
                        mesesAdded.add(mes.getMesYAnio());
                        CalendarDialog.this.addMonthPnl(mes);
                    }

                }
                ventanaOwner.getUsuario().actualizarNotificaciones();
            }
        });

        /**
         * Cuando se cierra el CalendarDialog, se guardan las notificaciones del usuario en la  bbdd
         */
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                //...
            }
        });

        this.pack();
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Calendario getCalendario()
    {
        return calendario;
    }

    /**
     * Método para añadir meses nuevos al seguimiento
     * @param mesNuevo
     */
    public void addMonthPnl(Mes mesNuevo) //hay que cambiar cosas
    {
        calendario.addMes(mesNuevo);
        MonthPanel pnlMesNuevo = new MonthPanel(mesNuevo, ventanaOwner);
        hmMeses.put(mesNuevo.getMesYAnio(), mesNuevo);
        cmbMesesSeg.addItem(mesNuevo.getMesYAnio());
        if (cmbMesesSeg.getSelectedItem().equals(cmbDefault))
            cmbMesesSeg.removeItem(cmbDefault);
        pnlCentro.add(pnlMesNuevo);
        pnlCentro.updateUI();
        ventanaOwner.getUsuario().actualizarNotificaciones();
    }
}
