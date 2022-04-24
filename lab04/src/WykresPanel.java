import java.awt.*;
import java.util.List;

public class WykresPanel extends javax.swing.JPanel {

    private static final int BORDER_GAP = 30;
    private List<Trening> listaTreningow;


    public WykresPanel(List<Trening> listaTreningow) {
        initComponents();
        this.listaTreningow = listaTreningow;
    }


    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Dimension d = this.getPreferredSize();


        //pasek x i y
        g.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g.drawLine(BORDER_GAP, getHeight() / 2 - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() / 2 - BORDER_GAP);

        int szerokoscMiedzySlupkami = (getWidth() - 2 * BORDER_GAP) / (listaTreningow.size() + 1);

        //Słupki x
        for (int i = 0; i < listaTreningow.size() + 1; i++) {
            int x0 = BORDER_GAP + i * szerokoscMiedzySlupkami;
            int x1 = x0;
            int y0 = getHeight() / 2 - BORDER_GAP + 3;
            int y1 = y0 - 6;
            g.drawLine(x0, y0, x1, y1);
        }
        int poprzedni = getHeight() / 2 - BORDER_GAP;
        g.setColor(Color.RED);
        for (int i = 0; i < listaTreningow.size(); i++) {

            int x0 = BORDER_GAP + i * szerokoscMiedzySlupkami;
            int x1 = (BORDER_GAP + i * szerokoscMiedzySlupkami) + szerokoscMiedzySlupkami;
            int y0 = poprzedni;
            int y1 = getHeight() / 2 - BORDER_GAP + listaTreningow.get(i).getRoznica() / getWidth() * 20;
            poprzedni = getHeight() / 2 - BORDER_GAP + listaTreningow.get(i).getRoznica() / getWidth() * 20;
            g.drawLine(x0, y0, x1, y1);
        }
        g.setColor(Color.BLACK);
    }

    private void initComponents() {

        setLayout(new java.awt.BorderLayout());

        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
    }


}