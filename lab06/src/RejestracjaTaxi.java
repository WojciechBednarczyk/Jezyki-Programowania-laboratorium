import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RejestracjaTaxi {
    private JPanel panelGlowny;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JButton zarejestrujButton;
    private JButton cofnijButton;
    private String imie;
    private String nazwisko;
    private int ostatniNumerTaxi=1025;

    public JPanel getPanelGlowny() {
        return panelGlowny;
    }

    public RejestracjaTaxi(JFrame rejestracja) throws IOException {

        Writer zapis = new BufferedWriter(new FileWriter("taksowkarze.txt", true));
        Scanner odczytTaxi = new Scanner(new File("taksowkarze.txt"));

        while (odczytTaxi.hasNextLine())
        {

            try {
                odczytTaxi.nextLine();
                ostatniNumerTaxi++;
            }
            catch (NoSuchElementException exception)
            {

            }



        }

        cofnijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rejestracja.dispose();
            }
        });
        zarejestrujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(imieTextField.getText().equals("") || nazwiskoTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Wypelnij wszystkie pola!");
                else
                {
                    try {
                        zapis.append(imieTextField.getText() + ";" + nazwiskoTextField.getText()+";"+ostatniNumerTaxi+";Nie pracuje\n");
                        zapis.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }

                    Taksowkarz taksowkarz = new Taksowkarz(imie,nazwisko,ostatniNumerTaxi,"Nie pracuje");
                    JOptionPane.showMessageDialog(null,"Twoj numer TAXI to " + ostatniNumerTaxi);
                    rejestracja.dispose();
                }
            }
        });
    }
}
