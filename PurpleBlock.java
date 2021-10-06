import java.awt.*;

public class PurpleBlock extends Blocks{
    public PurpleBlock(int width, int pos, int height) {
        super(width, pos, 'r', height);
        super.midY = super.blockWidth;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(101,0,147), midX- blockWidth, midY, blockWidth ));
        super.blocks.add(new Block(new Color(101,0,147), midX, midY, blockWidth ));
        super.blocks.add(new Block(new Color(101,0,147), midX, 0, blockWidth ));
        super.blocks.add(new Block(new Color(101,0,147), midX+ blockWidth, 0, blockWidth ));
    }

    @Override
    protected void turn() {
        if(direction == 'r' ||direction == 'l') {
            blocks.get(0).setX(blocks.get(0).getX() + 2 * blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() + 2 * blockWidth);
            super.direction = 'u';
        }
        else if(direction == 'u' || direction == 'd'){
            blocks.get(0).setX(blocks.get(0).getX() - 2 * blockWidth);
            blocks.get(3).setY(blocks.get(3).getY() - 2 * blockWidth);
            super.direction = 'r';
        }
    }

    @Override
    void setMidY(int mid) {
        midY = mid;
    }
}
