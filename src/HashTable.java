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

    public boolean put(Student student) {
        if (student == null) return false;

        int index = hash(student.getStudentId());
        Entry current = table[index];

        while (current != null) {
            if (current.key == student.getStudentId()) {
                current.value = student;
                return false;
            }
            current = current.next;
        }

        Entry entry = new Entry(student.getStudentId(), student);
        entry.next = table[index];
        table[index] = entry;
        return true;
    }
}