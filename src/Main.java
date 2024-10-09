//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Coche> coches = new ArrayList();
        System.out.println("Introduce el número de coches:");
        int numCoches = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < numCoches; ++i) {
            System.out.println("Introduce el nombre del coche:");
            String nombreCoche = scanner.nextLine();
            System.out.println("Introduce la velocidad máxima (km/h):");
            double velocidadMaxima = scanner.nextDouble();
            System.out.println("Introduce la aceleración (km/h/s):");
            double aceleracion = scanner.nextDouble();
            System.out.println("Introduce el manejo (1-10):");
            int manejo = scanner.nextInt();
            System.out.println("Introduce la durabilidad (1-100):");
            int durabilidad = scanner.nextInt();
            System.out.println("Introduce el combustible restante (litros):");
            double combustible = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Introduce el nombre del piloto:");
            String nombrePiloto = scanner.nextLine();
            Piloto piloto = new Piloto(nombrePiloto);
            Coche coche = new Coche(nombreCoche, velocidadMaxima, aceleracion, manejo, combustible, durabilidad, piloto);
            coches.add(coche);
        }

        System.out.println("Introduce la longitud del circuito (km):");
        double longitudCircuito = scanner.nextDouble();
        System.out.println("Introduce el número de vueltas:");
        int numeroVueltas = scanner.nextInt();
        System.out.println("Introduce el número de curvas:");
        int numeroCurvas = scanner.nextInt();
        System.out.println("Introduce la dificultad del circuito (1-10):");
        int dificultadCircuito = scanner.nextInt();
        Circuito circuito = new Circuito(longitudCircuito, numeroVueltas, numeroCurvas, dificultadCircuito);
        Carrera carrera = new Carrera(coches, circuito);
        carrera.iniciarCarrera();
    }
}
