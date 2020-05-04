/*
 * Divyesh Johri
 * 2/26/20
 * 
 * Worked with Yajur Tomar
 */
package highlow;

//Import appropriate packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HighLowFrame extends JFrame{
    
    // Instance game variables and Swing components
    private Random number = new Random();
    private int previous = 0;
    private int answer, guess;
    
    private JTextField input;
    private JButton restart;
    private JLabel prompt;
    private JLabel highOrLow = new JLabel();
    private JPanel panel;
    
    // Constructor
    public HighLowFrame()
    {
        super("Make a guess:");
        setLayout(new FlowLayout());
        play();        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,100);
    }
    
    // Panel that plays the game
    public void play()
    {
        // Starts a new panel
        panel = new JPanel();
        setContentPane(panel);
        
        // Prompts the user
        prompt = new JLabel("I've picked a number between 1 and 1000"
                + ", you need to guess what it is! I will tell you if"
                + " you're too high or too low, and show whether you are"
                + " hot or cold.");
        
        // Restart button
        restart = new JButton("Restart");
        
        // Obtain a new random number
        answer = number.nextInt(1000) + 1; 
        
        // Input field
        input = new JTextField(10);
        input.setEditable(true);
        
        // Tells the user if they are 'high' or 'low'
        highOrLow.setText("No input");
        
        // Action - pressing the restart button to restart the game
        restart.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent event)
           {
               clearComponents();
               play();               
           }
        });
        
        // Action - user inputs a guess
        input.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent event)
           {
               guess = Integer.parseInt(event.getActionCommand());
               checkHighLow();
               checkHotCold();
           }
        });
        
        // Add Components
        panel.add(prompt);
        panel.add(input);
        panel.add(restart);
        panel.add(highOrLow);        
        setVisible(true);
        
    }
    
    // Clear Components
    public void clearComponents()
    {
        setVisible(false);
        remove(prompt);
        remove(input);
        remove(restart);
        remove(highOrLow);
    }
    
    // Checks whether input is too high or too low
    public void checkHighLow()
    {
        if(answer == guess)
        { // Win
            highOrLow.setText("Correct!");
            input.setEditable(false);
        }
        else if(guess - answer > 0)
        { // Low
            highOrLow.setText("Too high");
        }
        else
        { // High
            highOrLow.setText("Too low");
        }
        
    }
    
    // Checks whether input is hot or cold, and displays red or
    // blue accordingly
    public void checkHotCold()
    {
        if(answer == guess)
        { // Win
            getContentPane().setBackground(Color.GREEN);
        }
        else if(Math.abs(answer-guess) < Math.abs(answer-previous))
        { // Hot
            getContentPane().setBackground(Color.RED);            
        }
        else
        { // Cold
            getContentPane().setBackground(Color.BLUE);
        }
        
        // Update previous guess
        previous = guess;
    }
    
}
