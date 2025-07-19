package DataStructures;

import DataTypes.Student;
import java.util.ArrayList;
import java.util.List;

public class Heap{
    private final static int defaultCapacity = 8;
    private Student[] students;
    private int size;
    private int maxSize;

    public Heap(int capacity){
        students = new Student[capacity];
        size = 0;
        maxSize = capacity;
    }

    public Heap(){
        this(defaultCapacity);
    }

    //Inserta un nuevo estudiante
    public void insert(Student newStudent){
        if(size >= maxSize){
            maxSize *= 2;
            Student[] newStudents = new Student[maxSize];
            for(int i = 0; i < size; ++i){
                newStudents[i] = students[i];
            }
            students = newStudents;
        }
        students[size] = newStudent;
        siftUp(size);
        size++;
    }

    //Borrar un estudiante sabiendo su posicion
    public Student delete(int i){
        if(i > size) return null;
        Student deleted = students[i]; 
        Student max = getMax();
        List<String> deletedSports = deleted.getSports();
        //Añadir un deporte para añadir prioridad al que se va eliminar
        deleted.setSports(new ArrayList<>(max.getSports()));
        deleted.addSport("toDelete");
        siftUp(i);
        deleted = extractMax();
        deleted.setSports(deletedSports);
        return deleted;
    }

    public Student extractMax(){
        if(size == 0) throw new RuntimeException("There are no students to delete");
        Student max = getMax();
        swap(0, size - 1);
        size--;
        siftDown(0);
        return max;
    }

    public Student getMax(){
        if (size > 0){
            return students[0];
        }
        return null;
    }

    //Intercambia dos estudiantes de posición
    public void swap(int i, int j){
        Student a = students[i];
        Student b = students[j];
        students[i] = b;
        students[j] = a;
    }

    public Student getParent(int i){
        return students[(i - 1) / 2];
    }

    public Student getRightChild(int i){
        if(i * 2 + 2 < size){
            return students[i * 2 + 2];
        }else{
            return null;
        }
    }

    public Student getLeftChild(int i){
        if(i * 2 + 1 < size){
            return students[i * 2 + 1];
        }else{
            return null;
        }
    }

    //Sube un estudiante hasta que el padre sea mayor
    public void siftUp(int i){
        while(students[i].compareTo(getParent(i)) > 0){
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    //Baja un estudiante hasta que sea no haya hijos menores
    public void siftDown(int i){
        int maxIndex = i;
        if(2 * i + 1 < size && students[maxIndex].compareTo(getLeftChild(i)) < 0){
            maxIndex = 2 * i + 1;
        }
        if(2 * i + 2 < size && students[maxIndex].compareTo(getRightChild(i)) < 0){
            maxIndex = 2 * i + 2;
        }
        if(i != maxIndex){
            swap(i, maxIndex);
            siftDown(maxIndex);
        }
    }

    @Override
    public String toString(){
        String printable = super.toString() + '\n';
        for(int i = 0; i < size; ++i){
            Student s = students[i];
            printable = printable + i + ": " + s.toString() + '\n';
        }
        return printable;
    }
}
