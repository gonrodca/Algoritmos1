package uy.edu.ort.obli;



import java.util.Arrays;

import java.util.Collections;

import javax.swing.JTable;

import junit.runner.BaseTestRunner;
import uy.edu.ort.dominio.Biblioteca;
import uy.edu.ort.dominio.Calificacion;
import uy.edu.ort.dominio.Libro;
import uy.edu.ort.listas.ILista;
import uy.edu.ort.listas.Lista;
import uy.edu.ort.listas.ListaOrd;
import uy.edu.ort.obli.Retorno.Resultado;
import uy.edu.ort.dominio.Reserva;
import uy.edu.ort.dominio.Cliente;
import uy.edu.ort.dominio.LibroRes;

public class Sistema implements ISistema {
	
	
	
	public Lista<Biblioteca> listaBibliotecas = new  Lista<Biblioteca>(); 
	public Lista<Biblioteca> getLista(){
			return listaBibliotecas;
	}
	
	public Lista<Libro> listaLibros = new Lista<Libro>();
	public Lista<Libro> getLibros (){
		return listaLibros;
	}
	
	public Lista<LibroRes> listaLibrosRes = new Lista<LibroRes>();
	public Lista<LibroRes> getLibrosRes (){
		return listaLibrosRes;
	}
 	
	

	
	@Override
	public Retorno crearSistemaReservas() {
		listaBibliotecas = new Lista<Biblioteca>();
		
		return new Retorno(Resultado.OK);
	}

	@Override
	
	//Pre: debe existir un sistema instanciado
	//Pos: Elimina todas las bibliotecas del sistema
	public Retorno destruirSistemaReservas() {
		listaBibliotecas = null;
		
		return new Retorno(Resultado.OK);
	}

	//Pre: Debe recibir un string biblioteca
	//Pos: Registra biblioteca en caso de que no haya sido creada y devuelve OK
	@Override
	public Retorno registrarBiblioteca(String biblioteca) {
		
		
		boolean existe = listaBibliotecas.pertenece(new Biblioteca(biblioteca));
		if(existe) {
			return new Retorno(Resultado.ERROR_1);
		} else {
			Biblioteca nuevaBib = new Biblioteca(biblioteca);
		
			//listaBibliotecas.insertarOrd(nuevaBib);
			listaBibliotecas.insertarPpio(nuevaBib);
			return new Retorno(Resultado.OK);
		}
	}

	//Pre: Debe recibir un string biblioteca
	//Pos: En caso de que exista, se elimina y retorna OK, de lo contrario ERROR_1
	@Override
	public Retorno eliminarBiblioteca(String biblioteca) {
		Biblioteca bibABorrar = new Biblioteca(biblioteca);
		boolean existe = listaBibliotecas.pertenece(bibABorrar);
		if(!existe) {
			return new Retorno(Resultado.ERROR_1);
		} else {
			listaBibliotecas.borrar(bibABorrar);
			return new Retorno(Resultado.OK);
		}
	}

	@Override
	//Pre :NO VALIDAMOS QUE LOS EJEMPLARES SEAN UN NUMERO VALIDO , NI QUE EDITORIAL NO SEA UN STRING VACIO
	// POS: Devuelve ok en caso de registrar un libro , error 1 si ya existe el libro y error 2 si no existe la biblioteca ingresada
	
	public Retorno registrarLibro(String titulo, String editorial, String biblioteca, int ejemplares) {
		
		
		
		Lista<LibroRes> lista = getLibrosRes();
	
		LibroRes li = new LibroRes(titulo);
		
		if(!lista.pertenece(li)) {
		
		lista.insertarPpio(li);
		
		}
		
		
		boolean existeBiblio = listaBibliotecas.pertenece(new Biblioteca(biblioteca));
		
		if(existeBiblio) {
			
			Biblioteca biblio = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
			Libro Libro = new Libro(titulo,editorial);
		
		
			boolean existeLibro=  biblio.getListaLibro().pertenece(Libro);
		
		
				if(!existeLibro) {
				
					biblio.setListaLibro( new Libro (titulo,editorial,ejemplares));
					return new Retorno(Resultado.OK);
					
				}else {
				
				return new Retorno(Resultado.ERROR_1);
			}
		}else {
			
			return new Retorno(Resultado.ERROR_2);
			
		}
		
	}
	
	
	

