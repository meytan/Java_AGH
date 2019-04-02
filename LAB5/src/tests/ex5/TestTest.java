package ex5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {

    private int[] expectedArray = {1,2,3,4,5};
    private int[] noSoretedArray;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @BeforeEach
    void setUp() {
        noSoretedArray = new int[]{3, 2, 5, 1, 4};

    }

    @Test
    void gnomeSort() {
        ex5.Test.gnomeSort(noSoretedArray,5);
        assertArrayEquals(expectedArray, noSoretedArray);
    }

    @Test
    void cycleSort() {
        ex5.Test.cycleSort(noSoretedArray,5);
        assertArrayEquals(expectedArray,noSoretedArray);
    }

    @Test
    void cocktailSort() {
        ex5.Test.cocktailSort(noSoretedArray);
        assertArrayEquals(expectedArray,noSoretedArray);
    }

    @Test
    void bubbleSort() {
        ex5.Test.bubbleSort(noSoretedArray);
        assertArrayEquals(expectedArray,noSoretedArray);
    }


    @Test
    void quickkSort() {
        ex5.Test.quickkSort(noSoretedArray,0,4);
        assertArrayEquals(expectedArray,noSoretedArray);
    }

    @Test
    void printArray() {

        ex5.Test.printArray(expectedArray);
        assertEquals("1 2 3 4 5 \n", outContent.toString());
    }

    @Test
    void genereatePesymisticArray() {
        int[] expected = {3,2,1};
        int[] actual = ex5.Test.genereatePesymisticArray(3);
        assertArrayEquals(expected,actual);
    }

    @Test
    void genereateOptymisticArray() {
        int[] expected = {0,1,2};
        int[] actual = ex5.Test.genereateOptymisticArray(3);
        assertArrayEquals(expected,actual);
    }

    @Test
    void genereateRealisticArray() {
        int expected = 5;
        int actual = ex5.Test.genereateRealisticArray(5).length;
        assertEquals(expected,actual);
    }

    @Test
    void testQuickSort() {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);

        assertTimeout(Duration.ofMinutes(1),() ->
                result.set(ex5.Test.testQuickSort(ex5.Test.genereateRealisticArray(10000000))));
        if(!(result.get() > 0))
            fail(TestTest::message);

    }

    @Test
    void testBubbleSort() {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);

        assertTimeout(Duration.ofMinutes(1),() ->
                result.set(ex5.Test.testBubbleSort(ex5.Test.genereateRealisticArray(100000))));
        if(!(result.get() > 0))
            fail(TestTest::message);
    }

    @Test
    void testCycleSort() {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);

        assertTimeout(Duration.ofSeconds(20),() ->
                result.set(ex5.Test.testCycleSort(ex5.Test.genereateRealisticArray(10000))));
        if(!(result.get() > 0))
            fail(TestTest::message);

    }

    @Test
    void testGnomeSort() {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);

        assertTimeout(Duration.ofMinutes(1),() ->
                result.set(ex5.Test.testGnomeSort(ex5.Test.genereateRealisticArray(100000))));
        if(!(result.get() > 0))
            fail(TestTest::message);

    }

    @Test
    void testCoctailSort() {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);

        assertTimeout(Duration.ofMinutes(1),() ->
                result.set(ex5.Test.testCoctailSort(ex5.Test.genereateRealisticArray(10000))));
        if(!(result.get() > 0))
            fail(TestTest::message);

    }

    private static String message () {
        return "Test of sorting doesn't work";
    }
}