import java.util.*;

public class SportsTree {

    private static class SportNode {
        String name;
        int counter;
        SportNode left, right;
        int height;

        public SportNode(String name) {
            this.name = name;
            this.counter = 1;
            this.left = null;
            this.right = null;
        }
    }

    private SportNode root;

    // Método para insertar desde un estudiante
    public void insertarDeportesDesdeEstudiante(Student student) {
        for (String sport : student.getSports()) {
            root = insert(root, sport);
        }
    }

    private SportNode insert(SportNode node, String sport) {
        if (node == null) return new SportNode(sport);
        if (sport.compareTo(node.name) < 0) {
            node.left = insert(node.left, sport);
        } else if (sport.compareTo(node.name) > 0) {
            node.right = insert(node.right, sport);
        } else {
            node.counter++;
        }
        return node;
    }

    // Recolectar nodos en inorden
    private void recolectarNodos(SportNode node, List<SportNode> list) {
        if (node != null) {
            recolectarNodos(node.left, list);
            list.add(node);
            recolectarNodos(node.right, list);
        }
    }

    // Orden ascendente
    public void mostrarPorCantidadAscendente() {
        List<SportNode> list = new ArrayList<>();
        recolectarNodos(root, list);
        list.sort(Comparator.comparingInt(n -> n.counter));
        for (SportNode node : list) {
            System.out.println(node.name + ": " + node.counter + " estudiantes");
        }
    }

    // Orden descendente
    public void mostrarPorCantidadDescendente() {
        List<SportNode> list = new ArrayList<>();
        recolectarNodos(root, list);
        list.sort((a, b) -> Integer.compare(b.counter, a.counter));
        for (SportNode node : list) {
            System.out.println(node.name + ": " + node.counter + " estudiantes");
        }
    }
}
