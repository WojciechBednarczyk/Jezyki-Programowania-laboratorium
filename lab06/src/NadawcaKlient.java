import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NadawcaKlient {

    public void send(String message) {
        Socket s;
        try {
            s = new Socket("localhost", 1024);
            OutputStream out = s.getOutputStream();
            PrintWriter pw = new PrintWriter(out, false);
            pw.println(message);
            pw.flush();
            pw.close();
            s.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
