import java.awt.*;
import java.util.ArrayList;

public class OrangeBlock extends Blocks{

    public OrangeBlock(int width, int pos, int height) {
        super(width, pos, 'l', height);
        super.midY = super.blockWidth;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(217, 90, 17), midX- blockWidth, 0, blockWidth ));
        super.blocks.add(new Block(new Color(217, 90, 17), midX, 0 , blockWidth));
        super.blocks.add(new Block(new Color(217, 90, 17), midX, midY, blockWidth));
        super.blocks.add(new Block(new Color(217, 90, 17), midX + blockWidth, midY, blockWidth));
    }

    @Override
    protected void turn() {
        if(direction == 'r' ||direction == 'l') {
            blocks.get(0).setX(blocks.get(0).getX() + 2 * blockWidth);
            blocks.get(1).setY(blocks.get(1).getY() + 2 * blockWidth);
            super.direction = 'u';
        }
        else if(direction == 'u' || direction == 'd'){
            blocks.get(0).setX(blocks.get(0).getX() - 2 * blockWidth);
            blocks.get(1).setY(blocks.get(1).getY() - 2 * blockWidth);
            super.direction = 'r';
        }
    }

    @Override
    void setMidY(int mid) {
        midY = mid;
    }
}
