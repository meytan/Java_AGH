package ex2;

public class ListSizeExcpetion extends Exception {
    @Override
    public String getMessage() {
        return "Size of this list is too big!!";
    }
}
