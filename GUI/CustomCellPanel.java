import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class CustomCellPanel extends JPanel {
    private boolean alive; 
    private static final int CELL_SIZE = 20; 

    public CustomCellPanel() {
        super();
        this.alive = false; // Initially dead
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  

        if (alive) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(0, 0, CELL_SIZE, CELL_SIZE); 
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, CELL_SIZE, CELL_SIZE);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
        repaint(); 
    }

    // public int getCellSize() {
    //     return CELL_SIZE;
    // } 
    
    public boolean isAlive() {
        return alive;
    }
}
