import java.util.*;
/**
 * This class gets phrases list from a file and manupilates it
 *
 * @author odilo M. Rugamba, Docile Umurengezi
 *
 * Andrew ID: omutuyin, udocile
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */
public class PhraseList
{
    private ArrayList<String> phrases;
    
    public PhraseList()
    {
        phrases = new ArrayList<String>();
    }
    public PhraseList(String fileName)
    {
        phrases = new ArrayList<String>();
        InputDataFile file = new InputDataFile(fileName);
        String phrase;
        file.open();
        if(file.isOpen())
        {
            phrase = file.readString();
            while(phrase != null)
            {
                phrases.add(phrase);
                phrase = file.readString();
            }
        }
        else
        {
            System.out.println("File failed to open");
        }
    }
    public void addPhrase(String phrase)
    {
        phrases.add(phrase);
    }
    public String getPhrase(int phraseNumber)
    {
        if(phraseNumber > phrases.size() || phraseNumber <= 0)
            return "";
        return phrases.get(phraseNumber-1);
    }
    public String getRandomPhrase()
    {
        Random r = new Random();
        return phrases.get(r.nextInt(phrases.size()));
    }
    public boolean updatePhraseList(String fileName)
    {
        InputDataFile file = new InputDataFile(fileName);
        String phrase;
        file.open();
        if(file.isOpen())
        {
            phrase = file.readString();
            while(phrase != null)
            {
                phrases.add(phrase);
                phrase = file.readString();
            }
        }
        else
        {
            System.out.println("File failed to open");
            return false;
        }

        return true;
    }
    public void clear()
    {
        phrases.clear();
    }
    public void print()
    {
        for(int i = 0; i < phrases.size(); i++)
            System.out.println(phrases.get(i)); 
    }
    public String toString()
    {
        return "this Object contains " + phrases.size() +" phrases";
    }
    
}
