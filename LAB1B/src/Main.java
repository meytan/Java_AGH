public class Main {

    public static void main(String[] args) {
        Print figure;
        while(true) {
            try{

                switch (GetData.getInfoAboutFidure()){
                    case 1:
                        figure= GetData.getInfoAboutSquare();
                        break;
                    case 2:
                        figure= GetData.getInfoAboutTriangle();
                        break;
                    case 3:
                        figure= GetData.getInfoAboutCircle();
                        break;
                    case 0:
                        return;
                    default:
                        throw new IllegalArgumentException("Wrong option!");
                }
                figure.print();

            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }



}
