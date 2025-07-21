package Testing;

import DataTypes.Student;
import DataStructures.Graph;
import java.util.List;

public class GraphTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Graph Connection ===");
        Graph graph = new Graph();
        
        // Create test students
        Student alice = new Student(1, "Alice");
        alice.addInterest("Basketball");
        alice.addSport("Chess");
        
        Student bob = new Student(2, "Bob");
        bob.addSport("Basketball"); // Matches Alice's interest
        bob.addInterest("Swimming");
        
        // Test 1: Add first student
        System.out.println("\n[Test 1] Adding Alice:");
        graph.addVertex(alice);
        printConnections(graph, alice);
        
        // Test 2: Add second student (should connect to Alice)
        System.out.println("\n[Test 2] Adding Bob:");
        graph.addVertex(bob);
        printConnections(graph, alice);
        printConnections(graph, bob);
        
        // Test 3: Try to connect student to themselves
        System.out.println("\n[Test 3] Attempting self-connection:");
        System.out.println("Before:");
        printConnections(graph, alice);
        
        System.out.println("Attempting to connect Alice to herself...");
        graph.addEdge(alice, alice); // Should be skipped due to 'continue'
        
        System.out.println("After:");
        printConnections(graph, alice); // Should be unchanged
    }
    
    private static void printConnections(Graph graph, Student student) {
        System.out.println(student.getName() + "'s connections:");
        List<Student> neighbors = graph.getNeighbors(student);
        
        if (neighbors.isEmpty()) {
            System.out.println("  None");
        } else {
            neighbors.forEach(neighbor -> 
                System.out.println("  - " + neighbor.getName()));
        }
    }
}