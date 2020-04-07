import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * This class populates and plays hangman game 
 *
 * @author odilo M. Rugamba
 *
 * Andrew ID: omutuyin
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */
public class HangmanGUI extends LayoutGUI implements StringHandler
{

    private HangedMan man;
    private PhraseList phrases;
    private JSecretString secret;
    private int games, lostGames, wonGames;
    private boolean gameOver;
    private JLabel gamesCount;
    private JLabel wonCount;
    private JLabel lostCount;
    private int uncoverCheck;
    private ArrayList<SingleUseButton> buttons;
    private SingleUseButton playAgain;

    HangmanGUI()
    {
        phrases = new PhraseList("phrases.txt");
        CommonInitializations(); 
    }
    
    HangmanGUI(String phraseListFile)
    {
        File f = new File(phraseListFile);
        if(f.exists())
            phrases = new PhraseList(phraseListFile);
        else
            phrases = new PhraseList("odilo.txt");
        CommonInitializations();
    }

    @Override
    public void processString(String text)
    {
        if (text.equals("Play again"))
        {
            man.reset();
            initializeGame();
            return;
        }
        play(text);
    }
    
    @Override
    public void addComponents(JFrame theFrame)
    {
        Container container = theFrame.getContentPane();
        container.setLayout(new GridLayout(2,2));
        container.add(getGameBoard());
        man.setBackground(Color.LIGHT_GRAY);
        container.add(man);
        container.add(getOnScreenKeyboard());
        
    }
    
    private JPanel getOnScreenKeyboard()
    {
        JPanel keyboard = new JPanel(new GridLayout(10, 3));
        for (SingleUseButton b : buttons)
            keyboard.add(b);
        return keyboard;
    }

    private JPanel getGameBoard()
    {
        JPanel board = new JPanel(new BorderLayout());
        board.setBackground(Color.WHITE);
        
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(new JLabel("Games: "));
        p1.add(gamesCount);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(new JLabel("Won: "));
        p2.add(wonCount);
        

        JPanel p3 = new JPanel(new FlowLayout());
        
        p3.add(new JLabel("Lost: "));
        p3.add(lostCount);

        board.add(p1, BorderLayout.NORTH);
        board.add(p2, BorderLayout.WEST);
        board.add(p3, BorderLayout.EAST);
        board.add(secret, BorderLayout.CENTER);

        return board;

    }

    private void play(String txt)
    {

        if (!gameOver)
        {
            uncoverCheck = secret.uncover(txt);
            secret.setText(secret.toString());
            if(uncoverCheck == 0)
                man.addBodyPart();
            if (secret.equals(secret.toString()))
            {
                updateGamesStatus(true);
                JOptionPane.showMessageDialog(null, "Congratulations! \n You won!!!", "You won", JOptionPane.INFORMATION_MESSAGE);
            }
            if (man.isHanged())
            {
                updateGamesStatus(false);
                JOptionPane.showMessageDialog(this, "OOh sorry ! \n You Lost!!!", "You Lost", JOptionPane.INFORMATION_MESSAGE);

            }
        }

    }
    private void CommonInitializations()
    {
        man = new HangedMan(300, 300);
        
        games = 0;
        lostGames = 0;
        wonGames = 0;
        gamesCount = new JLabel("0");
        wonCount = new JLabel("0");
        lostCount = new JLabel("0");
        buttons = SingleUseButton.getLetterButtons(this);
        playAgain = new SingleUseButton("Play again", this);
        buttons.add(playAgain);
        playAgain.setBackground(Color.RED);
        playAgain.setForeground(Color.white);
        buttons.add(playAgain);
        uncoverCheck = 0;
        initializeGame();
    }
    
    private void initializeGame()
    {
        String phrase = phrases.getRandomPhrase();
        //if no phrase the use a default phrase
        if(phrase == null)
        {
            //if secret not initialized before
            if(secret == null)
                secret = new JSecretString("Let us play");
            else
            {
                secret.setSecretPhrase("do you want to go again");  
                setAllButtons(true);
            }
                
        }
        else
        {
          //if secret not initialized before
            if(secret == null)
                secret = new JSecretString(phrase);
            else
            {
                secret.setSecretPhrase(phrase);
                setAllButtons(true);
            }  
        }
        gameOver = false;
    }
    
    private void updateGamesStatus(boolean won)
    {
        if(won)
            wonGames++;
        else
        {
            lostGames++;
            secret.uncoverAll();
        }      
        games++;
        gameOver = true;
        setAllButtons(false);
        
        gamesCount.setText(Integer.toString(games));
        wonCount.setText(Integer.toString(wonGames));
        lostCount.setText(Integer.toString(lostGames));
    }
    private void setAllButtons(boolean status)
    {
        for (SingleUseButton button : buttons)
            button.setEnabled(status);
        playAgain.setEnabled(!status);  
    }
}
