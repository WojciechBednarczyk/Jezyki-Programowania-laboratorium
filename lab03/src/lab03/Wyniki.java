package lab03;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Wyniki implements Serializable {

    private final String identyfikator;
    private final LocalDate dataWykonaniaBadania;
    private final Map<Próbki, Integer> wyniki;
    private final String wykonawcaBadania;

    public Wyniki(String identyfikator, LocalDate dataWykonaniaBadania, Map<Próbki, Integer> wyniki, String wykonawcaBadania) {
        this.identyfikator = identyfikator;
        this.dataWykonaniaBadania = dataWykonaniaBadania;
        this.wyniki = wyniki;
        this.wykonawcaBadania = wykonawcaBadania;
    }

    public String getIdentyfikator() {
        return identyfikator;
    }

    @Override
    public String toString() {
        System.out.println("Identyfikator: " + identyfikator);
        System.out.println("Data wykonania badania: " + dataWykonaniaBadania);
        System.out.println("Wykonawca badania: " + wykonawcaBadania);
        System.out.println();


        //Wypisanie mapy wyników
        Set<Map.Entry<Próbki, Integer>> entrySet = wyniki.entrySet();
        for (Map.Entry<Próbki, Integer> entry : entrySet) {
            System.out.println(entry.getKey().getBadanyCzynnik() + " - " + entry.getValue());
        }
        return "";
    }
}
