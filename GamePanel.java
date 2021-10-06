import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.stream.Collectors;

public class GamePanel extends JPanel implements Runnable {
    private final int GAME_HEIGHT = 16;
    private final int GAME_WIDTH = 10;
    private final int FIELD_WIDTH = 35;
    private final int MENU_SIZE = 210;
    private final int SHOW_HEIGHT = 4;
    private final int SHOW_WIDTH = 4;

    private Blocks currentBlock;
    private Blocks nextBlock;
    //This Array represents the highest Block in each row
    private ArrayList<Integer> highestBlocks;
    private ArrayList<Block> block;

    private boolean dead = false;
    private final Listener listener;
    private final ListenerMouse mouseListener;
    Thread thread;

    private int score = 0;

    public GamePanel() {
        setFocusable(true);

        Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH*FIELD_WIDTH + MENU_SIZE, GAME_HEIGHT*FIELD_WIDTH);
        setPreferredSize(SCREEN_SIZE);

        listener = new Listener();
        addKeyListener(listener);

        mouseListener = new ListenerMouse();

        highestBlocks = new ArrayList<>();
        for(int i = 0; i < GAME_WIDTH; i++) {
            highestBlocks.add(GAME_HEIGHT);
        }

        block = new ArrayList<>();

        thread = new Thread(this);
        thread.start();

