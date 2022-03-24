package uy.edu.ort.dominio;

import java.util.Objects;

public class Reserva {

private Boolean enEspera; 
private Integer idReserva;
private String fecha;
private String cliente;
private Libro libro;
 
private static int ID = 0;


public void setEspera() {
this.enEspera = true;	
}

public void CancelarEspera() {
	this.enEspera = false;	
}



public int getIdReserva() {
	return idReserva;
}
public void setIdReserva(int idReserva) {
	this.idReserva = idReserva;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getCliente() {
	return cliente;
}
public void setCliente(String cliente) {
	this.cliente = cliente;
}
public Libro getLibro() {
	return libro;
}

public boolean esEspera() {
	return this.enEspera;
	
}



public void setLibro(Libro libro) {
	this.libro = libro;
}
public Reserva(String fecha, String cliente, Libro libro) {
	super();
	this.idReserva = ID;
	ID++;
	this.fecha = fecha;
	this.cliente = cliente;
	this.libro = libro;
	this.enEspera = false;
}









@Override
public int hashCode() {
	return Objects.hash(idReserva);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Reserva other = (Reserva) obj;
	return idReserva == other.idReserva;
}


public Reserva(int idReserva) {
	super();
	this.idReserva = idReserva;
}

@Override
public String toString() {
	return "Reserva [enEspera=" + enEspera + ", idReserva=" + idReserva + ", fecha=" + fecha + ", cliente=" + cliente
			+ ", libro=" + libro + "]";
}










}
