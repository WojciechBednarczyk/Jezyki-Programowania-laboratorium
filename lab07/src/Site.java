import communication.IControlCenter;
import communication.IManager;
import communication.ISite;
import support.Mixer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Site extends JFrame implements ISite {

    private static ISite iSite;
    private Mixer mixer;
    private String name;
    private static IControlCenter ic;
    private boolean start = false;
    private JTextField nameTextField;
    private JPanel panelGlowny;
    private JButton dodajButton;
    private int[] lightings = {1, 2, 3, 4, 5, 6, 7, 8};
    private IlluminationFrame illuminationFrame;

    private static Site site;

    public Site() {
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                name = nameTextField.getText();
                try {
                    ic.add(iSite);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
                site.dispose();
                JFrame illumination = new JFrame();
                illuminationFrame = new IlluminationFrame(illumination, iSite);
                illumination.setContentPane(illuminationFrame.getPanelGlowny());
                illumination.setBounds(600, 300, 600, 300);
                illumination.setVisible(true);
                illumination.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }


        });
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 3000);
            ic = (IControlCenter) reg.lookup("ControlCenter");
            site = new Site();
            iSite = (ISite) UnicastRemoteObject.exportObject(site, 0);
            site.setContentPane(site.panelGlowny);
            site.setBounds(600, 300, 600, 300);
            site.setVisible(true);
        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void start() throws RemoteException {
        start = true;
        new Thread(new Runnable() {
            public void run() {
                while (start) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            if (mixer == null) {
                                illuminationFrame.defaultAlgorithm();
                                SwingUtilities.updateComponentTreeUI(illuminationFrame);
                            } else {
                                mixer.mix(lightings);
                                illuminationFrame.changeLights(lightings);
                                SwingUtilities.updateComponentTreeUI(illuminationFrame);
                            }
                        }
                    });

                    try {

                        java.lang.Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

    }

    @Override
    public void stop() throws RemoteException {
        start = false;
    }

    @Override
    public void setMixer(Mixer m) throws RemoteException {
        this.mixer = m;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }


}
