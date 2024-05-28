import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    Clip clip;

    public Audio() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("D:\\קוד\\Code - Java\\practice-02\\TicTacToeProgram\\out\\resources\\Alan Walker - Faded.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
    public void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void startAudio() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }
}