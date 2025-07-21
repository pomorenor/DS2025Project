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
        
        if(!adjacencyMap.containsKey(student_a) || !adjacencyMap.containsKey(student_b) ){
            throw new IllegalArgumentException("Both students must exist in the graph");
        }
        
        boolean theyShareSport = student_a.getInterest().stream().anyMatch(sport -> student_b.getSports().contains(sport));   
    
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
        // Skip self and already connected students
        if (existingStudent.equals(newStudent)) continue;
        
        // Check if they share any sports
        boolean shareSports = newStudent.getInterest().stream()
            .anyMatch(sport -> existingStudent.getSports().contains(sport));
        
        if (shareSports) {
            // Add bidirectional edge
            adjacencyMap.get(newStudent).add(existingStudent);
            adjacencyMap.get(existingStudent).add(newStudent);
            }
        }
    }
    
    
    /**
 * Gets and prints information about all neighbors of a student
 * @param studentId The ID of the student to examine
 */
public void printConnectedStudents(int studentId) {
    Student student = studentsIds.get(studentId);
    if (student == null) {
        System.out.println("Student with ID " + studentId + " not found in graph.");
        return;
    }

    List<Student> neighbors = adjacencyMap.get(student);
    if (neighbors.isEmpty()) {
        System.out.println(student.getName() + " (ID: " + studentId + ") has no connections.");
        return;
    }

    System.out.println("\nConnection report for: " + student.getName() + " (ID: " + studentId + ")");
    System.out.println("Shared sports: " + String.join(", ", student.getSports()));
    System.out.println("Connected to " + neighbors.size() + " student(s):\n");

    for (Student neighbor : neighbors) {
        // Find shared sports between the student and neighbor
        List<String> sharedSports = student.getSports().stream()
                .filter(sport -> neighbor.getSports().contains(sport))
                .collect(Collectors.toList());

        System.out.println("• " + neighbor.getName() + " (ID: " + neighbor.getId() + ")");
        System.out.println("  Shared sports: " + String.join(", ", sharedSports));
        System.out.println("  All sports: " + String.join(", ", neighbor.getSports()));
        System.out.println();
    }
}

/**
 * Overloaded version that accepts Student object directly
 */
    public void printConnectedStudents(Student student) {
        if (student == null) {
            System.out.println("Student cannot be null.");
            return;
        }
        printConnectedStudents(student.getId());
    }
    
}