        start();
    }

    public void start() {
        dead = false;
        System.out.println("start");
        removeMouseListener(mouseListener);
        for(int i = 0; i < highestBlocks.size(); i++) {
            highestBlocks.set(i, GAME_HEIGHT);
        }

        block.clear();
        System.out.println(dead);

        randomBlock();
        currentBlock = nextBlock;
        randomBlock();
    }

    private void checkRows() {
        int min = highestBlocks.indexOf(Collections.min(highestBlocks));
        ArrayList<Block> subList;
        ArrayList<Block> list = new ArrayList<>();
        for(int i = min; i < GAME_HEIGHT; i++) {
             int j = i;
             subList = (ArrayList<Block>) block.stream().filter(b -> b.getY() == (j*FIELD_WIDTH)).collect(Collectors.toList());
             if(subList.size() == GAME_WIDTH) {
                 for(Block block1 : subList) {
                     block.remove(block1);
                 }

                 for(Block block1 : list) {
                     int index = block.indexOf(block1);
                     block.get(index).setY(block.get(index).getY() + FIELD_WIDTH);
                 }

                 for(int k = 0; k < highestBlocks.size(); k++) {
                     int found = 1;
                     for(int h = highestBlocks.get(k); h < GAME_HEIGHT; h++) {
                         int counter = 0;
                         for(Block block2: block) {
                             if(block2.getY() / FIELD_WIDTH == h+1 && block2.getX() / FIELD_WIDTH == k) {
                                 counter++;
                                 break;
                             }
                         }
                         if(counter == 0) {
                             found++;
                         }
                         else {
                             break;
                         }
                     }
                     highestBlocks.set(k, highestBlocks.get(k) + found);
                     System.out.println(highestBlocks.get(k));
                 }

                 score += GAME_WIDTH * 2;
             }
             else {
                 list.addAll(subList);
             }
        }
    }

    private void checkDead() {
        for(int i : highestBlocks) {
            if(i <= 0) {
                addMouseListener(mouseListener);
                dead = true;
                break;
            }
        }
    }

    private void newBlock() {
        block.addAll(currentBlock.blocks);
        for(Block block : currentBlock.blocks) {
            if(block.getY() / FIELD_WIDTH <= highestBlocks.get(block.getX() / FIELD_WIDTH)) {
                highestBlocks.set(block.getX() / FIELD_WIDTH, block.getY() / FIELD_WIDTH);
            }
        }
        checkRows();
        checkDead();
        if(!dead) {
            currentBlock = nextBlock;
            randomBlock();
        }
    }

    private void randomBlock() {
        Random rand = new Random();
        int n = rand.nextInt(7);
        switch (n) {
            case 0:
                nextBlock = new OrangeBlock(FIELD_WIDTH,(GAME_WIDTH / 2 ) * FIELD_WIDTH, GAME_HEIGHT);
                break;
            case 1:
                nextBlock = new PurpleBlock(FIELD_WIDTH,(GAME_WIDTH / 2 ) * FIELD_WIDTH, GAME_HEIGHT);
                break;
            case 2:
                nextBlock = new RedBlock(FIELD_WIDTH,(GAME_WIDTH / 2 ) * FIELD_WIDTH, GAME_HEIGHT);
                break;
            case 3:
                nextBlock = new YellowBlock(FIELD_WIDTH,(GAME_WIDTH / 2 ) * FIELD_WIDTH, GAME_HEIGHT);
                break;
            case 4:
                nextBlock = new TurquoiseBlock(FIELD_WIDTH,(GAME_WIDTH / 2 - 1) * FIELD_WIDTH, GAME_HEIGHT);
                break;
            case 5:
                nextBlock = new BlueBlock(FIELD_WIDTH,(GAME_WIDTH / 2 - 1) * FIELD_WIDTH, GAME_HEIGHT);
                break;
            case 6:
                nextBlock = new GreenBlock(FIELD_WIDTH, (GAME_WIDTH / 2 ) * FIELD_WIDTH, GAME_HEIGHT);
                break;
            default:
                break;
        }
    }

    private void moveDown() {
        int counter = 0;
        for(Block block : currentBlock.blocks) {
            if(block.getY() >= (GAME_HEIGHT-1) * FIELD_WIDTH) {
                counter++;
            }
            else if(block.getY() / FIELD_WIDTH >= highestBlocks.get(block.getX() / FIELD_WIDTH)-1) {
                counter++;
            }
        }
        if(counter == 0) {
            currentBlock.move();
        }
        else {
            if(!dead) {
                newBlock();
            }
        }

    }

    private void moveRight() {
        int counter = 0;
        for(Block block1: currentBlock.blocks) {
            if(block1.getX() >= (GAME_WIDTH -1 ) * FIELD_WIDTH) {
                counter++;
            }
            for(Block block2 : block) {
                if(block2.getX() - FIELD_WIDTH == block1.getX() && block2.getY() == block1.getY()) {
                    counter++;
                }
            }
        }
        if(counter == 0) {
            currentBlock.moveRight();
        }
    }

    private void moveLeft() {
        int counter = 0;
        for(Block block1: currentBlock.blocks) {
            if(block1.getX() <= 1) {
                counter++;
            }
            for(Block block2 : block) {
                if(block2.getX() + FIELD_WIDTH == block1.getX() && block2.getY() == block1.getY()) {
                    counter++;
                }
            }
        }
        if(counter == 0) {
            currentBlock.moveLeft();
        }
    }

    private void draw(Graphics2D g) {
        for(Block block: block) {
            block.draw(g);
        }
        currentBlock.draw(g);
        g.setColor(new Color(50,54,57));
        g.setStroke(new BasicStroke(2));
        for(int i = 0; i < GAME_HEIGHT; i++) {
            g.drawLine(0, i*FIELD_WIDTH, GAME_WIDTH*FIELD_WIDTH, i*FIELD_WIDTH);
        }
        for(int i = 0; i < GAME_WIDTH + 1; i++) {
            g.drawLine(i*FIELD_WIDTH, 0, i*FIELD_WIDTH, GAME_HEIGHT*FIELD_WIDTH);
        }

        nextBlock.drawNext(g, (GAME_WIDTH / 2) * FIELD_WIDTH + MENU_SIZE / 2, FIELD_WIDTH * 2);

        g.setColor(new Color(50,54,57));
        g.setStroke(new BasicStroke(2));
        for(int i = 0; i < SHOW_HEIGHT + 2 ; i++) {
            g.drawLine(GAME_WIDTH * FIELD_WIDTH + (MENU_SIZE / 2) - 2 * FIELD_WIDTH, FIELD_WIDTH*i, GAME_WIDTH * FIELD_WIDTH + (MENU_SIZE / 2) + 2 * FIELD_WIDTH, FIELD_WIDTH*i);
        }

        for(int i = 0; i < SHOW_WIDTH + 1 ; i++) {
            g.drawLine(GAME_WIDTH * FIELD_WIDTH + (MENU_SIZE / 2) - 2 * FIELD_WIDTH + FIELD_WIDTH*i, FIELD_WIDTH, GAME_WIDTH * FIELD_WIDTH + (MENU_SIZE / 2) - 2 * FIELD_WIDTH + FIELD_WIDTH * i, FIELD_WIDTH+SHOW_HEIGHT*FIELD_WIDTH);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Sagoe UI", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (GAME_WIDTH * FIELD_WIDTH + MENU_SIZE - metrics.stringWidth("Score: " + score)), g.getFont().getSize());

        if(dead) {
            g.setColor(Color.GREEN);
            g.fillRect((GAME_WIDTH * FIELD_WIDTH + MENU_SIZE) / 2 - 3 * FIELD_WIDTH, GAME_HEIGHT * FIELD_WIDTH / 2 + 3 * FIELD_WIDTH, 6 * FIELD_WIDTH, 2* FIELD_WIDTH);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Sagoe UI", Font.BOLD, 50));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("Play", (GAME_WIDTH * FIELD_WIDTH + MENU_SIZE) / 2 - metrics.stringWidth("Play") - 15, GAME_HEIGHT * FIELD_WIDTH / 2 + 4 * FIELD_WIDTH  + g.getFont().getSize() / 2 - 10);

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
            if(dead) {
                delta = 0;
                repaint();
                continue;
            }
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            repaint();
            if(delta >= 1) {
                moveDown();
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
                moveLeft();
            }
            else if(keyId == KeyEvent.VK_RIGHT) {
                moveRight();
            }
            else if(keyId == KeyEvent.VK_DOWN) {
                if(!dead) {
                    moveDown();
                }
            }
            else if(keyId == KeyEvent.VK_UP) {
                currentBlock.turn();
            }
        }
    }

    private class ListenerMouse extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int i = (GAME_WIDTH * FIELD_WIDTH + MENU_SIZE) / 2 - 3 * FIELD_WIDTH;
            if(e.getX() >= i && e.getX() <= i + 6 * FIELD_WIDTH && e.getY() >= GAME_HEIGHT * FIELD_WIDTH / 2 + 3 * FIELD_WIDTH && e.getY() <= (GAME_HEIGHT * FIELD_WIDTH / 2 + 3 * FIELD_WIDTH) + 2* FIELD_WIDTH) {
                start();
                dead = false;
            }
        }
    }
}
