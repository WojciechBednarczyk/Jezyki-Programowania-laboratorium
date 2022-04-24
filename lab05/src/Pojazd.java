import javax.swing.*;

public class Pojazd extends Thread {

    private String etykieta;
    private int idEtykiety;
    private int szansa;
    private boolean koniec;
    private String stan = "p";
    private JLabel[] polozeniePolnoc;
    private JLabel[] polozeniePoludnie;
    private String polozenie = "polnoc";
    private ThreadGroup pojazdGrupa;
    private Most most;

    @Override
    public void run() {

        while (!koniec) {
            if (!stan.equals("P")) {
                szansa = (int) (Math.random() * 2);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (szansa == 1) {
                    etykieta = "P" + idEtykiety;
                    stan = "P";
                    if (polozenie.equals("polnoc")) {

                        for (JLabel pozycja : polozeniePolnoc) {
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
                        for (JLabel pozycja : polozeniePoludnie) {
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

            } else {
                try {
                    most.przejazd(polozenie, etykieta, idEtykiety, stan);
                    stan = "p";
                    if (polozenie.equals("polnoc"))
                        polozenie = "poludnie";
                    else
                        polozenie = "polnoc";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Pojazd(ThreadGroup pojazdGrupa, int idEtykiety, Most most, JLabel[] polozeniePolnoc, JLabel[] polozeniePoludnie) {
        this.idEtykiety = idEtykiety;
        this.etykieta = "p" + idEtykiety;
        this.most = most;
        this.polozeniePolnoc = polozeniePolnoc;
        this.polozeniePoludnie = polozeniePoludnie;
        this.pojazdGrupa = pojazdGrupa;
    }

    public String getEtykieta() {
        return etykieta;
    }

}
