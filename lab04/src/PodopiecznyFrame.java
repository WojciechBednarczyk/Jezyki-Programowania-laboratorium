import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PodopiecznyFrame {
    private JPanel podopiecznyPanel;
    private JTabbedPane tabbedPane;
    private JButton powrotButton;
    private JPanel podopiecznyTabPanel;
    private JLabel imieLabel;
    private JLabel idLabel;
    private JLabel wiekLabel;
    private JLabel nazwiskoLabel;
    private JLabel imieLabelRight;
    private JLabel nazwiskoLabelRight;
    private JLabel wiekLabelRight;
    private JLabel idLabelRight;
    private JTable tabelaTreningow;
    private JTextField tDystansDoPokonania;
    private JTextField tPrzewidywanyCzas;
    private JTextField tTermin;
    private JButton dodajTreningButton;
    private JPanel przydzielTreningPanel;
    private JPanel sprawdzTreningiPanel;
    private JPanel wykonanyTreningPanel;
    private JPanel wykresTreningPanel;
    private JTextField tIDTreningu;
    private JTextField tDystansPrzebyty;
    private JTextField tRzeczywistyCzasPokonania;
    private JButton wykonajButton;
    private JLabel czasPokonaniaLabel;
    private JLabel dystansPrzebytyLabel;
    private JLabel idTreninguLabel;
    private JButton wykresButton;
    private JLabel wykresLabel;
    private LocalDate termin;
    private int dystans;
    private int idTreningu;
    private Date czasPokonaniaDystansu;
    private Date przewidywanyCzas;
    private List<Trening> listaTreningow;
    private List<Trening> treningiWykonane = new ArrayList<>();

    public JPanel getPodopiecznyPanel() {
        return podopiecznyPanel;
    }

    public PodopiecznyFrame(JFrame podopiecznyFrame, String id) {


        listaTreningow = BazaPodopiecznych.wyszukajPoID(id).getListaTreningow();
        tabelaTreningow.setModel(new TreningTableModel(listaTreningow));
        tabelaTreningow.getColumnModel().getColumn(0).setHeaderValue("ID treningu");
        tabelaTreningow.getColumnModel().getColumn(1).setHeaderValue("Dystans do pokonania");
        tabelaTreningow.getColumnModel().getColumn(2).setHeaderValue("Przewidywany czas na pokonanie dystansu");
        tabelaTreningow.getColumnModel().getColumn(3).setHeaderValue("Termin");
        tabelaTreningow.getColumnModel().getColumn(4).setHeaderValue("Dystans pokonany");
        tabelaTreningow.getColumnModel().getColumn(5).setHeaderValue("Rzeczywisty czas pokonania dystansu");
        tabelaTreningow.getColumnModel().getColumn(6).setHeaderValue("Status");
        tabelaTreningow.getColumnModel().getColumn(7).setHeaderValue("Data wykonania treningu");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        idLabelRight.setText(BazaPodopiecznych.wyszukajPoID(id).getId());
        imieLabelRight.setText(BazaPodopiecznych.wyszukajPoID(id).getImie());
        nazwiskoLabelRight.setText(BazaPodopiecznych.wyszukajPoID(id).getNazwisko());
        wiekLabelRight.setText(Integer.toString(BazaPodopiecznych.wyszukajPoID(id).getWiek()));
        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                podopiecznyFrame.dispose();
                try {
                    BazaPodopiecznych.zapis();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        dodajTreningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean dodaj = true;

                try {
                    dystans = Integer.parseInt(tDystansDoPokonania.getText());
                    przewidywanyCzas = simpleDateFormat.parse(tPrzewidywanyCzas.getText());
                    termin = LocalDate.parse(tTermin.getText());
                    int idTreningu;
                    if (BazaPodopiecznych.wyszukajPoID(id).getListaTreningow() == null)
                        idTreningu = 1;
                    else
//                        idTreningu=BazaPodopiecznych.getListaPodopiecznych().size()+1;
                        idTreningu = BazaPodopiecznych.wyszukajPoID(id).getListaTreningow().size() + 1;
                    Trening trening = new Trening(termin, dystans, przewidywanyCzas, idTreningu);
                    BazaPodopiecznych.wyszukajPoID(id).dodajTrening(trening);
                    BazaPodopiecznych.zapis();


                    JOptionPane.showMessageDialog(null, "Dodano trening");
                    tDystansDoPokonania.setText("");
                    tPrzewidywanyCzas.setText("");
                    tTermin.setText("");
                    SwingUtilities.updateComponentTreeUI(podopiecznyFrame);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Podaj dystans w metrach");
                    tDystansDoPokonania.setText("");
                    dodaj = false;


                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Podaj poprawny format czasu HH:MM");
                    tPrzewidywanyCzas.setText("");
                    dodaj = false;
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Podaj poprawny format daty RR-MM-DD");
                    tTermin.setText("");
                    dodaj = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        wykonajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if (tIDTreningu.getText().equals("") || tDystansPrzebyty.getText().equals("") || tRzeczywistyCzasPokonania.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Pola nie mogą być puste");
                    else {
                        try {
                            idTreningu = Integer.parseInt(tIDTreningu.getText());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "ID musi być liczbą");
                            tIDTreningu.setText("");
                        }
                        try {
                            dystans = Integer.parseInt(tDystansPrzebyty.getText());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Dystans musi być liczbą");
                            tDystansPrzebyty.setText("");
                        }
                        czasPokonaniaDystansu = simpleDateFormat.parse(tRzeczywistyCzasPokonania.getText());
                        try {
                            Trening trening = BazaPodopiecznych.wyszukajPoID(id).getListaTreningow().get(idTreningu - 1);
                            if (trening.getStatus().equals("Wykonano")) {
                                JOptionPane.showMessageDialog(null, "Ten trening już został wykonany");
                                tIDTreningu.setText("");
                            } else {
                                trening.setDataWykonaniaTreningu(LocalDate.now());
                                trening.setDystansPokonany(dystans);
                                trening.setRzeczywistyCzasPokonania(czasPokonaniaDystansu);
                                trening.wykonajTrening();
                                JOptionPane.showMessageDialog(null, "Zrealizowano trening");
//                                treningiWykonane.add(trening);
                                tIDTreningu.setText("");
                                tRzeczywistyCzasPokonania.setText("");
                                tDystansPrzebyty.setText("");


                            }
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Nie ma takiego ID treningu");
                            tIDTreningu.setText("");
                        }

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Podaj poprawny format czasu [HH:MM]");
                    tRzeczywistyCzasPokonania.setText("");
                }


            }
        });
        wykresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                treningiWykonane = new ArrayList<>();
                for (Trening trening : listaTreningow) {
                    if (trening.getStatus().equals("Wykonano"))
                        treningiWykonane.add(trening);
                }
                JFrame wykres = new JFrame();
                wykres.setSize(600, 400);
                wykres.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                wykres.getContentPane().add(new WykresPanel(treningiWykonane));
                wykres.pack();
                wykres.setVisible(true);

                try {
                    BazaPodopiecznych.zapis();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
