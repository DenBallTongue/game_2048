import java.util.Iterator;
import java.util.Stack;

public class Cell {
    int x, y;
    boolean isVacant;
    int valueContains;
    Stack<Block> blockContains;
    Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.valueContains = 0;
        isVacant = true;
        blockContains = new Stack<Block>();
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setVacant(boolean vacant) {
        isVacant = vacant;
    }
    public boolean isVacant() {
        return isVacant;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setValueContains(int valueContains) {
        this.valueContains = valueContains;
    }

    public int getValueContains() {
        return valueContains;
    }

    public Stack<Block> getBlockContains() {
        return blockContains;
    }
    public void addBlockToCell(Block block){
        blockContains.add(block);
        this.setVacant(false);
        this.setValueContains(block.getValue());
    }

    public void releaseCell(Block block){
        blockContains.remove(block);
        if(this.blockContains.size()==0) {
            this.setVacant(true);
            this.setValueContains(0);
        }
    }
    public void checkCell(){
        if(getBlockContains().size()==2){
            Block block = this.blockContains.get(0);
            this.clearCell();
            Block newBlock = new Block(block.getGrid(), block.getGridPositionX(), block.getGridPositionY(), this.getValueContains()*2);
            this.addBlockToCell(newBlock);
        }
    }
    public void clearCell(){
        for (Block blockContain : blockContains) {
            releaseCell(blockContain);
        }
    }
}
