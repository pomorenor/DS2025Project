package Testing;

import DataStructures.DSet;

public class DSetTest {
    public static void main(String[] args) {
        DSet dset = new DSet();

        // Crear conjuntos para 5 estudiantes
        for (int i = 1; i <= 5; i++) {
            dset.makeSet(i);
        }

        // Unir algunos conjuntos
        dset.union(1, 2);
        dset.union(2, 3);

        System.out.println("=== Testing DSet ===");
        System.out.println("¿1 y 3 están conectados? (esperado: true): " + dset.isConnected(1, 3));
        System.out.println("¿1 y 4 están conectados? (esperado: false): " + dset.isConnected(1, 4));

        // Comprimir caminos y verificar representantes
        System.out.println("Representante de 3: " + dset.find(3));
        System.out.println("Representante de 1: " + dset.find(1));
    }
}

