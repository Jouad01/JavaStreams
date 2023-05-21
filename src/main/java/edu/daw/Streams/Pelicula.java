package edu.daw.Streams;

import java.io.Serializable;
import java.util.Scanner;

// interfaz serializable para poder escribir objetos en ficheros
public class Pelicula implements Serializable {
    //    la clase Película que define como son los objetos "película" que serán leidos y escritos en ficheros.
    private String titulo = "";
    private String year = "";
    private String director = "";
    private String duracion = "";
    private String sinopsis = "";
    private String reparto = "";
    private String sesion = "";

//    constructores
    public Pelicula() {
    }

    public Pelicula(String titulo, String year, String director, String duracion, String sinopsis, String reparto, String sesion) {
        this.titulo = titulo;
        this.year = year;
        this.director = director;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.sesion = sesion;
    }

    public Pelicula(Pelicula p1) {
        this.titulo = p1.titulo;
        this.year = p1.year;
        this.director = p1.director;
        this.duracion = p1.duracion;
        this.sinopsis = p1.sinopsis;
        this.reparto = p1.reparto;
        this.sesion = p1.sesion;
    }

//    getters y setters


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public void verPelicula() {
        System.out.println("Titulo: " + this.getTitulo());
        System.out.println("Año: " + this.getYear());
        System.out.println("Director: " + this.getDirector());
        System.out.println("Duración: " + this.getDuracion());
        System.out.println("Sinopsis: " + this.getSinopsis());
        System.out.println("Reparto: " + this.getReparto());
        System.out.println("Sesión: " + this.getSesion());
    }

    public void pedirPelicula() {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce el titulo de la pelicula: ");
        this.setTitulo(input.nextLine());
        System.out.println("Introduce el año de la pelicula: ");
        this.setYear(input.nextLine());
        System.out.println("Introduce el director de la pelicula: ");
        this.setDirector(input.nextLine());
        System.out.println("Introduce la duracion de la pelicula: ");
        this.setDuracion(input.nextLine());
        System.out.println("Introduce la sinopsis de la pelicula: ");
        this.setSinopsis(input.nextLine());
        System.out.println("Introduce el reparto de la pelicula: ");
        this.setReparto(input.nextLine());
        System.out.println("Introduce la sesion de la pelicula: ");
        this.setSesion(input.nextLine());
    }

}
