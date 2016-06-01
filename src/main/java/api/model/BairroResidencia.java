package api.model;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Bairro Residência", description = "Modelo que representa um Bairro")
public class BairroResidencia implements Serializable {

	private static final long serialVersionUID = -506982896668052070L;
	
	@ApiModelProperty(value = "Código único identificador do Bairro", required = true)
	private Integer codigo;
	
	@ApiModelProperty(value = "Nome do Bairro", required = true)
	private String nome;
	
	@ApiModelProperty(value = "Município do Bairro", required = false)
	private String municipio;

	public BairroResidencia() {
	}

	public BairroResidencia(Integer codigo, String nome, String municipio) {
		this.codigo = codigo;
		this.nome = nome;
		this.municipio = municipio;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BairroResidencia other = (BairroResidencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BairroResidencia [codigo=" + codigo + ", nome=" + nome + "]";
	}

}
