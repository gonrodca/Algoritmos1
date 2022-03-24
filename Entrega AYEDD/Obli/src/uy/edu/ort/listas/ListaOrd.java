package uy.edu.ort.listas;

public class ListaOrd<T extends Comparable<T>> extends Lista<T> {

	@Override
	public void insertarPpio(T dato) {
		throw new UnsupportedOperationException("Error: No se puede insertar al principio en una lista ordenada.");
	}
	
	@Override
	public void insertarFin(T dato) {
		throw new UnsupportedOperationException("Error: No se puede insertar al final en una lista ordenada.");
	}
	
	@Override
	public void insertarEnPos(T dato, int k) {
		throw new UnsupportedOperationException("Error: No se puede insertar por posición en una lista ordenada.");
	}
	
	
	@Override
    public void insertarOrd(T dato) {

        if(inicio == null || dato.compareTo(inicio.getDato()) <= 0) {
            super.insertarPpio(dato);
        } else {


            NodoLista<T> nuevo = new NodoLista<T>(dato, movermeDeNodo(dato, inicio).getSig());
            movermeDeNodo(dato, inicio).setSig(nuevo);
            largo++;
        }
    }

	
	
	public NodoLista<T> movermeDeNodo(T dato, NodoLista<T> inicio) {

//    NodoLista<T> aux = inicio;
    if(inicio.getSig() != null && dato.compareTo(inicio.getSig().getDato()) > 0) {
        inicio = inicio.getSig();
        return movermeDeNodo(dato, inicio);
    }
    return inicio;


}
	
	


}
