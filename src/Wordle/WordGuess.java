package Wordle;

import java.util.ArrayList;
import java.util.HashSet;

import Wordle.LetterGuess.InvalidLetterException;

public class WordGuess {
  
    HashSet<LetterGuess> wordGuess;

    /**
     * Creates an object to track word guesses
     * @param letterGuesses
     */
    public WordGuess(HashSet<LetterGuess> letterGuesses) {
      this.wordGuess = letterGuesses;
    }

    /**
     * * Creates an object to track word guesses
     * @param word
     * @param feedbacks
     * @throws InvalidLengthException If length of feedbacks does not equal length of word
     * @throws InvalidLetterException If given letter is not A-Z or a-z
     */
    public WordGuess(String word, ArrayList<FeedbackType> feedbacks) throws InvalidLengthException, InvalidLetterException {
      if (word.length() != feedbacks.size()) {
        throw new InvalidLengthException();
      }
      wordGuess = new HashSet<LetterGuess>();
      for (Integer i = 0; i < word.length(); i ++) {
        LetterGuess lGuess = new LetterGuess(word.charAt(i), i, feedbacks.get(i));
        wordGuess.add(lGuess);
      }
    }

    /**
     * Returns the letters in the word that were guessed in the correct position
     */
    public HashSet<LetterGuess> getCorrectPosGuesses() {
      HashSet<LetterGuess> correctGuesses = new HashSet<LetterGuess>();
      for (LetterGuess letterGuess : this.wordGuess) {
        Boolean isCorrect = letterGuess.getFeedbackType() == FeedbackType.CORRECTPOS;
        if (isCorrect) {
          correctGuesses.add(letterGuess);
        }
      }
      return correctGuesses;
    }

    /**
     * Returns the letters int he word that are present in the answer, but were guesseed in the wrong position
     */
    public HashSet<LetterGuess> getWrongPosGuesses() {
      HashSet<LetterGuess> wrongGuesses = new HashSet<LetterGuess>();
      for (LetterGuess letterGuess : this.wordGuess) {
        Boolean isCorrect = letterGuess.getFeedbackType() == FeedbackType.WRONGPOS;
        if (isCorrect) {
          wrongGuesses.add(letterGuess);
        }
      }
      return wrongGuesses;
    }

    /**
     * Returns the letters in the word are not present in the solution in the available positions
     */
    public HashSet<Character> getNotPresentLetters() {
      HashSet<Character> notPressentLetters = new HashSet<Character>();
      for (LetterGuess letterGuess : this.wordGuess) {
        if (letterGuess.getFeedbackType() == FeedbackType.NOTPRESENT) {
          notPressentLetters.add(letterGuess.getLetter());
        }
      }
      return notPressentLetters;
    }

    /**
     * Returns the positions of unknown letters in the answer
     */
    public HashSet<Integer> getAvailablePositions() {
      HashSet<Integer> availablePositions = new HashSet<Integer>();
      for (LetterGuess letterGuess : this.wordGuess) {
        Boolean isPosLocked = letterGuess.getFeedbackType() == FeedbackType.CORRECTPOS;
        if (!isPosLocked) {
          availablePositions.add(letterGuess.getPos());
        }
      }
      return availablePositions;
    }

    class InvalidLengthException extends Exception {
      public InvalidLengthException() {
        super("Length of feedbacks and word need to be the same");
      }
    }

}
