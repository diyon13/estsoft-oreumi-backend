package day07;

public class NegativeScoreException extends Exception {
    public NegativeScoreException() {
        super("Negative scores are not valid.");
    }
}
