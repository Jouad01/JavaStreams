package edu.daw.Streams;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class LecturaEscrituraStreams {
//    metodos pedirRuta, leerFicheroByteByte, leerCaracterCaracter, leerEscribirBuffer, , leerLineaEscribirObj (que leerá un fichero con BufferedReader y escribirá en otro con ObjectOutputStream)
//    y leerObjEscribirCons (que lea un fichero con ObjectInputStream y lo mostrará por consola).

    private final static String[] campos = {"Título", "Año", "Director", "Duración", "Sinopsis", "Reparto", "Sesión"};

//    esta variable sirve para separar los campos de las peliculas en el fichero de salida
    private final static String separador = System.getProperty("line.separator");

//    METODOS
    public static String pedirRuta(String tipo) throws RutaInvalida {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce la ruta del fichero: " + tipo);
        String ruta = input.nextLine();
        if (ruta.isEmpty()) {
            throw new RutaInvalida("La ruta está vacía");
        }
        return ruta;
    }
    public static void leerFicheroByteByte() throws RutaInvalida {
        try (FileInputStream fin = new FileInputStream(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\entrada.txt"));
             FileOutputStream fout = new FileOutputStream(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\salida.txt"))) {
            int c;
            int i = 0;
            boolean nuevaPelicula = true;
            fout.write("Cartelera de CineCIFFBMOLL\n\n".getBytes());
            do {
                c = fin.read();
                if (c != -1 && (char) c != '#' && (char) c != '{') {
                    if (nuevaPelicula) {
                        fout.write("\n\n-----".getBytes());
                        nuevaPelicula = false;
                    }
                    if ((char) c == '\n') {
                        fout.write(' ');
                    } else {
                        fout.write(c);
                    }
                    // si el carácter es un '#' es un nuevo campo
                } else if ((char) c == '#') {
//                    esto permite que vaya de 0 a 6
                    i = (i + 1) % 7;
                    fout.write("\n".getBytes());
                    fout.write(campos[i].getBytes());
                    fout.write(": ".getBytes());
                    // si es un '{' empieza una película nueva
                } else if ((char) c == '{') {
                    nuevaPelicula = true;
                    i = 0;
                    fout.write("------\n\n".getBytes()); // separador
                }
            } while (c != -1);
        } catch (IOException ex) {
            System.out.println("Error :(. Vuelve a intentarlo. ");
        }
    }


    public static void leerCaracterCaracter() throws RutaInvalida {
        try (FileReader fr = new FileReader(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\entrada.txt"));
             FileWriter fw = new FileWriter(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\salida.txt"))) {
            StringBuilder campoActual = new StringBuilder();
            int c;
            int i = 0;
            while ((c = fr.read()) != -1) {
                if ((char) c == '#') {
                    if (campoActual.length() > 0) {
                        fw.write(campoActual.toString().trim());
                        fw.write(System.lineSeparator());
                        campoActual.setLength(0);
                    }
                    i = (i + 1) % campos.length;
                    fw.write(System.lineSeparator());
                    fw.write(campos[i]);
                } else if ((char) c == '{') {
                    if (campoActual.length() > 0) {
                        fw.write(campoActual.toString().trim());
                        fw.write(System.lineSeparator());
                        campoActual.setLength(0);
                    }
                    i = (i + 1) % campos.length;
                    fw.write(System.lineSeparator());
                    fw.write(System.lineSeparator());
                    fw.write(campos[i]);
                } else {
                    campoActual.append((char) c);
                }
            }

            if (campoActual.length() > 0) {
                fw.write(campoActual.toString().trim());
            }
        } catch (IOException ex) {
            System.out.println("Error :(. Vuelve a intentarlo. ");
        }
    }


    public static void leerEscribirBuffer() throws RutaInvalida {
        try (BufferedReader lectorBuffer = new BufferedReader(new FileReader(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\entrada.txt")));
             BufferedWriter escribirBuffer = new BufferedWriter(new FileWriter(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\salida.txt")))) {

            boolean eof = false;
            String lineaLeida;
            String[] peliculas;
            String[] texto;
            int i = 0;
            escribirBuffer.write("Cartelera de CineCIFFBMOLL");
            escribirBuffer.newLine();

            do {
                lineaLeida = lectorBuffer.readLine();
                if (lineaLeida != null) {
                    peliculas = lineaLeida.split("\\{");

                    for (int j = 0; j < peliculas.length; j++) {
                        texto = peliculas[j].split("#");
                        if (j > 0) {
                            escribirBuffer.newLine();
                        }
                        escribirBuffer.write("-----" + texto[0] + "-----");
                        escribirBuffer.newLine();
                        for (int k = 1; k < texto.length; k++) {
                            escribirBuffer.write(campos[i] + ": " + texto[k]);
                            escribirBuffer.newLine();

                            i = (i + 1) % campos.length;
                        }
                    }
                } else {
                    eof = true;
                }
            } while (!eof);
        } catch (IOException ex) {
            System.out.println("Error :(. Vuelve a intentarlo. ");
        }
    }


    public static void leerLineaEscribirObj() throws RutaInvalida {
        try (BufferedReader lectorBuffer = new BufferedReader(new FileReader(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\entrada.txt")));
             ObjectOutputStream objectSalida = new ObjectOutputStream(new FileOutputStream(pedirRuta("C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\salida.txt")))) {

            String lineaLeida;
            Pelicula p = new Pelicula();

            while ((lineaLeida = lectorBuffer.readLine()) != null) {
                String[] peliculas = lineaLeida.split("\\{");
                for (String peliculaTexto : peliculas) {
                    String[] campos = peliculaTexto.split("#");

                    p.setTitulo(campos[0]);
                    p.setYear(campos[1]);
                    p.setDirector(campos[2]);
                    p.setDuracion(campos[3]);
                    p.setSinopsis(campos[4]);
                    p.setReparto(campos[5]);
                    p.setSesion(campos[6]);

                    objectSalida.writeObject(p);
                    p = new Pelicula();
                }
            }
        } catch (IOException ex) {
            System.out.println("Error :(. Vuelve a intentarlo.");
        }
    }

    public static void leerObjEscribirCons() throws RutaInvalida {
        String rutaEntrada = "C:\\Users\\jouad\\OneDrive\\Documentos\\JAVA\\JavaStreams\\src\\main\\java\\edu\\daw\\entrada.txt";
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(rutaEntrada))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Pelicula pelicula = (Pelicula) objectInputStream.readObject();
                    pelicula.verPelicula();
                } catch (EOFException ex) {
                    eof = true;
                }
            }
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println("Error de lectura o escritura");
        }
    }

}