import javax.swing.JLabel;

/**
 * This class takes in a secret string and hides it but reveals it through uncover method 
 *
 * @author odilo M. Rugamba, Docile Umurengezi
 *
 * Andrew ID: omutuyin, udocile
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */
public class JSecretString extends JLabel
{
    private String secretPhrase, displayPhrase;

    public JSecretString()
    {
        secretPhrase = "";
        displayPhrase = "";
    }
    public JSecretString(String passedSecretPhrase)
    {
        secretPhrase = passedSecretPhrase;
        displayPhrase = secretPhrase.replaceAll("[A-Za-z]","_");
        setText(displayPhrase);
    }

    public void setSecretPhrase(String passedSecretPhrase)
    {
        secretPhrase = passedSecretPhrase;
        displayPhrase = secretPhrase.replaceAll("[A-Za-z]","_");
        setText(displayPhrase);
    }
    public boolean equals(String str)
    {
        return str.equalsIgnoreCase(secretPhrase);
    }
    public int uncover(String chars)
    {
        int count=0;
        for(int i=0; i < chars.length(); i++)
        {
            String uncoverChar = chars.substring(i,i+1);
            for(int j=0; j < secretPhrase.length(); j++)
            {
                if(secretPhrase.substring(j,j+1).equalsIgnoreCase(uncoverChar) && !displayPhrase.substring(j,j+1).equalsIgnoreCase(uncoverChar) )
                {
                    displayPhrase = displayPhrase.substring(0,j) + secretPhrase.substring(j,j+1) + displayPhrase.substring(j+1,displayPhrase.length());
                    count++;
                }
            }
        }
        setText(displayPhrase);
        return count;
    }
    public void uncoverAll()
    {
        displayPhrase = secretPhrase;
        setText(displayPhrase);
    }
    public String toString()
    {
        return displayPhrase;
    }
}
