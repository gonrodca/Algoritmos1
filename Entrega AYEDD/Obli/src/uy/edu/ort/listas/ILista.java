package uy.edu.ort.listas;

public interface ILista<T> extends Iterable<T> {
	// Pre: !esLlena()
	// Pos: Inserta al elemento "dato" al comienzo de la lista
	public void insertarPpio(T dato);

	// Pre: !esVacia()
	// Pos: Elimina al primer elemento de la lista
	public void borrarPpio();

	// Pre: !esLlena()
	// Pos: Inserta a un elemento en el fin de la lista
	public void insertarFin(T dato);

	// Pre: !esVacia()
	// Pos: Borra al último elemento de la lista
	public void borrarFin();

	// Pre: pertenece(dato)
	// Pos: Borra al elemento dato de la lista
	public void borrar(T dato);

	// Pre: 0 <= k <= largo && !esLlena()
	// Pos: Inserta al elemento en la posición k de la lista
	public void insertarEnPos(T dato, int k);

	// Pre: 0 <= k < largo
	// Pos: Borra al elemento en la posición k de la lista
	public void borrarEnPos(int k);
	
	// Pre: !esLlena()
	// Pos: Inserta al elemento ordenado en la lista
	public void insertarOrd(T dato);

	// Pre:
	// Pos: Retorna true sii la lista está vacía. False en caso contrario.
	public boolean esVacia();

	// Pre:
	// Pos: Retorna true sii la lista está llena. False en caso contrario.
	public boolean esLlena();

	// Pre:
	// Pos: Retorna el largo de la lista
	public int largo();

	// Pre:
	// Pos: Muestra a todos los elementos de la lista
	public void mostrar();
	
	
	public void mostrarLibrosDeUnaBiblioteca(String biblio);

	// Pre:
	// Pos: Retorna true sii dato pertenece a la lista
	public boolean pertenece(T dato);

	// Pre:
	// Pos: Retorna al primer elemento de la lista igual a dato
	public T recuperar(T dato);
	
	
}
