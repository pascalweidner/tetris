import java.awt.*;

public class BlueBlock extends Blocks{
    public BlueBlock(int width, int pos, int height) {
        super(width, pos, 'l', height);
        super.midY = 0;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(15,69,124), midX-blockWidth, midY, blockWidth));
        super.blocks.add(new Block(new Color(15,69,124), midX, midY, blockWidth));
        super.blocks.add(new Block(new Color(15,69,124), midX + blockWidth, midY, blockWidth));
        super.blocks.add(new Block(new Color(15,69,124), midX + blockWidth * 2, midY, blockWidth));
    }

    @Override
    protected void turn() {
        if(direction == 'l') {
            blocks.get(0).setX(blocks.get(0).getX() + blockWidth);
            blocks.get(0).setY(blocks.get(0).getY() - blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() - blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() + blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() - blockWidth * 2);
            blocks.get(3).setY(blocks.get(3).getY() + blockWidth * 2);
            direction = 'd';
        }
        else if(direction == 'd') {
            blocks.get(0).setX(blocks.get(0).getX() + blockWidth);
            blocks.get(0).setY(blocks.get(0).getY() + blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() - blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() - blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() - blockWidth * 2);
            blocks.get(3).setY(blocks.get(3).getY() - blockWidth * 2);
            direction = 'r';
        }
        else if(direction == 'r') {
            blocks.get(0).setX(blocks.get(0).getX() - blockWidth);
            blocks.get(0).setY(blocks.get(0).getY() + blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() + blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() - blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() + blockWidth * 2);
            blocks.get(3).setY(blocks.get(3).getY() - blockWidth * 2);
            direction = 'u';
        }
        else if (direction == 'u') {
            blocks.get(0).setX(blocks.get(0).getX() - blockWidth);
            blocks.get(0).setY(blocks.get(0).getY() - blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() + blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() + blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() + blockWidth * 2);
            blocks.get(3).setY(blocks.get(3).getY() + blockWidth * 2);
            direction = 'l';
        }
    }

    @Override
    void setMidY(int mid) {
        midY = mid;
    }
}
