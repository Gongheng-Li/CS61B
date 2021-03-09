public class ArrayDequeTest{

    public static boolean checkEmpty(boolean expected, boolean actual){
        if(expected != actual){
            System.out.println("isEmpty() expected: " + expected + ", but got: " + actual);
            return false;
        }
        return true;
    }

    public static boolean checkSize(int expected, int actual){
        if(expected != actual){
            System.out.println("size() expected: " + expected + ", but got: " + actual);
            return false;
        }
        return true;
    }

    public static void printTestStatus(boolean passed){
        if(passed)
            System.out.println("Test passed!");
        else{
            System.out.println("Test failed.");
        }
    }

    public static void addRemoveIsEmptySizeTest(){
        System.out.println("Running add/remove/size/empty test.");
        ArrayDeque<Integer> testArray = new ArrayDeque<>();
        boolean passed = checkEmpty(true, testArray.isEmpty());
        testArray.addFirst(4);
        testArray.addFirst(3);
        testArray.addLast(5);
        passed = checkSize(3, testArray.size()) && passed;
        testArray.addLast(6);
        testArray.removeFirst();
        testArray.removeLast();
        passed = checkSize(2, testArray.size()) && passed;
        printTestStatus(passed);
    }

    public static void getPrintTest(){
        System.out.println("Running get/print test.");
        ArrayDeque<String> testArray = new ArrayDeque<>();
        testArray.addLast("still");
        testArray.addLast("raining");
        testArray.addLast("outside!");
        testArray.addFirst("is");
        testArray.addFirst("It");
        boolean passed = (testArray.get(1).equals("is"));
        passed = (testArray.get(3).equals("raining")) && passed;
        testArray.printDeque();
        printTestStatus(passed);
    }

    public static void autoResizeTest(){
        System.out.println("Running auto resize test");
        ArrayDeque<Integer> testArray = new ArrayDeque<>();
        for(int i=0; i <= 16; i++){
            testArray.addLast(1);
        }
        for(int i=0; i < 16; i++){
            testArray.addFirst(1);
        }
        for(int i=0; i < 18; i++){
            testArray.removeLast();
        }
        for(int i=0; i < 8; i++){
            testArray.removeFirst();
        }
    }

    public static void main(String[] args){
        System.out.println("Running tests.");
        addRemoveIsEmptySizeTest();
        getPrintTest();
        autoResizeTest();
    }
}
