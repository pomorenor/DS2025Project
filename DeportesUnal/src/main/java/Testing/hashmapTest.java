package Testing;

import DataStructures.hashmap;
import DataTypes.Student;

public class hashmapTest {
     public static void main(String[] args) {
        System.out.println("Inserting students");
        hashmap<Student> Students = new hashmap<Student>(8);
        
        // Create students
        Student alice = new Student(1, "Alice");
        Student bob = new Student(2, "Bob");
        Student pablo = new Student(3, "Pablo");   
        Student topo = new Student(4, "topo");
        Student jesus = new Student(5, "jesus");
        Student Tomas = new Student(6, "Tomas");
        Student David = new Student(7, "David");
        Student John = new Student(8, "John");   
        Student Mat = new Student(9, "Mat");
        Student Marc = new Student(10, "Marc");

        Students.insertStudent(alice); 
        Students.insertStudent(bob);  
        Students.insertStudent(pablo);  
        Students.insertStudent(topo);  
        Students.insertStudent(jesus);  

        Students.printMap();

        //Consultar estudiantes
        System.out.println("consulting students");
       System.out.println(Students.getStudent(4));
       System.out.println(Students.getStudent(2));

       //Consultar estudiantes inexistentes
       System.out.println(Students.getStudent(8));
       System.out.println(Students.getStudent(9));

       //Remove Students
       System.out.println("removing students");
       System.out.println(Students.removeStudent(1));
       System.out.println(Students.removeStudent(5));

       Students.printMap();

       // remove ALL students
        System.out.println(Students.removeStudent(2));
        System.out.println(Students.removeStudent(3));
        System.out.println(Students.removeStudent(4));

        Students.printMap();


        // Insert more than capacity
        System.out.println("Inserting more than initial capacity");
        Students.insertStudent(alice); 
        Students.insertStudent(bob);  
        Students.insertStudent(pablo);  
        Students.insertStudent(topo);  
        Students.insertStudent(jesus);  
        Students.insertStudent(Tomas); 
        Students.insertStudent(David);  
        Students.insertStudent(John);  
        Students.insertStudent(Mat);  
        Students.insertStudent(Marc);
        
        Students.printMap();
    
    }
}
