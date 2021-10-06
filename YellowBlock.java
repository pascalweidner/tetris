import java.awt.*;

public class YellowBlock extends Blocks{
    public YellowBlock(int width, int pos, int height) {
        super(width, pos, 'l', height);
        super.midY = 0;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(161,160,1), midX-blockWidth, midY + blockWidth, blockWidth));
        super.blocks.add(new Block(new Color(161,160,1), midX-blockWidth, 0, blockWidth));
        super.blocks.add(new Block(new Color(161,160,1), midX, 0, blockWidth));
        super.blocks.add(new Block(new Color(161,160,1), midX+blockWidth, 0, blockWidth));
    }

    @Override
    protected void turn() {
        if(direction == 'l') {
            blocks.get(0).setY(blocks.get(0).getY() - 2 * blockWidth);
            blocks.get(1).setY(blocks.get(1).getY() - blockWidth);
            blocks.get(1).setX(blocks.get(1).getX() + blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() - blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() + blockWidth);
            super.direction = 'u';
        }
        else if(direction == 'u') {
            blocks.get(0).setX(blocks.get(0).getX() + 2 * blockWidth);
            blocks.get(1).setY(blocks.get(1).getY() + blockWidth);
            blocks.get(1).setX(blocks.get(1).getX() + blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() - blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() - blockWidth);
            super.direction = 'r';
        }
        else if(direction == 'r') {
            blocks.get(0).setY(blocks.get(0).getY() + 2 * blockWidth);
            blocks.get(1).setY(blocks.get(1).getY() + blockWidth);
            blocks.get(1).setX(blocks.get(1).getX() - blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() + blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() - blockWidth);
            super.direction = 'd';
        }
        else if(direction == 'd') {
            blocks.get(0).setX(blocks.get(0).getX() - 2 * blockWidth);
            blocks.get(1).setY(blocks.get(1).getY() - blockWidth);
            blocks.get(1).setX(blocks.get(1).getX() - blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() + blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() + blockWidth);
            super.direction = 'l';
        }
    }

    @Override
    void setMidY(int mid) {
        midY = mid;
    }
}
