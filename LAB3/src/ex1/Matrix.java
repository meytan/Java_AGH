package ex1;

import java.util.*;

public class Matrix<T> implements Iterable<T> {

    List<List<T>> matrix;

    private int maxColIndex, maxRowIndex, currentColumn = -1, currentRow =0, columns, rows;

    public Matrix(T[][] matrix) {
        this.matrix = new ArrayList<List<T>>();
        maxColIndex = matrix[0].length-1;
        maxRowIndex = matrix.length-1;
        columns=matrix[0].length;
        rows=matrix.length;


        for(T[] i : matrix ){
            List<T> tmpList = new ArrayList<T>(Arrays.asList(i));
            this.matrix.add(tmpList);

        }
    }

    Matrix(List<List<T>> matrix) {
        this.matrix = matrix;
        maxColIndex = matrix.get(0).size()-1;
        maxRowIndex = matrix.size()-1;
        columns=matrix.get(0).size();
        rows=matrix.size();


    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (maxColIndex==currentColumn && maxRowIndex== currentRow) {
                    currentColumn = -1;
                    currentRow = 0;
                    return false;
                }
                else return true;
            }

            @Override
            public T next() {
                T next;
                if (currentColumn < maxColIndex) {
                    currentColumn++;
                    next = matrix.get(currentRow).get(currentColumn);

                }
                else{
                    currentColumn = 0;
                    currentRow++;
                    next = matrix.get(currentRow).get(currentColumn);
                }

                return next;
            }
        };
    }

    public void add(Matrix<T> matrix2, Additive<T> a){
        for(T i: matrix2){
            T x = this.matrix.get(matrix2.getCurrentRow()).get(matrix2.getCurrentColumn());
            this.matrix.get(matrix2.getCurrentRow()).set(matrix2.getCurrentColumn(),a.add(x,i));
        }
    }


    int getColumns() {
        return columns;
    }

    int getRows() {
        return rows;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getMaxColIndex() {
        return maxColIndex;
    }
}
