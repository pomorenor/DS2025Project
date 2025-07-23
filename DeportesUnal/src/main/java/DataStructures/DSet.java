package DataStructures;

import java.util.HashMap;
import java.util.Map;

public class DSet {

    private Map<Integer, Integer> group;

    public DSet() {
        this.group = new HashMap<>();
    }

    // Inicializa un conjunto para un estudiante
    public void makeSet(int id) {
        group.put(id, id);
    }

    // Encuentra el representante del conjunto (con compresión de caminos)
    public int find(int id) {
        if (group.get(id) != id) {
            group.put(id, find(group.get(id)));
        }
        return group.get(id);
    }

    // Une los conjuntos de dos estudiantes
    public void union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);

        if (root1 != root2) {
            group.put(root1, root2);
        }
    }

    // Verifica si dos estudiantes están en el mismo conjunto
    public boolean isConnected(int id1, int id2) {
        return find(id1) == find(id2);
    }
}
