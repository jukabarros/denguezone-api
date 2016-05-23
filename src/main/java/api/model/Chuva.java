package api.model;

import java.io.Serializable;
import java.util.Date;

public class Chuva implements Serializable {

	private static final long serialVersionUID = -5024415592547229878L;
	
	private Integer id;
	
	private String codigo;
	
	private Date data;
	
	private Integer hora;
	
	private double precipitacao;

	public Chuva(Integer id, String codigo, Date data, Integer hora, double precipitacao) {
		super();
		this.id = id;
		this.codigo = codigo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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
		return "Chuva [codigo=" + codigo + ", data=" + data + ", precipitacao=" + precipitacao + "]";
	}
	

}
