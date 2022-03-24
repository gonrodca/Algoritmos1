package uy.edu.ort.obli;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uy.edu.ort.obli.Retorno.Resultado;

public class ISistemaTest {

	private ISistema sis;
	
	@Before
	public void setUp() throws Exception {
		sis = new Sistema();
	}

	@Test
	public void testCrearSistemaReservas() {
		assertEquals(Resultado.OK, sis.crearSistemaReservas().resultado);
	}

	@Test
	public void testDestruirSistemaReservas() {
		assertEquals(Resultado.OK, sis.destruirSistemaReservas().resultado);
	}

	@Test
	public void testRegistrarBiblioteca() {
		Retorno ret;
		sis.crearSistemaReservas();

		ret = sis.registrarBiblioteca("Biblioteca 1");
		assertEquals(Resultado.OK, ret.resultado);
		ret = sis.registrarBiblioteca("Biblioteca 2");
		assertEquals(Resultado.OK, ret.resultado);
		ret = sis.registrarBiblioteca("Biblioteca 3");
		assertEquals(Resultado.OK, ret.resultado);

		ret = sis.registrarBiblioteca("Biblioteca 1");
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
		ret = sis.registrarBiblioteca("Biblioteca 4");
		assertEquals(Resultado.OK, ret.resultado);
		ret = sis.registrarBiblioteca("Biblioteca 4");
		assertEquals(Resultado.ERROR_1, ret.resultado);
	}

	@Test
	public void testEliminarBiblioteca() {
		Retorno ret;
		sis.crearSistemaReservas();
		
		sis.registrarBiblioteca("Biblioteca 1");
		sis.registrarBiblioteca("Biblioteca 2");
		sis.registrarBiblioteca("Biblioteca 3");
		sis.registrarBiblioteca("Biblioteca 4");
		sis.registrarBiblioteca("Biblioteca 5");

		ret = sis.eliminarBiblioteca("Biblioteca 6");
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
		ret = sis.eliminarBiblioteca("Biblioteca 5");
		assertEquals(Resultado.OK, ret.resultado);
		ret = sis.eliminarBiblioteca("Biblioteca 5");
		assertEquals(Resultado.ERROR_1, ret.resultado);
	}

	@Test
	public void testRegistrarLibro() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		
		ret = sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
		assertEquals(Resultado.OK, ret.resultado);
		
