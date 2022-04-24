import communication.IControlCenter;
import communication.IManager;
import communication.ISite;
import support.Mixer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ControlCenter extends UnicastRemoteObject implements IControlCenter {

    private List<ISite> listaISite = new ArrayList<>();
    private List<IManager> listaIManager = new ArrayList<>();
    private List<Mixer> listaMixer = new ArrayList<>();
    private static IManager im = null;

    public ControlCenter() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(3000);
            ControlCenter cc = new ControlCenter();
            reg.rebind("ControlCenter", cc);
            System.out.println("ControlCenter is ready");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Testowanie mixerow
//        int[] tablica = {0,1,2,3,4,5,6,7};
//        System.out.println(Arrays.toString(tablica));
//        ShiftRightMixer mixer = new ShiftRightMixer("xyz");
//        mixer.mix(tablica);
//        System.out.println(Arrays.toString(tablica));
//        mixer.mix(tablica);
//        System.out.println(Arrays.toString(tablica));
//        mixer.mix(tablica);
//        System.out.println(Arrays.toString(tablica));
//        mixer.mix(tablica);
//        System.out.println(Arrays.toString(tablica));
    }


    @Override
    public Boolean add(ISite is) throws RemoteException {
        if (isISite(is) == true)
            return false;
        else {
            listaISite.add(is);
            if (im != null) {
                im.notify(is, true);
            }
            return true;
        }
    }

    @Override
    public Boolean remove(ISite iSite) throws RemoteException {
        if (isISite(iSite) == false)
            return false;
        else {
            for (Iterator<ISite> it = listaISite.iterator(); it.hasNext(); ) {
                ISite next = it.next();
                if (next.getName().equals(iSite.getName()))
                    it.remove();
            }
            if (im != null) {
                im.notify(iSite, false);
            }
            return true;
        }
    }

    @Override
    public Boolean add(Mixer m) throws RemoteException {

        if (isMixer(m) == true)
            return false;
        else {
            listaMixer.add(m);
            if (im != null) {
                im.notify(m, true);
            }
            return true;
        }

    }

    @Override
    public Boolean remove(Mixer m) throws RemoteException {
        if (isMixer(m) == false)
            return false;
        else {
            for (Iterator<Mixer> it = listaMixer.iterator(); it.hasNext(); ) {
                Mixer next = it.next();
                if (next.getName().equals(m.getName()))
                    it.remove();
            }
            if (im != null) {
                im.notify(m, false);
            }
            return true;
        }
    }

    @Override
    public void subscribe(IManager im) throws RemoteException {
        this.im = im;
        listaIManager.add(im);
    }

    @Override
    public List<ISite> getAllSites() throws RemoteException {
        return listaISite;
    }

    @Override
    public List<Mixer> getAllMixers() throws RemoteException {
        return listaMixer;
    }

    public boolean isISite(ISite iSite) {
        for (ISite iSiteChecked : listaISite) {
            if (iSiteChecked.toString().equals(iSite.toString()))
                return true;
        }
        return false;
    }

    public boolean isMixer(Mixer mixer) {
        for (Mixer mixerChecked : listaMixer) {
            if (mixerChecked.getName().equals(mixer.getName()))
                return true;
        }
        return false;
    }


}
