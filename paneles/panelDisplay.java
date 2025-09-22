package paneles;

import java.awt.*;
import javax.swing.*;

public class panelDisplay extends JPanel {
    private JTextField display;

    public panelDisplay() {
        setLayout(new BorderLayout());
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 22));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.CENTER);
    }

    public void setTexto(String texto) { display.setText(texto); }
    public String getTexto() { return display.getText(); }
    public void limpiar() { display.setText(""); }
}
