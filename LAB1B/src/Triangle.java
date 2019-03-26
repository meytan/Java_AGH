import static java.lang.Math.sqrt;

public class Triangle extends Figure implements Print {

    private double side1,side2,side3;

    Triangle(double side1, double side2, double side3){
        if(side1 <= 0 || side2 <= 0 || side3 <= 0) throw new IllegalArgumentException("All sides must be bigger than 0");
        if( side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1){
            throw new IllegalArgumentException("Traingle cant't be build with that values");}
        this.side1=side1;
        this.side2=side2;
        this.side3=side3;
    }

    @Override
    double calculateArea() {

        double area,halfPerimeter;
        halfPerimeter=this.calculatePerimeter()/2;
        area=sqrt( halfPerimeter * (halfPerimeter-side1) * (halfPerimeter-side2) * (halfPerimeter-side3) );

        return area;
    }

    @Override
    double calculatePerimeter() {
        return side1+side2+side3;
    }

    @Override
    public void print() {
        System.out.println("Triangle");
        System.out.println("Sides: "+side1+"  " +side2+"  "+side3);
        System.out.println("Perimeter: "+calculatePerimeter());
        System.out.println("Area: "+calculateArea());


    }
}
