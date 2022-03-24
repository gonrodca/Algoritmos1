package uy.edu.ort.dominio;

import uy.edu.ort.dominio.Biblioteca;
import uy.edu.ort.dominio.Libro;
import uy.edu.ort.listas.ILista;
import uy.edu.ort.listas.Lista;
import uy.edu.ort.obli.Retorno.Resultado;

import java.util.Objects;

public class Libro implements Comparable <Libro> {
	private String Titulo;
	private String Editorial;
	private Integer CantidadEjemplares;
	private ILista<Calificacion> listaCalificacion;
	private Double promedio;

	
	



	public Libro(String titulo, String editorial, int cantidadEjemplares) {
		super();
		Titulo = titulo;
		Editorial = editorial;
		CantidadEjemplares = cantidadEjemplares;
		this.listaCalificacion = new Lista<Calificacion>();
		this.promedio = 0.0;
	}
	
	
	public Libro(String titulo) {
		this.Titulo = titulo;
		
	}
	
	
public Libro(String titulo, String editorial) {
	super();
	Titulo = titulo;
	Editorial = editorial;
	this.CantidadEjemplares = 0;
	this.listaCalificacion = new Lista<Calificacion>();
}





	public ILista<Calificacion> getListaCalificacion() {
	return listaCalificacion;
}



	public void setListaCalificacion(Calificacion c) {
	listaCalificacion.insertarPpio(c);
}


	public double calcularPromedio() {
		
		int cont = 0;
		double total = 0.0;
		
		for(Calificacion c: listaCalificacion) {
			
			total+= c.getCalificacion();
			cont++;
			this.promedio= total/cont;
		 
		}
		
		return total/cont;
		
	}

	public String getEditorial() {
		return Editorial;
	}

	public void setEditorial(String editorial) {
		Editorial = editorial;
	}


	public int getCantidadEjemplares() {
		return CantidadEjemplares;
	}
	
	public int ReservarEjemplar() {
		CantidadEjemplares --;
		return CantidadEjemplares;
	}
	
	public int CancelarEjemplar() {
		CantidadEjemplares ++;
		return CantidadEjemplares;
	}
	

	public void setCantidadEjemplares(int cantidadEjemplares) {
		CantidadEjemplares = cantidadEjemplares;
	}

	public String getNombre() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		this.Titulo = titulo;
	}

	@Override
	public String toString() {
		return "Libro [Titulo=" + Titulo + ", Editorial=" + Editorial + ", CantidadEjemplares=" + CantidadEjemplares + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(Editorial, Titulo);
	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(Editorial, other.Editorial) && Objects.equals(Titulo, other.Titulo);
	}


//SOLO ORDENA ASCENDENTE
	@Override
	public int compareTo(Libro o) {
		
		return -Double.valueOf(this.calcularPromedio()).compareTo(o.calcularPromedio());
			

	}






	
	



}
