import java.util.List;
import java.util.Random;
import java.util.Iterator;

public class Carrera {
    private List<Coche> coches;
    private Circuito circuito;
    private boolean carreraEnCurso;

    public Carrera(List<Coche> coches, Circuito circuito) {
        this.coches = coches;
        this.circuito = circuito;
        this.carreraEnCurso = true;
    }

    public void iniciarCarrera() {
        System.out.println("¡La carrera ha comenzado!");

        Random random = new Random();

        // Distribución de las curvas en la longitud total del circuito
        double longitudCurva = circuito.getLongitud() / circuito.getNumeroCurvas();

        // Simulación de la carrera
        while (carreraEnCurso) {
            for (Coche coche : coches) {
                if (!coche.estaFueraDeCarrera()) {
                    simularVuelta(coche, random, longitudCurva);
                }
            }

            actualizarPosiciones();
            verificarFinalCarrera();
            esperar(1000); // Simula la actualización en tiempo real cada segundo
        }

        mostrarResultadosFinales();
    }

    private void simularVuelta(Coche coche, Random random, double longitudCurva) {
        if (coche.getCombustible() <= 0) {
            System.out.println(coche.getNombre() + " se ha quedado sin combustible.");
            return;
        }

        // Acumular distancia en base a la velocidad actual para la parte recta
        double distanciaRecorridaEnEstePaso = coche.getVelocidadActual() / 3600; // Convertir de km/h a km/s
        double longitudPorVuelta = circuito.getLongitud() / circuito.getNumeroVueltas();

        // Si el coche alcanza una curva (distanciaRecorrida >= longitudCurva), primero simular la parte recta,
        // luego simular la curva con la velocidad reducida
        if (coche.getDistanciaRecorrida() >= longitudCurva) {
            // Simular la curva con velocidad reducida
            coche.tomarCurva(circuito.getDificultad());

            // Calcular la distancia recorrida durante la curva
            double distanciaRecorridaEnCurva = coche.getVelocidadActual() / 3600; // Convertir a km/s
            coche.actualizarDistancia(distanciaRecorridaEnCurva, longitudPorVuelta);  // Actualizar distancia para la curva

            System.out.printf("%s ha pasado por una curva a %.2f km/h y ha recorrido %.2f km en la curva.%n",
                    coche.getNombre(), coche.getVelocidadActual(), distanciaRecorridaEnCurva);

        } else {
            // Si no está en curva, acumula distancia normal en recta
            coche.actualizarDistancia(distanciaRecorridaEnEstePaso, longitudPorVuelta);
        }

        // Actualizar la velocidad para el siguiente paso
        coche.acelerar();
    }

    private void actualizarPosiciones() {
        // Ordenar coches por vueltas completadas y, en caso de empate, por distancia recorrida
        coches.sort((c1, c2) -> {
            if (c2.getVueltasCompletadas() != c1.getVueltasCompletadas()) {
                // Ordenar por vueltas completadas
                return Integer.compare(c2.getVueltasCompletadas(), c1.getVueltasCompletadas());
            } else {
                // Si las vueltas son iguales, ordenar por distancia recorrida
                return Double.compare(c2.getDistanciaRecorrida(), c1.getDistanciaRecorrida());
            }
        });

        System.out.println("Posiciones actuales:");

        // Mostrar las posiciones de los coches
        for (int i = 0; i < coches.size(); i++) {
            Coche coche = coches.get(i);
            System.out.printf("%d. %s - Velocidad: %.2f km/h, Combustible: %.2f L, Durabilidad: %d, Distancia Recorrida: %.2f km, Vueltas Completadas: %d%n",
                    (i + 1), coche.getNombre(), coche.getVelocidadActual(), coche.getCombustible(), coche.getDurabilidad(),
                    coche.getDistanciaRecorrida(), coche.getVueltasCompletadas());
        }
    }

    private void verificarFinalCarrera() {
        boolean todosCompleto = true; // Asumimos que todos completan la carrera

        // Verificamos el estado de cada coche
        for (Coche coche : coches) {
            if (coche.getVueltasCompletadas() < circuito.getNumeroVueltas()) {
                todosCompleto = false; // Si un coche no ha completado las vueltas, la carrera sigue
                break;
            }
        }

        // Si todos los coches completaron las vueltas, finaliza la carrera
        if (todosCompleto) {
            System.out.println("Todos los coches han completado la carrera.");
            carreraEnCurso = false;
        }

        // Actualizamos los coches que han terminado
        Iterator<Coche> iterator = coches.iterator();
        while (iterator.hasNext()) {
            Coche coche = iterator.next();
            if (coche.getDistanciaRecorrida() >= circuito.getLongitud()) {
                System.out.println(coche.getNombre() + " ha completado la carrera.");
                iterator.remove(); // Eliminar coche de la lista
            }
        }
    }

    private void mostrarResultadosFinales() {
        System.out.println("¡La carrera ha terminado!");
        actualizarPosiciones();
        System.out.println("Clasificación final:");
        coches.sort((c1, c2) -> Integer.compare(c2.getVueltasCompletadas(), c1.getVueltasCompletadas())); // Clasificación
        for (int i = 0; i < coches.size(); i++) {
            Coche coche = coches.get(i);
            System.out.printf("%d. %s - Vueltas Completadas: %d%n", (i + 1), coche.getNombre(), coche.getVueltasCompletadas());
        }
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
