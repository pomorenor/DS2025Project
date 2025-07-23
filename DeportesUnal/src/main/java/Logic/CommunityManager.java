package Logic;

import DataStructures.DSet;
import DataTypes.Student;

import java.util.*;

public class CommunityManager {

    private List<Student> students;
    private DSet dset;

    public CommunityManager() {
        students = new ArrayList<>();
        dset = new DSet();
    }

    // Agrega un estudiante y lo registra en el conjunto disjunto
    public void addStudent(Student student) {
        students.add(student);
        dset.makeSet(student.getId());
    }

    // Agrupa estudiantes en comunidades deportivas
    public void groupCommunities() {
        // Comparar todos los pares de estudiantes
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Student s1 = students.get(i);
                Student s2 = students.get(j);

                if (shareCommonSport(s1, s2)) {
                    dset.union(s1.getId(), s2.getId());
                }
            }
        }
    }

    // Verifica si dos estudiantes comparten al menos un deporte
    private boolean shareCommonSport(Student s1, Student s2) {
        for (String sport : s1.getSports()) {
            if (s2.getSports().contains(sport)) {
                return true;
            }
        }
        return false;
    }

    // Permite verificar si dos estudiantes están en la misma comunidad
    public boolean sameCommunity(int id1, int id2) {
        return dset.isConnected(id1, id2);
    }

    // Devuelve el ID del representante de la comunidad
    public int getCommunityRepresentative(int id) {
        return dset.find(id);
    }

    // Devuelve un mapa con los estudiantes agrupados por comunidad
    public Map<Integer, List<Student>> getCommunities() {
        Map<Integer, List<Student>> communities = new HashMap<>();

        for (Student s : students) {
            int rep = dset.find(s.getId());
            communities.putIfAbsent(rep, new ArrayList<>());
            communities.get(rep).add(s);
        }

        return communities;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
