package uy.edu.ort.listas;

public class Persona implements Comparable<Persona> {

	private int cedula;
	private String nombre;

	public Persona(int cedula, String nombre) {
		this.cedula = cedula;
		this.nombre = nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Persona)) {
			return false;
		} else {
			Persona p = (Persona)obj;
			return this.cedula == p.cedula;
		}
	}

	@Override
	public int compareTo(Persona p) {
		return -(this.cedula - p.cedula);
	}

}
