import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Rejestracja {
    private JPanel panelRejestracji;
    private JLabel tytulLabel;
    private JTextField tNazwisko;
    private JTextField tImie;
    private JTextField ftWiek;
    private JLabel imieLabel;
    private JButton rejestruj;
    private JButton reset;
    private JPanel rejestracjaPrzyciski;
    private JTextField tID;
    private JLabel idLabel;
    private JButton powrot;
    String imie;
    String nazwisko;
    int wiek;
    String id;

    public Rejestracja(JFrame rejestracja) {
        BazaPodopiecznych.odczyt();
        rejestruj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                try {
                    imie = tImie.getText();
                    nazwisko = tNazwisko.getText();
                    wiek = Integer.parseInt(ftWiek.getText());
                    id = tID.getText();
                    if (imie.equals("") || nazwisko.equals("") || id.equals(""))
                        JOptionPane.showMessageDialog(null, "Pola nie mogą pozostać puste!");
                    else if (!(BazaPodopiecznych.wyszukajPoID(id) == null)) {
                        JOptionPane.showMessageDialog(null, "Takie ID już istnieje w systemie");
                        tID.setText("");
                    } else {
                        Podopieczny podopieczny = new Podopieczny(imie, nazwisko, wiek, id);
                        BazaPodopiecznych.zarejestruj(podopieczny);
                        JOptionPane.showMessageDialog(null, "Zarejestrowano podopiecznego");
                        BazaPodopiecznych.zapis();
                        rejestracja.dispose();
                        Aplikacja.getFrame().show();
                    }
                } catch (NumberFormatException numberexception) {
                    JOptionPane.showMessageDialog(null, "Podaj poprawny wiek!");
                    ftWiek.setText("");
                } catch (IOException e) {
                    e.printStackTrace();

                }


            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tImie.setText("");
                tNazwisko.setText("");
                ftWiek.setText("");
                tID.setText("");
                ;
            }
        });
        powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rejestracja.dispose();
                Aplikacja.getFrame().show();
            }
        });
    }

    public JPanel getPanelRejestracji() {
        return panelRejestracji;
    }
}
