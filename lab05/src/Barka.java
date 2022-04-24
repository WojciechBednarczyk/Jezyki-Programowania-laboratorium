import javax.swing.*;

public class Barka extends Thread {


    private String etykieta;
    private int idEtykiety;
    private String status;
    private boolean koniec;
    private int szansa;
    private String polozenie = "wschod";
    private String stan = "b";
    private ThreadGroup barkaGrupa;
    private Most most;
    private JLabel[] polozenieWschod;
    private JLabel[] polozenieZachod;

    @Override
    public void run() {


        while (!koniec) {
            if (!stan.equals("B")) {
                szansa = (int) (Math.random() * 2);
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (szansa == 1) {
                    etykieta = "B" + idEtykiety;
                    stan = "B";
                    most.dodajOczekujacaBarke();
                    if (polozenie.equals("wschod")) {

                        for (JLabel pozycja : polozenieWschod) {
                            if (pozycja.getText().toUpperCase().equals(etykieta)) {
                                pozycja.setText(etykieta);
                                try {
                                    sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    } else {
                        for (JLabel pozycja : polozenieZachod) {
                            if (pozycja.getText().toUpperCase().equals(etykieta)) {
                                pozycja.setText(etykieta);
                                try {
                                    sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                }

                status = "oczekujacy";

            } else {
                try {
                    most.przeplyn(polozenie, etykieta, idEtykiety, stan);
                    stan = "b";
                    if (polozenie.equals("wschod"))
                        polozenie = "zachod";
                    else
                        polozenie = "wschod";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public Barka(ThreadGroup barkaGrupa, int idEtykiety, Most most, JLabel[] polozenieWschod, JLabel[] polozenieZachod) {
        this.idEtykiety = idEtykiety;
        this.etykieta = "b" + idEtykiety;
        this.most = most;
        this.polozenieWschod = polozenieWschod;
        this.polozenieZachod = polozenieZachod;
        this.barkaGrupa = barkaGrupa;
    }


    public String getEtykieta() {
        return etykieta;
    }
}