	@Override
	//Pre :El titulo , editorial y biblioteca no deben ser vacios , tampoco se veriifica que exista la biblioteca
	// POS: Devuelve ok en caso de eliminar un libro, error 1 si no existe el libro en la biblioteca
		
	public Retorno eliminarLibro(String titulo, String editorial, String biblioteca) {
	
		Biblioteca biblio = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
		
			
		Libro Libro = new Libro(titulo,editorial);
		boolean existeLibro = biblio.getListaLibro().pertenece(Libro);
			
				if(existeLibro) {
					
					biblio.BorrarLibro(Libro);
					return new Retorno(Resultado.OK);
				
				}else {
				
				return new Retorno(Resultado.ERROR_1);
}
		

	
	

	}

	@Override
	//Pre :El titulo , editorial , biblioteca y calificacion , no deben ser vacios , tampoco se verifica que exista la biblioteca
	// POS: Devuelve ok en caso de que el registro sea valido , error 2 si la calificacion no es valida , error 1 si el libro no pertenece a la biblioteca;   
	public Retorno registrarCalificacion(String titulo, String editorial, int calificacion, String biblioteca,
			String comentario) {
			
			Biblioteca b = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
			Libro L = new Libro(titulo,editorial);
			
			if( b.getListaLibro().pertenece(L) ) {
				
					if(calificacion <=5 && calificacion >=0) {
						 Libro LibroEnlista = b.getListaLibro().recuperar(L);
						 Calificacion c = new Calificacion(calificacion,comentario);
						 LibroEnlista.getListaCalificacion().insertarPpio(c);
						 b.getCalificaciones().insertarOrd(c);
							return new Retorno(Resultado.OK);
							
					}else {
						return new Retorno(Resultado.ERROR_2);
						}
			
			}else {
				return new Retorno(Resultado.ERROR_1);
				
			}
						
						
	}

	
	
	@Override
	//PRE:  editorial , biblioteca y calificacion , no deben ser vacios , tampoco se verifica que exista la biblioteca ,
	//POS: Devuelve OK en caso de que la reserva sea realizada o que quede en lista de espera 
	
	public Retorno realizarReserva(String cliente, int numero, String biblioteca, String titulo, String editorial,
			String fecha) {
			
			Biblioteca b = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
			
			Libro L = new Libro(titulo,editorial);
			Lista<LibroRes> librosRes = getLibrosRes();
		
			
			
			
			if( b.getListaLibro().pertenece(L)) {
				
				Libro LibroAreservar = b.getListaLibro().recuperar(L);
				
				//LibroRes li = new LibroRes(LibroAreservar.getNombre());
				
						for(LibroRes l : librosRes) {
						
						if(l.getNombre() == LibroAreservar.getNombre()) {
							
								l.actualizarContador();
							}
						
						
						
						}
					
		   		if( LibroAreservar.getCantidadEjemplares() > 0) {
						
						Reserva reserva = new Reserva(fecha ,cliente, LibroAreservar);
						b.getListaReserva().insertarFin(reserva);
						LibroAreservar.ReservarEjemplar();
						
						
					
						
						return new Retorno(Resultado.OK);
						
						
				
				}else {
					
					Reserva reserva = new Reserva(fecha ,cliente, LibroAreservar);
					reserva.setEspera();
					b.getListaReserva().insertarFin(reserva);
					
				
					return new Retorno(Resultado.OK);
					
					
				}
				
				
				
			
			
			}
			return new Retorno(Resultado.ERROR_1);
		
		
		
	}

	
	
