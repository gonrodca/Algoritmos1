package uy.edu.ort.obli;
import uy.edu.ort.dominio.Biblioteca;
import uy.edu.ort.dominio.Libro;
import uy.edu.ort.listas.ILista;
import uy.edu.ort.listas.Lista;
import uy.edu.ort.obli.Retorno.Resultado;

public interface ISistema {
	
   Retorno crearSistemaReservas();
   Retorno destruirSistemaReservas();
   Retorno registrarBiblioteca(String biblioteca);
   Retorno eliminarBiblioteca(String biblioteca);
   Retorno registrarLibro(String titulo, String editorial, String biblioteca, int ejemplares);
   Retorno eliminarLibro(String titulo, String editorial, String biblioteca);
   Retorno registrarCalificacion(String titulo, String editorial, int calificacion, String biblioteca, String comentario);
   Retorno realizarReserva(String cliente, int numero, String biblioteca, String titulo, String editorial, String fecha);
   Retorno cancelarReserva(String cliente, int numero, String biblioteca);
   Retorno listarLibros(String biblioteca);
   Retorno listarLibrosBiblioteca (String biblioteca);
   Retorno listarBibliotecaRanking();
   Retorno listarComentarios (String biblioteca);
   Retorno listarEspera (String titulo, String editorial, String biblioteca);
   Retorno mostrarReservasBiblioteca ();
   Biblioteca buscarBiblioPorNombre(String titulo);

}