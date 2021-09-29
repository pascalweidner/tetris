import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    private final int GAME_HEIGHT = 16;
    private final int GAME_WIDTH = 10;
    private final int FIELD_WIDTH = 35;

    private ArrayList<Blocks> blocks;
    private Blocks currentBlock;
    //This Array represents the highest Block in each row
    private ArrayList<Integer> highestBlocks;

    public GamePanel() {
        setFocusable(true);

        Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH*FIELD_WIDTH, GAME_HEIGHT*FIELD_WIDTH);
        setPreferredSize(SCREEN_SIZE);
        addKeyListener(new Listener());

        blocks = new ArrayList<>();

        randomBlock();

        Thread thread = new Thread(this);
        thread.start();
    }

    private void randomBlock() {
        Random rand = new Random();
        int n = rand.nextInt(7);
        switch (n) {
            case 0:
                currentBlock = new OrangeBlock(FIELD_WIDTH,(GAME_WIDTH / 2) * FIELD_WIDTH);
                break;
            case 1:
                currentBlock = new PurpleBlock(FIELD_WIDTH,(GAME_WIDTH / 2 ) * FIELD_WIDTH);
                break;
            case 2:
                currentBlock = new RedBlock(FIELD_WIDTH,(GAME_WIDTH / 2 ) * FIELD_WIDTH);
                break;
            case 3:
                currentBlock = new YellowBlock(FIELD_WIDTH,(GAME_WIDTH / 2 ) * FIELD_WIDTH);
                break;
            case 4:
                currentBlock = new TurquoiseBlock(FIELD_WIDTH,(GAME_WIDTH / 2 - 1) * FIELD_WIDTH);
                break;
            case 5:
                currentBlock = new BlueBlock(FIELD_WIDTH,(GAME_WIDTH / 2 - 1) * FIELD_WIDTH);
                break;
            case 6:
                currentBlock = new GreenBlock(FIELD_WIDTH, (GAME_WIDTH / 2 ) * FIELD_WIDTH);
                break;
            default:
                break;
        }
    }

    private void draw(Graphics2D g) {
        currentBlock.draw(g);
        g.setColor(new Color(50,54,57));
        g.setStroke(new BasicStroke(2));
        for(int i = 0; i < GAME_HEIGHT; i++) {
            g.drawLine(0, i*FIELD_WIDTH, GAME_WIDTH*FIELD_WIDTH, i*FIELD_WIDTH);
        }
        for(int i = 0; i < GAME_WIDTH; i++) {
            g.drawLine(i*FIELD_WIDTH, 0, i*FIELD_WIDTH, GAME_HEIGHT*FIELD_WIDTH);
        }
    }



    @Override
    public void paint(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        Graphics graphics = image.getGraphics();
        Graphics2D g2 = (Graphics2D) graphics;
        draw(g2);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1.5;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            repaint();
            if(delta >= 1) {
                currentBlock.move();
                repaint();
                delta--;
            }
        }
    }

    private class Listener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyId = e.getKeyCode();

            if(keyId == KeyEvent.VK_LEFT) {
                currentBlock.moveLeft();
            }
            else if(keyId == KeyEvent.VK_RIGHT) {
                currentBlock.moveRight();
            }
        }
    }
}
