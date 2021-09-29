import java.awt.*;

public class PurpleBlock extends Blocks{
    public PurpleBlock(int width, int pos) {
        super(width, pos);
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

    }
}
