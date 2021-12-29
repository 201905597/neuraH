package ui;

import javax.swing.*;
import java.awt.*;

public class JuegoDialog extends JDialog
{
    public JuegoDialog(boolean modal) {

        this.setTitle("Juegos");
        this.setModal(modal);

        JLabel lblJuegos = new JLabel("MIS JUEGOS",SwingConstants.CENTER);
        lblJuegos.setPreferredSize(new Dimension(20, 20));
        lblJuegos.setForeground(Color.BLUE.darker());
        lblJuegos.setFont(new Font("Basic", Font.BOLD, 23));
        this.add(lblJuegos, BorderLayout.NORTH);

        //JUEGOS RELAJANTES
        JPanel pnlJuegosRelaj = new JPanel();
        pnlJuegosRelaj.setBorder(javax.swing.BorderFactory.createTitledBorder("Juegos relajantes"));
        pnlJuegosRelaj.setLayout(new GridLayout(2,3));
        pnlJuegosRelaj.setBackground(new Color(245,235,242));

        JPanel pnlJuegosRelaj1= new JPanel();
        pnlJuegosRelaj1.setBackground(new Color(245,235,242));
        JPanel pnlJuegosRelaj2 = new JPanel();
        pnlJuegosRelaj2.setBackground(new Color(245,235,242));
        JPanel pnlJuegosRelaj3= new JPanel();
        pnlJuegosRelaj3.setBackground(new Color(245,235,242));
        JPanel pnlJuegosRelaj4 = new JPanel();
        pnlJuegosRelaj4.setBackground(new Color(245,235,242));
        JPanel pnlJuegosRelaj5= new JPanel();
        pnlJuegosRelaj5.setBackground(new Color(245,235,242));
        JPanel pnlJuegosRelaj6 = new JPanel();
        pnlJuegosRelaj6.setBackground(new Color(245,235,242));

        JLabel lblImagen1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego1.jpg")));
        pnlJuegosRelaj1.add(lblImagen1);

        JLabel lblImagen2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego2.jpeg")));
        pnlJuegosRelaj2.add(lblImagen2);

        JLabel lblImagen3 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego3.jpg")));
        pnlJuegosRelaj3.add(lblImagen3);

        URLabel label1 = new URLabel();
        label1.setURL("https://www.juegosdiarios.com/juegos/helix-jump-online.html");
        label1.setText("Jugar al Helix Jump");
        pnlJuegosRelaj4.add(label1);

        URLabel label2 = new URLabel();
        label2.setURL("https://poki.com/es/g/one-more-bounce");
        label2.setText("Jugar a One more bounce");
        pnlJuegosRelaj5.add(label2);

        URLabel label3 = new URLabel();
        label3.setURL("https://www.1001juegos.com/juego/blue");
        label3.setText("Jugar a Blue");
        pnlJuegosRelaj6.add(label3);


        pnlJuegosRelaj.add(pnlJuegosRelaj1);
        pnlJuegosRelaj.add(pnlJuegosRelaj2);
        pnlJuegosRelaj.add(pnlJuegosRelaj3);
        pnlJuegosRelaj.add(pnlJuegosRelaj4);
        pnlJuegosRelaj.add(pnlJuegosRelaj5);
        pnlJuegosRelaj.add(pnlJuegosRelaj6);

        this.add(pnlJuegosRelaj,BorderLayout.CENTER);


        //JUEGOS PARA EJERCITAR LA MENTE
        JPanel pnlJuegosEjercMente = new JPanel();
        pnlJuegosEjercMente.setBorder(javax.swing.BorderFactory.createTitledBorder("Juegos para ejercitar la mente"));
        pnlJuegosEjercMente.setLayout(new GridLayout(2,3));
        pnlJuegosEjercMente.setBackground(new Color(245,235,242));
        JPanel pnlJuegosEjercMente1= new JPanel();
        pnlJuegosEjercMente1.setBackground(new Color(245,235,242));
        JPanel pnlJuegosEjercMente2 = new JPanel();
        pnlJuegosEjercMente2.setBackground(new Color(245,235,242));
        JPanel pnlJuegosEjercMente3= new JPanel();
        pnlJuegosEjercMente3.setBackground(new Color(245,235,242));
        JPanel pnlJuegosEjercMente4 = new JPanel();
        pnlJuegosEjercMente4.setBackground(new Color(245,235,242));
        JPanel pnlJuegosEjercMente5= new JPanel();
        pnlJuegosEjercMente5.setBackground(new Color(245,235,242));
        JPanel pnlJuegosEjercMente6 = new JPanel();
        pnlJuegosEjercMente6.setBackground(new Color(245,235,242));

        JLabel lblImagen4 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego4.png")));
        pnlJuegosEjercMente1.add(lblImagen4);

        JLabel lblImagen5 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego5.JPG")));
        pnlJuegosEjercMente2.add(lblImagen5);

        JLabel lblImagen6 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego6.png")));
        pnlJuegosEjercMente3.add(lblImagen6);

        URLabel label4 = new URLabel();
        label4.setURL("https://www.cognifit.com/es/juegos-mentales/buscapalabras#");
        label4.setText("Jugar al buscapalabras");
        pnlJuegosEjercMente4.add(label4);

        URLabel label5 = new URLabel();
        label5.setURL("https://www.ajedrezonline.com/");
        label5.setText("Jugar al ajedrez");
        pnlJuegosEjercMente5.add(label5);

        URLabel label6 = new URLabel();
        label6.setURL("https://www.cognifit.com/es/juegos-mentales/sudoku");
        label6.setText("Jugar al sudoku");
        pnlJuegosEjercMente6.add(label6);


        pnlJuegosEjercMente.add(pnlJuegosEjercMente1);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente2);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente3);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente4);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente5);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente6);

        this.add(pnlJuegosEjercMente,BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setVisible(true);

    }
}
