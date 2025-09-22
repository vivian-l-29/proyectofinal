package Modulos;

public class calculadoraModule {

    public double sumar(double a, double b) { return a + b; }
    public double restar(double a, double b) { return a - b; }
    public double multiplicar(double a, double b) { return a * b; }
    public double dividir(double a, double b) {
        if (b == 0) throw new ArithmeticException("No se puede dividir entre 0");
        return a / b;
    }

    // Operaciones científicas
    public double potencia(double a, double b) { return Math.pow(a, b); }
    public double raiz(double a) { return Math.sqrt(a); }
    public double seno(double a) { return Math.sin(Math.toRadians(a)); }
    public double coseno(double a) { return Math.cos(Math.toRadians(a)); }
    public double tangente(double a) { return Math.tan(Math.toRadians(a)); }
    public double logaritmo(double a) { return Math.log10(a); }
    public double logaritmoNeperiano(double a) { return Math.log(a); }
}
