package uy.edu.ort.obli;
import uy.edu.ort.obli.Sistema;
import uy.edu.ort.dominio.Biblioteca;
import uy.edu.ort.dominio.Libro;

public class Main {

	public static void main(String[] args) {
	
	Sistema s = new Sistema();

			
s.registrarBiblioteca("Biblioteca Centro");
s.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
s.registrarCalificacion("Sapo", "Malvin", 5, "Biblioteca Centro", "NASHE");
s.registrarCalificacion("Sapo", "Malvin", 4, "Biblioteca Centro", "NASHE");
s.registrarLibro("Nacho1","Malvin", "Biblioteca Centro", 15);
s.registrarCalificacion("Nacho1", "Malvin", 2, "Biblioteca Centro", "NASHE");

s.registrarLibro("lucho","Malvin", "Biblioteca Centro", 14);
s.registrarCalificacion("lucho", "Malvin", 3, "Biblioteca Centro", "NASHE");
	
	
s.listarLibrosBiblioteca("Biblioteca Centro");

//s.listarLibros("Biblioteca Centro");
	
	
	}

}
