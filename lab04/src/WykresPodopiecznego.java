import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WykresPodopiecznego {

    private List<Trening> listaTreningow;
    private int idPodopiecznego;
    private JPanel wykresPanel;

    public WykresPodopiecznego(JFrame wykres, String idPodopiecznego) {
        this.idPodopiecznego = Integer.parseInt(idPodopiecznego);
    }

    public void paintComponent(java.awt.Graphics g) {
        wykresPanel.paintComponents(g);
        java.awt.Dimension d = wykresPanel.getPreferredSize();
        float dx = 2.0f, dy = 2.0f;
        float minX = -2.0f, maxX = 2.0f;
        float minY = -2.0f, maxY = 2.0f;
        float Sx = (maxX - minX) / d.width;
        float Sy = (maxY - minY) / d.height;
        float yr, xr;
        int ye;


        g.drawLine((int) ((-2.0 + dx) / Sx), d.height - (int) ((0 + dy) / Sy),
                (int) ((2.0 + dx) / Sx), d.height - (int) ((0 + dy) / Sy));
        g.drawLine((int) ((0 + dx) / Sx), d.height - (int) ((-2 + dy) / Sy),
                (int) ((0 + dx) / Sx), d.height - (int) ((2 + dy) / Sy));

    }

    public JPanel getWykresPanel() {
        return wykresPanel;
    }
}
