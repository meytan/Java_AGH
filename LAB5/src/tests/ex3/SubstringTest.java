package ex3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstringTest {

    @Test
    void substring_FirstArgumentEmpty_IllegalArgumentExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Substring.substring("", "asdfasd"));
    }

    @Test
    void substring_BothArgumentEmpty_IllegalArgumentExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Substring.substring("", ""));
    }

    @Test
    void substring_SecondArgumentEmpty_FailOfSearching() {
        assertEquals(-1, Substring.substring("asd", ""));
    }

    @Test
    void substring_IsSubstring_NumberOfRepeating() {
        assertEquals(3, Substring.substring("abcd", "cdabcdab"));
        assertEquals(1, Substring.substring("abcd", "abcd"));
        assertEquals(1, Substring.substring("a", "a"));
        assertEquals(59, Substring.substring("a", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

    }

    @Test
    void substring_IsNotSubstring_FailOfSearching() {
        assertEquals(-1, Substring.substring("abasd", "cdabcasdfasdaasdfjhlkb"));
        assertEquals(-1, Substring.substring("a", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"));
    }


}