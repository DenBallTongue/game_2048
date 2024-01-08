import javax.swing.*;
import java.awt.*;

public class JCell extends JComponent {
    Cell cell;
    Color color;
    int cageSize, arc;

    JCell(Cell cell, Color color, int cageSize, int arc) {
        this.cell = cell;
        this.color = color;
        this.cageSize = cageSize;
        this.arc = arc;
        setVisible(true);
        setLocation(this.cell.getX(), this.cell.getY());
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillRoundRect(cell.getX(), cell.getY(), cageSize, cageSize, arc, arc);
    }
}
