package Modulos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class historial {
    private File archivo;

    public historial() {
        archivo = new File("database/historial.txt");
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs(); // crea carpeta database
                archivo.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Agregar operación al archivo
    public void agregarOperacion(String operacion) {
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(operacion + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer historial
    public List<String> obtenerOperaciones() {
        List<String> operaciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                operaciones.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return operaciones;
    }

    // Limpiar historial
    public void limpiar() {
        try (FileWriter fw = new FileWriter(archivo, false)) {
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
