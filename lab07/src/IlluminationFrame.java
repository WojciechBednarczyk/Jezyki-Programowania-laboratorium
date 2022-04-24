import communication.IControlCenter;
import communication.ISite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class IlluminationFrame extends Component {
    private JPanel panelGlowny;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JButton zakonczButton;
    private JLabel[] listaLabel = {label1, label2, label3, label4, label5, label6, label7, label8};

    private static IControlCenter iControlCenter;
    private JFrame illumination;
    private JFrame site;
    private ISite iSite;
    static int change = 0;

    public IlluminationFrame(JFrame illumination, ISite iSite) {
        this.iSite = iSite;
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 3000);
            iControlCenter = (IControlCenter) reg.lookup("ControlCenter");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

        this.illumination = illumination;
        zakonczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    iControlCenter.remove(iSite);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                illumination.dispose();


            }
        });
    }

    public void defaultAlgorithm() {
        if (change == 0) {
            for (JLabel jLabel : listaLabel) {
                jLabel.setForeground(Color.RED);
            }
            change = 1;
        } else if (change == 1) {
            for (JLabel jLabel : listaLabel) {
                jLabel.setForeground(Color.BLACK);
            }
            change = 0;
        }
    }

    public void changeLights(int[] lights) {
        int i = 0;
        for (int light : lights) {
            switch (light) {
                case 1:
                    listaLabel[i].setForeground(Color.RED);
                    i++;
                    break;
                case 2:
                    listaLabel[i].setForeground(Color.GREEN);
                    i++;
                    break;
                case 3:
                    listaLabel[i].setForeground(Color.BLUE);
                    i++;
                    break;
                case 4:
                    listaLabel[i].setForeground(Color.PINK);
                    i++;
                    break;
                case 5:
                    listaLabel[i].setForeground(Color.ORANGE);
                    i++;
                    break;
                case 6:
                    listaLabel[i].setForeground(Color.YELLOW);
                    i++;
                    break;
                case 7:
                    listaLabel[i].setForeground(Color.CYAN);
                    i++;
                    break;
                case 8:
                    listaLabel[i].setForeground(Color.BLACK);
                    i++;
                    break;
                default:
                    break;

            }
        }
    }

    public JPanel getPanelGlowny() {
        return panelGlowny;
    }
}
