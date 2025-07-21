
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
        alice.addSport("Swimming");  // Alice ↔ Bob (direct)
        alice.addInterest("Chess");
        
        Student bob = new Student(2, "Bob");
        bob.addSport("Swimming");  // Alice ↔ Bob (direct)
        bob.addSport("Soccer");  // Alice ↔ Bob (direct)
        bob.addSport("Chess");

        
        Student pablo = new Student(3, "Pablo");
        pablo.addSport("Soccer");  // Alice ↔ Bob (direct)
        
        Student topo = new Student(4, "topo");
        topo.addSport("Soccer");
        topo.addSport("Chess");
        
        Student jesus = new Student(5, "jesus");
        jesus.addSport("Soccer");
        jesus.addSport("Chess");
        
        graph.addVertex(alice);  // Alice ↔ Bob
        graph.addVertex(bob);  // Alice ↔ Bob
        graph.addVertex(pablo);  // Alice ↔ Bob
        graph.addVertex(topo);  // Alice ↔ Bob
        graph.addVertex(jesus);  // Alice ↔ Bob

        
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