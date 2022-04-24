import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Trening implements Serializable {

    private LocalDate termin;
    private int dystansDoPokonania;
    private int dystansPokonany;
    private int idTreningu;
    private Date przewidywanyCzas;
    private String status = "Niewykonany";
    private Date rzeczywistyCzasPokonania;
    private String przewidywanyCzasString;
    private LocalDate dataWykonaniaTreningu;
    private String rzeczywistyCzasPokonaniaString;
    private int rzeczywistyCzasPokonaniaInt;
    private int przewidywanyCzaspokonaniaInt;
    private int roznica;

    public void wykonajTrening() {
        status = "Wykonano";
    }

    public Trening(LocalDate termin, int dystansDoPokonania, Date przewidywanyCzas, int idTreningu) {
        this.termin = termin;
        this.dystansDoPokonania = dystansDoPokonania;
        this.przewidywanyCzas = przewidywanyCzas;
        this.idTreningu = idTreningu;
        przewidywanyCzasString = przewidywanyCzas.getHours() + ":" + przewidywanyCzas.getMinutes();
        if (przewidywanyCzas.getMinutes() == 0)
            przewidywanyCzasString += "0";
        przewidywanyCzaspokonaniaInt = przewidywanyCzas.getHours() * 60 + przewidywanyCzas.getMinutes();

    }

    public LocalDate getTermin() {
        return termin;
    }

    public int getDystansDoPokonania() {
        return dystansDoPokonania;
    }

    public int getDystansPokonany() {
        return dystansPokonany;
    }

    public int getIdTreningu() {
        return idTreningu;
    }

    public Date getPrzewidywanyCzas() {
        return przewidywanyCzas;
    }

    public String getStatus() {
        return status;
    }

    public Date getRzeczywistyCzasPokonania() {
        return rzeczywistyCzasPokonania;
    }

    public String getPrzewidywanyCzasString() {
        return przewidywanyCzasString;
    }

    public LocalDate getDataWykonaniaTreningu() {
        return dataWykonaniaTreningu;
    }

    public void setDataWykonaniaTreningu(LocalDate dataWykonaniaTreningu) {
        this.dataWykonaniaTreningu = dataWykonaniaTreningu;
    }

    public void setDystansPokonany(int dystansPokonany) {
        this.dystansPokonany = dystansPokonany;
        roznica = dystansDoPokonania - dystansPokonany;
    }

    public void setRzeczywistyCzasPokonania(Date rzeczywistyCzasPokonania) {
        this.rzeczywistyCzasPokonania = rzeczywistyCzasPokonania;
        rzeczywistyCzasPokonaniaString = rzeczywistyCzasPokonania.getHours() + ":" + rzeczywistyCzasPokonania.getMinutes();
        if (rzeczywistyCzasPokonania.getMinutes() == 0)
            rzeczywistyCzasPokonaniaString += "0";
        rzeczywistyCzasPokonaniaInt = rzeczywistyCzasPokonania.getHours() * 60 + rzeczywistyCzasPokonania.getMinutes();
    }

    public String getRzeczywistyCzasPokonaniaString() {
        return rzeczywistyCzasPokonaniaString;
    }

    public int getRoznica() {
        return roznica;
    }
}
