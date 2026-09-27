import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentLinkedList studentList = new StudentLinkedList();
    private static final ActionStack actionStack = new ActionStack();
    private static final ServiceRequestQueue serviceQueue = new ServiceRequestQueue();
    private static final BST bst = new BST();
    private static final HashTable hashTable = new HashTable(101);
    private static final CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {
        seedDemoData();

        boolean running = true;

        System.out.println("==============================================");
        System.out.println(" UNIVERSITY STUDENT & CAMPUS ROUTE SYSTEM");
        System.out.println("==============================================");

        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> studentList.display();
                case 5 -> addServiceRequest();
                case 6 -> processServiceRequest();
                case 7 -> actionStack.display();
                case 8 -> bst.displayInOrder();
                case 9 -> searchUsingHashing();
                case 10 -> addCampusLocation();
                case 11 -> removeCampusLocation();
                case 12 -> addCampusConnection();
                case 13 -> removeCampusConnection();
                case 14 -> campusGraph.displayConnections();
                case 15 -> traverseCampus();
                case 16 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid menu option. Please choose 1-16.");
            }

            if (running) {
                System.out.println();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("--------------- MAIN MENU ----------------");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
        System.out.println("------------------------------------------");
    }

    private static void addStudent() {
        int id = readPositiveInt("Enter Student ID: ");

        if (studentList.search(id) != null) {
            System.out.println("Duplicate Student ID. Record was not added.");
            return;
        }

        String name = readRequiredText("Enter Name: ");
        String programme = readRequiredText("Enter Programme: ");
        double marks = readMarks();

        Student student = new Student(id, name, programme, marks);

        studentList.add(student);
        bst.insert(student);
        hashTable.put(student);

        actionStack.push("Added student " + id + " (" + name + ")");

        System.out.println("Student record added successfully.");
    }

    private static void updateStudent() {
        int id = readPositiveInt("Enter Student ID to update: ");
        Student student = studentList.search(id);

        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        String name = readRequiredText("Enter new Name: ");
        String programme = readRequiredText("Enter new Programme: ");
        double marks = readMarks();

        studentList.update(id, name, programme, marks);

        // BST and hash table reference the same Student object,
        // so their stored object reflects the update automatically.
        actionStack.push("Updated student " + id);

        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudent() {
        int id = readPositiveInt("Enter Student ID to delete: ");

        Student deleted = studentList.delete(id);

        if (deleted == null) {
            System.out.println("Student record not found.");
            return;
        }

        hashTable.remove(id);
        // The simple BST implementation intentionally provides insertion/search
        // for demonstration. The linked-list record is the source for deletion.
        actionStack.push("Deleted student " + id + " (" + deleted.getName() + ")");

        System.out.println("Student record deleted successfully.");
    }

    private static void addServiceRequest() {
        String request = readRequiredText("Enter service request: ");
        serviceQueue.addRequest(request);
        actionStack.push("Added service request: " + request);
        System.out.println("Service request added to queue.");
    }

    private static void processServiceRequest() {
        String request = serviceQueue.processNext();

        if (request == null) {
            System.out.println("No service requests are waiting.");
            return;
        }

        actionStack.push("Processed service request: " + request);
        System.out.println("Processed request: " + request);
    }

    private static void searchUsingHashing() {
        int id = readPositiveInt("Enter Student ID to search: ");
        Student student = hashTable.get(id);

        if (student == null) {
            System.out.println("Student not found in hash table.");
        } else {
            System.out.println("Student found:");
            System.out.println(student);
        }
    }

    private static void addCampusLocation() {
        String location = readRequiredText("Enter campus location name: ");

        if (campusGraph.addLocation(location)) {
            actionStack.push("Added campus location: " + location);
            System.out.println("Campus location added.");
        } else {
            System.out.println("Location already exists or input is invalid.");
        }
    }

    private static void removeCampusLocation() {
        String location = readRequiredText("Enter campus location to remove: ");

        if (campusGraph.removeLocation(location)) {
            actionStack.push("Removed campus location: " + location);
            System.out.println("Campus location removed.");
        } else {
            System.out.println("Campus location not found.");
        }
    }

    private static void addCampusConnection() {
        String from = readRequiredText("Enter first location: ");
        String to = readRequiredText("Enter second location: ");

        if (campusGraph.addConnection(from, to)) {
            actionStack.push("Added connection: " + from + " <-> " + to);
            System.out.println("Campus connection added.");
        } else {
            System.out.println("Unable to add connection. Check that both locations exist, are different, and are valid.");
        }
    }

    private static void removeCampusConnection() {
        String from = readRequiredText("Enter first location: ");
        String to = readRequiredText("Enter second location: ");

        if (campusGraph.removeConnection(from, to)) {
            actionStack.push("Removed connection: " + from + " <-> " + to);
            System.out.println("Campus connection removed.");
        } else {
            System.out.println("Connection was not found.");
        }
    }

    private static void traverseCampus() {
        String start = readRequiredText("Enter starting campus location: ");

        System.out.println("1. BFS");
        System.out.println("2. DFS");
        int choice = readInt("Choose traversal: ");

        if (choice == 1) {
            campusGraph.bfs(start);
        } else if (choice == 2) {
            campusGraph.dfs(start);
        } else {
            System.out.println("Invalid traversal choice.");
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a positive whole number.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readMarks() {
        while (true) {
            System.out.print("Enter Marks (0-100): ");
            String input = scanner.nextLine().trim();

            try {
                double marks = Double.parseDouble(input);

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter a number.");
            }
        }
    }

    private static String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    private static void seedDemoData() {
        // Demo data makes it easier to demonstrate the data structures.
        Student s1 = new Student(1001, "Nimal Perera", "Computer Science", 78.5);
        Student s2 = new Student(1002, "Kavindu Silva", "Software Engineering", 84.0);
        Student s3 = new Student(1003, "Ayesha Fernando", "Information Technology", 91.5);

        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);

        bst.insert(s1);
        bst.insert(s2);
        bst.insert(s3);

        hashTable.put(s1);
        hashTable.put(s2);
        hashTable.put(s3);

        campusGraph.addLocation("Main Gate");
        campusGraph.addLocation("Library");
        campusGraph.addLocation("Engineering Block");
        campusGraph.addLocation("Cafeteria");
        campusGraph.addLocation("Sports Complex");

        campusGraph.addConnection("Main Gate", "Library");
        campusGraph.addConnection("Library", "Engineering Block");
        campusGraph.addConnection("Library", "Cafeteria");
        campusGraph.addConnection("Engineering Block", "Sports Complex");
    }
}
