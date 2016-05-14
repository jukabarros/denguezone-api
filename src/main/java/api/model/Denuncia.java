package api.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe que representa uma denuncia do sistema.
 * @author juccelino.barros
 *
 */
public class Denuncia implements Serializable {

	private static final long serialVersionUID = -5354631791447498576L;
	private Integer id;
	private String protocolo;
	private String bairro;
	private String endereco;
	private String descricao;
	private String status;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Timestamp dataCriacao;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Timestamp dataAtualizacao;
	
	public Denuncia() {
		super();
	}

	public Denuncia(Integer id, String protocolo, String bairro, String endereco, String descricao, String status,
			Timestamp dataCriacao, Timestamp dataAtualizacao) {
		super();
		this.id = id;
		this.protocolo = protocolo;
		this.bairro = bairro;
		this.endereco = endereco;
		this.descricao = descricao;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	@Override
	public String toString() {
		return "Denuncia [id=" + id + ", bairro=" + bairro + ", status=" + status + "]";
	}
	
}
