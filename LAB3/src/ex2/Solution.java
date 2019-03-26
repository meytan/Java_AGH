package ex2;

import java.util.List;

public class Solution {

    public static int solution(List<Integer> a) throws ListSizeExcpetion, ItemRangeException {

        if(a.size()>(int) 1E5) throw new ListSizeExcpetion();
        if(a.stream().anyMatch(q -> q > 100000)) throw new ItemRangeException();
        if(a.stream().anyMatch(q -> q < -100000)) throw new ItemRangeException();


        for (int i=1;true;i++){
            if(!a.contains(i)){
                return i;
            }
        }


    }

}
