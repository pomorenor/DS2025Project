/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Benchmarking;

import DataStructures.Heap;
import DataTypes.Student;
import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class HeapBenchmark {

    public static void runTest() throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo donde se guardarán los resultados (No se permite espacios): ");
        System.out.print("> ");
        String file_name = scan.next();
        System.out.println("Ingrese el tamaño inicial del monticulo: ");
        System.out.print("> ");
        int start = scan.nextInt();
        System.out.println("Ingrese el tamaño final del monticulo: ");
        System.out.print("> ");
        int end = scan.nextInt();
        System.out.println("Ingrese la cantidad de datos que necesita: ");
        System.out.print("> ");
        int quant = scan.nextInt();
        int step = (end - start) / quant;
        scan.close();
        PrintWriter outFile = new PrintWriter(new File(file_name));
        testHeap(new Heap(), outFile, start, end, step);
        outFile.close();
        System.out.println("Archivo guardado: " + file_name);
    }

    private static void measureExecTime(Heap testHeap, String operation, int size, PrintWriter outFile) {
        Instant start;
        Instant finish;
        start = Instant.now();
        execMethod(testHeap, operation);
        finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos();
        outFile.printf("%s %s %d %d\n", "Heap", operation, size, timeElapsed);
    }

    private static void execMethod(Heap testHeap, String method){
        switch (method) {
            case "insert":
                Student inserted = new Student(0, "Persona especial");
                testHeap.insert(inserted);
                break;

            case "delete":
                testHeap.delete(testHeap.getSize() - 1);
                break;

            case "extractMax":
                testHeap.extractMax();
                break;

            case "getMax":
                testHeap.getMax();
                break;
        }
    }

    private static void fillHeap(Heap testHeap, int size){
        ArrayList<String> deportesEstudiante = new ArrayList<>();
        for(int i = 0; i < size; i++){
            Student inserted = new Student(i+1, "Estudiante " + (i + 1));
            inserted.setSports(new ArrayList<String>(deportesEstudiante));
            testHeap.insert(inserted);
            deportesEstudiante.add("Deporte " + i);
        }
    }

    private static void testHeap(Heap testHeap, PrintWriter outFile, int start, int end, int step){
        System.out.println("Probando Heap");
        String[] methods = {
            "insert",
            "delete",
            "extractMax",
            "getMax"
        };
        double perc = 0;
        double percBef = -1;
        System.out.println("0%");
        for (int size = start; size <= end; size += step){
            perc = ((double)(size - start) / (double)(end - start)) * 100;
            if((perc - percBef >= 10)){
                percBef = perc;
                System.out.printf("%.2f%s\n", perc, "%");
            }
            for(int i = 0; i < methods.length; i++){
                testHeap.clear();
                fillHeap(testHeap, size);
                measureExecTime(testHeap, methods[i], size, outFile);
            }
        }
        System.out.println("Fin");
    }
}
