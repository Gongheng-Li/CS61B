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
        if(passed){
            System.out.println("Test passed!");
        }
        else{
            System.out.println("Test failed.");
        }
    }

    public static void addRemoveIsEmptySizeTest(){
        System.out.println("Running add/remove/size/empty test.");
        ArrayDeque<Integer> TestArray = new ArrayDeque<>();
        boolean passed = checkEmpty(true, TestArray.isEmpty());
        TestArray.addFirst(4);
        TestArray.addFirst(3);
        TestArray.addLast(5);
        passed = checkSize(3, TestArray.size()) && passed;
        TestArray.addLast(6);
        TestArray.removeFirst();
        TestArray.removeLast();
        passed = checkSize(2, TestArray.size()) && passed;
        printTestStatus(passed);
    }

    public static void getPrintTest(){
        System.out.println("Running get/print test.");
        ArrayDeque<String> TestArray = new ArrayDeque<>();
        TestArray.addLast("still");
        TestArray.addLast("raining");
        TestArray.addLast("outside!");
        TestArray.addFirst("is");
        TestArray.addFirst("It");
        boolean passed = (TestArray.get(1).equals("is"));
        passed = (TestArray.get(3).equals("raining")) && passed;
        TestArray.printDeque();
        printTestStatus(passed);
    }

    public static void autoResizeTest(){
        System.out.println("Running auto resize test");
        ArrayDeque<Integer> TestArray = new ArrayDeque<>();
        for(int i=0; i <= 16; i++){
            TestArray.addLast(1);
        }
        for(int i=0; i < 16; i++){
            TestArray.addFirst(1);
        }
        for(int i=0; i < 18; i++){
            TestArray.removeLast();
        }
        for(int i=0; i < 8; i++){
            TestArray.removeFirst();
        }
    }

    public static void main(String[] args){
        System.out.println("Running tests.");
        addRemoveIsEmptySizeTest();
        getPrintTest();
        autoResizeTest();
    }
}