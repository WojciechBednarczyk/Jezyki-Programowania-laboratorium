import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ZgloszenieTableModel extends AbstractTableModel {

    private List<Zgloszenie> listaZgloszen = new ArrayList<>();
    private String[] nazwyKolumn = {"Numer zgloszenia","Numer klienta","Adres poczatkowy", "Adres koncowy","Data i godzina przyjazdu", "Dodatkowe uwagi dla kierowcy","Status"};


    public ZgloszenieTableModel(List<Zgloszenie> listaZgloszen) {
        this.listaZgloszen = listaZgloszen;
    }

    @Override
    public int getRowCount() {
        if(listaZgloszen == null)
        return 0;
        else
        return listaZgloszen.size();
    }

    @Override
    public int getColumnCount() {
        return nazwyKolumn.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) return listaZgloszen.get(row).getNumerZgloszenia();
        if (col == 1) return listaZgloszen.get(row).getNumerKlienta();
        if (col == 2) return listaZgloszen.get(row).getAdresPoczatkowy();
        if (col == 3) return listaZgloszen.get(row).getAdresKoncowy();
        if (col == 4) return listaZgloszen.get(row).getDataGodzinaPrzyjazdu();
        if (col == 5) return listaZgloszen.get(row).getStatus();
        if (col == 6) return listaZgloszen.get(row).getDodatkoweUwagi();
        return null;
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
