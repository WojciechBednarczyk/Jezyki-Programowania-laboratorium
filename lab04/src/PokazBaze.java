import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokazBaze {
    private JPanel panelPrzyciskow;
    private JTable tabelaPodopiecznych;
    private JPanel pokazBazePanel;
    private JButton statusButton;
    private JButton powrot;
    private JTable table1;
    private JLabel statusLabel;
    private JTextField tID;
    private JButton wykresZbiorowyWykonanychTreningówButton;

    public JPanel getPokazBazePanel() {
        return pokazBazePanel;
    }

    List<Podopieczny> listaPodopiecznych = new ArrayList<>();
    List<Trening> listaTreningowWykonanych = new ArrayList<>();

    public PokazBaze(JFrame pokazBaze) {
        BazaPodopiecznych.odczyt();
        listaPodopiecznych = BazaPodopiecznych.getListaPodopiecznych();
        table1.setModel(new MyTableModel(BazaPodopiecznych.getListaPodopiecznych()));
        table1.getColumnModel().getColumn(0).setHeaderValue("Imię");
        table1.getColumnModel().getColumn(1).setHeaderValue("Nazwisko");
        table1.getColumnModel().getColumn(2).setHeaderValue("Wiek");
        table1.getColumnModel().getColumn(3).setHeaderValue("ID");
        powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pokazBaze.dispose();
                Aplikacja.getFrame().show();
            }
        });
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (tID.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Pole id nie może być puste");
                else if (BazaPodopiecznych.wyszukajPoID(tID.getText()) == null)
                    JOptionPane.showMessageDialog(null, "Nie ma podopiecznego z takim ID");
                else {
                    JFrame podopiecznyFrame = new JFrame();
                    podopiecznyFrame.setSize(600, 400);
                    podopiecznyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    podopiecznyFrame.setContentPane(new PodopiecznyFrame(podopiecznyFrame, tID.getText()).getPodopiecznyPanel());
                    podopiecznyFrame.setVisible(true);
                }
                tID.setText("");
            }
        });
        wykresZbiorowyWykonanychTreningówButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listaTreningowWykonanych = new ArrayList<>();
                for (Podopieczny podopieczny : listaPodopiecznych) {
                    for (Trening trening : podopieczny.getListaTreningow()) {
                        if (trening.getStatus().equals("Wykonano"))
                            listaTreningowWykonanych.add(trening);
                    }
                }
                System.out.println(listaTreningowWykonanych.size());
                JFrame wykres = new JFrame();
                wykres.setSize(600, 400);
                wykres.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                wykres.getContentPane().add(new WykresPanel(listaTreningowWykonanych));
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

    public void setTabelaPodopiecznych(JTable tabelaPodopiecznych) {
        this.tabelaPodopiecznych = tabelaPodopiecznych;
    }
}
