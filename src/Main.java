import Final.Finals;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Main extends JFrame {
    private static Audio audioPlayer;
    private static boolean isSoundOn = true;
    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {

        this.setSize(Finals.WIDTH, Finals.HEIGHT);
        this.setLayout(new BorderLayout());
        setVisible(true);
        setTitle("Welcome to the Game --->  X||O ");
        Image icon = new ImageIcon(getClass().getResource("/tic-tac-toe.png")).getImage();
        setIconImage(icon);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            audioPlayer = new Audio();
        } catch (LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        SplashScreen splashScreen = new SplashScreen();
        this.add(splashScreen, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void toggleSound() {
        if (audioPlayer != null) {
            if (isSoundOn) {
                audioPlayer.stopAudio();
                isSoundOn = false;
            } else {
                audioPlayer.startAudio();
                isSoundOn = true;
            }
        }
    }

    public static void check(){
        /*
        JLabel textFiled = new JLabel();
        textFiled.setHorizontalAlignment(JLabel.CENTER);
        textFiled.setText("Tic-Tac-Toe");
        textFiled.setOpaque(true);
        this.add(textFiled);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBounds(0,0,600,100);

        jPanel.add(textFiled);
        this.add(jPanel,BorderLayout.NORTH);

         */
    }
}

