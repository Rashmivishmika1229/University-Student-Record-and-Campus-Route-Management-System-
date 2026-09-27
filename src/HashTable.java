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

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        table = new Entry[capacity];
    }

    private int hash(int key) {
        return Math.floorMod(key, table.length);
    }
}