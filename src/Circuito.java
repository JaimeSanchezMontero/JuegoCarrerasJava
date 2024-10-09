public class Circuito {
    private double longitud;
    private int numeroVueltas;
    private int numeroCurvas;
    private int dificultad; // Entre 1 y 10

    public Circuito(double longitud, int numeroVueltas, int numeroCurvas, int dificultad) {
        this.longitud = longitud;
        this.numeroVueltas = numeroVueltas;
        this.numeroCurvas = numeroCurvas;
        this.dificultad = dificultad;
    }

    public double getLongitud() { return longitud; }
    public int getNumeroVueltas() { return numeroVueltas; }
    public int getNumeroCurvas() { return numeroCurvas; }
    public int getDificultad() { return dificultad; }
}