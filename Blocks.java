import java.awt.*;
import java.util.ArrayList;

public abstract class Blocks {
    //3rd block is the middle block
    protected final ArrayList<Block> blocks;
    protected int midX;
    protected int midY;
    protected int blockWidth;
    //false = left & right true = up & down
    protected boolean direction = false;

    public Blocks(int width, int pos) {
        blockWidth = width;
        midX = pos;
        blocks = new ArrayList<>();
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

    public void draw(Graphics g) {
        for(int i = 0; i <  blocks.size(); i++ ) {
            blocks.get(i).draw(g);
        }
    }
}
