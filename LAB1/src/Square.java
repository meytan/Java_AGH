public class Square extends Figure implements Print {

    private double side;

    Square(double side){
        if(side <= 0) throw new IllegalArgumentException("Side must be bigger than 0");
        this.side = side;
    }

    @Override
    double calculateArea() {
        return side * side;
    }

    @Override
    double calculatePerimeter() {
        return side*4;
    }

    @Override
    public void print() {

    }
}
