import Final.Finals;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SplashScreen extends JPanel {
    private Image backgroundImage;

    public SplashScreen() throws IOException {
        this.setBounds(0, 0, Finals.WIDTH, Finals.HEIGHT);
        this.setLayout(null);

        // טוען את התמונה
        backgroundImage = new ImageIcon(getClass().getResource("תמונה למשחק.png")).getImage();

        // כפתור PLAY
        JButton playButton = new JButton("PLAY");
        playButton.setBounds(150, 200, 100, 50);
        playButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.getContentPane().removeAll();
            try {
                GameScene gameScene = new GameScene();
                topFrame.add(gameScene);
                topFrame.revalidate();
                topFrame.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // כפתור SOUND
        JButton soundButton = new JButton("SOUND");
        soundButton.setBounds(150, 270, 100, 50);
        soundButton.addActionListener(e -> {
            Main.toggleSound();
        });

        this.add(playButton);
        this.add(soundButton);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
