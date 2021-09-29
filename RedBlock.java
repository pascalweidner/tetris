import java.awt.*;

public class RedBlock extends Blocks{
    public RedBlock(int width, int pos) {
        super(width, pos);
        super.midY = 0;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(172,1,0), midX-blockWidth, 0, blockWidth));
        super.blocks.add(new Block(new Color(172,1,0), midX, 0, blockWidth));
        super.blocks.add(new Block(new Color(172,1,0), midX+blockWidth, 0, blockWidth));
        super.blocks.add(new Block(new Color(172,1,0), midX+blockWidth, midY+blockWidth, blockWidth));
    }

    @Override
    protected void turn() {

    }
}
