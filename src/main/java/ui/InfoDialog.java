package ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InfoDialog extends JDialog
{
    JTextArea txtArea;

    public InfoDialog(JVentana ventanaOwner, boolean modal)
    {
        this.setModal(modal);
        this.setTitle("Información");
        this.setLayout(new BorderLayout());

        //NORTE
        JLabel lblTitulo = new JLabel("INFORMACIÓN",SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 20));
        this.add(lblTitulo, BorderLayout.NORTH);

        //CENTRO (text pane)
        JPanel pnlCentro = new JPanel();
        pnlCentro.setOpaque(false);
        txtArea = new JTextArea(20,70);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("InfoSaludMental.txt");
        StringBuilder resultStringBuilder = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        txtArea.append(resultStringBuilder.toString());


        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setOpaque(false);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pnlCentro.add(scroll);
        this.add(pnlCentro, BorderLayout.CENTER);

        this.pack();
        this.setBackground(new Color(253,236,250));
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
