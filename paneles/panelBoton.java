package paneles;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import utils.diseño;

public class panelBoton extends JPanel {

    private String[] botones = {

        "√","sin","cos","tan",
        "asin","acos","atan","e^x",
        "π","e","n!","|x|",
        "x^y","x²","∛","C",
        "7","8","9","+",
        "4","5","6","-",
        "1","2","3","*",
        "0",".","=","/",
        "replay","limpiar"
    };

    public panelBoton() {
        setLayout(new GridLayout(9, 4, 5, 5));
        setBackground(diseño.FONDO_PANEL);

        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setFont(diseño.FUENTE_BOTON);
            btn.setFocusPainted(false);
            btn.setActionCommand(texto.toLowerCase());

            if ("0123456789.".contains(texto)) {
                btn.setBackground(diseño.COLOR_NUMERO);
                btn.setForeground(Color.WHITE);
            } else if ("+-*/x^y=x²∛".contains(texto)) {
                btn.setBackground(diseño.COLOR_OPERADOR);
                btn.setForeground(Color.WHITE);
            } else if ("c√sinscossintancosloglnasinacosatane^xπen!|x|".contains(texto.toLowerCase())) {
                btn.setBackground(diseño.COLOR_FUNCION);
                btn.setForeground(diseño.COLOR_OPERADOR);
            } else if ("replaylimpiar".contains(texto.toLowerCase())) {
                btn.setBackground(diseño.COLOR_HISTORIAL);
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(diseño.FONDO_PANEL);
                btn.setForeground(Color.BLACK);
            }

            btn.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
            add(btn);
        }
    }

    public void agregarController(ActionListener controlador) {
        for (Component c : getComponents()) {
            if (c instanceof JButton) ((JButton)c).addActionListener(controlador);
        }
    }
}
