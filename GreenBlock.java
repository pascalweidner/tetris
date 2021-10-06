import java.awt.*;

public class GreenBlock extends Blocks{

    public GreenBlock(int width, int pos, int height) {
        super(width, pos, 'u', height);
        super.midY = blockWidth;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(100,122,30), midX - blockWidth, midY, blockWidth ));
        super.blocks.add(new Block(new Color(100,122,30), midX, 0, blockWidth ));
        super.blocks.add(new Block(new Color(100,122,30), midX, midY, blockWidth ));
        super.blocks.add(new Block(new Color(100,122,30), midX+blockWidth, midY, blockWidth ));
    }

    @Override
    protected void turn() {
        if(direction == 'u') {
            blocks.get(0).setX(blocks.get(0).getX() + blockWidth);
            blocks.get(0).setY(blocks.get(0).getY() + blockWidth);
            direction = 'r';
        }
        else if(direction == 'r') {
            blocks.get(1).setY(blocks.get(1).getY() + blockWidth);
            blocks.get(1).setX(blocks.get(1).getX() - blockWidth);
            direction = 'd';
        }
        else if(direction == 'd') {
            blocks.get(3).setX(blocks.get(3).getX() - blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() - blockWidth);
            direction = 'l';
        }
        else if(direction == 'l') {
            blocks.get(0).setY(blocks.get(0).getY() - blockWidth);
            blocks.get(0).setX(blocks.get(0).getX() - blockWidth);
            blocks.get(1).setX(blocks.get(1).getX() + blockWidth);
            blocks.get(1).setY(blocks.get(1).getY() - blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() + blockWidth);
            blocks.get(3).setX(blocks.get(3).getX() + blockWidth);
            direction = 'u';
        }
    }

    @Override
    void setMidY(int mid) {
        midY = mid;
    }
}
