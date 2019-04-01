package ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListSizeExceptionTest {

    @Test
    void itemRangeExceptionTest(){
        Exception e = new ListSizeException();
        assertEquals( "Size of this list is too big!!" ,e.getMessage());
    }
}