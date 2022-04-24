import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class OdbiorcaCentrala {


    private final int port = 1024;
    private Thread t = null;
    private ServerSocket s = null;
    private boolean end = false;
    private JTextField zgloszenieJText;


    public OdbiorcaCentrala(JTextField zgloszenieJText) {
        this.zgloszenieJText = zgloszenieJText;
    }

    public void stop() {
        t.interrupt();
        try {
            s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String start() {
        end = false;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s = new ServerSocket(port);
                    while (true) {
                        Socket sc = s.accept();
                        InputStream is = sc.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        String theLine = br.readLine();
                        zgloszenieJText.setText(theLine);
                        sc.close();
                    }
                } catch (SocketException e) {
                    // TODO - podczas przerywania w�tku metoda accept zg�osi wyj�tek
                    // z wiadomo�ci�: socket closed
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        t.start();
        return "";
    }

}
