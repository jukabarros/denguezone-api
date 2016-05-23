package api.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Chuva implements Serializable {

	private static final long serialVersionUID = -5024415592547229878L;
	
	private Integer id;
	
	private String estacao;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Timestamp data;
	
	private Integer hora;
	
	private double precipitacao;

	public Chuva(Integer id, String estacao, Timestamp data, Integer hora, double precipitacao) {
		super();
		this.id = id;
		this.estacao = estacao;
		this.data = data;
		this.hora = hora;
		this.precipitacao = precipitacao;
	}

	public Chuva() {
		super();
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstacao() {
		return estacao;
	}

	public void setEstacao(String estacao) {
		this.estacao = estacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public double getPrecipitacao() {
		return precipitacao;
	}

	public void setPrecipitacao(double precipitacao) {
		this.precipitacao = precipitacao;
	}

	@Override
	public String toString() {
		return "Chuva [codigo=" + estacao + ", data=" + data + ", precipitacao=" + precipitacao + "]";
	}
	

}
