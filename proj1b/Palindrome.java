public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> lst = new LinkedListDeque<>();
        for (int i=0; i < word.length(); i++) {
            lst.addLast(word.charAt(i));
        }
        return lst;
    }

    private boolean isDequePalindrome(Deque<Character> lst) {
        if(lst.size() <= 1) {
            return true;
        }
        char first = lst.removeFirst();
        char last = lst.removeLast();
        return (first == last) && isDequePalindrome(lst);
    }

    public boolean isPalindrome(String word) {
        return isDequePalindrome(wordToDeque(word));
    }

    private boolean isDequePalindrome(Deque<Character> lst, CharacterComparator cc) {
        if(lst.size() <= 1) {
            return true;
        }
        char first = lst.removeFirst();
        char last = lst.removeLast();
        return cc.equalChars(first, last) && isDequePalindrome(lst, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isDequePalindrome(wordToDeque(word), cc);
    }
}