package ex1;

import ex1.Matrix;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void addMatrixWithSameIntValues() {
        Integer[][] intTab1 = {{0,0,0},{9,9,9},{-9,-9,-9}};
        Integer[][] resIntTab1 = {{0,0,0},{18,18,18},{-18,-18,-18}};

        checkIntMatrices(intTab1, intTab1, resIntTab1);

    }
    @Test
    void addMatrixWithDifferentIntValues() {
        Integer[][] intTab1 = {{0,0,0},{9,9,9},{-9,-9,-9}};
        Integer[][] intTab2 = {{7,5,1},{3,2,-1},{-4,1234,123}};
        Integer[][] resIntTab1 = {{7,5,1},{12,11,8},{-13,1225,114}};

        checkIntMatrices(intTab1, intTab2, resIntTab1);

    }

    @Test
    void checkGetters(){
        Integer[][] intTab1 = {
                {0,0,0,3,2,1},
                {9,9,9,3,2,1},
                {-9,-9,-9,3,0,0},
                {0,0,0,0,0,0},
                {999999999,999999999,999999999,999999999,999999999,999999999},
                {-999999999,-999999999,-999999999,-999999999,-999999999,-999999999},
                {-999999999,-999999999,-999999999,-999999999,-999999999,-999999999}
        };
        Matrix<Integer> matrix = new Matrix<Integer>(intTab1);
        matrix.iterator().next();
        matrix.iterator().next();
        matrix.iterator().next();
        matrix.iterator().next();
        assertAll(
                () -> assertEquals(6,matrix.getColumns()),
                () -> assertEquals(7,matrix.getRows()),
                () -> assertEquals(5,matrix.getMaxColIndex()),
                () -> assertEquals(3,matrix.getCurrentColumn()),
                () -> assertEquals(0,matrix.getCurrentRow()),
                () -> assertTrue(matrix.iterator().hasNext())

        );
    }

    @Test
    void exceptionOnAddingWrongMatrices(){
        Integer[][] intTab1 = {{0,0,0},{9,9,9},{-9,-9,-9}};
        Integer[][] intTab2 = {{7,5,1,1},{3,2,-1,1},{-4,1234,123,2}};

        Matrix<Integer> matrix1 = new Matrix<>(intTab1);
        Matrix<Integer> matrix2 = new Matrix<>(intTab2);

        assertThrows(IllegalArgumentException.class,
                () -> matrix1.add(matrix2, (a, b) -> a+b)
        );
    }



    private void checkIntMatrices(Integer[][] intTab1, Integer[][] intTab2, Integer[][] resIntTab1) {
        Matrix<Integer> matrix1 = new Matrix<>(intTab1);
        Matrix<Integer> matrix2 = new Matrix<>(intTab2);
        matrix1.add(matrix2, (a, b) -> a+b);

        List<List<Integer>> intList1 = createIntList(resIntTab1);

        assertEquals(intList1,matrix1.getMatrix());
    }
    private void checkStringMatrices(String[][] strTab1, String[][] strTab2, String[][] resStrTab1) {
        Matrix<String> matrix1 = new Matrix<>(strTab1);
        Matrix<String> matrix2 = new Matrix<>(strTab2);
        matrix1.add(matrix2, (a, b) -> a+b);

        List<List<String>> intList1 = createStrList(resStrTab1);

        assertEquals(intList1,matrix1.getMatrix());
    }

    List<List<Integer>> createIntList(Integer[][] intTab){
        List<List<Integer>> intList = new ArrayList<>();
        for(Integer[] i : intTab ){
            List<Integer> tmpList = new ArrayList<>(Arrays.asList(i));
            intList.add(tmpList);
        }
        return intList;
    }
    List<List<String>> createStrList(String[][] strTab){
        List<List<String>> strList = new ArrayList<>();
        for(String[] i : strTab ){
            List<String> tmpList = new ArrayList<>(Arrays.asList(i));
            strList.add(tmpList);
        }
        return strList;
    }
}