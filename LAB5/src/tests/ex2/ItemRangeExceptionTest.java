package ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRangeExceptionTest {

    @Test
    void itemRangeExceptionTest(){
        Exception e = new ItemRangeException();
        assertEquals("Some element on this list is out of range!",e.getMessage());
    }
}