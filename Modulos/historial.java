package Modulos;

import java.util.ArrayList;
import java.util.List;

public class historial {
    private List<String> operaciones;

    public historial() {
        operaciones = new ArrayList<>();
    }

    public void agregarOperacion(String operacion) {
        operaciones.add(operacion);
    }

    public List<String> getOperaciones() {
        return operaciones;
    }

    public void limpiar() {
        operaciones.clear();
    }
}
