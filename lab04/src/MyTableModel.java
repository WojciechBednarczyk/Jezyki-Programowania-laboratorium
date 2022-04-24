import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel extends AbstractTableModel {

    private List<Podopieczny> listaPodopiecznych;
    private String[] nazwyKolumn = {"Imie", "Nazwisko", "Wiek", "ID"};

    public MyTableModel(List<Podopieczny> listaPodopiecznych) {
        this.listaPodopiecznych = listaPodopiecznych;
    }


    public List<Podopieczny> getListaPodopiecznych() {
        return listaPodopiecznych;
    }

    public void setListaPodopiecznych(List<Podopieczny> listaPodopiecznych) {
        this.listaPodopiecznych = listaPodopiecznych;
    }

    @Override
    public int getRowCount() {
        return listaPodopiecznych.size();
    }

    @Override
    public int getColumnCount() {
        return nazwyKolumn.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) return listaPodopiecznych.get(row).getImie();
        if (col == 1) return listaPodopiecznych.get(row).getNazwisko();
        if (col == 2) return listaPodopiecznych.get(row).getWiek();
        if (col == 3) return listaPodopiecznych.get(row).getId();
        return null;
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public String getNazwaKolumny(int col) {
        return nazwyKolumn[col - 1];
    }
}
