import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TerminalKlienta {
    private JButton zamowTAXIButton;
    private JTextField poczatekTrasyTextField;
    private JTextField dataGodzinaTextField;
    private JTextField numerKlientaTextField;
    private JTextField koniecTrasyTextField;
    private JTextField dodatkoweUwagiTextField;
    private JPanel panelGlowny;
    private JTextField statusZgloszeniaTextField;
    private JLabel statusZgloszenia;
    private String adresPoczatkowy;
    private String adresKoncowy;
    private int numerZgloszenia;
    private String dodatkoweUwagi;
    private String status;
    private String dataGodzinaPrzyjazdu;
    private int numerKlienta;

    public TerminalKlienta() throws IOException {


        File plik = new File("listaZgloszen.txt");
        Writer zapis = new BufferedWriter(new FileWriter("listaZgloszen.txt", true));
        statusZgloszeniaTextField.setEditable(false);
        zamowTAXIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (poczatekTrasyTextField.getText().equals("") || koniecTrasyTextField.getText().equals("") || numerKlientaTextField.getText().equals("") || dataGodzinaTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Pierwsze cztery pola nie moga pozostac puste!");
                else {
                    adresPoczatkowy = poczatekTrasyTextField.getText();
                    adresKoncowy = koniecTrasyTextField.getText();
                    dataGodzinaPrzyjazdu = dataGodzinaTextField.getText();
                    if (dodatkoweUwagiTextField.getText().equals(""))
                        dodatkoweUwagi = "brak";
                    else
                        dodatkoweUwagi = dodatkoweUwagiTextField.getText();
                    status = "oczekujace";
                    statusZgloszeniaTextField.setText("Oczekujace");
                    zamowTAXIButton.setVisible(false);
                    numerKlienta = Integer.parseInt(numerKlientaTextField.getText());
                    Zgloszenie zgloszenie = new Zgloszenie(numerZgloszenia, numerKlienta, adresPoczatkowy, adresKoncowy, dataGodzinaPrzyjazdu, status, dodatkoweUwagi);

                    try {
                        zapis.append(zgloszenie.toString() + "\n");
                        zapis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //wyslanie do centrali
                    new NadawcaKlient().send(zgloszenie.toString());
                }
                OdbiorcaTerminal odbiorcaTerminal = new OdbiorcaTerminal(statusZgloszeniaTextField, numerKlienta);
                odbiorcaTerminal.start();
            }

        });
    }

    public static void main(String[] args) throws IOException {
        JFrame terminal = new JFrame("KLIENT");
        terminal.setContentPane(new TerminalKlienta().panelGlowny);
        terminal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        terminal.setBounds(600, 300, 900, 500);
        terminal.setVisible(true);

    }
}
