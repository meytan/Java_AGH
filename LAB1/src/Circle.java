public class Circle extends Figure implements Print {

    private double radius;

    Circle(double radius )
    {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return radius * radius *Math.PI;
    }

    @Override
    double calculatePerimeter() {
        return 2*radius*Math.PI;
    }

    @Override
    public void print() {

    }
}
