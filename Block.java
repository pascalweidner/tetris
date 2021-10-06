import java.awt.*;

public class Block {
    private Color color;
    private final int width;
    private int x;
    private int y;

    public Block(Color color, int xPosition, int yPosition, int width) {
        this.color = color;
        this.x = xPosition;
        this.y = yPosition;
        this.width = width;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, width);
    }

    public void drawNext(Graphics g, int xNext, int yNext) {
        g.setColor(color);
        g.fillRect(x + xNext, y + yNext, width, width);
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
