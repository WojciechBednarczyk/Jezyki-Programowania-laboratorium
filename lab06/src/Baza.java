import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Baza {
    private JPanel panelGlowny;
    private JTabbedPane tabbedPane1;
    private JTextField numerZgloszeniaTextField;
    private JButton cofnijButton;
    private JButton przydzielZgłoszenieButton;
    private JTextField numerTaxiTextField;
    private JTable tabelaTAXI;
    private JTable tabelaZgloszen;
    private JButton odswiezButton;
    private String[] daneTaksowkarza;
    private String[] daneZgloszenia;
    private int numerZgloszenia = 0;

    private List<Zgloszenie> listaZgloszen2 = new ArrayList<>();
    private List<Taksowkarz> listaTaxi2 = new ArrayList<>();
    public JPanel getPanelGlowny() {
        return panelGlowny;
    }

    public void zapisz() throws IOException {
        Writer zapis = new BufferedWriter(new FileWriter("taksowkarze.txt",false));
        for(Taksowkarz taksowkarz : listaTaxi2)
        {
            try {
                zapis.append(taksowkarz.toString() + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        zapis.close();
    }

    public void zapiszZgloszenia() throws IOException {
        Writer zapis = new BufferedWriter(new FileWriter("listaZgloszen.txt",false));
        for(Zgloszenie zgloszenie : listaZgloszen2)
        {
            try {
                zapis.append(zgloszenie.toString() + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        zapis.close();
    }

    public void odczytajZgloszenia () throws FileNotFoundException {
        listaZgloszen2= new ArrayList<>();
        Scanner odczytZgloszen= new Scanner(new File("listaZgloszen.txt"));
        numerZgloszenia=0;
        while (odczytZgloszen.hasNextLine())
        {
            try {
                daneZgloszenia=odczytZgloszen.nextLine().split(";");
                numerZgloszenia++;
                Zgloszenie zgloszenie = new Zgloszenie(numerZgloszenia,Integer.parseInt(daneZgloszenia[1]),daneZgloszenia[2],daneZgloszenia[3],daneZgloszenia[4],daneZgloszenia[5],daneZgloszenia[6]);
                listaZgloszen2.add(zgloszenie);

            }
            catch (NoSuchElementException exception)
            {

            }
        }
    }
    public void odczytajTaxi() throws FileNotFoundException {
        listaTaxi2=new ArrayList<>();
        Scanner odczytTaxi = new Scanner(new File("taksowkarze.txt"));
        while (odczytTaxi.hasNextLine())
        {
            try {
                daneTaksowkarza=odczytTaxi.nextLine().split(";");
                Taksowkarz taksowkarz = new Taksowkarz(daneTaksowkarza[0],daneTaksowkarza[1],Integer.parseInt(daneTaksowkarza[2]),daneTaksowkarza[3]);
                listaTaxi2.add(taksowkarz);

            }
            catch (NoSuchElementException exception)
            {

            }
        }
    }

    public Taksowkarz wyszukajTaksowkarza(int numerTaxi)
    {
        for(Taksowkarz taksowkarz : listaTaxi2)
        {
            if(taksowkarz.getNumerTaxi()==numerTaxi) {
                return taksowkarz;
            }
        }
        return null;
    }

    public boolean czyJestZgloszenie(int numerZgloszenia,List<Zgloszenie> listaZgloszen)
    {

        boolean jestZgloszenie=false;
        System.out.println(listaZgloszen.size());
        for(Zgloszenie zgloszenie : listaZgloszen)
        {
            System.out.println(zgloszenie.getNumerZgloszenia());
            if(zgloszenie.getNumerZgloszenia()==numerZgloszenia) {
                jestZgloszenie = true;
                break;
            }
        }
        return jestZgloszenie;
    }
    public boolean czyJestWolny(int numerTaxi,List<Taksowkarz> listaTaxi)
    {
        boolean jestWolny=false;
        for(Taksowkarz taksowkarz : listaTaxi)
        {
            if(taksowkarz.getNumerTaxi()==numerTaxi)
            {
                if(taksowkarz.getStatus().equals("Wolny")) {
                    jestWolny = true;
                    break;
                }
            }
        }
        return jestWolny;
    }
    public boolean czyJestTaxi(int numerTaxi, List<Taksowkarz> listaTaxi)
    {
        boolean jestTaksowkarz=false;
        for(Taksowkarz taksowkarz : listaTaxi)
        {
            if(taksowkarz.getNumerTaxi()==numerTaxi)
            {
                jestTaksowkarz=true;
                break;
            }
        }
        return jestTaksowkarz;
    }

    public Zgloszenie wyszukajZgloszenie(int numerZgloszenia,List<Zgloszenie> listaZgloszen)
    {
        for(Zgloszenie zgloszenie : listaZgloszen)
        {
            if(zgloszenie.getNumerZgloszenia()==numerZgloszenia)
            {
                return zgloszenie;
            }
        }
        return null;
    }

    public int wyszukujPoNumerzeZgloszenia(int numerZgloszenia,List<Zgloszenie> listaZgloszen)
    {
        for(Zgloszenie zgloszenie : listaZgloszen)
        {
            if(zgloszenie.getNumerZgloszenia()==numerZgloszenia)
            {
                return zgloszenie.getNumerKlienta();
            }
        }
        return 0;
    }

    public Baza(JFrame baza,List<Zgloszenie> listaZgloszen,List<Taksowkarz> listaTaxi) {

        listaTaxi2=listaTaxi;
//        listaZgloszen2=listaZgloszen;

        tabelaZgloszen.setModel(new ZgloszenieTableModel(listaZgloszen));
        tabelaZgloszen.getColumnModel().getColumn(0).setHeaderValue("Numer zgloszenia");
        tabelaZgloszen.getColumnModel().getColumn(1).setHeaderValue("Numer klienta");
        tabelaZgloszen.getColumnModel().getColumn(2).setHeaderValue("Poczatek trasy");
        tabelaZgloszen.getColumnModel().getColumn(3).setHeaderValue("Koniec trasy");
        tabelaZgloszen.getColumnModel().getColumn(4).setHeaderValue("Data i godzina przyjazdu");
        tabelaZgloszen.getColumnModel().getColumn(5).setHeaderValue("Status");
        tabelaZgloszen.getColumnModel().getColumn(6).setHeaderValue("Dodatkowe uwagi dla kierowcy");

        tabelaTAXI.setModel(new TaxiTableModel(listaTaxi));
        tabelaTAXI.getColumnModel().getColumn(0).setHeaderValue("Imie");
        tabelaTAXI.getColumnModel().getColumn(1).setHeaderValue("Nazwisko");
        tabelaTAXI.getColumnModel().getColumn(2).setHeaderValue("Numer Taxi");
        tabelaTAXI.getColumnModel().getColumn(3).setHeaderValue("Status");


        cofnijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                baza.dispose();
            }
        });

        przydzielZgłoszenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(numerZgloszeniaTextField.getText().equals("") || numerTaxiTextField.getText().equals("")) {

                    JOptionPane.showMessageDialog(null,"Wszystkie pola musza byc wypelnione!");
                }
                else
                {
                    int numerZgloszenia = Integer.parseInt(numerZgloszeniaTextField.getText());
                    listaZgloszen.get(numerZgloszenia-1).setStatus("W trakcie realizacji");
                    int numerTaxi = Integer.parseInt(numerTaxiTextField.getText());
                    try {
                         numerZgloszenia = Integer.parseInt(numerZgloszeniaTextField.getText());
                         numerTaxi = Integer.parseInt(numerTaxiTextField.getText());
                    }catch (NumberFormatException exception)
                    {
                        exception.printStackTrace();
                    }
                    if(czyJestZgloszenie(numerZgloszenia,listaZgloszen)==false)
                    {
                        JOptionPane.showMessageDialog(null,"Nie ma takiego zgloszenia");
                    }
                    else
                    if(czyJestTaxi(numerTaxi,listaTaxi)==false)
                    {
                        JOptionPane.showMessageDialog(null,"Nie ma takiego taksowkarza");
                    }
                    else
                    if(czyJestWolny(numerTaxi,listaTaxi)==false)
                    {
                        JOptionPane.showMessageDialog(null,"Ten taksowkarz jest aktualnie zajety lub nie pracuje");
                    }
                    else
                    {
                        System.out.println(wyszukujPoNumerzeZgloszenia(numerZgloszenia,listaZgloszen));
                        new NadawcaCentrala().send("Zgloszenie w trakcie realizacji",wyszukujPoNumerzeZgloszenia(numerZgloszenia,listaZgloszen));
                        new NadawcaCentrala().send(wyszukajZgloszenie(numerZgloszenia,listaZgloszen).toString(),numerTaxi);
                    }
                }

                //odczytanie przed zapisem
                try {
                    odczytajTaxi();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                wyszukajTaksowkarza(Integer.parseInt(numerTaxiTextField.getText())).setStatus("Zajety");
                try {
                    zapisz();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                //odczytanie przed zapisem
                try {
                    odczytajZgloszenia();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                wyszukajZgloszenie(numerZgloszenia,listaZgloszen2).setStatus("W trakcie");
                try {
                    zapiszZgloszenia();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                baza.dispose();
            }
        });
        odswiezButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    listaTaxi2=new ArrayList<>();
                    Scanner odczytTaxi = new Scanner(new File("taksowkarze.txt"));
                    while (odczytTaxi.hasNextLine())
                    {
                        try {
                            daneTaksowkarza=odczytTaxi.nextLine().split(";");
                            Taksowkarz taksowkarz = new Taksowkarz(daneTaksowkarza[0],daneTaksowkarza[1],Integer.parseInt(daneTaksowkarza[2]),daneTaksowkarza[3]);
                            listaTaxi2.add(taksowkarz);

                        }
                        catch (NoSuchElementException exception)
                        {

                        }
                    }
                    tabelaTAXI.setModel(new TaxiTableModel(listaTaxi2));
                    tabelaTAXI.getColumnModel().getColumn(0).setHeaderValue("Imie");
                    tabelaTAXI.getColumnModel().getColumn(1).setHeaderValue("Nazwisko");
                    tabelaTAXI.getColumnModel().getColumn(2).setHeaderValue("Numer Taxi");
                    tabelaTAXI.getColumnModel().getColumn(3).setHeaderValue("Status");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    numerZgloszenia=0;
                    listaZgloszen2=new ArrayList<>();
                    Scanner odczyt = new Scanner(new File("listaZgloszen.txt"));
                    while (odczyt.hasNextLine())
                    {
                        try {

                            daneZgloszenia=odczyt.nextLine().split(";");
                            numerZgloszenia++;
                            Zgloszenie zgloszenie = new Zgloszenie(numerZgloszenia,Integer.parseInt(daneZgloszenia[1]),daneZgloszenia[2],daneZgloszenia[3],daneZgloszenia[4],daneZgloszenia[5],daneZgloszenia[6]);
                            listaZgloszen2.add(zgloszenie);
//

                        }catch (NoSuchElementException e)
                        {

                        }

                    }
                    tabelaZgloszen.setModel(new ZgloszenieTableModel(listaZgloszen2));
                    tabelaZgloszen.getColumnModel().getColumn(0).setHeaderValue("Numer zgloszenia");
                    tabelaZgloszen.getColumnModel().getColumn(1).setHeaderValue("Numer klienta");
                    tabelaZgloszen.getColumnModel().getColumn(2).setHeaderValue("Poczatek trasy");
                    tabelaZgloszen.getColumnModel().getColumn(3).setHeaderValue("Koniec trasy");
                    tabelaZgloszen.getColumnModel().getColumn(4).setHeaderValue("Data i godzina przyjazdu");
                    tabelaZgloszen.getColumnModel().getColumn(5).setHeaderValue("Status");
                    tabelaZgloszen.getColumnModel().getColumn(6).setHeaderValue("Dodatkowe uwagi dla kierowcy");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(baza);
                JOptionPane.showMessageDialog(null,"Odswiezono tabele");
            }
        });
    }

//    public void dodajZgloszenie(Zgloszenie zgloszenie) {
//        listaZgloszen.add(zgloszenie);
//    }
}
