import ex1.Matrix;
import ex2.ItemRangeException;
import ex2.ListSizeException;
import ex2.Solution;
import ex3.Substring;
import ex4.Exercise2;
import ex5.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Init {
    static void initEx1() {
        System.out.println("Exercise 1");
        Integer[][] integer1 = {{1,2,3,4},{5,7,1,3}};
        Integer[][] integer2 = {{5,3,3,4},{5,7,1,7}};

        String[][] string1 = {{"Wisla ","Lech "},{"Slask ","Legia "}};
        String[][] string2 = {{"Krakow ","Poznan"},{"Wroclaw","Warszawa"}};

        Matrix<Integer> matrix1 = new Matrix<>(integer1);
        Matrix<Integer> matrix2 = new Matrix<>(integer2);

        Matrix<String> matrix3 = new Matrix<>(string1);
        Matrix<String> matrix4 = new Matrix<>(string2);

        matrix1.add(matrix2, (a, b) -> a+b);
        matrix3.add(matrix4, (a, b) -> a+b);

        for(Integer i: matrix1){
            System.out.print(i+" ");
            if(matrix1.getCurrentColumn()==matrix1.getMaxColIndex()){
                System.out.print("\n");
            }
        }

        for(String i: matrix3){
            System.out.print(i+" ");
            if(matrix3.getCurrentColumn()==matrix3.getMaxColIndex()){
                System.out.print("\n");
            }
        }
    }

    static void initEx2() {
        System.out.println();
        System.out.println("Exercise 2");
        Integer[] array = {1, 3, 6, 4, 1, 2, 5,7,8,10,-20,-1,15,1,1,1};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(array));


        try {
            System.out.println(Solution.solution(list));
        } catch (ListSizeException listSizeException) {
            System.out.println(listSizeException.getMessage());
        } catch (ItemRangeException e) {
            System.out.println(e.getMessage());
        }
    }

    static void initEx3() {
        System.out.println();
        System.out.println("Exercise 3");
        System.out.println(Substring.substring("abcd","cdabcdab"));

    }

    static void initEx4() {
        System.out.println();
        System.out.println("Exercise 4");
        float[] arr = {2,3,4, 7,8, 11, 15};
        float target = 9;
        int[] res = Exercise2.solution( arr,target);

        System.out.println(target+" = "+ arr[res[0]] + " + " + arr[res[1]]);
    }

    static void initEx5() {
        System.out.println();
        System.out.println("Exercise 5");
        double elapsedSeconds;
        int[] array;

        //==================Quick Sort==================
        System.out.println();
        System.out.println("Algorithm: Quick Sort");

        array = Test.genereatePesymisticArray(10000);
        elapsedSeconds = Test.testQuickSort(array);
        System.out.println("Pessimistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateRealisticArray(10000);
        elapsedSeconds = Test.testQuickSort(array);
        System.out.println("Realistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateOptymisticArray(10000);
        elapsedSeconds = Test.testQuickSort(array);
        System.out.println("Optimistic Case: \t" + elapsedSeconds + "s");

        //==================Bubble Sort==================
        System.out.println();
        System.out.println("Algorithm: Bubble Sort");

        array = Test.genereatePesymisticArray(10000);
        elapsedSeconds = Test.testBubbleSort(array);
        System.out.println("Pessimistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateRealisticArray(10000);
        elapsedSeconds = Test.testBubbleSort(array);
        System.out.println("Realistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateOptymisticArray(10000);
        elapsedSeconds = Test.testBubbleSort(array);
        System.out.println("Optimistic Case: \t" + elapsedSeconds + "s");

        //==================Cycle Sort==================
        System.out.println();
        System.out.println("Algorithm: Cycle Sort");

        array = Test.genereatePesymisticArray(10000);
        elapsedSeconds = Test.testCycleSort(array);
        System.out.println("Pessimistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateRealisticArray(10000);
        elapsedSeconds = Test.testCycleSort(array);
        System.out.println("Realistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateOptymisticArray(10000);
        elapsedSeconds = Test.testCycleSort(array);
        System.out.println("Optimistic Case: \t" + elapsedSeconds + "s");

        //==================Gnome Sort==================
        System.out.println();
        System.out.println("Algorithm: Gnome Sort");

        array = Test.genereatePesymisticArray(10000);
        elapsedSeconds = Test.testGnomeSort(array);
        System.out.println("Pessimistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateRealisticArray(10000);
        elapsedSeconds = Test.testGnomeSort(array);
        System.out.println("Realistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateOptymisticArray(10000);
        elapsedSeconds = Test.testGnomeSort(array);
        System.out.println("Optimistic Case: \t" + elapsedSeconds + "s");

        //==================Coctail Sort==================
        System.out.println();
        System.out.println("Algorithm: Coctail Sort");

        array = Test.genereatePesymisticArray(10000);
        elapsedSeconds = Test.testCoctailSort(array);
        System.out.println("Pessimistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateRealisticArray(10000);
        elapsedSeconds = Test.testCoctailSort(array);
        System.out.println("Realistic Case: \t" + elapsedSeconds + "s");

        array = Test.genereateOptymisticArray(10000);
        elapsedSeconds = Test.testCoctailSort(array);
        System.out.println("Optimistic Case: \t" + elapsedSeconds + "s");

    }

}