	//Pre: cliente, numero y biblioteca no deben ser null
	//Pos: en caso de exisitr ese numero de reserva, retorna OK en caso de estar tanto en de espera como en lista de reserva
	@Override
	public Retorno cancelarReserva(String cliente, int numero, String biblioteca) {
		
		Biblioteca b = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
		
		
		if(b.getListaReserva().pertenece(new Reserva(numero))) {
			
			Reserva reserva = b.getListaReserva().recuperar(new Reserva(numero));
			Libro L = reserva.getLibro();
		
			if(!reserva.esEspera()) {
			
					b.getListaReserva().borrar(reserva);
					L.CancelarEjemplar();
					
				    Reserva nuevaReserva = b.PrimeraEspera(L);
				    if(nuevaReserva != null) {
				    	  nuevaReserva.CancelarEspera();
				    }
				    
				
					
				  
				    return new Retorno(Resultado.OK);
				    
					
					
			}else {
				
				b.getListaReserva().borrar(reserva);
				return new Retorno(Resultado.OK);
				
			}
		
		
		}
		
		return new Retorno(Resultado.ERROR_1);
	
	
	
	
	
	
	
	
	
	
	}

	//Pre: debe recibir una biblioteca
	//Pos: En caso de que la biblioteca exista se muestran los libros que tiene y retorna OK, de lo contrario retorna ERROR_1
	@Override
	public Retorno listarLibros(String biblioteca) {
		
		
		Biblioteca b = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
		if(b!= null) {
			
			b.getListaLibro().mostrarLibrosDeUnaBiblioteca(biblioteca);
			
			return new Retorno(Resultado.OK);
		} else {
			
			return new Retorno(Resultado.ERROR_1);
		}
		
		
	}
	
	//Pre: Debe recibir una biblioteca
	//Pos: En caso de que exista la biblioteca se listaran sus libros ordenados por calificacion, en caso de no tener libros
	//se muestra un mensaje, siempre retorna OK

	@Override
	public Retorno listarLibrosBiblioteca(String biblioteca) {
		 ListaOrd<Libro> listaLibrosOrd = new ListaOrd<Libro>();
		Biblioteca b = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
		
		if(b!= null) {
			
			if(!b.getListaLibro().esVacia()) {
				System.out.println(String.format("Libro de la biblioteca <%s>", b.getNombre()));
				for(Libro l: b.getListaLibro()) {
					
					
					listaLibrosOrd.insertarOrd(l);
					
					
					
				} 
				for(Libro l: listaLibrosOrd) {
					
					System.out.println(String.format("<%s> <%s> <%s>", l.getNombre(), l.getEditorial(), l.calcularPromedio()));
				}
			}
			
			
			
			
		 else {
			
			System.out.println("No existen libros registrados");
			
		}
		}
		
		return new Retorno(Resultado.OK);
		
	}
	
	//Pre: Debe recibir una biblioteca
	//Pos: En caso de que exista la biblioteca se listaran sus libros ordenados por calificacion, en caso de no tener libros
	//se muestra un mensaje, siempre retorna OK
	@Override
	public Retorno listarBibliotecaRanking() {
		
		ListaOrd<Libro> liso = new ListaOrd<Libro>();
		int cont = 0;
	
		System.out.println("Bibliotecas ordenadas por ranking");
		for(Biblioteca b : listaBibliotecas) {
			if(!b.getListaLibro().esVacia()) {
				cont ++;
				listarLibrosBiblioteca(b.getNombre());
			}
			
			
				
			} 
		if(cont == 0) {
			System.out.println("No hay registros de libros en el sistema");	
		}
		
		return new Retorno(Resultado.OK);
		
	}
	
	
	//Pre: Debe recibir una biblioteca
	//Pos: En caso de que la biblioteca exista y tenga comentarios se listan las calificaciones,
	//en caso de no tener comentarios se muestra un mensaje. Si la biblioteca no existe retorna ERROR_1, sino OK
	@Override
	public Retorno listarComentarios(String biblioteca) {
		
		Biblioteca b = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
		//ListaOrd<Calificacion> lo = new ListaOrd<Calificacion>();
		if(b != null) {
			int cont = 0;
			for(Calificacion c : b.getCalificaciones()) {
				System.out.println(String.format("%s <%s %s>", cont, c.getComentario(), cont++));
			}
			
			if(cont == 0) {
				System.out.println(String.format("No se han agregado comentarios de la biblioteca <%s>", b.getNombre()));
			}
			return new Retorno(Resultado.OK);
		} else {
			return new Retorno(Resultado.ERROR_1);
		}

		
	}
	//Pre: Debe recibir titulo, ediatorial, biblioteca
	//Pos: Retorna OK  en caso de existir la biblioteca y muestra las reservas, de lo contrario retorna ERROR_1
	@Override
	public Retorno listarEspera(String titulo, String editorial, String biblioteca) {
		
		Biblioteca b = listaBibliotecas.recuperar(new Biblioteca(biblioteca));
		
		
			if(b != null && b.getListaLibro().pertenece(new Libro(titulo, editorial))) {
				
				System.out.println(String.format("Lista de espera del titulo “%s” de la editorial “%s” en la biblioteca “%s”", titulo,editorial,biblioteca));
				int cont = 1;
				for(Reserva r : b.getListaReserva()) {
					if(r.esEspera() && r.getLibro().equals(new Libro(titulo, editorial))) {
						
						System.out.println(cont++ +" - " + r.getCliente());
						
					}
				}
				return new Retorno(Resultado.OK);
				
			} else {
				
				//error
				return new Retorno(Resultado.ERROR_1);
			}
			
			
			
		
	}
	
