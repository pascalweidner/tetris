import java.awt.*;

public class RedBlock extends Blocks{
    public RedBlock(int width, int pos, int height) {
        super(width, pos, 'r', height);
        super.midY = 0;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(172,1,0), midX-blockWidth, midY, blockWidth));
        super.blocks.add(new Block(new Color(172,1,0), midX, midY, blockWidth));
        super.blocks.add(new Block(new Color(172,1,0), midX+blockWidth, midY, blockWidth));
        super.blocks.add(new Block(new Color(172,1,0), midX + blockWidth, midY+blockWidth, blockWidth));
    }

    @Override
    protected void turn() {
        if(direction == 'r') {
            blocks.get(0).setY(blocks.get(0).getY() + blockWidth);
            blocks.get(0).setX(blocks.get(0).getX() + blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() - blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() - blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() - 2 * blockWidth);
            super.direction = 'u';
        }
        else if(direction == 'u') {
            blocks.get(0).setY(blocks.get(0).getY() - blockWidth);
            blocks.get(0).setX(blocks.get(0).getX() + blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() - blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() + blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() - 2 * blockWidth);
            super.direction = 'l';
        }
        else if(direction == 'l') {
            blocks.get(0).setY(blocks.get(0).getY() - blockWidth);
            blocks.get(0).setX(blocks.get(0).getX() - blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() + blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() + blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() + 2 * blockWidth);
            super.direction = 'd';
        }
        else if(direction == 'd') {
            blocks.get(0).setY(blocks.get(0).getY() + blockWidth);
            blocks.get(0).setX(blocks.get(0).getX() - blockWidth);
            blocks.get(2).setX(blocks.get(2).getX() + blockWidth);
            blocks.get(2).setY(blocks.get(2).getY() - blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() + 2 * blockWidth);
            super.direction = 'r';
        }
    }

    @Override
    void setMidY(int mid) {
        midY = mid;
    }
}
