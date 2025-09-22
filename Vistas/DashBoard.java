package Vistas;

import Modulos.calculadoraModule;
import control.calculadoraControl;
import java.awt.*;
import javax.swing.*;
import paneles.panelBoton;
import paneles.panelDisplay;

public class DashBoard extends JFrame {

    public DashBoard() {

        setTitle("Calculadora Científica");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        calculadoraModule modelo = new calculadoraModule();

        panelDisplay display = new panelDisplay();
        panelBoton botones = new panelBoton();

        calculadoraControl control = new calculadoraControl(modelo, display);


        botones.agregarController(control);

        add(display, BorderLayout.NORTH);  
        add(botones, BorderLayout.CENTER);  
    }
}
