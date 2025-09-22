package control;

import Modulos.calculadoraModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import paneles.panelDisplay;

public class calculadoraControl implements ActionListener {

    private calculadoraModule modelo;
    private panelDisplay display;
    private double num1 = 0;
    private String operador = "";
    private boolean esperandoNuevoNumero = false;

    public calculadoraControl(calculadoraModule modelo, panelDisplay display) {
        this.modelo = modelo;
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
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

            // Operadores binarios
            if ("+-*/x^y".contains(cmd)) {
                num1 = Double.parseDouble(display.getTexto());
                operador = cmd;
                esperandoNuevoNumero = true;
                return;
            }

            if ("=".equals(cmd)) {
                double num2 = Double.parseDouble(display.getTexto());
                double res = 0;
                switch (operador) {
                    case "+": res = modelo.sumar(num1,num2); break;
                    case "-": res = modelo.restar(num1,num2); break;
                    case "*": res = modelo.multiplicar(num1,num2); break;
                    case "/": res = modelo.dividir(num1,num2); break;
                    case "x^y": res = modelo.potencia(num1,num2); break;
                    default: res = num2;
                }
                display.setTexto(formatResultado(res));
                num1 = res;
                esperandoNuevoNumero = true;
                operador = "";
                return;
            }

            double valor = display.getTexto().isEmpty() ? 0 : Double.parseDouble(display.getTexto());
            double res = 0;
            switch (cmd) {
                case "√": res = modelo.raiz(valor); break;
                case "sin": res = modelo.seno(valor); break;
                case "cos": res = modelo.coseno(valor); break;
                case "tan": res = modelo.tangente(valor); break;
                case "log": res = modelo.logaritmo(valor); break;
                case "ln": res = modelo.logaritmoNeperiano(valor); break;
                case "C": display.limpiar(); operador=""; num1=0; esperandoNuevoNumero=false; return;
            }
            display.setTexto(formatResultado(res));
            esperandoNuevoNumero = true;

        } catch (Exception ex) {
            display.setTexto("Error");
            esperandoNuevoNumero = true;
        }
    }

    private String formatResultado(double v){
        if(v==Math.rint(v)) return String.valueOf((long)v);
        else return String.valueOf(v);
    }
}