		ret = sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
		ret = sis.registrarLibro("Sapo","Malvin", "Biblioteca Norte", 14);
		assertEquals(Resultado.ERROR_2, ret.resultado);
		
		
	}

	@Test
	public void testEliminarLibro() {
		
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
		
		ret = sis.eliminarLibro("Sapo","Malvin", "Biblioteca Centro");
		assertEquals(Resultado.OK, ret.resultado);
		
		ret = sis.eliminarLibro("Sapo","Malvin2", "Biblioteca Centro");
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
		
	}

	@Test
	public void testRegistrarCalificacion() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
		
		ret = sis.registrarCalificacion("Sapo", "Malvin", 5, "Biblioteca Centro", "NASHE");
		assertEquals(Resultado.OK, ret.resultado);
		
		ret = sis.registrarCalificacion("Sapo", "Malvin23", 5, "Biblioteca Centro", "NASHE");
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
		ret = sis.registrarCalificacion("Sapo", "Malvin", -10, "Biblioteca Centro", "NASHE");
		assertEquals(Resultado.ERROR_2, ret.resultado);
		
		
	}

	@Test
	public void testRealizarReserva() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 1);
		
		ret = sis.realizarReserva("45256646", 0, "Biblioteca Centro", "Sapo", "Malvin", "22/01/2021");
		
		assertEquals(Resultado.OK, ret.resultado);
		
		ret = sis.realizarReserva("48786646", 1, "Biblioteca Centro", "Sapo", "Malvin", "24/01/2021");
		
		assertEquals(Resultado.OK, ret.resultado);
		
		
		ret = sis.realizarReserva("45256646", 0, "Biblioteca Centro", "harry", "Malvin", "22/01/2021");
		
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
		
	}

	@Test
	public void testCancelarReserva() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 1);
		
		sis.realizarReserva("45256646", 0, "Biblioteca Centro", "Sapo", "Malvin", "22/01/2021");
	
		
		ret = sis.cancelarReserva("45256646", 0, "Biblioteca Centro");
		
		assertEquals(Resultado.OK, ret.resultado);
		

		ret = sis.cancelarReserva("45256646", 1, "Biblioteca Centro");
		
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
		
	}

	@Test
	public void testListarLibros() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 1);
		sis.registrarLibro("Sapo2","Malvin", "Biblioteca Centro", 1);
		sis.registrarLibro("Sapo3","Malvin", "Biblioteca Centro", 1);
		sis.registrarLibro("Sapo4","Malvin", "Biblioteca Centro", 1);
		
		
		 ret = sis.listarLibros("Biblioteca Centro");
		assertEquals(Resultado.OK, ret.resultado);
		
		ret = sis.listarLibros("Biblioteca Centro121");
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
	}

	@Test
	public void testListarLibrosBiblioteca() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
		sis.registrarLibro("Sapo2","Malvin2", "Biblioteca Centro", 14);
		sis.registrarLibro("Sapo8","Malvin8", "Biblioteca Centro", 14);
		
		sis.registrarCalificacion("Sapo", "Malvin", 5, "Biblioteca Centro", "NASHE");
		sis.registrarCalificacion("Sapo2", "Malvin2", 3, "Biblioteca Centro", "NASHE");
		sis.registrarCalificacion("Sapo8", "Malvin8", 4, "Biblioteca Centro", "NASHE");
		
		
		ret = sis.listarLibrosBiblioteca("Biblioteca Centro");
		assertEquals(Resultado.OK, ret.resultado);
		
	}

	@Test
	public void testListarBibliotecaRanking() {
		
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		sis.registrarBiblioteca("Biblioteca Centro2");
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
		sis.registrarLibro("Sapo2","Malvin2", "Biblioteca Centro", 14);

		sis.registrarLibro("Sapo8","Malvin8", "Biblioteca Centro2", 14);
		sis.registrarLibro("Sapo7","Malvin7", "Biblioteca Centro2", 14);
		
		sis.registrarCalificacion("Sapo", "Malvin", 5, "Biblioteca Centro", "NASHE");
		sis.registrarCalificacion("Sapo2", "Malvin2", 1, "Biblioteca Centro", "NASHE");
		
		
		
		sis.registrarCalificacion("Sapo8", "Malvin8", 3, "Biblioteca Centro2", "NASHE");
		sis.registrarCalificacion("Sapo7", "Malvin7", 4, "Biblioteca Centro2", "NASHE");
		
		ret = sis.listarBibliotecaRanking();
		
		assertEquals(Resultado.OK, ret.resultado);
	}

	@Test
	public void testListarComentarios() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 14);
		sis.registrarLibro("Sapo2","Malvin2", "Biblioteca Centro", 14);
		sis.registrarLibro("Sapo8","Malvin8", "Biblioteca Centro", 14);
		
		sis.registrarCalificacion("Sapo", "Malvin", 5, "Biblioteca Centro", "com 1");
		sis.registrarCalificacion("Sapo2", "Malvin2", 3, "Biblioteca Centro", "com 2");
		sis.registrarCalificacion("Sapo8", "Malvin8", 4, "Biblioteca Centro", "com 3");
		sis.registrarCalificacion("Sapo", "Malvin", 5, "Biblioteca Centro", "com 4");
		
		
		ret = sis.listarComentarios("Biblioteca Centro");
		assertEquals(Resultado.OK, ret.resultado);
		
		ret = sis.listarComentarios("Biblioteca Centro12");
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
	}

	@Test
	public void testListarEspera() {
		Retorno ret;
		sis.crearSistemaReservas();
		sis.registrarBiblioteca("Biblioteca Centro");
		sis.registrarLibro("Sapo","Malvin", "Biblioteca Centro", 2);
		sis.realizarReserva("45256646", 0, "Biblioteca Centro", "Sapo", "Malvin", "22/01/1992");
		sis.realizarReserva("45256647", 1, "Biblioteca Centro", "Sapo", "Malvin", "22/01/1992");
		sis.realizarReserva("45256648", 2, "Biblioteca Centro", "Sapo", "Malvin", "22/01/1992");
		sis.realizarReserva("45256649", 3, "Biblioteca Centro", "Sapo", "Malvin", "22/01/1992");
		
		
		
		ret = sis.listarEspera("Sapo","Malvin", "Biblioteca Centro");
		assertEquals(Resultado.OK, ret.resultado);
		
		ret = sis.listarEspera("ElSapoooo","Malvin12", "Biblioteca Centro");
		assertEquals(Resultado.ERROR_1, ret.resultado);
		
	}

	@Test
	public void testMostrarReservasBiblioteca() {
		Retorno ret;
		
	
		sis.crearSistemaReservas();
		sis.crearSistemaReservas();
		
		sis.registrarBiblioteca("centro");
		
		sis.registrarBiblioteca("norte");
		sis.registrarBiblioteca("sur");
		
		

		sis.registrarLibro("sapo", "1", "centro", 14);
		
		
		sis.registrarLibro("harry", "1", "centro", 14);
		
		sis.registrarLibro("Flash", "1", "centro", 14);
		
		sis.registrarLibro("Batman", "1", "centro", 14);
		
		sis.registrarLibro("Superman", "1", "centro", 14);
		
		sis.registrarLibro("IRON", "1", "centro", 14);
		
		sis.registrarLibro("cap", "1", "centro", 14);


		sis.registrarLibro("sapo", "1", "norte", 14);
		
		sis.registrarLibro("Superman", "1", "norte", 14);
		
		sis.registrarLibro("IRON", "1", "norte", 14);
		
		sis.registrarLibro("cap", "1", "norte", 14);
	    
       
	
		sis.realizarReserva("12", 1, "centro", "sapo", "1", "010101");
		sis.realizarReserva("122", 2, "centro", "sapo", "1", "010101");
		sis.realizarReserva("12222", 3, "centro", "Flash", "1", "010101");
		sis.realizarReserva("134", 4, "centro", "Flash", "1", "010101");
		sis.realizarReserva("14", 5, "centro", "Flash", "1", "010101");
		sis.realizarReserva("15", 6, "centro", "Superman", "1", "010101");
		sis.realizarReserva("16",7, "centro", "cap", "1", "010101");
		sis.realizarReserva("17", 8, "centro", "sapo", "1", "010101");
		
		
		sis.realizarReserva("123", 0, "norte", "sapo", "1", "010101");
		sis.realizarReserva("1435", 1, "norte", "sapo", "1", "010101");
		sis.realizarReserva("13321", 2, "norte", "sapo", "1", "010101");
		sis.realizarReserva("12342", 3, "norte", "Flash", "1", "010101");
		sis.realizarReserva("143", 4, "norte", "Flash", "1", "010101");
		sis.realizarReserva("15345", 5, "norte", "Flash", "1", "010101");
		sis.realizarReserva("13453", 6, "norte", "Superman", "1", "010101");
		sis.realizarReserva("13457",7, "norte", "cap", "1", "010101");
		sis.realizarReserva("1678967", 8, "norte", "sapo", "1", "010101");
		
		                                                                                                                                 

	
		ret = sis.mostrarReservasBiblioteca();
		assertEquals(Resultado.OK, ret.resultado);
		
	
		

		
	}

}
