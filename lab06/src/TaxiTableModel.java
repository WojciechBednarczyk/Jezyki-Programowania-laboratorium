import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaxiTableModel extends AbstractTableModel {

    private List<Taksowkarz> listaTaxi;
    private String[] nazwyKolumn = {"Numer TAXI","Imie","Nazwisko","Status"};

    public TaxiTableModel(List<Taksowkarz> listaTaxi) {
        this.listaTaxi = listaTaxi;
    }

    @Override
    public int getRowCount() {
        if(listaTaxi==null)
            return 0;
        else
        return listaTaxi.size();
    }

    @Override
    public int getColumnCount() {
        return nazwyKolumn.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) return listaTaxi.get(row).getImie();
        if (col == 1) return listaTaxi.get(row).getNazwisko();
        if (col == 2) return listaTaxi.get(row).getNumerTaxi();
        if (col == 3) return listaTaxi.get(row).getStatus();
        return null;
    }
}
