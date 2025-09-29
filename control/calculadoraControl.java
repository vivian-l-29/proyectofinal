package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import paneles.panelDisplay;

public class calculadoraControl implements ActionListener {
    private panelDisplay display;
    private double num1 = 0;
    private String operador = "";
    private boolean esperandoNuevoNumero = false;
    private ArrayList<String> historial;

    public calculadoraControl(panelDisplay display) {
        this.display = display;
        this.historial = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd == null) return;

        try {
            // Números y punto
            if ("0123456789.".contains(cmd)) {
                if (esperandoNuevoNumero) {
                    display.limpiar();
                    esperandoNuevoNumero = false;
                }
                if (cmd.equals(".") && display.getTexto().contains(".")) return;
                display.setTexto(display.getTexto() + cmd);
                return;
            }

            // Limpiar display
            if (cmd.equals("c")) {
                display.limpiar();
                operador = "";
                num1 = 0;
                esperandoNuevoNumero = false;
                return;
            }

            // Operadores básicos
            if ("+-*/x^y".contains(cmd)) {
                num1 = Double.parseDouble(display.getTexto());
                operador = cmd;
                esperandoNuevoNumero = true;
                return;
            }

            // Igual
            if (cmd.equals("=")) {
                double num2 = Double.parseDouble(display.getTexto());
                double res = 0;

                if (operador.equals("+")) res = num1 + num2;
                if (operador.equals("-")) res = num1 - num2;
                if (operador.equals("*")) res = num1 * num2;
                if (operador.equals("/")) res = num2 == 0 ? 0 : num1 / num2;
                if (operador.equals("x^y")) res = Math.pow(num1, num2);

                historial.add(num1 + " " + operador + " " + num2 + " = " + res);
                display.setTexto(String.valueOf(res));
                num1 = res;
                esperandoNuevoNumero = true;
                operador = "";
                return;
            }

            // Funciones cientificas
            double valor = display.getTexto().isEmpty() ? 0 : Double.parseDouble(display.getTexto());
            double res = valor;

            if (cmd.equals("√")) res = Math.sqrt(valor);
            if (cmd.equals("∛")) res = Math.cbrt(valor);
            if (cmd.equals("x²")) res = Math.pow(valor, 2);
            if (cmd.equals("sin")) res = Math.sin(Math.toRadians(valor));
            if (cmd.equals("cos")) res = Math.cos(Math.toRadians(valor));
            if (cmd.equals("tan")) res = Math.tan(Math.toRadians(valor));
            if (cmd.equals("n!")) res = factorial((int)valor);
            if (cmd.equals("|x|")) res = Math.abs(valor);

            if (!cmd.equals("=") && !"0123456789.c+-*/x^y".contains(cmd)) {
                historial.add(cmd + "(" + valor + ") = " + res);
                display.setTexto(String.valueOf(res));
                esperandoNuevoNumero = true;
            }

            // Mostrar historial
            if (cmd.equals("replay")) {
                StringBuilder sb = new StringBuilder();
                for (String op : historial) sb.append(op).append("\n");
                display.setTexto(sb.toString());
                return;
            }

            // Limpiar historial
            if (cmd.equals("limpiar")) {
                historial.clear();
                display.setTexto("Historial borrado");
                return;
            }

        } catch (Exception ex) {
            display.setTexto("Error");
            esperandoNuevoNumero = true;
        }
    }

    private double factorial(int n) {
        if (n < 0) return 0;
        double f = 1;
        for (int i = 1; i <= n; i++) f *= i;
        return f;
    }
}
