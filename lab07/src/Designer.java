import communication.IControlCenter;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Designer {

    private static IControlCenter ic;
    private JPanel panelGlowny;
    private JButton addShiftRightButton;
    private JButton addReflectMixerButton;
    private JButton addShiftLeftMixerButton;
    private JButton removeShiftLeftMixerButton;
    private JButton removeShiftRightMixerButton;
    private JButton removeReflectMixerButton;

    public Designer(JFrame designer) {

        ReflectMixer reflectMixer = new ReflectMixer("reflectMixer");
        ShiftLeftMixer shiftLeftMixer = new ShiftLeftMixer("shiftLeftMixer");
        ShiftRightMixer shiftRightMixer = new ShiftRightMixer("shiftRightMixer");

        addReflectMixerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if (ic.add(reflectMixer) == false)
                        JOptionPane.showMessageDialog(null, "Juz dodano ten mixer");
                    else {
                        ic.add(reflectMixer);
                        JOptionPane.showMessageDialog(null, "Dodano mixer");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        addShiftLeftMixerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if (ic.add(shiftLeftMixer) == false)
                        JOptionPane.showMessageDialog(null, "Juz dodano ten mixer");
                    else {
                        ic.add(shiftLeftMixer);
                        JOptionPane.showMessageDialog(null, "Dodano mixer");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        addShiftRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    if (ic.add(shiftRightMixer) == false)
                        JOptionPane.showMessageDialog(null, "Juz dodano ten mixer");
                    else {
                        ic.add(shiftRightMixer);
                        JOptionPane.showMessageDialog(null, "Dodano mixer");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        removeReflectMixerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (ic.remove(reflectMixer) == false)
                        JOptionPane.showMessageDialog(null, "Nie ma tego mixera");
                    else {
                        ic.remove(reflectMixer);
                        JOptionPane.showMessageDialog(null, "Usunieto mixer");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        removeShiftLeftMixerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (ic.remove(shiftLeftMixer) == false)
                        JOptionPane.showMessageDialog(null, "Nie ma tego mixera");
                    else {
                        ic.remove(shiftLeftMixer);
                        JOptionPane.showMessageDialog(null, "Usunieto mixer");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        removeShiftRightMixerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (ic.remove(shiftRightMixer) == false)
                        JOptionPane.showMessageDialog(null, "Nie ma tego mixera");
                    else {
                        ic.remove(shiftRightMixer);
                        JOptionPane.showMessageDialog(null, "Usunieto mixer");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 3000);
            ic = (IControlCenter) reg.lookup("ControlCenter");
        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JFrame designer = new JFrame("Designer");
        designer.setContentPane(new Designer(designer).panelGlowny);
        designer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        designer.setBounds(600, 300, 600, 300);
        designer.setVisible(true);
    }


}
