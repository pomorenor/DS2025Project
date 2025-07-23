package DataStructures;
import DataTypes.Student;

public class hashmap<T extends Student> {
    private int capacity;
    private int size;
    private Node<T>[] buckets;

    public class Node<U> {
        U student;
        Node<U> next;
    
        Node(U student, Node<U> next){
            this.student = student;
            this.next = next;
        }
    }
    @SuppressWarnings("unchecked")
    public hashmap(int capacity){
        this.capacity = capacity;
        size = 0;
        this.buckets = (Node<T>[])new Node[capacity];
    }

    public int hash(int id){
        return (Integer.valueOf(id).hashCode() & 0x7fffffff) % capacity;
    }

    public Boolean insertStudent(T student){
        if ((double)size / capacity > 0.8){
            hashmap<T> rehashed = this.rehash();
            this.buckets = rehashed.buckets;
            this.capacity = rehashed.capacity;
        }

        int idx = hash(student.getId());
        Node<T> curr = buckets[idx];
        while (curr != null) {
            if(curr.student.equals(student)){
                return false;
            }
            curr = curr.next;
        }
        Node<T> newStudent = new Node<T>(student, buckets[idx]);
        buckets[idx] = newStudent;
        size++;
        return true;
    }

    public hashmap<T> rehash(){
        hashmap<T> rehashed = new hashmap<T>(2*capacity);
        for (int i = 0; i < this.buckets.length; i++){
            Node<T> curr = buckets[i];
            while (curr != null) {
                rehashed.insertStudent(curr.student);
                curr = curr.next;
            }
        }
        return rehashed;
    }

    public Student getStudent(int id){
        int idx = hash(id);
        Node<T> curr = buckets[idx];
        while (curr != null) {
            if(curr.student.getId() == (id)){
                return curr.student;
            }
            curr = curr.next;
        }
        return null;
    }

    public Boolean removeStudent(int id){
        int idx = hash(id);
        Node<T> curr = buckets[idx];
        Node<T> prev = null;
        while (curr != null) {
            if(curr.student.getId() == (id)){
                if(prev == null){
                    curr = curr.next;
                    buckets[idx] = curr;
                }
                else{
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }


    public void printMap(){
        for (int i = 0; i < this.buckets.length; i++){
            Node<T> curr = buckets[i];
            while (curr != null) {
                System.out.println(curr.student.toString());
                curr = curr.next;
            }
        }
    }
}
