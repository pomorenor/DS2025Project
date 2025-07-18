/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pohlj
 */
public class Student implements Comparable<Student>{
 
    //Attributes 
    
    private int id;
    private String name;
    private List<String> sports;
    private List<String> interest;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.sports = new ArrayList<>();
        this.interest = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSports() {
        return sports;
    }

    public List<String> getInterest() {
        return interest;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    // Métodos para agregar deportes, evitando duplicados
    public void addSport(String sport) {
        if (!sports.contains(sport)) {
            sports.add(sport);
        }
    }

    public void addInterest(String sport) {
        if (!interest.contains(sport)) {
            interest.add(sport);
        }
    }

    // Opcional: eliminar deporte
    public void removeSport(String sport) {
        sports.remove(sport);
    }

    public void removeInterest(String sport) {
        interest.remove(sport);
    }

    //Se compara los estudiantes de acuerdo a la cantidad de deportes e intereses
    public int compareTo(Student other){
        if(this.sports.size() > other.sports.size()){
            return 1;
        }else if(this.sports.size() < other.sports.size()){
            return -1;
        }else{
            if(this.interest.size() > other.interest.size()){
                return 1;
            }else if(this.interest.size() < other.interest.size()){
                return -1;
            }
            else{
                return 0;
            }
        }
    }

    // Para poder imprimir el estudiante
    @Override
    public String toString() {
        return "ID: " + id +
               ", Nombre: " + name +
               ", Practica: " + sports +
               ", Le interesa: " + interest;
    }
    
}
