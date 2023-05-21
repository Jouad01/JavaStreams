package edu.daw.Streams;

import java.io.FileWriter;
import java.util.Arrays;

public class RutaInvalida extends Exception {
//    RutaInvalida extiende de Excepcion que básicamente se utilizará cuando no se pueda acceder al fichero de entrada, debe tener constructores y un sistema de logs, es decir, guardar en un fichero de texto los errores que se han producido.
    private final String separador = System.getProperty("line.separator");

//    constructores
    public RutaInvalida() {
    }

    public RutaInvalida(String message) {
        super(message);
    }


//    metodo para los errores
    public void compruebaErrores() {
        try (FileWriter fw = new FileWriter("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\entrada.txt", true)) {
            fw.write(Arrays.toString(this.getStackTrace()));
            fw.write(this.getMessage() + separador);
        } catch (Exception e) {
            System.out.println("Error al escribir en el fichero de log");
        }
    }
}
