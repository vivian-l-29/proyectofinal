package Modulos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class historial {

    private File archivo;

    public historial() {
        archivo = new File("database/historial.txt");
        try {
            if (!archivo.exists()) archivo.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarOperacion(String operacion) {
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(operacion + "\n"); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void limpiar() {
        archivo.delete(); 
}
}
