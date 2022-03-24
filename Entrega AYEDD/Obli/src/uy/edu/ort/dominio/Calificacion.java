package uy.edu.ort.dominio;

import java.util.Objects;
import java.util.Date;

public class Calificacion implements Comparable <Calificacion> {
	
	private Integer calificacion ;
	private String comentario;
	private Date fecha;
	
	
	public Calificacion(int calificacion, String comentario) {
		//super();
		this.calificacion = calificacion;
		this.comentario = comentario;
		this.fecha = new Date();
	}
	@Override
	public int hashCode() {
		return Objects.hash(calificacion, comentario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calificacion other = (Calificacion) obj;
		return calificacion == other.calificacion && Objects.equals(comentario, other.comentario);
	}
	@Override
	public String toString() {
		return "Calificacion [calificacion=" + calificacion + ", comentario=" + comentario + "]";
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int compareTo(Calificacion c) {
		return this.fecha.compareTo(c.fecha);
	}
	
	

}
