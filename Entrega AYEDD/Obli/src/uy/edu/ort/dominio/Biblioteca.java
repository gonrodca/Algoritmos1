package uy.edu.ort.dominio;

import uy.edu.ort.dominio.Biblioteca;

import uy.edu.ort.dominio.Libro;
import uy.edu.ort.listas.ILista;
import uy.edu.ort.listas.Lista;
import uy.edu.ort.listas.ListaOrd;
import uy.edu.ort.obli.Retorno.Resultado;

public class Biblioteca {
	private String nombre;
	private ListaOrd<Libro> listaLibro;
	private ILista<Reserva> listaReserva;
	private ListaOrd<Calificacion> calificaciones;
	
	
	public int CantReservLibro (Libro l) {
		int cantReser = 0;
		
		for(Reserva r : listaReserva) {
			
				if(r.getLibro().equals(l)) {
					cantReser ++;
			}
		}
		
		return cantReser;
	}

 

	public Biblioteca(String nombre) {
		super();
		this.nombre = nombre;
		this.listaLibro = new ListaOrd<Libro>();
		this.listaReserva = new Lista<Reserva>();
		this.calificaciones = new ListaOrd<Calificacion>();
	}
	
	


	public ListaOrd<Calificacion> getCalificaciones() {
		return calificaciones;
	}




	public void setCalificaciones(ListaOrd<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}




	public ILista<Reserva> getListaReserva() {
		return listaReserva;
	}


	public void setListaReserva(ILista<Reserva> listaReserva) {
		this.listaReserva = listaReserva;
	}
	
	public Reserva PrimeraEspera(Libro l) {
		
		for(Reserva r: listaReserva) {
			
			if(r.esEspera() && r.getLibro().equals(l)) {
			
				return r;
			}
			
		}
		
		return null;
	}
	
	public ILista<Libro> getListaLibro() {
		return listaLibro;
	}

	public void setListaLibro(Libro l) {
		//this.listaLibro.insertarOrd(l);
		this.listaLibro.insertarOrd(l);
	}
	
	public void BorrarLibro(Libro l) {
		this.listaLibro.borrar(l);
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biblioteca other = (Biblioteca) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	

}
