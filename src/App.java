import java.util.Scanner;
import java.util.ArrayList;
import WordList.WordListImporter;
import Wordle.*;

public class App {
  public static void main(String[] args) throws Exception {
    ArrayList<String> wordList = generateWordList();
    if (wordList.size() <= 0) {
      throw new Exception("Empty word list");
    }

    WordleCompanion WordleCompanion = new WordleCompanion(wordList);
    Scanner input = new Scanner(System.in);

    do {       
      try {
        System.out.println("Enter 5-letter word guessed (ex. JUMPS):");      
        String word = input.nextLine();
        System.out.println("Enter 5-letter feedback G=Green Y=Yellow B=Black (ex. GBBYB):");
        String feedbackString = input.nextLine();
        ArrayList<FeedbackType> feedbacks = feedbacksFromString(feedbackString);
        WordleCompanion.addWordGuess(word, feedbacks);
        System.out.println("Potential Answers:");
        System.out.println(WordleCompanion.getAnswerSuggestions());        
      } catch(Exception e) {
        System.out.println("Input error, try this word again");
        continue;
      }      
    } while (WordleCompanion.getAnswerSuggestions().size() > 0);
    input.close();
  }

  private static ArrayList<String> generateWordList() {
    ArrayList<String> wordList = new ArrayList<String>();
    String filePath = "./lib/wordle-answers-alphabetical.txt";
    WordListImporter wordListImporter = new WordListImporter(filePath);        
    try {
      wordList = wordListImporter.getWordList();
      return wordList;
    } catch(Exception err) {
      System.out.println("Error parsing word file");
    }
    return wordList;
  }

  private static ArrayList<FeedbackType> feedbacksFromString(String feedbackString) {
    ArrayList<FeedbackType> feedback = new ArrayList<FeedbackType>(); 
    for (Character ch : feedbackString.toCharArray()) {
      switch (ch) {
        case 'G','g':
          feedback.add(FeedbackType.CORRECTPOS);
          break;
        case 'Y', 'y':
          feedback.add(FeedbackType.WRONGPOS);
          break;
        default:
          feedback.add(FeedbackType.NOTPRESENT);
      }
    }
    return feedback;
  }
}