import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Game {

    final int BLOCK_SIZE = 100, ARC = 25, BORDER = 10;
    final static int WIDTH = 4;
    final static int HEIGHT = 4;
    final int LEFT = 37, UP = 38, RIGHT = 39, DOWN = 40;

    Grid grid;
    int windowWidth;
    int windowHeight;
    Canvas canvas;
    JFrame window;

    int minSpeed;

    int score;
    boolean isGameOver;

    public static void main(String[] args){}
    void init(){
        score = 0;
        isGameOver = false;
        windowWidth = WIDTH*BLOCK_SIZE + (WIDTH+2)*BORDER+400;
        windowHeight = HEIGHT*BLOCK_SIZE + (HEIGHT+2)*BORDER+50;
        minSpeed = 5*WIDTH;

        grid = new Grid(WIDTH, HEIGHT);
            for (int i = 0; i < WIDTH; i++){
                int x = BORDER * (i+1) + BLOCK_SIZE * i;
                for (int j = 0; j < HEIGHT; j++){
                    int y = BORDER * (j+1) + BLOCK_SIZE * j;
                    grid.setCell(i, j, new Cell(x, y));
                }
            }

        canvas = new Canvas();
            canvas.setBackground(Color.white);

        window = new JFrame("2048");
            window.setBounds(300,150, windowWidth, windowHeight);
            window.setResizable(false);
            window.setContentPane(canvas);
            window.setVisible(true);
            window.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                    switch (e.getKeyCode()) {
                        case (LEFT) -> move(LEFT);
                        case (UP) -> move(UP);
                        case (RIGHT) -> move(RIGHT);
                        case (DOWN) -> move(DOWN);
                        default -> {
                            return;
                        }
                    }
                    createNewBlock();
                    canvas.repaint();
                }
            });
    }

    void run(){
        init();
        createNewBlock();
    }

    class Canvas extends JComponent {
        public void paint(Graphics g){
            for(int i = 0; i < Game.WIDTH; i++){
                for(int j = 0; j < Game.HEIGHT; j++){
                    JCell c = new JCell(grid.getCell(i,j), Color.lightGray, BLOCK_SIZE, ARC);
                    c.paint(g);
                }
            }
            for(Block block : grid.getBlocksContains()){
                JBlock b = new JBlock(block, Color.green, BLOCK_SIZE, ARC);
                b.paint(g);
                b.update();
            }
        }
    }

    void move(int direction){
        switch(direction){
            case(LEFT) -> grid.reversedSortOnX();
            case(RIGHT) -> grid.sortOnX();
            case(DOWN) -> grid.reversedSortOnY();
            case(UP) -> grid.sortOnY();
        }
        for(int i = 0; i < grid.getBlocksContains().size(); i++){
            grid.getBlocksContains().get(i);
        }
    }

    void createNewBlock(){
        int r, r1;
        do {
            r = (int) (Math.random() * (WIDTH));
            r1 = (int) (Math.random() * (HEIGHT));
        } while (!grid.isCellVacant(r,r1));
        createBlock(5, r, r1);
    }
    void createBlock(int value, int xGridPos, int yGridPos){
        grid.occupyCell(xGridPos, yGridPos, new Block(grid, xGridPos, yGridPos, value));
    }
}