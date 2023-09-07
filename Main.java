package ex9;

public class Main {
    public static void main(String[] args) {
        Heap<String> test = new Heap<>(String.class,100);
        test.add("E");
        test.add("A");
        test.add("G");
        test.add("K");
        test.add("B");
        test.add("D");
        test.add("F");
        test.add("T");
        test.add("S");
        test.showElements();
        test.remove("F");
        test.showElements();
    }
}
