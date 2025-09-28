package Vistas;

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

        panelDisplay display = new panelDisplay();
        panelBoton botones = new panelBoton();

        calculadoraControl control = new calculadoraControl(display);

        botones.agregarController(control);

        add(display, BorderLayout.NORTH);  
        add(botones, BorderLayout.CENTER);  

    }
}
