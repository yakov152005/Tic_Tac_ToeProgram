import Final.Finals;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class GameScene extends JPanel {
    public static int counter = 0;
    private static int player1Score = 0;
    private static int player2Score = 0;
    private JLabel player1Label;
    private JLabel player2Label;
    private static int[] source;
    public GameScene() throws IOException {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        source = new int[2];

        JPanel gridPanel = new JPanel(new GridLayout(3, 3)); // פריסה בצורת טבלה
        Font myFont = new Font("Ink Free", Font.BOLD, 75);
        JButton[] buttons = new JButton[Finals.SIZE_FOR_BUTTON];

        for (int i = 0; i < buttons.length; i++) {
            JButton newButton = new JButton();
            newButton.setFont(myFont);
            newButton.setBackground(Color.DARK_GRAY);

            newButton.addActionListener(e -> {
                if (counter % 2 == 0) {
                    if (Objects.equals(newButton.getText(), "")) {
                        newButton.setText("X");
                        newButton.setForeground(Color.RED); // צבע לאיקס
                        counter++;
                    }
                } else {
                    if (Objects.equals(newButton.getText(), "")) {
                        newButton.setText("O");
                        newButton.setForeground(Color.YELLOW); // צבע לעיגול
                       // newButton.setHorizontalAlignment(JLabel.CENTER);
                        counter++;
                    }
                }
                if (checkWinner(buttons)) {
                    String winner = (counter % 2 == 0) ? "O" : "X";
                    if (winner.equals("X")) {
                        player1Score++;
                    } else {
                        player2Score++;
                    }

                    JOptionPane.showMessageDialog(null, "The Winner is ---> " + winner);
                    updateScore();
                    resetGame(buttons);
                } else if (isDraw(buttons)) {
                    JOptionPane.showMessageDialog(null, "|DRAW|, TRY AGAIN!");
                    resetGame(buttons);
                }
            });


            source[0] += player1Score;
            source[1] += player2Score;
            File file = new File("Source.txt");
            savePoints(file);
            buttons[i] = newButton;
            gridPanel.add(buttons[i]);
        }


        JPanel scorePanel = new JPanel(new GridLayout(1, 2));
        player1Label = new JLabel("Player 1 (X): " + player1Score);
        player2Label = new JLabel("Player 2 (O): " + player2Score);
        scorePanel.add(player1Label);
        scorePanel.add(player2Label);

        this.add(scorePanel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);
    }


    public void savePoints(File file)throws IOException{
        PrintWriter pw = new PrintWriter(file);
        pw.println(source[0]);
        pw.println(source[1]);
        pw.close();
    }
    private void updateScore() {
        player1Label.setText("Player 1 (X): " + player1Score);
        player2Label.setText("Player 2 (O): " + player2Score);
    }


    public boolean isDraw(JButton[] buttons) {
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public boolean checkWinner(JButton[] buttons) {
        // Rows
        int a,b,c;
        for (int i = 0; i < 9; i += 3) {
            if (!buttons[i].getText().isEmpty() &&
                    buttons[i].getText().equals(buttons[i + 1].getText()) &&
                    buttons[i + 1].getText().equals(buttons[i + 2].getText())) {
                a = i;
                b = i+1;
                c=i+2;
                wins(buttons,a,b,c);
                return true;
            }
        }

        // Columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().isEmpty() &&
                    buttons[i].getText().equals(buttons[i + 3].getText()) &&
                    buttons[i + 3].getText().equals(buttons[i + 6].getText())) {
                a = i;
                b = i+3;
                c=i+6;
                wins(buttons,a,b,c);
                return true;
            }
        }

        // Diagonals
        if (!buttons[0].getText().isEmpty() &&
                buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[8].getText())) {
            a = 0;
            b = 4;
            c= 8;
            wins(buttons,a,b,c);
            return true;
        }
        if (!buttons[2].getText().isEmpty() &&
                buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[6].getText())) {
            a = 2;
            b = 4;
            c= 6;
            wins(buttons,a,b,c);
            return true;
        }

        return false;
    }

    private void resetGame(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setText("");
        }
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setBackground(Color.darkGray);
        }
        counter = 0;
    }
    public void wins(JButton[] buttons,int a,int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }


}