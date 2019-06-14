package task.tenth;

public class App {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<Integer, String>();
        map.put(13, "Hello");
        map.put(5, "Five");
        map.put(8, "Eight");
        map.put(6, "Six");
        map.put(4, "Four");
        map.put(2, "Two");
        map.put(1, "One");
        map.put(3, "Three");
        map.put(7, "Seven");
        map.put(16, "Sixteen");
        map.put(14, "Fourteen");

        for (int i = 0; i <16; i++) {
            System.out.println(i + "-" + map.get(i));
        }
    }
}
