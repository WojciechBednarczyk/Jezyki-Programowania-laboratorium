import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import communication.IControlCenter;
import communication.IManager;
import communication.ISite;
import support.Mixer;

import javax.swing.*;

public class Manager extends JFrame implements IManager {

    private List<ISite> listaISite = new ArrayList<>();
    private List<Mixer> listaMixer = new ArrayList<>();
    private static IControlCenter ic;
    private JPanel panelGlowny;
    private JButton startButton;
    private JButton stopButton;
    private JTable iSiteTable;
    private JTable mixerTable;

    private ISite iSiteChecked = null;
    private Mixer mixerChecked = null;

    public Manager() throws RemoteException {
        listaISite = ic.getAllSites();
        listaMixer = ic.getAllMixers();
        iSiteTable.setModel(new ISiteTableModel(listaISite));
        iSiteTable.getColumnModel().getColumn(0).setHeaderValue("ISite");
        mixerTable.setModel(new MixerTableModel(listaMixer));
        mixerTable.getColumnModel().getColumn(0).setHeaderValue("Mixer");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (ISite iSite : listaISite) {
                    if (iSiteTable.getSelectedRow() == -1) {
                        continue;
                    } else {
                        try {
                            if (iSite.getName().equals(iSiteTable.getValueAt(iSiteTable.getSelectedRow(), 0))) {
                                iSiteChecked = iSite;
                                System.out.println(iSiteChecked.getName());
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for (Mixer mixer : listaMixer) {
                    if (mixerTable.getSelectedRow() == -1) {
                        mixerChecked = null;
                        continue;

                    } else {
                        System.out.println(mixerTable.getSelectedRow());
                        if (mixer.getName().equals(mixerTable.getValueAt(mixerTable.getSelectedRow(), 0))) {
                            mixerChecked = mixer;
                            System.out.println(mixerChecked.getName());
                        }
                    }
                }
                if (iSiteChecked == null)
                    JOptionPane.showMessageDialog(null, "Nie wybrano zadnego ISite");
                else {
                    try {
                        iSiteChecked.setMixer(mixerChecked);
                        iSiteChecked.start();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (ISite iSite : listaISite) {
                    try {
                        if (iSite.getName().equals(iSiteTable.getValueAt(iSiteTable.getSelectedRow(), 0))) {
                            iSiteChecked = iSite;
                            System.out.println(iSiteChecked.getName());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }


                if (iSiteChecked == null)
                    JOptionPane.showMessageDialog(null, "Nie wybrano zadnego ISite");
                else {
                    try {
                        iSiteChecked.stop();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }


    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 3000);
            ic = (IControlCenter) reg.lookup("ControlCenter");
            Manager manager = new Manager();
            IManager im = (IManager) UnicastRemoteObject.exportObject(manager, 0);
            System.out.println("Manager is ready");
            ic.subscribe(im);


            manager.setContentPane(manager.panelGlowny);
            manager.setBounds(600, 300, 600, 300);
            manager.setDefaultCloseOperation(EXIT_ON_CLOSE);
            manager.setVisible(true);
        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void notify(ISite iSite, Boolean add) throws RemoteException {

        if (add == true)
            ((ISiteTableModel) iSiteTable.getModel()).add(iSite);
        else if (add == false)
            ((ISiteTableModel) iSiteTable.getModel()).remove(iSite);

        System.out.println("notify(ISite,Boolean) was called");

    }

    @Override
    public void notify(Mixer mixer, Boolean add) throws RemoteException {
        if (add == true) {
            ((MixerTableModel) (mixerTable.getModel())).add(mixer);
        } else if (add == false) {
            ((MixerTableModel) (mixerTable.getModel())).remove(mixer);
        }
        System.out.println("notify(Mixer,Boolean)was called");

    }


}
