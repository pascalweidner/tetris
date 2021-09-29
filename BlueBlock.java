import java.awt.*;

public class BlueBlock extends Blocks{
    public BlueBlock(int width, int pos) {
        super(width, pos);
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

    }
}
