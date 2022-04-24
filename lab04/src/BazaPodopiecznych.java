import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BazaPodopiecznych {

    private static List<Podopieczny> listaPodopiecznych = new ArrayList<>();
    private static List<Trening> listaTreningowWykonanych = new ArrayList<>();

    public static void zapis() throws IOException {
        ObjectOutputStream plik;
        try {
            plik = new ObjectOutputStream(new FileOutputStream("ListaPodopiecznych.txt"));
            plik.writeObject(listaPodopiecznych);
            plik.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void odczyt() {
        ObjectInputStream plikOdczyt;
        try {
            plikOdczyt = new ObjectInputStream(new FileInputStream("ListaPodopiecznych.txt"));
            listaPodopiecznych = (List<Podopieczny>) plikOdczyt.readObject();
        } catch (IOException ignored) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static List<Podopieczny> getListaPodopiecznych() {
        return listaPodopiecznych;
    }

    public static void zarejestruj(Podopieczny podopieczny) {
        listaPodopiecznych.add(podopieczny);
    }

    public static void wyrejestruj(String id) {
        listaPodopiecznych.remove(wyszukajPoID(id));
    }

    public static Podopieczny wyszukajPoID(String id) {
        for (Podopieczny podopieczny : listaPodopiecznych) {
            if (podopieczny.getId().equals(id))
                return podopieczny;
        }
        return null;
    }

    public static List<Trening> getListaTreningowWykonanych() {
        return listaTreningowWykonanych;
    }
}
