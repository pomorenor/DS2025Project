package Benchmarking;

import DataStructures.hashmap;
import DataTypes.Student;

public class HashmapBenchmark {
    public static void main(String[] args) {

        String operation1 = "insert";
        String operation2 = "get";

        //Test insert function
        System.out.println(runTime(operation1,100, 100));
        System.out.println(runTime(operation1,100, 1000));
        System.out.println(runTime(operation1,100, 10_000));
        System.out.println(runTime(operation1,100, 100_000));
        System.out.println(runTime(operation1,100, 1_000_000));
        System.out.println(runTime(operation1,100, 10_000_000));

        //Test get function
        System.out.println(runTime(operation2,100, 100));
        System.out.println(runTime(operation2,100, 1000));
        System.out.println(runTime(operation2,100, 10_000));
        System.out.println(runTime(operation2,100, 100_000));
        System.out.println(runTime(operation2,100, 1_000_000));
        System.out.println(runTime(operation2,100, 10_000_000));


       
    }
    // Run benchmark capacity and number of elements
    public static String runTime(String operation, int capacity, int numElements){
        hashmap<Student> Testmap = new hashmap<>(capacity);
        long totalTime = 0;
        int runs = 20;
        //Does 20 runs and averages them out
        for (int i = 0; i < runs; i++) {
            long start = System.nanoTime();

                if (operation.equals("insert")) {
                    Testmap = new hashmap<>(capacity); // reset for each run
                    for (int j = 0; j < numElements; j++) {
                        Student student = new Student(j, "Student" + j);
                        Testmap.insertStudent(student);
                    }
                } else if (operation.equals("get")) {
                    for (int j = 0; j < numElements; j++) {
                        Testmap.getStudent(j);
                    }
                }
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        long duration = (totalTime/runs) / 1000; 
        String result = operation + ": " + numElements + " students in " + duration + "µs";
        return result;
    }
    
    
}
