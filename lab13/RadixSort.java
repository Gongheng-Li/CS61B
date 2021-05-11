/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int maxLength = 0;
        int i = 0;
        String[] sorted = new String[asciis.length];
        for (String str : asciis) {
            if (str.length() > maxLength) {
                maxLength = str.length();
            }
            sorted[i] = str;
            i += 1;
        }
        for (int index = maxLength - 1; index >= 0; index--) {
            sortHelperLSD(sorted, index);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[257];
        for (String str : asciis) {
            if (str.length() <= index) {
                counts[0] += 1;
            } else {
                counts[str.charAt(index) + 1] += 1;
            }
        }
        int[] starts = new int[257];
        for (int i = 1; i < 257; i++) {
            starts[i] = starts[i - 1] + counts[i - 1];
        }
        String[] sorted = new String[asciis.length];
        for (String str : asciis) {
            if (str.length() <= index) {
                sorted[starts[0]] = str;
                starts[0] += 1;
            } else {
                sorted[starts[str.charAt(index) + 1]] = str;
                starts[str.charAt(index) + 1] += 1;
            }
        }
        System.arraycopy(sorted, 0, asciis, 0, asciis.length);
    }

    public static String[] MSDSort(String[] ascii) {
        String[] sorted = new String[ascii.length];
        System.arraycopy(ascii, 0, sorted, 0, ascii.length);
        sortHelperMSD(sorted, 0, sorted.length, 0);
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        if (end - start <= 1) {
            return;
        }
        int maxLength = 0;
        for (int i = start; i < end; i++) {
            if (asciis[i].length() > maxLength) {
                maxLength = asciis[i].length();
            }
        }
        if (maxLength <= index) {
            return;
        }
        int[] counts = new int[257];
        for (int i = start; i < end; i++) {
            if (asciis[i].length() <= index) {
                counts[0] += 1;
            } else {
                counts[asciis[i].charAt(index) + 1] += 1;
            }
        }
        int[] starts = new int[257];
        for (int i = 1; i < 257; i++) {
            starts[i] = starts[i - 1] + counts[i - 1];
        }
        String[] sorted = new String[end - start];
        for (int i = start; i < end; i++) {
            if (asciis[i].length() <= index) {
                sorted[starts[0]] = asciis[i];
                starts[0] += 1;
            } else {
                sorted[starts[asciis[i].charAt(index) + 1]] = asciis[i];
                starts[asciis[i].charAt(index) + 1] += 1;
            }
        }
        for (int i = 0; i < 257; i++) {
            sortHelperMSD(sorted, starts[i] - counts[i], starts[i], index + 1);
        }
        System.arraycopy(sorted, 0, asciis, start, sorted.length);
    }

    public static void main(String[] args) {
        String[] testLSDRadixSort = new String[]{"a1b2c3", "ab123c", "ab", "12c3", "ca2b13", "b2c13", "ac2b", "2abc3",
                                                 "b2c31", "12c3", "ca2b1", "a2b1c", "b2ac", "1cab3", "21bc", "ab23c"};
        String[] sorted = MSDSort(testLSDRadixSort);
        for (String str : testLSDRadixSort) {
            System.out.print(str + " ");
        }
        System.out.println();
        for (String str : sorted) {
            System.out.print(str + " ");
        }
    }
}
