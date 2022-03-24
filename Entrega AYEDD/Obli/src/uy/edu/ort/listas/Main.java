package uy.edu.ort.listas;

import java.lang.reflect.Array;

import java.util.Arrays;
import java.util.Collection;

import uy.edu.ort.dominio.Biblioteca;
import uy.edu.ort.dominio.Libro;
import uy.edu.ort.dominio.LibroRes;
import uy.edu.ort.dominio.Reserva;
import uy.edu.ort.listas.ILista;
import uy.edu.ort.listas.Lista;
import uy.edu.ort.obli.Retorno.Resultado;
import uy.edu.ort.obli.Retorno;
import uy.edu.ort.obli.Sistema;

public class Main {

	public static void main(String[] args) {

		Sistema s = new Sistema();
		s.crearSistemaReservas();
		s.registrarBiblioteca("centro");
		s.registrarBiblioteca("norte");

		s.registrarLibro("sapo", "1", "centro", 14);
		
		
		s.registrarLibro("harry", "1", "centro", 14);
		
		s.registrarLibro("Flash", "1", "centro", 14);
		
		s.registrarLibro("Batman", "1", "centro", 14);
		
		s.registrarLibro("Superman", "1", "centro", 14);
		
		s.registrarLibro("IRON", "1", "centro", 14);
		
		s.registrarLibro("cap", "1", "centro", 14);
		
		
		s.registrarLibro("sapo", "1", "norte", 14);
		
		s.registrarLibro("Superman", "1", "norte", 14);
		
		s.registrarLibro("IRON", "1", "norte", 14);
		
		s.registrarLibro("cap", "1", "norte", 14);
	

		s.realizarReserva("1", 0, "centro", "sapo", "1", "010101");
		s.realizarReserva("12", 1, "centro", "sapo", "1", "010101");
		s.realizarReserva("122", 2, "centro", "sapo", "1", "010101");
		s.realizarReserva("12222", 3, "centro", "Flash", "1", "010101");
		s.realizarReserva("134", 4, "centro", "Flash", "1", "010101");
		s.realizarReserva("14", 5, "centro", "Flash", "1", "010101");
		s.realizarReserva("15", 6, "centro", "Superman", "1", "010101");
		s.realizarReserva("16",7, "centro", "cap", "1", "010101");
		s.realizarReserva("17", 8, "centro", "sapo", "1", "010101");
		
		
		s.realizarReserva("123", 0, "norte", "sapo", "1", "010101");
		s.realizarReserva("1435", 1, "norte", "sapo", "1", "010101");
		s.realizarReserva("13321", 2, "norte", "sapo", "1", "010101");
		s.realizarReserva("12342", 3, "norte", "Flash", "1", "010101");
		s.realizarReserva("143", 4, "norte", "Flash", "1", "010101");
		s.realizarReserva("15345", 5, "norte", "Flash", "1", "010101");
		s.realizarReserva("13453", 6, "norte", "Superman", "1", "010101");
		s.realizarReserva("13457",7, "norte", "cap", "1", "010101");
		s.realizarReserva("1678967", 8, "norte", "sapo", "1", "010101");
		
		
		
		//-----------------------------------funcion----------------------------------------------------------------------
		
		Lista<Biblioteca> listabiblio = s.getLista();
		Lista<LibroRes> listaLibros = s.getLibrosRes();
		ListaOrd<LibroRes> topLibros = new ListaOrd<LibroRes>();
		ListaOrd<LibroRes> top5 = new ListaOrd<LibroRes>();
		
		String[][] matrix = new String[6][listabiblio.largo()+2];
		
		matrix[0][0] = "Reservas/biblio";
		matrix[0][listabiblio.largo()+1] = "Total";
		
		
			for (Biblioteca b : listabiblio) {
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

		}
			
			
		for(LibroRes l : listaLibros) {
			
		
				
				topLibros.insertarOrd(l);
			
		}	
		
		int contador = 0;
		for(LibroRes r : topLibros) {
		
		if (contador < 5) {
			
			top5.insertarOrd(r);
			contador++;
			
		}else {
			break;
		}
		
		}
		
		
		
		int contadorFila = 1;
		
		for(LibroRes r : top5) {
		
			int contadorColumna = 1;
			matrix[contadorFila][0] = r.getNombre();
			matrix[contadorFila][listabiblio.largo()+1] = r.getContador()+"";
			
			
			for(int j  : r.getCantBiblio()) {
		
				if(contadorFila<6 && contadorColumna <listabiblio.largo()+1) {
					
					System.out.println("Fila  "+contadorFila+"---- columna " + contadorColumna );
					System.out.println(j);
					matrix[contadorFila][contadorColumna] = j +"";
					contadorColumna++;
					showMatrix(matrix);     	
				
				}
			
			
			}
			
			contadorFila ++;
		
			
		
		}
			
		
		
		
		showMatrix(matrix);
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		//----------------------------------- fin funcion----------------------------------------------------------------------


		
	
		for(LibroRes r : topLibros) {
			
			System.out.println(r.getNombre());
			
			for(int i : r.getCantBiblio()) {
				
				System.out.println(i);
			}
			
			
		}
     
		
		
		
	
	
	
	
	
	}

	public static void showMatrix(String[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
		System.out.println();
	}

	private static int posMaximoDelVector(int[] vec, int desde, int hasta) {
		int max = vec[desde], posMax = desde;
		for (int i = desde + 1; i <= hasta; i++) {
			if (vec[i] > max) {
				max = vec[i];
				posMax = i;
			}
		}
		return posMax;
	}

	public static int[] ordenar(int[] vec, int desde, int hasta) {
		for (int i = desde; i < hasta; i++) {
			int posMax = posMaximoDelVector(vec, i + 1, hasta);
			if (vec[posMax] > vec[i]) {
				int aux = vec[i];
				vec[i] = vec[posMax];
				vec[posMax] = aux;
			}
		}
		return vec;
	}

	public static int[] TopCinco(int[] a, int desde, int hasta) {

		if (a.length <= 5) {

			return ordenar(a, 0, a.length - 1);

		} else {

			int min = minimo(a, desde, hasta);
			int[] nuevoArray = null;

			for (int i = 0; i < a.length; i++) {

				if (a[i] == min) {
					int pos = i;
					nuevoArray = new int[a.length - 1];
					for (int j = 0; j < nuevoArray.length; j++) {

						if (j < pos) {

							nuevoArray[j] = a[j];
						} else if (j >= pos) {
							nuevoArray[j] = a[j + 1];
						}
					}
				}

			}
			return TopCinco(nuevoArray, 0, nuevoArray.length - 1);

		}

	}

	public static int maximo(int[] v, int desde, int hasta) {
		if (desde == hasta) {
			return v[desde];
		} else {
			int medio = (desde + hasta) / 2;
			int maxIzq = maximo(v, desde, medio);
			int maxDer = maximo(v, medio + 1, hasta);
			return Math.max(maxIzq, maxDer);
		}
	}

	public static int minimo(int[] v, int desde, int hasta) {
		if (desde == hasta) {
			return v[desde];
		} else {
			int medio = (desde + hasta) / 2;
			int maxIzq = minimo(v, desde, medio);
			int maxDer = minimo(v, medio + 1, hasta);
			return Math.min(maxIzq, maxDer);
		}
	}

}
