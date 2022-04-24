public class Taksowkarz {

    private final String imie;
    private final String nazwisko;
    private int numerTaxi;
    private String status = "Nie pracuje";


    public Taksowkarz(String imie, String nazwisko,int numerTaxi,String status) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTaxi=numerTaxi;
        this.status=status;
    }

    @Override
    public String toString() {
        return imie + ";" + nazwisko + ";" + numerTaxi + ";" + status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void przyjmijZgloszenie(Zgloszenie zgloszenie)
    {

    }
    public void odrzucZgloszenie(Zgloszenie zgloszenie)
    {

    }

    public int getNumerTaxi() {
        return numerTaxi;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getStatus() {
        return status;
    }
}