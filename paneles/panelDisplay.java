package paneles;

import java.awt.*;
import javax.swing.*;
import utils.diseño;

public class panelDisplay extends JPanel {
    private JTextField display;

    public panelDisplay() {
        setLayout(new BorderLayout());
        display = new JTextField();
        display.setFont(diseño.FUENTE_DISPLAY);
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBackground(diseño.FONDO_DISPLAY);
        display.setForeground(diseño.COLOR_TEXTO_OSCURO);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.CENTER);
    }

    public void setTexto(String texto) { display.setText(texto); }
    public String getTexto() { return display.getText(); }
    public void limpiar() { display.setText(""); }
}
