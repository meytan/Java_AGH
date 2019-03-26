import java.util.Scanner;

class GetData {

    static int getInfoAboutFidure(){
        System.out.println("Choose figure: \n 1. Square \n 2. Triangle \n 3. Square \n Press 0 if you want to stop program");
        Scanner scanner = new Scanner(System.in);
        return  scanner.nextInt();

    }

    static Square getInfoAboutSquare(){
        System.out.println("What is side of your square?");
        Scanner scanner = new Scanner(System.in);
        return  new Square(scanner.nextDouble());
    }

    static Triangle getInfoAboutTriangle(){
        Scanner scanner = new Scanner(System.in);
        double side1,side2,side3;
        System.out.println("What is first side of your triangle?");
        side1=scanner.nextDouble();
        System.out.println("What is second side of your triangle?");
        side2=scanner.nextDouble();
        System.out.println("What is third side of your triangle?");
        side3=scanner.nextDouble();

        return  new Triangle(side1,side2,side3);
    }

    static Circle getInfoAboutCircle(){
        System.out.println("What is radius of your circle?");
        Scanner scanner = new Scanner(System.in);
        return  new Circle(scanner.nextDouble());
    }


}
