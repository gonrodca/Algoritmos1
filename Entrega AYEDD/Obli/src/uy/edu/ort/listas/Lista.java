package uy.edu.ort.listas;

import java.util.Iterator;
import uy.edu.ort.dominio.Libro;
import uy.edu.ort.dominio.Biblioteca;

public class Lista<T> implements ILista<T> {
	
	protected NodoLista<T> inicio;
	protected int largo;
	protected int tope;

	public Lista() {
		this.inicio = null;
		this.largo = 0;
		this.tope = -1;
	}

	public Lista(int tope) {
		this.inicio = null;
		this.largo = 0;
		this.tope = tope;
	}

	public void insertarPpio(T dato) {
//		NodoLista nuevo = new NodoLista(dato, inicio);
//		inicio = nuevo;

		inicio = new NodoLista<T>(dato, inicio);
		largo++;
	}

	public void borrarPpio() {
		inicio = inicio.getSig();
		largo--;
	}

	public void insertarFin(T dato) {
		if (inicio == null) {
			insertarPpio(dato);
		} else {
			NodoLista<T> aux = inicio;
			while (aux.getSig() != null) {
				aux = aux.getSig();
			}
			NodoLista<T> nuevo = new NodoLista<T>(dato);
			aux.setSig(nuevo);
			largo++;
		}
	}

	public void borrarFin() {
		if (inicio.getSig() == null) {
			borrarPpio();
		} else {
			NodoLista<T> aux = inicio;
			while (aux.getSig().getSig() != null) {
				aux = aux.getSig();
			}
			aux.setSig(null);
			largo--;
		}
	}

	public void borrar(T dato) {
		if (dato.equals(inicio.getDato())) {
			borrarPpio();
		} else {
			NodoLista<T> aux = inicio;
			while (dato != aux.getSig().getDato()) {
				aux = aux.getSig();
			}
			aux.setSig(aux.getSig().getSig());
			largo--;
		}
	}

	public void insertarEnPos(T dato, int k) {
		if (k == 0) {
			insertarPpio(dato);
		} else {
			NodoLista<T> aux = inicio;
			while (k > 1) {
				aux = aux.getSig();
				k--;
			}
			NodoLista<T> nuevo = new NodoLista<T>(dato, aux.getSig());
			aux.setSig(nuevo);
			largo++;
		}
	}

	public void borrarEnPos(int k) {
		if (k == 0) {
			borrarPpio();
		} else {
			NodoLista<T> aux = inicio;
			while (k > 1) {
				aux = aux.getSig();
				k--;
			}
			aux.setSig(aux.getSig().getSig());
			largo--;
		}
	}

	public boolean esVacia() {
//		return largo == 0;
		return inicio == null;
	}

	public boolean esLlena() {
		return largo == tope;
	}

	public int largo() {
		return largo;
	}

	public void mostrar() {
		NodoLista<T> aux = inicio;
		int cont = 0;
		while (aux != null) {
			System.out.println(cont + ": " + aux.getDato());
			aux = aux.getSig();
			cont++;
		}
		System.out.println();
	}
	
	
	

	public boolean pertenece(T dato) {
		NodoLista<T> aux = inicio;
		while (aux != null) {
			if (dato.equals(aux.getDato())) {
				return true;
			}
			aux = aux.getSig();
		}
		return false;
	}

	public T recuperar(T dato) {
		NodoLista<T> aux = inicio;
		while (aux != null) {
			if (dato.equals(aux.getDato())) {
				return aux.getDato();
			}
			aux = aux.getSig();
		}
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private NodoLista<T> aux = inicio;
			
			@Override
			public boolean hasNext() {
				return aux != null;
			}

			@Override
			public T next() {
				T ret = aux.getDato();
				aux = aux.getSig();
				return ret;
			}
		};
	}

	@Override
	public void insertarOrd(T dato) {
		throw new UnsupportedOperationException("Error: No se puede insertar ordenado en una lista no ordenada.");
	}
	
	
	
	
	
	
	//public void mostrarLibrosDeUnaBibliotecaPorCalificacion(Biblioteca b) {
		
		
		//Lista<Libro> librosCal = 
	
	
	//}
	
	
	
	public void mostrarLibrosDeUnaBiblioteca(String biblioteca) {
		
		NodoLista<T> aux = inicio;
		
		if(aux!= null) {
			
				int cont = 0;
				System.out.println("Libros de la biblioteca " + biblioteca);
				while (aux != null) {
					System.out.println(cont + " - " + aux.getDato());
					aux = aux.getSig();
					cont++;
				}
				System.out.println();
		}
		
		else {
			System.out.println("No existen libros registrados en la biblioteca " + biblioteca);
		}
	
	
	}
	


}
