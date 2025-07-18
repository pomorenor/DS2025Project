package DataStructures;

import DataTypes.Student;

public class Heap{
    private final static int defaultCapacity = 8;
    private Student[] students;
    private int size;
    private int max_size;

    public Heap(int capacity){
        students = new Student[capacity];
        size = 0;
        max_size = capacity;
    }

    public Heap(){
        this(defaultCapacity);
    }
}
