public class HashTable {
    private static class Entry {
        int key;
        Student value;
        Entry next;

        Entry(int key, Student value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Entry[] table;
}