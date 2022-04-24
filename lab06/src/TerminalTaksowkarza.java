import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TerminalTaksowkarza {
    private JPanel panelGlowny;
    private JTextField numerTextField;
    private JButton zalogujButton;
    private JButton zakonczPraceButton;
    private JButton zrealizujZgloszenieButton;
    private JLabel numerLabel;
    private JTextField zgloszenieTextField;
    private int numer;
    private boolean zgloszenie = false;
    private Taksowkarz taksowkarz;
    private List<Taksowkarz> taksowkarzList = new ArrayList<>();
    private String[] daneTaksowkarza;

    public void zapisz() throws IOException {
        Writer zapis = new BufferedWriter(new FileWriter("taksowkarze.txt", false));
        for (Taksowkarz taksowkarz : taksowkarzList) {
            try {
                zapis.append(taksowkarz.toString() + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        zapis.close();
    }

    public void odczytaj() throws FileNotFoundException {
        taksowkarzList = new ArrayList<>();
        Scanner odczytTaxi = new Scanner(new File("taksowkarze.txt"));
        while (odczytTaxi.hasNextLine()) {
            try {
                daneTaksowkarza = odczytTaxi.nextLine().split(";");
                Taksowkarz taksowkarz = new Taksowkarz(daneTaksowkarza[0], daneTaksowkarza[1], Integer.parseInt(daneTaksowkarza[2]), daneTaksowkarza[3]);
                taksowkarzList.add(taksowkarz);

            } catch (NoSuchElementException exception) {

            }


        }

    }

    public boolean wyszkujPoNumerze(int numer) {
        boolean isTaksowkarz = false;
        for (Taksowkarz taksowkarz : taksowkarzList) {
            if (taksowkarz.getNumerTaxi() == numer) {
                isTaksowkarz = true;
                break;
            }
        }
        return isTaksowkarz;
    }

    public Taksowkarz wyszukajTaksowkarza(int numer) {
        for (Taksowkarz taksowkarz : taksowkarzList) {
            if (taksowkarz.getNumerTaxi() == numer)
                this.taksowkarz = taksowkarz;
        }
        return taksowkarz;
    }

    public TerminalTaksowkarza(JFrame terminal) throws FileNotFoundException {
        zakonczPraceButton.setVisible(false);
        zrealizujZgloszenieButton.setVisible(false);
        zgloszenieTextField.setVisible(false);
        zgloszenieTextField.setEditable(false);

        odczytaj();
        zalogujButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (numerTextField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Podaj swój numer TAXI!");
                    else
                        numer = Integer.parseInt(numerTextField.getText());
                    if (wyszkujPoNumerze(numer) == false) {
                        JOptionPane.showMessageDialog(null, "Nie ma takiego numeru TAXI!");
                    } else {
                        numer = Integer.parseInt(numerTextField.getText());
                        zakonczPraceButton.setVisible(true);
                        zgloszenieTextField.setVisible(true);
                        zrealizujZgloszenieButton.setVisible(true);
                        zalogujButton.setVisible(false);
                        numerTextField.setText("");
                        numerTextField.setVisible(false);
                        numerLabel.setText("TAXI " + numer);
                        wyszukajTaksowkarza(numer);
                        //Przed kazdym zapisaniem do pliku powinienem go jescze raz odczytac zeby nie nadpisac zmian z innych aplikacji
                        odczytaj();
                        wyszukajTaksowkarza(numer).setStatus("Wolny");
                        zapisz();
                        JOptionPane.showMessageDialog(null, "Zalogowano");
                        terminal.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

                    }

                } catch (NumberFormatException | FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(null, "Numer TAXI musi być liczbą!");
                    numerTextField.setText("");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                OdbiorcaTerminal odbiorcaTerminal = new OdbiorcaTerminal(zgloszenieTextField, numer);
                odbiorcaTerminal.start();
            }
        });
        zakonczPraceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!zgloszenieTextField.getText().equals("Brak zgloszenia"))
                    JOptionPane.showMessageDialog(null, "Nie zakonczono zgloszenia!");
                else {
                    zrealizujZgloszenieButton.setVisible(false);
                    zgloszenieTextField.setVisible(false);
                    zalogujButton.setVisible(true);
                    numerTextField.setVisible(true);

                    try {
                        odczytaj();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    wyszukajTaksowkarza(numer).setStatus("Nie pracuje");
                    try {
                        zapisz();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    terminal.dispose();
                }
            }
        });
        zrealizujZgloszenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Zrealizowano zgłoszenie");
                zgloszenie = false;
                int numerZgloszenia = Integer.parseInt(zgloszenieTextField.getText().substring(0, zgloszenieTextField.getText().indexOf(";")));
                zgloszenieTextField.setText("Brak zgloszenia");
                Writer zapisZgloszen = null;
                try {
                    zapisZgloszen = new BufferedWriter(new FileWriter("zrealizowane zgloszenia.txt", true));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                try {
                    zapisZgloszen.append(String.valueOf(numerZgloszenia) + "\n");

                } catch (IOException e) {
                    e.printStackTrace();

                }
                try {
                    zapisZgloszen.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                //odczytanie przed zapisem
                try {
                    odczytaj();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                wyszukajTaksowkarza(numer).setStatus("Wolny");
                try {
                    zapisz();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

        });
    }

    public static void main(String[] args) throws FileNotFoundException {

        JFrame terminal = new JFrame("TAXI");
        terminal.setContentPane(new TerminalTaksowkarza(terminal).panelGlowny);
        terminal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        terminal.setBounds(600, 300, 600, 300);
        terminal.setVisible(true);
    }
}
