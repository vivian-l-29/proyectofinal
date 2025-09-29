package Modulos;

import java.util.ArrayList;
import java.util.List;

public class historialModule {
    private List<String> operaciones;

    public historialModule() {
        operaciones = new ArrayList<>();
    }

    public void agregarOperacion(String operacion) {
        operaciones.add(operacion);
    }

    public List<String> obtenerOperaciones() {
        return operaciones;
    }

    public void limpiar() {
        operaciones.clear();
    }
}
