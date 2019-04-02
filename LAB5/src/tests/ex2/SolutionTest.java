package ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    List<Integer> list1;
    List<Integer> list2;

    @BeforeEach
    void setup() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    @Test
    void solution() throws ItemRangeException, ListSizeException {

        for(int i = 0; i<100000 ; i++){
            list1.add(i);
        }

        assertEquals(100000,Solution.solution(list1));
        assertEquals(1,Solution.solution(list2));

    }

    @Test
    void expectedItemRangeException(){
        for(int i = 2; i<100002 ; i++){
            list1.add(i);
        }
        for(int i = -2; i>-100002 ; i--){
            list2.add(i);
        }
        assertThrows(ItemRangeException.class,() ->Solution.solution(list1));
        assertThrows(ItemRangeException.class,() ->Solution.solution(list2));

    }
    @Test
    void expectedListSizeException(){
        for(int i = 0; i<100001 ; i++){
            list1.add(i);
        }
        assertThrows(ListSizeException.class,() ->Solution.solution(list1));

    }
}