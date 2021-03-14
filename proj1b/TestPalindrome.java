import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("0"));
        assertTrue(palindrome.isPalindrome("1212121"));
        assertTrue(palindrome.isPalindrome("12122121"));
        assertFalse(palindrome.isPalindrome("01"));
        assertFalse(palindrome.isPalindrome("1212212"));
        assertFalse(palindrome.isPalindrome("12121212"));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("%lk&", cc));
        assertFalse(palindrome.isPalindrome("asdfg", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("!", cc));
        assertFalse(palindrome.isPalindrome("cv", cc));
    }
}
