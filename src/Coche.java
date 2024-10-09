public class Coche {
    private String nombre;
    private double velocidadMaxima;
    private double aceleracion;
    private int manejo; // De 1 a 10
    private double combustible;
    private int durabilidad; // De 1 a 100
    private Piloto piloto;

    private double velocidadActual = 0;
    private double distanciaRecorrida = 0; // Distancia total acumulada
    private int vueltasCompletadas = 0;

    public Coche(String nombre, double velocidadMaxima, double aceleracion, int manejo, double combustible, int durabilidad, Piloto piloto) {
        this.nombre = nombre;
        this.velocidadMaxima = velocidadMaxima;
        this.aceleracion = aceleracion;
        this.manejo = manejo;
        this.combustible = combustible;
        this.durabilidad = durabilidad;
        this.piloto = piloto;
        this.velocidadActual = 0; // Velocidad inicial
    }

    public void acelerar() {
        if (combustible > 0) {
            velocidadActual += aceleracion;
            if (velocidadActual > velocidadMaxima) {
                velocidadActual = velocidadMaxima;
            }
            consumirCombustible();
        } else {
            velocidadActual = 0; // Si no hay combustible, velocidad a 0
        }
    }

    public void frenar() {
        velocidadActual -= aceleracion;
        if (velocidadActual < 0) {
            velocidadActual = 0;
        }
    }

    public void tomarCurva(int dificultadCurva) {
        // Reducción de velocidad basada en el manejo y dificultad de la curva
        double reduccionVelocidad = (10 - manejo) * 0.1;
        velocidadActual = velocidadActual * (1 - reduccionVelocidad); // Reducir la velocidad por curva

        System.out.println(nombre + " ha reducido su velocidad a " + velocidadActual + " km/h tras tomar una curva.");

        // Simular daños por curva
        if (Math.random() < (dificultadCurva / 20.0)) {
            int daño = (int) (Math.random() * 5) + 1;
            recibirDaño(daño);
            System.out.println(nombre + " ha sufrido " + daño + " de daño en la curva.");
        }

        // Después de tomar la curva, permitir que el coche vuelva a acelerar
        acelerar(); // Llamar a acelerar para recuperar velocidad
    }

    private void consumirCombustible() {
        combustible -= velocidadActual * 0.001; // Consumo de combustible por km
        if (combustible < 0) {
            combustible = 0;
        }
    }

    public double getVelocidadActual() { return velocidadActual; }
    public double getCombustible() { return combustible; }
    public int getDurabilidad() { return durabilidad; }
    public double getDistanciaRecorrida() { return distanciaRecorrida; }
    public int getVueltasCompletadas() { return vueltasCompletadas; }
    public String getNombre() { return nombre; }
    public Piloto getPiloto() { return piloto; }

    // Recibe la longitud de la vuelta como parámetro
    public void actualizarDistancia(double distancia, double longitudPorVuelta) {
        distanciaRecorrida += distancia;

        // Verificar si completó la vuelta
        while (distanciaRecorrida >= longitudPorVuelta) {
            completarVuelta();
            distanciaRecorrida -= longitudPorVuelta; // Reducir la distancia recorrida para la siguiente vuelta
        }
    }

    public void completarVuelta() {
        vueltasCompletadas++;
        System.out.println(nombre + " ha completado una vuelta.");
    }

    public void recibirDaño(int daño) {
        durabilidad -= daño;
        if (durabilidad < 0) {
            durabilidad = 0;
        }
    }

    public boolean estaFueraDeCarrera() {
        return durabilidad <= 0; // El coche está fuera si la durabilidad es 0
    }
}