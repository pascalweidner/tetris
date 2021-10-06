import java.awt.*;

public class TurquoiseBlock extends Blocks{
    public TurquoiseBlock(int width, int pos, int height) {
        super(width, pos, 'p', height);
        midY = 0;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(4,154,152), midX, 0, blockWidth ));
        super.blocks.add(new Block(new Color(4,154,152), midX+blockWidth, 0, blockWidth ));
        super.blocks.add(new Block(new Color(4,154,152), midX, midY + blockWidth, blockWidth ));
        super.blocks.add(new Block(new Color(4,154,152), midX + blockWidth, midY + blockWidth, blockWidth ));
    }

    @Override
    protected void turn() {

    }

    @Override
    void setMidY(int mid) {
        midY = mid;
    }
}
