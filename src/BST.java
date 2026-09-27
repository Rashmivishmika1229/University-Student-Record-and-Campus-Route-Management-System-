public class BST {
    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public boolean insert(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            return true;
        }

        Node current = root;

        while (true) {
            int id = student.getStudentId();

            if (id == current.student.getStudentId()) {
                return false;
            }

            if (id < current.student.getStudentId()) {
                if (current.left == null) {
                    current.left = new Node(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    return true;
                }
                current = current.right;
            }
        }
    }
        public Student search(int studentId) {
        Node current = root;

        while (current != null) {
            if (studentId == current.student.getStudentId()) {
                return current.student;
            }

            current = studentId < current.student.getStudentId()
                    ? current.left
                    : current.right;
        }

        return null;
    }
}