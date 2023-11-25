import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {
    private int generatedNumber;
    private int attempts;
    private int maxAttempts = 10;  
    private int roundsWon;

    private JTextField guessTextField;
    private JLabel feedbackLabel;
    private JButton guessButton;
    private JButton playAgainButton;

    private List<String> scoreboard;

    public GuessTheNumberGame() {
        setTitle("Guess the Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        getContentPane().setBackground(new Color(0, 0, 102));

        setLayout(new GridLayout(4, 1));

        
        guessTextField = new JTextField();
        guessTextField.setBackground(new Color(173, 216, 230));

        feedbackLabel = new JLabel("Enter your guess and press Guess!");
        guessButton = new JButton("Guess");
        playAgainButton = new JButton("Play Again");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame();
            }
        });

        add(guessTextField);
        add(feedbackLabel);
        add(guessButton);
        add(playAgainButton);

        scoreboard = new ArrayList<>();

        
        String userName = JOptionPane.showInputDialog("Enter your name:");
        int maxAttemptsPrompt = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of attempts:"));
        if (maxAttemptsPrompt > 0) {
            maxAttempts = maxAttemptsPrompt;
        }

        feedbackLabel.setText("Hi, " + userName + "! Enter your guess and press Guess! Attempts left: " + maxAttempts);

       
        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        generatedNumber = random.nextInt(100) + 1; 
        attempts = 0;
        feedbackLabel.setText("Enter your guess and press Guess! Attempts left: " + maxAttempts);
        guessTextField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            attempts++;

            if (userGuess == generatedNumber) {
                feedbackLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
                roundsWon++;

               
                scoreboard.add("Name: " + JOptionPane.showInputDialog("Enter your name: ") + ", Score: " + attempts);
            } else if (attempts >= maxAttempts) {
                feedbackLabel.setText("Sorry! You've run out of attempts. The correct number was " + generatedNumber);
                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            } else {
                String hint = (userGuess < generatedNumber) ? "Too low. Try again!" : "Too high. Try again!";
                feedbackLabel.setText(hint + " Attempts left: " + (maxAttempts - attempts));
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumberGame().setVisible(true);
            }
        });
    }
}