	//PRE: Deben existir libros registrados en las bibliotecas sino se cae el programa 
	//pos: te devuelve una matriz;
	@Override
	public Retorno mostrarReservasBiblioteca() {
		
		Lista<Biblioteca> listabiblio = getLista();
		Lista<LibroRes> listaLibros = getLibrosRes();
		ListaOrd<LibroRes> topLibros = new ListaOrd<LibroRes>();
		ListaOrd<LibroRes> top5 = new ListaOrd<LibroRes>();
		
		String[][] matrix = new String[6][listabiblio.largo()+2];
		
		matrix[0][0] = "Reservas/biblio";
		matrix[0][listabiblio.largo()+1] = "Total";
		int ColumnaBiblioteca = 1;
		
			for (Biblioteca b : listabiblio) {
				matrix[0][listabiblio.largo()+1-ColumnaBiblioteca]=String.format("%15s", b.getNombre()+"     ");
			ILista<Reserva> listaReservas = b.getListaReserva();
			for (LibroRes l : listaLibros) {
				int cont = 0;

				for (Reserva r : listaReservas) {
					Libro libroRes = r.getLibro();

					if (l.getNombre() == libroRes.getNombre()) {
						cont++;
							}

				}	
				l.getCantBiblio().insertarPpio(cont);
				}

			ColumnaBiblioteca++;
			}
			
			for(LibroRes l : listaLibros) {
				topLibros.insertarOrd(l);
			}	
		
		int contador = 0;
		for(LibroRes r : topLibros) {
		
		if (contador < 5) {
			
			top5.insertarOrd(r);
			contador++;}
		
		}
		
		
		int contadorFila = 1;
		
		for(LibroRes r : top5) {
		
			int contadorColumna = 1;
			matrix[contadorFila][0] = String.format("%10s",r.getNombre()+"");
			matrix[contadorFila][listabiblio.largo()+1] = String.format("%10s",r.getContador()+"");
			
			
			for(int j  : r.getCantBiblio()) {
	
					matrix[contadorFila][contadorColumna] = String.format("%15s",j+"     ");
					contadorColumna++;
						
			}
			
			contadorFila ++;
		
		}
					
		
		showMatrix(matrix);
		
		return new Retorno(Resultado.OK);
		
		
		
		
		
		
		
		
	}
		
		
	
	
	
	public static void showMatrix(String[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
		System.out.println();
	}

	@Override
	public Biblioteca buscarBiblioPorNombre(String titulo) {
		if(!titulo.equals("")) {
			for(Biblioteca b : listaBibliotecas) {
				if(b.getNombre().equals(titulo)) {
					
					return b;
				}
			}
			
		}
		return null;
}

	
	
	//--------------------------------------------------------------------------------------------------------------------------	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
