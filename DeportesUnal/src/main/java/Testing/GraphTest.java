
package Testing;
import DataTypes.Student;
import DataStructures.Graph;
import java.util.List;
import java.util.Set;


public class GraphTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Indirect Connections ===");
        Graph graph = new Graph();
        
        // Create students
        Student alice = new Student(1, "Alice");
        alice.addInterest("Basketball");
        
        Student bob = new Student(2, "Bob");
        bob.addSport("Basketball");  // Alice ↔ Bob (direct)
        bob.addInterest("Swimming");
        
        Student charlie = new Student(3, "Charlie");
        charlie.addSport("Swimming"); // Bob ↔ Charlie (direct)
        charlie.addInterest("Chess");
        
        Student topo = new Student(4, "Topo");
        topo.addSport("Chess");       // Charlie ↔ Topo (direct)
        
        // Build graph (add in order to see connections form)
        System.out.println("\nAdding Alice:");
        graph.addVertex(alice);
        printConnections(graph, alice);
        
        System.out.println("\nAdding Bob:");
        graph.addVertex(bob);  // Alice ↔ Bob
        printConnections(graph, alice);
        printConnections(graph, bob);
        
        System.out.println("\nAdding Charlie:");
        graph.addVertex(charlie); // Bob ↔ Charlie
        printConnections(graph, bob);
        printConnections(graph, charlie);
        
        System.out.println("\nAdding Topo:");
        graph.addVertex(topo);  // Charlie ↔ Topo
        printConnections(graph, charlie);
        printConnections(graph, topo);
        
        // Test indirect connections
        System.out.println("\n=== Indirect Connection Results ===");
        
        Set<Student> aliceConnections = graph.findAllConnectedStudents(alice);
        System.out.println("Alice's network (direct + indirect):");
        aliceConnections.forEach(s -> System.out.println("- " + s.getName())); 
        // Expected: Bob, Charlie, Topo
        
        System.out.println("\nIs Alice indirectly connected to Topo? " + 
            aliceConnections.contains(topo));  // true (Alice → Bob → Charlie → Topo)
    }
    
    private static void printConnections(Graph graph, Student student) {
        System.out.println(student.getName() + "'s direct connections:");
        List<Student> neighbors = graph.getNeighbors(student);
        if (neighbors.isEmpty()) {
            System.out.println("  None");
        } else {
            neighbors.forEach(n -> System.out.println("  - " + n.getName()));
        }
    }
}