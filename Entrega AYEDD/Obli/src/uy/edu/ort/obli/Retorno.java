package uy.edu.ort.obli;

public class Retorno {
	public enum Resultado {
		OK, ERROR_1, ERROR_2, ERROR_3, ERROR_4, ERROR_5, NO_IMPLEMENTADA
	};

	public int valorEntero;
	public String valorString;
	public boolean valorbooleano;
	public Resultado resultado;

	public Retorno() {
		this.resultado = Resultado.NO_IMPLEMENTADA;
	}

	public Retorno(Resultado resultado) {
		this.resultado = resultado;
	}
	
	public Retorno(Resultado resultado, int valorEntero, String valorString) {
		this.resultado = resultado;
		this.valorEntero = valorEntero;
		this.valorString = valorString;
	}

}
