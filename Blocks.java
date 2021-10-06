import java.awt.*;
import java.util.ArrayList;

public abstract class Blocks {
    //3rd block is the middle block
    protected final ArrayList<Block> blocks;
    protected int midX;
    protected int midY;
    protected int blockWidth;
    //false = left & right true = up & down
    protected char direction;
    protected final int GAME_HEIGHT;

    public Blocks(int width, int pos, char dir, int height) {
        blockWidth = width;
        midX = pos;
        blocks = new ArrayList<>();
        direction = dir;
        GAME_HEIGHT = height;
    }

    abstract protected void generateBlock();

    abstract protected void turn();

    public void move() {
        for (Block block : blocks) {
            block.setY(block.getY() + blockWidth);
        }
    }

    public void moveRight() {
        for(Block block : blocks) {
            block.setX(block.getX() + blockWidth);
        }
    }

    public void moveLeft() {
        for(Block block : blocks) {
            block.setX(block.getX() - blockWidth);
        }
    }

    public void moveDown() {
        for(Block block : blocks) {
            block.setY(block.getY() + blockWidth);
        }
    }

    public void draw(Graphics g) {
        for(int i = 0; i <  blocks.size(); i++ ) {
            blocks.get(i).draw(g);
        }
    }

    public void drawNext(Graphics g, int x, int y) {
        for(int i = 0; i < blocks.size(); i++) {
            blocks.get(i).drawNext(g, x, y);
        }
    }

    public void setMidX(int mid) {
        midX = mid;
    }

    abstract void setMidY(int mid);
}
