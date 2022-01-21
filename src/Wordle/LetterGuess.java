package Wordle;

class LetterGuess { 

  private Character letter;
  private Integer position;
  private FeedbackType feedbackType;
  
  /**
   * Used to track letters that have been guessed by the user
   * @param letter letter that has been guessed
   * @param position position of letter starting with 0
   * @throws InvalidLetterException
   */
  public LetterGuess(Character letter, Integer position, FeedbackType feedbackType) throws InvalidLetterException {
    if (!isValidLetter(letter)) {
      throw new InvalidLetterException();
    }
    this.letter = Character.toLowerCase(letter);
    this.position = position;
    this.feedbackType = feedbackType;
  }

  /**
   * Returns the letter guessed
   */
  public Character getLetter() {
    return this.letter;
  }

  /**
   * Returns the position at which the letter was guessed
   */
  public Integer getPos() {
    return this.position;
  }

  /**
   * Returns whether the letter was in the right/wrong position, or 
   * if it was not present at all
   */
  public FeedbackType getFeedbackType() {
    return this.feedbackType;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int)this.letter;
    result = prime * result + position;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof LetterGuess)) {
      return false;
    }        
    if (obj == this) {
      return true;
    }        
    LetterGuess otherGuess = (LetterGuess)obj;
    Boolean isSameLetter = this.letter == otherGuess.letter;
    Boolean isSamePos = this.position == otherGuess.position;
    return isSameLetter && isSamePos;
  }

  @Override
  public String toString() {
    return String.valueOf(this.letter) + String.valueOf(this.position) + "-" + this.feedbackType.toString();
  }

  private Boolean isValidLetter(Character letter) {
    return letter >= 'A' && letter <= 'z';
  }

  class InvalidLetterException extends Exception {
    public InvalidLetterException() {
      super("Only letters between A-Z are allowed");
    }
  }

}
