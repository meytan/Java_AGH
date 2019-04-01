package ex2;

public class ListSizeException extends Exception {
    @Override
    public String getMessage() {
        return "Size of this list is too big!!";
    }
}
