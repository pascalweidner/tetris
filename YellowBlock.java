import java.awt.*;

public class YellowBlock extends Blocks{
    public YellowBlock(int width, int pos) {
        super(width, pos);
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

    }
}
