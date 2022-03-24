package uy.edu.ort.dominio;

import java.util.Objects;

import uy.edu.ort.listas.ILista;
import uy.edu.ort.listas.Lista;

public class LibroRes implements Comparable<LibroRes>
{

	@Override
	public String toString() {
		return "LibroRes [Titulo=" + Titulo + ", contadorReserva=" + contadorReserva + ", cantBiblio=" + cantBiblio
				+ "]";
	}


	private String Titulo;
	private Integer contadorReserva;
	private Lista<Integer> cantBiblio;

	public LibroRes(String titulo) {
		this.Titulo = titulo;
		this.contadorReserva = 0;
		this.cantBiblio = new Lista<Integer>();
	}

	public void actualizarContador() {
		
		this.contadorReserva++;
	}
		
	public int getContador() {
		
		return this.contadorReserva;
	}


	public  Lista<Integer> getCantBiblio(){
		
		return this.cantBiblio;
	}



	public String getNombre() {
		// TODO Auto-generated method stub
		return this.Titulo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibroRes other = (LibroRes) obj;
		return Objects.equals(Titulo, other.Titulo);
	}


	@Override
	public int compareTo(LibroRes o) {
		
		return -this.contadorReserva.compareTo(o.contadorReserva);
			

	}



}
