package day07;

public class NegativeScoreException extends Exception {
    public NegativeScoreException() {
        super("ERROR: Negative scores are not valid. \nTry again.");
    }
}
