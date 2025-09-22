package paneles;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class panelBoton extends JPanel {

    private String[] botones = {
        "7","8","9","+",
        "4","5","6","-",
        "1","2","3","*",
        "0",".","C","/",
        "x^y","√","sin","cos",
        "tan","log","ln","="
    };

    public panelBoton() {
        setLayout(new GridLayout(6, 4, 5, 5));
        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setActionCommand(texto);
            add(btn);
        }
    }

    public void agregarController(ActionListener controlador) {
        for (Component c : getComponents()) {
            if (c instanceof JButton) ((JButton)c).addActionListener(controlador);
        }
    }
}
