package Wordle;

import java.util.ArrayList;
import java.util.HashSet;

import Wordle.LetterGuess.InvalidLetterException;
import Wordle.WordGuess.InvalidLengthException;

public class WordleCompanion {

  private ArrayList<WordGuess> wordGuesses;
  private HashSet<LetterGuess> correctPosGuesses;
  private HashSet<LetterGuess> wrongPosGuesses;

  private ArrayList<String> wordList;

  /**
   * Suggests possible answers to Wordle puzzle based on previous word guesses and feedback
   * @param wordList A list of possible words that can be a solution to the Wordle puzzle
   */
  public WordleCompanion(ArrayList<String> wordList) {    
    wordGuesses = new ArrayList<WordGuess>();
    correctPosGuesses = new HashSet<LetterGuess>();
    wrongPosGuesses = new HashSet<LetterGuess>();    
    this.wordList = wordList;
  }

  /**
   * 
   * @param word
   * @param feedbacks
   * @throws InvalidLengthException
   * @throws InvalidLetterException
   */
  public void addWordGuess(String word, ArrayList<FeedbackType> feedbacks) throws InvalidLengthException, InvalidLetterException {
    WordGuess wordGuess = new WordGuess(word, feedbacks); 
    this.wordGuesses.add(wordGuess);
    correctPosGuesses.addAll(wordGuess.getCorrectPosGuesses());
    wrongPosGuesses.addAll(wordGuess.getWrongPosGuesses());    
  }


  /** 
   * Returns a list of possible answers based on the guesses already made
   */
  public ArrayList<String> getAnswerSuggestions() {
    ArrayList<String> wordSuggestions = new ArrayList<String>();
    for (String wordCandidate : this.wordList) {
      
      Boolean isValidCandidate = true;

      for (LetterGuess letterGuess : this.correctPosGuesses) {
        if (wordCandidate.charAt(letterGuess.getPos()) != letterGuess.getLetter()) {
          isValidCandidate = false;
          break;
        }
      }

      for (LetterGuess letterGuess : this.wrongPosGuesses) {
        Boolean isLetterPresent = wordCandidate.contains(String.valueOf(letterGuess.getLetter()));
        Boolean isLetterInWrongPos = wordCandidate.charAt(letterGuess.getPos()) == letterGuess.getLetter();
        if (!isLetterPresent || isLetterInWrongPos) {
          isValidCandidate = false;;
          break;
        }
      }

      for (WordGuess wordGuess : this.wordGuesses) {
        HashSet<Character> notPresentLetters = wordGuess.getNotPresentLetters();
        HashSet<Integer> availablePositions = wordGuess.getAvailablePositions();
        for (Integer pos : availablePositions) {
          Boolean isLetterPresent = notPresentLetters.contains(wordCandidate.charAt(pos));
          if (isLetterPresent) {
            isValidCandidate = false;
            break;
          }
        }
      }

      if (isValidCandidate) {
        wordSuggestions.add(wordCandidate);
      }      
    }
    
    return wordSuggestions;
  } 
  
  
}
