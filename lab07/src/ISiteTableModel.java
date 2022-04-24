import communication.ISite;

import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

public class ISiteTableModel extends AbstractTableModel {

    private List<ISite> listaISite;
    private String[] columnNames = {"ISite"};

    public ISiteTableModel(List<ISite> listaISite) {
        this.listaISite = listaISite;
    }

    public void add(ISite iSite) {
        this.listaISite.add(iSite);
        this.fireTableRowsInserted(listaISite.size() - 1, listaISite.size());
    }

    public void remove(ISite iSite) throws RemoteException {
        for (Iterator<ISite> it = listaISite.iterator(); it.hasNext(); ) {
            ISite next = it.next();
            System.out.println(next.getName());
            System.out.println(iSite.getName());
            if (next.getName().equals(iSite.getName()))
                it.remove();
        }
        this.fireTableRowsInserted(listaISite.size() - 1, listaISite.size());

    }

    @Override
    public int getRowCount() {
        return listaISite.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            try {
                return listaISite.get(row).getName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
