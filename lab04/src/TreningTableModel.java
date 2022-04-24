import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TreningTableModel extends AbstractTableModel {

    private List<Trening> listaTreningow;
    private String[] nazwyKolumn = {"ID treningu", "Dystans do pokonania", "Przewidywany czas na pokonanie dystansu", "Termin", "Dystans pokonany", "Rzeczywisty czas pokonania dystansu", "Status", "Data wykonania treningu"};

    @Override
    public int getRowCount() {
        return listaTreningow.size();
    }

    @Override
    public int getColumnCount() {
        return nazwyKolumn.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) return listaTreningow.get(row).getIdTreningu();
        if (col == 1) return listaTreningow.get(row).getDystansDoPokonania();
        if (col == 2) return listaTreningow.get(row).getPrzewidywanyCzasString();
        if (col == 3) return listaTreningow.get(row).getTermin();
        if (col == 4) return listaTreningow.get(row).getDystansPokonany();
        if (col == 5) return listaTreningow.get(row).getRzeczywistyCzasPokonaniaString();
        if (col == 6) return listaTreningow.get(row).getStatus();
        if (col == 7) return listaTreningow.get(row).getDataWykonaniaTreningu();
        return null;
    }

    public TreningTableModel(List<Trening> listaTreningow) {
        this.listaTreningow = listaTreningow;
    }
}
