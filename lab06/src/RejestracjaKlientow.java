import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RejestracjaKlientow {
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JButton zarejestrujButton;
    private JButton cofnijButton;
    private JPanel panelGlowny;
    private int ostatniNumerKlienta=1201;
    private String daneKlienta[];

    public JPanel getPanelGlowny() {
        return panelGlowny;
    }

    public RejestracjaKlientow(JFrame rejestracja) throws IOException {
        Writer zapis = new BufferedWriter(new FileWriter("klienci.txt", true));
        Scanner odczytKlientow = new Scanner(new File("klienci.txt"));

        while (odczytKlientow.hasNextLine())
        {

                try {
                    odczytKlientow.nextLine();
                    ostatniNumerKlienta++;
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

                if (imieTextField.getText().equals("") || nazwiskoTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Wypelnij wszystkie pola!");
                else {
                    try {
                        zapis.append(imieTextField.getText() + ";" + nazwiskoTextField.getText()+";"+ostatniNumerKlienta+"\n");
                        zapis.close();
                        JOptionPane.showMessageDialog(null,"Zarejestrowano Klienta \nTwoj numer klienta to: " + ostatniNumerKlienta);
                        rejestracja.dispose();
                    } catch (IOException exception) {

                    }
                }
            }

        });
    }
}
