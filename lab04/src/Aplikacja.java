import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aplikacja {
    private static JFrame frame;
    private JButton przyciskRejestr;
    private JPanel panelGlowny;
    private JPanel panelRejestracji;
    private JButton przyciskWyrejestr;
    private JButton przyciskBaza;
    private JLabel aplikacjaLabel;

    public static JFrame getFrame() {
        return frame;
    }


    public Aplikacja() {

        przyciskRejestr.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent actionEvent) {
                JFrame rejestracja = new JFrame();
                rejestracja.setSize(400, 250);
                rejestracja.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                rejestracja.setContentPane(new Rejestracja(rejestracja).getPanelRejestracji());
                rejestracja.pack();
                rejestracja.setVisible(true);
                frame.hide();
            }
        });
        przyciskWyrejestr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame wyrejestruj = new JFrame();
                wyrejestruj.setSize(400, 250);
                wyrejestruj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                wyrejestruj.setContentPane(new Wyrejestruj(wyrejestruj).getWyrejestrujPanel());
                wyrejestruj.pack();
                wyrejestruj.setVisible(true);
                frame.hide();
            }
        });
        przyciskBaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame pokazBaze = new JFrame();
                pokazBaze.setSize(600, 400);
                pokazBaze.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                pokazBaze.setContentPane(new PokazBaze(pokazBaze).getPokazBazePanel());
                pokazBaze.pack();
                pokazBaze.setVisible(true);
                frame.hide();
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("TrenerPRO");
        frame.setContentPane(new Aplikacja().panelGlowny);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(600, 300, 600, 300);
//        frame.pack();
        frame.setVisible(true);


    }
}
