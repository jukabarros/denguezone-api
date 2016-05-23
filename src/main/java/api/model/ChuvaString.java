package api.model;

import java.io.Serializable;

public class ChuvaString implements Serializable {

	private static final long serialVersionUID = 1484331216226994484L;

	private String codigo;
	
	private String estacao;
	
	private String data;
	
	private String hora;
	
	private String precipitacao;

	public ChuvaString(String estacao, String data, String hora, String precipitacao) {
		super();
		this.estacao = estacao;
		this.data = data;
		this.hora = hora;
		this.precipitacao = precipitacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getPrecipitacao() {
		return precipitacao;
	}

	public void setPrecipitacao(String precipitacao) {
		this.precipitacao = precipitacao;
	}


	public String getEstacao() {
		return estacao;
	}

	public void setEstacao(String estacao) {
		this.estacao = estacao;
	}

	@Override
	public String toString() {
		return "ChuvaString [codigo=" + codigo + ", data=" + data + ", precipitacao=" + precipitacao + "]";
	}
	
}
