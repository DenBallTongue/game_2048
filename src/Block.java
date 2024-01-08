
public class Block {
    final int LEFT = 37, UP = 38, RIGHT = 39, DOWN = 40;
    Grid grid;
    int value;
    int x, y;
    int gridPositionX, gridPositionY;
    boolean canMoveOnX, canMoveOnY;
    BlockCondition condition;

    Block(Grid grid, int gridPositionX, int gridPositionY, int value) {
        this.grid = grid;
        this.gridPositionX = gridPositionX;
        this.gridPositionY = gridPositionY;
        this.value = value;
        this.x = grid.getCell(gridPositionX, gridPositionY).getX();
        this.y = grid.getCell(gridPositionX, gridPositionY).getY();
        this.condition = BlockCondition.REST;
        occupyCell(gridPositionX, gridPositionY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public int getGridPositionX() {
        return gridPositionX;
    }

    public int getGridPositionY() {
        return gridPositionY;
    }

    public void setGridPositionX(int gridPositionX) {
        this.gridPositionX = gridPositionX;
    }

    public void setGridPositionY(int gridPositionY) {
        this.gridPositionY = gridPositionY;
    }

    public BlockCondition getCondition() {
        return condition;
    }

    public boolean isCannotMove() {
        return !canMoveOnX && !canMoveOnY;
    }

    public Grid getGrid() {
        return grid;
    }

    void occupyCell(int gridPosX, int gridPosY) {
        grid.occupyCell(gridPosX, gridPosY, this);
    }

    void releaseCage(int gridPosX, int gridPosY) {
        grid.releaseCell(gridPosX, gridPosY, this);
    }

    public void move(int direction, int minSpeed) {
        int xPos = this.gridPositionX, yPos = this.gridPositionY;
        int newXPos = xPos, newYPos = yPos;
        int newX, newY;
        int speed = minSpeed;
        int moveX = 0, moveY = 0;

        switch (direction) {
            case (LEFT), (RIGHT) -> {
                moveX = direction - 38;
                for (int i = xPos + moveX; i >= 0 == i < grid.getWidth(); i += moveX) {
                    if (grid.isCellVacant(i, yPos) || grid.getValueContains(i, yPos) == this.value) {
                        canMoveOnX = true;
                        speed -= 5;
                        newXPos = i;
                        releaseCage(xPos, yPos);
                    } else {
                        canMoveOnX = false;
                        return;
                    }
                }
            }
            case (UP), (DOWN) -> {
                moveY = -(39 - direction);
                for (int i = yPos + moveY; i >= 0 == i < grid.getHeight(); i += moveY) {
                    if (grid.isCellVacant(xPos, i) || grid.getValueContains(xPos, i) == this.value) {
                        canMoveOnY = true;
                        speed -= 5;
                        newYPos = i;
                        releaseCage(xPos, yPos);
                    } else {
                        canMoveOnY = false;
                        return;
                    }
                }
            }
        }

        newX = grid.getCell(newXPos, newYPos).getX();
        newY = grid.getCell(newXPos, newYPos).getY();


        this.condition = BlockCondition.MOVING;
        while (this.x != newX || this.y != newY) {
            this.x += moveX;
            this.y += moveY;
        }
        this.setGridPositionX(newXPos);
        this.setGridPositionY(newYPos);
        this.occupyCell(newXPos, newYPos);
        grid.getCell(newXPos, newYPos).checkCell();
        this.condition = BlockCondition.REST;
    }
}
