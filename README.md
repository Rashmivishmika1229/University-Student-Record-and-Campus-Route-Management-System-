# Student Records and Campus Route Management System

## Introduction

The Student Records and Campus Route Management System is a Java console application developed for the CIT300 Data Structures and Algorithms Graded Practical Assignment 1. It provides a simple way to manage student records and service requests, while also modelling campus locations and their direct connections. The project demonstrates how core data structures can be applied to common university tasks through a menu-driven interface with input validation.

## Features and Data Structures

- **Student records:** add, update, delete, search, and display records containing a student ID, name, programme, and marks.
- **Linked list:** stores and manages student records.
- **Stack:** records recent system actions.
- **Queue:** processes student service requests in arrival order.
- **Binary search tree (BST):** organizes and displays students by student ID.
- **Hash table:** supports student ID lookup.
- **Campus graph:** models campus locations and roads with an adjacency list, and supports adding/removing locations and connections.
- **Graph traversal:** explores the campus network using breadth-first search (BFS) and depth-first search (DFS).
- **Input validation:** checks menu choices, required text, student IDs, and marks.

## Project Structure

```text
src/
├── Main.java
├── Student.java
├── StudentLinkedList.java
├── ActionStack.java
├── ServiceRequestQueue.java
├── BST.java
├── HashTable.java
├── CampusLocation.java
└── CampusGraph.java
```

## How to Run

From the project root:

```bash
javac -d out src/*.java
java -cp out Main
```

Alternatively, run `Main.java` using the Java extension in VS Code.

## Group Members and Responsibilities

| Group Member | Student ID | Assigned Responsibility | Contribution |
|---|---|---|---|
| K. A. S. N. Kodithuwakku | `23DA2-0327-K.A.S.N-Kodithuwakku` | BST implementation and hashing/search functionality | Implemented the BST and hash table components for organizing and searching student records. |
| M. V. B. M. P. Senaviratna | `23DA2-0030-M.V.B.M.P.Senaviratna` | Stack and queue implementation and related operations | Implemented the stack for recent actions and the queue for student service requests. |
| Rashmi Kodithuwakku (K. A. R. V. Kodithuwakku) | `23DA2-0370-K.A.R.V-Kodithuwakku` | Graph implementation, campus locations, connections, and BFS/DFS traversal | Implemented the campus graph using an adjacency list, including operations to add and remove locations and connections, display the network, and traverse it using BFS and DFS. |
| U. G. Damith | `23DA2-0075-U.G.Damith` | Linked list implementation and student-record management | Implemented the linked list and student-record management operations. |

All members contributed to integration, validation, documentation, GitHub collaboration, and completion of the project. K. A. S. N. Kodithuwakku and K. A. R. V. Kodithuwakku worked on integration and validation. M. V. B. M. P. Senaviratna and U. G. Damith contributed to testing and debugging.

## GitHub Collaboration

The project was managed using GitHub to track development and member contributions. Each member used a separate branch to develop their assigned components and made meaningful commits to show their individual contributions. After all components were completed, the final `Main.java` was added to the main branch to integrate the complete system.

## Demonstration

The project demonstration video presents the completed system and each member's contribution. The video is prepared as a separate submission deliverable.
