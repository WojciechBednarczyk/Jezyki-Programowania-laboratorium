import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Podopieczny implements Serializable {
    @Override
    public String toString() {
        return "Podopieczny [Imie=" + imie + ", Nazwisko=" + nazwisko + ", wiek=" + wiek + ", ID=" + id + "]";
    }

    private final String imie;
    private final String nazwisko;
    private int wiek;
    private final String id;
    private List<Trening> listaTreningow = new ArrayList<Trening>();
    private List<Trening> listaTreningowWykonanych = new ArrayList<>();

    public Podopieczny(String name, String surname, int age, String id) {
        this.imie = name;
        this.nazwisko = surname;
        this.wiek = age;
        this.id = id;
    }

    public String getImie() {
        return imie;
    }


    public String getNazwisko() {
        return nazwisko;
    }


    public int getWiek() {
        return wiek;
    }

    public String getId() {
        return id;
    }

    public List<Trening> getListaTreningow() {
        return listaTreningow;
    }

    public void dodajTrening(Trening trening) {
        listaTreningow.add(trening);
    }

    public List<Trening> getListaTreningowWykonanych() {
        return listaTreningowWykonanych;
    }
}
