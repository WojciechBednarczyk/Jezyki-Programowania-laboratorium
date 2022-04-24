import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Aplikacja {
    private JTextField tWatki;
    private JButton startButton;
    private JButton stopButton;
    private JPanel panelDolny;
    private JLabel mostLabel;
    private JPanel panelGlowny;
    private JPanel panelGorny;
    private JLabel polnoc1;
    private JLabel polnoc2;
    private JLabel polnoc3;
    private JLabel polnoc4;
    private JLabel polnoc5;
    private JLabel poludnie1;
    private JLabel poludnie2;
    private JLabel poludnie3;
    private JLabel poludnie4;
    private JLabel poludnie5;
    private JLabel zachod1;
    private JLabel zachod2;
    private JLabel zachod3;
    private JLabel zachod4;
    private JLabel zachod5;
    private JLabel wschod1;
    private JLabel wschod2;
    private JLabel wschod3;
    private JLabel wschod4;
    private JLabel wschod5;

    private int parametr;
    private int iloscPojazdow;
    private int iloscBarek;

    private List<Pojazd> listaPojazdow = new ArrayList<Pojazd>();
    private List<Barka> listaBarek = new ArrayList<Barka>();
    private JLabel[] polozeniePolnoc = new JLabel[]{polnoc1, polnoc2, polnoc3, polnoc4, polnoc5};
    private JLabel[] polozeniePoludnie = new JLabel[]{poludnie1, poludnie2, poludnie3, poludnie4, poludnie5};
    private JLabel[] polozenieWschod = new JLabel[]{wschod1, wschod2, wschod3, wschod4, wschod5};
    private JLabel[] polozenieZachod = new JLabel[]{zachod1, zachod2, zachod3, zachod4, zachod5};

    public Aplikacja() {

        startButton.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent actionEvent) {

                                              Most most = new Most(mostLabel, polozeniePolnoc, polozeniePoludnie, polozenieWschod, polozenieZachod);
                                              try {
                                                  parametr = Integer.parseInt(tWatki.getText());
                                                  if (parametr > 10 || parametr < 1) {
                                                      JOptionPane.showMessageDialog(null, "Podaj liczbę z zakresu 1-10");
                                                      tWatki.setText("");
                                                  } else {
                                                      listaBarek = new ArrayList<>();
                                                      listaPojazdow = new ArrayList<>();
                                                      if (parametr % 2 == 0) {
                                                          iloscPojazdow = parametr / 2;
                                                          iloscBarek = parametr / 2;
                                                      } else {
                                                          iloscPojazdow = (parametr / 2) + 1;
                                                          iloscBarek = parametr / 2;
                                                      }
                                                      ThreadGroup pojazdGrupa = new ThreadGroup("watki pojazdow");
                                                      for (int i = 1; i <= iloscPojazdow; i++) {
                                                          Pojazd pojazd = new Pojazd(pojazdGrupa, i, most, polozeniePolnoc, polozeniePoludnie);
                                                          listaPojazdow.add(pojazd);
                                                      }

                                                      ThreadGroup barkaGrupa = new ThreadGroup("watki barek");
                                                      for (int i = 1; i <= iloscBarek; i++) {
                                                          Barka barka = new Barka(barkaGrupa, i, most, polozenieWschod, polozenieZachod);
                                                          listaBarek.add(barka);

                                                      }
                                                      for (Pojazd pojazd : listaPojazdow) {
                                                          for (JLabel polozenie : polozeniePolnoc) {
                                                              if (polozenie.getText().equals("--")) {
                                                                  polozenie.setText(pojazd.getEtykieta());
                                                                  break;
                                                              }
                                                          }
                                                      }
                                                      for (Barka barka : listaBarek) {
                                                          for (JLabel polozenie : polozenieWschod) {
                                                              if (polozenie.getText().equals("--")) {
                                                                  polozenie.setText(barka.getEtykieta());
                                                                  break;
                                                              }
                                                          }
                                                      }
                                                  }

                                                  //Uruchomienie watkow
                                                  for (Pojazd pojazd : listaPojazdow)
                                                      pojazd.start();
                                                  for (Barka barka : listaBarek)
                                                      barka.start();
                                              } catch (NumberFormatException e) {
                                                  JOptionPane.showMessageDialog(null, "Ilość wątków musi być liczbą!");
                                                  tWatki.setText("");
                                              }

                                          }
                                      }


        );
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tWatki.setText("");
                for (Pojazd pojazd : listaPojazdow)
                    pojazd.stop();
                for (Barka barka : listaBarek)
                    barka.stop();
                for (JLabel etykieta : polozeniePolnoc) {
                    etykieta.setText("--");
                }
                for (JLabel etykieta : polozeniePoludnie) {
                    etykieta.setText("--");
                }
                for (JLabel etykieta : polozenieWschod) {
                    etykieta.setText("--");
                }
                for (JLabel etykieta : polozenieZachod) {
                    etykieta.setText("--");
                }
                mostLabel.setText("<html>[ ]<br>[ ]<br>[ ] ");
            }
        });
    }


    public static void main(String[] args) {

        JFrame aplikacja = new JFrame("Symulator");
        aplikacja.setContentPane(new Aplikacja().panelGlowny);
        aplikacja.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        aplikacja.setBounds(600, 300, 600, 300);
        aplikacja.setVisible(true);


    }
}
