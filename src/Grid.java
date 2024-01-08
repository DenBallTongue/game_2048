import java.util.Comparator;
import java.util.Stack;

public class Grid {
    Comparator<Block> comparatorOnX = new Comparator<Block>() {
        @Override
        public int compare(Block o1, Block o2) {
            return Integer.compare(o1.getX(), o2.getX());
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public Comparator<Block> reversed() {
            return Comparator.super.reversed();
        }
    };

    Comparator<Block> comparatorOnY = new Comparator<Block>() {
        @Override
        public int compare(Block o1, Block o2) {
            return Integer.compare(o1.getY(), o2.getY());
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public Comparator<Block> reversed() {
            return Comparator.super.reversed();
        }
    };
    int width, height;
    Cell[][] cells;
    Stack<Block> blocksContains;

    Grid(int width, int height){
        this.width=width;
        this.height=height;
        this.cells = new Cell[width][height];
        this.blocksContains = new Stack<Block>();
    }
    public void setCell(int x, int y, Cell cell){
        this.cells[x][y] = cell;
    }
    public void occupyCell(int x, int y, Block block){
        this.cells[x][y].addBlockToCell(block);
        this.blocksContains.add(block);
    }
    public void releaseCell(int x, int y, Block block){
        this.cells[x][y].releaseCell(block);
        this.blocksContains.remove(block);
    }
    public Stack<Block> getBlocksContains() {
        return blocksContains;
    }
    public Cell getCell(int x, int y){
        return this.cells[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public boolean isCellVacant(int x, int y){
        return cells[x][y].isVacant();
    }

    public int getValueContains(int x, int y){
        return cells[x][y].getValueContains();
    }

    public void sortOnX(){
        this.blocksContains.sort(comparatorOnX);
    }
    public void reversedSortOnX(){
        this.blocksContains.sort(comparatorOnX.reversed());
    }
    public void sortOnY(){
        this.blocksContains.sort(comparatorOnY);
    }
    public void reversedSortOnY(){
        this.blocksContains.sort(comparatorOnY.reversed());
    }
}
