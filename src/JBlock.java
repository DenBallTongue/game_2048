import javax.swing.*;
import java.awt.*;

public class JBlock extends JComponent {
    Block block;
    Color color;
    int blockSize;
    int arc;
    int fontsize;
    Font font;

    JBlock(Block block, Color color, int blockSize, int arc){
        this.block = block;
        this.color = color;
        this.blockSize = blockSize;
        this.arc = arc;
        fontsize = blockSize/4;
        this.font = new Font(Font.SERIF, Font.BOLD,fontsize);

    }
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRoundRect(block.getX(), block.getY(), blockSize, blockSize, arc, arc);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(Integer.toString(block.getValue()), block.getX()+blockSize/2-5, block.getY()+blockSize/2+5);
    }
    public void update(){
        if(this.getBlock().getCondition()==BlockCondition.MOVING){
                this.repaint();
            }
    }

    public Block getBlock() {
        return block;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
