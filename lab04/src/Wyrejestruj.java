import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Wyrejestruj {
    private JLabel wyrejestrujLabel;
    private JLabel wyrejestrujIDlabel;
    private JTextField tID;
    private JButton wyrejestrujButton;
    private JPanel wyrejestrujPanel;
    private JButton powrotPrzycisk;

    private String id;

    public Wyrejestruj(JFrame wyrejestruj){
        BazaPodopiecznych.odczyt();
        wyrejestrujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                id=tID.getText();
                if(id.equals(""))
                    JOptionPane.showMessageDialog(null,"Pole nie może pozostać puste");
                else
                if((BazaPodopiecznych.wyszukajPoID(id) == null)) {
                    JOptionPane.showMessageDialog(null, "Nie ma podopiecznego z takim ID");
                    tID.setText("");
                }
                else {
                    BazaPodopiecznych.wyrejestruj(id);
                    JOptionPane.showMessageDialog(null, "Wyrejestrowano podopiecznego");
                    try {
                        BazaPodopiecznych.zapis();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    wyrejestruj.dispose();
                    Aplikacja.getFrame().show();
                }
            }
        });
        powrotPrzycisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wyrejestruj.dispose();
                Aplikacja.getFrame().show();
            }
        });
    }

    public JPanel getWyrejestrujPanel() {
        return wyrejestrujPanel;
    }
}
