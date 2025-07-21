/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import DataTypes.Student;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;


/*
 I decided to use the adjacencyList implementation of the graph ADT because 
 of the complexity of the associated algorithms   
*/


public class Graph {

    //Atributes 
    private Map<Integer, Student> studentsIds;
    private Map<Student, List<Student>> adjacencyMap;
    
    
    //Constructor
    
    public Graph(){
        this.studentsIds = new HashMap<>();
        this.adjacencyMap = new HashMap<>();
        
    }
       

    //Methods
    
    /*We are taking the students themselves as vertex, so 
      I might as well call it addStudent
    */
    public void addVertex(Student student){
        
        studentsIds.putIfAbsent(student.getId(), student);
        adjacencyMap.putIfAbsent(student, new ArrayList<>());
        createEdgesForNewStudent(student);
    }
    
    
    
    public void removeVertex(Student student){
        if(!adjacencyMap.containsKey(student)){
            return;
        }
        
        for(Student neighbor : adjacencyMap.get(student)){
            adjacencyMap.get(neighbor).remove(student);
        }
        
        adjacencyMap.remove(student);
        
        studentsIds.remove(student.getId());
    }
    
    public void removeVertex(int studentId) {
       Student student = studentsIds.get(studentId);
        if (student != null) {
            removeVertex(student);
    }
}
    
    
    public void addEdge(Student student_a, Student student_b){
        
        if (student_a.equals(student_b)) {
            return; 
        }
        
        if(!adjacencyMap.containsKey(student_a) || !adjacencyMap.containsKey(student_b) ){
            throw new IllegalArgumentException("Both students must exist in the graph");
        }
        
        boolean theyShareSport = student_a.getSports().stream().anyMatch(sport -> student_b.getSports().contains(sport));
                   
  
        if(theyShareSport) {
            //We have an undirected graph, so add edge to both lists corresponding to the students
            adjacencyMap.get(student_a).add(student_b);
            adjacencyMap.get(student_b).add(student_a);            
        }
        
    
    }
    
    
    public boolean containsVertex(Student student) {
    return adjacencyMap.containsKey(student);
    }

     public boolean containsVertex(int studentId) {
        return studentsIds.containsKey(studentId);
    }

    
    public List<Student> getNeighbors(Student student) {
       return adjacencyMap.getOrDefault(student, new ArrayList<>());
    }
    
    public List<Student> getNeighbors(int studentId) {
        Student student = studentsIds.get(studentId);
        if (student == null) {
            return new ArrayList<>(); // Return empty list if student doesn't exist
        }
        return adjacencyMap.getOrDefault(student, new ArrayList<>());
    }


    public int getVertexCount() {
        return adjacencyMap.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Student> neighbors : adjacencyMap.values()) {
            count += neighbors.size();
        }
        return count / 2; // Since it's undirected
    }
    
    
    private void createEdgesForNewStudent(Student newStudent) {
     for (Student existingStudent : adjacencyMap.keySet()) {
        if (existingStudent.equals(newStudent)) continue;
        
        // Directly implement connection logic here to avoid recursion
        boolean shareSport = 
            newStudent.getSports().stream()
                .anyMatch(sport -> existingStudent.getSports().contains(sport));
          
        if (shareSport && !adjacencyMap.get(newStudent).contains(existingStudent)) {
            adjacencyMap.get(newStudent).add(existingStudent);
            adjacencyMap.get(existingStudent).add(newStudent);
            }
        }
    }
    
    // Graph traversing algorithms
   
    public Set<Student> findStudentsMatchingInterests(Student student) {
        Set<Student> matches = new HashSet<>();
        if (!adjacencyMap.containsKey(student) || student.getInterest().isEmpty()) {
            return matches;
        }

        Set<Student> visited = new HashSet<>();
        Queue<Student> queue = new LinkedList<>();
        queue.add(student);
        visited.add(student);

        while (!queue.isEmpty()) {
            Student current = queue.poll();

        // Verifica si el estudiante actual practica alguno de los intereses del estudiante original
            boolean isMatch = current != student && 
                current.getSports().stream()
                    .anyMatch(sport -> student.getInterest().contains(sport));
        
            if (isMatch) {
                matches.add(current); // ¡Encontrado!
            }

            // Continúa explorando el grafo
            for (Student neighbor : adjacencyMap.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return matches;
    }
    
}
