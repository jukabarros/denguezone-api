package api.model;

import java.io.Serializable;

/**
 * Classe que representa as coordenadas do
 * grafico barra eixo x -> mes, eixo y -> qntd de casos
 * para um determinado bairro e ano
 * @author juccelino.barros
 *
 */
public class ValoresPorMesBairro implements Serializable{

	private static final long serialVersionUID = 5262951937844285036L;
	
	private int mesNumero;
	
	private int qntdCasos;

	public ValoresPorMesBairro() {
		super();
	}

	public ValoresPorMesBairro(int mesNumero, int qntdCasos) {
		super();
		this.mesNumero = mesNumero;
		this.qntdCasos = qntdCasos;
	}

	public int getMesNumero() {
		return mesNumero;
	}

	public void setMesNumero(int mesNumero) {
		this.mesNumero = mesNumero;
	}

	public int getQntdCasos() {
		return qntdCasos;
	}

	public void setQntdCasos(int qntdCasos) {
		this.qntdCasos = qntdCasos;
	}
	
}
