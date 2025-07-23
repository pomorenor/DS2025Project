package Benchmarking;

import DataTypes.Student;
import DataStructures.Graph;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GraphBenchmark {
    private static final String[] SPORTS = {
        "Football", "Basketball", "Tennis", "Swimming", "Volleyball",
        "Baseball", "Soccer", "Golf", "Running", "Cycling"
    };
    
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        benchmarkAllOperations();
    }
    
    public static void benchmarkAllOperations() {
        System.out.println("Comprehensive Graph Operation Benchmarks");
        System.out.println("Students\tAddVertex\tAddVertexNoUp\tRemoveVertex\tContainsV\tGetNeighbors\tAddEdge\t\tFindMatches");
        
        int[] studentCounts = {10, 50, 100, 200, 500, 1000, 2000, 3000, 4500};
        
        for (int count : studentCounts) {
            // Generate test data
            List<Student> students = generateTestStudents(count);
            Graph graph = new Graph();
            
            // Benchmark all operations
            long addTime = benchmarkAddVertex(graph, students);
            long addNoUpdateTime = benchmarkAddVertexNoUpdate(graph, students);
            long containsTime = benchmarkContainsVertex(graph, students);
            long neighborsTime = benchmarkGetNeighbors(graph, students);
            long addEdgeTime = benchmarkAddEdge(graph, students);
            long findMatchesTime = benchmarkFindMatches(graph, students.get(0));
            long removeTime = benchmarkRemoveVertex(graph, students);
            
            System.out.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d%n",
                count, addTime, addNoUpdateTime, removeTime, containsTime, 
                neighborsTime, addEdgeTime, findMatchesTime);
        }
    }
    
    // New benchmark method for addVertexNoUpdate
    private static long benchmarkAddVertexNoUpdate(Graph graph, List<Student> students) {
        long start = System.nanoTime();
        for (Student s : students) {
            graph.addVertexNoUpdate(s);
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }
    
    // Existing benchmark methods remain unchanged
    private static long benchmarkAddVertex(Graph graph, List<Student> students) {
        long start = System.nanoTime();
        for (Student s : students) {
            graph.addVertex(s);
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }
    
    private static long benchmarkRemoveVertex(Graph graph, List<Student> students) {
        long start = System.nanoTime();
        for (Student s : students) {
            graph.removeVertex(s);
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }
    
    private static long benchmarkContainsVertex(Graph graph, List<Student> students) {
        // Test both contains methods
        long start = System.nanoTime();
        for (Student s : students) {
            graph.containsVertex(s);
            graph.containsVertex(s.getId());
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start) / (students.size() * 2);
    }
    
    private static long benchmarkGetNeighbors(Graph graph, List<Student> students) {
        long start = System.nanoTime();
        for (Student s : students) {
            graph.getNeighbors(s);
            graph.getNeighbors(s.getId());
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start) / (students.size() * 2);
    }
    
    private static long benchmarkAddEdge(Graph graph, List<Student> students) {
        // Add edges between random pairs
        long start = System.nanoTime();
        for (int i = 0; i < students.size() / 2; i++) {
            Student a = students.get(random.nextInt(students.size()));
            Student b = students.get(random.nextInt(students.size()));
            try {
                graph.addEdge(a, b);
            } catch (IllegalArgumentException e) {
                // Skip if vertices don't exist (shouldn't happen here)
            }
        }
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }
    
    private static long benchmarkFindMatches(Graph graph, Student searchStudent) {
        // Warm up
        graph.findStudentsMatchingInterests(searchStudent);
        
        long start = System.nanoTime();
        graph.findStudentsMatchingInterests(searchStudent);
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }
    
    private static List<Student> generateTestStudents(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Student s = new Student(i, "Student " + i);
            
            // Add 1-3 random sports
            int sports = 1 + random.nextInt(3);
            for (int j = 0; j < sports; j++) {
                s.addSport(SPORTS[random.nextInt(SPORTS.length)]);
            }
            
            // Add 1-2 interests
            int interests = 1 + random.nextInt(2);
            for (int j = 0; j < interests; j++) {
                s.addInterest(SPORTS[random.nextInt(SPORTS.length)]);
            }
            
            students.add(s);
        }
        return students;
    }
}