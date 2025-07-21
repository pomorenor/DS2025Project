package Testing;

import DataTypes.Student;
import DataStructures.Graph;

public class GraphManualTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Graph Implementation ===");
        
        // 1. Create a new graph
        Graph graph = new Graph();
        System.out.println("\nCreated empty graph.");
        
        // 2. Create test students with interests and sports
        Student alice = new Student(1, "Alice");
        alice.addInterest("Basketball"); // Alice is interested in Basketball
        alice.addSport("Chess");        // Alice plays Chess
        
        Student bob = new Student(2, "Bob");
        bob.addSport("Basketball");     // Bob plays Basketball
        bob.addSport("Swimming");       // Bob plays Swimming
        bob.addInterest("Tennis");      // Bob is interested in Tennis
        
        Student charlie = new Student(3, "Charlie");
        charlie.addSport("Tennis");    // Charlie plays Tennis
        charlie.addInterest("Chess");   // Charlie is interested in Chess
        
        Student dana = new Student(4, "Dana");
        dana.addSport("Football");      // Dana plays Football
        dana.addInterest("Swimming");   // Dana is interested in Swimming
        
        // 3. Add students to graph
        System.out.println("\nAdding students to graph...");
        graph.addVertex(alice);
        graph.addVertex(bob);
        graph.addVertex(charlie);
        graph.addVertex(dana);
        
        // 4. Verify connections
        System.out.println("\n=== Connection Verification ===");
        System.out.println("Total students: " + graph.getVertexCount());
        System.out.println("Total connections: " + graph.getEdgeCount());
        
        // Expected connections:
        // Alice (interested in Basketball) -> Bob (plays Basketball)
        // Bob (interested in Tennis) -> Charlie (plays Tennis)
        // Dana (interested in Swimming) -> Bob (plays Swimming)
        
        System.out.println("\nAlice's connections (should connect to Bob):");
        graph.printConnectedStudents(alice);
        
        System.out.println("\nBob's connections (should connect to Alice and Dana):");
        graph.printConnectedStudents(bob);
        
        System.out.println("\nCharlie's connections (should connect to Bob):");
        graph.printConnectedStudents(charlie);
        
        System.out.println("\nDana's connections (should connect to Bob):");
        graph.printConnectedStudents(dana);
        
        // 5. Test edge cases
        System.out.println("\n=== Edge Case Testing ===");
        
        // Student with no matches
        Student eve = new Student(5, "Eve");
        eve.addInterest("Volleyball");
        graph.addVertex(eve);
        System.out.println("\nEve's connections (should be empty):");
        graph.printConnectedStudents(eve);
        
        // Student interested in multiple sports
        Student frank = new Student(6, "Frank");
        frank.addInterest("Football");
        frank.addInterest("Tennis");
        graph.addVertex(frank);
        System.out.println("\nFrank's connections (should connect to Dana and Charlie):");
        graph.printConnectedStudents(frank);
        
        // 6. Test removal
        System.out.println("\n=== Testing Removal ===");
        System.out.println("Removing Bob...");
        graph.removeVertex(bob);
        
        System.out.println("\nAlice's connections after removal (should be empty):");
        graph.printConnectedStudents(alice);
        
        System.out.println("\nDana's connections after removal (should be empty):");
        graph.printConnectedStudents(dana);
        
        System.out.println("\nFinal student count: " + graph.getVertexCount());
        System.out.println("Final connection count: " + graph.getEdgeCount());
    }
}