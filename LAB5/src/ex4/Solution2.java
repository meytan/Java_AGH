package ex4;

import java.util.Arrays;

public class Solution2 {

    public static int[] solution (float[] arr, float target) {

        Arrays.sort(arr);
        int[] result = new int[]{0,arr.length-1};

        while(result[0]<result[1]){
            if (arr[result[0]]+arr[result[1]]==target)
                return result;
            else if(arr[result[0]]+arr[result[1]]<target)
                result[0]++;
            else
                result[1]--;
        }

        throw new ArithmeticException("There is no solution");



    }
}
