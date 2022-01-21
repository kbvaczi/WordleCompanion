package WordList;

import java.io.*;
import java.util.ArrayList;

public class WordListImporter {

  private String filePath;

  public WordListImporter(String filePath) {
    this.filePath = filePath;    
  }

  public ArrayList<String> getWordList() throws IOException {
    ArrayList<String> wordList = new ArrayList<String>();
    BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
    String currentLine;
    while ((currentLine = reader.readLine()) != null){
      String currentLineFormatted = currentLine.toLowerCase();
      wordList.add(currentLineFormatted);      
    }
    reader.close();
    return wordList;
  }

}
