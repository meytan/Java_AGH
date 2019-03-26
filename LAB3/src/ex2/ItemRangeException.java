package ex2;

public class ItemRangeException extends Exception {
    @Override
    public String getMessage() {
        return "Some element on this list is out of range!";
    }
}
