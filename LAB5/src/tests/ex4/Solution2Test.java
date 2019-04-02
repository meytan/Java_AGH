package ex4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution2Test {

    @Test
    void solution_SortedTablePossibleTarget_Success() {
        float[] arr = {1, 2, 3, 7, 11, 15};
        float target = 9;
        int[] actual  = Solution2.solution(arr, target);
        int[] expected = {1,3};
        assertArrayEquals(expected,actual);
    }

    @Test
    void solution_NonSortedTablePossibleTarget_Success() {
        float[] arr = {2, 23, 11, 15,7};
        float target = 9;
        int[] actual  = Solution2.solution(arr, target);
        int[] expected = {0,4};
        assertArrayEquals(expected,actual);
    }

    @Test
    void solution_SortedTableNoPossibleTarget_ArithmeticExceptionThrown() {
        float[] arr = {2, 7, 11, 15,};
        float target = 8;
        assertThrows(ArithmeticException.class,() -> Solution2.solution(arr, target));
    }

    @Test
    void solution_NonSortedTableNoPossibleTarget_ArithmeticExceptionThrown() {
        float[] arr = {2, 23, 11, 15,7};
        float target = 8;
        assertThrows(ArithmeticException.class,() -> Solution2.solution(arr, target));
    }

    @Test
    void solution_EmptyArray_ArithmeticExceptionThrown() {
        float[] arr = {};
        float target = 8;
        assertThrows(ArithmeticException.class,() -> Solution2.solution(arr, target));
    }


}