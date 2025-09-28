package control;

import Modulos.historial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import paneles.panelDisplay;

public class calculadoraControl implements ActionListener {
    private panelDisplay display;
    private double num1 = 0;
    private String operador = "";
    private boolean esperandoNuevoNumero = false;
    private historial historial;

    public calculadoraControl(panelDisplay display) {
        this.display = display;
        this.historial = new historial();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd == null) return;
        cmd = cmd.toLowerCase();

        if ("replay".equals(cmd) || "historial".equals(cmd)) {
            StringBuilder sb = new StringBuilder();
            for (String op : historial.obtenerOperaciones()) {
                sb.append(op).append("\n");
            }
            if (sb.length() == 0) sb.append("No hay operaciones aún.");
            JOptionPane.showMessageDialog(null, sb.toString(), "Historial", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if ("limpiar".equals(cmd)) {
            historial.limpiar();
            JOptionPane.showMessageDialog(null, "Historial borrado", "Historial", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            if ("0123456789".contains(cmd) || ".".equals(cmd)) {
                if (esperandoNuevoNumero) {
                    display.limpiar();
                    esperandoNuevoNumero = false;
                }
                if (".".equals(cmd) && display.getTexto().contains(".")) return;
                display.setTexto(display.getTexto() + cmd);
                return;
            }

            if ("c".equals(cmd)) {
                display.limpiar();
                operador = "";
                num1 = 0;
                esperandoNuevoNumero = false;
                return;
            }

            if ("+-*/x^y".contains(cmd)) {
                num1 = Double.parseDouble(display.getTexto());
                operador = cmd;
                esperandoNuevoNumero = true;
                return;
            }

            if ("=".equals(cmd)) {
                double num2 = Double.parseDouble(display.getTexto());
                double res = calcular(num1, num2, operador);
                String operacion = num1 + " " + operador + " " + num2 + " = " + res;
                historial.agregarOperacion(operacion);
                display.setTexto(formatResultado(res));
                num1 = res;
                esperandoNuevoNumero = true;
                operador = "";
                return;
            }

            double valor = display.getTexto().isEmpty() ? 0 : Double.parseDouble(display.getTexto());
            double res = calcularUnaria(cmd, valor);
            if (!"c".equals(cmd)) historial.agregarOperacion(cmd + "(" + valor + ") = " + res);
            display.setTexto(formatResultado(res));
            esperandoNuevoNumero = true;

        } catch (Exception ex) {
            display.setTexto("Error");
            esperandoNuevoNumero = true;
        }
    }

    private double calcular(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            case "x^y": return Math.pow(a, b);
            default: return b;
        }
    }

    private double calcularUnaria(String cmd, double valor) {
        switch (cmd) {
            case "√": return Math.sqrt(valor);
            case "∛": return Math.cbrt(valor);
            case "x²": return Math.pow(valor, 2);
            case "sin": return Math.sin(Math.toRadians(valor));
            case "cos": return Math.cos(Math.toRadians(valor));
            case "tan": return Math.tan(Math.toRadians(valor));
            case "asin": return Math.toDegrees(Math.asin(valor));
            case "acos": return Math.toDegrees(Math.acos(valor));
            case "atan": return Math.toDegrees(Math.atan(valor));
            case "log": return Math.log10(valor);
            case "ln": return Math.log(valor);
            case "e^x": return Math.exp(valor);
            case "π": return Math.PI;
            case "e": return Math.E;
            case "n!": return factorial((int)valor);
            case "|x|": return Math.abs(valor);
            case "c": display.limpiar(); operador=""; num1=0; esperandoNuevoNumero=false; return 0;
            default: return valor;
        }
    }

    private double factorial(int n) {
        if (n < 0) return Double.NaN;
        double f = 1;
        for (int i = 1; i <= n; i++) f *= i;
        return f;
    }

    private String formatResultado(double v){
        if (v == Math.rint(v)) return String.valueOf((long)v);
        else return String.valueOf(v);
    }
}
