package ex1;

import java.util.ArrayList;
import java.util.List;

public class MatrixAddition {


    public static <T> Matrix<T> addMat(Matrix<T> matrix1, Matrix<T> matrix2) {
        if (matrix1.getColumns() == matrix2.getColumns() && matrix1.getRows() == matrix2.getRows()) {

            T a = matrix1.matrix.get(0).get(0);
            List<List<T>> result = new ArrayList<List<T>>();;
            List<T> tmp = new ArrayList<T>();
            int flag = 0;
            if(!(a.getClass() == Integer.class || a.getClass() == String.class || a.getClass() == Double.class || a.getClass() == Long.class) )
                throw new IllegalArgumentException("This type is not supported");

            while (matrix1.iterator().hasNext() && matrix2.iterator().hasNext()) {

                if(a.getClass() == Integer.class){
                    tmp.add( (T) (Integer) ((Integer)matrix1.iterator().next() + (Integer) matrix2.iterator().next()));

                }
                else if(a.getClass() == Long.class){
                    tmp.add( (T) (Long) ((Long)matrix1.iterator().next() + (Long) matrix2.iterator().next()));

                }
                else if(a.getClass() == Double.class){
                    tmp.add( (T) (Double) ((Double)matrix1.iterator().next() + (Double) matrix2.iterator().next()));

                }
                else if(a.getClass() == Float.class){
                    tmp.add( (T) (Float) ((Float)matrix1.iterator().next() + (Float) matrix2.iterator().next()));

                }
                else if(a.getClass() == String.class){
                    tmp.add( (T) (String) ((String)matrix1.iterator().next() + (String) matrix2.iterator().next()));

                }
                flag++;
                if(flag == matrix1.getColumns() ){
                    result.add(tmp);
                    flag=0;
                    tmp= new ArrayList<T>();
                }
            }

            return new Matrix<T>(result);
        } else
            throw new IllegalArgumentException("This matrix can't be add up");

    }


}
