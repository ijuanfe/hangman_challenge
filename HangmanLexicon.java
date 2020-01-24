/*
* File: HangmanLexicon.java
* -------------------------
* This file contains a stub implementation of the HangmanLexicon
* class that you will reimplement for Part III of the assignment.
*/

//import acm.util.*;
import java.util.Random;

// To read from console
import java.util.Scanner;

public class HangmanLexicon {

    private String word;
    private int remaining_guesses;
    private int guessed_counter;
    private char[] word_characters;
    private char[] guessed_characters;
    
    // Constructor
    public HangmanLexicon() {
        newGame();
    }

    // Initialize all variables for a new game
    public void newGame() {
        
        this.word = "HUBBUB"; //selectWord();
        this.remaining_guesses = 8;
        this.guessed_counter = 0;
        this.word = this.word;
        this.word_characters = this.word.toCharArray();
        // Guessed characters
        this.guessed_characters = new char [this.word_characters.length];
        for(int i = 0; i < this.guessed_characters.length; i++) {
            this.guessed_characters[i] = '_';
        }
    }

    // Returns the number of words in the lexicon
    public int getWordCount() {
        return 10;
    }
    
    // Returns the word at the specified index
    public String getWord(int index) {

        switch (index) {
            case 0: return "BUOY";
            case 1: return "COMPUTER";
            case 2: return "CONNOISSEUR";
            case 3: return "DEHYDRATE";
            case 4: return "FUZZY";
            case 5: return "HUBBUB";
            case 6: return "KEYHOLE";
            case 7: return "QUAGMIRE";
            case 8: return "SLITHER";
            case 9: return "ZIRCON";
            default: return "Option not avaliable"; //throw new ErrorException("getWord: Illegal index");
        }
    }
    
    // Look if a player's guess is correct
    public Boolean lookGuess(char guess) {
        
        Boolean found = false;

        for(int i = 0; i < this.word_characters.length; i++) {
            if(this.word_characters[i] == guess) {
                this.guessed_characters[i] = guess;
                this.guessed_counter++;
                found = true;
            }
        }
        if(found == false) {
            this.remaining_guesses--;
        }
        return found;
    }

    // Select a word from the list randomly
    public String selectWord() {
        
        Random rand = new Random();
        int max = getWordCount() - 1;
        int min = 0;
        int random_number = rand.nextInt((max - min) + 1) + min;
        return getWord(random_number);
    }

    // Get word status
    public String getStatusWord() {
        
        String status_word = "";
        for(int i = 0; i < this.guessed_characters.length; i++) {
            status_word += this.guessed_characters[i] + " ";
        }
        return ("The word now looks like this: " + status_word);
    }

    // Get remaining guesses
    public String getRemainingGuesses() {
        return ("You have " + this.remaining_guesses + " guesses left.");
    }

    public String guessExceptionHandler(String guess) {

        String exception = "";
        if(guess.length() == 0) {
            exception = "You did not enter any characters, try again." ;
            return exception;
        }else if(guess.length() > 1) {
            exception = "Illegal move. Guess only one character.";
            return exception;
        }else {
            return "";
        }
    }
    
    public void runGame() {

        // Cleaning variables for a new game
        newGame();
        
        // Player interface
        System.out.println("Welcome to Hangman!");
        System.out.println(getStatusWord());
        System.out.println(getRemainingGuesses());
        // Reading input from console
        Scanner input = new Scanner(System.in);
        String guess = "";

        while(this.remaining_guesses > 0) {
            
            System.out.print("Your guess: ");
            guess = input.nextLine().toUpperCase();

            if(guessExceptionHandler(guess) == "") {
                
                if(lookGuess(guess.charAt(0))) {
                    System.out.println("That guess is correct.");
                    // If player guess the word finish the game
                    if(this.guessed_counter == this.word.length() ) {
                        break;
                    }
                    System.out.println(getStatusWord());
                    System.out.println(getRemainingGuesses());
                }else {
                    System.out.println("There are no " + guess.charAt(0) + "'s in the word.");
                    System.out.println(getStatusWord());
                    System.out.println(getRemainingGuesses());
                }
            }else {
                System.out.println(guessExceptionHandler(guess));
            }
        }

        if(this.guessed_counter == this.word.length()) {

            System.out.println("You guess the word: " + this.word + "\nYou win.");
            System.out.println("Want to play again? Yes (Y) - No (N)");
            guess = input.nextLine().toUpperCase();
            if(guess.charAt(0) == 'Y'){
                runGame();
            }else{
                System.exit(0);
            }
        }else {

            System.out.println("You're completely hung. \nThe word was: " + this.word + "\nYou lose.");
            System.out.println("Want to play again? Yes (Y) - No (N)");
            guess = input.nextLine().toUpperCase();
            if(guess.charAt(0) == 'Y'){
                runGame();
            }else{
                System.exit(0);
            }
        }
    }
}