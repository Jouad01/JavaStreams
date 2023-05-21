package edu.daw;

import edu.daw.Streams.LecturaEscrituraStreams;
import edu.daw.Streams.RutaInvalida;

import java.util.Scanner;

public class MenuStreams {

    LecturaEscrituraStreams lecturaEscrituraStreams = new LecturaEscrituraStreams();
    public static void main(String[] args) throws RutaInvalida {
        Scanner input = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("---MENU PRINCIPAL---");
            System.out.println("1. Leer y escribir caracter a caracter");
            System.out.println("2. Leer y escribir byte a byte");
            System.out.println("3. Leer y escribir con buffer");
            System.out.println("4. Leer y escribir con objetos");
            System.out.println("5. Leer objetos y escribir en consola");
            System.out.println("6. Salir");

            System.out.print("Introduce una opción: ");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    LecturaEscrituraStreams.leerCaracterCaracter();
                    break;
                case 2:
                    LecturaEscrituraStreams.leerFicheroByteByte();
                    break;
                case 3:
                    LecturaEscrituraStreams.leerEscribirBuffer();
                    break;
                case 4:
                    LecturaEscrituraStreams.leerLineaEscribirObj();
                    break;
                case 5:
                    LecturaEscrituraStreams.leerObjEscribirCons();
                    break;
                case 6:
                    System.out.println("Hasta luego");
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 6);
            input.close();
    }

}
