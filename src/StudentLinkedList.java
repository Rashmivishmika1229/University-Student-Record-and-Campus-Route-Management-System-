public class StudentLinkedList {
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public boolean add(Student student) {
        if (student == null || search(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        return true;
    }

    public Student search(int studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId() == studentId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean update(int studentId, String name, String programme, double marks) {
        Student student = search(studentId);
        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);
        return true;
    }

    public Student delete(int studentId) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.data.getStudentId() == studentId) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.data;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    public void display() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("Total records: " + size);
    }

    public int size() {
        return size;
    }
}
