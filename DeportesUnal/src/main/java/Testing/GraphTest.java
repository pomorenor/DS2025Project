
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
        
        alice.addSport("Basketball");  // Alice ↔ Bob (direct)
        alice.addInterest("Soccer");
        
        Student bob = new Student(2, "Bob");
        bob.addSport("Basketball");  // Alice ↔ Bob (direct)
        bob.addSport("Soccer");  // Alice ↔ Bob (direct)

        bob.addInterest("Swimming");
        
        Student pablo = new Student(3, "Pablo");
        pablo.addSport("Soccer");  // Alice ↔ Bob (direct)
        bob.addInterest("Swimming");
        
        graph.addVertex(alice);  // Alice ↔ Bob

        graph.addVertex(bob);  // Alice ↔ Bob

        graph.addVertex(pablo);  // Alice ↔ Bob

        printConnections(graph, alice);
        printConnections(graph, bob);

       
    
        
 
        // Test indirect connections
        System.out.println("\n=== Indirect Connection Results ===");
        
        Set<Student> aliceConnections = graph.findStudentsMatchingInterests(alice);
        System.out.println("alice's network (direct + indirect):");
        aliceConnections.forEach(s -> System.out.println("- " + s.getName())); 
        // Expected: Bob, Charlie, Topo
      
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