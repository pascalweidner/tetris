import java.awt.*;

public class GreenBlock extends Blocks{
    public GreenBlock(int width, int pos) {
        super(width, pos);
        super.midY = 0;
        generateBlock();
    }

    @Override
    protected void generateBlock() {
        super.blocks.add(new Block(new Color(100,122,30), midX- blockWidth, 0, blockWidth ));
        super.blocks.add(new Block(new Color(100,122,30), midX, 0, blockWidth ));
        super.blocks.add(new Block(new Color(100,122,30), midX + blockWidth, 0, blockWidth ));
        super.blocks.add(new Block(new Color(100,122,30), midX, midY + blockWidth, blockWidth ));
    }

    @Override
    protected void turn() {

    }
}
