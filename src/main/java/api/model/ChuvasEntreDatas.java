package api.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChuvasEntreDatas implements Serializable{

	private static final long serialVersionUID = 2621150283044485469L;
	
	private Double quantidade;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Timestamp dataChuva;

	public ChuvasEntreDatas(Timestamp dataChuva, Double quantidade) {
		super();
		this.quantidade = quantidade;
		this.dataChuva = dataChuva;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}


	public Timestamp getDataChuva() {
		return dataChuva;
	}

	public void setDataChuva(Timestamp dataChuva) {
		this.dataChuva = dataChuva;
	}

	@Override
	public String toString() {
		return "ChuvasEntreDatas [quantidade=" + quantidade + ", dataChuva=" + dataChuva + "]";
	}

}
