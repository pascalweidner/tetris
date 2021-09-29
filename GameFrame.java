import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel panel;

    public GameFrame() {
        panel = new GamePanel();
        add(panel);
        setResizable(false);
        setTitle("Tetris");
        setBackground(new Color(24,26,27));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
