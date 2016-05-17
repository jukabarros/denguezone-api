package api.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CasosAedesEntreDatas implements Serializable{

	private static final long serialVersionUID = 2621150283044485469L;
	
	private Integer quantidade;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Timestamp dataNotificacao;

	public CasosAedesEntreDatas(Integer quantidade, Timestamp dataNotificacao) {
		super();
		this.quantidade = quantidade;
		this.dataNotificacao = dataNotificacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Timestamp getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(Timestamp dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}

	@Override
	public String toString() {
		return "CasosAedesByDate [quantidade=" + quantidade + ", dataNotificacao=" + dataNotificacao + "]";
	}

}
